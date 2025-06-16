package danex;

import danex.grammar.DanexParserBaseVisitor;
import danex.grammar.DanexParser;
import danex.ast.*;

import java.util.*;
import org.antlr.v4.runtime.tree.ParseTree;

import static java.util.stream.Collectors.toList;

public class AstBuildingVisitor extends DanexParserBaseVisitor<Object> {
    private final AstBuilder builder;

    public AstBuildingVisitor(AstBuilder builder) {
        this.builder = builder;
    }

    public List<Stmt> build(DanexParser.CompilationUnitContext ctx) {
        return ctx.statement().stream()
                .map(stmt -> (Stmt) visit(stmt))
                .collect(toList());
    }

    @Override
    public Object visitBlock(DanexParser.BlockContext ctx) {
        List<Stmt> statements = ctx.blockContent().stream()
                .map(s -> (Stmt) visit(s.statement()))
                .collect(toList());
        return builder.visitBlockStmt(new BlockStmt(statements));
    }

    @Override
    public Object visitExpressionStatement(DanexParser.ExpressionStatementContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        return builder.visitExprStmt(new ExprStmt(expr));
    }

    @Override
    public Object visitReturnStatement(DanexParser.ReturnStatementContext ctx) {
        Expr value = ctx.expression() != null ? (Expr) visit(ctx.expression()) : null;
        return builder.visitReturnStmt(new ReturnStmt(value));
    }

    @Override
    public Object visitIfStatement(DanexParser.IfStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt thenBranch = (Stmt) visit(ctx.statement(0));
        Stmt elseBranch = ctx.statement().size() > 1 ? (Stmt) visit(ctx.statement(1)) : null;
        return builder.visitIfStmt(new IfStmt(condition, thenBranch, elseBranch));
    }

    @Override
    public Object visitWhileStatement(DanexParser.WhileStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt body = (Stmt) visit(ctx.statement());
        return builder.visitWhileStmt(new WhileStmt(condition, body));
    }

    @Override
    public Object visitDoWhileStatement(DanexParser.DoWhileStatementContext ctx) {
        Stmt body = (Stmt) visit(ctx.statement());
        Expr condition = (Expr) visit(ctx.expression());
        return builder.visitDoWhileStmt(new DoWhileStmt(body, condition));
    }

    @Override
    public Object visitForStatement(DanexParser.ForStatementContext ctx) {
        Stmt init = ctx.statement(0) != null ? (Stmt) visit(ctx.statement(0)) : null;
        Expr cond = ctx.expression() != null ? (Expr) visit(ctx.expression()) : null;
        Stmt update = ctx.statement().size() > 2 ? (Stmt) visit(ctx.statement(1)) : null;
        Stmt body = (Stmt) visit(ctx.statement(ctx.statement().size() - 1));
        return builder.visitForStmt(new ForStmt(init, cond, update, body));
    }

    @Override
    public Object visitExitStatement(DanexParser.ExitStatementContext ctx) {
        return builder.visitExitStmt(new ExitStmt());
    }

    @Override
    public Object visitThrowStatement(DanexParser.ThrowStatementContext ctx) {
        Expr exception = (Expr) visit(ctx.expression());
        return builder.visitThrowStmt(new ThrowStmt(exception));
    }

    @Override
    public Object visitTryStatement(DanexParser.TryStatementContext ctx) {
        List<Stmt> tryBlock = ctx.block(0).blockContent().stream().map(c -> (Stmt) visit(c.statement())).collect(toList());
        String exceptionName = ctx.IDENTIFIER().getText();
        List<Stmt> catchBlock = ctx.block(1).blockContent().stream().map(c -> (Stmt) visit(c.statement())).collect(toList());
        List<Stmt> finallyBlock = ctx.block().size() > 2
                ? ctx.block(2).blockContent().stream().map(c -> (Stmt) visit(c.statement())).collect(toList())
                : new ArrayList<>();
        return builder.visitTryStmt(new TryStmt(tryBlock, exceptionName, catchBlock, finallyBlock));
    }

