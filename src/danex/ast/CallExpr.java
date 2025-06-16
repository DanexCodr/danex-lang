package danex.ast;

import java.util.*;
public class CallExpr extends Expr {
    public final Expr callee;
    public final List<Expr> arguments;

    public CallExpr(Expr callee, List<Expr> arguments) {
        this.callee = callee;
        this.arguments = arguments;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitCallExpr(this);
    }
}
