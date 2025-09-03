package danex.ast;

import java.util.*;

public class GroupingExpr extends Expr {
    public final Expr expression;

    public GroupingExpr(Expr expression) {
        this.expression = expression;
    }

    @Override
    public <R> R accept(Expr.Visitor<R> visitor) {
        return visitor.visitGroupingExpr(this);
    }
}
