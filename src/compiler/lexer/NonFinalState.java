package compiler.lexer;

import compiler.graph.Node;

public class NonFinalState extends LexicalNode {
  public static final Node END_OF_TERMINAL = new NonFinalState("END_OF_TERMINAL");
  public static final Node FATAL_ERROR = new NonFinalState("FATAL_ERROR");

  public NonFinalState(String name) {
    super(name);
  }

}
