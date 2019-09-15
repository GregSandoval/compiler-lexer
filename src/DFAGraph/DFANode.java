package DFAGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static utils.Constants.FINAL_STATE;
import static utils.Constants.NON_FINAL_STATE;

public enum DFANode {
  // DFA Grammar
  FATAL_ERROR(NON_FINAL_STATE),
  END_OF_TERMINAL(NON_FINAL_STATE),
  NO_TRANSITION_FOUND(NON_FINAL_STATE),

  START(NON_FINAL_STATE),
  IDENTIFIER(FINAL_STATE),
  INTEGER(FINAL_STATE),
  MAYBE_FLOAT(NON_FINAL_STATE),
  FLOAT(FINAL_STATE),

  OPENING_STRING(NON_FINAL_STATE),
  STRING_CONTENTS(NON_FINAL_STATE),
  CLOSING_STRING(FINAL_STATE),

  WHITESPACE(FINAL_STATE),
  COMMENT(FINAL_STATE),

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
  private final List<Function<Character, DFANode>> transitions = new ArrayList<>();

  DFANode(boolean finalState) {
    this.IS_FINAL_STATE = finalState;
  }

  public PartialEdge ON(Predicate<Character> predicate) {
    return end -> transitions.add(character -> predicate.test(character) ? end : null);
  }

  public DFANode ON(Character character) {
    return this.transitions
      .stream()
      .map(transitionFunc -> transitionFunc.apply(character))
      .filter(Objects::nonNull)
      .findFirst()
      .orElse(IS_FINAL_STATE ? END_OF_TERMINAL : FATAL_ERROR);
  }

  public interface PartialEdge {
    void GOTO(DFANode end);
  }
}
