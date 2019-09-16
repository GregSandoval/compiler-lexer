package compiler.lexer;

import java.util.HashMap;
import java.util.Map;

public final class KeywordToken extends Token {
  private static Map<String, compiler.lexer.KeywordToken> identityMap = new HashMap<>();

  private KeywordToken(String str) {
    super(str);
  }

  public static compiler.lexer.KeywordToken build(String string) {
    return identityMap.computeIfAbsent(string, compiler.lexer.KeywordToken::new);
  }

  public static Token get(Token identifier) {
    var cached = identityMap.get(identifier.str);
    return (cached == null) ? identifier : cached;
  }
}
