// src/danex/ast/BinaryOp.java
package danex.ast;

public class BinaryOp extends Expression {
    public String op; // "+", "-", "*", etc.
    public Expression left;
    public Expression right;

    public BinaryOp(String op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }
}
