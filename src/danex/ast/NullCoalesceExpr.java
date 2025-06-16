package danex.ast;

import java.util.*;
public class NullCoalesceExpr extends Expr {
    public final Expr left;
    public final Expr right;

    public NullCoalesceExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public <R> R accept(Expr.Visitor<R> visitor) {
        return visitor.visitNullCoalesceExpr(this);
    }
}
