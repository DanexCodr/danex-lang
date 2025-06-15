// src/Main.java
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import danex.antlr.DanexLexer;
import danex.antlr.DanexParser;

import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Usage: java Main <path-to-.danex>");
            System.exit(1);
        }
        String inputPath = args[0];
        CharStream input = CharStreams.fromStream(new FileInputStream(inputPath));

        DanexLexer lexer = new DanexLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DanexParser parser = new DanexParser(tokens);

        // Call the top-level rule
        ParseTree tree = parser.compilationUnit();

        // Print the parse tree for debugging
        System.out.println(tree.toStringTree(parser));
    }
}
