package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Node {
  private static final Node FATAL_ERROR = new Node();
  private static final Node END_OF_TERMINAL = new Node();
  private final List<Function<Character, Node>> transitions = new ArrayList<>();

  public PartialEdge ON(Predicate<Character> predicate) {
    return end -> transitions.add(character -> predicate.test(character) ? end : null);
  }

  public Node ON(Character character) {
    return this.transitions
      .stream()
      .map(transitionFunc -> transitionFunc.apply(character))
      .filter(Objects::nonNull)
      .findFirst()
      .orElse(this instanceof FINAL_STATE ? END_OF_TERMINAL : FATAL_ERROR);
  }

  public interface PartialEdge {
    void GOTO(Node end);
  }

  static final class FINAL_STATE<T extends Token> extends Node {
    private final Function<String, T> constructor;

    public FINAL_STATE(Function<String, T> token) {
      this.constructor = token;
    }

    public T buildToken(String str) {
      return this.constructor.apply(str);
    }
  }

  static final class NON_FINAL_STATE extends Node {

  }
}

