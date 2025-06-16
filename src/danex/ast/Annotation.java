package danex.ast;

import java.util.*;
public class Annotation extends Decl {
    public final String name;

    public Annotation(String name) {
        this.name = name;
    }

    @Override
    public <R> R accept(Decl.Visitor<R> visitor) {
        return visitor.visitAnnotation(this);
    }
}
