package Graph;

import static Graph.Node.FINAL_STATE;
import static Graph.Node.NON_FINAL_STATE;

public class DFAStates {
  // DFA Grammar
  public static final Node FATAL_ERROR = new NON_FINAL_STATE();
  public static final Node END_OF_TERMINAL = new NON_FINAL_STATE();
  public static final Node NO_TRANSITION_FOUND = new NON_FINAL_STATE();

  public static final Node START = new NON_FINAL_STATE();
  public static final Node IDENTIFIER = new FINAL_STATE<>(IdentifierToken::new);
  public static final Node INTEGER = new FINAL_STATE<>(IntegerToken::new);
  public static final Node MAYBE_FLOAT = new NON_FINAL_STATE();
  public static final Node FLOAT = new FINAL_STATE<>(FloatToken::new);

  public static final Node OPENING_STRING = new NON_FINAL_STATE();
  public static final Node STRING_CONTENTS = new NON_FINAL_STATE();
  public static final Node CLOSING_STRING = new FINAL_STATE<>(StringToken::new);

  public static final Node WHITESPACE = new FINAL_STATE<>(WhitespaceToken::new);
  public static final Node COMMENT = new FINAL_STATE<>(CommentToken::new);

  // Unpaired delimiters
  public static final Node COMMA = new FINAL_STATE<>(SymbolToken::new);
  public static final Node SEMI_COLON = new FINAL_STATE<>(SymbolToken::new);

  // Paired delimiters
  public static final Node LESS_THAN = new FINAL_STATE<>(SymbolToken::new);
  public static final Node GREATER_THAN = new FINAL_STATE<>(SymbolToken::new);

  public static final Node LEFT_BRACE = new FINAL_STATE<>(SymbolToken::new);
  public static final Node RIGHT_BRACE = new FINAL_STATE<>(SymbolToken::new);

  public static final Node LEFT_BRACKET = new FINAL_STATE<>(SymbolToken::new);
  public static final Node RIGHT_BRACKET = new FINAL_STATE<>(SymbolToken::new);

  public static final Node LEFT_PAREN = new FINAL_STATE<>(SymbolToken::new);
  public static final Node RIGHT_PAREN = new FINAL_STATE<>(SymbolToken::new);

  // Other punctuation tokens
  public static final Node ASTERISK = new FINAL_STATE<>(OperatorToken::new);
  public static final Node CARET = new FINAL_STATE<>(SymbolToken::new);
  public static final Node COLON = new FINAL_STATE<>(SymbolToken::new);
  public static final Node PERIOD = new FINAL_STATE<>(SymbolToken::new);
  public static final Node EQUAL = new FINAL_STATE<>(OperatorToken::new);
  public static final Node MINUS = new FINAL_STATE<>(OperatorToken::new);
  public static final Node PLUS = new FINAL_STATE<>(OperatorToken::new);
  public static final Node FORWARD_SLASH = new FINAL_STATE<>(SymbolToken::new);
  public static final Node AND = new FINAL_STATE<>(OperatorToken::new);
  public static final Node EXCLAMATION_MARK = new FINAL_STATE<>(OperatorToken::new);

  // Multi character operators
  public static final Node OP_ARROW = new FINAL_STATE<>(OperatorToken::new);
  public static final Node OP_EQUAL = new FINAL_STATE<>(OperatorToken::new);
  public static final Node OP_NEGATE = new FINAL_STATE<>(OperatorToken::new);
  public static final Node OP_LESS_THAN = new FINAL_STATE<>(OperatorToken::new);
  public static final Node OP_GREATER_THAN = new FINAL_STATE<>(OperatorToken::new);
  public static final Node OP_SHIFT_LEFT = new FINAL_STATE<>(OperatorToken::new);
  public static final Node OP_SHIFT_RIGHT = new FINAL_STATE<>(OperatorToken::new);

  private DFAStates() {
  }
}
