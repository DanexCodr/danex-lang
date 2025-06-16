package danex;

import danex.ast.*;
import java.util.*;

public class AstBuilder implements Expr.Visitor<Expr>, Stmt.Visitor<Stmt>, Decl.Visitor<Decl> {

    @Override
    public Decl visitImportDecl(ImportDecl importDecl) {
        String moduleName = importDecl.moduleName;
        String alias = importDecl.alias;
        return new ImportDecl(moduleName, alias);
    }

    @Override
    public Expr visitAwaitExpr(AwaitExpr awaitExpr) {
        Expr expression = awaitExpr.expression;
        return new AwaitExpr(expression);
    }

    @Override
    public Decl visitMethodDecl(MethodDecl methodDecl) {
        String name = methodDecl.name;
        String resultType = methodDecl.resultType;
        String resultName = methodDecl.resultName;
        List<Annotation> annotations = methodDecl.annotations;
        List<String> modifiers = methodDecl.modifiers;
        List<Param> params = methodDecl.params;
        Stmt body = methodDecl.body;
        return new MethodDecl(name, resultType, resultName, annotations, modifiers, params, body);
    }

    @Override
    public Expr visitTryExpr(TryExpr tryExpr) {
        List<Stmt> tryBlock = tryExpr.tryBlock;
        String catchType = tryExpr.catchType;
        String catchName = tryExpr.catchName;
        List<Stmt> catchBlock = tryExpr.catchBlock;
        List<Stmt> finallyBlock = tryExpr.finallyBlock;
        return new TryExpr(tryBlock, catchType, catchName, catchBlock, finallyBlock);
    }

    @Override
    public Expr visitNullCoalesceExpr(NullCoalesceExpr nullCoalesceExpr) {
        Expr left = nullCoalesceExpr.left;
        Expr right = nullCoalesceExpr.right;
        return new NullCoalesceExpr(left, right);
    }

    @Override
    public Decl visitResourceDecl(ResourceDecl resourceDecl) {
        String type = resourceDecl.type;
        String name = resourceDecl.name;
        Expr initializer = resourceDecl.initializer;
        return new ResourceDecl(type, name, initializer);
    }

    @Override
    public Expr visitCallExpr(CallExpr callExpr) {
        Expr callee = callExpr.callee;
        List<Expr> arguments = callExpr.arguments;
        return new CallExpr(callee, arguments);
    }

    @Override
    public Stmt visitForStmt(ForStmt forStmt) {
        Stmt init = forStmt.init;
        Expr condition = forStmt.condition;
        Expr update = forStmt.update;
        Stmt body = forStmt.body;
        return new ForStmt(init, condition, update, body);
    }

    @Override
    public Expr visitComparatorExpr(ComparatorExpr comparatorExpr) {
        Expr left = comparatorExpr.left;
        Expr right = comparatorExpr.right;
        return new ComparatorExpr(left, right);
    }

    @Override
    public Stmt visitBlockStmt(BlockStmt blockStmt) {
        List<Stmt> statements = blockStmt.statements;
        return new BlockStmt(statements);
    }

    @Override
    public Stmt visitWhileStmt(WhileStmt whileStmt) {
        Expr condition = whileStmt.condition;
        Stmt body = whileStmt.body;
        return new WhileStmt(condition, body);
    }

    @Override
    public Stmt visitDoWhileStmt(DoWhileStmt doWhileStmt) {
        Stmt body = doWhileStmt.body;
        Expr condition = doWhileStmt.condition;
        return new DoWhileStmt(body, condition);
    }

    @Override
    public Expr visitLiteralExpr(LiteralExpr literalExpr) {
        Object value = literalExpr.value;
        return new LiteralExpr(value);
    }

    @Override
    public Expr visitAssignExpr(AssignExpr assignExpr) {
        String name = assignExpr.name;
        Expr value = assignExpr.value;
        return new AssignExpr(name, value);
    }

