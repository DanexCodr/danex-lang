package tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// Generates AST node classes in src/danex/ast
public class AstGenerator {
    private static final String OUTPUT_DIR = "src/danex/ast";

    public static void main(String[] args) throws IOException {
        Path outDir = Paths.get(OUTPUT_DIR);
        if (!Files.exists(outDir)) {
            Files.createDirectories(outDir);
        }

        generateASTNode();
        generateExprBase();
        generateStmtBase();
        generateDeclBase();

        List<NodeDef> exprNodes = Arrays.asList(
            new NodeDef("BinaryExpr", Arrays.asList("Expr left", "String operator", "Expr right")),
            new NodeDef("UnaryExpr", Arrays.asList("String operator", "Expr right")),
            new NodeDef("LiteralExpr", Arrays.asList("Object value")),
            new NodeDef("GroupingExpr", Arrays.asList("Expr expression")),
            new NodeDef("VariableExpr", Arrays.asList("String name")),
            new NodeDef("AssignExpr", Arrays.asList("String name", "Expr value")),
            new NodeDef("CallExpr", Arrays.asList("Expr callee", "List<Expr> arguments")),
            new NodeDef("LambdaExpr", Arrays.asList("List<Param> params", "Expr body")),
            new NodeDef("DoExpr", Arrays.asList("List<Stmt> body")),
            new NodeDef("TryExpr", Arrays.asList(
                "List<Stmt> tryBlock", "String catchType", "String catchName",
                "List<Stmt> catchBlock", "List<Stmt> finallyBlock"
            )),
            new NodeDef("AwaitExpr", Arrays.asList("Expr expression")),
            new NodeDef("NullCoalesceExpr", Arrays.asList("Expr left", "Expr right")),
            new NodeDef("ComparatorExpr", Arrays.asList("Expr left", "Expr right"))
        );
        for (NodeDef nd : exprNodes) generateExprSubclass(nd);

        List<NodeDef> stmtNodes = Arrays.asList(
            new NodeDef("ExprStmt", Arrays.asList("Expr expression")),
            new NodeDef("BlockStmt", Arrays.asList("List<Stmt> statements")),
            new NodeDef("IfStmt", Arrays.asList("Expr condition", "Stmt thenBranch", "Stmt elseBranch")),
            new NodeDef("WhileStmt", Arrays.asList("Expr condition", "Stmt body")),
            new NodeDef("DoWhileStmt", Arrays.asList("Stmt body", "Expr condition")),
            new NodeDef("ForStmt", Arrays.asList("Stmt init", "Expr condition", "Expr update", "Stmt body")),
            new NodeDef("AssignStmt", Arrays.asList("Expr target", "Expr value")),
            new NodeDef("ThrowStmt", Arrays.asList("Expr exception")),
            new NodeDef("ExitStmt", Collections.emptyList()),
            new NodeDef("TryStmt", Arrays.asList(
                "List<ResourceDecl> resources",
                "List<Stmt> tryBlock",
                "String catchType",
                "String catchName",
                "List<Stmt> catchBlock",
                "List<Stmt> finallyBlock"
            ))
        );
        for (NodeDef nd : stmtNodes) generateStmtSubclass(nd);

        List<NodeDef> declNodes = Arrays.asList(
            new NodeDef("MethodDecl", Arrays.asList(
                "String name", "String resultType", "String resultName",
                "List<Annotation> annotations", "List<String> modifiers",
                "List<Param> params", "Stmt body"
            )),
            new NodeDef("ClassDecl", Arrays.asList(
                "String name", "List<Annotation> annotations",
                "List<String> modifiers", "List<Decl> members"
            )),
            new NodeDef("ImportDecl", Arrays.asList("String moduleName", "String alias")),
            new NodeDef("Annotation", Arrays.asList("String name")),
            new NodeDef("Param", Arrays.asList("String type", "String name", "boolean varargs")),
            new NodeDef("ResourceDecl", Arrays.asList("String type", "String name", "Expr initializer"))
        );
        for (NodeDef nd : declNodes) generateDeclSubclass(nd);

        System.out.println("AST classes generated in " + OUTPUT_DIR);
    }

    static class NodeDef {
        String className;
        List<String> fields;
        NodeDef(String className, List<String> fields) {
            this.className = className;
            this.fields = fields;
        }
    }

