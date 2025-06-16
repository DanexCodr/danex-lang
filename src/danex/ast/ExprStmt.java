package danex.ast;

import java.util.*;
public class ExprStmt extends Stmt {
    public final Expr expression;

    public ExprStmt(Expr expression) {
        this.expression = expression;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitExprStmt(this);
    }
}
