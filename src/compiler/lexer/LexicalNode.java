package compiler.lexer;

import compiler.graph.Node;

import java.util.Optional;

import static compiler.lexer.NonFinalState.END_OF_TERMINAL;
import static compiler.lexer.NonFinalState.FATAL_ERROR;

public class LexicalNode extends Node<LexicalNode> {

  LexicalNode(String name) {
    super(name, LexicalNode::routeErrorToCustomStates);
  }

  private static Optional<LexicalNode> routeErrorToCustomStates(LexicalNode from) {
    return Optional.of(from instanceof FinalState ? END_OF_TERMINAL : FATAL_ERROR);
  }

  @Override
  protected LexicalNode me() {
    return this;
  }
}
