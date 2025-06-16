// src/Main.java 
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import danex.grammar.DanexLexer;
import danex.grammar.DanexParser;
import danex.AstBuilder;
import danex.Interpreter;
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

        // Step 1: Lexing & Parsing
        DanexLexer lexer = new DanexLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DanexParser parser = new DanexParser(tokens);

        ParseTree tree = parser.compilationUnit();

        // Step 2: Build AST
        AstBuilder builder = new AstBuilder();
        AstBuildingVisitor visitor = new AstBuildingVisitor(builder);
        @SuppressWarnings("unchecked")
        List<Decl> programDecls = (List<Decl>) visitor.visit(tree);

        // Step 3: Interpret declarations
        Interpreter interpreter = new Interpreter();
        interpreter.interpretDecls(programDecls);

        // If your language allows top-level statements (grammar does not), you could also:
        // interpreter.interpret(statements);
    }
}
