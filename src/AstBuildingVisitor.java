package src; // adjust package as needed

import danex.ast.*;       // ensure AST node classes live here
import danex.grammar.*;   // adjust if your parser package differs
import danex.*;           // for AstBuilder

import java.util.ArrayList;
import java.util.List;

public class AstBuildingVisitor extends DanexParserBaseVisitor<Object> {
    private final AstBuilder builder;

    public AstBuildingVisitor(AstBuilder builder) {
        this.builder = builder;
    }

    /**
     * Top-level: collect declarations (ImportDecl, ClassDecl, MethodDecl).
     * Grammar: compilationUnit: statement* EOF;
     * statement: classDecl | methodDecl | topLevelMethodDecl | importStmt;
     */
    @Override
    public Object visitCompilationUnit(DanexParser.CompilationUnitContext ctx) {
        List<Decl> decls = new ArrayList<>();
        for (var stmtCtx : ctx.statement()) {
            Object result = visit(stmtCtx);
            if (result instanceof Decl) {
                decls.add((Decl) result);
            } else {
                // Should not happen: top-level statements are declarations in this grammar
                throw new RuntimeException("Expected declaration at top-level, got: " + result);
            }
        }
        return decls;
    }

    // -------------------
    // Import statements
    // -------------------
    @Override
    public Object visitImportStmt(DanexParser.ImportStmtContext ctx) {
        String moduleName = ctx.IDENTIFIER(0).getText();
        String alias = ctx.IDENTIFIER().size() > 1 ? ctx.IDENTIFIER(1).getText() : null;
        ImportDecl importNode = new ImportDecl(moduleName, alias);
        return builder.visitImportDecl(importNode);
    }

    // -------------------
    // Class declarations
    // -------------------
    @Override
    public Object visitClassDecl(DanexParser.ClassDeclContext ctx) {
        // Annotations
        List<Annotation> annotations = new ArrayList<>();
        for (var annCtx : ctx.annotation()) {
            Object annObj = visit(annCtx);
            if (annObj instanceof Annotation) {
                annotations.add((Annotation) annObj);
            }
        }
        // Modifiers
        List<String> modifiers = new ArrayList<>();
        for (var m : ctx.modifier()) {
            modifiers.add(m.getText());
        }
        // Name
        String name = ctx.IDENTIFIER().getText();

        // Members: grammar says classBodyMember: methodDecl | classDecl
        List<Decl> members = new ArrayList<>();
        for (var memberCtx : ctx.classBody().classBodyMember()) {
            Object memberObj = visit(memberCtx);
            if (memberObj instanceof Decl) {
                members.add((Decl) memberObj);
            } else {
                throw new RuntimeException("Expected Decl inside class body, got: " + memberObj);
            }
        }

        ClassDecl classNode = new ClassDecl(name, annotations, modifiers, members);
        return builder.visitClassDecl(classNode);
    }

    // classBodyMember: methodDecl | classDecl
    @Override
    public Object visitClassBodyMember(DanexParser.ClassBodyMemberContext ctx) {
        if (ctx.methodDecl() != null) {
            return visit(ctx.methodDecl());
        } else if (ctx.classDecl() != null) {
            return visit(ctx.classDecl());
        } else {
            throw new RuntimeException("Unknown classBodyMember: " + ctx.getText());
        }
    }

    // -------------------
    // Method declarations
    // -------------------
    @Override
    public Object visitMethodDecl(DanexParser.MethodDeclContext ctx) {
        return buildMethod(ctx);
    }

