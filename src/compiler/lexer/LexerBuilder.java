package compiler.lexer;

import compiler.graph.Node;
import compiler.graph.Token;
import compiler.utils.TriConsumer;

public class LexerBuilder {
  private TriConsumer<Node, Character, Node> onTransition = (i, j, k) -> {
  };

  private TriConsumer<Node, Node, Token> onTokenCreated = (i, j, k) -> {
  };

  private Node startState;

  public LexerBuilder() {

  }

  public LexerBuilder onTransition(TriConsumer<Node, Character, Node> onTransition) {
    this.onTransition = this.onTransition.andThen(onTransition);
    return this;
  }

  public LexerBuilder onTokenCreated(TriConsumer<Node, Node, Token> onTokenCreated) {
    this.onTokenCreated = this.onTokenCreated.andThen(onTokenCreated);
    return this;
  }

  public LexerBuildReady setStartState(Node startState) {
    this.startState = startState;
    return new LexerBuildReady();
  }

  public final class LexerBuildReady {
    private LexerBuildReady() {

    }

    public Lexer createLexer() {
      return new Lexer(startState, onTransition, onTokenCreated);
    }

    public LexerBuilder onTransition(TriConsumer<Node, Character, Node> onTransition) {
      return LexerBuilder.this.onTransition(onTransition);
    }

    public LexerBuilder onTokenCreated(TriConsumer<Node, Node, Token> onTokenCreated) {
      return LexerBuilder.this.onTokenCreated(onTokenCreated);
    }
  }
}
