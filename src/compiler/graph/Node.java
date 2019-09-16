package compiler.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class Node<T extends Node<T>> {
  private final Supplier<Optional<T>> onError;
  private final List<Function<Character, T>> transitions = new ArrayList<>();
  private final String name;

  protected Node(String name) {
    this(name, ignored -> Optional.empty());
  }

  protected Node(String name, Function<T, Optional<T>> onError) {
    this.name = name;
    this.onError = () -> onError.apply(me());
  }

  public PartialEdge<T> ON(Predicate<Character> predicate) {
    return end -> transitions.add(character -> predicate.test(character) ? end : null);
  }

  public T ON(Character character) {
    return this.transitions
      .stream()
      .map(transitionFunc -> transitionFunc.apply(character))
      .filter(Objects::nonNull)
      .findFirst()
      .or(onError)
      .orElseThrow(() -> new NoEdgeFound(this, character));
  }

  @Override
  public String toString() {
    return name;
  }

  public interface PartialEdge<T> {
    void GOTO(T end);
  }

  protected abstract T me();
}

