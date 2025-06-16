package danex;

import danex.ast.*;
import java.util.*;

public class Interpreter implements Expr.Visitor<Object>, Stmt.Visitor<Void>, Decl.Visitor<Void> {
    private Environment globals = new Environment();
    private Environment environment = globals;

    private static class Return extends RuntimeException {
        final Object value;
        Return(Object value) { super(null, null, false, false); this.value = value; }
    }

    public void interpret(List<Stmt> statements) {
        try {
            for (Stmt stmt : statements) execute(stmt);
        } catch (RuntimeError error) {
            System.err.println("Runtime error: " + error.getMessage());
        }
    }

    public void interpretDecls(List<Decl> decls) {
        try {
            for (Decl decl : decls) decl.accept(this);
        } catch (RuntimeError error) {
            System.err.println("Runtime error in declaration: " + error.getMessage());
        }
    }

    private void execute(Stmt stmt) { stmt.accept(this); }
    private Object evaluate(Expr expr) { return expr.accept(this); }

    private boolean isTruthy(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Boolean) return (Boolean) obj;
        return true;
    }

    private boolean isEqual(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null) return false;
        return a.equals(b);
    }

    private void checkNumberOperand(String op, Object operand) {
        if (!(operand instanceof Double)) throw new RuntimeError("Operand must be a number for '" + op + "'.");
    }

    private double toNumber(Object obj) { return (Double) obj; }
    private String toStr(Object obj) { return obj == null ? "null" : obj.toString(); }

    @Override
    public Object visitLiteralExpr(LiteralExpr literalExpr) {
        return literalExpr.value;
    }

    @Override
    public Object visitGroupingExpr(GroupingExpr groupingExpr) {
        return evaluate(groupingExpr.expression);
    }

    @Override
    public Object visitVariableExpr(VariableExpr variableExpr) {
        return environment.get(variableExpr.name);
    }

    @Override
    public Object visitAssignExpr(AssignExpr assignExpr) {
        Object value = evaluate(assignExpr.value);
        environment.assign(assignExpr.name, value);
        return value;
    }

    @Override
    public Object visitBinaryExpr(BinaryExpr binaryExpr) {
        Object left = evaluate(binaryExpr.left);
        Object right = evaluate(binaryExpr.right);
        String op = binaryExpr.operator;
        switch (op) {
            case "+":
                if (left instanceof Double && right instanceof Double) return toNumber(left) + toNumber(right);
                if (left instanceof String || right instanceof String) return toStr(left) + toStr(right);
                throw new RuntimeError("Operands must be two numbers or one must be string for '+'.");
            case "-":
                checkNumberOperand(op, left); checkNumberOperand(op, right);
                return toNumber(left) - toNumber(right);
            case "*":
                checkNumberOperand(op, left); checkNumberOperand(op, right);
                return toNumber(left) * toNumber(right);
            case "/":
                checkNumberOperand(op, left); checkNumberOperand(op, right);
                if (toNumber(right) == 0) throw new RuntimeError("Division by zero.");
                return toNumber(left) / toNumber(right);
            case "%":
                checkNumberOperand(op, left); checkNumberOperand(op, right);
                return toNumber(left) % toNumber(right);
            case "<":
                checkNumberOperand(op, left); checkNumberOperand(op, right);
                return toNumber(left) < toNumber(right);
            case ">":
                checkNumberOperand(op, left); checkNumberOperand(op, right);
                return toNumber(left) > toNumber(right);
            case "<=":
                checkNumberOperand(op, left); checkNumberOperand(op, right);
                return toNumber(left) <= toNumber(right);
            case ">=":
                checkNumberOperand(op, left); checkNumberOperand(op, right);
                return toNumber(left) >= toNumber(right);
            case "==":
                return isEqual(left, right);
            case "!=":
                return !isEqual(left, right);
            case "&&":
                return isTruthy(left) && isTruthy(right);
            case "||":
                return isTruthy(left) || isTruthy(right);
            case "??":
                return left != null ? left : right;
            case "<=>":
                checkNumberOperand(op, left); checkNumberOperand(op, right);
                double l = toNumber(left), r = toNumber(right);
                return l < r ? -1.0 : (l > r ? 1.0 : 0.0);
            default:
                throw new RuntimeError("Unknown operator '" + op + "'.");
        }
    }

    @Override
    public Object visitUnaryExpr(UnaryExpr unaryExpr) {
        Object rightVal = evaluate(unaryExpr.right);
        String opu = unaryExpr.operator;
        switch (opu) {
            case "-":
                checkNumberOperand(opu, rightVal);
                return -toNumber(rightVal);
            case "!":
                return !isTruthy(rightVal);
            default:
                throw new RuntimeError("Unknown unary operator '" + opu + "'.");
        }
    }

    @Override
    public Object visitCallExpr(CallExpr callExpr) {
        Object callee = evaluate(callExpr.callee);
        List<Object> args = new ArrayList<>();
        for (Expr arg : callExpr.arguments) args.add(evaluate(arg));
        throw new RuntimeError("Function calls not implemented yet.");
    }

    @Override
    public Object visitLambdaExpr(LambdaExpr lambdaExpr) {
        throw new RuntimeError("Lambdas not supported yet.");
    }

    @Override
    public Object visitNullCoalesceExpr(NullCoalesceExpr nullCoalesceExpr) {
        Object lVal = evaluate(nullCoalesceExpr.left);
        if (lVal != null) return lVal;
        return evaluate(nullCoalesceExpr.right);
    }

    @Override
    public Object visitComparatorExpr(ComparatorExpr comparatorExpr) {
        Object lc = evaluate(comparatorExpr.left);
        Object rc = evaluate(comparatorExpr.right);
        if (!(lc instanceof Double) || !(rc instanceof Double)) throw new RuntimeError("Operands must be numbers for '<=>'.");
        double ld = toNumber(lc), rd = toNumber(rc);
        return ld < rd ? -1.0 : (ld > rd ? 1.0 : 0.0);
    }

    @Override
    public Object visitAwaitExpr(AwaitExpr awaitExpr) {
        throw new RuntimeError("Await not supported yet.");
    }

    @Override
    public Object visitDoExpr(DoExpr doExpr) {
        throw new RuntimeError("Do-expression not supported yet.");
    }

    @Override
    public Object visitTryExpr(TryExpr tryExpr) {
        // Evaluate try-expression: TODO implement semantics
        throw new RuntimeError("Try-expression not supported yet.");
    }

    @Override
    public Void visitExprStmt(ExprStmt exprStmt) {
        evaluate(exprStmt.expression);
        return null;
    }

    @Override
    public Void visitBlockStmt(BlockStmt blockStmt) {
        Environment previous = environment;
        environment = new Environment(previous);
        for (Stmt s : blockStmt.statements) execute(s);
        environment = previous;
        return null;
    }

    @Override
    public Void visitIfStmt(IfStmt ifStmt) {
        if (isTruthy(evaluate(ifStmt.condition))) {
            execute(ifStmt.thenBranch);
        } else if (ifStmt.elseBranch != null) {
            execute(ifStmt.elseBranch);
        }
        return null;
    }

    @Override
    public Void visitWhileStmt(WhileStmt whileStmt) {
        while (isTruthy(evaluate(whileStmt.condition))) {
            execute(whileStmt.body);
        }
        return null;
    }

    @Override
    public Void visitDoWhileStmt(DoWhileStmt doWhileStmt) {
        do {
            execute(doWhileStmt.body);
        } while (isTruthy(evaluate(doWhileStmt.condition)));
        return null;
    }

    @Override
    public Void visitForStmt(ForStmt forStmt) {
        if (forStmt.init != null) execute(forStmt.init);
        while (true) {
            if (forStmt.condition != null && !isTruthy(evaluate(forStmt.condition))) break;
            execute(forStmt.body);
            if (forStmt.update != null) evaluate(forStmt.update);
        }
        return null;
    }

    @Override
    public Void visitReturnStmt(ReturnStmt returnStmt) {
        Object value = null;
        if (returnStmt.value != null) value = evaluate(returnStmt.value);
        throw new Return(value);
        return null;
    }

    @Override
    public Void visitThrowStmt(ThrowStmt throwStmt) {
        Object ex = evaluate(throwStmt.exception);
        throw new RuntimeError(toStr(ex));
        return null;
    }

    @Override
    public Void visitExitStmt(ExitStmt exitStmt) {
        System.exit(0);
        return null;
    }

    @Override
    public Void visitTryStmt(TryStmt tryStmt) {
        try {
            for (Stmt s : tryStmt.tryBlock) execute(s);
        } catch (RuntimeError e) {
            // TODO: handle catch: type = " + tryStmt.catchType + ", name = " + tryStmt.catchName + "
            throw e;
        } finally {
            for (Stmt s : tryStmt.finallyBlock) execute(s);
        }
        return null;
    }

    @Override
    public Void visitClassDecl(ClassDecl classDecl) {
        // TODO: implement class declaration: name = " + classDecl.name + "
        throw new RuntimeError("Class declarations not implemented yet.");
        return null;
    }

    @Override
    public Void visitMethodDecl(MethodDecl methodDecl) {
        // TODO: implement method declaration: name = " + methodDecl.name + "
        throw new RuntimeError("Method declarations not implemented yet.");
        return null;
    }

    @Override
    public Void visitImportDecl(ImportDecl importDecl) {
        // 'use' / import: no-op or record module as needed
        return null;
    }

    @Override
    public Void visitResourceDecl(ResourceDecl rd) {
        // handle try-with-resources or ignore
        return null;
    }

    @Override
    public Void visitParam(Param param) {
        return null;
    }

    @Override
    public Void visitAnnotation(Annotation annotation) {
        return null;
    }
    
}
