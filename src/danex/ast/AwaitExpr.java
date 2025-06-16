package danex.ast;

import java.util.*;
public class AwaitExpr extends Expr {
    public final Expr expression;

    public AwaitExpr(Expr expression) {
        this.expression = expression;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitAwaitExpr(this);
    }
}
