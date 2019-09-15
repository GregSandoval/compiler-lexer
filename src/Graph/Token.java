package Graph;

import java.util.HashMap;
import java.util.Map;

public abstract class Token {
  private static int UUID = 5;
  public final String str;
  public final int ID;

  protected Token(String str) {
    this.str = str;
    this.ID = ++UUID;
  }

  protected Token(String str, int UUID) {
    this.str = str;
    this.ID = UUID;
  }

  @Override
  public String toString() {
    return "(Tok: " + ID + " line= <line, pos> str = \"" + this.str + "\")";
  }

  public final static class Tokens {
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


    public static final OperatorToken EXCLAMATION_MARK = new OperatorToken("!");
  }

  public final static class SymbolToken extends Token {
    private SymbolToken(String str) {
      super(str);
    }
  }

  private final static class IDOffsetToken extends Token {
    private IDOffsetToken() {
      super("This should not be printed; Error!");
    }
  }

  public static final class OperatorToken extends Token {

    protected OperatorToken(String str) {
      super(str);
    }
  }

  public static final class KeywordToken extends Token {
    private static Map<String, KeywordToken> identityMap = new HashMap<>();

    private KeywordToken(String str) {
      super(str);
    }

    public static KeywordToken build(String string) {
      return identityMap.computeIfAbsent(string, KeywordToken::new);
    }

    public static Token get(Token identifier) {
      var cached = identityMap.get(identifier.str);
      return (cached == null) ? identifier : cached;
    }
  }

  public static final class StringToken extends Token {
    private static Map<String, StringToken> identityMap = new HashMap<>();

    private StringToken(String str) {
      super(str.replace("\"", ""), 5);
    }

    public static StringToken build(String string) {
      return identityMap.computeIfAbsent(string, StringToken::new);
    }
  }

  public static final class WhitespaceToken extends Token {
    protected WhitespaceToken(String str) {
      super(str, 100);
    }
  }

  public static final class CommentToken extends Token {
    protected CommentToken(String str) {
      super(str, 1);
    }
  }

  public static abstract class TypedToken<Value> extends Token {
    public final Value value;

    protected TypedToken(String str, int tokenID) {
      super(str, tokenID);
      this.value = parse(str);
    }

    protected abstract Value parse(String str);
  }

  public static class IdentifierToken extends TypedToken<String> {
    private static Map<String, IdentifierToken> identityMap = new HashMap<>();

    public IdentifierToken(String s) {
      super(s, 2);
    }

    @Override
    protected String parse(String str) {
      return str;
    }

    public static IdentifierToken build(String string) {
      return identityMap.computeIfAbsent(string, IdentifierToken::new);
    }
  }

  public static final class FloatToken extends TypedToken<Float> {
    private static Map<String, FloatToken> identityMap = new HashMap<>();

    protected FloatToken(String str) {
      super(str, 4);
    }

    public static FloatToken build(String string) {
      return identityMap.computeIfAbsent(string, FloatToken::new);
    }

    @Override
    protected Float parse(String str) {
      return Float.parseFloat(str);
    }

    @Override
    public String toString() {
      return "(Tok: " + ID + " line= <line, pos> str = \"" + this.str + "\" flo= " + this.value + ")";
    }
  }

  public static final class IntegerToken extends TypedToken<Integer> {
    private static Map<String, IntegerToken> identityMap = new HashMap<>();

    protected IntegerToken(String str) {
      super(str, 3);
    }

    public static IntegerToken build(String string) {
      return identityMap.computeIfAbsent(string, IntegerToken::new);
    }

    @Override
    protected Integer parse(String str) {
      return Integer.parseInt(str);
    }

    @Override
    public String toString() {
      return "(Tok: " + ID + " line= <line, pos> str = \"" + this.str + "\" int= " + this.value + ")";
    }
  }
}


