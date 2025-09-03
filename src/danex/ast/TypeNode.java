package danex.ast;

/**
 * Minimal TypeNode skeleton. Extend as needed.
 */
public class TypeNode extends ASTNode {
    public final String name;

    public TypeNode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
