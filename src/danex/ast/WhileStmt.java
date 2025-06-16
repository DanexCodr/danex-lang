package danex.ast;

import java.util.*;
public class WhileStmt extends Stmt {
    public final Expr condition;
    public final Stmt body;

    public WhileStmt(Expr condition, Stmt body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitWhileStmt(this);
    }
}
