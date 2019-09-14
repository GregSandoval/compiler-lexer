package DFAGraph;

import utils.TextCursor;

import java.util.ArrayList;

import static DFAGraph.DFANode.*;
import static DFAGraph.DFATransitionPredicates.*;

public class LexerMain {
  public static void main(String[] args) {
    buildGraph();

    final var text = " prog main { // Find the hypotenuse of a right triangle.\n" +
      "      print( \"Input legs> \" );\n" +
      "      var a = input( int );\n" +
      "      var b = input( int );\n" +
      "      print( \"Hypotenuse= \", ( a * a + b * b ) ^ 0.5 );\n" +
      "    }";

    var CURRENT_STATE = START;
    var tokens = new ArrayList<String>();
    var currentToken = new StringBuilder();
    System.out.println("Parsing: \n" + text + "\n");

    var cursor = new TextCursor(text);
    var line = 1;
    for (var letter : cursor) {
      var GOTO = CURRENT_STATE.ON(letter);

      if (CURRENT_STATE == COMMENT && CURRENT_STATE.IS_NOT_FINAL_STATE && GOTO == ERROR) {
        System.out.println("Ignored comment: " + currentToken + "\n");
        currentToken.setLength(0);
        CURRENT_STATE = START;
        cursor.rewind();
        continue;
      }

      if (CURRENT_STATE == WHITESPACE && CURRENT_STATE.IS_NOT_FINAL_STATE && GOTO == ERROR) {
        System.out.println("Ignored whitespace\n");
        currentToken.setLength(0);
        cursor.rewind();
        CURRENT_STATE = START;
        continue;
      }

      if (CURRENT_STATE.IS_NOT_FINAL_STATE && GOTO == ERROR) {
        log(CURRENT_STATE, letter, GOTO);
        currentToken.append(letter);
        var unknown = currentToken.toString().replace("\t", "\\t").replace("\n", "\\n");
        System.out.println("Unknown token: " + unknown + "\n");
        currentToken.setLength(0);
        break;
      }

      if (CURRENT_STATE.IS_FINAL_STATE && GOTO == ERROR) {
        System.out.println("Accepted token: " + currentToken + "\nLine Number: " + line + "\n");
        tokens.add(currentToken.toString());
        currentToken.setLength(0);
        cursor.rewind();
        CURRENT_STATE = START;
        continue;
      }

      log(CURRENT_STATE, letter, GOTO);
      if (CURRENT_STATE != START || GOTO != START) {
        currentToken.append(letter);
        if (letter == '\n')
          line++;
      }

      CURRENT_STATE = GOTO;

      if (!cursor.hasNext() && CURRENT_STATE.IS_FINAL_STATE) {
        System.out.println("Accepted token: " + currentToken + "\n");
        tokens.add(currentToken.toString());
        currentToken.setLength(0);
      }
    }

    System.out.printf("\nAccepted %s tokens: \n", tokens.size());
    for (var i = 0; i < tokens.size(); i++) {
      System.out.printf("Token %d: ", i + 1);
      System.out.println(tokens.get(i).replace("\t", "\\t").replace("\n", "\\n"));
    }
  }

  public static void log(DFANode start, Character character, DFANode end) {
    String letter;

    switch (character) {
      case '\t':
        letter = "\\t";
        break;
      case '\n':
        letter = "\\n";
        break;
      default:
        letter = String.valueOf(character);
    }
    System.out.printf("%-15s %-5s %-1s\n", start, "-(" + letter + ")->", end);
  }

