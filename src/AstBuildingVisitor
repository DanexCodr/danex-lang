// src/danex/AstBuildingVisitor.java
package danex;

import danex.antlr.DanexBaseVisitor;
import danex.antlr.DanexParser;
import danex.ast.*;

import java.util.*;
import static java.util.stream.Collectors.toList;

public class AstBuildingVisitor extends DanexBaseVisitor<Object> {

// Entry point: build from compilationUnit  
public List<Stmt> build(DanexParser.CompilationUnitContext ctx) {  
    return ctx.statement().stream()  
        .map(stmt -> (Stmt) visit(stmt))  
        .collect(toList());  
}  

// Statement visitors  
@Override  
public Object visitBlockStmt(DanexParser.BlockStmtContext ctx) {  
    List<Stmt> statements = ctx.statement().stream()  
        .map(stmt -> (Stmt) visit(stmt))  
        .collect(toList());  
    return new BlockStmt(statements);  
}  

@Override  
public Object visitExprStmt(DanexParser.ExprStmtContext ctx) {  
    Expr expr = (Expr) visit(ctx.expression());  
    return new ExprStmt(expr);  
}  

@Override  
public Object visitReturnStmt(DanexParser.ReturnStmtContext ctx) {  
    Expr value = ctx.expression() != null ? (Expr) visit(ctx.expression()) : null;  
    return new ReturnStmt(value);  
}  

@Override  
public Object visitIfStmt(DanexParser.IfStmtContext ctx) {  
    Expr condition = (Expr) visit(ctx.expression());  
    Stmt thenBranch = (Stmt) visit(ctx.thenBranch);  
    Stmt elseBranch = ctx.elseBranch != null ? (Stmt) visit(ctx.elseBranch) : null;  
    return new IfStmt(condition, thenBranch, elseBranch);  
}  

@Override  
public Object visitWhileStmt(DanexParser.WhileStmtContext ctx) {  
    Expr condition = (Expr) visit(ctx.expression());  
    Stmt body = (Stmt) visit(ctx.statement());  
    return new WhileStmt(condition, body);  
}  

@Override  
public Object visitDoWhileStmt(DanexParser.DoWhileStmtContext ctx) {  
    Stmt body = (Stmt) visit(ctx.statement());  
    Expr condition = (Expr) visit(ctx.expression());  
    return new DoWhileStmt(body, condition);  
}  

@Override  
public Object visitForStmt(DanexParser.ForStmtContext ctx) {  
    Stmt init = ctx.init != null ? (Stmt) visit(ctx.init) : null;  
    Expr condition = ctx.cond != null ? (Expr) visit(ctx.cond) : null;  
    Expr update = ctx.update != null ? (Expr) visit(ctx.update) : null;  
    Stmt body = (Stmt) visit(ctx.statement());  
    return new ForStmt(init, condition, update, body);  
}  

@Override  
public Object visitExitStmt(DanexParser.ExitStmtContext ctx) {  
    return new ExitStmt();  
}  

@Override  
public Object visitMethodStmt(DanexParser.MethodStmtContext ctx) {  
    String name = ctx.IDENTIFIER().getText();  
    List<String> params = ctx.parameters() != null  
        ? ctx.parameters().IDENTIFIER().stream().map(ParseTree::getText).collect(toList())  
        : new ArrayList<>();  
    List<Stmt> body = ctx.block().statement().stream()  
        .map(s -> (Stmt) visit(s)).collect(toList());  
    return new MethodStmt(name, params, body);  
}  

@Override  
public Object visitClassStmt(DanexParser.ClassStmtContext ctx) {  
    String name = ctx.IDENTIFIER().getText();  
    List<Stmt> members = ctx.statement().stream()  
        .map(stmt -> (Stmt) visit(stmt)).collect(toList());  
    return new ClassStmt(name, members);  
}  

@Override  
public Object visitThrowStmt(DanexParser.ThrowStmtContext ctx) {  
    Expr exception = (Expr) visit(ctx.expression());  
    return new ThrowStmt(exception);  
}  

@Override  
public Object visitTryStmt(DanexParser.TryStmtContext ctx) {  
    List<Stmt> tryBlock = ctx.tryBlock.statement().stream()  
        .map(s -> (Stmt) visit(s)).collect(toList());  
    String exceptionName = ctx.exception().IDENTIFIER().getText();  
    List<Stmt> catchBlock = ctx.catchBlock.statement().stream()  
        .map(s -> (Stmt) visit(s)).collect(toList());  
    List<Stmt> finallyBlock = ctx.finallyBlock != null  
        ? ctx.finallyBlock.statement().stream().map(s -> (Stmt) visit(s)).collect(toList())  
        : new ArrayList<>();  
    return new TryStmt(tryBlock, exceptionName, catchBlock, finallyBlock);  
}  

@Override  
public Object visitImportStmt(DanexParser.ImportStmtContext ctx) {  
    String moduleName = ctx.moduleName.getText();  
    String alias = ctx.alias != null ? ctx.alias.getText() : null;  
    return new ImportStmt(moduleName, alias);  
}  

// Expression visitors  
@Override  
public Object visitLiteralExpr(DanexParser.LiteralExprContext ctx) {  
    if (ctx.STRING() != null) return new LiteralExpr(ctx.STRING().getText());  
    if (ctx.NUMBER() != null) return new LiteralExpr(Double.parseDouble(ctx.NUMBER().getText()));  
    if (ctx.BOOL() != null) return new LiteralExpr(Boolean.parseBoolean(ctx.BOOL().getText()));  
    if (ctx.NULL() != null) return new LiteralExpr(null);  
    return null;  
}  

@Override  
public Object visitVariableExpr(DanexParser.VariableExprContext ctx) {  
    return new VariableExpr(ctx.IDENTIFIER().getText());  
}  

@Override  
public Object visitAssignExpr(DanexParser.AssignExprContext ctx) {  
    String name = ctx.IDENTIFIER().getText();  
    Expr value = (Expr) visit(ctx.expression());  
    return new AssignExpr(name, value);  
}  

@Override  
public Object visitBinaryExpr(DanexParser.BinaryExprContext ctx) {  
    Expr left = (Expr) visit(ctx.left);  
    String operator = ctx.op.getText();  
    Expr right = (Expr) visit(ctx.right);  
    return new BinaryExpr(left, operator, right);  
}  

@Override  
public Object visitUnaryExpr(DanexParser.UnaryExprContext ctx) {  
    String operator = ctx.op.getText();  
    Expr right = (Expr) visit(ctx.expression());  
    return new UnaryExpr(operator, right);  
}  

@Override  
public Object visitCallExpr(DanexParser.CallExprContext ctx) {  
    Expr callee = (Expr) visit(ctx.expression());  
    List<Expr> args = ctx.arguments() != null  
        ? ctx.arguments().expression().stream().map(e -> (Expr) visit(e)).collect(toList())  
        : new ArrayList<>();  
    return new CallExpr(callee, args);  
}  

@Override  
public Object visitGroupingExpr(DanexParser.GroupingExprContext ctx) {  
    return new GroupingExpr((Expr) visit(ctx.expression()));  
}  

@Override  
public Object visitLambdaExpr(DanexParser.LambdaExprContext ctx) {  
    List<String> params = ctx.parameters() != null  
        ? ctx.parameters().IDENTIFIER().stream().map(ParseTree::getText).collect(toList())  
        : new ArrayList<>();  
    Expr body = (Expr) visit(ctx.expression());  
    return new LambdaExpr(params, body);  
}  

@Override  
public Object visitNullCoalesceExpr(DanexParser.NullCoalesceExprContext ctx) {  
    Expr left = (Expr) visit(ctx.left);  
    Expr right = (Expr) visit(ctx.right);  
    return new NullCoalesceExpr(left, right);  
}  

@Override  
public Object visitTryExpr(DanexParser.TryExprContext ctx) {  
    Expr tryBody = (Expr) visit(ctx.tryBody);  
    String exceptionName = ctx.exception().IDENTIFIER().getText();  
    Expr catchBody = (Expr) visit(ctx.catchBody);  
    Expr finallyBody = ctx.finallyBody != null ? (Expr) visit(ctx.finallyBody) : null;  
    return new TryExpr(tryBody, exceptionName, catchBody, finallyBody);  
}  

@Override  
public Object visitAwaitExpr(DanexParser.AwaitExprContext ctx) {  
    Expr expr = (Expr) visit(ctx.expression());  
    return new AwaitExpr(expr);  
}  

@Override  
public Object visitDoExpr(DanexParser.DoExprContext ctx) {  
    List<Stmt> body = ctx.statement().stream()  
        .map(stmt -> (Stmt) visit(stmt)).collect(toList());  
    return new DoExpr(body);  
}  

@Override  
public Object visitComparatorExpr(DanexParser.ComparatorExprContext ctx) {  
    Expr left = (Expr) visit(ctx.left);  
    Expr right = (Expr) visit(ctx.right);  
    return new ComparatorExpr(left, right);  
}

}

