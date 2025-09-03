package danex.ast;

import java.util.*;

public class VarDeclStmt extends Stmt {
    public final TypeNode type;
    public final String name;
    public final Expr initializer;

    public VarDeclStmt(TypeNode type, String name, Expr initializer) {
        this.type = type;
        this.name = name;
        this.initializer = initializer;
    }

    @Override
    public <R> R accept(Stmt.Visitor<R> visitor) {
        return visitor.visitVarDeclStmt(this);
    }
}
