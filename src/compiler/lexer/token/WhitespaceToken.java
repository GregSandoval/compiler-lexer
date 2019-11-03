package compiler.lexer.token;

import compiler.parser.TokenVisitor;

public final class WhitespaceToken extends Token {
  public WhitespaceToken(String str) {
    super(str, 100);
  }

  @Override
  public void accept(TokenVisitor visitor) {
    visitor.visit(this);
  }
}
