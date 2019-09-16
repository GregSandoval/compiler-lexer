package compiler.lexer;

import compiler.graph.Node;

public class NonFinalState extends Node {
  public static final Node FATAL_ERROR = new NonFinalState("FATAL_ERROR");
  public static final Node END_OF_TERMINAL = new NonFinalState("END_OF_TERMINAL");

  public NonFinalState(String name) {
    super(name);
  }

}
