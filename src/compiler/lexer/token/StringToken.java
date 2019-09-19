package compiler.lexer.token;

public final class StringToken extends Token {

  public StringToken(String str) {
    super(str.replace("\"", ""), 5);
  }

}
