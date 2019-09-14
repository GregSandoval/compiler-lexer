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
    System.out.println("Parsing: \n" + text + "\n");

    var cursor = new TextCursor(text);
    for (var letter : cursor) {
      var next = graph.transition(current, letter);


      if (current == COMMENT && current.state == NON_FINAL_STATE && next == ERROR) {
        System.out.println("Ignored comment: " + currentToken + "\n");
        currentToken.setLength(0);
        current = START;
        continue;
      }

      if (current == WHITESPACE && current.state == NON_FINAL_STATE && next == ERROR) {
        System.out.println("Ignored whitespace\n");
        currentToken.setLength(0);
        cursor.rewind();
        current = START;
        continue;
      }

      if (current.state == NON_FINAL_STATE && next == ERROR) {
        log(current, letter, next);
        currentToken.append(letter);
        var unknown = currentToken.toString().replace("\t", "\\t").replace("\n", "\\n");
        System.out.println("Unknown token: " + unknown + "\n");
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

      log(current, letter, next);
      if (current != START || next != START)
        currentToken.append(letter);

      current = next;

      if (!cursor.hasNext() && current.state == FINAL_STATE) {
        System.out.println("Accepted token: " + currentToken + "\n");
        tokens.add(currentToken.toString());
        currentToken.setLength(0);
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
    graph.start()
      // WHITESPACE STATE
      .transition(START, IS_WHITESPACE.or(NEWLINE), WHITESPACE)
      .transition(WHITESPACE, IS_WHITESPACE.or(NEWLINE), WHITESPACE)

      // IDENTIFIER
      .transition(START, LETTER.or(IS_UNDERSCORE), IDENTIFIER)
      .transition(IDENTIFIER, IS_UNDERSCORE.or(DIGIT).or(LETTER), IDENTIFIER)

      // INTEGER
      .transition(START, IS_PLUS.or(IS_MINUS).or(DIGIT), INTEGER)
      .transition(INTEGER, DIGIT, INTEGER)

      // FLOAT
      .transition(INTEGER, IS_PERIOD, MAYBE_FLOAT)
      .transition(MAYBE_FLOAT, DIGIT, FLOAT)
      .transition(FLOAT, DIGIT, FLOAT)

      // STRING
      .transition(START, IS_QUOTE, OPENING_STRING)
      .transition(OPENING_STRING, ANY.and(IS_QUOTE.negate()), STRING_CONTENTS)
      .transition(STRING_CONTENTS, ANY.and(IS_QUOTE.negate()), STRING_CONTENTS)
      .transition(STRING_CONTENTS, IS_QUOTE, CLOSING_STRING)

      // COMMENT
      .transition(START, IS_FORWARD_SLASH, COMMENT)
      .transition(COMMENT, ANY, COMMENT)

      // UNPAIRED DELIMITERS
      .transition(START, IS_COMMA, COMMA)
      .transition(START, IS_SEMI_COLON, SEMI_COLON)

      // PAIRED DELIMITERS
      .transition(START, IS_LESS_THAN, LESS_THAN)
      .transition(START, IS_GREATER_THAN, GREATER_THAN)

      .transition(START, IS_LEFT_BRACE, LEFT_BRACE)
      .transition(START, IS_RIGHT_BRACE, RIGHT_BRACE)

      .transition(START, IS_LEFT_BRACKET, LEFT_BRACKET)
      .transition(START, IS_RIGHT_BRACKET, RIGHT_BRACKET)

      .transition(START, IS_LEFT_PAREN, LEFT_PAREN)
      .transition(START, IS_RIGHT_PAREN, RIGHT_PAREN)

      // OTHER PUNCTUATION
      .transition(START, IS_ASTERISK, ASTERISK)
      .transition(START, IS_CARET, CARET)
      .transition(START, IS_COLON, COLON)
      .transition(START, IS_PERIOD, PERIOD)
      .transition(START, IS_EQUAL, EQUAL)
      .transition(START, IS_MINUS, MINUS)
      .transition(START, IS_PLUS, PLUS)
      .transition(START, IS_FORWARD_SLASH, FORWARD_SLASH)
      .transition(START, IS_AND, AND)
      .transition(START, IS_AND, AND)
      .transition(START, IS_AND, AND)
      .transition(START, IS_EXCLAMATION_MARK, EXCLAMATION_MARK)

      // MULTI CHARACTER OPERATORS
      .transition(MINUS, IS_GREATER_THAN, OP_ARROW)
      .transition(EQUAL, IS_EQUAL, OP_EQUAL)
      .transition(EXCLAMATION_MARK, IS_EQUAL, OP_NEGATE)
      .transition(LESS_THAN, IS_EQUAL, OP_LESS_THAN)
      .transition(GREATER_THAN, IS_EQUAL, OP_GREATER_THAN)
      .transition(LESS_THAN, IS_LESS_THAN, OP_SHIFT_LEFT)
      .transition(GREATER_THAN, IS_GREATER_THAN, OP_SHIFT_RIGHT)

      // END OF EDGES
      .end();
  }
}
