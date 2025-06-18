package danex;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import danex.grammar.DanexLexer;
import danex.grammar.DanexParser;
import danex.ast.Decl;

import java.io.FileInputStream;
import java.util.List;

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

        // --- Token dump for debugging ---
        tokens.fill();
        Vocabulary vocab = lexer.getVocabulary();
        System.out.println("=== Token dump for " + inputPath + " ===");
        for (Token t : tokens.getTokens()) {
            if (t.getType() == Token.EOF) {
                System.out.println("EOF");
            } else {
                String name = vocab.getSymbolicName(t.getType());
                String text = t.getText().replace("\u00A0", "<NBSP>");
                System.out.printf("  %-15s -> '%s'%n", name, text);
            }
        }
        System.out.println("=== End token dump ===");

        DanexParser parser = new DanexParser(tokens);
        ParseTree tree = parser.compilationUnit();

        AstBuilder builder = new AstBuilder();
        AstBuildingVisitor visitor = new AstBuildingVisitor(builder);
        @SuppressWarnings("unchecked")
        List<Decl> programDecls = (List<Decl>) visitor.visit(tree);

        Interpreter interpreter = new Interpreter();
        interpreter.interpretDecls(programDecls);

        // ✅ Run main() automatically
        DanexCallable mainFn = (DanexCallable) interpreter.getGlobals().get("main");
        mainFn.call(interpreter, new java.util.ArrayList<>());
    }
}