  public static void buildGraph() {
    // WHITESPACE STATE
    START
      .ON(IS_WHITESPACE.or(NEWLINE))
      .GOTO(WHITESPACE);

    WHITESPACE
      .ON(IS_WHITESPACE.or(NEWLINE))
      .GOTO(WHITESPACE);


    // IDENTIFIER
    START
      .ON(LETTER.or(IS_UNDERSCORE))
      .GOTO(IDENTIFIER);

    IDENTIFIER
      .ON(IS_UNDERSCORE.or(DIGIT).or(LETTER))
      .GOTO(IDENTIFIER);


    // INTEGER
    START
      .ON(IS_PLUS.or(IS_MINUS).or(DIGIT))
      .GOTO(INTEGER);

    INTEGER
      .ON(DIGIT)
      .GOTO(INTEGER);


    // FLOAT
    INTEGER
      .ON(IS_PERIOD)
      .GOTO(MAYBE_FLOAT);

    MAYBE_FLOAT
      .ON(DIGIT)
      .GOTO(FLOAT);

    FLOAT
      .ON(DIGIT)
      .GOTO(FLOAT);


    // STRING
    START
      .ON(IS_QUOTE)
      .GOTO(OPENING_STRING);

    OPENING_STRING
      .ON(ANY.and(IS_QUOTE.negate()))
      .GOTO(STRING_CONTENTS);

    STRING_CONTENTS
      .ON(ANY.and(IS_QUOTE.negate()))
      .GOTO(STRING_CONTENTS);

    STRING_CONTENTS
      .ON(IS_QUOTE)
      .GOTO(CLOSING_STRING);

    // COMMENT
    START
      .ON(IS_FORWARD_SLASH)
      .GOTO(COMMENT);

    COMMENT
      .ON(ANY)
      .GOTO(COMMENT);


    // UNPAIRED DELIMITERS
    START
      .ON(IS_COMMA)
      .GOTO(COMMA);

    START
      .ON(IS_SEMI_COLON)
      .GOTO(SEMI_COLON);


    // PAIRED DELIMITERS
    START
      .ON(IS_LESS_THAN)
      .GOTO(LESS_THAN);

    START
      .ON(IS_GREATER_THAN)
      .GOTO(GREATER_THAN);


    START
      .ON(IS_LEFT_BRACE)
      .GOTO(LEFT_BRACE);

    START
      .ON(IS_RIGHT_BRACE)
      .GOTO(RIGHT_BRACE);


    START
      .ON(IS_LEFT_BRACKET)
      .GOTO(LEFT_BRACKET);

    START
      .ON(IS_RIGHT_BRACKET)
      .GOTO(RIGHT_BRACKET);


    START
      .ON(IS_LEFT_PAREN)
      .GOTO(LEFT_PAREN);

    START
      .ON(IS_RIGHT_PAREN)
      .GOTO(RIGHT_PAREN);

    // OTHER PUNCTUATION
    START
      .ON(IS_ASTERISK)
      .GOTO(ASTERISK);

    START
      .ON(IS_CARET)
      .GOTO(CARET);

    START
      .ON(IS_COLON)
      .GOTO(COLON);

    START
      .ON(IS_PERIOD)
      .GOTO(PERIOD);

    START
      .ON(IS_EQUAL)
      .GOTO(EQUAL);

    START
      .ON(IS_MINUS)
      .GOTO(MINUS);

    START
      .ON(IS_PLUS)
      .GOTO(PLUS);

    START
      .ON(IS_FORWARD_SLASH)
      .GOTO(FORWARD_SLASH);

    START
      .ON(IS_AND)
      .GOTO(AND);

    START
      .ON(IS_AND)
      .GOTO(AND);

    START
      .ON(IS_AND)
      .GOTO(AND);

    START
      .ON(IS_EXCLAMATION_MARK)
      .GOTO(EXCLAMATION_MARK);


    // MULTI CHARACTER OPERATORS
    MINUS
      .ON(IS_GREATER_THAN)
      .GOTO(OP_ARROW);

    EQUAL
      .ON(IS_EQUAL)
      .GOTO(OP_EQUAL);

    EXCLAMATION_MARK
      .ON(IS_EQUAL)
      .GOTO(OP_NEGATE);

    LESS_THAN
      .ON(IS_EQUAL)
      .GOTO(OP_LESS_THAN);

    GREATER_THAN
      .ON(IS_EQUAL)
      .GOTO(OP_GREATER_THAN);

    LESS_THAN
      .ON(IS_LESS_THAN)
      .GOTO(OP_SHIFT_LEFT);

    GREATER_THAN
      .ON(IS_GREATER_THAN)
      .GOTO(OP_SHIFT_RIGHT);

  }
}
