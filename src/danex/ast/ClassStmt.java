package danex.ast;

import java.util.*;
public class ClassStmt extends Stmt {
    public final String name;
    public final List<Stmt> members;

    public ClassStmt(String name, List<Stmt> members) {
        this.name = name;
        this.members = members;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitClassStmt(this);
    }
}
