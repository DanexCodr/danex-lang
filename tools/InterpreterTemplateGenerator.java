// tools/InterpreterTemplateGenerator.java
package tools;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class InterpreterTemplateGenerator {
    // Output path for the generated Interpreter.java
    private static final String OUT_DIR = "src/danex";
    private static final String PACKAGE_NAME = "danex";

    // Lists of AST node class names (should match your AST classes in package danex.ast)
    private static final String[] EXPR_NODES = {
        "LiteralExpr", "GroupingExpr", "VariableExpr", "AssignExpr", "BinaryExpr",
        "UnaryExpr", "CallExpr", "LambdaExpr", "NullCoalesceExpr", "ComparatorExpr",
        "AwaitExpr", "DoExpr", "TryExpr"
    };
    private static final String[] STMT_NODES = {
        "ExprStmt", "BlockStmt", "IfStmt", "WhileStmt", "DoWhileStmt",
        "ForStmt", "ReturnStmt", "ThrowStmt", "ExitStmt", "TryStmt",
        "ClassStmt", "MethodStmt", "ImportStmt"
    };

    public static void main(String[] args) throws IOException {
        Path outDir = Paths.get(OUT_DIR);
        if (!Files.exists(outDir)) {
            Files.createDirectories(outDir);
        }
        Path file = outDir.resolve("Interpreter.java");
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            writeHeader(w);
            writeHelpers(w);
            writeExprVisitorMethods(w);
            writeStmtVisitorMethods(w);
            w.write("}\n");
        }
        System.out.println("Generated Interpreter.java in " + file.toString());
    }

    private static void writeHeader(BufferedWriter w) throws IOException {
        w.write("package " + PACKAGE_NAME + ";\n\n");
        w.write("import danex.ast.*;\n");
        w.write("import java.util.*;\n\n");
        w.write("public class Interpreter implements Expr.Visitor<Object>, Stmt.Visitor<Void> {\n");
        // Environment and Return exception
        w.write("    private Environment globals = new Environment();\n");
        w.write("    private Environment environment = globals;\n\n");
        w.write("    private static class Return extends RuntimeException {\n");
        w.write("        final Object value;\n");
        w.write("        Return(Object value) { super(null, null, false, false); this.value = value; }\n");
        w.write("    }\n\n");
        // interpret / execute / evaluate methods
        w.write("    public void interpret(List<Stmt> statements) {\n");
        w.write("        try {\n");
        w.write("            for (Stmt stmt : statements) execute(stmt);\n");
        w.write("        } catch (RuntimeError error) {\n");
        w.write("            System.err.println(\"Runtime error: \" + error.getMessage());\n");
        w.write("        }\n");
        w.write("    }\n\n");
        w.write("    private void execute(Stmt stmt) { stmt.accept(this); }\n");
        w.write("    private Object evaluate(Expr expr) { return expr.accept(this); }\n\n");
    }

    private static void writeHelpers(BufferedWriter w) throws IOException {
        w.write("    private boolean isTruthy(Object obj) {\n");
        w.write("        if (obj == null) return false;\n");
        w.write("        if (obj instanceof Boolean) return (Boolean) obj;\n");
        w.write("        return true;\n");
        w.write("    }\n\n");
        w.write("    private boolean isEqual(Object a, Object b) {\n");
        w.write("        if (a == null && b == null) return true;\n");
        w.write("        if (a == null) return false;\n");
        w.write("        return a.equals(b);\n");
        w.write("    }\n\n");
        w.write("    private void checkNumberOperand(String op, Object operand) {\n");
        w.write("        if (!(operand instanceof Double)) throw new RuntimeError(\"Operand must be a number for '\" + op + \"'.\");\n");
        w.write("    }\n\n");
        w.write("    private double toNumber(Object obj) { return (Double) obj; }\n");
        w.write("    private String toStr(Object obj) { return obj == null ? \"null\" : obj.toString(); }\n\n");
    }

    private static void writeExprVisitorMethods(BufferedWriter w) throws IOException {
        for (String className : EXPR_NODES) {
            String var = decap(className);
            w.write("    @Override\n");
            w.write("    public Object visit" + className + "(" + className + " " + var + ") {\n");
            switch (className) {
                case "LiteralExpr":
                    w.write("        return " + var + ".value;\n");
                    break;
                case "GroupingExpr":
                    w.write("        return evaluate(" + var + ".expression);\n");
                    break;
                case "VariableExpr":
                    w.write("        return environment.get(" + var + ".name);\n");
                    break;
                case "AssignExpr":
                    w.write("        Object value = evaluate(" + var + ".value);\n");
                    w.write("        environment.assign(" + var + ".name, value);\n");
                    w.write("        return value;\n");
                    break;
                case "BinaryExpr":
                    w.write("        Object left = evaluate(" + var + ".left);\n");
                    w.write("        Object right = evaluate(" + var + ".right);\n");
                    w.write("        String op = " + var + ".operator;\n");
                    w.write("        switch (op) {\n");
                    // +, -, *, /, %, comparisons, logicals, null-coalesce, <=> 
                    w.write("            case \"+\":\n");
                    w.write("                if (left instanceof Double && right instanceof Double) return toNumber(left) + toNumber(right);\n");
                    w.write("                if (left instanceof String || right instanceof String) return toStr(left) + toStr(right);\n");
                    w.write("                throw new RuntimeError(\"Operands must be two numbers or one must be string for '+'.\");\n");
                    w.write("            case \"-\":\n");
                    w.write("                checkNumberOperand(op, left); checkNumberOperand(op, right);\n");
                    w.write("                return toNumber(left) - toNumber(right);\n");
                    w.write("            case \"*\":\n");
                    w.write("                checkNumberOperand(op, left); checkNumberOperand(op, right);\n");
                    w.write("                return toNumber(left) * toNumber(right);\n");
                    w.write("            case \"/\":\n");
                    w.write("                checkNumberOperand(op, left); checkNumberOperand(op, right);\n");
                    w.write("                if (toNumber(right) == 0) throw new RuntimeError(\"Division by zero.\");\n");
                    w.write("                return toNumber(left) / toNumber(right);\n");
                    w.write("            case \"%\":\n");
                    w.write("                checkNumberOperand(op, left); checkNumberOperand(op, right);\n");
                    w.write("                return toNumber(left) % toNumber(right);\n");
                    w.write("            case \"<\":\n");
                    w.write("                checkNumberOperand(op, left); checkNumberOperand(op, right);\n");
                    w.write("                return toNumber(left) < toNumber(right);\n");
                    w.write("            case \">\":\n");
                    w.write("                checkNumberOperand(op, left); checkNumberOperand(op, right);\n");
                    w.write("                return toNumber(left) > toNumber(right);\n");
                    w.write("            case \"<=\":\n");
                    w.write("                checkNumberOperand(op, left); checkNumberOperand(op, right);\n");
                    w.write("                return toNumber(left) <= toNumber(right);\n");
                    w.write("            case \">=\":\n");
                    w.write("                checkNumberOperand(op, left); checkNumberOperand(op, right);\n");
                    w.write("                return toNumber(left) >= toNumber(right);\n");
                    w.write("            case \"==\":\n");
                    w.write("                return isEqual(left, right);\n");
                    w.write("            case \"!=\":\n");
                    w.write("                return !isEqual(left, right);\n");
                    w.write("            case \"&&\":\n");
                    w.write("                return isTruthy(left) && isTruthy(right);\n");
                    w.write("            case \"||\":\n");
                    w.write("                return isTruthy(left) || isTruthy(right);\n");
                    w.write("            case \"??\":\n");
                    w.write("                return left != null ? left : right;\n");
                    w.write("            case \"<=>\":\n");
                    w.write("                checkNumberOperand(op, left); checkNumberOperand(op, right);\n");
                    w.write("                double l = toNumber(left), r = toNumber(right);\n");
                    w.write("                return l < r ? -1.0 : (l > r ? 1.0 : 0.0);\n");
                    w.write("            default:\n");
                    w.write("                throw new RuntimeError(\"Unknown operator '\" + op + \"'.\");\n");
                    w.write("        }\n");
                    break;
                case "UnaryExpr":
                    w.write("        Object rightVal = evaluate(" + var + ".right);\n");
                    w.write("        String opu = " + var + ".operator;\n");
                    w.write("        switch (opu) {\n");
                    w.write("            case \"-\":\n");
                    w.write("                checkNumberOperand(opu, rightVal);\n");
                    w.write("                return -toNumber(rightVal);\n");
                    w.write("            case \"!\":\n");
                    w.write("                return !isTruthy(rightVal);\n");
                    w.write("            default:\n");
                    w.write("                throw new RuntimeError(\"Unknown unary operator '\" + opu + \"'.\");\n");
                    w.write("        }\n");
                    break;
                case "CallExpr":
                    w.write("        Object callee = evaluate(" + var + ".callee);\n");
                    w.write("        List<Object> args = new ArrayList<>();\n");
                    w.write("        for (Expr arg : " + var + ".arguments) args.add(evaluate(arg));\n");
                    w.write("        throw new RuntimeError(\"Function calls not implemented yet.\");\n");
                    break;
                case "LambdaExpr":
                    w.write("        throw new RuntimeError(\"Lambdas not supported yet.\");\n");
                    break;
                case "NullCoalesceExpr":
                    w.write("        Object lVal = evaluate(" + var + ".left);\n");
                    w.write("        if (lVal != null) return lVal;\n");
                    w.write("        return evaluate(" + var + ".right);\n");
                    break;
                case "ComparatorExpr":
                    w.write("        Object lc = evaluate(" + var + ".left);\n");
                    w.write("        Object rc = evaluate(" + var + ".right);\n");
                    w.write("        if (!(lc instanceof Double) || !(rc instanceof Double)) throw new RuntimeError(\"Operands must be numbers for '<=>'.\");\n");
                    w.write("        double ld = toNumber(lc), rd = toNumber(rc);\n");
                    w.write("        return ld < rd ? -1.0 : (ld > rd ? 1.0 : 0.0);\n");
                    break;
                case "AwaitExpr":
                    w.write("        throw new RuntimeError(\"Await not supported yet.\");\n");
                    break;
                case "DoExpr":
                    w.write("        throw new RuntimeError(\"Do-expression not supported yet.\");\n");
                    break;
                case "TryExpr":
                    w.write("        throw new RuntimeError(\"Try-expression not supported yet.\");\n");
                    break;
                default:
                    w.write("        // TODO: implement " + className + "\n");
                    w.write("        return null;\n");
            }
            w.write("    }\n\n");
        }
    }

    private static void writeStmtVisitorMethods(BufferedWriter w) throws IOException {
        for (String className : STMT_NODES) {
            String var = decap(className);
            w.write("    @Override\n");
            w.write("    public Void visit" + className + "(" + className + " " + var + ") {\n");
            switch (className) {
                case "ExprStmt":
                    w.write("        evaluate(" + var + ".expression);\n");
                    break;
                case "BlockStmt":
                    w.write("        Environment previous = environment;\n");
                    w.write("        environment = new Environment(previous);\n");
                    w.write("        for (Stmt s : " + var + ".statements) execute(s);\n");
                    w.write("        environment = previous;\n");
                    break;
                case "IfStmt":
                    w.write("        if (isTruthy(evaluate(" + var + ".condition))) {\n");
                    w.write("            execute(" + var + ".thenBranch);\n");
                    w.write("        } else if (" + var + ".elseBranch != null) {\n");
                    w.write("            execute(" + var + ".elseBranch);\n");
                    w.write("        }\n");
                    break;
                case "WhileStmt":
                    w.write("        while (isTruthy(evaluate(" + var + ".condition))) {\n");
                    w.write("            execute(" + var + ".body);\n");
                    w.write("        }\n");
                    break;
                case "DoWhileStmt":
                    w.write("        do {\n");
                    w.write("            execute(" + var + ".body);\n");
                    w.write("        } while (isTruthy(evaluate(" + var + ".condition)));\n");
                    break;
                case "ForStmt":
                    w.write("        if (" + var + ".init != null) execute(" + var + ".init);\n");
                    w.write("        while (true) {\n");
                    w.write("            if (" + var + ".condition != null && !isTruthy(evaluate(" + var + ".condition))) break;\n");
                    w.write("            execute(" + var + ".body);\n");
                    w.write("            if (" + var + ".update != null) evaluate(" + var + ".update);\n");
                    w.write("        }\n");
                    break;
                case "ReturnStmt":
                    w.write("        Object value = null;\n");
                    w.write("        if (" + var + ".value != null) value = evaluate(" + var + ".value);\n");
                    w.write("        throw new Return(value);\n");
                    break;
                case "ThrowStmt":
                    w.write("        Object ex = evaluate(" + var + ".exception);\n");
                    w.write("        throw new RuntimeError(toStr(ex));\n");
                    break;
                case "ExitStmt":
                    w.write("        System.exit(0);\n");
                    break;
                case "TryStmt":
                    w.write("        try {\n");
                    w.write("            for (Stmt s : " + var + ".tryBlock) execute(s);\n");
                    w.write("        } catch (RuntimeError e) {\n");
                    w.write("            // TODO: handle catch: variable name = " + var + ".exceptionName\n");
                    w.write("            throw e;\n");
                    w.write("        } finally {\n");
                    w.write("            for (Stmt s : " + var + ".finallyBlock) execute(s);\n");
                    w.write("        }\n");
                    break;
                case "ClassStmt":
                    w.write("        throw new RuntimeError(\"Class declarations not implemented yet.\");\n");
                    break;
                case "MethodStmt":
                    w.write("        throw new RuntimeError(\"Method declarations not implemented yet.\");\n");
                    break;
                case "ImportStmt":
                    w.write("        // 'use' / import: no-op at runtime\n");
                    break;
                default:
                    w.write("        // TODO: handle " + className + "\n");
            }
            w.write("        return null;\n");
            w.write("    }\n\n");
        }
    }

    private static String decap(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }
  }
