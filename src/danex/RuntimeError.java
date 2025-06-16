package danex;

import danex.ast.ASTNode;

public class RuntimeError extends RuntimeException {
    public final ASTNode node;

    public RuntimeError(ASTNode node, String message) {
        super(message);
        this.node = node;
    }
}
