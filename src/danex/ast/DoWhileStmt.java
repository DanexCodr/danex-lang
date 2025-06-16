package danex.ast;

import java.util.*;
public class DoWhileStmt extends Stmt {
    public final Stmt body;
    public final Expr condition;

    public DoWhileStmt(Stmt body, Expr condition) {
        this.body = body;
        this.condition = condition;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitDoWhileStmt(this);
    }
}
