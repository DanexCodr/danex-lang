package danex.ast;

import java.util.*;
public class AssignExpr extends Expr {
    public final String name;
    public final Expr value;

    public AssignExpr(String name, Expr value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitAssignExpr(this);
    }
}
