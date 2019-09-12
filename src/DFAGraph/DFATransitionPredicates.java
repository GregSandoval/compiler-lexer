package DFAGraph;

import java.util.function.Predicate;

public final class DFATransitionPredicates {
  public static Predicate<Character> UNDERSCORE = symbol -> symbol == '_';
  public static Predicate<Character> PLUS = symbol -> symbol == '+';
  public static Predicate<Character> MINUS = symbol -> symbol == '-';
  public static Predicate<Character> QUOTE = symbol -> symbol == '"';
  public static Predicate<Character> PERIOD = symbol -> symbol == '.';
  public static Predicate<Character> LOWER_CASE_LETTER = symbol -> symbol >= 'a' && symbol <= 'z';
  public static Predicate<Character> UPPER_CASE_LETTER = symbol -> symbol >= 'A' && symbol <= 'Z';
  public static Predicate<Character> LETTER = LOWER_CASE_LETTER.or(UPPER_CASE_LETTER);
  public static Predicate<Character> DIGIT = symbol -> symbol >= '0' && symbol <= '9';
  public static Predicate<Character> IS_WHITESPACE = Character::isWhitespace;
  public static Predicate<Character> ANY = IS_WHITESPACE.negate();
}
