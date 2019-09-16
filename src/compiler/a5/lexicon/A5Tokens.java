package compiler.a5.lexicon;

import static compiler.graph.Token.*;

public class A5Tokens {
  // Unpaired delimiters
  public static final SymbolToken COMMA = new SymbolToken(",");
  public static final SymbolToken SEMI_COLON = new SymbolToken(";");

  // Padding for IDs
  private static final IDOffsetToken SKIP_ID_8 = new IDOffsetToken();
  private static final IDOffsetToken SKIP_ID_9 = new IDOffsetToken();

  // Keywords
  public static final KeywordToken PROG = KeywordToken.build("prog");
  public static final KeywordToken MAIN = KeywordToken.build("main");
  public static final KeywordToken FCN = KeywordToken.build("fcn");
  public static final KeywordToken CLASS = KeywordToken.build("class");
  private static final IDOffsetToken SKIP_ID_14 = new IDOffsetToken();
  public static final KeywordToken FLOAT = KeywordToken.build("float");
  public static final KeywordToken INT = KeywordToken.build("int");
  public static final KeywordToken STRING = KeywordToken.build("string");
  public static final KeywordToken IF = KeywordToken.build("if");
  public static final KeywordToken ELSEIF = KeywordToken.build("elseif");
  public static final KeywordToken ELSE = KeywordToken.build("else");
  public static final KeywordToken WHILE = KeywordToken.build("while");
  public static final KeywordToken INPUT = KeywordToken.build("input");
  public static final KeywordToken PRINT = KeywordToken.build("print");
  public static final KeywordToken NEW = KeywordToken.build("new");
  public static final KeywordToken RETURN = KeywordToken.build("return");
  public static final KeywordToken VAR = KeywordToken.build("var");

  // Padding for IDs
  private static final IDOffsetToken SKIP_ID_27 = new IDOffsetToken();
  private static final IDOffsetToken SKIP_ID_28 = new IDOffsetToken();
  private static final IDOffsetToken SKIP_ID_29 = new IDOffsetToken();
  private static final IDOffsetToken SKIP_ID_30 = new IDOffsetToken();

  // Paired delimiters
  public static final OperatorToken LESS_THAN = new OperatorToken("<");
  public static final OperatorToken GREATER_THAN = new OperatorToken(">");
  public static final SymbolToken LEFT_BRACE = new SymbolToken("{");
  public static final SymbolToken RIGHT_BRACE = new SymbolToken("}");
  public static final SymbolToken LEFT_BRACKET = new SymbolToken("[");
  public static final SymbolToken RIGHT_BRACKET = new SymbolToken("]");
  public static final SymbolToken LEFT_PAREN = new SymbolToken("(");
  public static final SymbolToken RIGHT_PAREN = new SymbolToken(")");

  // Padding for IDs
  private static final IDOffsetToken SKIP_ID_39 = new IDOffsetToken();
  private static final IDOffsetToken SKIP_ID_40 = new IDOffsetToken();

  // Other punctuation
  public static final OperatorToken ASTERISK = new OperatorToken("*");
  public static final SymbolToken CARET = new SymbolToken("^");
  public static final SymbolToken COLON = new SymbolToken(":");
  public static final SymbolToken PERIOD = new SymbolToken(".");
  public static final OperatorToken EQUAL = new OperatorToken("=");
  public static final OperatorToken MINUS = new OperatorToken("-");
  public static final OperatorToken PLUS = new OperatorToken("+");
  public static final SymbolToken FORWARD_SLASH = new SymbolToken("/");
  public static final OperatorToken AND = new OperatorToken("&");

  // Padding for IDs
  private static final IDOffsetToken SKIP_ID_50 = new IDOffsetToken();

  // Multi-char operators
  public static final OperatorToken OP_ARROW = new OperatorToken("->");
  public static final OperatorToken OP_EQUAL = new OperatorToken("==");
  public static final OperatorToken OP_NEGATE = new OperatorToken("!=");
  public static final OperatorToken OP_LESS_THAN = new OperatorToken("<=");
  public static final OperatorToken OP_GREATER_THAN = new OperatorToken(">=");
  public static final OperatorToken OP_SHIFT_LEFT = new OperatorToken("<<");
  public static final OperatorToken OP_SHIFT_RIGHT = new OperatorToken(">>");

  // Not in language, but needed for opcode
  public static final OperatorToken EXCLAMATION_MARK = new OperatorToken("!");
}
