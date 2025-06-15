public class PrintStatement extends ASTNode {
    public ASTNode expression;

    public PrintStatement(ASTNode expression) {
        this.expression = expression;
    }
}
