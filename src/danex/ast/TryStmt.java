package danex.ast;

import java.util.*;

public class TryStmt extends Stmt {
    public final List<ResourceDecl> resources;
    public final List<Stmt> tryBlock;
    public final String catchType;
    public final String catchName;
    public final List<Stmt> catchBlock;
    public final List<Stmt> finallyBlock;

    public TryStmt(List<ResourceDecl> resources, List<Stmt> tryBlock, String catchType, String catchName, List<Stmt> catchBlock, List<Stmt> finallyBlock) {
        this.resources = resources;
        this.tryBlock = tryBlock;
        this.catchType = catchType;
        this.catchName = catchName;
        this.catchBlock = catchBlock;
        this.finallyBlock = finallyBlock;
    }

    @Override
    public <R> R accept(Stmt.Visitor<R> visitor) {
        return visitor.visitTryStmt(this);
    }
}
