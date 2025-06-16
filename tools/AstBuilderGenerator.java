package tools;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

/**
 * Generates AstBuilder.java by scanning AST classes in src/danex/ast/.
 * Assumes each AST class has a single constructor initializing final fields.
 * AST class names end with Expr or Stmt.
 */
public class AstBuilderGenerator {
    private static final Path AST_DIR = Paths.get("src/danex/ast");
    private static final Path OUTPUT_FILE = Paths.get("src/danex/AstBuilder.java");

    public static void main(String[] args) {
        try {
            List<AstClass> astClasses = new ArrayList<>();

            if (!Files.exists(AST_DIR) || !Files.isDirectory(AST_DIR)) {
                System.err.println("AST directory not found: " + AST_DIR.toAbsolutePath());
                return;
            }

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(AST_DIR, "*.java")) {
                for (Path path : stream) {
                    AstClass cls = parseAstClass(path);
                    if (cls != null) astClasses.add(cls);
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("package danex;\n\n");
            sb.append("import danex.ast.*;\n");
            sb.append("import java.util.*;\n\n");
            sb.append("public class AstBuilder implements DanexParserVisitor<Object> {\n\n");

            for (AstClass cls : astClasses) {
                sb.append(generateVisitorMethod(cls)).append("\n");
            }

            sb.append("}\n");

            Files.createDirectories(OUTPUT_FILE.getParent());
            Files.write(OUTPUT_FILE, sb.toString().getBytes());
            System.out.println("✅ AstBuilder.java generated at " + OUTPUT_FILE.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error generating AstBuilder:");
            e.printStackTrace();
        }
    }

    private static AstClass parseAstClass(Path path) throws IOException {
        String content = new String(Files.readAllBytes(path));
        String fileName = path.getFileName().toString();
        String className = fileName.substring(0, fileName.length() - 5);

        if (!className.endsWith("Expr") && !className.endsWith("Stmt")) {
            return null; // skip non-AST classes
        }
        boolean isExpr = className.endsWith("Expr");
        String ruleName = className.substring(0, className.length() - (isExpr ? 4 : 4));

        // Find constructor signature: public ClassName(Type param, ...)
        Pattern ctorPattern = Pattern.compile("public\\s+" + className + "\\s*\\(([^)]*)\\)");
        Matcher matcher = ctorPattern.matcher(content);
        if (!matcher.find()) {
            System.err.println("Warning: No constructor found in " + className);
            return new AstClass(className, ruleName, isExpr, Collections.emptyList());
        }
        String params = matcher.group(1).trim();
        List<Field> fields = new ArrayList<>();
        if (!params.isEmpty()) {
            String[] parts = params.split(",");
            for (String part : parts) {
                part = part.trim();
                String[] toks = part.split("\\s+");
                if (toks.length >= 2) {
                    String type = toks[0];
                    String name = toks[1];
                    fields.add(new Field(type, name));
                } else {
                    System.err.println("Warning: cannot parse constructor param '" + part + "' in " + className);
                }
            }
        }
        return new AstClass(className, ruleName, isExpr, fields);
    }

    private static String generateVisitorMethod(AstClass cls) {
        StringBuilder sb = new StringBuilder();
        String methodName = capitalize(cls.ruleName) + (cls.isExpr ? "Expr" : "Stmt");
        sb.append("    @Override\n");
        sb.append("    public Object visit").append(methodName).append("(DanexParser.")
          .append(methodName).append("Context ctx) {\n");

        List<String> argNames = new ArrayList<>();
        for (Field f : cls.fields) {
            sb.append("        ").append(generateFieldLine(f)).append("\n");
            argNames.add(f.name);
        }
        sb.append("        return new ").append(cls.className).append("(")
          .append(String.join(", ", argNames)).append(");\n");
        sb.append("    }\n");
        return sb.toString();
    }

    private static String generateFieldLine(Field field) {
        String n = field.name;
        String t = field.type;
        switch (t) {
            case "Expr":
                return "Expr " + n + " = (Expr) ctx." + n + "().accept(this);";
            case "Stmt":
                return "Stmt " + n + " = (Stmt) ctx." + n + "().accept(this);";
            case "List<Expr>":
                return "List<Expr> " + n + " = new ArrayList<>();\n"
                     + "        for (var e : ctx." + n + "()) " + n + ".add((Expr) e.accept(this));";
            case "List<Stmt>":
                return "List<Stmt> " + n + " = new ArrayList<>();\n"
                     + "        for (var s : ctx." + n + "()) " + n + ".add((Stmt) s.accept(this));";
            case "List<String>":
                return "List<String> " + n + " = new ArrayList<>();\n"
                     + "        for (var t : ctx." + n + "()) " + n + ".add(t.getText());";
            case "String":
                return "String " + n + " = ctx." + n + "().getText();";
            case "Object":
                return "Object " + n + " = ctx." + n + "().getText(); // TODO: parse Object properly";
            default:
                return "// TODO: handle field type " + t + " " + n;
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private static class Field {
        final String type;
        final String name;
        Field(String type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    private static class AstClass {
        final String className;
        final String ruleName;
        final boolean isExpr;
        final List<Field> fields;
        AstClass(String className, String ruleName, boolean isExpr, List<Field> fields) {
            this.className = className;
            this.ruleName = ruleName;
            this.isExpr = isExpr;
            this.fields = fields;
        }
    }
}
