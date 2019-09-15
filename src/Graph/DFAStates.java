package Graph;

import Graph.Token.*;

import static Graph.Node.FINAL_STATE;
import static Graph.Node.NON_FINAL_STATE;

public class DFAStates {
  // DFA Grammar
  public static final Node FATAL_ERROR = new NON_FINAL_STATE("FATAL_ERROR");
  public static final Node END_OF_TERMINAL = new NON_FINAL_STATE("END_OF_TERMINAL");
  public static final Node NO_TRANSITION_FOUND = new NON_FINAL_STATE("NO_TRANSITION_FOUND");

  public static final Node START = new NON_FINAL_STATE("START");
  public static final Node IDENTIFIER = new FINAL_STATE("IDENTIFIER", IdentifierToken::build);

  public static final Node INTEGER = new FINAL_STATE("INTEGER", Token.IntegerToken::build);
  public static final Node MAYBE_FLOAT = new NON_FINAL_STATE("MAYBE_FLOAT");
  public static final Node FLOAT = new FINAL_STATE("FLOAT", Token.FloatToken::build);

  public static final Node OPENING_STRING = new NON_FINAL_STATE("OPENING_STRING");
  public static final Node STRING_CONTENTS = new NON_FINAL_STATE("STRING_CONTENTS");
  public static final Node CLOSING_STRING = new FINAL_STATE("CLOSING_STRING", StringToken::build);

  public static final Node WHITESPACE = new FINAL_STATE("WHITESPACE", WhitespaceToken::new);
  public static final Node COMMENT = new FINAL_STATE("COMMENT", CommentToken::new);

  // Unpaired delimiters
  public static final Node COMMA = new FINAL_STATE("COMMA", SymbolToken.COMMA);
  public static final Node SEMI_COLON = new FINAL_STATE("SEMI_COLON", SymbolToken.SEMI_COLON);

  // Paired delimiters
  public static final Node LESS_THAN = new FINAL_STATE("LESS_THAN", OperatorToken.LESS_THAN);
  public static final Node GREATER_THAN = new FINAL_STATE("GREATER_THAN", OperatorToken.GREATER_THAN);

  public static final Node LEFT_BRACE = new FINAL_STATE("LEFT_BRACE", SymbolToken.LEFT_BRACE);
  public static final Node RIGHT_BRACE = new FINAL_STATE("RIGHT_BRACE", SymbolToken.RIGHT_BRACE);

  public static final Node LEFT_BRACKET = new FINAL_STATE("LEFT_BRACKET", SymbolToken.LEFT_BRACKET);
  public static final Node RIGHT_BRACKET = new FINAL_STATE("RIGHT_BRACKET", SymbolToken.RIGHT_BRACKET);

  public static final Node LEFT_PAREN = new FINAL_STATE("LEFT_PAREN", SymbolToken.LEFT_PAREN);
  public static final Node RIGHT_PAREN = new FINAL_STATE("RIGHT_PAREN", SymbolToken.RIGHT_PAREN);

  // Other punctuation tokens
  public static final Node ASTERISK = new FINAL_STATE("ASTERISK", OperatorToken.ASTERISK);
  public static final Node CARET = new FINAL_STATE("CARET", SymbolToken.CARET);
  public static final Node COLON = new FINAL_STATE("COLON", SymbolToken.COLON);
  public static final Node PERIOD = new FINAL_STATE("PERIOD", SymbolToken.PERIOD);
  public static final Node EQUAL = new FINAL_STATE("EQUAL", OperatorToken.EQUAL);
  public static final Node MINUS = new FINAL_STATE("MINUS", OperatorToken.MINUS);
  public static final Node PLUS = new FINAL_STATE("PLUS", OperatorToken.PLUS);
  public static final Node FORWARD_SLASH = new FINAL_STATE("FORWARD_SLASH", SymbolToken.FORWARD_SLASH);
  public static final Node AND = new FINAL_STATE("AND", OperatorToken.AND);
  public static final Node EXCLAMATION_MARK = new FINAL_STATE("EXCLAMATION_MARK", OperatorToken.EXCLAMATION_MARK);

  // Multi character operators
  public static final Node OP_ARROW = new FINAL_STATE("OP_ARROW", OperatorToken.OP_ARROW);
  public static final Node OP_EQUAL = new FINAL_STATE("OP_EQUAL", OperatorToken.OP_EQUAL);
  public static final Node OP_NEGATE = new FINAL_STATE("OP_NEGATE", OperatorToken.OP_NEGATE);
  public static final Node OP_LESS_THAN = new FINAL_STATE("OP_LESS_THAN", OperatorToken.OP_LESS_THAN);
  public static final Node OP_GREATER_THAN = new FINAL_STATE("OP_GREATER_THAN", OperatorToken.OP_GREATER_THAN);
  public static final Node OP_SHIFT_LEFT = new FINAL_STATE("OP_SHIFT_LEFT", OperatorToken.OP_SHIFT_LEFT);
  public static final Node OP_SHIFT_RIGHT = new FINAL_STATE("OP_SHIFT_RIGHT", OperatorToken.OP_SHIFT_RIGHT);

  private DFAStates() {
  }
}
