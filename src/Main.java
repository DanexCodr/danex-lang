package danex;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
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
