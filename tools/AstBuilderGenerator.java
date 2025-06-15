package tools;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AstBuilderGenerator {
    private static final String OUTPUT_PATH = "src/danex/AstBuilder.java";

    public static void main(String[] args) throws IOException {
        List<String> exprTypes = Arrays.asList(
            "Assign     : String name, Expr value",
            "Binary     : Expr left, String operator, Expr right",
            "Call       : Expr callee, List<Expr> arguments",
            "Comparator : Expr left, Expr right",
            "Do         : List<Stmt> body",
            "DoExpr     : List<Stmt> body",
            "Grouping   : Expr expression",
            "Lambda     : List<String> params, List<Stmt> body",
            "Literal    : Object value",
            "NullCoalesce : Expr left, Expr right",
            "Unary      : String operator, Expr right",
            "Variable   : String name",
            "Await      : Expr expression",
            "TryExpr    : Expr tryExpr, String exceptionName, Expr catchExpr"
        );

        List<String> stmtTypes = Arrays.asList(
            "Block      : List<Stmt> statements",
            "Expr       : Expr expression",
            "If         : Expr condition, Stmt thenBranch, Stmt elseBranch",
            "While      : Expr condition, Stmt body",
            "DoWhile    : Stmt body, Expr condition",
            "For        : Stmt init, Expr condition, Expr update, Stmt body",
            "Return     : Expr value",
            "Throw      : Expr exception",
            "Exit       : ",
            "Try        : List<Stmt> tryBlock, String exceptionName, List<Stmt> catchBlock, List<Stmt> finallyBlock",
            "Class      : String name, List<Stmt> methods",
            "Method     : String name, List<String> params, List<Stmt> body",
            "Import     : String path"
        );

        StringBuilder sb = new StringBuilder();
        sb.append("package danex;\n\n");
        sb.append("import danex.ast.*;\n");
        sb.append("import java.util.*;\n\n");
        sb.append("public class AstBuilder implements DanexParserVisitor<Object> {\n\n");

        // Expression builders
        for (String type : exprTypes) {
            generateVisitorMethod(sb, type, true);
        }

        // Statement builders
        for (String type : stmtTypes) {
            generateVisitorMethod(sb, type, false);
        }

        sb.append("}\n");

        // Write to file
        Files.createDirectories(Paths.get("src/danex"));
        Files.write(Paths.get(OUTPUT_PATH), sb.toString().getBytes());
        System.out.println("✅ AstBuilder.java generated!");
    }

    private static void generateVisitorMethod(StringBuilder sb, String typeDef, boolean isExpr) {
        String[] parts = typeDef.split(":");
        String className = parts[0].trim();
        String fieldList = parts.length > 1 ? parts[1].trim() : "";

        String ruleName = className.toLowerCase() + (isExpr ? "Expr" : "Stmt");
        String returnType = isExpr ? "Expr" : "Stmt";
        String concreteClass = className + (isExpr ? "Expr" : "Stmt");

        sb.append("    @Override\n");
        sb.append("    public Object visit").append(capitalize(ruleName)).append("(DanexParser.")
          .append(capitalize(ruleName)).append("Context ctx) {\n");

        if (fieldList.isEmpty()) {
            sb.append("        return new ").append(concreteClass).append("();\n");
        } else {
            List<String> fields = new ArrayList<>();
            for (String field : fieldList.split(",")) {
                String[] pair = field.trim().split(" ");
                String fieldType = pair[0];
                String fieldName = pair[1];

                String parseCode = generateFieldExpr(fieldType, fieldName);
                sb.append("        ").append(parseCode).append("\n");
                fields.add(fieldName);
            }
            sb.append("        return new ").append(concreteClass).append("(")
              .append(String.join(", ", fields)).append(");\n");
        }

        sb.append("    }\n\n");
    }

    private static String generateFieldExpr(String type, String name) {
        if (type.equals("Expr")) {
            return "Expr " + name + " = (Expr) ctx." + name + "().accept(this);";
        } else if (type.equals("Stmt")) {
            return "Stmt " + name + " = (Stmt) ctx." + name + "().accept(this);";
        } else if (type.equals("List<Expr>")) {
            return "List<Expr> " + name + " = new ArrayList<>();\n"
                 + "        for (var e : ctx." + name + "()) " + name + ".add((Expr) e.accept(this));";
        } else if (type.equals("List<Stmt>")) {
            return "List<Stmt> " + name + " = new ArrayList<>();\n"
                 + "        for (var s : ctx." + name + "()) " + name + ".add((Stmt) s.accept(this));";
        } else if (type.equals("List<String>")) {
            return "List<String> " + name + " = new ArrayList<>();\n"
                 + "        for (var t : ctx." + name + "()) " + name + ".add(t.getText());";
        } else if (type.equals("String")) {
            return "String " + name + " = ctx." + name + "().getText();";
        } else if (type.equals("Object")) {
            return "Object " + name + " = ctx." + name + "().getText(); // TODO: parse Object properly";
        }
        return "// Unknown type for field: " + type + " " + name;
    }

    private static String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
              }
