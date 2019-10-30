package compiler.lexer.token;

public class IdentifierToken extends TypedToken<String> {
  private static final IdentifierToken sentinel = new IdentifierToken("Sentinel");

  public IdentifierToken(String s) {
    super(s, 2);
  }

  @Override
  protected String parse(String str) {
    return str;
  }

  public static IdentifierToken getSentinel() {
    return sentinel;
  }

}
