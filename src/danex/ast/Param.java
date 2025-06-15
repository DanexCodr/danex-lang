// src/danex/ast/Param.java
package danex.ast;

public class Param {
    public final String type;
    public final String name;

    public Param(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
