package compiler.graph;

public class NoEdgeFound extends RuntimeException {
  public NoEdgeFound(Node node, Character character) {
    super("No transition found for " + node + " on character: " + character);
  }
}
