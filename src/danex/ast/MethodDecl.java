package danex.ast;

import java.util.*;
public class MethodDecl extends Decl {
    public final String name;
    public final String resultType;
    public final List<Annotation> annotations;
    public final List<String> modifiers;
    public final List<Param> params;
    public final Object body;

    public MethodDecl(String name, String resultType, List<Annotation> annotations, List<String> modifiers, List<Param> params, Object body) {
        this.name = name;
        this.resultType = resultType;
        this.annotations = annotations;
        this.modifiers = modifiers;
        this.params = params;
        this.body = body;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        switch(visitor) {
            default: return visitor.visitMethodDecl(this);
        }
    }
}
