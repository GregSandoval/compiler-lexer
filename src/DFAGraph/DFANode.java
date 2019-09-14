package DFAGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static utils.Constants.FINAL_STATE;
import static utils.Constants.NON_FINAL_STATE;

public enum DFANode {
  // DFA Grammar
  ERROR(NON_FINAL_STATE),
  START(NON_FINAL_STATE),
  IDENTIFIER(FINAL_STATE),
  INTEGER(FINAL_STATE),
  MAYBE_FLOAT(NON_FINAL_STATE),
  FLOAT(FINAL_STATE),

  OPENING_STRING(NON_FINAL_STATE),
  STRING_CONTENTS(NON_FINAL_STATE),
  CLOSING_STRING(FINAL_STATE),

  WHITESPACE(NON_FINAL_STATE),
  COMMENT(NON_FINAL_STATE),

  // Unpaired delimiters
  COMMA(FINAL_STATE),
  SEMI_COLON(FINAL_STATE),

  // Paired delimiters
  LESS_THAN(FINAL_STATE),
  GREATER_THAN(FINAL_STATE),

  LEFT_BRACE(FINAL_STATE),
  RIGHT_BRACE(FINAL_STATE),

  LEFT_BRACKET(FINAL_STATE),
  RIGHT_BRACKET(FINAL_STATE),

  LEFT_PAREN(FINAL_STATE),
  RIGHT_PAREN(FINAL_STATE),

  // Other punctuation tokens
  ASTERISK(FINAL_STATE),
  CARET(FINAL_STATE),
  COLON(FINAL_STATE),
  PERIOD(FINAL_STATE),
  EQUAL(FINAL_STATE),
  MINUS(FINAL_STATE),
  PLUS(FINAL_STATE),
  FORWARD_SLASH(FINAL_STATE),
  AND(FINAL_STATE),
  EXCLAMATION_MARK(FINAL_STATE),

  // Multi character operators
  OP_ARROW(FINAL_STATE),
  OP_EQUAL(FINAL_STATE),
  OP_NEGATE(FINAL_STATE),
  OP_LESS_THAN(FINAL_STATE),
  OP_GREATER_THAN(FINAL_STATE),
  OP_SHIFT_LEFT(FINAL_STATE),
  OP_SHIFT_RIGHT(FINAL_STATE);

  public final boolean IS_FINAL_STATE;
  public final boolean IS_NOT_FINAL_STATE;
  private final List<Function<Character, DFANode>> transitions = new ArrayList<>();

  DFANode(boolean finalState) {
    this.IS_FINAL_STATE = finalState;
    this.IS_NOT_FINAL_STATE = !finalState;
  }

  public PartialEdge ON(Predicate<Character> predicate) {
    return end -> transitions.add(character -> predicate.test(character) ? end : ERROR);
  }

  public DFANode ON(Character character) {
    return this.transitions
      .stream()
      .map(transitionFunc -> transitionFunc.apply(character))
      .filter(state -> state != ERROR)
      .findFirst()
      .orElse(ERROR);
  }

  public interface PartialEdge {
    void GOTO(DFANode end);
  }
}
