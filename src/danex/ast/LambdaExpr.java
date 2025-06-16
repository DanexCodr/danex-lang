package danex.ast;

import java.util.*;
public class LambdaExpr extends Expr {
    public final List<Param> params;
    public final Expr body;

    public LambdaExpr(List<Param> params, Expr body) {
        this.params = params;
        this.body = body;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitLambdaExpr(this);
    }
}
