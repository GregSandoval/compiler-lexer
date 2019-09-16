package compiler.lexer.token;

import java.util.HashMap;
import java.util.Map;

public class IdentifierToken extends TypedToken<String> {
  private static Map<String, IdentifierToken> identityMap = new HashMap<>();

  public IdentifierToken(String s) {
    super(s, 2);
  }

  public static IdentifierToken build(String string) {
    return identityMap.computeIfAbsent(string, IdentifierToken::new);
  }

  @Override
  protected String parse(String str) {
    return str;
  }
}
