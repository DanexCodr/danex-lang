package danex.ast;

import java.util.*;
public class ClassDecl extends Decl {
    public final String name;
    public final List<Annotation> annotations;
    public final List<String> modifiers;
    public final List<Stmt> members;

    public ClassDecl(String name, List<Annotation> annotations, List<String> modifiers, List<Stmt> members) {
        this.name = name;
        this.annotations = annotations;
        this.modifiers = modifiers;
        this.members = members;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        switch(visitor) {
            default: return visitor.visitClassDecl(this);
        }
    }
}
