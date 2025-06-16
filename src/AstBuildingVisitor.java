package src; // adjust package as needed

import danex.ast.*;       // ensure AST node classes (ClassDecl, MethodDecl, ImportStmt, etc.) live here
import danex.grammar.*;   // adjust if your parser package differs
import danex.*;           // for AstBuilder

import java.util.ArrayList;
import java.util.List;

public class AstBuildingVisitor extends DanexParserBaseVisitor<Object> {

    private final AstBuilder builder = new AstBuilder();

    @Override
    public Object visitCompilationUnit(DanexParser.CompilationUnitContext ctx) {
        List<Stmt> statements = new ArrayList<>();
        for (var stmtCtx : ctx.statement()) {
            statements.add((Stmt) visit(stmtCtx));
        }
        return statements;
    }

    @Override
    public Object visitImportStmt(DanexParser.ImportStmtContext ctx) {
        String moduleName = ctx.IDENTIFIER(0).getText();
        String alias = ctx.IDENTIFIER().size() > 1 ? ctx.IDENTIFIER(1).getText() : null;
        ImportStmt importNode = new ImportStmt(moduleName, alias);
        return builder.visitImportStmt(importNode);
    }

    @Override
    public Object visitClassDecl(DanexParser.ClassDeclContext ctx) {
        String name = ctx.IDENTIFIER().getText();

        List<String> modifiers = new ArrayList<>();
        for (var m : ctx.modifier()) {
            modifiers.add(m.getText());
        }

        List<Stmt> members = new ArrayList<>();
        for (var memberCtx : ctx.classBody().classBodyMember()) {
            members.add((Stmt) visit(memberCtx));
        }

        // Assumes ClassDecl constructor: (String name, List<String> modifiers, List<Stmt> members)
        ClassDecl classNode = new ClassDecl(name, modifiers, members);
        return builder.visitClassDecl(classNode);
    }

    @Override
    public Object visitMethodDecl(DanexParser.MethodDeclContext ctx) {
        return buildMethod(ctx);
    }

