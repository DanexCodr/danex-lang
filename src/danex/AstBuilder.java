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
        Expr expression = awaitExpr.expression.accept(this);
        return new AwaitExpr(expression);
    }

    @Override
    public Decl visitMethodDecl(MethodDecl methodDecl) {
        String name = methodDecl.name;
        ReturnSpec returnSpec = methodDecl.returnSpec.accept(this);
        List<Annotation> annotations = methodDecl.annotations;
        List<String> modifiers = methodDecl.modifiers;
        List<ParamDecl> params = methodDecl.params;
        BlockStmt body = methodDecl.body.accept(this);
        Expr exprBody = methodDecl.exprBody.accept(this);
        boolean isArrow = methodDecl.isArrow;
        return new MethodDecl(name, returnSpec, annotations, modifiers, params, body, exprBody, isArrow);
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
        Expr left = nullCoalesceExpr.left.accept(this);
        Expr right = nullCoalesceExpr.right.accept(this);
        return new NullCoalesceExpr(left, right);
    }

    @Override
    public Stmt visitAssignStmt(AssignStmt assignStmt) {
        Expr target = assignStmt.target.accept(this);
        Expr value = assignStmt.value.accept(this);
        return new AssignStmt(target, value);
    }

    @Override
    public Expr visitCallExpr(CallExpr callExpr) {
        Expr callee = callExpr.callee.accept(this);
        List<Expr> arguments = callExpr.arguments;
        return new CallExpr(callee, arguments);
    }

    @Override
    public Stmt visitForStmt(ForStmt forStmt) {
        Stmt init = forStmt.init.accept(this);
        Expr condition = forStmt.condition.accept(this);
        Expr update = forStmt.update.accept(this);
        Stmt body = forStmt.body.accept(this);
        return new ForStmt(init, condition, update, body);
    }

    @Override
    public Expr visitComparatorExpr(ComparatorExpr comparatorExpr) {
        Expr left = comparatorExpr.left.accept(this);
        Expr right = comparatorExpr.right.accept(this);
        return new ComparatorExpr(left, right);
    }

    @Override
    public Stmt visitBlockStmt(BlockStmt blockStmt) {
        List<Stmt> statements = blockStmt.statements;
        return new BlockStmt(statements);
    }

    @Override
    public Stmt visitWhileStmt(WhileStmt whileStmt) {
        Expr condition = whileStmt.condition.accept(this);
        Stmt body = whileStmt.body.accept(this);
        return new WhileStmt(condition, body);
    }

    @Override
    public Stmt visitDoWhileStmt(DoWhileStmt doWhileStmt) {
        Stmt body = doWhileStmt.body.accept(this);
        Expr condition = doWhileStmt.condition.accept(this);
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
        Expr value = assignExpr.value.accept(this);
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
        Expr left = binaryExpr.left.accept(this);
        String operator = binaryExpr.operator;
        Expr right = binaryExpr.right.accept(this);
        return new BinaryExpr(left, operator, right);
    }

    @Override
    public Expr visitDoExpr(DoExpr doExpr) {
        List<Stmt> body = doExpr.body;
        return new DoExpr(body);
    }

    @Override
    public Expr visitGroupingExpr(GroupingExpr groupingExpr) {
        Expr expression = groupingExpr.expression.accept(this);
        return new GroupingExpr(expression);
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
        Expr condition = ifStmt.condition.accept(this);
        Stmt thenBranch = ifStmt.thenBranch.accept(this);
        Stmt elseBranch = ifStmt.elseBranch.accept(this);
        return new IfStmt(condition, thenBranch, elseBranch);
    }

    @Override
    public Expr visitLambdaExpr(LambdaExpr lambdaExpr) {
        List<ParamDecl> params = lambdaExpr.params;
        Expr body = lambdaExpr.body.accept(this);
        return new LambdaExpr(params, body);
    }

    @Override
    public Expr visitUnaryExpr(UnaryExpr unaryExpr) {
        String operator = unaryExpr.operator;
        Expr right = unaryExpr.right.accept(this);
        return new UnaryExpr(operator, right);
    }

    @Override
    public Stmt visitThrowStmt(ThrowStmt throwStmt) {
        Expr exception = throwStmt.exception.accept(this);
        return new ThrowStmt(exception);
    }

    // === Manual overrides for certain AST node types ===

    @Override
    public Annotation visitAnnotation(Annotation annotation) {
        return new Annotation(annotation.name);
    }

    @Override
    public Param visitParam(Param param) {
        return new Param(param.type, param.name, param.varargs);
    }

    @Override
    public ResourceDecl visitResourceDecl(ResourceDecl rd) {
        Expr init = rd.initializer.accept(this);
        return new ResourceDecl(rd.type, rd.name, init);
    }

    @Override
    public ExprStmt visitExprStmt(ExprStmt stmt) {
        Expr e = stmt.expression.accept(this);
        return new ExprStmt(e);
    }

}
