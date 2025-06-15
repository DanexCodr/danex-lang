package danex.ast;

import java.util.*;
public class MethodStmt extends Stmt {
    public final String name;
    public final List<String> params;
    public final List<Stmt> body;

    public MethodStmt(String name, List<String> params, List<Stmt> body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitMethodStmt(this);
    }
}
