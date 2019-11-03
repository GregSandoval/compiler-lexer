package compiler.lexer.token;

import compiler.parser.TokenVisitor;

public final class FloatToken extends TypedToken<Float> {
  private static final FloatToken sentinel = new FloatToken("0");

  public FloatToken(String str) {
    super(str, 4);
  }

  @Override
  protected Float parse(String str) {
    return Float.parseFloat(str);
  }

  public static FloatToken getSentinel() {
    return sentinel;
  }

  @Override
  public String toStringExtra() {
    return " flo= " + this.value;
  }

  @Override
  public void accept(TokenVisitor visitor) {
    visitor.visit(this);
  }
}
