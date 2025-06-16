package danex;

import java.util.List;

public interface DanexCallable {
    Object call(Interpreter interpreter, List<Object> args);
}
