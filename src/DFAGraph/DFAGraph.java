package DFAGraph;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static DFAGraph.DFANode.*;

public class DFAGraph {
  private final Map<DFANode, List<Function<Character, DFANode>>> dfaMatrix = new EnumMap<>(DFANode.class);

  public DFAGraph() {
    for (var node : values()) {
      dfaMatrix.put(node, new ArrayList<>());
    }
  }

  public void addTransition(DFANode start, Function<Character, DFANode> transitionFunction) {
    dfaMatrix.get(start).add(transitionFunction);
  }

  public void addTransition(DFANode start, Predicate<Character> predicate, DFANode end) {
    dfaMatrix.get(start).add(character -> predicate.test(character) ? end : ERROR);
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
