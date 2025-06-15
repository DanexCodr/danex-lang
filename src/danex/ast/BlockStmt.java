package danex.ast;

import java.util.*;
public class BlockStmt extends Stmt {
    public final List<Stmt> statements;

    public BlockStmt(List<Stmt> statements) {
        this.statements = statements;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitBlockStmt(this);
    }
}
