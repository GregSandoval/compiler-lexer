package compiler.lexer.token;

public abstract class TypedToken<Value> extends Token {
  public final Value value;

  protected TypedToken(String str, int tokenID) {
    super(str, tokenID);
    this.value = parse(str);
  }

  protected abstract Value parse(String str);
}
