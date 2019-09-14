package DFAGraph;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static DFAGraph.DFANode.*;

public class DFAGraph {
  private final Map<DFANode, List<Function<Character, DFANode>>> dfaMatrix = new EnumMap<>(DFANode.class);

  protected DFAGraph() {
    for (var node : values()) {
      dfaMatrix.put(node, new ArrayList<>());
    }
  }

  protected DFAGraph transition() {
    return this;
  }

  protected void transition(DFANode start, Function<Character, DFANode> transitionFunction) {
    dfaMatrix.get(start).add(transitionFunction);
  }


  protected DFAGraph transition(DFANode start, Predicate<Character> predicate, DFANode end) {
    dfaMatrix.get(start).add(character -> predicate.test(character) ? end : ERROR);
    return this;
  }

  public DFANode transition(DFANode start, Character character) {
    return dfaMatrix.get(start)
      .stream()
      .map(transitionFunc -> transitionFunc.apply(character))
      .filter(state -> state != ERROR)
      .findFirst()
      .orElse(ERROR);
  }
}
