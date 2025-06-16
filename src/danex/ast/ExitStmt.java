package danex.ast;

import java.util.*;
public class ExitStmt extends Stmt {

    public ExitStmt() {
    }

    @Override
    public <R> R accept(Stmt.Visitor<R> visitor) {
        return visitor.visitExitStmt(this);
    }
}
