package danex.ast;

import java.util.*;

public class ImportDecl extends Decl {
    public final String moduleName;
    public final String alias;

    public ImportDecl(String moduleName, String alias) {
        this.moduleName = moduleName;
        this.alias = alias;
    }

    @Override
    public <R> R accept(Decl.Visitor<R> visitor) {
        return visitor.visitImportDecl(this);
    }
}
