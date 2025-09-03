package danex.ast;

import java.util.*;

public class LambdaExpr extends Expr {
    public final List<ParamDecl> params;
    public final Expr body;

    public LambdaExpr(List<ParamDecl> params, Expr body) {
        this.params = params;
        this.body = body;
    }

    @Override
    public <R> R accept(Expr.Visitor<R> visitor) {
        return visitor.visitLambdaExpr(this);
    }
}
