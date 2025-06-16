package tools;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

/**
 * Generates AstBuilder.java by scanning AST classes in src/danex/ast/.
 * Assumes each AST class has a single constructor initializing final fields.
 * AST class names end with Expr or Stmt.
 * Generates methods implementing Expr.Visitor<Object> and Stmt.Visitor<Object>.
 */
public class AstBuilderGenerator {
    private static final Path AST_DIR = Paths.get("src/danex/ast");
    private static final Path OUTPUT_FILE = Paths.get("src/danex/AstBuilder.java");

    public static void main(String[] args) {
        try {
            if (!Files.exists(AST_DIR) || !Files.isDirectory(AST_DIR)) {
                System.err.println("AST directory not found: " + AST_DIR.toAbsolutePath());
                return;
            }

            List<AstClass> astClasses = new ArrayList<>();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(AST_DIR, "*.java")) {
                for (Path path : stream) {
                    AstClass cls = parseAstClass(path);
                    if (cls != null) {
                        astClasses.add(cls);
                    }
                }
            }

            // Build AstBuilder.java content
            StringBuilder sb = new StringBuilder();
            sb.append("package danex;\n\n");
            sb.append("import danex.ast.*;\n");
            sb.append("import java.util.*;\n\n");
            sb.append("public class AstBuilder implements Expr.Visitor<Object>, Stmt.Visitor<Object> {\n\n");

            for (AstClass cls : astClasses) {
                sb.append(generateVisitorMethod(cls)).append("\n");
            }

            sb.append("}\n");

            // Write to file
            Files.createDirectories(OUTPUT_FILE.getParent());
            Files.write(OUTPUT_FILE, sb.toString().getBytes());
            System.out.println("✅ AstBuilder.java generated at " + OUTPUT_FILE.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error generating AstBuilder:");
            e.printStackTrace();
        }
    }

    /**
     * Parse an AST class file to extract className, whether Expr or Stmt, and its constructor fields.
     * Returns null if the class is not an AST node (not ending in Expr or Stmt).
     */
    private static AstClass parseAstClass(Path path) throws IOException {
        String content = Files.readString(path);
        String fileName = path.getFileName().toString();
        if (!fileName.endsWith(".java")) {
            return null;
        }
        String className = fileName.substring(0, fileName.length() - 5);

        boolean isExpr = className.endsWith("Expr");
        boolean isStmt = className.endsWith("Stmt");
        if (!isExpr && !isStmt) {
            // skip non-Expr/Stmt classes
            return null;
        }

        // Find constructor signature: public ClassName(Type param, ...)
        Pattern ctorPattern = Pattern.compile("public\\s+" + className + "\\s*\([^)]*)\");
        Matcher matcher = ctorPattern.matcher(content);

        List<Field> fields = new ArrayList<>();
        if (matcher.find()) {
            String params = matcher.group(1).trim();
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
                        System.err.println("Warning: cannot parse constructor param '" + part +
                                "' in " + className);
                    }
                }
            }
        } else {
            System.err.println("Warning: No constructor found in " + className);
        }
        return new AstClass(className, isExpr, isStmt, fields);
    }

    /**
     * Generate the visitor method for one AST class.
     * E.g., for class BinaryExpr, generates:
     *   @Override
     *   public Object visitBinaryExpr(Expr.BinaryExpr binaryExpr) { ... }
     */
    private static String generateVisitorMethod(AstClass cls) {
        StringBuilder sb = new StringBuilder();
        String className = cls.className;           // e.g. "BinaryExpr"
        String baseType = cls.isExpr ? "Expr" : "Stmt";
        // parameter name: lowerCamel of className, e.g. "binaryExpr"
        String paramName = Character.toLowerCase(className.charAt(0)) + className.substring(1);

        sb.append("    @Override\n");
        sb.append("    public Object visit").append(className)
          .append("(").append(baseType).append(" ").append(paramName).append(") {\n");

        List<String> argNames = new ArrayList<>();
        for (Field f : cls.fields) {
            sb.append("        ").append(generateFieldLineFromNode(f, paramName)).append("\n");
            argNames.add(f.name);
        }
        // Construct new node or handle logic; here we just reconstruct same class
        sb.append("        return new ").append(className)
          .append("(").append(String.join(", ", argNames)).append(");\n");
        sb.append("    }\n");
        return sb.toString();
    }

    /**
     * Generate a line extracting a field from the AST node parameter.
     * Assumes public final fields with same names as constructor parameters.
     */
    private static String generateFieldLineFromNode(Field field, String nodeVar) {
        String n = field.name;
        String t = field.type;
        switch (t) {
            case "Expr":
                return "Expr " + n + " = " + nodeVar + "." + n + ";";
            case "Stmt":
                return "Stmt " + n + " = " + nodeVar + "." + n + ";";
            case "List<Expr>":
                return "List<Expr> " + n + " = " + nodeVar + "." + n + ";";
            case "List<Stmt>":
                return "List<Stmt> " + n + " = " + nodeVar + "." + n + ";";
            case "List<String>":
                return "List<String> " + n + " = " + nodeVar + "." + n + ";";
            case "String":
                return "String " + n + " = " + nodeVar + "." + n + ";";
            case "Object":
                return "Object " + n + " = " + nodeVar + "." + n + "; // TODO: adjust if needed";
            default:
                return "// TODO: handle field type " + t + " for " + n;
        }
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
        final boolean isExpr;
        final boolean isStmt;
        final List<Field> fields;
        AstClass(String className, boolean isExpr, boolean isStmt, List<Field> fields) {
            this.className = className;
            this.isExpr = isExpr;
            this.isStmt = isStmt;
            this.fields = fields;
        }
    }
                    }
