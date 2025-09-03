package danex.ast;

import java.util.*;

public class ResourceDecl extends Decl {
    public final String type;
    public final String name;
    public final Expr initializer;

    public ResourceDecl(String type, String name, Expr initializer) {
        this.type = type;
        this.name = name;
        this.initializer = initializer;
    }

    @Override
    public <R> R accept(Decl.Visitor<R> visitor) {
        return visitor.visitResourceDecl(this);
    }
}
