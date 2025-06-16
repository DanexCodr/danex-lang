package danex.ast;

import java.util.*;
public class VariableExpr extends Expr {
    public final String name;

    public VariableExpr(String name) {
        this.name = name;
    }

    @Override
    public <R> R accept(Expr.Visitor<R> visitor) {
        return visitor.visitVariableExpr(this);
    }
}
