package danex.ast;

import java.util.*;

public class AssignStmt extends Stmt {
    public final Expr target;
    public final Expr value;

    public AssignStmt(Expr target, Expr value) {
        this.target = target;
        this.value = value;
    }

    @Override
    public <R> R accept(Stmt.Visitor<R> visitor) {
        return visitor.visitAssignStmt(this);
    }
}
