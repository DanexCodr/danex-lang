package danex.ast;

public abstract class Decl extends ASTNode {
    public interface Visitor<R> {
        R visitMethodDecl(MethodDecl methodDecl);
        R visitClassDecl(ClassDecl classDecl);
        R visitImportDecl(ImportDecl importDecl);
    }

    public abstract <R> R accept(Visitor<R> visitor);
}
