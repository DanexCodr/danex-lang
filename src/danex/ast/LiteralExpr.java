package danex.ast;

import java.util.*;
public class LiteralExpr extends Expr {
    public final Object value;

    public LiteralExpr(Object value) {
        this.value = value;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitLiteralExpr(this);
    }
}
