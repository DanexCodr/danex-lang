package danex;

import danex.ast.*;
import danex.grammar.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;

/**
 * Updated AstBuildingVisitor that builds the new AST shapes:
 * - MethodDecl uses ReturnSpec and List<ParamDecl>
 * - ParamDecl/TypeNode/ReturnSpec are used instead of old Param/resultName
 *
 * This class assumes your AstBuilder exposes methods like visitMethodDecl,
 * visitBlockStmt, visitExprStmt, visitAssignExpr, etc., that accept the
 * constructed AST nodes and return the canonical AST node object.
 */
public class AstBuildingVisitor extends DanexParserBaseVisitor<Object> {
    private final AstBuilder builder;

    public AstBuildingVisitor(AstBuilder builder) {
        this.builder = builder;
    }

    // -------------------
    // Helper: detect assignment to a variable name inside a Stmt subtree
    // -------------------
    private boolean detectAssignmentTo(Stmt stmt, String resultName, String methodName) {
        class Scanner implements Stmt.Visitor<Boolean>, Expr.Visitor<Boolean>, Decl.Visitor<Boolean> {
            @Override public Boolean visitBinaryExpr(BinaryExpr be) { return scanExpr(be.left) || scanExpr(be.right); }
            @Override public Boolean visitUnaryExpr(UnaryExpr ue) { return scanExpr(ue.right); }
            @Override public Boolean visitLiteralExpr(LiteralExpr le) { return false; }
            @Override public Boolean visitGroupingExpr(GroupingExpr ge) { return scanExpr(ge.expression); }
            @Override public Boolean visitVariableExpr(VariableExpr ve) { return false; }
            @Override public Boolean visitAssignExpr(AssignExpr ae) { return scanExpr(ae.value); }
            @Override public Boolean visitCallExpr(CallExpr ce) { if (scanExpr(ce.callee)) return true; for (Expr a : ce.arguments) if (scanExpr(a)) return true; return false; }
            @Override public Boolean visitLambdaExpr(LambdaExpr le) { return false; }
            @Override public Boolean visitDoExpr(DoExpr de) { for (Stmt s : de.body) if (scanStmt(s)) return true; return false; }
            @Override public Boolean visitTryExpr(TryExpr te) { if (te.tryBlock != null) for (Stmt s : te.tryBlock) if (scanStmt(s)) return true; if (te.catchBlock != null) for (Stmt s : te.catchBlock) if (scanStmt(s)) return true; if (te.finallyBlock != null) for (Stmt s : te.finallyBlock) if (scanStmt(s)) return true; return false; }
            @Override public Boolean visitAwaitExpr(AwaitExpr ae) { return false; }
            @Override public Boolean visitNullCoalesceExpr(NullCoalesceExpr ne) { return scanExpr(ne.left) || scanExpr(ne.right); }
            @Override public Boolean visitComparatorExpr(ComparatorExpr ce) { return scanExpr(ce.left) || scanExpr(ce.right); }

            @Override public Boolean visitExprStmt(ExprStmt es) { return scanExpr(es.expression); }
            @Override public Boolean visitAssignStmt(AssignStmt stmt) {
                Expr tgt = stmt.target;
                if (tgt instanceof VariableExpr) {
                    String var = ((VariableExpr) tgt).name;
                    if ((resultName != null && var.equals(resultName)) || var.equals(methodName)) return true;
                }
                return scanExpr(stmt.value);
            }
            @Override public Boolean visitBlockStmt(BlockStmt bs) { for (Stmt s : bs.statements) if (scanStmt(s)) return true; return false; }
            @Override public Boolean visitIfStmt(IfStmt ifs) { if (scanExpr(ifs.condition)) return true; if (scanStmt(ifs.thenBranch)) return true; if (ifs.elseBranch != null && scanStmt(ifs.elseBranch)) return true; return false; }
            @Override public Boolean visitWhileStmt(WhileStmt ws) { if (scanExpr(ws.condition)) return scanStmt(ws.body); return false; }
            @Override public Boolean visitDoWhileStmt(DoWhileStmt dws) { if (scanStmt(dws.body)) return true; return scanExpr(dws.condition); }
            @Override public Boolean visitForStmt(ForStmt fs) { if (fs.init != null && scanStmt(fs.init)) return true; if (fs.condition != null && scanExpr(fs.condition)) return true; if (fs.update != null && scanExpr(fs.update)) return true; return scanStmt(fs.body); }
            @Override public Boolean visitThrowStmt(ThrowStmt ts) { return scanExpr(ts.exception); }
            @Override public Boolean visitExitStmt(ExitStmt es) { return false; }
            @Override public Boolean visitTryStmt(TryStmt ts) { if (ts.tryBlock != null) for (Stmt s : ts.tryBlock) if (scanStmt(s)) return true; if (ts.catchBlock != null) for (Stmt s : ts.catchBlock) if (scanStmt(s)) return true; if (ts.finallyBlock != null) for (Stmt s : ts.finallyBlock) if (scanStmt(s)) return true; return false; }

            @Override public Boolean visitClassDecl(ClassDecl cd) { return false; }
            @Override public Boolean visitMethodDecl(MethodDecl md) { return false; }
            @Override public Boolean visitImportDecl(ImportDecl id) { return false; }
            @Override public Boolean visitAnnotation(Annotation a) { return false; }
            @Override public Boolean visitParamDecl(ParamDecl pd) { return false; }
            @Override public Boolean visitResourceDecl(ResourceDecl rd) { return false; }

            private Boolean scanExpr(Expr e) { return e != null && e.accept(this); }
            private Boolean scanStmt(Stmt s) { return s != null && s.accept(this); }
        }

        Scanner sc = new Scanner();
        return sc.scanStmt(stmt);
    }

