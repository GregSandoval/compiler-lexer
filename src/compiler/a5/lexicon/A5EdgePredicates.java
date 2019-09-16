package compiler.a5.lexicon;

import java.util.function.Predicate;

public final class A5EdgePredicates {
  // CHARACTER SETS
  public static Predicate<Character> LOWER_CASE_LETTER = symbol -> symbol >= 'a' && symbol <= 'z';
  public static Predicate<Character> UPPER_CASE_LETTER = symbol -> symbol >= 'A' && symbol <= 'Z';
  public static Predicate<Character> LETTER = LOWER_CASE_LETTER.or(UPPER_CASE_LETTER);
  public static Predicate<Character> DIGIT = symbol -> symbol >= '0' && symbol <= '9';

  // WHITE SPACE AND NEWLINES
  public static Predicate<Character> TAB = IS_CHARACTER('\t');
  public static Predicate<Character> NEWLINE = IS_CHARACTER('\n');
  public static Predicate<Character> FORM_FEED = IS_CHARACTER('\f');
  public static Predicate<Character> CARRIAGE_RETURN = IS_CHARACTER('\r');
  public static Predicate<Character> SPACE = IS_CHARACTER(' ');
  public static Predicate<Character> IS_LINE_SEPARATOR = NEWLINE.or(FORM_FEED).or(CARRIAGE_RETURN);
  public static Predicate<Character> IS_WHITESPACE = TAB.or(SPACE);

  // UNPAIRED DELIMITERS
  public static Predicate<Character> IS_COMMA = IS_CHARACTER(',');
  public static Predicate<Character> IS_SEMI_COLON = IS_CHARACTER(';');

  // PAIRED DELIMITERS
  public static Predicate<Character> IS_LESS_THAN = IS_CHARACTER('<');
  public static Predicate<Character> IS_GREATER_THAN = IS_CHARACTER('>');

  public static Predicate<Character> IS_LEFT_BRACE = IS_CHARACTER('{');
  public static Predicate<Character> IS_RIGHT_BRACE = IS_CHARACTER('}');

  public static Predicate<Character> IS_LEFT_BRACKET = IS_CHARACTER('[');
  public static Predicate<Character> IS_RIGHT_BRACKET = IS_CHARACTER(']');

  public static Predicate<Character> IS_LEFT_PAREN = IS_CHARACTER('(');
  public static Predicate<Character> IS_RIGHT_PAREN = IS_CHARACTER(')');

  // OTHER PUNCTUATION
  public static Predicate<Character> IS_ASTERISK = IS_CHARACTER('*');
  public static Predicate<Character> IS_CARET = IS_CHARACTER('^');
  public static Predicate<Character> IS_COLON = IS_CHARACTER(':');
  public static Predicate<Character> IS_PERIOD = IS_CHARACTER('.');
  public static Predicate<Character> IS_EQUAL = IS_CHARACTER('=');
  public static Predicate<Character> IS_MINUS = IS_CHARACTER('-');
  public static Predicate<Character> IS_PLUS = IS_CHARACTER('+');
  public static Predicate<Character> IS_FORWARD_SLASH = IS_CHARACTER('/');
  public static Predicate<Character> IS_AND = IS_CHARACTER('&');
  public static Predicate<Character> IS_QUOTE = IS_CHARACTER('"');
  public static Predicate<Character> IS_UNDERSCORE = IS_CHARACTER('_');
  public static Predicate<Character> IS_EXCLAMATION_MARK = IS_CHARACTER('!');

  // EVERYTHING EXCEPT LINE FEED
  public static Predicate<Character> ANY = IS_LINE_SEPARATOR.negate();

  private static Predicate<Character> IS_CHARACTER(Character character) {
    return symbol -> symbol == character;
  }
}
