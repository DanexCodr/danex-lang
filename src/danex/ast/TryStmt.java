package danex.ast;

import java.util.*;
public class TryStmt extends Stmt {
    public final List<Stmt> tryBlock;
    public final String exceptionName;
    public final List<Stmt> catchBlock;
    public final List<Stmt> finallyBlock;

    public TryStmt(List<Stmt> tryBlock, String exceptionName, List<Stmt> catchBlock, List<Stmt> finallyBlock) {
        this.tryBlock = tryBlock;
        this.exceptionName = exceptionName;
        this.catchBlock = catchBlock;
        this.finallyBlock = finallyBlock;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitTryStmt(this);
    }
}
