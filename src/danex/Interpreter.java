package danex;

import danex.ast.*;
import java.util.*;

public class Interpreter implements Expr.Visitor<Object>, Stmt.Visitor<Void>, Decl.Visitor<Void> {
    private Environment globals = new Environment();
    private Environment environment = globals;

    public Interpreter() {
        setupBuiltins();
    }

    private void setupBuiltins() {
        globals.define("print", new DanexCallable() {
            @Override
            public Object call(Interpreter interpreter, List<Object> args) {
                for (Object arg : args) {
                    System.out.println(arg);
                }
                return null;
            }

            @Override
            public String toString() {
                return "<builtin fn print>";
            }
        });
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

    public void executeInEnvironment(Stmt body, Environment newEnv) {
        Environment previous = this.environment;
        try {
            this.environment = newEnv;
            execute(body);
        } finally {
            this.environment = previous;
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

    // --- Visitor methods for declarations/params/annotations ---
    @Override
    public Void visitVarDeclStmt(VarDeclStmt varDeclStmt) {
    return null;
    }
    
    @Override
public Void visitParamDecl(danex.ast.ParamDecl paramDecl) {
    // ParamDecl nodes are metadata only for interpreter/runtime signature handling.
    // No runtime action required here.
    return null;
}
    @Override
    public Void visitAnnotation(Annotation annotation) {
        // No-op or implement annotation behavior here
        return null;
    }

    // --- Expr visitors ---
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
                if (left instanceof String || right instanceof String) {
                    return toStr(left) + toStr(right);
                }
                if (left instanceof Number && right instanceof Number) {
                    return binaryOp("+", (Number) left, (Number) right);
                }
                throw new RuntimeError("Operands must be two numbers or one must be string for '+'.");
            case "-":
            case "*":
            case "/":
            case "%":
                if (left instanceof Number && right instanceof Number) {
                    return binaryOp(op, (Number) left, (Number) right);
                }
                throw new RuntimeError("Operands must be numbers for '" + op + "'.");
            case "<":
                return compare((Number)left, (Number)right) < 0;
            case ">":
                return compare((Number)left, (Number)right) > 0;
            case "<=":
                return compare((Number)left, (Number)right) <= 0;
            case ">=":
                return compare((Number)left, (Number)right) >= 0;
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
                return (double) compare((Number)left, (Number)right);
            default:
                throw new RuntimeError("Unknown operator '" + op + "'.");
        }
    }

    private Number binaryOp(String op, Number a, Number b) {
        boolean aIsFloating = (a instanceof Double || a instanceof Float);
        boolean bIsFloating = (b instanceof Double || b instanceof Float);
        if (aIsFloating || bIsFloating) {
            double x = a.doubleValue(), y = b.doubleValue();
            switch (op) {
                case "+": return x + y;
                case "-": return x - y;
                case "*": return x * y;
                case "/":
                    if (y == 0) throw new RuntimeError("Division by zero.");
                    return x / y;
                case "%":
                    if (y == 0) throw new RuntimeError("Division by zero.");
                    return x % y;
            }
        } else if (a instanceof Long || b instanceof Long) {
            long x = a.longValue(), y = b.longValue();
            switch (op) {
                case "+": return x + y;
                case "-": return x - y;
                case "*": return x * y;
                case "/":
                    if (y == 0) throw new RuntimeError("Division by zero.");
                    return x / y;
                case "%":
                    if (y == 0) throw new RuntimeError("Division by zero.");
                    return x % y;
            }
        } else {
            int x = a.intValue(), y = b.intValue();
            switch (op) {
                case "+": return x + y;
                case "-": return x - y;
                case "*": return x * y;
                case "/":
                    if (y == 0) throw new RuntimeError("Division by zero.");
                    return x / y;
                case "%":
                    if (y == 0) throw new RuntimeError("Division by zero.");
                    return x % y;
            }
        }
        throw new RuntimeError("Unknown numeric operator: " + op);
    }

    private int compare(Number a, Number b) {
        double x = a.doubleValue(), y = b.doubleValue();
        return Double.compare(x, y);
    }

    @Override
    public Object visitUnaryExpr(UnaryExpr unaryExpr) {
        Object rightVal = evaluate(unaryExpr.right);
        String opu = unaryExpr.operator;
        switch (opu) {
            case "-":
                if (!(rightVal instanceof Number)) {
                    throw new RuntimeError("Operand must be a number for unary '-'.");
                }
                Number num = (Number) rightVal;
                if (num instanceof Double) {
                    return -num.doubleValue();
                } else if (num instanceof Float) {
                    return -num.floatValue();
                } else if (num instanceof Long) {
                    return -num.longValue();
                } else {
                    return -num.intValue();
                }
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

        if (!(callee instanceof DanexCallable)) {
            throw new RuntimeError("Can only call functions and classes.");
        }

        DanexCallable function = (DanexCallable) callee;
        return function.call(this, args);
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
        if (!(lc instanceof Number) || !(rc instanceof Number)) throw new RuntimeError("Operands must be numbers for '<=>'.");
        double ld = ((Number)lc).doubleValue(), rd = ((Number)rc).doubleValue();
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
        throw new RuntimeError("Try-expression not supported yet.");
    }

    // --- Stmt visitors ---
    @Override
    public Void visitAssignStmt(AssignStmt stmt) {
        Object value = evaluate(stmt.value);
        if (!(stmt.target instanceof VariableExpr)) {
            throw new RuntimeError("Invalid assignment target.");
        }
        String varName = ((VariableExpr) stmt.target).name;
        environment.assign(varName, value);
        return null;
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
    public Void visitThrowStmt(ThrowStmt throwStmt) {
        Object ex = evaluate(throwStmt.exception);
        throw new RuntimeError(toStr(ex));
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
            throw e;
        } finally {
            for (Stmt s : tryStmt.finallyBlock) execute(s);
        }
        return null;
    }

    @Override
    public Void visitClassDecl(ClassDecl classDecl) {
        throw new RuntimeError("Class declarations not implemented yet.");
    }

    @Override
    public Void visitMethodDecl(MethodDecl methodDecl) {
        DanexFunction function = new DanexFunction(methodDecl, environment);
        environment.define(methodDecl.name, function);
        return null;
    }

    @Override
    public Void visitImportDecl(ImportDecl importDecl) {
        return null;
    }

    @Override
    public Void visitResourceDecl(ResourceDecl decl) {
        return null;
    }

    public Environment getGlobals() {
        return globals;
    }
}
