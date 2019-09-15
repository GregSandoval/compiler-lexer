package DFAGraph;

import static DFAGraph.DFANode.FLOAT;
import static DFAGraph.DFANode.INTEGER;
import static DFAGraph.LexerMain.escape;

public class Terminal {
  public final DFANode lexicon;
  public final String string;

  private int intValue;
  private float floatValue;

  public Terminal(DFANode lexicon, String string) {
    this.lexicon = lexicon;
    this.string = escape(string);
    if (lexicon == INTEGER) intValue = Integer.parseInt(string);
    if (lexicon == FLOAT) floatValue = Float.parseFloat(string);
    System.out.println("Accepted token: " + this.string + "\n");
  }

  @Override
  public String toString() {
    var dataType = "";
    if (lexicon == INTEGER) dataType = " int= " + intValue;
    if (lexicon == FLOAT) dataType = " flo= " + floatValue;

    return "(Tok: <ID#> line <line, pos> str = \"" + string + "\"" + dataType + ')';
  }

}
