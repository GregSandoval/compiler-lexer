package compiler.lexer.token;

import java.util.HashMap;
import java.util.Map;

public final class FloatToken extends TypedToken<Float> {
  private static Map<String, FloatToken> identityMap = new HashMap<>();

  protected FloatToken(String str) {
    super(str, 4);
  }

  public static FloatToken build(String string) {
    return identityMap.computeIfAbsent(string, FloatToken::new);
  }

  @Override
  protected Float parse(String str) {
    return Float.parseFloat(str);
  }

  @Override
  public String toString() {
    return "(Tok: " + ID + " line= <line, pos> str = \"" + this.str + "\" flo= " + this.value + ")";
  }
}
