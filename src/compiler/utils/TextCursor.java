package compiler.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TextCursor implements Iterator<Character>, Iterable<Character> {
  private final char[] text;
  private final int[] lineNumbers;
  private final int[] linePositions;

  private int cursor = -1;

  public TextCursor(@NotNull String text) {
    this.text = (text + " ").toCharArray();
    this.lineNumbers = new int[this.text.length];
    this.linePositions = new int[this.text.length];
    int line = 1;
    int pos = 1;
    for (var i = 0; i < this.text.length; i++) {
      lineNumbers[i] = line;
      linePositions[i] = pos;
      if (this.text[i] == '\n' || this.text[i] == '\r' || this.text[i] == '\f') {
        line++;
        pos = 0;
      }
      pos++;
    }

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

  public int getCursorLineNumber() {
    return this.lineNumbers[cursor];
  }

  public int getCursorLinePosition(){
    return this.linePositions[cursor];
  }

  @NotNull
  @Override
  public Iterator<Character> iterator() {
    return this;
  }
}
