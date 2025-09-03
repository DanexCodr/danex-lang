package danex.ast;

import java.util.*;

public class BinaryExpr extends Expr {
    public final Expr left;
    public final String operator;
    public final Expr right;

    public BinaryExpr(Expr left, String operator, Expr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public <R> R accept(Expr.Visitor<R> visitor) {
        return visitor.visitBinaryExpr(this);
    }
}
