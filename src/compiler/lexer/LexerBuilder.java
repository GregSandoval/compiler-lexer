package compiler.lexer;

import compiler.graph.Node;
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

  public LexerBuilderReady setStartState(Node startState) {
    this.startState = startState;
    return new LexerBuilderReady();
  }

  public final class LexerBuilderReady {
    private LexerBuilderReady() {

    }

    public Lexer createLexer() {
      return new Lexer(startState, onTransition, onTokenCreated);
    }

    public LexerBuilderReady onTransition(TriConsumer<Node, Character, Node> onTransition) {
      LexerBuilder.this.onTransition(onTransition);
      return this;
    }

    public LexerBuilderReady onTokenCreated(TriConsumer<Node, Node, Token> onTokenCreated) {
      LexerBuilder.this.onTokenCreated(onTokenCreated);
      return this;
    }
  }
}
