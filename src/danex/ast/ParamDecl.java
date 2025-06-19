package danex.ast;

import java.util.*;
public class ParamDecl extends Decl {
    public final TypeNode type;
    public final String name;
    public final boolean varargs;

    public ParamDecl(TypeNode type, String name, boolean varargs) {
        this.type = type;
        this.name = name;
        this.varargs = varargs;
    }

    @Override
    public <R> R accept(Decl.Visitor<R> visitor) {
        return visitor.visitParamDecl(this);
    }
}
