package tools;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Generates AstBuilder.java with visitor methods for AST nodes,
 * but skips those we handle manually (Annotation, Param, ResourceDecl, ExprStmt),
 * and excludes removed AST nodes (e.g. ReturnStmt).
 */
public class AstBuilderGenerator {
    private static final Path AST_DIR = Paths.get("src/danex/ast");
    private static final Path OUTPUT_FILE = Paths.get("src/danex/AstBuilder.java");

    /** Node class names for which we supply manual overrides or are deprecated/removed. */
    private static final Set<String> MANUAL_OVERRIDES = Set.of(
        "Annotation",
        "Param",
        "ResourceDecl",
        "ExprStmt"
    );

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
            sb.append("public class AstBuilder implements Expr.Visitor<Expr>, Stmt.Visitor<Stmt>, Decl.Visitor<Decl> {\n\n");

            for (AstClass cls : astClasses) {
                String name = cls.className;
                if (name.equals("Expr") || name.equals("Stmt") || name.equals("Decl")) continue;
                if (MANUAL_OVERRIDES.contains(name)) continue;
                sb.append(generateVisitorMethod(cls)).append("\n");
            }

            sb.append("    // === Manual overrides for certain AST node types ===\n\n");

            sb.append("    @Override\n");
            sb.append("    public Annotation visitAnnotation(Annotation annotation) {\n");
            sb.append("        return new Annotation(annotation.name);\n");
            sb.append("    }\n\n");

            sb.append("    @Override\n");
            sb.append("    public Param visitParam(Param param) {\n");
            sb.append("        return new Param(param.type, param.name, param.varargs);\n");
            sb.append("    }\n\n");

            sb.append("    @Override\n");
            sb.append("    public ResourceDecl visitResourceDecl(ResourceDecl rd) {\n");
            sb.append("        Expr init = rd.initializer.accept(this);\n");
            sb.append("        return new ResourceDecl(rd.type, rd.name, init);\n");
            sb.append("    }\n\n");

            sb.append("    @Override\n");
            sb.append("    public ExprStmt visitExprStmt(ExprStmt stmt) {\n");
            sb.append("        Expr e = stmt.expression.accept(this);\n");
            sb.append("        return new ExprStmt(e);\n");
            sb.append("    }\n\n");

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
        if (!fileName.endsWith(".java")) return null;
        String className = fileName.substring(0, fileName.length() - 5);

        if (MANUAL_OVERRIDES.contains(className)) return null;

        boolean isExpr = className.endsWith("Expr");
        boolean isStmt = className.endsWith("Stmt");
        boolean isDecl = className.endsWith("Decl");
        if (!isExpr && !isStmt && !isDecl) return null;

        List<Field> fields = new ArrayList<>();
        for (String rawLine : lines) {
            String line = rawLine.trim();
            if (line.startsWith("public final ") && line.endsWith(";")) {
                String decl = line.substring("public final ".length(), line.length() - 1).trim();
                int lastSpace = decl.lastIndexOf(' ');
                if (lastSpace != -1) {
                    String type = decl.substring(0, lastSpace).trim();
                    String name = decl.substring(lastSpace + 1).trim();
                    if (!type.isEmpty() && !name.isEmpty()) {
                        fields.add(new Field(type, name));
                    }
                }
            }
        }
        return new AstClass(className, isExpr, isStmt, isDecl, fields);
    }

    private static String generateVisitorMethod(AstClass cls) {
        StringBuilder sb = new StringBuilder();
        String className = cls.className;
        String paramName = Character.toLowerCase(className.charAt(0)) + className.substring(1);
        String returnType = cls.isExpr ? "Expr" : (cls.isStmt ? "Stmt" : "Decl");

        sb.append("    @Override\n");
        sb.append("    public ").append(returnType).append(" visit").append(className)
          .append("(").append(className).append(" ").append(paramName).append(") {\n");

        List<String> argNames = new ArrayList<>();
        for (Field f : cls.fields) {
            boolean isAstNode = f.type.equals("Expr") || f.type.equals("Stmt") || f.type.equals("Decl");
            String rhs = isAstNode
                ? paramName + "." + f.name + ".accept(this)"
                : paramName + "." + f.name;
            sb.append("        ").append(f.type).append(" ").append(f.name).append(" = ").append(rhs).append(";\n");
            argNames.add(f.name);
        }

        sb.append("        return new ").append(className).append("(").append(String.join(", ", argNames)).append(");\n");
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