    @Override
    public Object visitTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx) {
        // Grammar wraps a MethodDecl inside TopLevelMethodDecl
        DanexParser.MethodDeclContext inner = ctx.methodDecl();
        return buildMethod(inner);
    }

    /**
     * Build MethodDecl AST node, then pass to builder.visitMethodDecl.
     * Fields: name, resultType (or null), resultName (or null), annotations, modifiers, params, body (Stmt).
     */
    private Decl buildMethod(DanexParser.MethodDeclContext ctx) {
        // Annotations
        List<Annotation> annotations = new ArrayList<>();
        for (var annCtx : ctx.annotation()) {
            Object annObj = visit(annCtx);
            if (annObj instanceof Annotation) {
                annotations.add((Annotation) annObj);
            }
        }
        // Modifiers
        List<String> modifiers = new ArrayList<>();
        for (var m : ctx.modifier()) {
            modifiers.add(m.getText());
        }
        // Name
        String name = ctx.IDENTIFIER().getText();

        // Result declaration: (Type IDENTIFIER?)?
        String resultType = null;
        String resultName = null;
        if (ctx.resultDecl() != null) {
            // resultDecl: LPAREN type IDENTIFIER? RPAREN
            resultType = ctx.resultDecl().type().getText();
            if (ctx.resultDecl().IDENTIFIER() != null) {
                // If grammar allows optional IDENTIFIER
                resultName = ctx.resultDecl().IDENTIFIER().getText();
            } else {
                resultName = null;
            }
        }

        // Parameters: List<Param>
        List<Param> params = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (var pCtx : ctx.paramList().param()) {
                String pType = pCtx.type().getText();
                String pName;
                boolean varargs = false;
                if (pCtx.VARARGS() != null) {
                    // grammar: type VARARGS IDENTIFIER
                    varargs = true;
                    pName = pCtx.IDENTIFIER().getText();
                } else {
                    // grammar: type IDENTIFIER
                    pName = pCtx.IDENTIFIER().getText();
                }
                Param paramNode = new Param(pType, pName, varargs);
                // builder.visitParam if desired; but usually visitor constructs AST, then builder builds children:
                paramNode = (Param) builder.visitParam(paramNode);
                params.add(paramNode);
            }
        }

        // Body: visit methodBody
        Stmt bodyStmt;
        if (ctx.methodBody().block() != null) {
            Object bObj = visit(ctx.methodBody().block());
            if (!(bObj instanceof Stmt)) {
                throw new RuntimeException("Expected Stmt from block, got: " + bObj);
            }
            bodyStmt = (Stmt) bObj;
        } else {
            // Arrow form: ctx.methodBody().ARROW expression SEMI
            // We must wrap into a BlockStmt with a single assignment to resultName or methodName.
            Expr expr = (Expr) visit(ctx.methodBody().expression());
            // Determine assignment target: if resultName != null, assign to resultName; else assign to method name
            String targetName = (resultName != null) ? resultName : name;
            // Build AssignExpr: targetName = expr
            AssignExpr assign = new AssignExpr(targetName, expr);
            assign = (AssignExpr) builder.visitAssignExpr(assign);
            // Wrap into ExpressionStmt
            ExprStmt exprStmt = new ExprStmt(assign);
            exprStmt = (ExprStmt) builder.visitExprStmt(exprStmt);
            // Wrap into BlockStmt
            List<Stmt> stmts = new ArrayList<>();
            stmts.add(exprStmt);
            BlockStmt block = new BlockStmt(stmts);
            bodyStmt = (BlockStmt) builder.visitBlockStmt(block);
        }

        MethodDecl methodNode = new MethodDecl(name, resultType, resultName, annotations, modifiers, params, bodyStmt);
        return builder.visitMethodDecl(methodNode);
    }

    // -------------------
    // Blocks
    // -------------------
    @Override
    public Object visitBlock(DanexParser.BlockContext ctx) {
        // block: LBRACE blockContent* RBRACE
        List<Stmt> stmts = new ArrayList<>();
        for (var bc : ctx.blockContent()) {
            Object o = visit(bc);
            if (o instanceof Stmt) {
                stmts.add((Stmt) o);
            } else {
                throw new RuntimeException("Expected Stmt in blockContent, got: " + o);
            }
        }
        BlockStmt blockNode = new BlockStmt(stmts);
        return builder.visitBlockStmt(blockNode);
    }

    @Override
    public Object visitBlockContent(DanexParser.BlockContentContext ctx) {
        if (ctx.statement() != null) {
            Object stmtObj = visit(ctx.statement());
            if (stmtObj instanceof Stmt) return stmtObj;
            if (stmtObj instanceof Decl) {
                // Declarations inside block? Typically not allowed per grammar. Error:
                throw new RuntimeException("Declaration not allowed here: " + stmtObj);
            }
            throw new RuntimeException("Expected Stmt in blockContent, got: " + stmtObj);
        } else if (ctx.expressionStatement() != null) {
            return visit(ctx.expressionStatement());
        } else if (ctx.ifStatement() != null) {
            return visit(ctx.ifStatement());
        } else if (ctx.whileStatement() != null) {
            return visit(ctx.whileStatement());
        } else if (ctx.doWhileStatement() != null) {
            return visit(ctx.doWhileStatement());
        } else if (ctx.forStatement() != null) {
            return visit(ctx.forStatement());
        } else if (ctx.tryStatement() != null) {
            return visit(ctx.tryStatement());
        } else if (ctx.exitStatement() != null) {
            return visit(ctx.exitStatement());
        } else if (ctx.throwStatement() != null) {
            return visit(ctx.throwStatement());
        } else if (ctx.returnStatement() != null) {
            return visit(ctx.returnStatement());
        } else {
            throw new RuntimeException("Unknown blockContent: " + ctx.getText());
        }
    }

    // -------------------
    // If / While / DoWhile / For
    // -------------------
    @Override
    public Object visitIfStatement(DanexParser.IfStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt thenBranch = (Stmt) visit(ctx.block(0));
        Stmt elseBranch = null;
        if (ctx.ELSE() != null) {
            elseBranch = (Stmt) visit(ctx.block(1));
        }
        IfStmt ifNode = new IfStmt(condition, thenBranch, elseBranch);
        return builder.visitIfStmt(ifNode);
    }

    @Override
    public Object visitWhileStatement(DanexParser.WhileStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt body = (Stmt) visit(ctx.block());
        WhileStmt whileNode = new WhileStmt(condition, body);
        return builder.visitWhileStmt(whileNode);
    }

    @Override
    public Object visitDoWhileStatement(DanexParser.DoWhileStatementContext ctx) {
        Stmt body = (Stmt) visit(ctx.block());
        Expr condition = (Expr) visit(ctx.expression());
        DoWhileStmt doNode = new DoWhileStmt(body, condition);
        return builder.visitDoWhileStmt(doNode);
    }

    @Override
    public Object visitForStatement(DanexParser.ForStatementContext ctx) {
        // forStatement: FOR LPAREN assignment SEMI expression SEMI assignment RPAREN block
        // Note: init and update are assignments, but can be null if grammar allows?
        Stmt initStmt = null;
        if (ctx.assignment(0) != null) {
            // assignment returns Expr; wrap into ExprStmt if needed?
            Expr initExpr = (Expr) visit(ctx.assignment(0));
            initStmt = new ExprStmt(initExpr);
            initStmt = (ExprStmt) builder.visitExprStmt((ExprStmt)initStmt);
        }
        Expr condition = (Expr) visit(ctx.expression());
        Stmt body = (Stmt) visit(ctx.block());
        Stmt updateStmt = null;
        if (ctx.assignment(1) != null) {
            Expr updateExpr = (Expr) visit(ctx.assignment(1));
            updateStmt = new ExprStmt(updateExpr);
            updateStmt = (ExprStmt) builder.visitExprStmt((ExprStmt)updateStmt);
        }
        // Our ForStmt AST: ForStmt(Stmt init, Expr condition, Expr update, Stmt body)
        // But since updateStmt is an ExprStmt wrapping an AssignExpr, for interpreter we can evaluate via evaluate().
        ForStmt forNode = new ForStmt(initStmt, condition, updateStmt == null ? null : ((ExprStmt)updateStmt).expression, body);
        return builder.visitForStmt(forNode);
    }

    // -------------------
    // Throw, Return, Exit
    // -------------------
    @Override
    public Object visitThrowStatement(DanexParser.ThrowStatementContext ctx) {
        Expr value = (Expr) visit(ctx.expression());
        ThrowStmt throwNode = new ThrowStmt(value);
        return builder.visitThrowStmt(throwNode);
    }

    @Override
    public Object visitReturnStatement(DanexParser.ReturnStatementContext ctx) {
        Expr value = null;
        if (ctx.expression() != null) {
            value = (Expr) visit(ctx.expression());
        }
        ReturnStmt returnNode = new ReturnStmt(value);
        return builder.visitReturnStmt(returnNode);
    }

    @Override
    public Object visitExitStatement(DanexParser.ExitStatementContext ctx) {
        ExitStmt exitNode = new ExitStmt();
        return builder.visitExitStmt(exitNode);
    }

    // -------------------
    // Expression statements
    // -------------------
    @Override
    public Object visitExpressionStatement(DanexParser.ExpressionStatementContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        ExprStmt exprStmt = new ExprStmt(expr);
        return builder.visitExpressionStmt(exprStmt);
    }

    // -------------------
    // Assignment & binary/unary
    // -------------------
    @Override
    public Object visitAssignment(DanexParser.AssignmentContext ctx) {
        // assignment: IDENTIFIER assignOp assignment
        String name = ctx.IDENTIFIER().getText();
        String op = ctx.assignOp().getText(); // "=", "+=", etc.
        Expr value = (Expr) visit(ctx.assignment());
        AssignExpr assignNode;
        if ("=".equals(op)) {
            assignNode = new AssignExpr(name, value);
        } else {
            // e.g., a += b  -> rewrite to a = a + b
            String binaryOp = op.substring(0, op.length() - 1); // "+", "-", "*", "/", "%"
            // Build: BinaryExpr(VariableExpr(name), binaryOp, value)
            VariableExpr leftVar = new VariableExpr(name);
            leftVar = (VariableExpr) builder.visitVariableExpr(leftVar);
            BinaryExpr bin = new BinaryExpr(leftVar, binaryOp, value);
            bin = (BinaryExpr) builder.visitBinaryExpr(bin);
            assignNode = new AssignExpr(name, bin);
        }
        return builder.visitAssignExpr(assignNode);
    }

    @Override
    public Object visitLogicalOrExpr(DanexParser.LogicalOrExprContext ctx) {
        if (ctx.logicalAndExpr().size() == 1) {
            return visit(ctx.logicalAndExpr(0));
        }
        Expr left = (Expr) visit(ctx.logicalAndExpr(0));
        for (int i = 1; i < ctx.logicalAndExpr().size(); i++) {
            String op = ctx.OR_OR(i - 1).getText();
            Expr right = (Expr) visit(ctx.logicalAndExpr(i));
            BinaryExpr bin = new BinaryExpr(left, op, right);
            left = (BinaryExpr) builder.visitBinaryExpr(bin);
        }
        return left;
    }

    @Override
    public Object visitLogicalAndExpr(DanexParser.LogicalAndExprContext ctx) {
        if (ctx.nullCoalesceExpr().size() == 1) {
            return visit(ctx.nullCoalesceExpr(0));
        }
        Expr left = (Expr) visit(ctx.nullCoalesceExpr(0));
        for (int i = 1; i < ctx.nullCoalesceExpr().size(); i++) {
            String op = ctx.AND_AND(i - 1).getText();
            Expr right = (Expr) visit(ctx.nullCoalesceExpr(i));
            BinaryExpr bin = new BinaryExpr(left, op, right);
            left = (BinaryExpr) builder.visitBinaryExpr(bin);
        }
        return left;
    }

    @Override
    public Object visitNullCoalesceExpr(DanexParser.NullCoalesceExprContext ctx) {
        if (ctx.comparisonExpr().size() == 1) {
            return visit(ctx.comparisonExpr(0));
        }
        Expr left = (Expr) visit(ctx.comparisonExpr(0));
        for (int i = 1; i < ctx.comparisonExpr().size(); i++) {
            Expr right = (Expr) visit(ctx.comparisonExpr(i));
            NullCoalesceExpr nc = new NullCoalesceExpr(left, right);
            left = (NullCoalesceExpr) builder.visitNullCoalesceExpr(nc);
        }
        return left;
    }

    @Override
    public Object visitComparisonExpr(DanexParser.ComparisonExprContext ctx) {
        if (ctx.additiveExpr().size() == 1) {
            return visit(ctx.additiveExpr(0));
        }
        Expr left = (Expr) visit(ctx.additiveExpr(0));
        for (int i = 1; i < ctx.additiveExpr().size(); i++) {
            String op = ctx.getChild(2*i - 1).getText(); // operator token
            Expr right = (Expr) visit(ctx.additiveExpr(i));
            ComparatorExpr cmp = new ComparatorExpr(left, right);
            // Note: ComparatorExpr AST may assume operator "<=>"? But grammar allows multiple comparison operators.
            // If you want to support <, >, <=, >=, ==, !=: you'd need a more general BinaryExpr.
            // For simplicity, transform non-<=> comparisons into BinaryExpr:
            if ("<=>".equals(op)) {
                cmp = new ComparatorExpr(left, right);
                left = (ComparatorExpr) builder.visitComparatorExpr(cmp);
            } else {
                BinaryExpr bin = new BinaryExpr(left, op, right);
                left = (BinaryExpr) builder.visitBinaryExpr(bin);
            }
        }
        return left;
    }

    @Override
    public Object visitAdditiveExpr(DanexParser.AdditiveExprContext ctx) {
        if (ctx.multiplicativeExpr().size() == 1) {
            return visit(ctx.multiplicativeExpr(0));
        }
        Expr left = (Expr) visit(ctx.multiplicativeExpr(0));
        for (int i = 1; i < ctx.multiplicativeExpr().size(); i++) {
            String op = ctx.getChild(2*i - 1).getText();
            Expr right = (Expr) visit(ctx.multiplicativeExpr(i));
            BinaryExpr bin = new BinaryExpr(left, op, right);
            left = (BinaryExpr) builder.visitBinaryExpr(bin);
        }
        return left;
    }

    @Override
    public Object visitMultiplicativeExpr(DanexParser.MultiplicativeExprContext ctx) {
        if (ctx.unaryExpr().size() == 1) {
            return visit(ctx.unaryExpr(0));
        }
        Expr left = (Expr) visit(ctx.unaryExpr(0));
        for (int i = 1; i < ctx.unaryExpr().size(); i++) {
            String op = ctx.getChild(2*i - 1).getText();
            Expr right = (Expr) visit(ctx.unaryExpr(i));
            BinaryExpr bin = new BinaryExpr(left, op, right);
            left = (BinaryExpr) builder.visitBinaryExpr(bin);
        }
        return left;
    }

    @Override
    public Object visitUnaryExpr(DanexParser.UnaryExprContext ctx) {
        // unaryExpr: (BANG | PLUS | MINUS | AWAIT)* primaryExpr
        Expr expr = (Expr) visit(ctx.primaryExpr());
        // Apply prefixes in reverse order
        for (int i = ctx.getChildCount() - 2; i >= 0; i--) {
            String op = ctx.getChild(i).getText();
            UnaryExpr u = new UnaryExpr(op, expr);
            expr = (UnaryExpr) builder.visitUnaryExpr(u);
        }
        return expr;
    }

    // -------------------
    // Primary expressions
    // -------------------
    @Override
    public Object visitPrimaryExpr(DanexParser.PrimaryExprContext ctx) {
        if (ctx.expression() != null) {
            return visit(ctx.expression());
        }
        if (ctx.doExpression() != null) {
            return visit(ctx.doExpression());
        }
        if (ctx.tryExpression() != null) {
            return visit(ctx.tryExpression());
        }
        if (ctx.functionCall() != null) {
            return visit(ctx.functionCall());
        }
        if (ctx.literal() != null) {
            return visit(ctx.literal());
        }
        if (ctx.IDENTIFIER() != null) {
            VariableExpr varNode = new VariableExpr(ctx.IDENTIFIER().getText());
            return builder.visitVariableExpr(varNode);
        }
        throw new RuntimeException("Unknown primaryExpr: " + ctx.getText());
    }

    @Override
    public Object visitFunctionCall(DanexParser.FunctionCallContext ctx) {
        // functionCall: IDENTIFIER LPAREN (expression (COMMA expression)*)? RPAREN
        String name = ctx.IDENTIFIER().getText();
        // Build callee as VariableExpr
        VariableExpr callee = new VariableExpr(name);
        callee = (VariableExpr) builder.visitVariableExpr(callee);

        List<Expr> args = new ArrayList<>();
        if (ctx.expression() != null && !ctx.expression().isEmpty()) {
            for (var eCtx : ctx.expression()) {
                Expr arg = (Expr) visit(eCtx);
                args.add(arg);
            }
        }
        CallExpr callNode = new CallExpr(callee, args);
        return builder.visitCallExpr(callNode);
    }

    @Override
    public Object visitLiteral(DanexParser.LiteralContext ctx) {
        Object value;
        if (ctx.NUMBER() != null) {
            // parse number as Double
            value = Double.parseDouble(ctx.NUMBER().getText());
        } else if (ctx.STRING() != null) {
            // strip quotes? Assuming AST stores raw string literal including quotes or unquoted?
            String text = ctx.STRING().getText();
            // Remove surrounding quotes and unescape if desired. For simplicity, store the raw string literal text:
            value = text.substring(1, text.length() - 1);
        } else if (ctx.TRUE() != null) {
            value = Boolean.TRUE;
        } else if (ctx.FALSE() != null) {
            value = Boolean.FALSE;
        } else if (ctx.NULL() != null) {
            value = null;
        } else {
            throw new RuntimeException("Unknown literal: " + ctx.getText());
        }
        LiteralExpr lit = new LiteralExpr(value);
        return builder.visitLiteralExpr(lit);
    }

    // -------------------
    // Do-expression
    // -------------------
    @Override
    public Object visitDoExpression(DanexParser.DoExpressionContext ctx) {
        // doExpression: DO block
        BlockStmt block = (BlockStmt) visit(ctx.block());
        // AST DoExpr expects List<Stmt>
        DoExpr doNode = new DoExpr(block.statements);
        return builder.visitDoExpr(doNode);
    }

    // -------------------
    // Try-expression
    // -------------------
    @Override
    public Object visitTryExpression(DanexParser.TryExpressionContext ctx) {
        // tryExpression: TRY block (CATCH (...)? block)? (FINALLY block)?
        BlockStmt tryBlock = (BlockStmt) visit(ctx.block(0));

        String catchType = null;
        String catchName = null;
        List<Stmt> catchStmts = null;
        if (ctx.CATCH() != null) {
            catchType = ctx.IDENTIFIER(0).getText();
            catchName = ctx.IDENTIFIER(1).getText();
            BlockStmt catchBlock = (BlockStmt) visit(ctx.block(1));
            catchStmts = catchBlock.statements;
        }

        List<Stmt> finallyStmts = null;
        if (ctx.FINALLY() != null) {
            int lastIdx = ctx.block().size() - 1;
            BlockStmt finallyBlock = (BlockStmt) visit(ctx.block(lastIdx));
            finallyStmts = finallyBlock.statements;
        }

        TryExpr tryNode = new TryExpr(tryBlock.statements, catchType, catchName,
                                      catchStmts, finallyStmts);
        return builder.visitTryExpr(tryNode);
    }

    // -------------------
    // Try-statement (with optional resources)
    // -------------------
    @Override
    public Object visitTryStatement(DanexParser.TryStatementContext ctx) {
        // tryStatement: TRY (resourceSpec)? block (CATCH LPAREN IDENTIFIER IDENTIFIER RPAREN block)? (FINALLY block)?;
        List<ResourceDecl> resources = new ArrayList<>();
        if (ctx.resourceSpec() != null) {
            for (var resCtx : ctx.resourceSpec().resourceDecl()) {
                ResourceDecl rd = buildResourceDecl(resCtx);
                rd = (ResourceDecl) builder.visitResourceDecl(rd);
                resources.add(rd);
            }
        }
        BlockStmt tryBlock = (BlockStmt) visit(ctx.block(0));

        String catchType = null;
        String catchName = null;
        List<Stmt> catchStmts = null;
        if (ctx.CATCH() != null) {
            catchType = ctx.IDENTIFIER(0).getText();
            catchName = ctx.IDENTIFIER(1).getText();
            BlockStmt catchBlock = (BlockStmt) visit(ctx.block(1));
            catchStmts = catchBlock.statements;
        }

        List<Stmt> finallyStmts = null;
        if (ctx.FINALLY() != null) {
            int lastIdx = ctx.block().size() - 1;
            BlockStmt finallyBlock = (BlockStmt) visit(ctx.block(lastIdx));
            finallyStmts = finallyBlock.statements;
        }

        TryStmt tryNode = new TryStmt(resources, tryBlock.statements,
                                      catchType, catchName,
                                      catchStmts, finallyStmts);
        return builder.visitTryStmt(tryNode);
    }

    private ResourceDecl buildResourceDecl(DanexParser.ResourceDeclContext ctx) {
        // resourceDecl: type IDENTIFIER EQ expression
        String type = ctx.type().getText();
        String name = ctx.IDENTIFIER().getText();
        Expr initExpr = (Expr) visit(ctx.expression());
        return new ResourceDecl(type, name, initExpr);
    }

    // -------------------
    // Annotation
    // -------------------
    @Override
    public Object visitAnnotation(DanexParser.AnnotationContext ctx) {
        // annotation: HASH IDENTIFIER
        String name = ctx.IDENTIFIER().getText();
        Annotation ann = new Annotation(name);
        return builder.visitAnnotation(ann);
    }

    // -------------------
    // Type: just return text
    // -------------------
    @Override
    public Object visitType(DanexParser.TypeContext ctx) {
        return ctx.getText();
    }

    // -------------------
    // Other rules: you may need to implement more visitor methods per grammar.
    // -------------------

    // For rules not explicitly overridden, the default BaseVisitor will recurse.
    // If some rules need special handling, override them similarly.
            }