    @Override
    public Object visitImportStmt(DanexParser.ImportStmtContext ctx) {
        String moduleName = ctx.moduleName.getText();
        String alias = ctx.alias != null ? ctx.alias.getText() : null;
        return builder.visitImportStmt(new ImportStmt(moduleName, alias));
    }

    @Override
    public Object visitLiteral(DanexParser.LiteralContext ctx) {
        Object value = null;
        if (ctx.STRING() != null) value = ctx.STRING().getText();
        else if (ctx.NUMBER() != null) value = Double.parseDouble(ctx.NUMBER().getText());
        else if (ctx.BOOL() != null) value = Boolean.parseBoolean(ctx.BOOL().getText());
        return builder.visitLiteralExpr(new LiteralExpr(value));
    }

    @Override
    public Object visitPrimaryExpr(DanexParser.PrimaryExprContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Object visitAssignment(DanexParser.AssignmentContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Expr value = (Expr) visit(ctx.expression());
        return builder.visitAssignExpr(new AssignExpr(name, value));
    }

    @Override
    public Object visitBinaryExpr(DanexParser.AdditiveExprContext ctx) {
        Expr left = (Expr) visit(ctx.expression(0));
        String operator = ctx.op.getText();
        Expr right = (Expr) visit(ctx.expression(1));
        return builder.visitBinaryExpr(new BinaryExpr(left, operator, right));
    }

    @Override
    public Object visitUnaryExpr(DanexParser.UnaryExprContext ctx) {
        String operator = ctx.op.getText();
        Expr right = (Expr) visit(ctx.expression());
        return builder.visitUnaryExpr(new UnaryExpr(operator, right));
    }

    @Override
    public Object visitFunctionCall(DanexParser.FunctionCallContext ctx) {
        Expr callee = (Expr) visit(ctx.expression());
        List<Expr> args = ctx.arguments().expression().stream()
                .map(e -> (Expr) visit(e))
                .collect(toList());
        return builder.visitCallExpr(new CallExpr(callee, args));
    }

    @Override
    public Object visitGroupingExpr(DanexParser.GroupingExprContext ctx) {
        return builder.visitGroupingExpr(new GroupingExpr((Expr) visit(ctx.expression())));
    }

    @Override
    public Object visitLambdaExpr(DanexParser.LambdaExprContext ctx) {
        List<String> params = ctx.paramList() != null
                ? ctx.paramList().param().stream().map(p -> p.IDENTIFIER().getText()).collect(toList())
                : new ArrayList<>();
        Expr body = (Expr) visit(ctx.expression());
        return builder.visitLambdaExpr(new LambdaExpr(params, body));
    }

    @Override
    public Object visitNullCoalesceExpr(DanexParser.NullCoalesceExprContext ctx) {
        Expr left = (Expr) visit(ctx.expression(0));
        Expr right = (Expr) visit(ctx.expression(1));
        return builder.visitNullCoalesceExpr(new NullCoalesceExpr(left, right));
    }

    @Override
    public Object visitTryExpression(DanexParser.TryExpressionContext ctx) {
        Expr tryBody = (Expr) visit(ctx.tryBody);
        String exceptionName = ctx.IDENTIFIER().getText();
        Expr catchBody = (Expr) visit(ctx.catchBody);
        Expr finallyBody = ctx.finallyBody != null ? (Expr) visit(ctx.finallyBody) : null;
        return builder.visitTryExpr(new TryExpr(tryBody, exceptionName, catchBody, finallyBody));
    }

    @Override
    public Object visitAwaitExpr(DanexParser.AwaitExprContext ctx) {
        return builder.visitAwaitExpr(new AwaitExpr((Expr) visit(ctx.expression())));
    }

    @Override
    public Object visitDoExpression(DanexParser.DoExpressionContext ctx) {
        List<Stmt> body = ctx.block().blockContent().stream().map(b -> (Stmt) visit(b.statement())).collect(toList());
        return builder.visitDoExpr(new DoExpr(body));
    }

    @Override
    public Object visitComparisonExpr(DanexParser.ComparisonExprContext ctx) {
        Expr left = (Expr) visit(ctx.expression(0));
        Expr right = (Expr) visit(ctx.expression(1));
        return builder.visitComparatorExpr(new ComparatorExpr(left, right));
    }
    }
