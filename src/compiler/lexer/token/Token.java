package compiler.lexer.token;

public abstract class Token {
  private final String value;
  private final int ID;
  private int lineNumber;
  private int linePosition;

  protected Token(String value, int UUID) {
    this.value = value;
    this.ID = UUID;
  }

  public int getLineNumber() {
    return this.lineNumber;
  }

  public void setLineNumber(int lineNumber) {
    this.lineNumber = lineNumber;
  }

  public int getLinePosition() {
    return this.linePosition;
  }

  public void setLinePosition(int linePosition) {
    this.linePosition = linePosition;
  }

  public final String toString() {
    final var format = "(Tok: %s line= %s,%s str = \"%s\"%s)";
    return String.format(format, ID, lineNumber, linePosition, value, toStringExtra());
  }

  public String getValue() {
    return this.value;
  }

  protected String toStringExtra() {
    return "";
  }

}


