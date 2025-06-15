// src/danex/ast/PrintStatement.java
package danex.ast;

public class PrintStatement extends ASTNode {
    public ASTNode expression;

    public PrintStatement(ASTNode expression) {
        this.expression = expression;
    }
}
