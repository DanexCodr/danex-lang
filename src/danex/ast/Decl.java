package danex.ast;

public abstract class Decl extends ASTNode {
    public interface Visitor<R> {
        R visitMethodDecl(MethodDecl methodDecl);
        R visitClassDecl(ClassDecl classDecl);
        R visitImportDecl(ImportDecl importDecl);
        R visitAnnotation(Annotation annotation);
        R visitParamDecl(ParamDecl paramDecl);
        R visitResourceDecl(ResourceDecl resourceDecl);
    }

    public abstract <R> R accept(Visitor<R> visitor);
}
