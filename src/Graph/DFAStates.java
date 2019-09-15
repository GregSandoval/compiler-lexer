package Graph;

import Graph.Token.*;

import static Graph.Node.FINAL_STATE;
import static Graph.Node.NON_FINAL_STATE;
import static Graph.Token.*;

public class DFAStates {
  // DFA Grammar
  public static final Node FATAL_ERROR = new NON_FINAL_STATE("FATAL_ERROR");
  public static final Node END_OF_TERMINAL = new NON_FINAL_STATE("END_OF_TERMINAL");
  public static final Node NO_TRANSITION_FOUND = new NON_FINAL_STATE("NO_TRANSITION_FOUND");

  public static final Node START = new NON_FINAL_STATE("START");
  public static final Node IDENTIFIER = new FINAL_STATE("IDENTIFIER", IdentifierToken::build);

  public static final Node INTEGER = new FINAL_STATE("INTEGER", IntegerToken::build);
  public static final Node MAYBE_FLOAT = new NON_FINAL_STATE("MAYBE_FLOAT");
  public static final Node FLOAT = new FINAL_STATE("FLOAT", FloatToken::build);

  public static final Node OPENING_STRING = new NON_FINAL_STATE("OPENING_STRING");
  public static final Node STRING_CONTENTS = new NON_FINAL_STATE("STRING_CONTENTS");
  public static final Node CLOSING_STRING = new FINAL_STATE("CLOSING_STRING", StringToken::build);

  public static final Node WHITESPACE = new FINAL_STATE("WHITESPACE", WhitespaceToken::new);
  public static final Node COMMENT = new FINAL_STATE("COMMENT", CommentToken::new);

  // Unpaired delimiters
  public static final Node COMMA = new FINAL_STATE("COMMA", Tokens.COMMA);
  public static final Node SEMI_COLON = new FINAL_STATE("SEMI_COLON", Tokens.SEMI_COLON);

  // Paired delimiters
  public static final Node LESS_THAN = new FINAL_STATE("LESS_THAN", Tokens.LESS_THAN);
  public static final Node GREATER_THAN = new FINAL_STATE("GREATER_THAN", Tokens.GREATER_THAN);

  public static final Node LEFT_BRACE = new FINAL_STATE("LEFT_BRACE", Tokens.LEFT_BRACE);
  public static final Node RIGHT_BRACE = new FINAL_STATE("RIGHT_BRACE", Tokens.RIGHT_BRACE);

  public static final Node LEFT_BRACKET = new FINAL_STATE("LEFT_BRACKET", Tokens.LEFT_BRACKET);
  public static final Node RIGHT_BRACKET = new FINAL_STATE("RIGHT_BRACKET", Tokens.RIGHT_BRACKET);

  public static final Node LEFT_PAREN = new FINAL_STATE("LEFT_PAREN", Tokens.LEFT_PAREN);
  public static final Node RIGHT_PAREN = new FINAL_STATE("RIGHT_PAREN", Tokens.RIGHT_PAREN);

  // Other punctuation tokens
  public static final Node ASTERISK = new FINAL_STATE("ASTERISK", Tokens.ASTERISK);
  public static final Node CARET = new FINAL_STATE("CARET", Tokens.CARET);
  public static final Node COLON = new FINAL_STATE("COLON", Tokens.COLON);
  public static final Node PERIOD = new FINAL_STATE("PERIOD", Tokens.PERIOD);
  public static final Node EQUAL = new FINAL_STATE("EQUAL", Tokens.EQUAL);
  public static final Node MINUS = new FINAL_STATE("MINUS", Tokens.MINUS);
  public static final Node PLUS = new FINAL_STATE("PLUS", Tokens.PLUS);
  public static final Node FORWARD_SLASH = new FINAL_STATE("FORWARD_SLASH", Tokens.FORWARD_SLASH);
  public static final Node AND = new FINAL_STATE("AND", Tokens.AND);
  public static final Node EXCLAMATION_MARK = new FINAL_STATE("EXCLAMATION_MARK", Tokens.EXCLAMATION_MARK);

  // Multi character operators
  public static final Node OP_ARROW = new FINAL_STATE("OP_ARROW", Tokens.OP_ARROW);
  public static final Node OP_EQUAL = new FINAL_STATE("OP_EQUAL", Tokens.OP_EQUAL);
  public static final Node OP_NEGATE = new FINAL_STATE("OP_NEGATE", Tokens.OP_NEGATE);
  public static final Node OP_LESS_THAN = new FINAL_STATE("OP_LESS_THAN", Tokens.OP_LESS_THAN);
  public static final Node OP_GREATER_THAN = new FINAL_STATE("OP_GREATER_THAN", Tokens.OP_GREATER_THAN);
  public static final Node OP_SHIFT_LEFT = new FINAL_STATE("OP_SHIFT_LEFT", Tokens.OP_SHIFT_LEFT);
  public static final Node OP_SHIFT_RIGHT = new FINAL_STATE("OP_SHIFT_RIGHT", Tokens.OP_SHIFT_RIGHT);

  private DFAStates() {
  }
}
