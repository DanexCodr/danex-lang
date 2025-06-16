package danex.ast;

import java.util.*;
public class TryExpr extends Expr {
    public final Expr tryBody;
    public final String exceptionName;
    public final Expr catchBody;
    public final Expr finallyBody;

    public TryExpr(Expr tryBody, String exceptionName, Expr catchBody, Expr finallyBody) {
        this.tryBody = tryBody;
        this.exceptionName = exceptionName;
        this.catchBody = catchBody;
        this.finallyBody = finallyBody;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitTryExpr(this);
    }
}
