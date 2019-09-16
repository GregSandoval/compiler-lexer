package compiler.lexer;

import compiler.graph.Node;

import java.util.Optional;

import static compiler.lexer.NonFinalState.END_OF_TERMINAL;
import static compiler.lexer.NonFinalState.FATAL_ERROR;

public class LexicalNode extends Node {

  protected LexicalNode(String name) {
    super(name, LexicalNode::onTransitionError);
  }

  private static Optional<Node> onTransitionError(Node from) {
    return Optional.of(from instanceof FinalState ? END_OF_TERMINAL : FATAL_ERROR);
  }
}
