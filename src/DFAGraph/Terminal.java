package DFAGraph;

import static DFAGraph.DFANode.FLOAT;
import static DFAGraph.DFANode.INTEGER;
import static DFAGraph.LexerMain.escape;

public class Terminal {
  public final DFANode lexicon;
  public final String value;

  public Terminal(DFANode lexicon, String value) {
    this.lexicon = lexicon;
    this.value = escape(value);
    System.out.println("Accepted token: " + value + "\n");
  }

  @Override
  public String toString() {
    var dataType = "";
    if (lexicon == INTEGER) dataType = " int";
    if (lexicon == FLOAT) dataType = " flo";

    return "(Tok: <ID#> line <line, pos> str = \"" + value + "\"" + dataType + ')';
  }

}
