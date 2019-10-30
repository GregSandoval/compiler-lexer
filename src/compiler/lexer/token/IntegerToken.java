package compiler.lexer.token;

public final class IntegerToken extends TypedToken<Integer> {
  private static final IntegerToken sentinel = new IntegerToken("0");

  public IntegerToken(String str) {
    super(str, 3);
  }

  @Override
  protected Integer parse(String str) {
    return Integer.parseInt(str);
  }

  public static IntegerToken getSentinel() {
    return sentinel;
  }

  @Override
  public String toStringExtra() {
    return " int= " + this.value + ")";
  }

}
