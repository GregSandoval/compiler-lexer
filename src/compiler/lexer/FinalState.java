package compiler.lexer;

import compiler.lexer.token.Token;

import java.util.function.Function;

public class FinalState extends LexicalNode {
  private Function<String, Token> constructor;
  private Token instance;

  public FinalState(String name, Function<String, Token> token) {
    super(name);
    this.constructor = token;
  }

  public FinalState(String name, Token instance) {
    super(name);
    this.instance = instance;
  }

  public Token getToken(String str) {
    return instance != null ? instance : this.constructor.apply(str);
  }
}
