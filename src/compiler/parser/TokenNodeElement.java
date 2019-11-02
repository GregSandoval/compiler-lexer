package compiler.parser;


public interface TokenNodeElement {
  public void accept(TokenVisitor visitor);
}
