// src/danex/ast/MethodDecl.java
package danex.ast;

import java.util.List;

public class MethodDecl extends ASTNode {
    public final String name;
    public final List<Param> params;
    public final ASTNode body; // body is a BlockStmt or similar

    public MethodDecl(String name, List<Param> params, ASTNode body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }

    @Override
    public String toString() {
        return "MethodDecl " + name + "(" + params + ") " + body;
    }
}
