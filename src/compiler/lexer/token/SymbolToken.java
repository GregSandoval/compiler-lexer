package compiler.lexer.token;

public class SymbolToken extends Token {

  private SymbolToken(String str, int UUID) {
    super(str, UUID);
  }

  public static class Comma extends SymbolToken {
    public Comma() {
      super(",", 6);
    }
  }

  public static class SemiColon extends SymbolToken {
    public SemiColon() {
      super(";", 7);
    }
  }

  public static class LeftBrace extends SymbolToken {
    public LeftBrace() {
      super("{", 33);
    }
  }

  public static class RightBrace extends SymbolToken {
    public RightBrace() {
      super("}", 34);
    }
  }

  public static class LeftBracket extends SymbolToken {
    public LeftBracket() {
      super("[", 35);
    }
  }

  public static class RightBracket extends SymbolToken {
    public RightBracket() {
      super("]", 36);
    }
  }

  public static class LeftParen extends SymbolToken {
    public LeftParen() {
      super("(", 37);
    }
  }

  public static class RightParen extends SymbolToken {
    public RightParen() {
      super(")", 38);
    }
  }

  public static class Caret extends SymbolToken {
    public Caret() {
      super("^", 42);
    }
  }

  public static class Colon extends SymbolToken {
    public Colon() {
      super(":", 43);
    }
  }

  public static class Period extends SymbolToken {
    public Period() {
      super(".", 44);
    }
  }

  public static class ForwardSlash extends SymbolToken {
    public ForwardSlash() {
      super("/", 48);
    }
  }

  public static class ExclamationMark extends SymbolToken {
    public ExclamationMark() {
      super("!", 100);
    }
  }

}
