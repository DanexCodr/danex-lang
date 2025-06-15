// src/danex/ast/Literal.java
package danex.ast;

public class Literal extends Expression {
    public String value; // Could be number or string

    public Literal(String value) {
        this.value = value;
    }
}
