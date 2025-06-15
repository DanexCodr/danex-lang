// src/danex/ast/ExpressionStmt.java
package danex.ast;

public class ExpressionStmt extends Statement {
    public final Expression expr;

    public ExpressionStmt(Expression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return expr.toString() + ";";
    }
}
