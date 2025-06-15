package danex.ast;

public abstract class Expr extends ASTNode {
    public interface Visitor<R> {
        R visitBinaryExpr(BinaryExpr binaryExpr);
        R visitUnaryExpr(UnaryExpr unaryExpr);
        R visitLiteralExpr(LiteralExpr literalExpr);
        R visitGroupingExpr(GroupingExpr groupingExpr);
        R visitVariableExpr(VariableExpr variableExpr);
        R visitAssignExpr(AssignExpr assignExpr);
        R visitCallExpr(CallExpr callExpr);
        R visitLambdaExpr(LambdaExpr lambdaExpr);
        R visitDoExpr(DoExpr doExpr);
        R visitTryExpr(TryExpr tryExpr);
        R visitAwaitExpr(AwaitExpr awaitExpr);
        R visitNullCoalesceExpr(NullCoalesceExpr nullCoalesceExpr);
        R visitComparatorExpr(ComparatorExpr comparatorExpr);
    }

    public abstract <R> R accept(Visitor<R> visitor);
}
