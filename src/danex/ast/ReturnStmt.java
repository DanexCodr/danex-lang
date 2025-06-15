package danex.ast;

import java.util.*;
public class ReturnStmt extends Stmt {
    public final Expr value;

    public ReturnStmt(Expr value) {
        this.value = value;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitReturnStmt(this);
    }
}
