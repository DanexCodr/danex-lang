import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import danex.antlr.DanexLexer;
import danex.antlr.DanexParser;

import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        // Load your test DanexLang file from /examples
        CharStream input = CharStreams.fromStream(new FileInputStream("examples/Hello.danex"));

        // Create lexer and parser
        DanexLexer lexer = new DanexLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DanexParser parser = new DanexParser(tokens);

        // Replace with your actual start rule (e.g., program, script, etc.)
        ParseTree tree = parser.program(); 

        // Print the tree (optional)
        System.out.println(tree.toStringTree(parser));
    }
}
