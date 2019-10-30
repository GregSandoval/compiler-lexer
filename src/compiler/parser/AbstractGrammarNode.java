package compiler.parser;

import java.util.LinkedList;

public abstract class AbstractGrammarNode {
  public AbstractGrammarNode parent;
  public AbstractGrammarNode left;
  public AbstractGrammarNode right;
  public LinkedList<AbstractGrammarNode> children = new LinkedList<>();
}
