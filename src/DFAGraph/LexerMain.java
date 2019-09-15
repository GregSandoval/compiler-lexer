package DFAGraph;

import Graph.Node;
import Graph.Node.FINAL_STATE;
import Graph.Token;
import Graph.Token.CommentToken;
import Graph.Token.WhitespaceToken;
import utils.TextCursor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static DFAGraph.DFATransitionPredicates.*;
import static Graph.DFAStates.*;

public class LexerMain {
  public static void main(String[] args) {
    final var text = " prog main { // Find the hypotenuse of a right triangle.\n" +
      "      print( \"Input legs> \" );\n" +
      "      var a = input( int );\n" +
      "      var b = input( int );\n" +
      "      print( \"Hypotenuse= \", ( a * a + b * b ) ^ -0.5 );\n" +
      "    }\n\n\t  \t \r \f \n  ";

    final var terminals = process(text);

    System.out.printf("Accepted %s tokens: \n", terminals.size());
    for (var i = 0; i < terminals.size(); i++) {
      System.out.printf("Token %d: ", i + 1);
      System.out.println(terminals.get(i));
    }
  }

  public static List<Token> process(String text) {
    buildLexer();
    var CURRENT_STATE = START;
    List<Token> tokens = new ArrayList<>();
    var currentToken = new StringBuilder();
    System.out.println("Parsing: \n" + text + "\n");

    var cursor = new TextCursor(text);
    for (var letter : cursor) {
      var GOTO = CURRENT_STATE.ON(letter);
      log(CURRENT_STATE, letter, GOTO);

      if (GOTO == END_OF_TERMINAL && CURRENT_STATE instanceof FINAL_STATE) {
        tokens.add(((FINAL_STATE) CURRENT_STATE).buildToken(escape(currentToken.toString())));
        currentToken.setLength(0);
        cursor.rewind();
        CURRENT_STATE = START;
        continue;
      }

      if (GOTO == FATAL_ERROR) {
        System.out.println("Unknown token: " + escape(currentToken.toString() + letter) + "\n");
        break;
      }

      currentToken.append(letter);
      CURRENT_STATE = GOTO;
    }

    return tokens
      .stream()
      .filter(terminal -> !(terminal instanceof WhitespaceToken))
      .filter(terminal -> !(terminal instanceof CommentToken))
      .collect(Collectors.toList());
  }

  public static void buildLexer() {
    // WHITESPACE STATE
    START
      .ON(IS_WHITESPACE.or(IS_LINE_SEPARATOR))
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
      .ON(DIGIT)
      .GOTO(INTEGER);

    INTEGER
      .ON(DIGIT)
      .GOTO(INTEGER);

    PLUS
      .ON(DIGIT)
      .GOTO(INTEGER);

    MINUS
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
    FORWARD_SLASH
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

  public static void log(Node start, Character character, Node end) {
    System.out.printf("%-15s %-5s %-1s\n", start, "-(" + escape(String.valueOf(character)) + ")->", end);
  }

  public static String escape(String string) {
    return string.replace("\n", "\\n").replace("\t", "\\t").replace("\r", "\\r").replace("\f", "\\f");
  }

}
