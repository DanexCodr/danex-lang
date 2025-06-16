package tools;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AstBuilderGenerator {
    private static final String OUTPUT_PATH = "src/danex/AstBuilder.java";

    public static void main(String[] args) throws IOException {
        System.out.println("Working dir: " + System.getProperty("user.dir"));
        System.out.println("Starting AstBuilder generation...");
        StringBuilder sb = new StringBuilder();
        
        // Header and imports
        sb.append("""
            package danex;
            
            import danex.antlr.DanexParser;
            import danex.antlr.DanexParserBaseVisitor;
            import danex.ast.*;
            import org.antlr.v4.runtime.tree.TerminalNode;
            import java.util.*;
            
            public class AstBuilder extends DanexParserBaseVisitor<Object> {
            
            """);

        // Helper method for list collection
        String listCollector = """
                private <T> List<T> collectList(java.util.List<? extends org.antlr.v4.runtime.ParserRuleContext> contexts) {
                    List<T> list = new ArrayList<>();
                    for (var ctx : contexts) {
                        list.add((T) visit(ctx));
                    }
                    return list;
                }
            """;
        sb.append(listCollector).append("\n");

        // Visit methods
        sb.append("""
            @Override
            public Object visitCompilationUnit(DanexParser.CompilationUnitContext ctx) {
                return collectList<Stmt>(ctx.statement());
            }
            
            @Override
            public Object visitImportStmt(DanexParser.ImportStmtContext ctx) {
                String module = ctx.IDENTIFIER(0).getText();
                String alias = ctx.IDENTIFIER().size() > 1 
                    ? ctx.IDENTIFIER(1).getText() 
                    : null;
                return new ImportStmt(module, alias);
            }
            """);

        // Class declaration
        sb.append("""
            private static class ClassBodyResult {
                List<Stmt> members;
                ClassBodyResult(List<Stmt> m) { this.members = m; }
            }
            
            @Override
            public Object visitClassDecl(DanexParser.ClassDeclContext ctx) {
                List<String> annotations = collectAnnotations(ctx.annotation());
                List<String> modifiers = collectModifiers(ctx.modifier());
                ClassBodyResult body = (ClassBodyResult) visit(ctx.classBody());
                return new ClassStmt(
                    ctx.IDENTIFIER().getText(), 
                    modifiers, 
                    annotations, 
                    body.members
                );
            }
            
            @Override
            public Object visitClassBody(DanexParser.ClassBodyContext ctx) {
                return new ClassBodyResult(collectList<Stmt>(ctx.classBodyMember()));
            }
            
            @Override
            public Object visitClassBodyMember(DanexParser.ClassBodyMemberContext ctx) {
                return ctx.methodDecl() != null 
                    ? visitMethodDecl(ctx.methodDecl()) 
                    : visitClassDecl(ctx.classDecl());
            }
            
            private List<String> collectAnnotations(List<DanexParser.AnnotationContext> annotations) {
                List<String> result = new ArrayList<>();
                for (DanexParser.AnnotationContext a : annotations) {
                    result.add(a.IDENTIFIER().getText());
                }
                return result;
            }
            
            private List<String> collectModifiers(List<DanexParser.ModifierContext> modifiers) {
                List<String> result = new ArrayList<>();
                for (DanexParser.ModifierContext m : modifiers) {
                    result.add(m.getText());
                }
                return result;
            }
            """);

        // Method declarations
        sb.append("""
            @Override
            public Object visitMethodDecl(DanexParser.MethodDeclContext ctx) {
                List<String> annotations = collectAnnotations(ctx.annotation());
                List<String> modifiers = collectModifiers(ctx.modifier());
                
                String returnType = null;
                String returnName = null;
                if (ctx.resultDecl() != null) {
                    DanexParser.ResultDeclContext rCtx = ctx.resultDecl();
                    returnType = rCtx.type().getText();
                    returnName = rCtx.IDENTIFIER() != null 
                        ? rCtx.IDENTIFIER().getText() 
                        : null;
                }
                
                List<Parameter> params = ctx.paramList() != null
                    ? collectList<Parameter>(ctx.paramList().param())
                    : new ArrayList<>();
                
                Stmt body = (Stmt) visit(ctx.methodBody());
                return new MethodStmt(
                    ctx.IDENTIFIER().getText(),
                    returnType,
                    params,
                    body,
                    modifiers,
                    annotations
                );
            }
            
            @Override
            public Object visitTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx) {
                return visitMethodDecl(ctx.methodDecl());
            }
            
            @Override
            public Object visitParam(DanexParser.ParamContext ctx) {
                return new Parameter(
                    ctx.type().getText(),
                    ctx.IDENTIFIER().getText(),
                    ctx.VARARGS() != null
                );
            }
            
            @Override
            public Object visitMethodBody(DanexParser.MethodBodyContext ctx) {
                return ctx.block() != null
                    ? visitBlock(ctx.block())
                    : new ExprStmt((Expr) visit(ctx.expression()));
            }
            """);

        // Control flow statements
        sb.append(generateControlFlowMethods());

        // Expressions
        sb.append(generateExpressionMethods());

        // Footer
        sb.append("}\n");

        // Write file
        Path out = Paths.get(OUTPUT_PATH);
        Files.createDirectories(out.getParent());
        Files.write(out, sb.toString().getBytes());
        System.out.println("✅ AstBuilder.java generated at " + out.toAbsolutePath());
    }

    private static String generateControlFlowMethods() {
        return """
            @Override
            public Object visitBlock(DanexParser.BlockContext ctx) {
                List<Stmt> stmts = new ArrayList<>();
                for (DanexParser.BlockContentContext bc : ctx.blockContent()) {
                    Object v = visit(bc);
                    if (v instanceof Stmt) stmts.add((Stmt) v);
                }
                return new BlockStmt(stmts);
            }
            
            @Override
            public Object visitBlockContent(DanexParser.BlockContentContext ctx) {
                if (ctx.statement() != null) return visit(ctx.statement());
                if (ctx.expressionStatement() != null) return visitExpressionStatement(ctx.expressionStatement());
                if (ctx.ifStatement() != null) return visitIfStatement(ctx.ifStatement());
                if (ctx.whileStatement() != null) return visitWhileStatement(ctx.whileStatement());
                if (ctx.doWhileStatement() != null) return visitDoWhileStatement(ctx.doWhileStatement());
                if (ctx.forStatement() != null) return visitForStatement(ctx.forStatement());
                if (ctx.tryStatement() != null) return visitTryStatement(ctx.tryStatement());
                if (ctx.exitStatement() != null) return visitExitStatement(ctx.exitStatement());
                if (ctx.throwStatement() != null) return visitThrowStatement(ctx.throwStatement());
                if (ctx.returnStatement() != null) return visitReturnStatement(ctx.returnStatement());
                throw new RuntimeException("Unknown blockContent: " + ctx.getText());
            }
            
            @Override
            public Object visitExpressionStatement(DanexParser.ExpressionStatementContext ctx) {
                return new ExprStmt((Expr) visit(ctx.expression()));
            }
            
            @Override
            public Object visitIfStatement(DanexParser.IfStatementContext ctx) {
                Expr cond = (Expr) visit(ctx.expression());
                Stmt thenB = (Stmt) visit(ctx.block(0));
                Stmt elseB = ctx.block().size() > 1 
                    ? (Stmt) visit(ctx.block(1)) 
                    : null;
                return new IfStmt(cond, thenB, elseB);
            }
            
            @Override
            public Object visitWhileStatement(DanexParser.WhileStatementContext ctx) {
                return new WhileStmt(
                    (Expr) visit(ctx.expression()),
                    (Stmt) visit(ctx.block())
                );
            }
            
            @Override
            public Object visitDoWhileStatement(DanexParser.DoWhileStatementContext ctx) {
                return new DoWhileStmt(
                    (Stmt) visit(ctx.block()),
                    (Expr) visit(ctx.expression())
                );
            }
            
            @Override
            public Object visitForStatement(DanexParser.ForStatementContext ctx) {
                return new ForStmt(
                    (Stmt) visit(ctx.assignment(0)),
                    (Expr) visit(ctx.expression()),
                    (Expr) visit(ctx.assignment(1)),
                    (Stmt) visit(ctx.block())
                );
            }
            
            @Override
            public Object visitTryStatement(DanexParser.TryStatementContext ctx) {
                List<Stmt> tryBlk = collectList(ctx.block(0).blockContent());
                
                String excName = null;
                List<Stmt> catchBlk = new ArrayList<>();
                if (ctx.CATCH() != null) {
                    excName = ctx.IDENTIFIER(0).getText();
                    catchBlk = collectList(ctx.block(1).blockContent());
                }
                
                List<Stmt> finBlk = new ArrayList<>();
                if (ctx.FINALLY() != null) {
                    int finallyIndex = ctx.block().size() - 1;
                    finBlk = collectList(ctx.block(finallyIndex).blockContent());
                }
                
                return new TryStmt(tryBlk, excName, catchBlk, finBlk);
            }
            
            @Override
            public Object visitExitStatement(DanexParser.ExitStatementContext ctx) { 
                return new ExitStmt(); 
            }
            
            @Override
            public Object visitThrowStatement(DanexParser.ThrowStatementContext ctx) { 
                return new ThrowStmt((Expr) visit(ctx.expression())); 
            }
            
            @Override
            public Object visitReturnStatement(DanexParser.ReturnStatementContext ctx) { 
                return new ReturnStmt(
                    ctx.expression() != null 
                        ? (Expr) visit(ctx.expression()) 
                        : null
                ); 
            }
            """;
    }

    private static String generateExpressionMethods() {
        return """
            @Override
            public Object visitAssignment(DanexParser.AssignmentContext ctx) {
                String name = ctx.IDENTIFIER().getText();
                String op = ctx.assignOp().getText();
                Expr r = (Expr) visit(ctx.assignment());
                return "=".equals(op)
                    ? new AssignExpr(name, r)
                    : new CompoundAssignExpr(name, op, r);
            }
            
            @Override
            public Object visitLambdaExpr(DanexParser.LambdaExprContext ctx) {
                List<Parameter> params = ctx.paramList() != null
                    ? collectList<Parameter>(ctx.paramList().param())
                    : new ArrayList<>();
                List<String> names = new ArrayList<>();
                for (Parameter p : params) names.add(p.getName());
                return new LambdaExpr(names, (Expr) visit(ctx.expression()));
            }
            
            @Override
            public Object visitLogicalOrExpr(DanexParser.LogicalOrExprContext ctx) {
                return buildLeftAssociativeExpr(
                    ctx.logicalAndExpr(), 
                    ctx.OR_OR()
                );
            }
            
            @Override
            public Object visitLogicalAndExpr(DanexParser.LogicalAndExprContext ctx) {
                return buildLeftAssociativeExpr(
                    ctx.nullCoalesceExpr(), 
                    ctx.AND_AND()
                );
            }
            
            @Override
            public Object visitNullCoalesceExpr(DanexParser.NullCoalesceExprContext ctx) {
                Expr expr = (Expr) visit(ctx.comparisonExpr(0));
                for (int i = 1; i < ctx.comparisonExpr().size(); i++) {
                    expr = new BinaryExpr(expr, "??", (Expr) visit(ctx.comparisonExpr(i)));
                }
                return expr;
            }
            
            private Expr buildLeftAssociativeExpr(
                List<? extends org.antlr.v4.runtime.ParserRuleContext> exprs,
                List<? extends org.antlr.v4.runtime.Token> ops
            ) {
                Expr left = (Expr) visit(exprs.get(0));
                for (int i = 1; i < exprs.size(); i++) {
                    left = new BinaryExpr(
                        left,
                        ops.get(i-1).getText(),
                        (Expr) visit(exprs.get(i))
                    );
                }
                return left;
            }
            
            // Additional expression methods would follow similar patterns...
            """;
    }
}
