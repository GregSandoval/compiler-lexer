package DFAGraph;

import Graph.Node;
import Graph.Token;
import utils.TextCursor;
import utils.TriConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Graph.Node.NON_FINAL_STATE.END_OF_TERMINAL;
import static Graph.Node.NON_FINAL_STATE.FATAL_ERROR;

public class Lexer {
  private final TriConsumer<Node, Character, Node> onTransition;
  private final TriConsumer<Node, Node, Token> onTokenCreated;
  private final Node START_STATE;

  protected Lexer(Node startState, TriConsumer<Node, Character, Node> onTransition, TriConsumer<Node, Node, Token> onTokenCreated) {
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

      if (CURRENT_STATE instanceof Node.FINAL_STATE && GOTO == END_OF_TERMINAL) {
        var token = ((Node.FINAL_STATE) CURRENT_STATE).buildToken(escape(currentToken.toString()));
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
      .filter(terminal -> !(terminal instanceof Token.WhitespaceToken))
      .filter(terminal -> !(terminal instanceof Token.CommentToken))
      .map(Token.KeywordToken::get)
      .collect(Collectors.toList());
  }

  public static String escape(String string) {
    return string.replace("\n", "\\n").replace("\t", "\\t").replace("\r", "\\r").replace("\f", "\\f");
  }
}
