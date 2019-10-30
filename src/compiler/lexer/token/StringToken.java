package compiler.lexer.token;

public final class StringToken extends Token {
  private static final StringToken sentinel = new StringToken("");

  public StringToken(String str) {
    super(str.replace("\"", ""), 5);
  }

  public static StringToken getSentinel() {
    return sentinel;
  }
}
