package danex.ast;

import java.util.*;

public class UnaryExpr extends Expr {
    public final String operator;
    public final Expr right;

    public UnaryExpr(String operator, Expr right) {
        this.operator = operator;
        this.right = right;
    }

    @Override
    public <R> R accept(Expr.Visitor<R> visitor) {
        return visitor.visitUnaryExpr(this);
    }
}
