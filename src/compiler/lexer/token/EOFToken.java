package compiler.lexer.token;

import compiler.parser.TokenVisitor;

public class EOFToken extends Token {

  public EOFToken() {
    super("", 0);
  }

  @Override
  public void accept(TokenVisitor visitor) {
    visitor.visit(this);
  }
}
