package compiler.lexer.token;

import java.util.HashMap;
import java.util.Map;

public final class KeywordToken extends Token {
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
