package danex;

import danex.ast.*;
import java.util.*;

public class Interpreter implements Expr.Visitor<Object>, Stmt.Visitor<Void>, Decl.Visitor<Void> {

    private Environment globals = new Environment();
    private Environment environment = globals;

    public Environment getGlobals() {
        return globals;
    }

    public void interpret(List<Object> statementsAndDecls) {
        try {
            for (Object obj : statementsAndDecls) {
                if (obj instanceof Stmt) {
                    execute((Stmt) obj);
                } else if (obj instanceof Decl) {
                    declare((Decl) obj);
                }
            }
        } catch (RuntimeError error) {
            System.err.println("[Runtime Error] " + error.getMessage());
        }
    }

    private void execute(Stmt stmt) {
        stmt.accept(this);
    }

    private void declare(Decl decl) {
        decl.accept(this);
    }

    public void executeBlock(List<Object> body, Environment newEnv) {
        Environment previous = this.environment;
        try {
            this.environment = newEnv;
            for (Object obj : body) {
                if (obj instanceof Stmt) {
                    execute((Stmt) obj);
                } else if (obj instanceof Decl) {
                    declare((Decl) obj);
                }
            }
        } finally {
            this.environment = previous;
        }
    }

    // --------- Stmt Visitors ---------

    @Override
    public Void visitExpressionStmt(ExpressionStmt stmt) {
        evaluate(stmt.expression);
        return null;
    }

    @Override
    public Void visitVarStmt(VarStmt stmt) {
        Object value = null;
        if (stmt.initializer != null) {
            value = evaluate(stmt.initializer);
        }
        environment.define(stmt.name.lexeme, value);
        return null;
    }

    @Override
    public Void visitAssignStmt(AssignStmt stmt) {
        Object value = evaluate(stmt.value);
        environment.assign(stmt.name, value);
        return null;
    }

    @Override
    public Void visitPrintStmt(PrintStmt stmt) {
        Object value = evaluate(stmt.expression);
        System.out.println(stringify(value));
        return null;
    }

    @Override
    public Void visitReturnStmt(ReturnStmt stmt) {
        Object value = null;
        if (stmt.value != null) value = evaluate(stmt.value);
        throw new Return(value);
    }

    @Override
    public Void visitBlockStmt(BlockStmt stmt) {
        executeBlock(stmt.statements, new Environment(environment));
        return null;
    }

    @Override
    public Void visitIfStmt(IfStmt stmt) {
        if (isTruthy(evaluate(stmt.condition))) {
            execute(stmt.thenBranch);
        } else if (stmt.elseBranch != null) {
            execute(stmt.elseBranch);
        }
        return null;
    }

    @Override
    public Void visitWhileStmt(WhileStmt stmt) {
        while (isTruthy(evaluate(stmt.condition))) {
            execute(stmt.body);
        }
        return null;
    }

    @Override
    public Void visitExitStmt(ExitStmt stmt) {
        System.exit(0);
        return null;
    }

    // --------- Decl Visitors ---------

    @Override
    public Void visitClassDecl(ClassDecl decl) {
        DanexClass klass = new DanexClass(decl.name.lexeme);
        environment.define(decl.name.lexeme, klass);
        return null;
    }

    @Override
    public Void visitMethodDecl(MethodDecl decl) {
        DanexFunction function = new DanexFunction(decl, environment);
        environment.define(decl.name.lexeme, function);
        return null;
    }

    @Override
    public Void visitImportDecl(ImportDecl decl) {
        // Placeholder: implement your import system if any
        return null;
    }

    @Override
    public Void visitResourceDecl(ResourceDecl decl) {
        // Optional: implement if needed
        return null;
    }

    // These are placeholders until you create actual AST classes for Annotation and Param

    @Override public Void visitAnnotation(Annotation a) {
        return null;
    }

    @Override public Void visitParam(Param p) {
        return null;
    }

    // --------- Expression Visitors ---------

    @Override
    public Object visitLiteralExpr(LiteralExpr expr) {
        return expr.value;
    }

    @Override
    public Object visitVariableExpr(VariableExpr expr) {
        return environment.get(expr.name);
    }

    @Override
    public Object visitAssignExpr(AssignExpr expr) {
        Object value = evaluate(expr.value);
        environment.assign(expr.name, value);
        return value;
    }

    @Override
    public Object visitBinaryExpr(BinaryExpr expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case PLUS:
                if (left instanceof Double && right instanceof Double) {
                    return (double) left + (double) right;
                }
                if (left instanceof String || right instanceof String) {
                    return stringify(left) + stringify(right);
                }
                throw new RuntimeError(expr.operator, "Operands must be numbers or strings.");
            case MINUS:
                return (double) left - (double) right;
            case STAR:
                return (double) left * (double) right;
            case SLASH:
                return (double) left / (double) right;
            case GREATER:
                return (double) left > (double) right;
            case GREATER_EQUAL:
                return (double) left >= (double) right;
            case LESS:
                return (double) left < (double) right;
            case LESS_EQUAL:
                return (double) left <= (double) right;
            case EQUAL_EQUAL:
                return Objects.equals(left, right);
            case BANG_EQUAL:
                return !Objects.equals(left, right);
        }

        return null;
    }

    @Override
    public Object visitGroupingExpr(GroupingExpr expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Object visitCallExpr(CallExpr expr) {
        Object callee = evaluate(expr.callee);

        List<Object> arguments = new ArrayList<>();
        for (Expr arg : expr.arguments) {
            arguments.add(evaluate(arg));
        }

        if (!(callee instanceof DanexCallable)) {
            throw new RuntimeError(expr.paren, "Can only call functions and classes.");
        }

        DanexCallable function = (DanexCallable) callee;
        return function.call(this, arguments);
    }

    // --------- Utilities ---------

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

    private boolean isTruthy(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Boolean) return (Boolean) obj;
        return true;
    }

    private String stringify(Object obj) {
        if (obj == null) return "nil";
        return obj.toString();
    }
}
