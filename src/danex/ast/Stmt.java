package danex.ast;

public abstract class Stmt extends ASTNode {
    public interface Visitor<R> {
        R visitExprStmt(ExprStmt exprStmt);
        R visitBlockStmt(BlockStmt blockStmt);
        R visitIfStmt(IfStmt ifStmt);
        R visitWhileStmt(WhileStmt whileStmt);
        R visitDoWhileStmt(DoWhileStmt doWhileStmt);
        R visitForStmt(ForStmt forStmt);
        R visitReturnStmt(ReturnStmt returnStmt);
        R visitThrowStmt(ThrowStmt throwStmt);
        R visitExitStmt(ExitStmt exitStmt);
        R visitTryStmt(TryStmt tryStmt);
        R visitClassStmt(ClassStmt classStmt);
        R visitMethodStmt(MethodStmt methodStmt);
        R visitImportStmt(ImportStmt importStmt);
    }

    public abstract <R> R accept(Visitor<R> visitor);
}