    // -------------------
    // Top-level compilation unit
    // -------------------
    @Override
    public Object visitCompilationUnit(DanexParser.CompilationUnitContext ctx) {
        List<Decl> decls = new ArrayList<>();
        for (var stmtCtx : ctx.statement()) {
            Object result = visit(stmtCtx);
            if (result instanceof Decl) decls.add((Decl) result);
            else throw new RuntimeException("Expected top-level Decl, got: " + result);
        }
        return decls;
    }

    @Override
    public Object visitImportStmt(DanexParser.ImportStmtContext ctx) {
        String moduleName = ctx.IDENTIFIER(0).getText();
        String alias = ctx.IDENTIFIER().size() > 1 ? ctx.IDENTIFIER(1).getText() : null;
        ImportDecl importNode = new ImportDecl(moduleName, alias);
        return builder.visitImportDecl(importNode);
    }

    @Override
    public Object visitClassDecl(DanexParser.ClassDeclContext ctx) {
        List<Annotation> annotations = new ArrayList<>();
        for (var annCtx : ctx.annotation()) {
            Object annObj = visit(annCtx);
            if (annObj instanceof Annotation) annotations.add((Annotation) annObj);
        }
        List<String> modifiers = buildModifiers(ctx.modifier());
        String name = ctx.IDENTIFIER().getText();

        List<Decl> members = new ArrayList<>();
        for (var memberCtx : ctx.classBody().classBodyMember()) {
            Object memberObj = visit(memberCtx);
            if (memberObj instanceof Decl) members.add((Decl) memberObj);
            else throw new RuntimeException("Expected Decl inside class body, got: " + memberObj);
        }

        ClassDecl classNode = new ClassDecl(name, annotations, modifiers, members);
        return builder.visitClassDecl(classNode);
    }

