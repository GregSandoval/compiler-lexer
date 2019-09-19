package compiler.lexer.token;

public final class FloatToken extends TypedToken<Float> {

  public FloatToken(String str) {
    super(str, 4);
  }

  @Override
  protected Float parse(String str) {
    return Float.parseFloat(str);
  }

  @Override
  public String toStringExtra() {
    return " flo= " + this.value;
  }

}
