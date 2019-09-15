package DFAGraph;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static DFAGraph.DFANode.END_OF_TERMINAL;
import static DFAGraph.DFANode.values;

public class DFAGraph {
  private final Map<DFANode, List<Function<Character, DFANode>>> dfaMatrix = new EnumMap<>(DFANode.class);

  protected DFAGraph() {
    for (var node : values()) {
      dfaMatrix.put(node, new ArrayList<>());
    }
  }

  protected DFAGraph start() {
    return this;
  }

  protected void end() {

  }

  protected DFAGraph transition(DFANode start, Predicate<Character> predicate, DFANode end) {
    dfaMatrix.get(start).add(character -> predicate.test(character) ? end : END_OF_TERMINAL);
    return this;
  }

  public DFANode transition(DFANode start, Character character) {
    return dfaMatrix.get(start)
      .stream()
      .map(transitionFunc -> transitionFunc.apply(character))
      .filter(state -> state != END_OF_TERMINAL)
      .findFirst()
      .orElse(END_OF_TERMINAL);
  }
}
