package tools;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

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

            if (astClasses.isEmpty()) {
                System.err.println("⚠️ No AST classes found in " + AST_DIR.toAbsolutePath());
            }

            StringBuilder sb = new StringBuilder();
            sb.append("package danex;\n\n");
            sb.append("import danex.ast.*;\n");
            sb.append("import java.util.*;\n\n");
            // Now implement Expr.Visitor<Expr>, Stmt.Visitor<Stmt>, Decl.Visitor<Decl>
            sb.append("public class AstBuilder implements Expr.Visitor<Expr>, Stmt.Visitor<Stmt>, Decl.Visitor<Decl> {\n\n");

            for (AstClass cls : astClasses) {
                // Skip base classes
                if (cls.className.equals("Expr") || cls.className.equals("Stmt") || cls.className.equals("Decl")) {
                    continue;
                }
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
        List<String> lines = Files.readAllLines(path);
        String fileName = path.getFileName().toString();
        if (!fileName.endsWith(".java")) {
            return null;
        }
        String className = fileName.substring(0, fileName.length() - 5);

        boolean isExpr = className.endsWith("Expr");
        boolean isStmt = className.endsWith("Stmt");
        boolean isDecl = className.endsWith("Decl");
        if (!isExpr && !isStmt && !isDecl) {
            return null;
        }

        List<Field> fields = new ArrayList<>();
        for (String rawLine : lines) {
            String line = rawLine.trim();
            if (line.startsWith("public final ") && line.endsWith(";")) {
                String declaration = line.substring("public final ".length(), line.length() - 1).trim();
                int lastSpace = declaration.lastIndexOf(' ');
                if (lastSpace != -1) {
                    String type = declaration.substring(0, lastSpace).trim();
                    String name = declaration.substring(lastSpace + 1).trim();
                    if (!type.isEmpty() && !name.isEmpty()) {
                        fields.add(new Field(type, name));
                    }
                }
            }
        }

        if (fields.isEmpty()) {
            System.err.println("Warning: No public final fields found in " + className);
        }

        return new AstClass(className, isExpr, isStmt, isDecl, fields);
    }

    private static String generateVisitorMethod(AstClass cls) {
        StringBuilder sb = new StringBuilder();
        String className = cls.className;
        String paramName = Character.toLowerCase(className.charAt(0)) + className.substring(1);

        String returnType;
        if (cls.isExpr) {
            returnType = "Expr";
        } else if (cls.isStmt) {
            returnType = "Stmt";
        } else { // Decl
            returnType = "Decl";
        }

        sb.append("    @Override\n");
        sb.append("    public ").append(returnType)
          .append(" visit").append(className)
          .append("(").append(className).append(" ").append(paramName).append(") {\n");

        // Extract fields
        List<String> argNames = new ArrayList<>();
        for (Field f : cls.fields) {
            sb.append("        ").append(f.type).append(" ").append(f.name)
              .append(" = ").append(paramName).append(".").append(f.name).append(";\n");
            argNames.add(f.name);
        }

        // Return a new node with the same fields
        sb.append("        return new ").append(className)
          .append("(").append(String.join(", ", argNames)).append(");\n");
        sb.append("    }\n");
        return sb.toString();
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
        final boolean isDecl;
        final List<Field> fields;
        AstClass(String className, boolean isExpr, boolean isStmt, boolean isDecl, List<Field> fields) {
            this.className = className;
            this.isExpr = isExpr;
            this.isStmt = isStmt;
            this.isDecl = isDecl;
            this.fields = fields;
        }
    }
}
