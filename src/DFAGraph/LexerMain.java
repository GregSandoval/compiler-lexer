package DFAGraph;

import java.util.ArrayList;

import static DFAGraph.DFANode.*;
import static DFAGraph.DFATransitionPredicates.*;

public class LexerMain {
  public static void main(String[] args) {
    var graph = new DFAGraph();
    buildGraph(graph);

    final var text = "   \"ad\" is this working 123 ?";

    var current = START;
    var tokens = new ArrayList<String>();
    var currentToken = new StringBuilder();
    System.out.println("Parsing: " + text);
    for (var letter : text.toCharArray()) {
      var next = graph.transition(current, letter);
      log(current, letter, next);
      current = next;

      if (next == WHITESPACE) {
        if (currentToken.length() != 0) {
          tokens.add(currentToken.toString());
          System.out.println("Accepted token: " + currentToken);
        }
        currentToken.setLength(0);
      } else if (next == ERROR) {
        currentToken.setLength(0);
        current = START;
      } else {
        currentToken.append(letter);
      }
    }

    if (currentToken.length() != 0) {
      tokens.add(currentToken.toString());
      System.out.println("Accepted token: " + currentToken);
    }
    System.out.println("Accepted tokens: " + tokens);
  }

  public static void log(DFANode start, Character character, DFANode end) {
    System.out.printf("%-15s %-5s %-1s\n", start, "-(" + character + ")->", end);
  }

  public static void buildGraph(DFAGraph graph) {
    // IDENTIFIER
    graph.addTransition(START, LETTER.or(UNDERSCORE), IDENTIFIER);
    graph.addTransition(IDENTIFIER, UNDERSCORE.or(DIGIT).or(LETTER), IDENTIFIER);
    graph.addTransition(IDENTIFIER, IS_WHITESPACE, WHITESPACE);

    // INTEGER
    graph.addTransition(START, PLUS.or(MINUS).or(DIGIT), INTEGER);
    graph.addTransition(INTEGER, DIGIT, INTEGER);
    graph.addTransition(INTEGER, IS_WHITESPACE, WHITESPACE);

    // FLOAT
    graph.addTransition(INTEGER, PERIOD, FLOAT);
    graph.addTransition(FLOAT, DIGIT, FLOAT);
    graph.addTransition(FLOAT, IS_WHITESPACE, WHITESPACE);

    // STRING
    graph.addTransition(START, QUOTE, OPENING_STRING);
    graph.addTransition(OPENING_STRING, ANY.and(QUOTE.negate()), STRING_CONTENTS);
    graph.addTransition(STRING_CONTENTS, ANY.and(QUOTE.negate()), STRING_CONTENTS);
    graph.addTransition(STRING_CONTENTS, QUOTE, CLOSING_STRING);
    graph.addTransition(CLOSING_STRING, IS_WHITESPACE, WHITESPACE);

    // WHITESPACE
    graph.addTransition(START, IS_WHITESPACE, WHITESPACE);
    graph.addTransition(WHITESPACE, IS_WHITESPACE, WHITESPACE);
    graph.addTransition(WHITESPACE, LETTER.or(UNDERSCORE), IDENTIFIER);
    graph.addTransition(WHITESPACE, PLUS.or(MINUS).or(DIGIT), INTEGER);
    graph.addTransition(WHITESPACE, QUOTE, OPENING_STRING);
  }
}