    @Override
    public Object visitTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx) {
        // Extract inner MethodDeclContext. Adjust if grammar differs.
        DanexParser.MethodDeclContext inner = ctx.methodDecl();
        return buildMethod(inner);
    }

    /**
     * Builds a MethodDecl. Since no Annotation/Param AST nodes yet,
     * we collect only parameter names as strings.
     */
    private MethodDecl buildMethod(DanexParser.MethodDeclContext ctx) {
        String name = ctx.IDENTIFIER().getText();

        String resultType = null;
        if (ctx.resultDecl() != null) {
            resultType = ctx.resultDecl().type().getText();
        }

        List<String> modifiers = new ArrayList<>();
        for (var m : ctx.modifier()) {
            modifiers.add(m.getText());
        }

        // Collect parameter names (or type+name if you prefer: here we just take name)
        List<String> paramNames = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (var pCtx : ctx.paramList().param()) {
                String pname = pCtx.IDENTIFIER().getText();
                paramNames.add(pname);
            }
        }

        Object body = visit(ctx.methodBody());

        // Assumes MethodDecl constructor: (String name, String resultType, List<String> modifiers, List<String> paramNames, Object body)
        MethodDecl methodNode = new MethodDecl(name, resultType, modifiers, paramNames, body);
        return builder.visitMethodDecl(methodNode);
    }

    @Override
    public Object visitMethodBody(DanexParser.MethodBodyContext ctx) {
        if (ctx.block() != null) {
            return visit(ctx.block());
        } else {
            return visit(ctx.expression());
        }
    }

    @Override
    public Object visitIfStatement(DanexParser.IfStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt thenBranch = (Stmt) visit(ctx.block(0));
        Stmt elseBranch = ctx.ELSE() != null ? (Stmt) visit(ctx.block(1)) : null;
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
        DoWhileStmt doWhileNode = new DoWhileStmt(body, condition);
        return builder.visitDoWhileStmt(doWhileNode);
    }

    @Override
    public Object visitForStatement(DanexParser.ForStatementContext ctx) {
        Expr init = (Expr) visit(ctx.assignment(0));
        Expr condition = (Expr) visit(ctx.expression());
        Expr update = (Expr) visit(ctx.assignment(1));
        Stmt body = (Stmt) visit(ctx.block());
        ForStmt forNode = new ForStmt(init, condition, update, body);
        return builder.visitForStmt(forNode);
    }

    @Override
    public Object visitThrowStatement(DanexParser.ThrowStatementContext ctx) {
        Expr value = (Expr) visit(ctx.expression());
        ThrowStmt throwNode = new ThrowStmt(value);
        return builder.visitThrowStmt(throwNode);
    }

    @Override
    public Object visitReturnStatement(DanexParser.ReturnStatementContext ctx) {
        Expr value = ctx.expression() != null ? (Expr) visit(ctx.expression()) : null;
        ReturnStmt returnNode = new ReturnStmt(value);
        return builder.visitReturnStmt(returnNode);
    }

    @Override
    public Object visitExitStatement(DanexParser.ExitStatementContext ctx) {
        ExitStmt exitNode = new ExitStmt();
        return builder.visitExitStmt(exitNode);
    }

    @Override
    public Object visitExpressionStatement(DanexParser.ExpressionStatementContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        ExpressionStmt exprStmt = new ExpressionStmt(expr);
        return builder.visitExpressionStmt(exprStmt);
    }

    @Override
    public Object visitAssignment(DanexParser.AssignmentContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        String op = ctx.assignOp().getText();
        Expr value = (Expr) visit(ctx.assignment());
        AssignExpr assignNode = new AssignExpr(name, op, value);
        return builder.visitAssignExpr(assignNode);
    }

    @Override
    public Object visitBinaryExpr(DanexParser.LogicalOrExprContext ctx) {
        if (ctx.logicalAndExpr().size() == 1) {
            return visit(ctx.logicalAndExpr(0));
        }
        Expr left = (Expr) visit(ctx.logicalAndExpr(0));
        for (int i = 1; i < ctx.logicalAndExpr().size(); i++) {
            String op = ctx.OR_OR(i - 1).getText();
            Expr right = (Expr) visit(ctx.logicalAndExpr(i));
            left = new BinaryExpr(left, op, right);
        }
        return left;
    }

    @Override
    public Object visitUnaryExpr(DanexParser.UnaryExprContext ctx) {
        Expr expr = (Expr) visit(ctx.primaryExpr());
        // Apply operators in reverse order
        for (int i = ctx.getChildCount() - 2; i >= 0; i--) {
            String op = ctx.getChild(i).getText();
            expr = new UnaryExpr(op, expr);
        }
        return expr;
    }

    @Override
    public Object visitFunctionCall(DanexParser.FunctionCallContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        List<Expr> args = new ArrayList<>();
        if (ctx.expression() != null) {
            for (var e : ctx.expression()) {
                args.add((Expr) visit(e));
            }
        }
        CallExpr callNode = new CallExpr(name, args);
        return builder.visitCallExpr(callNode);
    }

    @Override
    public Object visitLiteral(DanexParser.LiteralContext ctx) {
        if (ctx.NUMBER() != null) {
            return builder.visitLiteralExpr(new LiteralExpr(ctx.NUMBER().getText()));
        }
        if (ctx.STRING() != null) {
            return builder.visitLiteralExpr(new LiteralExpr(ctx.STRING().getText()));
        }
        if (ctx.TRUE() != null) {
            return builder.visitLiteralExpr(new LiteralExpr(true));
        }
        if (ctx.FALSE() != null) {
            return builder.visitLiteralExpr(new LiteralExpr(false));
        }
        if (ctx.NULL() != null) {
            return builder.visitLiteralExpr(new LiteralExpr(null));
        }
        return null;
    }

    @Override
    public Object visitPrimaryExpr(DanexParser.PrimaryExprContext ctx) {
        if (ctx.expression() != null) {
            return visit(ctx.expression());
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
        if (ctx.doExpression() != null) {
            return visit(ctx.doExpression());
        }
        if (ctx.tryExpression() != null) {
            return visit(ctx.tryExpression());
        }
        return null;
    }

    @Override
    public Object visitDoExpression(DanexParser.DoExpressionContext ctx) {
        Stmt body = (Stmt) visit(ctx.block());
        DoExpr doNode = new DoExpr(body);
        return builder.visitDoExpr(doNode);
    }

    @Override
    public Object visitTryExpression(DanexParser.TryExpressionContext ctx) {
        Stmt body = (Stmt) visit(ctx.block(0));
        CatchStmt catchBlock = null;
        Stmt finallyBlock = null;

        if (ctx.CATCH() != null) {
            // Adjust based on grammar: typically IDENTIFIER(0) = exception type, IDENTIFIER(1) = var name
            String errType = ctx.IDENTIFIER(0).getText();
            String errName = ctx.IDENTIFIER(1).getText();
            Stmt catchBody = (Stmt) visit(ctx.block(1));
            catchBlock = new CatchStmt(errType, errName, catchBody);
        }
        if (ctx.FINALLY() != null) {
            int lastIndex = ctx.block().size() - 1;
            finallyBlock = (Stmt) visit(ctx.block(lastIndex));
        }

        TryExpr tryNode = new TryExpr(body, catchBlock, finallyBlock);
        return builder.visitTryExpr(tryNode);
    }

    @Override
    public Object visitType(DanexParser.TypeContext ctx) {
        return ctx.getText();
    }

    // Add other visitor methods for rules in your grammar as needed, always constructing the AST node
    // with the correct constructor signature (omitting Annotation/Param for now).

            }
