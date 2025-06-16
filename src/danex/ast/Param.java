package danex.ast;

import java.util.*;
public class Param extends Decl {
    public final String type;
    public final String name;
    public final boolean varargs;

    public Param(String type, String name, boolean varargs) {
        this.type = type;
        this.name = name;
        this.varargs = varargs;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitParam(this);
    }
}
