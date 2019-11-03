package compiler.lexer.token;

import compiler.parser.TokenVisitor;

public class CommentToken extends Token {

  public CommentToken(String str) {
    super(str, 1);
  }

  @Override
  public void accept(TokenVisitor visitor) {
    visitor.visit(this);
  }
}
