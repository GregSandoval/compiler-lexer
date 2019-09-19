package compiler;

import compiler.a5.lexicon.A5LexiconDFA;
import compiler.graph.Node;
import compiler.lexer.LexerBuilder;
import compiler.lexer.token.Token;
import compiler.utils.TextCursor;

import java.util.Scanner;
import java.util.regex.Pattern;

import static compiler.lexer.NonFinalState.END_OF_TERMINAL;
import static compiler.utils.StringUtils.escape;

public class Main {

  public static void main(String[] args) {
    // Read all input;
    final var scanner = new Scanner(System.in).useDelimiter(Pattern.compile("$"));
    final var text = scanner.hasNext() ? scanner.next() : "";

    // System.out.println("Parsing text: \n" + text + "\n");

    final var lexer = new LexerBuilder()
      // .onTransition(Main::logTransition)
      // .onTokenCreated(Main::logAcceptedToken)
      .onUnknownTokenFound(Main::logUnknownToken)
      .setStartState(A5LexiconDFA.START)
      .createLexer();

    final var terminals = lexer.analyze(text);

    // System.out.printf("\nAccepted %s tokens: \n", terminals.size());
    terminals.forEach(System.out::println);
  }

  private static void logTransition(Node start, Character character, Node end) {
    if (end != END_OF_TERMINAL)
      System.out.printf("%-15s = %-4s=> %-15s\n", start, "'" + escape(character) + "'", end);
  }

  private static void logAcceptedToken(Node start, Node end, Token token) {
    System.out.println("Accepted token value: \"" + escape(token.getValue()) + "\"\n");
  }

  private static void logUnknownToken(String unknownToken, TextCursor cursor) {
    final var line = cursor.getCursorLineNumber();
    final var pos = cursor.getCursorLinePosition() - unknownToken.length();

    System.out.printf("Error occurred on line %s, position %s; Unexpected symbol\n", line, pos);
    System.out.println(cursor.getCurrentLineOfText());

    final var builder = new StringBuilder();
    while(builder.length() <= pos - 1){
      builder.append(' ');
    }
    builder.append('^');
    System.out.println(builder.toString());
    System.exit(0);
  }
}
