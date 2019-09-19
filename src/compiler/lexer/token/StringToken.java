package compiler.lexer.token;

import java.util.HashMap;
import java.util.Map;

public final class StringToken extends Token {
  private static Map<String, StringToken> identityMap = new HashMap<>();

  public StringToken(String str) {
    super(str.replace("\"", ""), 5);
  }

  public static StringToken build(String string) {
    return identityMap.computeIfAbsent(string, StringToken::new);
  }
}
