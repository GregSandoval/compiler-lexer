package compiler.lexer;

import compiler.lexer.token.Token;
import compiler.utils.TriConsumer;

import java.util.function.Consumer;

public class LexerBuilder {
  private TriConsumer<LexicalNode, Character, LexicalNode> onTransition = (i, j, k) -> {
  };

  private TriConsumer<LexicalNode, LexicalNode, Token> onTokenCreated = (i, j, k) -> {
  };

  private Consumer<String> onUnknownTokenFound = (ignored) -> {

  };

  private LexicalNode startState;

  public LexerBuilder() {

  }

  public LexerBuilder onTransition(TriConsumer<LexicalNode, Character, LexicalNode> onTransition) {
    this.onTransition = this.onTransition.andThen(onTransition);
    return this;
  }

  public LexerBuilder onTokenCreated(TriConsumer<LexicalNode, LexicalNode, Token> onTokenCreated) {
    this.onTokenCreated = this.onTokenCreated.andThen(onTokenCreated);
    return this;
  }

  public LexerBuilder onUnknownTokenFound(Consumer<String> onUnknownTokenFound) {
    this.onUnknownTokenFound = this.onUnknownTokenFound.andThen(onUnknownTokenFound);
    return this;
  }

  public LexerBuilderReady setStartState(LexicalNode startState) {
    this.startState = startState;
    return new LexerBuilderReady();
  }

  public final class LexerBuilderReady {
    private LexerBuilderReady() {

    }

    public LexerBuilderReady onTransition(TriConsumer<LexicalNode, Character, LexicalNode> onTransition) {
      LexerBuilder.this.onTransition(onTransition);
      return this;
    }

    public LexerBuilderReady onTokenCreated(TriConsumer<LexicalNode, LexicalNode, Token> onTokenCreated) {
      LexerBuilder.this.onTokenCreated(onTokenCreated);
      return this;
    }

    public LexerBuilderReady onUnknownTokenFound(Consumer<String> onUnknownTokenFound) {
      LexerBuilder.this.onUnknownTokenFound(onUnknownTokenFound);
      return this;
    }

    public Lexer createLexer() {
      return new Lexer(startState, onTransition, onTokenCreated, onUnknownTokenFound);
    }
  }
}
