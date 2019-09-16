package compiler.graph;


import compiler.lexer.FinalState;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static compiler.lexer.NonFinalState.END_OF_TERMINAL;
import static compiler.lexer.NonFinalState.FATAL_ERROR;

public class Node {
  private final List<Function<Character, Node>> transitions = new ArrayList<>();
  private final String name;

  protected Node(String name) {
    this.name = name;
  }

  public PartialEdge ON(Predicate<Character> predicate) {
    return end -> transitions.add(character -> predicate.test(character) ? end : null);
  }

  public Node ON(Character character) {
    return this.transitions
      .stream()
      .map(transitionFunc -> transitionFunc.apply(character))
      .filter(Objects::nonNull)
      .findFirst()
      .orElse(this instanceof FinalState ? END_OF_TERMINAL : FATAL_ERROR);
  }

  @Override
  public String toString() {
    return name;
  }

  public interface PartialEdge {
    void GOTO(Node end);
  }
}

