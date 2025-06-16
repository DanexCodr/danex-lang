package danex;

import danex.ast.*;
import java.util.*;

public class AstBuilder implements Expr.Visitor<Expr>, Stmt.Visitor<Stmt> {

    @Override
    public Object visitAwaitExpr(AwaitExpr awaitExpr) {
        Expr expression = awaitExpr.expression;
        return new AwaitExpr(expression);
    }

    @Override
    public Object visitTryExpr(TryExpr tryExpr) {
        Expr tryBody = tryExpr.tryBody;
        String exceptionName = tryExpr.exceptionName;
        Expr catchBody = tryExpr.catchBody;
        Expr finallyBody = tryExpr.finallyBody;
        return new TryExpr(tryBody, exceptionName, catchBody, finallyBody);
    }

    @Override
    public Object visitNullCoalesceExpr(NullCoalesceExpr nullCoalesceExpr) {
        Expr left = nullCoalesceExpr.left;
        Expr right = nullCoalesceExpr.right;
        return new NullCoalesceExpr(left, right);
    }

    @Override
    public Object visitCallExpr(CallExpr callExpr) {
        Expr callee = callExpr.callee;
        List<Expr> arguments = callExpr.arguments;
        return new CallExpr(callee, arguments);
    }

    @Override
    public Object visitForStmt(ForStmt forStmt) {
        Stmt init = forStmt.init;
        Expr condition = forStmt.condition;
        Expr update = forStmt.update;
        Stmt body = forStmt.body;
        return new ForStmt(init, condition, update, body);
    }

    @Override
    public Object visitComparatorExpr(ComparatorExpr comparatorExpr) {
        Expr left = comparatorExpr.left;
        Expr right = comparatorExpr.right;
        return new ComparatorExpr(left, right);
    }

    @Override
    public Object visitBlockStmt(BlockStmt blockStmt) {
        List<Stmt> statements = blockStmt.statements;
        return new BlockStmt(statements);
    }

    @Override
    public Object visitWhileStmt(WhileStmt whileStmt) {
        Expr condition = whileStmt.condition;
        Stmt body = whileStmt.body;
        return new WhileStmt(condition, body);
    }

    @Override
    public Object visitClassStmt(ClassStmt classStmt) {
        String name = classStmt.name;
        List<Stmt> members = classStmt.members;
        return new ClassStmt(name, members);
    }

    @Override
    public Object visitDoWhileStmt(DoWhileStmt doWhileStmt) {
        Stmt body = doWhileStmt.body;
        Expr condition = doWhileStmt.condition;
        return new DoWhileStmt(body, condition);
    }

    @Override
    public Object visitLiteralExpr(LiteralExpr literalExpr) {
        Object value = literalExpr.value;
        return new LiteralExpr(value);
    }

    @Override
    public Object visitAssignExpr(AssignExpr assignExpr) {
        String name = assignExpr.name;
        Expr value = assignExpr.value;
        return new AssignExpr(name, value);
    }

    @Override
    public Object visitBinaryExpr(BinaryExpr binaryExpr) {
        Expr left = binaryExpr.left;
        String operator = binaryExpr.operator;
        Expr right = binaryExpr.right;
        return new BinaryExpr(left, operator, right);
    }

    @Override
    public Object visitReturnStmt(ReturnStmt returnStmt) {
        Expr value = returnStmt.value;
        return new ReturnStmt(value);
    }

    @Override
    public Object visitDoExpr(DoExpr doExpr) {
        List<Stmt> body = doExpr.body;
        return new DoExpr(body);
    }

    @Override
    public Object visitGroupingExpr(GroupingExpr groupingExpr) {
        Expr expression = groupingExpr.expression;
        return new GroupingExpr(expression);
    }

    @Override
    public Object visitExprStmt(ExprStmt exprStmt) {
        Expr expression = exprStmt.expression;
        return new ExprStmt(expression);
    }

    @Override
    public Object visitVariableExpr(VariableExpr variableExpr) {
        String name = variableExpr.name;
        return new VariableExpr(name);
    }

    @Override
    public Object visitTryStmt(TryStmt tryStmt) {
        List<Stmt> tryBlock = tryStmt.tryBlock;
        String exceptionName = tryStmt.exceptionName;
        List<Stmt> catchBlock = tryStmt.catchBlock;
        List<Stmt> finallyBlock = tryStmt.finallyBlock;
        return new TryStmt(tryBlock, exceptionName, catchBlock, finallyBlock);
    }

    @Override
    public Object visitExitStmt(ExitStmt exitStmt) {
        return new ExitStmt();
    }

    @Override
    public Object visitIfStmt(IfStmt ifStmt) {
        Expr condition = ifStmt.condition;
        Stmt thenBranch = ifStmt.thenBranch;
        Stmt elseBranch = ifStmt.elseBranch;
        return new IfStmt(condition, thenBranch, elseBranch);
    }

    @Override
    public Object visitMethodStmt(MethodStmt methodStmt) {
        String name = methodStmt.name;
        List<String> params = methodStmt.params;
        List<Stmt> body = methodStmt.body;
        return new MethodStmt(name, params, body);
    }

    @Override
    public Object visitLambdaExpr(LambdaExpr lambdaExpr) {
        List<String> params = lambdaExpr.params;
        Expr body = lambdaExpr.body;
        return new LambdaExpr(params, body);
    }

    @Override
    public Object visitUnaryExpr(UnaryExpr unaryExpr) {
        String operator = unaryExpr.operator;
        Expr right = unaryExpr.right;
        return new UnaryExpr(operator, right);
    }

    @Override
    public Object visitThrowStmt(ThrowStmt throwStmt) {
        Expr exception = throwStmt.exception;
        return new ThrowStmt(exception);
    }

    @Override
    public Object visitImportStmt(ImportStmt importStmt) {
        String moduleName = importStmt.moduleName;
        String alias = importStmt.alias;
        return new ImportStmt(moduleName, alias);
    }

}
