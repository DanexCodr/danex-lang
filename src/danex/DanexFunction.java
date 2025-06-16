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
            String paramName = declaration.params.get(i).lexeme;
            Object argValue = i < args.size() ? args.get(i) : null;
            localEnv.define(paramName, argValue);
        }

        try {
            for (Stmt stmt : declaration.body) {
                interpreter.executeInEnvironment(stmt, localEnv);
            }
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
