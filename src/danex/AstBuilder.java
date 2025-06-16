package danex;

import danex.ast.*;
import java.util.*;

public class AstBuilder implements DanexParserVisitor<Object> {

    @Override
    public Object visitAssignExpr(DanexParser.AssignExprContext ctx) {
        String name = ctx.name().getText();
        Expr value = (Expr) ctx.value().accept(this);
        return new AssignExpr(name, value);
    }

    @Override
    public Object visitBinaryExpr(DanexParser.BinaryExprContext ctx) {
        Expr left = (Expr) ctx.left().accept(this);
        String operator = ctx.operator().getText();
        Expr right = (Expr) ctx.right().accept(this);
        return new BinaryExpr(left, operator, right);
    }

    @Override
    public Object visitCallExpr(DanexParser.CallExprContext ctx) {
        Expr callee = (Expr) ctx.callee().accept(this);
        List<Expr> arguments = new ArrayList<>();
        for (var e : ctx.arguments()) arguments.add((Expr) e.accept(this));
        return new CallExpr(callee, arguments);
    }

    @Override
    public Object visitComparatorExpr(DanexParser.ComparatorExprContext ctx) {
        Expr left = (Expr) ctx.left().accept(this);
        Expr right = (Expr) ctx.right().accept(this);
        return new ComparatorExpr(left, right);
    }

    @Override
    public Object visitDoExpr(DanexParser.DoExprContext ctx) {
        List<Stmt> body = new ArrayList<>();
        for (var s : ctx.body()) body.add((Stmt) s.accept(this));
        return new DoExpr(body);
    }

    @Override
    public Object visitDoexprExpr(DanexParser.DoexprExprContext ctx) {
        List<Stmt> body = new ArrayList<>();
        for (var s : ctx.body()) body.add((Stmt) s.accept(this));
        return new DoExprExpr(body);
    }

    @Override
    public Object visitGroupingExpr(DanexParser.GroupingExprContext ctx) {
        Expr expression = (Expr) ctx.expression().accept(this);
        return new GroupingExpr(expression);
    }

    @Override
    public Object visitLambdaExpr(DanexParser.LambdaExprContext ctx) {
        List<String> params = new ArrayList<>();
        for (var t : ctx.params()) params.add(t.getText());
        List<Stmt> body = new ArrayList<>();
        for (var s : ctx.body()) body.add((Stmt) s.accept(this));
        return new LambdaExpr(params, body);
    }

    @Override
    public Object visitLiteralExpr(DanexParser.LiteralExprContext ctx) {
        Object value = ctx.value().getText(); // TODO: parse Object properly
        return new LiteralExpr(value);
    }

    @Override
    public Object visitNullcoalesceExpr(DanexParser.NullcoalesceExprContext ctx) {
        Expr left = (Expr) ctx.left().accept(this);
        Expr right = (Expr) ctx.right().accept(this);
        return new NullCoalesceExpr(left, right);
    }

    @Override
    public Object visitUnaryExpr(DanexParser.UnaryExprContext ctx) {
        String operator = ctx.operator().getText();
        Expr right = (Expr) ctx.right().accept(this);
        return new UnaryExpr(operator, right);
    }

    @Override
    public Object visitVariableExpr(DanexParser.VariableExprContext ctx) {
        String name = ctx.name().getText();
        return new VariableExpr(name);
    }

    @Override
    public Object visitAwaitExpr(DanexParser.AwaitExprContext ctx) {
        Expr expression = (Expr) ctx.expression().accept(this);
        return new AwaitExpr(expression);
    }

    @Override
    public Object visitTryexprExpr(DanexParser.TryexprExprContext ctx) {
        Expr tryExpr = (Expr) ctx.tryExpr().accept(this);
        String exceptionName = ctx.exceptionName().getText();
        Expr catchExpr = (Expr) ctx.catchExpr().accept(this);
        return new TryExprExpr(tryExpr, exceptionName, catchExpr);
    }

