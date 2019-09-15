package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static Graph.Node.NON_FINAL_STATE.END_OF_TERMINAL;
import static Graph.Node.NON_FINAL_STATE.FATAL_ERROR;

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
      .orElse(this instanceof FINAL_STATE ? END_OF_TERMINAL : FATAL_ERROR);
  }

  @Override
  public String toString() {
    return name;
  }

  public interface PartialEdge {
    void GOTO(Node end);
  }

  public static final class FINAL_STATE extends Node {
    private Function<String, Token> constructor;
    private Token instance;

    public FINAL_STATE(String name, Function<String, Token> token) {
      super(name);
      this.constructor = token;
    }

    public FINAL_STATE(String name, Token instance) {
      super(name);
      this.instance = instance;
    }

    public Token buildToken(String str) {
      return instance != null ? instance : this.constructor.apply(str);
    }
  }

  public static final class NON_FINAL_STATE extends Node {
    public static final Node FATAL_ERROR = new NON_FINAL_STATE("FATAL_ERROR");
    public static final Node END_OF_TERMINAL = new NON_FINAL_STATE("END_OF_TERMINAL");

    public NON_FINAL_STATE(String name) {
      super(name);
    }
  }

}

