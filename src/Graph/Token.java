package Graph;

import java.util.HashMap;
import java.util.Map;

public abstract class Token {
  public final String str;

  protected Token(String str) {
    this.str = str;
  }
}

final class SymbolToken extends Token {
  protected SymbolToken(String str) {
    super(str);
  }
}

final class OperatorToken extends Token {
  protected OperatorToken(String str) {
    super(str);
  }
}

final class KeywordToken extends Token {
  protected KeywordToken(String str) {
    super(str);
  }
}

final class StringToken extends Token {
  protected StringToken(String str) {
    super(str);
  }
}

final class WhitespaceToken extends Token {
  protected WhitespaceToken(String str) {
    super(str);
  }
}

final class CommentToken extends Token {
  protected CommentToken(String str) {
    super(str);
  }
}

abstract class TypedToken<Value> extends Token {
  public final Value value;

  protected TypedToken(String str) {
    super(str);
    this.value = parse(str);
  }

  protected abstract Value parse(String str);
}

final class IdentifierToken extends TypedToken<String> {
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

final class FloatToken extends TypedToken<Float> {
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
}

final class IntegerToken extends TypedToken<Integer> {
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
}
