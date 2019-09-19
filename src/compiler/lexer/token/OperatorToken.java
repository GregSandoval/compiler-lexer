package compiler.lexer.token;

public class OperatorToken extends Token {

  private OperatorToken(String str, int UUID) {
    super(str, UUID);
  }

  public static class LessThan extends OperatorToken {
    public LessThan() {
      super("<", 31);
    }
  }

  public static class GreaterThan extends OperatorToken {
    public GreaterThan() {
      super(">", 32);
    }
  }

  public static class Asterisk extends OperatorToken {
    public Asterisk() {
      super("*", 41);
    }
  }

  public static class Equal extends OperatorToken {
    public Equal() {
      super("=", 45);
    }
  }

  public static class Minus extends OperatorToken {
    public Minus() {
      super("-", 46);
    }
  }

  public static class Plus extends OperatorToken {
    public Plus() {
      super("+", 47);
    }
  }

  public static class Ampersand extends OperatorToken {
    public Ampersand() {
      super("&", 49);
    }
  }

  public static class Arrow extends OperatorToken {
    public Arrow() {
      super("->", 51);
    }
  }

  public static class EqualEqual extends OperatorToken {
    public EqualEqual() {
      super("==", 52);
    }
  }

  public static class NotEqual extends OperatorToken {
    public NotEqual() {
      super("!=", 53);
    }
  }

  public static class LessThanOrEqual extends OperatorToken {
    public LessThanOrEqual() {
      super("<=", 54);
    }
  }

  public static class GreaterThanOrEqual extends OperatorToken {
    public GreaterThanOrEqual() {
      super(">=", 55);
    }
  }

  public static class BitShiftLeft extends OperatorToken {
    public BitShiftLeft() {
      super("<<", 56);
    }
  }

  public static class BitShiftRight extends OperatorToken {
    public BitShiftRight() {
      super(">>", 57);
    }
  }
}
