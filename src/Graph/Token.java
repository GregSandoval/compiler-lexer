package Graph;

import java.util.HashMap;
import java.util.Map;

public abstract class Token {
  public final String str;

  protected Token(String str) {
    this.str = str;
  }

  @Override
  public String toString() {
    return "(Tok: <ID#> line= <line, pos> str = \"" + this.str + "\")";
  }

  public final static class SymbolToken extends Token {
    public static final SymbolToken COMMA = new SymbolToken(",");
    public static final SymbolToken SEMI_COLON = new SymbolToken(";");
    public static final SymbolToken LEFT_BRACE = new SymbolToken("{");
    public static final SymbolToken RIGHT_BRACE = new SymbolToken("}");
    public static final SymbolToken LEFT_BRACKET = new SymbolToken("[");
    public static final SymbolToken RIGHT_BRACKET = new SymbolToken("]");
    public static final SymbolToken LEFT_PAREN = new SymbolToken("(");
    public static final SymbolToken RIGHT_PAREN = new SymbolToken(")");
    public static final SymbolToken CARET = new SymbolToken("^");
    public static final SymbolToken COLON = new SymbolToken(":");
    public static final SymbolToken PERIOD = new SymbolToken(".");
    public static final SymbolToken FORWARD_SLASH = new SymbolToken("/");

    private SymbolToken(String str) {
      super(str);
    }
  }

  public static final class OperatorToken extends Token {
    public static final OperatorToken LESS_THAN = new OperatorToken("<");
    public static final OperatorToken GREATER_THAN = new OperatorToken(">");
    public static final OperatorToken ASTERISK = new OperatorToken("*");
    public static final OperatorToken EQUAL = new OperatorToken("=");
    public static final OperatorToken MINUS = new OperatorToken("-");
    public static final OperatorToken PLUS = new OperatorToken("+");
    public static final OperatorToken AND = new OperatorToken("&");
    public static final OperatorToken EXCLAMATION_MARK = new OperatorToken("!");
    public static final OperatorToken OP_ARROW = new OperatorToken("->");
    public static final OperatorToken OP_EQUAL = new OperatorToken("==");
    public static final OperatorToken OP_NEGATE = new OperatorToken("!=");
    public static final OperatorToken OP_LESS_THAN = new OperatorToken("<=");
    public static final OperatorToken OP_GREATER_THAN = new OperatorToken(">=");
    public static final OperatorToken OP_SHIFT_LEFT = new OperatorToken("<<");
    public static final OperatorToken OP_SHIFT_RIGHT = new OperatorToken(">>");

    protected OperatorToken(String str) {
      super(str);
    }
  }

  public static final class KeywordToken extends Token {
    protected KeywordToken(String str) {
      super(str);
    }
  }

  public static final class StringToken extends Token {
    private static Map<String, StringToken> identityMap = new HashMap<>();

    private StringToken(String str) {
      super(str.replace("\"", ""));
    }

    public static StringToken build(String string) {
      return identityMap.computeIfAbsent(string, StringToken::new);
    }
  }

  public static final class WhitespaceToken extends Token {
    protected WhitespaceToken(String str) {
      super(str);
    }
  }

  public static final class CommentToken extends Token {
    protected CommentToken(String str) {
      super(str);
    }
  }

  public static abstract class TypedToken<Value> extends Token {
    public final Value value;

    protected TypedToken(String str) {
      super(str);
      this.value = parse(str);
    }

    protected abstract Value parse(String str);
  }

  public static final class IdentifierToken extends TypedToken<String> {
    private static Map<String, IdentifierToken> identityMap = new HashMap<>();

    public IdentifierToken(String s) {
      super(s);
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
      super(str);
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
      return "(Tok: <ID#> line= <line, pos> str = \"" + this.str + "\" flo= " + this.value + ")";
    }
  }

  public static final class IntegerToken extends TypedToken<Integer> {
    private static Map<String, IntegerToken> identityMap = new HashMap<>();

    protected IntegerToken(String str) {
      super(str);
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
      return "(Tok: <ID#> line= <line, pos> str = \"" + this.str + "\" int= " + this.value + ")";
    }
  }
}


