# A5 Programming Language Lexer
A simple lexer for the A5 Programming language. The goal of the project is to
create a __simple__ and __readable__ lexer. 

A Lexer is a deterministic finite automata. This is generally hidden 
behind layers of character manipulations. Instead, I wanted to focus on 
the DFA itself, and let the low level details fade in background. This led
to modeling the graph, then modeling a lexer, which uses the graph. The lexer 
depends on the graph, but the graph does not know the lexer exists. Similarly, 
the lexer doesn't know about the regular language, rules can be added arbitrarily!

## The Graph
There is no 'Graph' class, rather a node holds a reference to a list of
other nodes, making edges. Each of these 'edges' has a corresponding predicate 
function, which takes some parameter and returns whether we should 'walk' to 
the other node. Walking the graph can be done by repeatedly finding the next
node to walk to giving a particular object. 

## The Lexer
Given the graph abstraction, we can create a deterministic finite automata using
a graph. Each graph node represents a state in the DFA; Each graph edge
has a predicate function that determines if the letter would cause a transition. 
The lexer *_extends_* the graph nodes, adding relevant information, i.e,
if the current state is a *final* or *non-final* state. Accepting tokens would 
be modeled by iteratively passing a character to the lexer, which defers to the graph,
until no viable transition can be made, accepting the token on a final
state.

The code comes with several built-in predicates for detection of letters, numbers, 
whitespace, etc. I could've used a regular expression, but that's cheating! You wouldn't
write a hashtable using a hashtable right? :)

### Example Code
```java
import compiler.lexer.FinalState;
import compiler.lexer.LexerBuilder;
import compiler.lexer.NonFinalState;
import compiler.lexer.token.IntegerToken;
import compiler.lexer.token.WhitespaceToken;

import static compiler.a5.lexicon.A5EdgePredicates.*;

class MyHeavyHitterClass {
  public static void main(String[] args) {
    // States
    var START = new NonFinalState("START");
    var INTEGER = new FinalState("INTEGER", IntegerToken::new);
    var WHITESPACE = new FinalState("WHITESPACE", WhitespaceToken::new);

    // Transitions
    START.ON(A_WHITESPACE).OR(A_LINE_SEPARATOR).GOTO(WHITESPACE);
    WHITESPACE.ON(A_WHITESPACE).OR(A_NEWLINE).GOTO(WHITESPACE);
    START.ON(A_DIGIT).GOTO(INTEGER);
    INTEGER.ON(A_DIGIT).GOTO(INTEGER);

    var lexer = new LexerBuilder().setStartState(START).createLexer();

    // Convert text to tokens, filter out whitespace, accept only integers
    lexer.analyze("123 43 23 34")
      .stream()
      .filter(token -> !(token instanceof WhitespaceToken))
      .forEach(System.out::println);
  }
}
```

## A5 Grammar


The grammar corresponds to the following DFA, like in most texts, consider
any missing transitions to exist and go to a common error state.

![image](./DFA%20%20Diagram.png)
## Dependencies
- Java 12
- Ant

# Installing Dependencies 
`brew install ant`

`brew install java`

# How to run
In the root directory, run the following commands.
The first command builds the java code. The second passes a text file
to the lexer. The lexer outputs the results to standard out in `.alex` format.

```shell script
ant
java -cp ./out/production/Lexer compiler.Main < TestInput.txt
```

#### TODO
- Clean code, requirements changed, and old designs aren't cutting the mustard anymore.
- Add tests.
- Introduce another layer of indirection.
- Rethink treatment of keywords.
- Construct diagram for DFA
- Construct EBNF for A5 programming language
