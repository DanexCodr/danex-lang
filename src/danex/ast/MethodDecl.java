public class MethodDecl extends ASTNode {
    public String name;
    public List<String> params; // You can later replace with `Param` objects
    public ASTNode body;

    public MethodDecl(String name, List<String> params, ASTNode body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }
}
