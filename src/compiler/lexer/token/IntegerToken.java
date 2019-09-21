package compiler.lexer.token;

public final class IntegerToken extends TypedToken<Integer> {

  public IntegerToken(String str) {
    super(str, 3);
  }

  @Override
  protected Integer parse(String str) {
    return Integer.parseInt(str);
  }

  @Override
  public String toStringExtra() {
    return " int= " + this.value + ")";
  }

}