    @Override public Object visitMethodDecl(DanexParser.MethodDeclContext ctx) { return buildMethod(ctx); }
    @Override public Object visitTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx) { return buildTopLevelMethod(ctx); }

    private Decl buildMethod(DanexParser.MethodDeclContext ctx) {
        List<Annotation> annotations = buildAnnotations(ctx.annotation());
        List<String> modifiers = buildModifiers(ctx.modifier());

        if (ctx.IDENTIFIER() == null) throw new RuntimeException("MethodDecl missing name");
        String methodName = ctx.IDENTIFIER().getText();

        String resultType = null;
        String resultName = null;
        if (ctx.resultDecl() != null) {
            List<org.antlr.v4.runtime.tree.TerminalNode> ids = ctx.resultDecl().IDENTIFIER();
            if (ids.size() == 2) { resultType = ids.get(0).getText(); resultName = ids.get(1).getText(); }
            else if (ids.size() == 1) { resultName = ids.get(0).getText(); }
            else throw new RuntimeException("Invalid result declaration syntax");
        }

        List<ParamDecl> params = buildParams(ctx.paramList());

        if (resultName != null) {
            for (ParamDecl p : params) if (p.name.equals(resultName))
                throw new RuntimeException("Parameter '" + p.name + "' conflicts with result variable name in method '" + methodName + "'");
        }

        Stmt bodyStmt = buildMethodBody(methodName, resultName, ctx.methodBody());

        if (resultName != null) {
            boolean assigned = detectAssignmentTo(bodyStmt, resultName, methodName);
            if (!assigned) throw new RuntimeException("Method '" + methodName + "' declares result '" + resultName + "' but no assignment found in body");
        }

        ReturnSpec rs = null;
        if (resultType != null || resultName != null) {
            TypeNode tn = (resultType != null) ? new TypeNode(resultType) : null;
            rs = new ReturnSpec(tn, resultName);
        }

        if (!(bodyStmt instanceof BlockStmt)) throw new RuntimeException("Expected BlockStmt as method body");
        MethodDecl methodNode = new MethodDecl(methodName, rs, annotations, modifiers, params, (BlockStmt) bodyStmt, null, false);
        System.out.println("[DEBUG] Built method: " + methodName);
        return builder.visitMethodDecl(methodNode);
    }

    private Decl buildTopLevelMethod(DanexParser.TopLevelMethodDeclContext ctx) {
        List<Annotation> annotations = buildAnnotations(ctx.annotation());
        List<String> modifiers = buildModifiers(ctx.modifier());

        if (ctx.IDENTIFIER() == null) throw new RuntimeException("TopLevelMethodDecl missing name");
        String methodName = ctx.IDENTIFIER().getText();

        String resultType = null;
        String resultName = null;
        if (ctx.resultDecl() != null) {
            List<org.antlr.v4.runtime.tree.TerminalNode> ids = ctx.resultDecl().IDENTIFIER();
            if (ids.size() == 2) { resultType = ids.get(0).getText(); resultName = ids.get(1).getText(); }
            else if (ids.size() == 1) { resultName = ids.get(0).getText(); }
            else throw new RuntimeException("Invalid result declaration syntax");
        }

        List<ParamDecl> params = buildParams(ctx.paramList());

        if (resultName != null) {
            for (ParamDecl p : params) if (p.name.equals(resultName))
                throw new RuntimeException("Parameter '" + p.name + "' conflicts with result variable name in top-level method '" + methodName + "'");
        }

        Stmt bodyStmt = buildMethodBody(methodName, resultName, ctx.methodBody());

        if (resultName != null) {
            boolean assigned = detectAssignmentTo(bodyStmt, resultName, methodName);
            if (!assigned) throw new RuntimeException("Top-level method '" + methodName + "' declares result '" + resultName + "' but no assignment found in body");
        }

        ReturnSpec rs = null;
        if (resultType != null || resultName != null) {
            TypeNode tn = (resultType != null) ? new TypeNode(resultType) : null;
            rs = new ReturnSpec(tn, resultName);
        }

        if (!(bodyStmt instanceof BlockStmt)) throw new RuntimeException("Expected BlockStmt as method body");
        MethodDecl methodNode = new MethodDecl(methodName, rs, annotations, modifiers, params, (BlockStmt) bodyStmt, null, false);
        System.out.println("[DEBUG] Built top-level method: " + methodName);
        return builder.visitMethodDecl(methodNode);
    }

    private List<ParamDecl> buildParams(DanexParser.ParamListContext paramListCtx) {
        List<ParamDecl> params = new ArrayList<>();
        if (paramListCtx == null) return params;
        for (var pCtx : paramListCtx.param()) {
            String pTypeText = (pCtx.type() != null) ? pCtx.type().getText() : null;
            TypeNode pTypeNode = (pTypeText != null) ? new TypeNode(pTypeText) : null;
            String pName = pCtx.IDENTIFIER().getText();
            boolean varargs = pCtx.VARARGS() != null;
            params.add(new ParamDecl(pTypeNode, pName, varargs));
        }
        return params;
    }

    private Stmt buildMethodBody(String methodName, String resultName, DanexParser.MethodBodyContext bodyCtx) {
        if (bodyCtx.block() != null) {
            Object bObj = visit(bodyCtx.block());
            if (!(bObj instanceof Stmt)) throw new RuntimeException("Expected Stmt from block, got: " + bObj);
            return (Stmt) bObj;
        } else {
            Expr expr = (Expr) visit(bodyCtx.expression());
            String target = (resultName != null) ? resultName : methodName;
            VariableExpr targetVar = new VariableExpr(target);
            AssignStmt assign = new AssignStmt(targetVar, expr);
            List<Stmt> stmts = new ArrayList<>();
            stmts.add(assign);
            return new BlockStmt(stmts);
        }
    }

    @Override
    public Object visitBlock(DanexParser.BlockContext ctx) {
        List<Stmt> stmts = new ArrayList<>();
        for (var bc : ctx.blockContent()) {
            Object o = visit(bc);
            if (o instanceof Stmt) stmts.add((Stmt) o);
            else throw new RuntimeException("Expected Stmt in blockContent, got: " + o);
        }
        BlockStmt blockNode = new BlockStmt(stmts);
        return builder.visitBlockStmt(blockNode);
    }

    @Override
    public Object visitBlockContent(DanexParser.BlockContentContext ctx) {
        if (ctx.statement() != null) {
            Object stmtObj = visit(ctx.statement());
            if (stmtObj instanceof Stmt) return stmtObj;
            if (stmtObj instanceof Decl) throw new RuntimeException("Declaration not allowed here: " + stmtObj);
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
        }
        throw new RuntimeException("Unknown blockContent: " + ctx.getText());
    }

    @Override
    public Object visitIfStatement(DanexParser.IfStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt thenBranch = (Stmt) visit(ctx.block(0));
        Stmt elseBranch = (ctx.ELSE() != null) ? (Stmt) visit(ctx.block(1)) : null;
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
        // Conservative, compile-safe implementation:
        // We produce a ForStmt with null init/condition/update if parser details differ.
        Stmt initStmt = null;
        Expr condition = null;
        Expr updateExpr = null;

        // If the grammar provides an assignment() child, visit it as init.
        try {
            if (ctx.assignment() != null) {
                Object initObj = visit(ctx.assignment());
                if (initObj instanceof Expr) {
                    Expr initExpr = (Expr) initObj;
                    initStmt = (ExprStmt) builder.visitExprStmt(new ExprStmt(initExpr));
                } else if (initObj instanceof Stmt) {
                    initStmt = (Stmt) initObj;
                }
            }
        } catch (Exception ignored) {}

        // If the grammar provides a single expression child, treat it as condition.
        try {
            if (ctx.expression() != null) {
                Object condObj = visit(ctx.expression());
                if (condObj instanceof Expr) condition = (Expr) condObj;
            }
        } catch (Exception ignored) {}

        // Update expression often lives in a separate child; leave null if unknown.

        Stmt body = (Stmt) visit(ctx.block());
        ForStmt forNode = new ForStmt(initStmt, condition, updateExpr, body);
        return builder.visitForStmt(forNode);
    }

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

    @Override
    public Object visitExpressionStatement(DanexParser.ExpressionStatementContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        ExprStmt exprStmt = new ExprStmt(expr);
        return builder.visitExprStmt(exprStmt);
    }

    @Override
    public Object visitAssignment(DanexParser.AssignmentContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        String op = ctx.assignOp().getText();
        Expr value = (Expr) visit(ctx.expression());
        AssignExpr assignNode;
        if ("=".equals(op)) {
            assignNode = new AssignExpr(name, value);
        } else {
            String binOp = op.substring(0, op.length() - 1);
            VariableExpr leftVar = new VariableExpr(name);
            BinaryExpr bin = new BinaryExpr(leftVar, binOp, value);
            assignNode = new AssignExpr(name, bin);
        }
        return builder.visitAssignExpr(assignNode);
    }

    @Override
    public Object visitLogicalOrExpr(DanexParser.LogicalOrExprContext ctx) {
        if (ctx.logicalAndExpr().size() == 1) return visit(ctx.logicalAndExpr(0));
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
        if (ctx.nullCoalesceExpr().size() == 1) return visit(ctx.nullCoalesceExpr(0));
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
        if (ctx.comparisonExpr().size() == 1) return visit(ctx.comparisonExpr(0));
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
        if (ctx.additiveExpr().size() == 1) return visit(ctx.additiveExpr(0));
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
        if (ctx.multiplicativeExpr().size() == 1) return visit(ctx.multiplicativeExpr(0));
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
        if (ctx.unaryExpr().size() == 1) return visit(ctx.unaryExpr(0));
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
        int childCount = ctx.getChildCount();
        for (int i = childCount - 2; i >= 0; i--) {
            String op = ctx.getChild(i).getText();
            UnaryExpr u = new UnaryExpr(op, expr);
            expr = (UnaryExpr) builder.visitUnaryExpr(u);
        }
        return expr;
    }

    @Override
    public Object visitPrimaryExpr(DanexParser.PrimaryExprContext ctx) {
        if (ctx.expression() != null) return visit(ctx.expression());
        if (ctx.doExpression() != null) return visit(ctx.doExpression());
        if (ctx.tryExpression() != null) return visit(ctx.tryExpression());
        if (ctx.functionCall() != null) return visit(ctx.functionCall());
        if (ctx.literal() != null) return visit(ctx.literal());
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
            for (var eCtx : ctx.expression()) args.add((Expr) visit(eCtx));
        }
        CallExpr callNode = new CallExpr(callee, args);
        return builder.visitCallExpr(callNode);
    }

    @Override
    public Object visitLiteral(DanexParser.LiteralContext ctx) {
        Object value;
        if (ctx.NUMBER() != null) {
            String text = ctx.NUMBER().getText();
            if (text.contains(".")) value = Double.parseDouble(text);
            else {
                try { value = Integer.parseInt(text); }
                catch (NumberFormatException e) { value = Long.parseLong(text); }
            }
        } else if (ctx.STRING() != null) {
            String text = ctx.STRING().getText();
            value = text.substring(1, text.length() - 1);
        } else if (ctx.TRUE() != null) value = Boolean.TRUE;
        else if (ctx.FALSE() != null) value = Boolean.FALSE;
        else if (ctx.NULL() != null) value = null;
        else throw new RuntimeException("Unknown literal: " + ctx.getText());
        LiteralExpr lit = new LiteralExpr(value);
        return builder.visitLiteralExpr(lit);
    }

    @Override
    public Object visitDoExpression(DanexParser.DoExpressionContext ctx) {
        BlockStmt block = (BlockStmt) visit(ctx.block());
        DoExpr doNode = new DoExpr(block.statements);
        return builder.visitDoExpr(doNode);
    }

    @Override
    public Object visitTryExpression(DanexParser.TryExpressionContext ctx) {
        BlockStmt tryBlock = (BlockStmt) visit(ctx.block(0));
        String catchType = null, catchName = null;
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
        String catchType = null, catchName = null;
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

    @Override
    public Object visitAnnotation(DanexParser.AnnotationContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Annotation ann = new Annotation(name);
        return builder.visitAnnotation(ann);
    }

    private List<Annotation> buildAnnotations(List<DanexParser.AnnotationContext> annCtxs) {
        List<Annotation> annotations = new ArrayList<>();
        for (var annCtx : annCtxs) {
            Object annObj = visit(annCtx);
            if (annObj instanceof Annotation) annotations.add((Annotation) annObj);
        }
        return annotations;
    }

    private List<String> buildModifiers(List<DanexParser.ModifierContext> modifierCtxs) {
        List<String> modifiers = new ArrayList<>();
        for (var m : modifierCtxs) modifiers.add(m.getText());
        return modifiers;
    }

    @Override
    public Object visitType(DanexParser.TypeContext ctx) {
        return ctx.getText();
    }
                }