    @Override
    public Object visitBlockStmt(DanexParser.BlockStmtContext ctx) {
        List<Stmt> statements = new ArrayList<>();
        for (var s : ctx.statements()) statements.add((Stmt) s.accept(this));
        return new BlockStmt(statements);
    }

    @Override
    public Object visitExprStmt(DanexParser.ExprStmtContext ctx) {
        Expr expression = (Expr) ctx.expression().accept(this);
        return new ExprStmt(expression);
    }

    @Override
    public Object visitIfStmt(DanexParser.IfStmtContext ctx) {
        Expr condition = (Expr) ctx.condition().accept(this);
        Stmt thenBranch = (Stmt) ctx.thenBranch().accept(this);
        Stmt elseBranch = (Stmt) ctx.elseBranch().accept(this);
        return new IfStmt(condition, thenBranch, elseBranch);
    }

    @Override
    public Object visitWhileStmt(DanexParser.WhileStmtContext ctx) {
        Expr condition = (Expr) ctx.condition().accept(this);
        Stmt body = (Stmt) ctx.body().accept(this);
        return new WhileStmt(condition, body);
    }

    @Override
    public Object visitDowhileStmt(DanexParser.DowhileStmtContext ctx) {
        Stmt body = (Stmt) ctx.body().accept(this);
        Expr condition = (Expr) ctx.condition().accept(this);
        return new DoWhileStmt(body, condition);
    }

    @Override
    public Object visitForStmt(DanexParser.ForStmtContext ctx) {
        Stmt init = (Stmt) ctx.init().accept(this);
        Expr condition = (Expr) ctx.condition().accept(this);
        Expr update = (Expr) ctx.update().accept(this);
        Stmt body = (Stmt) ctx.body().accept(this);
        return new ForStmt(init, condition, update, body);
    }

    @Override
    public Object visitReturnStmt(DanexParser.ReturnStmtContext ctx) {
        Expr value = (Expr) ctx.value().accept(this);
        return new ReturnStmt(value);
    }

    @Override
    public Object visitThrowStmt(DanexParser.ThrowStmtContext ctx) {
        Expr exception = (Expr) ctx.exception().accept(this);
        return new ThrowStmt(exception);
    }

    @Override
    public Object visitExitStmt(DanexParser.ExitStmtContext ctx) {
        return new ExitStmt();
    }

    @Override
    public Object visitTryStmt(DanexParser.TryStmtContext ctx) {
        List<Stmt> tryBlock = new ArrayList<>();
        for (var s : ctx.tryBlock()) tryBlock.add((Stmt) s.accept(this));
        String exceptionName = ctx.exceptionName().getText();
        List<Stmt> catchBlock = new ArrayList<>();
        for (var s : ctx.catchBlock()) catchBlock.add((Stmt) s.accept(this));
        List<Stmt> finallyBlock = new ArrayList<>();
        for (var s : ctx.finallyBlock()) finallyBlock.add((Stmt) s.accept(this));
        return new TryStmt(tryBlock, exceptionName, catchBlock, finallyBlock);
    }

    @Override
    public Object visitClassStmt(DanexParser.ClassStmtContext ctx) {
        String name = ctx.name().getText();
        List<Stmt> methods = new ArrayList<>();
        for (var s : ctx.methods()) methods.add((Stmt) s.accept(this));
        return new ClassStmt(name, methods);
    }

    @Override
    public Object visitMethodStmt(DanexParser.MethodStmtContext ctx) {
        String name = ctx.name().getText();
        List<String> params = new ArrayList<>();
        for (var t : ctx.params()) params.add(t.getText());
        List<Stmt> body = new ArrayList<>();
        for (var s : ctx.body()) body.add((Stmt) s.accept(this));
        return new MethodStmt(name, params, body);
    }

    @Override
    public Object visitImportStmt(DanexParser.ImportStmtContext ctx) {
        String path = ctx.path().getText();
        return new ImportStmt(path);
    }

}
