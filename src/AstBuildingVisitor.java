// File: src/danex/AstBuildingVisitor.java
package danex;

import danex.ast.*;         // AST node classes: Annotation, Param, ExprStmt, ResourceDecl, etc.
import danex.*;             // AstBuilder, RuntimeError, etc.
import danex.grammar.*;     // ANTLR-generated parser/lexer classes
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor that walks the ANTLR parse tree and builds AST Decl nodes via AstBuilder.
 */
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

        // Members: classBodyMember: methodDecl | classDecl
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
    return buildTopLevelMethod(ctx);
}

/**
 * Helper for MethodDeclContext.
 */
private Decl buildMethod(DanexParser.MethodDeclContext ctx) {
    List<Annotation> annotations = buildAnnotations(ctx.annotation());
    List<String> modifiers = buildModifiers(ctx.modifier());

    if (ctx.IDENTIFIER() == null) {
        throw new RuntimeException("MethodDecl is missing a name.");
    }
    String name = ctx.IDENTIFIER().getText();

    String resultType = null;
    String resultName = null;
    if (ctx.resultDecl() != null) {
        resultType = ctx.resultDecl().type().getText();
        if (ctx.resultDecl().IDENTIFIER() != null) {
            resultName = ctx.resultDecl().IDENTIFIER().getText();
        }
    }

    List<Param> params = buildParams(ctx.paramList());
    Stmt bodyStmt = buildMethodBody(name, resultName, ctx.methodBody());

    MethodDecl methodNode = new MethodDecl(name, resultType, resultName, annotations, modifiers, params, bodyStmt);
    
    if (methodNode.name == null) {
        throw new RuntimeException("Built method has null name.");
    }
    System.out.println("[DEBUG] Built method: " + methodNode.name);
    
    return builder.visitMethodDecl(methodNode);
}

private Decl buildTopLevelMethod(DanexParser.TopLevelMethodDeclContext ctx) {
    List<Annotation> annotations = buildAnnotations(ctx.annotation());
    List<String> modifiers = buildModifiers(ctx.modifier());

    if (ctx.IDENTIFIER() == null) {
        throw new RuntimeException("TopLevelMethodDecl is missing a name.");
    }
    String name = ctx.IDENTIFIER().getText();

    String resultType = null;
    String resultName = null;
    if (ctx.resultDecl() != null) {
        resultType = ctx.resultDecl().type().getText();
        if (ctx.resultDecl().IDENTIFIER() != null) {
            resultName = ctx.resultDecl().IDENTIFIER().getText();
        }
    }

    List<Param> params = buildParams(ctx.paramList());
    Stmt bodyStmt = buildMethodBody(name, resultName, ctx.methodBody());

    MethodDecl methodNode = new MethodDecl(name, resultType, resultName, annotations, modifiers, params, bodyStmt);

    if (methodNode.name == null) {
        throw new RuntimeException("Built top-level method has null name.");
    }
    System.out.println("[DEBUG] Built top-level method: " + methodNode.name);

    return builder.visitMethodDecl(methodNode);
}
    
    // -------------------
    // Blocks
    // -------------------
    @Override
    public Object visitBlock(DanexParser.BlockContext ctx) {
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
        // init
        Stmt initStmt = null;
        if (ctx.assignment(0) != null) {
            Expr initExpr = (Expr) visit(ctx.assignment(0));
            ExprStmt eStmt = new ExprStmt(initExpr);
            initStmt = (ExprStmt) builder.visitExprStmt(eStmt);
        }
        Expr condition = (Expr) visit(ctx.expression());
        Stmt body = (Stmt) visit(ctx.block());
        Stmt updateStmt = null;
        if (ctx.assignment(1) != null) {
            Expr updateExpr = (Expr) visit(ctx.assignment(1));
            ExprStmt uStmt = new ExprStmt(updateExpr);
            updateStmt = (ExprStmt) builder.visitExprStmt(uStmt);
        }
        // ForStmt constructor: (Stmt init, Expr condition, Expr update, Stmt body)
        Expr updateExpr = null;
        if (updateStmt != null && updateStmt instanceof ExprStmt) {
            updateExpr = ((ExprStmt) updateStmt).expression;
        }
        ForStmt forNode = new ForStmt(initStmt, condition, updateExpr, body);
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
        return builder.visitExprStmt(exprStmt);
    }

    // -------------------
    // Assignment & binary/unary
    // -------------------
    @Override
    public Object visitAssignment(DanexParser.AssignmentContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        String op = ctx.assignOp().getText();
        Expr value = (Expr) visit(ctx.assignment());
        AssignExpr assignNode;
        if ("=".equals(op)) {
            assignNode = new AssignExpr(name, value);
        } else {
            // e.g., a += b
            String binOp = op.substring(0, op.length() - 1);
            VariableExpr leftVar = new VariableExpr(name);
            leftVar = (VariableExpr) builder.visitVariableExpr(leftVar);
            BinaryExpr bin = new BinaryExpr(leftVar, binOp, value);
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
            String op = ctx.getChild(2 * i - 1).getText();
            Expr right = (Expr) visit(ctx.additiveExpr(i));
            if ("<=>".equals(op)) {
                ComparatorExpr cmp = new ComparatorExpr(left, right);
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
            String op = ctx.getChild(2 * i - 1).getText();
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
            String op = ctx.getChild(2 * i - 1).getText();
            Expr right = (Expr) visit(ctx.unaryExpr(i));
            BinaryExpr bin = new BinaryExpr(left, op, right);
            left = (BinaryExpr) builder.visitBinaryExpr(bin);
        }
        return left;
    }

    @Override
    public Object visitUnaryExpr(DanexParser.UnaryExprContext ctx) {
        Expr expr = (Expr) visit(ctx.primaryExpr());
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
        String name = ctx.IDENTIFIER().getText();
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
            value = Double.parseDouble(ctx.NUMBER().getText());
        } else if (ctx.STRING() != null) {
            String text = ctx.STRING().getText();
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
        BlockStmt block = (BlockStmt) visit(ctx.block());
        DoExpr doNode = new DoExpr(block.statements);
        return builder.visitDoExpr(doNode);
    }

    // -------------------
    // Try-expression
    // -------------------
    @Override
    public Object visitTryExpression(DanexParser.TryExpressionContext ctx) {
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
        TryExpr tryNode = new TryExpr(tryBlock.statements, catchType, catchName, catchStmts, finallyStmts);
        return builder.visitTryExpr(tryNode);
    }

    // -------------------
    // Try-statement
    // -------------------
    @Override
    public Object visitTryStatement(DanexParser.TryStatementContext ctx) {
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
        TryStmt tryNode = new TryStmt(resources, tryBlock.statements, catchType, catchName, catchStmts, finallyStmts);
        return builder.visitTryStmt(tryNode);
    }

    private ResourceDecl buildResourceDecl(DanexParser.ResourceDeclContext ctx) {
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
        String name = ctx.IDENTIFIER().getText();
        Annotation ann = new Annotation(name);
        return builder.visitAnnotation(ann);
    }

    // -------------------
    // Type: return as text if needed
    // -------------------
    @Override
    public Object visitType(DanexParser.TypeContext ctx) {
        return ctx.getText();
    }
    // Other rules omitted for brevity; add as needed.
            }
