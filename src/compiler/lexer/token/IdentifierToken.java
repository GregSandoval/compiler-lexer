package compiler.lexer.token;

public class IdentifierToken extends TypedToken<String> {

  public IdentifierToken(String s) {
    super(s, 2);
  }

  @Override
  protected String parse(String str) {
    return str;
  }

}
