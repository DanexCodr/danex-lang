package danex;

import danex.ast.*;
import danex.parser.DanexParser;
import danex.parser.DanexBaseVisitor;

import java.util.*;
import static danex.TokenUtil.*;

public class AstBuildingVisitor extends DanexBaseVisitor<Object> {

    @Override
    public Object visitCompilationUnit(DanexParser.CompilationUnitContext ctx) {
        List<Stmt> statements = new ArrayList<>();
        for (var stmtCtx : ctx.statement()) {
            statements.add((Stmt) visit(stmtCtx));
        }
        return statements;
    }

    // ----------------------
    // Statement Visitors
    // ----------------------

    @Override
    public Object visitBlock(DanexParser.BlockContext ctx) {
        List<Stmt> statements = new ArrayList<>();
        for (var content : ctx.blockContent()) {
            statements.add((Stmt) visit(content));
        }
        return new BlockStmt(statements);
    }

    @Override
    public Object visitExpressionStatement(DanexParser.ExpressionStatementContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        return new ExprStmt(expr);
    }

    @Override
    public Object visitReturnStatement(DanexParser.ReturnStatementContext ctx) {
        Token keyword = convertToken(ctx.RETURN().getSymbol());
        Expr value = ctx.expression() != null ? (Expr) visit(ctx.expression()) : null;
        return new ReturnStmt(keyword, value);
    }

    @Override
    public Object visitIfStatement(DanexParser.IfStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt thenBranch = (Stmt) visit(ctx.trueBranch);
        Stmt elseBranch = ctx.falseBranch != null ? (Stmt) visit(ctx.falseBranch) : null;
        return new IfStmt(condition, thenBranch, elseBranch);
    }

    @Override
    public Object visitWhileStatement(DanexParser.WhileStatementContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt body = (Stmt) visit(ctx.statement());
        return new WhileStmt(condition, body);
    }

    @Override
    public Object visitDoWhileStatement(DanexParser.DoWhileStatementContext ctx) {
        Stmt body = (Stmt) visit(ctx.statement());
        Expr condition = (Expr) visit(ctx.expression());
        return new DoWhileStmt(condition, body);
    }

    @Override
    public Object visitForStatement(DanexParser.ForStatementContext ctx) {
        Stmt init = ctx.init != null ? (Stmt) visit(ctx.init) : null;
        Expr cond = ctx.condition != null ? (Expr) visit(ctx.condition) : null;
        Expr step = ctx.step != null ? (Expr) visit(ctx.step) : null;
        Stmt body = (Stmt) visit(ctx.statement());
        return new ForStmt(init, cond, step, body);
    }

    @Override
    public Object visitExitStatement(DanexParser.ExitStatementContext ctx) {
        Token keyword = convertToken(ctx.EXIT().getSymbol());
        return new ExitStmt(keyword);
    }

    @Override
    public Object visitThrowStatement(DanexParser.ThrowStatementContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        return new ThrowStmt(expr);
    }

    @Override
    public Object visitTryStatement(DanexParser.TryStatementContext ctx) {
        Stmt tryBlock = (Stmt) visit(ctx.block());
        List<CatchClause> catches = new ArrayList<>();
        for (var c : ctx.catchClause()) {
            Token name = convertToken(c.IDENTIFIER().getSymbol());
            Stmt catchBlock = (Stmt) visit(c.block());
            catches.add(new CatchClause(name, catchBlock));
        }
        Stmt finallyBlock = ctx.finallyBlock != null ? (Stmt) visit(ctx.finallyBlock.block()) : null;
        return new TryStmt(tryBlock, catches, finallyBlock);
    }

    @Override
    public Object visitClassDecl(DanexParser.ClassDeclContext ctx) {
        Token name = convertToken(ctx.IDENTIFIER().getSymbol());
        List<Stmt> members = new ArrayList<>();
        for (var member : ctx.classBody().classBodyMember()) {
            members.add((Stmt) visit(member));
        }
        return new ClassStmt(name, members);
    }

