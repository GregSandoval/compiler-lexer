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

  public final static class SymbolToken extends Token {
    public SymbolToken(String str) {
      super(str);
    }
  }

  public final static class IDOffsetToken extends Token {
    public IDOffsetToken() {
      super("This should not be printed; Error!");
    }
  }

  public static final class OperatorToken extends Token {

    public OperatorToken(String str) {
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
    public WhitespaceToken(String str) {
      super(str, 100);
    }
  }

  public static final class CommentToken extends Token {
    public CommentToken(String str) {
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


