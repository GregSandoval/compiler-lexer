package compiler.lexer;

public final class CommentToken extends Token {
  public CommentToken(String str) {
    super(str, 1);
  }
}
