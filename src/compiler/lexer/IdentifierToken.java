package compiler.lexer;

import java.util.HashMap;
import java.util.Map;

public class IdentifierToken extends TypedToken<String> {
  private static Map<String, compiler.lexer.IdentifierToken> identityMap = new HashMap<>();

  public IdentifierToken(String s) {
    super(s, 2);
  }

  @Override
  protected String parse(String str) {
    return str;
  }

  public static compiler.lexer.IdentifierToken build(String string) {
    return identityMap.computeIfAbsent(string, compiler.lexer.IdentifierToken::new);
  }
}
