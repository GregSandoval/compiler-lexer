package compiler.lexer.token;

import java.util.HashMap;
import java.util.Map;

public final class KeywordToken extends Token {
  private static final Map<String, Integer> keywords = new HashMap<>();

  static {
    keywords.put("prog", 10);
    keywords.put("main", 11);
    keywords.put("fcn", 12);
    keywords.put("class", 13);
    keywords.put("float", 15);
    keywords.put("int", 16);
    keywords.put("string", 17);
    keywords.put("if", 18);
    keywords.put("elseif", 19);
    keywords.put("else", 20);
    keywords.put("while", 21);
    keywords.put("input", 22);
    keywords.put("print", 23);
    keywords.put("new", 24);
    keywords.put("return", 25);
    keywords.put("var", 2);
  }

  private KeywordToken(String str, int UUID) {
    super(str, UUID);
  }

  public static Token get(Token identifier) {
    final var tokenValue = identifier.getValue();
    if (keywords.containsKey(tokenValue)) {
      final var keyword = new KeywordToken(tokenValue, keywords.get(tokenValue));
      keyword.setLineNumber(identifier.getLineNumber());
      keyword.setLinePosition(identifier.getLinePosition());
      return keyword;
    }
    return identifier;
  }
}
