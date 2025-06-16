package danex.ast;

import java.util.*;
public class DoExpr extends Expr {
    public final List<Stmt> body;

    public DoExpr(List<Stmt> body) {
        this.body = body;
    }

    @Override
    public <R> R accept(Expr.Visitor<R> visitor) {
        return visitor.visitDoExpr(this);
    }
}