    @Override
    public Decl visitClassDecl(ClassDecl classDecl) {
        String name = classDecl.name;
        List<Annotation> annotations = classDecl.annotations;
        List<String> modifiers = classDecl.modifiers;
        List<Decl> members = classDecl.members;
        return new ClassDecl(name, annotations, modifiers, members);
    }

    @Override
    public Expr visitBinaryExpr(BinaryExpr binaryExpr) {
        Expr left = binaryExpr.left;
        String operator = binaryExpr.operator;
        Expr right = binaryExpr.right;
        return new BinaryExpr(left, operator, right);
    }

    @Override
    public Stmt visitReturnStmt(ReturnStmt returnStmt) {
        Expr value = returnStmt.value;
        return new ReturnStmt(value);
    }

    @Override
    public Expr visitDoExpr(DoExpr doExpr) {
        List<Stmt> body = doExpr.body;
        return new DoExpr(body);
    }

    @Override
    public Expr visitGroupingExpr(GroupingExpr groupingExpr) {
        Expr expression = groupingExpr.expression;
        return new GroupingExpr(expression);
    }

    @Override
    public Stmt visitExprStmt(ExprStmt exprStmt) {
        Expr expression = exprStmt.expression;
        return new ExprStmt(expression);
    }

    @Override
    public Expr visitVariableExpr(VariableExpr variableExpr) {
        String name = variableExpr.name;
        return new VariableExpr(name);
    }

    @Override
    public Stmt visitTryStmt(TryStmt tryStmt) {
        List<ResourceDecl> resources = tryStmt.resources;
        List<Stmt> tryBlock = tryStmt.tryBlock;
        String catchType = tryStmt.catchType;
        String catchName = tryStmt.catchName;
        List<Stmt> catchBlock = tryStmt.catchBlock;
        List<Stmt> finallyBlock = tryStmt.finallyBlock;
        return new TryStmt(resources, tryBlock, catchType, catchName, catchBlock, finallyBlock);
    }

    @Override
    public Stmt visitExitStmt(ExitStmt exitStmt) {
        return new ExitStmt();
    }

    @Override
    public Stmt visitIfStmt(IfStmt ifStmt) {
        Expr condition = ifStmt.condition;
        Stmt thenBranch = ifStmt.thenBranch;
        Stmt elseBranch = ifStmt.elseBranch;
        return new IfStmt(condition, thenBranch, elseBranch);
    }

    @Override
    public Expr visitLambdaExpr(LambdaExpr lambdaExpr) {
        List<Param> params = lambdaExpr.params;
        Expr body = lambdaExpr.body;
        return new LambdaExpr(params, body);
    }

    @Override
    public Expr visitUnaryExpr(UnaryExpr unaryExpr) {
        String operator = unaryExpr.operator;
        Expr right = unaryExpr.right;
        return new UnaryExpr(operator, right);
    }

    @Override
    public Stmt visitThrowStmt(ThrowStmt throwStmt) {
        Expr exception = throwStmt.exception;
        return new ThrowStmt(exception);
    }

    // === Manual overrides for certain AST node types ===

    @Override
    public Annotation visitAnnotation(Annotation annotation) {
        // Return a new Annotation or process children if needed
        return new Annotation(annotation.name);
    }

    @Override
    public Param visitParam(Param param) {
        // Return a new Param or process children if needed
        return new Param(param.type, param.name, param.varargs);
    }

    @Override
    public ResourceDecl visitResourceDecl(ResourceDecl rd) {
        // Visit initializer expression
        Expr init = rd.initializer.accept(this);
        return new ResourceDecl(rd.type, rd.name, init);
    }

    @Override
    public ExprStmt visitExprStmt(ExprStmt stmt) {
        // Visit inner expression
        Expr e = stmt.expression.accept(this);
        return new ExprStmt(e);
    }

    // If your AST uses ExpressionStmt instead of ExprStmt, uncomment below:
    // @Override
    // public ExpressionStmt visitExpressionStmt(ExpressionStmt stmt) {
    //     Expr e = stmt.expression.accept(this);
    //     return new ExpressionStmt(e);
    // }

    // Add further manual overrides here if needed.
}
