package compiler.lexer.token;

public class KeywordToken extends Token {
  private KeywordToken(String str, int UUID) {
    super(str, UUID);
  }

  public static class ProgramKeywordToken extends KeywordToken {
    public ProgramKeywordToken() {
      super("prog", 10);
    }
  }

  public static class MainKeywordToken extends KeywordToken {
    public MainKeywordToken() {
      super("main", 11);
    }
  }

  public static class FunctionKeywordToken extends KeywordToken {
    public FunctionKeywordToken() {
      super("fcn", 12);
    }
  }

  public static class ClassKeywordToken extends KeywordToken {
    public ClassKeywordToken() {
      super("class", 13);
    }
  }

  public static class FloatKeywordToken extends KeywordToken {
    public FloatKeywordToken() {
      super("float", 13);
    }
  }

  public static class IntegerKeywordToken extends KeywordToken {
    public IntegerKeywordToken() {
      super("int", 16);
    }
  }

  public static class StringKeywordToken extends KeywordToken {
    public StringKeywordToken() {
      super("string", 17);
    }
  }

  public static class IfKeywordToken extends KeywordToken {
    public IfKeywordToken() {
      super("if", 18);
    }
  }

  public static class ElseIfKeywordToken extends KeywordToken {
    public ElseIfKeywordToken() {
      super("elseif", 19);
    }
  }

  public static class ElseKeywordToken extends KeywordToken {
    public ElseKeywordToken() {
      super("else", 20);
    }
  }

  public static class WhileKeywordToken extends KeywordToken {
    public WhileKeywordToken() {
      super("while", 21);
    }
  }

  public static class InputKeywordToken extends KeywordToken {
    public InputKeywordToken() {
      super("input", 22);
    }
  }

  public static class PrintKeywordToken extends KeywordToken {
    public PrintKeywordToken() {
      super("print", 23);
    }
  }

  public static class NewKeywordToken extends KeywordToken {
    public NewKeywordToken() {
      super("new", 24);
    }
  }

  public static class ReturnKeywordToken extends KeywordToken {
    public ReturnKeywordToken() {
      super("return", 25);
    }
  }

  public static class VarKeywordToken extends KeywordToken {
    public VarKeywordToken() {
      super("var", 2);
    }
  }
}
