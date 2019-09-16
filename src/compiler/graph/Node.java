package compiler.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Node {
  private final Supplier<Optional<Node>> onError;
  private final List<Function<Character, Node>> transitions = new ArrayList<>();
  private final String name;

  protected Node(String name) {
    this(name, ignored -> Optional.empty());
  }

  protected Node(String name, Function<Node, Optional<Node>> onError) {
    this.name = name;
    this.onError = () -> onError.apply(this);
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
      .or(onError)
      .orElseThrow(() -> new RuntimeException("No transition found for " + this + " on character: " + character));
  }

  @Override
  public String toString() {
    return name;
  }

  public interface PartialEdge {
    void GOTO(Node end);
  }
}

