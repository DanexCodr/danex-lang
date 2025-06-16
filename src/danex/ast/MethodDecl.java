package danex.ast;

import java.util.*;
public class MethodDecl extends Decl {
    public final String name;
    public final String resultType;
    public final String resultName;
    public final List<Annotation> annotations;
    public final List<String> modifiers;
    public final List<Param> params;
    public final Stmt body;

    public MethodDecl(String name, String resultType, String resultName, List<Annotation> annotations, List<String> modifiers, List<Param> params, Stmt body) {
        this.name = name;
        this.resultType = resultType;
        this.resultName = resultName;
        this.annotations = annotations;
        this.modifiers = modifiers;
        this.params = params;
        this.body = body;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitMethodDecl(this);
    }
}
