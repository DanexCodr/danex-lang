package danex.ast;

import java.util.*;
public class MethodDecl extends Decl {
    public final String name;
    public final ReturnSpec returnSpec;
    public final List<Annotation> annotations;
    public final List<String> modifiers;
    public final List<ParamDecl> params;
    public final BlockStmt body;
    public final Expr exprBody;
    public final boolean isArrow;

    public MethodDecl(String name, ReturnSpec returnSpec, List<Annotation> annotations, List<String> modifiers, List<ParamDecl> params, BlockStmt body, Expr exprBody, boolean isArrow) {
        this.name = name;
        this.returnSpec = returnSpec;
        this.annotations = annotations;
        this.modifiers = modifiers;
        this.params = params;
        this.body = body;
        this.exprBody = exprBody;
        this.isArrow = isArrow;
    }

    @Override
    public <R> R accept(Decl.Visitor<R> visitor) {
        return visitor.visitMethodDecl(this);
    }
}
