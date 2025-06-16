package tools;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Generates AstBuilder.java by scanning AST classes in src/danex/ast/.
 * Assumes each AST class is a top-level class in its own file, named XxxExpr or XxxStmt,
 * and contains `public final Type name;` fields corresponding to constructor parameters.
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

            // If no AST classes found, warn
            if (astClasses.isEmpty()) {
                System.err.println("⚠️ No AST classes found in " + AST_DIR.toAbsolutePath());
            }

            // Build AstBuilder.java content
            StringBuilder sb = new StringBuilder();
            sb.append("package danex;\n\n");
            sb.append("import danex.ast.*;\n");
            sb.append("import java.util.*;\n\n");
            sb.append("public class AstBuilder implements Expr.Visitor<Expr>, Stmt.Visitor<Stmt> {\n\n");

            for (AstClass cls : astClasses) {
    if (cls.className.equals("Expr") || cls.className.equals("Stmt")) continue;
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
     * Parse an AST class file to extract className, whether Expr or Stmt, and its public final fields.
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

        List<Field> fields = new ArrayList<>();
        // Scan line by line for `public final TYPE name;`
        String[] lines = content.split("\\r?\\n");
        for (String rawLine : lines) {
            String line = rawLine.trim();
            // Simple check: startsWith "public final"
            if (line.startsWith("public final ")) {
                // remove trailing ';' if present
                if (line.endsWith(";")) {
                    String decl = line.substring("public final ".length(), line.length() - 1).trim();
                    // decl is like "Expr left" or "List<Expr> elements"
                    // Split by whitespace: last token is name, rest is type
                    String[] toks = decl.split("\\s+");
                    if (toks.length >= 2) {
                        String name = toks[toks.length - 1];
                        // Reconstruct type from preceding tokens
                        String type = toks[0];
                        if (toks.length > 2) {
                            StringBuilder tsb = new StringBuilder(toks[0]);
                            for (int i = 1; i < toks.length - 1; i++) {
                                tsb.append(" ").append(toks[i]);
                            }
                            type = tsb.toString();
                        }
                        fields.add(new Field(type, name));
                    } else {
                        System.err.println("Warning: cannot parse field declaration '" + line +
                                           "' in " + className);
                    }
                }
            }
        }

        // If no public final fields found, warn
        if (fields.isEmpty()) {
            System.err.println("Warning: No public final fields found in " + className +
                               ". Check if fields are declared differently or if constructor-only fields exist.");
        }

        return new AstClass(className, isExpr, isStmt, fields);
    }

    /**
     * Generate the visitor method for one AST class.
     * E.g., for class BinaryExpr, generates:
     *
     * @Override
     * public Object visitBinaryExpr(Expr.BinaryExpr binaryExpr) { ... }
     */
    private static String generateVisitorMethod(AstClass cls) {
        StringBuilder sb = new StringBuilder();
        String className = cls.className;           // e.g. "BinaryExpr"
        String baseType = cls.isExpr ? "Expr" : "Stmt";
        // parameter name: lowerCamel of className, e.g. "binaryExpr"
        String paramName = Character.toLowerCase(className.charAt(0)) + className.substring(1);

        sb.append("    @Override\n");
        sb.append("    public Object visit").append(className)
  .append("(").append(className).append(" ").append(paramName).append(") {\n");
        List<String> argNames = new ArrayList<>();
        for (Field f : cls.fields) {
            sb.append("        ").append(generateFieldLineFromNode(f, paramName)).append("\n");
            argNames.add(f.name);
        }
        // Reconstruct same node by calling its constructor with the fields in order.
        // Note: This assumes the class has a constructor matching (TYPE... field order as declared).
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
        // You may adjust if some types need special handling; by default we just read the field.
        return t + " " + n + " = " + nodeVar + "." + n + ";";
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
