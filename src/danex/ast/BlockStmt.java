// src/danex/ast/BlockStmt.java
package danex.ast;

import java.util.List;

public class BlockStmt extends ASTNode {
    public final List<ASTNode> statements;
    public BlockStmt(List<ASTNode> statements) {
        this.statements = statements;
    }
    @Override
    public String toString() {
        return "{ " + statements + " }";
    }
}
