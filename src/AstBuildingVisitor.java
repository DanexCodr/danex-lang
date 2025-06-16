package src;

import danex.ast.*;
import danex.grammar.*;
import danex.*;

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
        return builder.visitImportStmt(new ImportStmt(moduleName, alias));
    }

    @Override
    public Object visitClassDecl(DanexParser.ClassDeclContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        List<Annotation> annotations = new ArrayList<>();
        for (var a : ctx.annotation()) annotations.add((Annotation) visit(a));
        List<String> modifiers = new ArrayList<>();
        for (var m : ctx.modifier()) modifiers.add(m.getText());
        List<Stmt> members = new ArrayList<>();
        for (var m : ctx.classBody().classBodyMember()) {
            members.add((Stmt) visit(m));
        }
        return builder.visitClassDecl(new ClassDecl(name, annotations, modifiers, members));
    }

    @Override
    public Object visitMethodDecl(DanexParser.MethodDeclContext ctx) {
        return buildMethod(ctx);
    }

    @Override
    public Object visitTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx) {
        return buildMethod(ctx);
    }

    private MethodDecl buildMethod(DanexParser.MethodDeclContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        String resultType = null;
        if (ctx.resultDecl() != null) {
            resultType = ctx.resultDecl().type().getText();
        }
        List<Annotation> annotations = new ArrayList<>();
        for (var a : ctx.annotation()) annotations.add((Annotation) visit(a));
        List<String> modifiers = new ArrayList<>();
        for (var m : ctx.modifier()) modifiers.add(m.getText());
        List<Param> params = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (var p : ctx.paramList().param()) {
                params.add((Param) visit(p));
            }
        }
        Object body = visit(ctx.methodBody());
        return builder.visitMethodDecl(new MethodDecl(name, resultType, annotations, modifiers, params, body));
    }

    @Override
    public Object visitAnnotation(DanexParser.AnnotationContext ctx) {
        return new Annotation(ctx.IDENTIFIER().getText());
    }

    @Override
    public Object visitParam(DanexParser.ParamContext ctx) {
        String type = ctx.type().getText();
        String name = ctx.IDENTIFIER().getText();
        return new Param(type, name);
    }

    @Override
    public Object visitMethodBody(DanexParser.MethodBodyContext ctx) {
        return ctx.block() != null ? visit(ctx.block()) : visit(ctx.expression());
    }

    @Override
    public Object visitIfStatement(DanexParser.IfStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt thenBranch = (Stmt) visit(ctx.block(0));
        Stmt elseBranch = ctx.ELSE() != null ? (Stmt) visit(ctx.block(1)) : null;
        return builder.visitIfStmt(new IfStmt(condition, thenBranch, elseBranch));
    }

    @Override
    public Object visitWhileStatement(DanexParser.WhileStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt body = (Stmt) visit(ctx.block());
        return builder.visitWhileStmt(new WhileStmt(condition, body));
    }

    @Override
    public Object visitDoWhileStatement(DanexParser.DoWhileStatementContext ctx) {
        Stmt body = (Stmt) visit(ctx.block());
        Expr condition = (Expr) visit(ctx.expression());
        return builder.visitDoWhileStmt(new DoWhileStmt(body, condition));
    }

    @Override
    public Object visitForStatement(DanexParser.ForStatementContext ctx) {
        Expr init = (Expr) visit(ctx.assignment(0));
        Expr condition = (Expr) visit(ctx.expression());
        Expr update = (Expr) visit(ctx.assignment(1));
        Stmt body = (Stmt) visit(ctx.block());
        return builder.visitForStmt(new ForStmt(init, condition, update, body));
    }

    @Override
    public Object visitThrowStatement(DanexParser.ThrowStatementContext ctx) {
        Expr value = (Expr) visit(ctx.expression());
        return builder.visitThrowStmt(new ThrowStmt(value));
    }

    @Override
    public Object visitReturnStatement(DanexParser.ReturnStatementContext ctx) {
        Expr value = ctx.expression() != null ? (Expr) visit(ctx.expression()) : null;
        return builder.visitReturnStmt(new ReturnStmt(value));
    }

    @Override
    public Object visitExitStatement(DanexParser.ExitStatementContext ctx) {
        return builder.visitExitStmt(new ExitStmt());
    }

    @Override
    public Object visitExpressionStatement(DanexParser.ExpressionStatementContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        return builder.visitExpressionStmt(new ExpressionStmt(expr));
    }

    @Override
    public Object visitAssignment(DanexParser.AssignmentContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        String op = ctx.assignOp().getText();
        Expr value = (Expr) visit(ctx.assignment());
        return builder.visitAssignExpr(new AssignExpr(name, op, value));
    }

    @Override
    public Object visitBinaryExpr(DanexParser.LogicalOrExprContext ctx) {
        if (ctx.logicalAndExpr().size() == 1) return visit(ctx.logicalAndExpr(0));
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
            for (var e : ctx.expression()) args.add((Expr) visit(e));
        }
        return builder.visitCallExpr(new CallExpr(name, args));
    }

    @Override
    public Object visitLiteral(DanexParser.LiteralContext ctx) {
        if (ctx.NUMBER() != null) return builder.visitLiteralExpr(new LiteralExpr(ctx.NUMBER().getText()));
        if (ctx.STRING() != null) return builder.visitLiteralExpr(new LiteralExpr(ctx.STRING().getText()));
        if (ctx.TRUE() != null) return builder.visitLiteralExpr(new LiteralExpr(true));
        if (ctx.FALSE() != null) return builder.visitLiteralExpr(new LiteralExpr(false));
        if (ctx.NULL() != null) return builder.visitLiteralExpr(new LiteralExpr(null));
        return null;
    }

    @Override
    public Object visitPrimaryExpr(DanexParser.PrimaryExprContext ctx) {
        if (ctx.expression() != null) return visit(ctx.expression());
        if (ctx.functionCall() != null) return visit(ctx.functionCall());
        if (ctx.literal() != null) return visit(ctx.literal());
        if (ctx.IDENTIFIER() != null) return builder.visitVariableExpr(new VariableExpr(ctx.IDENTIFIER().getText()));
        if (ctx.doExpression() != null) return visit(ctx.doExpression());
        if (ctx.tryExpression() != null) return visit(ctx.tryExpression());
        return null;
    }

    @Override
    public Object visitDoExpression(DanexParser.DoExpressionContext ctx) {
        return builder.visitDoExpr(new DoExpr((Stmt) visit(ctx.block())));
    }

    @Override
    public Object visitTryExpression(DanexParser.TryExpressionContext ctx) {
        Stmt body = (Stmt) visit(ctx.block(0));
        CatchStmt catchBlock = null;
        Stmt finallyBlock = null;

        if (ctx.CATCH() != null) {
            String errType = ctx.IDENTIFIER(0).getText();
            String errName = ctx.IDENTIFIER(1).getText();
            Stmt catchBody = (Stmt) visit(ctx.block(1));
            catchBlock = new CatchStmt(errType, errName, catchBody);
        }
        if (ctx.FINALLY() != null) {
            int blockIndex = ctx.block().size() - 1;
            finallyBlock = (Stmt) visit(ctx.block(blockIndex));
        }

        return builder.visitTryExpr(new TryExpr(body, catchBlock, finallyBlock));
    }

    @Override
    public Object visitType(DanexParser.TypeContext ctx) {
        return ctx.getText();
    }
             }
