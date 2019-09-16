package compiler.lexer;

public abstract class Token {
  private static int UUID = 5;
  public final String str;
  public final int ID;

  protected Token(String str) {
    this.str = str;
    this.ID = ++UUID;
  }

  protected Token(String str, int UUID) {
    this.str = str;
    this.ID = UUID;
  }

  @Override
  public String toString() {
    return "(Tok: " + ID + " line= <line, pos> str = \"" + this.str + "\")";
  }
}


