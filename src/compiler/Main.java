package compiler;

import compiler.a5.lexicon.A5LexiconDFA;
import compiler.graph.Node;
import compiler.lexer.LexerBuilder;
import compiler.lexer.token.Token;

import static compiler.utils.StringUtils.escape;

public class Main {
  private static final String text = " prog main { // Find the hypotenuse of a right triangle.\n" +
    "      print( \"Input legs> \" );\n" +
    "      var a = input( int );\n" +
    "      var b = input( int );\n" +
    "      print( \"Hypotenuse= \", ( a * a + b * b ) ^ -0.5 );\n" +
    "    }\n\n\t  \t \r \f \n  ";

  public static void main(String[] args) {
    System.out.println("Parsing text: \n" + text + "\n");

    final var lexer = new LexerBuilder()
      .onTransition(Main::logTransition)
      .onTokenCreated(Main::logAcceptedToken)
      .setStartState(A5LexiconDFA.START)
      .createLexer();

    final var terminals = lexer.analyze(text);

    System.out.printf("\nAccepted %s tokens: \n", terminals.size());
    terminals.forEach(System.out::println);
  }

  private static void logTransition(Node start, Character character, Node end) {
    System.out.printf("%-15s = %-4s=> %-15s\n", start, "'" + escape(character) + "'", end);
  }

  private static void logAcceptedToken(Node start, Node end, Token token) {
    System.out.println("Accepted token value: \"" + token.str + "\"\n");
  }
}
