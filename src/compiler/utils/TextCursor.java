package compiler.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TextCursor implements Iterator<Character>, Iterable<Character> {
  private final char[] text;
  private int cursor = -1;

  public TextCursor(@NotNull String text) {
    this.text = (text + "\n\n").toCharArray();
  }

  @Override
  public boolean hasNext() {
    return this.cursor + 1 < this.text.length;
  }

  @Override
  public Character next() {
    if (cursor + 1 >= this.text.length) {
      throw new NoSuchElementException();
    }

    return text[++cursor];
  }

  public void rewind() {
    if (cursor - 1 < 0) {
      throw new NoSuchElementException();
    }

    cursor--;
  }

  @NotNull
  @Override
  public Iterator<Character> iterator() {
    return this;
  }
}
