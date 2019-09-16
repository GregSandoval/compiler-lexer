package compiler.lexer;

import java.util.HashMap;
import java.util.Map;

public final class StringToken extends Token {
  private static Map<String, compiler.lexer.StringToken> identityMap = new HashMap<>();

  private StringToken(String str) {
    super(str.replace("\"", ""), 5);
  }

  public static compiler.lexer.StringToken build(String string) {
    return identityMap.computeIfAbsent(string, compiler.lexer.StringToken::new);
  }
}
