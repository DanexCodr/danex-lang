package danex;

import danex.ast.*;
import java.util.List;

public class DanexFunction implements DanexCallable {
    private final MethodDecl declaration;
    private final Environment closure;

    public DanexFunction(MethodDecl declaration, Environment closure) {
        this.declaration = declaration;
        this.closure = closure;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> args) {
        Environment localEnv = new Environment(closure);
        // Bind parameters:
        for (int i = 0; i < declaration.params.size(); i++) {
            String paramName = declaration.params.get(i).name;
            Object argValue = i < args.size() ? args.get(i) : null;
            localEnv.define(paramName, argValue);
        }
        // Execute the method body in localEnv:
        interpreter.executeInEnvironment(declaration.body, localEnv);

        // Determine return:
        // If a resultName was declared, use that; otherwise use method name.
        String resultKey = (declaration.returnSpec != null && declaration.returnSpec.name != null)
    ? declaration.returnSpec.name
    : declaration.name;
        if (localEnv.contains(resultKey)) {
            return localEnv.get(resultKey);
        }
        // No assignment to resultKey → void method → return null
        return null;
    }

    @Override
    public String toString() {
        return "<method " + declaration.name + ">";
    }
}
