package compiler.lexer.token;

import compiler.parser.TokenVisitor;

public abstract class OperatorToken extends Token {

  private OperatorToken(String str, int UUID) {
    super(str, UUID);
  }

  public static class LessThan extends OperatorToken {
    public LessThan() {
      super("<", 31);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class GreaterThan extends OperatorToken {
    public GreaterThan() {
      super(">", 32);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class Asterisk extends OperatorToken {
    public Asterisk() {
      super("*", 41);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class Equal extends OperatorToken {
    public Equal() {
      super("=", 45);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class Minus extends OperatorToken {
    public Minus() {
      super("-", 46);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class Plus extends OperatorToken {
    public Plus() {
      super("+", 47);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class Ampersand extends OperatorToken {
    public Ampersand() {
      super("&", 49);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class Arrow extends OperatorToken {
    public Arrow() {
      super("->", 51);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class EqualEqual extends OperatorToken {
    public EqualEqual() {
      super("==", 52);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class NotEqual extends OperatorToken {
    public NotEqual() {
      super("!=", 53);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class LessThanOrEqual extends OperatorToken {
    public LessThanOrEqual() {
      super("<=", 54);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class GreaterThanOrEqual extends OperatorToken {
    public GreaterThanOrEqual() {
      super(">=", 55);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class BitShiftLeft extends OperatorToken {
    public BitShiftLeft() {
      super("<<", 56);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }

  public static class BitShiftRight extends OperatorToken {
    public BitShiftRight() {
      super(">>", 57);
    }

    @Override
    public void accept(TokenVisitor visitor) {
      visitor.visit(this);
    }
  }
}
