package danex.ast;

/**
 * Represents return specification: optional type and optional name.
 */
public class ReturnSpec extends ASTNode {
    public final TypeNode type; // may be null
    public final String name;   // may be null

    public ReturnSpec(TypeNode type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        if (type != null && name != null) return "(" + type + " " + name + ")";
        if (type == null && name != null)   return "(" + name + ")";
        if (type != null)                   return "(" + type + ")";
        return "";
    }
}
