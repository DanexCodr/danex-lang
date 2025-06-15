package danex.ast;

import java.util.*;
public class ThrowStmt extends Stmt {
    public final Expr exception;

    public ThrowStmt(Expr exception) {
        this.exception = exception;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitThrowStmt(this);
    }
}
