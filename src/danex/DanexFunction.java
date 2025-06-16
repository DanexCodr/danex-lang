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
        for (int i = 0; i < declaration.params.size(); i++) {
            String paramName = declaration.params.get(i).name;
            Object argValue = i < args.size() ? args.get(i) : null;
            localEnv.define(paramName, argValue);
        }

        try {
            // The method body is a single Stmt (probably BlockStmt)
            interpreter.executeInEnvironment(declaration.body, localEnv);
        } catch (Interpreter.Return r) {
            return r.value;
        }

        return null;
    }

    @Override
    public String toString() {
        return "<method " + declaration.name + ">";
    }
}
