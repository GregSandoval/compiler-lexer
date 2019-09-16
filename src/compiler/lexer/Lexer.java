package compiler.lexer;

import compiler.graph.Node;
import compiler.lexer.token.CommentToken;
import compiler.lexer.token.KeywordToken;
import compiler.lexer.token.Token;
import compiler.lexer.token.WhitespaceToken;
import compiler.utils.TextCursor;
import compiler.utils.TriConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static compiler.lexer.NonFinalState.END_OF_TERMINAL;
import static compiler.lexer.NonFinalState.FATAL_ERROR;
import static compiler.utils.StringUtils.escape;

public class Lexer {
  private final TriConsumer<Node, Character, Node> onTransition;
  private final TriConsumer<Node, Node, Token> onTokenCreated;
  private final Node START_STATE;

  protected Lexer(
    Node startState,
    TriConsumer<Node, Character, Node> onTransition,
    TriConsumer<Node, Node, Token> onTokenCreated
  ) {
    this.onTransition = onTransition;
    this.onTokenCreated = onTokenCreated;
    this.START_STATE = startState;
  }

  public List<Token> analyze(String text) {
    var CURRENT_STATE = START_STATE;
    var tokens = new ArrayList<Token>();
    var currentToken = new StringBuilder();
    var cursor = new TextCursor(text);
    for (var letter : cursor) {
      var GOTO = CURRENT_STATE.ON(letter);

      if (GOTO != END_OF_TERMINAL)
        onTransition.accept(CURRENT_STATE, letter, GOTO);

      if (CURRENT_STATE instanceof FinalState && GOTO == END_OF_TERMINAL) {
        var token = ((FinalState) CURRENT_STATE).buildToken(currentToken.toString());
        tokens.add(token);
        onTokenCreated.accept(CURRENT_STATE, GOTO, token);
        currentToken.setLength(0);
        cursor.rewind();
        CURRENT_STATE = START_STATE;
        continue;
      }

      if (GOTO == FATAL_ERROR) {
        System.out.println("Unknown token: " + escape(currentToken.toString() + letter) + "\n");
        break;
      }

      currentToken.append(letter);
      CURRENT_STATE = GOTO;
    }

    return tokens
      .stream()
      .filter(terminal -> !(terminal instanceof WhitespaceToken))
      .filter(terminal -> !(terminal instanceof CommentToken))
      .map(KeywordToken::get)
      .collect(Collectors.toList());
  }
}
