package DFAGraph;

import utils.TextCursor;

import java.util.ArrayList;

import static DFAGraph.DFANode.*;
import static DFAGraph.DFAState.FINAL_STATE;
import static DFAGraph.DFAState.NON_FINAL_STATE;
import static DFAGraph.DFATransitionPredicates.*;

public class LexerMain {
  public static void main(String[] args) {
    var graph = new DFAGraph();
    buildGraph(graph);

    final var text = " prog main { // Find the hypotenuse of a right triangle.\n" +
      "      print( \"Input legs> \" );\n" +
      "      var a = input( int );\n" +
      "      var b = input( int );\n" +
      "      print( \"Hypotenuse= \", ( a * a + b * b ) ^ 0.5 );\n" +
      "    }";

    var current = START;
    var tokens = new ArrayList<String>();
    var currentToken = new StringBuilder();
    System.out.println("Parsing: " + text);

    var cursor = new TextCursor(text);
    for (var letter : cursor) {
      var next = graph.transition(current, letter);
      log(current, letter, next);


      if (current == COMMENT && current.state == NON_FINAL_STATE && next == ERROR) {
        System.out.println("Ignored token: " + currentToken + "\n");
        currentToken.setLength(0);
        current = START;
        continue;
      }

      if (current.state == NON_FINAL_STATE && next == ERROR) {
        currentToken.append(letter);
        System.out.println("Unknown token: " + currentToken + "\n");
        currentToken.setLength(0);
        break;
      }

      if (current.state == FINAL_STATE && next == ERROR) {
        System.out.println("Accepted token: " + currentToken + "\n");
        tokens.add(currentToken.toString());
        currentToken.setLength(0);
        cursor.rewind();
        current = START;
        continue;
      }

      if (current != START || next != START)
        currentToken.append(letter);

      current = next;
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
    graph.start()
      // START STATE
      .transition(START, IS_WHITESPACE, START)

      // IDENTIFIER
      .transition(START, LETTER.or(IS_UNDERSCORE), IDENTIFIER)
      .transition(IDENTIFIER, IS_UNDERSCORE.or(DIGIT).or(LETTER), IDENTIFIER)

      // INTEGER
      .transition(START, IS_PLUS.or(IS_MINUS).or(DIGIT), INTEGER)
      .transition(INTEGER, DIGIT, INTEGER)

      // FLOAT
      .transition(INTEGER, IS_PERIOD, FLOAT)
      .transition(FLOAT, DIGIT, FLOAT)

      // STRING
      .transition(START, IS_QUOTE, OPENING_STRING)
      .transition(OPENING_STRING, ANY.and(IS_QUOTE.negate()), STRING_CONTENTS)
      .transition(STRING_CONTENTS, ANY.and(IS_QUOTE.negate()), STRING_CONTENTS)
      .transition(STRING_CONTENTS, IS_QUOTE, CLOSING_STRING)

      // PAREN
      .transition(START, IS_LEFT_BRACE, LEFT_BRACE)

      // COMMA
      .transition(START, IS_COMMA, COMMA)

      // COMMENT
      .transition(START, IS_FORWARD_SLASH, COMMENT)
      .transition(COMMENT, ANY, COMMENT)
      

      // END OF EDGES
      .end();
  }
}
