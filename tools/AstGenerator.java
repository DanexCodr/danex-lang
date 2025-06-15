// tools/AstGenerator.java
package tools;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AstGenerator {
    // Output directory for generated AST .java files
    private static final String OUTPUT_DIR = "src/danex/ast";

    public static void main(String[] args) throws IOException {
        // Ensure output directory exists
        Path outDir = Paths.get(OUTPUT_DIR);
        if (!Files.exists(outDir)) {
            Files.createDirectories(outDir);
        }
        // Base classes
        generateASTNode();
        generateExprBase();
        generateStmtBase();

        // Expression subclasses
        List<NodeDef> exprNodes = Arrays.asList(
            new NodeDef("BinaryExpr", Arrays.asList("Expr left", "String operator", "Expr right")),
            new NodeDef("UnaryExpr", Arrays.asList("String operator", "Expr right")),
            new NodeDef("LiteralExpr", Arrays.asList("Object value")),
            new NodeDef("GroupingExpr", Arrays.asList("Expr expression")),
            new NodeDef("VariableExpr", Arrays.asList("String name")),
            new NodeDef("AssignExpr", Arrays.asList("String name", "Expr value")),
            new NodeDef("CallExpr", Arrays.asList("Expr callee", "List<Expr> arguments")),
            new NodeDef("LambdaExpr", Arrays.asList("List<String> params", "Expr body")),
            new NodeDef("DoExpr", Arrays.asList("List<Stmt> body")),       // adjust fields as needed
            new NodeDef("TryExpr", Arrays.asList("Expr tryBody", "String exceptionName", "Expr catchBody", "Expr finallyBody")),
            new NodeDef("AwaitExpr", Arrays.asList("Expr expression")),
            new NodeDef("NullCoalesceExpr", Arrays.asList("Expr left", "Expr right")),
            new NodeDef("ComparatorExpr", Arrays.asList("Expr left", "Expr right"))
        );
        for (NodeDef nd : exprNodes) {
            generateExprSubclass(nd);
        }

        // Statement subclasses
        List<NodeDef> stmtNodes = Arrays.asList(
            new NodeDef("ExprStmt", Arrays.asList("Expr expression")),
            new NodeDef("BlockStmt", Arrays.asList("List<Stmt> statements")),
            new NodeDef("IfStmt", Arrays.asList("Expr condition", "Stmt thenBranch", "Stmt elseBranch")), // elseBranch may be null
            new NodeDef("WhileStmt", Arrays.asList("Expr condition", "Stmt body")),
            new NodeDef("DoWhileStmt", Arrays.asList("Stmt body", "Expr condition")),
            new NodeDef("ForStmt", Arrays.asList("Stmt init", "Expr condition", "Expr update", "Stmt body")),
            new NodeDef("ReturnStmt", Arrays.asList("Expr value")),
            new NodeDef("ThrowStmt", Arrays.asList("Expr exception")),
            new NodeDef("ExitStmt", Collections.emptyList()),
            new NodeDef("TryStmt", Arrays.asList("List<Stmt> tryBlock", "String exceptionName", "List<Stmt> catchBlock", "List<Stmt> finallyBlock")),
            new NodeDef("ClassStmt", Arrays.asList("String name", "List<Stmt> members")),
            new NodeDef("MethodStmt", Arrays.asList("String name", "List<String> params", "List<Stmt> body")),
            new NodeDef("ImportStmt", Arrays.asList("String moduleName", "String alias")) // alias may be null if not used
        );
        for (NodeDef nd : stmtNodes) {
            generateStmtSubclass(nd);
        }

        System.out.println("AST classes generated in " + OUTPUT_DIR);
    }

    // Represents a node name and its fields: "Type name"
    static class NodeDef {
        String className;
        List<String> fields; // each like "Type name"
        NodeDef(String className, List<String> fields) {
            this.className = className;
            this.fields = fields;
        }
    }

    private static void generateASTNode() throws IOException {
        String pkg = "danex.ast";
        String className = "ASTNode";
        Path file = Paths.get(OUTPUT_DIR, className + ".java");
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            w.write("package " + pkg + ";"); w.newLine(); w.newLine();
            w.write("public abstract class " + className + " {"); w.newLine();
            w.write("    // Base for all AST nodes. You may add common info like source position."); w.newLine();
            w.write("}"); w.newLine();
        }
    }

    private static void generateExprBase() throws IOException {
        String pkg = "danex.ast";
        String className = "Expr";
        Path file = Paths.get(OUTPUT_DIR, className + ".java");
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            w.write("package " + pkg + ";"); w.newLine(); w.newLine();
            w.write("public abstract class " + className + " extends ASTNode {"); w.newLine();
            // Visitor interface
            w.write("    public interface Visitor<R> {"); w.newLine();
            // One method per concrete Expr: we know names in exprNodes list
            String[] concreteExprs = {"BinaryExpr","UnaryExpr","LiteralExpr","GroupingExpr","VariableExpr","AssignExpr","CallExpr","LambdaExpr","DoExpr","TryExpr","AwaitExpr","NullCoalesceExpr","ComparatorExpr"};
            for (String ce : concreteExprs) {
                w.write("        R visit" + ce + "(" + ce + " " + decap(ce) + ");"); w.newLine();
            }
            w.write("    }"); w.newLine(); w.newLine();
            // accept method
            w.write("    public abstract <R> R accept(Visitor<R> visitor);"); w.newLine();
            w.write("}"); w.newLine();
        }
    }

    private static void generateStmtBase() throws IOException {
        String pkg = "danex.ast";
        String className = "Stmt";
        Path file = Paths.get(OUTPUT_DIR, className + ".java");
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            w.write("package " + pkg + ";"); w.newLine(); w.newLine();
            w.write("public abstract class " + className + " extends ASTNode {"); w.newLine();
            // Visitor interface
            w.write("    public interface Visitor<R> {"); w.newLine();
            String[] concreteStmts = {"ExprStmt","BlockStmt","IfStmt","WhileStmt","DoWhileStmt","ForStmt","ReturnStmt","ThrowStmt","ExitStmt","TryStmt","ClassStmt","MethodStmt","ImportStmt"};
            for (String cs : concreteStmts) {
                w.write("        R visit" + cs + "(" + cs + " " + decap(cs) + ");"); w.newLine();
            }
            w.write("    }"); w.newLine(); w.newLine();
            w.write("    public abstract <R> R accept(Visitor<R> visitor);"); w.newLine();
            w.write("}"); w.newLine();
        }
    }

    private static void generateExprSubclass(NodeDef nd) throws IOException {
        String pkg = "danex.ast";
        Path file = Paths.get(OUTPUT_DIR, nd.className + ".java");
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            w.write("package " + pkg + ";"); w.newLine(); w.newLine();
            w.write("import java.util.*;"); w.newLine();
            w.write("public class " + nd.className + " extends Expr {"); w.newLine();
            // Fields
            for (String field : nd.fields) {
                w.write("    public final " + field + ";"); w.newLine();
            }
            w.newLine();
            // Constructor
            w.write("    public " + nd.className + "(");
            for (int i = 0; i < nd.fields.size(); i++) {
                String f = nd.fields.get(i);
                w.write(f);
                if (i < nd.fields.size() - 1) w.write(", ");
            }
            w.write(") {"); w.newLine();
            for (String field : nd.fields) {
                String name = field.split(" ")[1];
                w.write("        this." + name + " = " + name + ";"); w.newLine();
            }
            w.write("    }"); w.newLine(); w.newLine();
            // accept method
            w.write("    @Override"); w.newLine();
            w.write("    public <R> R accept(Visitor<R> visitor) {"); w.newLine();
            w.write("        return visitor.visit" + nd.className + "(this);"); w.newLine();
            w.write("    }"); w.newLine();
            w.write("}"); w.newLine();
        }
    }

    private static void generateStmtSubclass(NodeDef nd) throws IOException {
        String pkg = "danex.ast";
        Path file = Paths.get(OUTPUT_DIR, nd.className + ".java");
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            w.write("package " + pkg + ";"); w.newLine(); w.newLine();
            w.write("import java.util.*;"); w.newLine();
            w.write("public class " + nd.className + " extends Stmt {"); w.newLine();
            // Fields
            for (String field : nd.fields) {
                w.write("    public final " + field + ";"); w.newLine();
            }
            w.newLine();
            // Constructor
            w.write("    public " + nd.className + "(");
            for (int i = 0; i < nd.fields.size(); i++) {
                String f = nd.fields.get(i);
                w.write(f);
                if (i < nd.fields.size() - 1) w.write(", ");
            }
            w.write(") {"); w.newLine();
            for (String field : nd.fields) {
                String name = field.split(" ")[1];
                w.write("        this." + name + " = " + name + ";"); w.newLine();
            }
            w.write("    }"); w.newLine(); w.newLine();
            // accept method
            w.write("    @Override"); w.newLine();
            w.write("    public <R> R accept(Visitor<R> visitor) {"); w.newLine();
            w.write("        return visitor.visit" + nd.className + "(this);"); w.newLine();
            w.write("    }"); w.newLine();
            w.write("}"); w.newLine();
        }
    }

    private static String decap(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }
}
