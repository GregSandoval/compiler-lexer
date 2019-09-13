package DFAGraph;

import java.util.ArrayList;

import static DFAGraph.DFANode.*;
import static DFAGraph.DFATransitionPredicates.*;

public class LexerMain {
  public static void main(String[] args) {
    var graph = new DFAGraph();
    buildGraph(graph);

    final var text = "prog main { print( \"ASCII:\", \" A= \", 65, \" Z= \", 90 ); }\n";

    var current = START;
    var tokens = new ArrayList<String>();
    var currentToken = new StringBuilder();
    System.out.println("Parsing: " + text);
    for (var letter : text.toCharArray()) {
      final var next = graph.transition(current, letter);
      log(current, letter, next);
      current = next;

      if (current == ERROR) {
        current = START;
      }

      if (current == START && currentToken.length() > 0) {
        System.out.println("Accepted token: " + currentToken + "\n");
        tokens.add(currentToken.toString());
        currentToken.setLength(0);
      }


      if (current != START || !IS_WHITESPACE.test(letter)) {
        currentToken.append(letter);
      }
    }
    System.out.printf("Accepted %s tokens: \n", tokens.size());
    for (var i = 0; i < tokens.size(); i++) {
      System.out.printf("Token %d: ", i + 1);
      System.out.println(tokens.get(i).replace("\t", "\\t").replace("\n", "\\n"));
    }
  }

  public static void log(DFANode start, Character character, DFANode end) {
    var letter = (character == '\t') ? "\\t" : character;
    letter = (character == '\n') ? "\\n" : character;

    System.out.printf("%-15s %-5s %-1s\n", start, "-(" + letter + ")->", end);
  }

  public static void buildGraph(DFAGraph graph) {
    // START
    graph.addTransition(START, IS_WHITESPACE, START);

    // IDENTIFIER
    graph.addTransition(START, LETTER.or(UNDERSCORE), IDENTIFIER);
    graph.addTransition(IDENTIFIER, UNDERSCORE.or(DIGIT).or(LETTER), IDENTIFIER);
    graph.addTransition(IDENTIFIER, IS_WHITESPACE, START);

    // INTEGER
    graph.addTransition(START, PLUS.or(MINUS).or(DIGIT), INTEGER);
    graph.addTransition(INTEGER, DIGIT, INTEGER);
    graph.addTransition(INTEGER, IS_WHITESPACE, START);

    // FLOAT
    graph.addTransition(INTEGER, PERIOD, FLOAT);
    graph.addTransition(FLOAT, DIGIT, FLOAT);
    graph.addTransition(FLOAT, IS_WHITESPACE, START);

    // STRING
    graph.addTransition(START, QUOTE, OPENING_STRING);
    graph.addTransition(OPENING_STRING, ANY.and(QUOTE.negate()), STRING_CONTENTS);
    graph.addTransition(STRING_CONTENTS, ANY.and(QUOTE.negate()), STRING_CONTENTS);
    graph.addTransition(STRING_CONTENTS, QUOTE, CLOSING_STRING);
    graph.addTransition(CLOSING_STRING, IS_WHITESPACE, START);

    // PAREN
    graph.addTransition(START, IS_LEFT_BRACKET, LEFT_BRACKET);
    graph.addTransition(LEFT_BRACKET, IS_WHITESPACE, START);

    graph.addTransition(START, IS_COMMA, COMMA);
    graph.addTransition(COMMA, IS_WHITESPACE, START);
  }
}
