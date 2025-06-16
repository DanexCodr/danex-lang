package tools;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Generates AstBuilder.java based on manual mapping from grammar rule contexts to AST node constructors.
 * Adjust mappings as your AST class constructors evolve.
 */
public class AstBuilderGenerator {
    private static final String OUTPUT_PATH = "src/danex/AstBuilder.java";

    public static void main(String[] args) throws IOException {
        System.out.println("Working dir: " + System.getProperty("user.dir"));
        System.out.println("Starting AstBuilder generation...");
        StringBuilder sb = new StringBuilder();
        sb.append("package danex;\n\n");
        sb.append("import danex.antlr.DanexParser;\n");
        sb.append("import danex.antlr.DanexParserBaseVisitor;\n");
        sb.append("import danex.ast.*;\n");
        sb.append("import org.antlr.v4.runtime.tree.TerminalNode;\n");
        sb.append("import java.util.*;\n\n");
        sb.append("public class AstBuilder extends DanexParserBaseVisitor<Object> {\n\n");

        // Visit compilationUnit
        sb.append("    @Override\n");
        sb.append("    public Object visitCompilationUnit(DanexParser.CompilationUnitContext ctx) {\n");
        sb.append("        List<Stmt> statements = new ArrayList<>();\n");
        sb.append("        for (DanexParser.StatementContext sCtx : ctx.statement()) {\n");
        sb.append("            statements.add((Stmt) visit(sCtx));\n");
        sb.append("        }\n");
        sb.append("        return statements;\n");
        sb.append("    }\n\n");

        // importStmt: USE IDENTIFIER (AS IDENTIFIER)? SEMI
        sb.append("    @Override\n");
        sb.append("    public Object visitImportStmt(DanexParser.ImportStmtContext ctx) {\n");
        sb.append("        String module = ctx.IDENTIFIER(0).getText();\n");
        sb.append("        String alias = null;\n");
        sb.append("        if (ctx.IDENTIFIER().size() > 1) alias = ctx.IDENTIFIER(1).getText();\n");
        sb.append("        return new ImportStmt(module, alias);\n");
        sb.append("    }\n\n");

        // classDecl: annotation* modifier* CLASS IDENTIFIER classBody
        sb.append("    @Override\n");
        sb.append("    public Object visitClassDecl(DanexParser.ClassDeclContext ctx) {\n");
        sb.append("        List<String> annotations = new ArrayList<>();\n");
        sb.append("        for (DanexParser.AnnotationContext a : ctx.annotation()) annotations.add(a.IDENTIFIER().getText());\n");
        sb.append("        List<String> modifiers = new ArrayList<>();\n");
        sb.append("        for (DanexParser.ModifierContext m : ctx.modifier()) modifiers.add(m.getText());\n");
        sb.append("        String name = ctx.IDENTIFIER().getText();\n");
        sb.append("        ClassBodyResult body = (ClassBodyResult) visit(ctx.classBody());\n");
        sb.append("        return new ClassStmt(name, modifiers, annotations, body.members);\n");
        sb.append("    }\n\n");
        // Helper ClassBodyResult
        sb.append("    private static class ClassBodyResult { List<Stmt> members; ClassBodyResult(List<Stmt> m) { this.members = m; } }\n\n");
        sb.append("    @Override\n");
        sb.append("    public Object visitClassBody(DanexParser.ClassBodyContext ctx) {\n");
        sb.append("        List<Stmt> members = new ArrayList<>();\n");
        sb.append("        for (DanexParser.ClassBodyMemberContext mCtx : ctx.classBodyMember()) {\n");
        sb.append("            members.add((Stmt) visit(mCtx));\n");
        sb.append("        }\n");
        sb.append("        return new ClassBodyResult(members);\n");
        sb.append("    }\n\n");
        sb.append("    @Override\n");
        sb.append("    public Object visitClassBodyMember(DanexParser.ClassBodyMemberContext ctx) {\n");
        sb.append("        if (ctx.methodDecl() != null) return visitMethodDecl(ctx.methodDecl());\n");
        sb.append("        else return visitClassDecl(ctx.classDecl());\n");
        sb.append("    }\n\n");

        // methodDecl
        sb.append("    @Override\n");
        sb.append("    public Object visitMethodDecl(DanexParser.MethodDeclContext ctx) {\n");
        sb.append("        List<String> annotations = new ArrayList<>();\n");
        sb.append("        for (DanexParser.AnnotationContext a : ctx.annotation()) annotations.add(a.IDENTIFIER().getText());\n");
        sb.append("        String returnType = null; String returnName = null;\n");
        sb.append("        if (ctx.resultDecl() != null) {\n");
        sb.append("            DanexParser.ResultDeclContext rCtx = ctx.resultDecl();\n");
        sb.append("            returnType = rCtx.type().getText();\n");
        sb.append("            if (rCtx.IDENTIFIER() != null) returnName = rCtx.IDENTIFIER().getText();\n");
        sb.append("        }\n");
        sb.append("        List<String> modifiers = new ArrayList<>();\n");
        sb.append("        for (DanexParser.ModifierContext m : ctx.modifier()) modifiers.add(m.getText());\n");
        sb.append("        String name = ctx.IDENTIFIER().getText();\n");
        sb.append("        List<Parameter> params = new ArrayList<>();\n");
        sb.append("        if (ctx.paramList() != null) { for (DanexParser.ParamContext p : ctx.paramList().param()) params.add((Parameter) visit(p)); }\n");
        sb.append("        Stmt body = (Stmt) visit(ctx.methodBody());\n");
        sb.append("        return new MethodStmt(name, returnType, params, body, modifiers, annotations);\n");
        sb.append("    }\n\n");
        // topLevelMethodDecl forwards to methodDecl
        sb.append("    @Override\n");
        sb.append("    public Object visitTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx) {\n");
        sb.append("        return visitMethodDecl(ctx);\n");
        sb.append("    }\n\n");

        // annotation and modifier not standalone

        // param
        sb.append("    @Override\n");
        sb.append("    public Object visitParam(DanexParser.ParamContext ctx) {\n");
        sb.append("        String type = ctx.type().getText();\n");
        sb.append("        boolean varargs = ctx.VARARGS() != null;\n");
        sb.append("        String name = ctx.IDENTIFIER().getText();\n");
        sb.append("        return new Parameter(type, name, varargs);\n");
        sb.append("    }\n\n");
        // methodBody
        sb.append("    @Override\n");
        sb.append("    public Object visitMethodBody(DanexParser.MethodBodyContext ctx) {\n");
        sb.append("        if (ctx.block() != null) return visitBlock(ctx.block());\n");
        sb.append("        Expr e = (Expr) visit(ctx.expression());\n");
        sb.append("        return new ExprStmt(e);\n");
        sb.append("    }\n\n");

        // block & blockContent
        sb.append("    @Override\n");
        sb.append("    public Object visitBlock(DanexParser.BlockContext ctx) {\n");
        sb.append("        List<Stmt> stmts = new ArrayList<>();\n");
        sb.append("        for (DanexParser.BlockContentContext bc : ctx.blockContent()) { Object v = visit(bc); if (v instanceof Stmt) stmts.add((Stmt)v); }\n");
        sb.append("        return new BlockStmt(stmts);\n");
        sb.append("    }\n\n");
        sb.append("    @Override\n");
        sb.append("    public Object visitBlockContent(DanexParser.BlockContentContext ctx) {\n");
        sb.append("        if (ctx.statement() != null) return visit(ctx.statement());\n");
        sb.append("        if (ctx.expressionStatement() != null) return visitExpressionStatement(ctx.expressionStatement());\n");
        sb.append("        if (ctx.ifStatement() != null) return visitIfStatement(ctx.ifStatement());\n");
        sb.append("        if (ctx.whileStatement() != null) return visitWhileStatement(ctx.whileStatement());\n");
        sb.append("        if (ctx.doWhileStatement() != null) return visitDoWhileStatement(ctx.doWhileStatement());\n");
        sb.append("        if (ctx.forStatement() != null) return visitForStatement(ctx.forStatement());\n");
        sb.append("        if (ctx.tryStatement() != null) return visitTryStatement(ctx.tryStatement());\n");
        sb.append("        if (ctx.exitStatement() != null) return visitExitStatement(ctx.exitStatement());\n");
        sb.append("        if (ctx.throwStatement() != null) return visitThrowStatement(ctx.throwStatement());\n");
        sb.append("        if (ctx.returnStatement() != null) return visitReturnStatement(ctx.returnStatement());\n");
        sb.append("        throw new RuntimeException(\"Unknown blockContent: \" + ctx.getText());\n");
        sb.append("    }\n\n");

        // expressionStatement
        sb.append("    @Override\n");
        sb.append("    public Object visitExpressionStatement(DanexParser.ExpressionStatementContext ctx) {\n");
        sb.append("        Expr e = (Expr) visit(ctx.expression()); return new ExprStmt(e);\n");
        sb.append("    }\n\n");

        // ifStatement
        sb.append("    @Override\n");
        sb.append("    public Object visitIfStatement(DanexParser.IfStatementContext ctx) {\n");
        sb.append("        Expr cond = (Expr) visit(ctx.expression());\n");
        sb.append("        Stmt thenB = (Stmt) visit(ctx.block(0));\n");
        sb.append("        Stmt elseB = null; if (ctx.block().size() > 1) elseB = (Stmt) visit(ctx.block(1));\n");
        sb.append("        return new IfStmt(cond, thenB, elseB);\n");
        sb.append("    }\n\n");

        // whileStatement
        sb.append("    @Override\n");
        sb.append("    public Object visitWhileStatement(DanexParser.WhileStatementContext ctx) {\n");
        sb.append("        Expr cond = (Expr) visit(ctx.expression());\n");
        sb.append("        Stmt body = (Stmt) visit(ctx.block()); return new WhileStmt(cond, body);\n");
        sb.append("    }\n\n");

        // doWhileStatement
        sb.append("    @Override\n");
        sb.append("    public Object visitDoWhileStatement(DanexParser.DoWhileStatementContext ctx) {\n");
        sb.append("        Stmt body = (Stmt) visit(ctx.block()); Expr cond = (Expr) visit(ctx.expression()); return new DoWhileStmt(body, cond);\n");
        sb.append("    }\n\n");

        // forStatement
        sb.append("    @Override\n");
        sb.append("    public Object visitForStatement(DanexParser.ForStatementContext ctx) {\n");
        sb.append("        Stmt init = (Stmt) visit(ctx.assignment(0));\n");
        sb.append("        Expr cond = (Expr) visit(ctx.expression());\n");
        sb.append("        Expr upd = (Expr) visit(ctx.assignment(1));\n");
        sb.append("        Stmt body = (Stmt) visit(ctx.block()); return new ForStmt(init, cond, upd, body);\n");
        sb.append("    }\n\n");

        // tryStatement
        sb.append("    @Override\n");
        sb.append("    public Object visitTryStatement(DanexParser.TryStatementContext ctx) {\n");
        sb.append("        List<Stmt> tryBlk = new ArrayList<>(); for (DanexParser.BlockContentContext bc: ctx.block().blockContent()) { Object v=visit(bc); if(v instanceof Stmt) tryBlk.add((Stmt)v);}\n");
        sb.append("        String excName = null; List<Stmt> catchBlk = new ArrayList<>(); List<Stmt> finBlk = new ArrayList<>();\n");
        sb.append("        if (ctx.CATCH() != null) { excName = ctx.IDENTIFIER(0).getText(); DanexParser.BlockContext cctx = ctx.block(1); for (DanexParser.BlockContentContext bc: cctx.blockContent()) { Object v=visit(bc); if(v instanceof Stmt) catchBlk.add((Stmt)v);} }\n");
        sb.append("        if (ctx.FINALLY() != null) { DanexParser.BlockContext fctx = ctx.block(ctx.block().size()-1); for (DanexParser.BlockContentContext bc: fctx.blockContent()) { Object v=visit(bc); if(v instanceof Stmt) finBlk.add((Stmt)v);} }\n");
        sb.append("        return new TryStmt(tryBlk, excName, catchBlk, finBlk);\n");
        sb.append("    }\n\n");

        // exitStatement
        sb.append("    @Override\n");
        sb.append("    public Object visitExitStatement(DanexParser.ExitStatementContext ctx) { return new ExitStmt(); }\n\n");

        // throwStatement
        sb.append("    @Override\n");
        sb.append("    public Object visitThrowStatement(DanexParser.ThrowStatementContext ctx) { Expr e=(Expr)visit(ctx.expression()); return new ThrowStmt(e); }\n\n");

        // returnStatement
        sb.append("    @Override\n");
        sb.append("    public Object visitReturnStatement(DanexParser.ReturnStatementContext ctx) { Expr v=null; if(ctx.expression()!=null) v=(Expr)visit(ctx.expression()); return new ReturnStmt(v); }\n\n");

        // assignment
        sb.append("    @Override\n");
        sb.append("    public Object visitAssignment(DanexParser.AssignmentContext ctx) { String name=ctx.IDENTIFIER().getText(); String op=ctx.assignOp().getText(); Expr r=(Expr)visit(ctx.assignment()); if("=".equals(op)) return new AssignExpr(name, r); else return new CompoundAssignExpr(name, op, r); }\n\n");

        // lambdaExpr
        sb.append("    @Override\n");
        sb.append("    public Object visitLambdaExpr(DanexParser.LambdaExprContext ctx) { List<Parameter> params=new ArrayList<>(); if(ctx.paramList()!=null) for(var p:ctx.paramList().param()) params.add((Parameter)visit(p)); Expr body=(Expr)visit(ctx.expression()); List<String> names=new ArrayList<>(); for(Parameter p:params) names.add(p.getName()); return new LambdaExpr(names, body); }\n\n");

        // logicalOrExpr
        sb.append("    @Override\n");
        sb.append("    public Object visitLogicalOrExpr(DanexParser.LogicalOrExprContext ctx) { Expr expr=(Expr)visit(ctx.logicalAndExpr(0)); for(int i=1;i<ctx.logicalAndExpr().size();i++){ Expr right=(Expr)visit(ctx.logicalAndExpr(i)); String op=ctx.OR_OR(i-1).getText(); expr=new BinaryExpr(expr, op, right);} return expr; }\n\n");
        // logicalAndExpr
        sb.append("    @Override\n");
        sb.append("    public Object visitLogicalAndExpr(DanexParser.LogicalAndExprContext ctx) { Expr expr=(Expr)visit(ctx.nullCoalesceExpr(0)); for(int i=1;i<ctx.nullCoalesceExpr().size();i++){ Expr right=(Expr)visit(ctx.nullCoalesceExpr(i)); String op=ctx.AND_AND(i-1).getText(); expr=new BinaryExpr(expr, op, right);} return expr; }\n\n");
        // nullCoalesceExpr
        sb.append("    @Override\n");
        sb.append("    public Object visitNullCoalesceExpr(DanexParser.NullCoalesceExprContext ctx) { Expr expr=(Expr)visit(ctx.comparisonExpr(0)); for(int i=1;i<ctx.comparisonExpr().size();i++){ Expr right=(Expr)visit(ctx.comparisonExpr(i)); expr=new BinaryExpr(expr, \"??\", right);} return expr; }\n\n");
        // comparisonExpr
        sb.append("    @Override\n");
        sb.append("    public Object visitComparisonExpr(DanexParser.ComparisonExprContext ctx) { Expr expr=(Expr)visit(ctx.additiveExpr(0)); for(int i=1;i<ctx.additiveExpr().size();i++){ Expr right=(Expr)visit(ctx.additiveExpr(i)); String op=ctx.getChild(2*i-1).getText(); expr=new BinaryExpr(expr, op, right);} return expr; }\n\n");
        // additiveExpr
        sb.append("    @Override\n");
        sb.append("    public Object visitAdditiveExpr(DanexParser.AdditiveExprContext ctx) { Expr expr=(Expr)visit(ctx.multiplicativeExpr(0)); for(int i=1;i<ctx.multiplicativeExpr().size();i++){ Expr right=(Expr)visit(ctx.multiplicativeExpr(i)); String op=ctx.getChild(2*i-1).getText(); expr=new BinaryExpr(expr, op, right);} return expr; }\n\n");
        // multiplicativeExpr
        sb.append("    @Override\n");
        sb.append("    public Object visitMultiplicativeExpr(DanexParser.MultiplicativeExprContext ctx) { Expr expr=(Expr)visit(ctx.unaryExpr(0)); for(int i=1;i<ctx.unaryExpr().size();i++){ Expr right=(Expr)visit(ctx.unaryExpr(i)); String op=ctx.getChild(2*i-1).getText(); expr=new BinaryExpr(expr, op, right);} return expr; }\n\n");
        // unaryExpr
        sb.append("    @Override\n");
        sb.append("    public Object visitUnaryExpr(DanexParser.UnaryExprContext ctx) { Expr expr=(Expr)visit(ctx.primaryExpr()); for(int i=ctx.getChildCount()-2;i>=0;i--){ String op=ctx.getChild(i).getText(); expr=new UnaryExpr(op, expr);} return expr; }\n\n");
        // primaryExpr
        sb.append("    @Override\n");
        sb.append("    public Object visitPrimaryExpr(DanexParser.PrimaryExprContext ctx) {\n");
        sb.append("        if (ctx.expression() != null) return visit(ctx.expression());\n");
        sb.append("        if (ctx.doExpression() != null) return visitDoExpression(ctx.doExpression());\n");
        sb.append("        if (ctx.tryExpression() != null) return visitTryExpression(ctx.tryExpression());\n");
        sb.append("        if (ctx.functionCall() != null) return visitFunctionCall(ctx.functionCall());\n");
        sb.append("        if (ctx.literal() != null) return visitLiteral(ctx.literal());\n");
        sb.append("        if (ctx.IDENTIFIER() != null) return new VariableExpr(ctx.IDENTIFIER().getText());\n");
        sb.append("        throw new RuntimeException(\"Unknown primaryExpr: \" + ctx.getText()); }\n\n");
        // doExpression
        sb.append("    @Override\n");
        sb.append("    public Object visitDoExpression(DanexParser.DoExpressionContext ctx) { Stmt blk=(Stmt)visit(ctx.block()); return new DoExpr(((BlockStmt)blk).getStatements()); }\n\n");
        // tryExpression
        sb.append("    @Override\n");
        sb.append("    public Object visitTryExpression(DanexParser.TryExpressionContext ctx) { List<Stmt> tryBlk=((BlockStmt)visit(ctx.block())).getStatements(); return new TryExpr(tryBlk); }\n\n");
        // functionCall
        sb.append("    @Override\n");
        sb.append("    public Object visitFunctionCall(DanexParser.FunctionCallContext ctx) { Expr callee=new VariableExpr(ctx.IDENTIFIER().getText()); List<Expr> args=new ArrayList<>(); if(ctx.expression()!=null){ for(var e:ctx.expression()) args.add((Expr)visit(e)); } return new CallExpr(callee, args);}\n\n");
        // literal
        sb.append("    @Override\n");
        sb.append("    public Object visitLiteral(DanexParser.LiteralContext ctx) { if(ctx.NUMBER()!=null){ return new LiteralExpr(Double.parseDouble(ctx.NUMBER().getText())); } if(ctx.STRING()!=null){ String t=ctx.STRING().getText(); t=t.substring(1,t.length()-1); return new LiteralExpr(t);} if(ctx.TRUE()!=null) return new LiteralExpr(true); if(ctx.FALSE()!=null) return new LiteralExpr(false); if(ctx.NULL()!=null) return new LiteralExpr(null); throw new RuntimeException(\"Unknown literal: \"+ctx.getText()); }\n\n");
        // type
        sb.append("    @Override\n");
        sb.append("    public Object visitType(DanexParser.TypeContext ctx) { return ctx.IDENTIFIER().getText(); }\n");

        sb.append("}\n");

        // Write file
        Path out = Paths.get(OUTPUT_PATH);
        Files.createDirectories(out.getParent());
        Files.write(out, sb.toString().getBytes());
        System.out.println("✅ AstBuilder.java generated at " + out.toAbsolutePath());
    }
}
