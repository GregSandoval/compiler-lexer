package compiler.a5.lexicon;

import compiler.lexer.FinalState;
import compiler.lexer.LexicalNode;
import compiler.lexer.NonFinalState;
import compiler.lexer.token.*;

import static compiler.a5.lexicon.A5EdgePredicates.*;

public class A5LexiconDFA {
  // DFA Grammar
  public static final LexicalNode START = new NonFinalState("START");
  public static final LexicalNode IDENTIFIER = new FinalState("IDENTIFIER", IdentifierToken::build);

  public static final LexicalNode INTEGER = new FinalState("INTEGER", IntegerToken::build);
  public static final LexicalNode MAYBE_FLOAT = new NonFinalState("MAYBE_FLOAT");
  public static final LexicalNode FLOAT = new FinalState("FLOAT", FloatToken::build);

  public static final LexicalNode OPENING_STRING = new NonFinalState("OPENING_STRING");
  public static final LexicalNode STRING_CONTENTS = new NonFinalState("STRING_CONTENTS");
  public static final LexicalNode CLOSING_STRING = new FinalState("CLOSING_STRING", StringToken::build);

  public static final LexicalNode WHITESPACE = new FinalState("WHITESPACE", WhitespaceToken::new);
  public static final LexicalNode COMMENT = new FinalState("COMMENT", CommentToken::new);

  // Unpaired delimiters
  public static final LexicalNode COMMA = new FinalState("COMMA", A5Tokens.COMMA);
  public static final LexicalNode SEMI_COLON = new FinalState("SEMI_COLON", A5Tokens.SEMI_COLON);

  // Paired delimiters
  public static final LexicalNode LESS_THAN = new FinalState("LESS_THAN", A5Tokens.LESS_THAN);
  public static final LexicalNode GREATER_THAN = new FinalState("GREATER_THAN", A5Tokens.GREATER_THAN);

  public static final LexicalNode LEFT_BRACE = new FinalState("LEFT_BRACE", A5Tokens.LEFT_BRACE);
  public static final LexicalNode RIGHT_BRACE = new FinalState("RIGHT_BRACE", A5Tokens.RIGHT_BRACE);

  public static final LexicalNode LEFT_BRACKET = new FinalState("LEFT_BRACKET", A5Tokens.LEFT_BRACKET);
  public static final LexicalNode RIGHT_BRACKET = new FinalState("RIGHT_BRACKET", A5Tokens.RIGHT_BRACKET);

  public static final LexicalNode LEFT_PAREN = new FinalState("LEFT_PAREN", A5Tokens.LEFT_PAREN);
  public static final LexicalNode RIGHT_PAREN = new FinalState("RIGHT_PAREN", A5Tokens.RIGHT_PAREN);

  // Other punctuation tokens
  public static final LexicalNode ASTERISK = new FinalState("ASTERISK", A5Tokens.ASTERISK);
  public static final LexicalNode CARET = new FinalState("CARET", A5Tokens.CARET);
  public static final LexicalNode COLON = new FinalState("COLON", A5Tokens.COLON);
  public static final LexicalNode PERIOD = new FinalState("PERIOD", A5Tokens.PERIOD);
  public static final LexicalNode EQUAL = new FinalState("EQUAL", A5Tokens.EQUAL);
  public static final LexicalNode MINUS = new FinalState("MINUS", A5Tokens.MINUS);
  public static final LexicalNode PLUS = new FinalState("PLUS", A5Tokens.PLUS);
  public static final LexicalNode FORWARD_SLASH = new FinalState("FORWARD_SLASH", A5Tokens.FORWARD_SLASH);
  public static final LexicalNode AND = new FinalState("AND", A5Tokens.AND);
  public static final LexicalNode EXCLAMATION_MARK = new FinalState("EXCLAMATION_MARK", A5Tokens.EXCLAMATION_MARK);

  // Multi character operators
  public static final LexicalNode OP_ARROW = new FinalState("OP_ARROW", A5Tokens.OP_ARROW);
  public static final LexicalNode OP_EQUAL = new FinalState("OP_EQUAL", A5Tokens.OP_EQUAL);
  public static final LexicalNode OP_NEGATE = new FinalState("OP_NEGATE", A5Tokens.OP_NEGATE);
  public static final LexicalNode OP_LESS_THAN = new FinalState("OP_LESS_THAN", A5Tokens.OP_LESS_THAN);
  public static final LexicalNode OP_GREATER_THAN = new FinalState("OP_GREATER_THAN", A5Tokens.OP_GREATER_THAN);
  public static final LexicalNode OP_SHIFT_LEFT = new FinalState("OP_SHIFT_LEFT", A5Tokens.OP_SHIFT_LEFT);
  public static final LexicalNode OP_SHIFT_RIGHT = new FinalState("OP_SHIFT_RIGHT", A5Tokens.OP_SHIFT_RIGHT);

  static {
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

  private A5LexiconDFA() {
  }
}