    @Override
    public Object visitTopLevelMethodDecl(DanexParser.TopLevelMethodDeclContext ctx) {
        return visit(ctx.methodDecl());
    }

    @Override
    public Object visitMethodDecl(DanexParser.MethodDeclContext ctx) {
        Token name = convertToken(ctx.IDENTIFIER().getSymbol());
        List<Param> params = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (var paramCtx : ctx.paramList().param()) {
                params.add(new Param(convertToken(paramCtx.IDENTIFIER().getSymbol())));
            }
        }
        Stmt body = (Stmt) visit(ctx.methodBody());
        return new MethodStmt(name, params, body);
    }

    // ----------------------
    // Expression Visitors
    // ----------------------

    @Override
    public Object visitLiteral(DanexParser.LiteralContext ctx) {
        Token value = convertToken(ctx.getStart());
        return new LiteralExpr(value);
    }

    @Override
    public Object visitAssignment(DanexParser.AssignmentContext ctx) {
        Expr target = (Expr) visit(ctx.primaryExpr());
        Expr value = (Expr) visit(ctx.expression());
        return new AssignExpr(target, value);
    }

    @Override
    public Object visitLogicalOrExpr(DanexParser.LogicalOrExprContext ctx) {
        return buildBinaryExpr(ctx.expression(), ctx.OR().getSymbol(), ctx.logicalAndExpr());
    }

    @Override
    public Object visitLogicalAndExpr(DanexParser.LogicalAndExprContext ctx) {
        return buildBinaryExpr(ctx.expression(), ctx.AND().getSymbol(), ctx.nullCoalesceExpr());
    }

    @Override
    public Object visitComparisonExpr(DanexParser.ComparisonExprContext ctx) {
        return buildBinaryExpr(ctx.left, ctx.op.getSymbol(), ctx.right);
    }

    @Override
    public Object visitAdditiveExpr(DanexParser.AdditiveExprContext ctx) {
        return buildBinaryExpr(ctx.left, ctx.op.getSymbol(), ctx.right);
    }

    @Override
    public Object visitMultiplicativeExpr(DanexParser.MultiplicativeExprContext ctx) {
        return buildBinaryExpr(ctx.left, ctx.op.getSymbol(), ctx.right);
    }

    @Override
    public Object visitUnaryExpr(DanexParser.UnaryExprContext ctx) {
        Token operator = convertToken(ctx.op.getSymbol());
        Expr right = (Expr) visit(ctx.unaryExpr());
        return new UnaryExpr(operator, right);
    }

    @Override
    public Object visitFunctionCall(DanexParser.FunctionCallContext ctx) {
        Expr callee = (Expr) visit(ctx.primaryExpr());
        List<Expr> args = new ArrayList<>();
        if (ctx.arguments() != null) {
            for (var arg : ctx.arguments().expression()) {
                args.add((Expr) visit(arg));
            }
        }
        return new CallExpr(callee, args);
    }

    @Override
    public Object visitPrimaryExpr(DanexParser.PrimaryExprContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            return new VariableExpr(convertToken(ctx.IDENTIFIER().getSymbol()));
        } else if (ctx.literal() != null) {
            return visit(ctx.literal());
        } else if (ctx.expression() != null) {
            return visit(ctx.expression());
        }
        throw new RuntimeException("Unknown primary expression: " + ctx.getText());
    }

    @Override
    public Object visitTryExpression(DanexParser.TryExpressionContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        return new TryExpr(expr);
    }

    @Override
    public Object visitDoExpression(DanexParser.DoExpressionContext ctx) {
        Stmt body = (Stmt) visit(ctx.block());
        return new DoExpr(body);
    }

    // ----------------------
    // Utility
    // ----------------------

    private Expr buildBinaryExpr(Object leftNode, org.antlr.v4.runtime.Token opToken, Object rightNode) {
        Expr left = (Expr) visit(leftNode);
        Token operator = convertToken(opToken);
        Expr right = (Expr) visit(rightNode);
        return new BinaryExpr(left, operator, right);
    }
    }
