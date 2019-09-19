package compiler.lexer.token;

public abstract class Token {
  private static String format = "(Tok: %s line= %s, %s str = \"%s\"%s)";
  public final String str;
  public final int ID;
  public int lineNumber;
  public int linePosition;

  protected Token(String str, int UUID) {
    this.str = str;
    this.ID = UUID;
  }

  public void setLineNumber(int lineNumber){
    this.lineNumber = lineNumber;
  }

  public void setLinePosition(int linePosition){
    this.linePosition = linePosition;
  }

  public final String toString() {
    return String.format(format, ID, lineNumber, linePosition, str,toStringExtra());
  }

  protected String toStringExtra(){
    return "";
  }
}


