package danex.ast;

import java.util.*;
public class ImportStmt extends Stmt {
    public final String moduleName;
    public final String alias;

    public ImportStmt(String moduleName, String alias) {
        this.moduleName = moduleName;
        this.alias = alias;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitImportStmt(this);
    }
}