    private static void generateASTNode() throws IOException {
        String pkg = "danex.ast";
        Path file = Paths.get(OUTPUT_DIR, "ASTNode.java");
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            w.write("package " + pkg + ";\n\n");
            w.write("public abstract class ASTNode {\n");
            w.write("    // Base for all AST nodes. You may add common info like source position.\n");
            w.write("}\n");
        }
    }

    private static void generateExprBase() throws IOException {
        String pkg = "danex.ast";
        Path file = Paths.get(OUTPUT_DIR, "Expr.java");
        String[] concreteExprs = {
            "BinaryExpr", "UnaryExpr", "LiteralExpr", "GroupingExpr", "VariableExpr", "AssignExpr",
            "CallExpr", "LambdaExpr", "DoExpr", "TryExpr", "AwaitExpr", "NullCoalesceExpr", "ComparatorExpr"
        };
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            w.write("package " + pkg + ";\n\n");
            w.write("public abstract class Expr extends ASTNode {\n");
            w.write("    public interface Visitor<R> {\n");
            for (String name : concreteExprs) {
                w.write("        R visit" + name + "(" + name + " " + decap(name) + ");\n");
            }
            w.write("    }\n\n");
            w.write("    public abstract <R> R accept(Visitor<R> visitor);\n");
            w.write("}\n");
        }
    }

    private static void generateStmtBase() throws IOException {
        String pkg = "danex.ast";
        Path file = Paths.get(OUTPUT_DIR, "Stmt.java");
        String[] concreteStmts = {
            "ExprStmt", "BlockStmt", "IfStmt", "WhileStmt", "DoWhileStmt", "ForStmt",
            "AssignStmt", "ThrowStmt", "ExitStmt", "TryStmt"
        };
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            w.write("package " + pkg + ";\n\n");
            w.write("public abstract class Stmt extends ASTNode {\n");
            w.write("    public interface Visitor<R> {\n");
            for (String name : concreteStmts) {
                w.write("        R visit" + name + "(" + name + " " + decap(name) + ");\n");
            }
            w.write("    }\n\n");
            w.write("    public abstract <R> R accept(Visitor<R> visitor);\n");
            w.write("}\n");
        }
    }

    private static void generateDeclBase() throws IOException {
        String pkg = "danex.ast";
        Path file = Paths.get(OUTPUT_DIR, "Decl.java");
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            w.write("package " + pkg + ";\n\n");
            w.write("public abstract class Decl extends ASTNode {\n");
            w.write("    public interface Visitor<R> {\n");
            w.write("        R visitMethodDecl(MethodDecl methodDecl);\n");
            w.write("        R visitClassDecl(ClassDecl classDecl);\n");
            w.write("        R visitImportDecl(ImportDecl importDecl);\n");
            w.write("        R visitAnnotation(Annotation annotation);\n");
            w.write("        R visitParam(Param param);\n");
            w.write("        R visitResourceDecl(ResourceDecl resourceDecl);\n");
            w.write("    }\n\n");
            w.write("    public abstract <R> R accept(Visitor<R> visitor);\n");
            w.write("}\n");
        }
    }

    private static void generateExprSubclass(NodeDef nd) throws IOException {
        writeNodeFile(nd, "Expr");
    }

    private static void generateStmtSubclass(NodeDef nd) throws IOException {
        writeNodeFile(nd, "Stmt");
    }

    private static void generateDeclSubclass(NodeDef nd) throws IOException {
        writeNodeFile(nd, "Decl");
    }

    private static void writeNodeFile(NodeDef nd, String baseClass) throws IOException {
        String pkg = "danex.ast";
        Path file = Paths.get(OUTPUT_DIR, nd.className + ".java");
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            w.write("package " + pkg + ";\n\n");
            w.write("import java.util.*;\n");
            w.write("public class " + nd.className + " extends " + baseClass + " {\n");
            for (String field : nd.fields) {
                w.write("    public final " + field + ";\n");
            }
            w.write("\n    public " + nd.className + "(");
            for (int i = 0; i < nd.fields.size(); i++) {
                w.write(nd.fields.get(i));
                if (i < nd.fields.size() - 1) w.write(", ");
            }
            w.write(") {\n");
            for (String field : nd.fields) {
                String name = field.split(" ")[1];
                w.write("        this." + name + " = " + name + ";\n");
            }
            w.write("    }\n\n");
            w.write("    @Override\n");
            w.write("    public <R> R accept(" + baseClass + ".Visitor<R> visitor) {\n");
            w.write("        return visitor.visit" + nd.className + "(this);\n");
            w.write("    }\n");
            w.write("}\n");
        }
    }

    private static String decap(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }
}
