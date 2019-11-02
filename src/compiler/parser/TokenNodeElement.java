package compiler.parser;


public interface TokenNodeElement {
  void accept(TokenVisitor visitor);
}
