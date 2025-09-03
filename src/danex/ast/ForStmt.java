package danex.ast;

import java.util.*;

public class ForStmt extends Stmt {
    public final Stmt init;
    public final Expr condition;
    public final Expr update;
    public final Stmt body;

    public ForStmt(Stmt init, Expr condition, Expr update, Stmt body) {
        this.init = init;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    @Override
    public <R> R accept(Stmt.Visitor<R> visitor) {
        return visitor.visitForStmt(this);
    }
}
