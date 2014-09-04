// Trie Node.

import java.util.LinkedList;
import java.util.List;

public class TrieNode
{
  private char character;
  private List<TrieNode> children;

  public TrieNode(char charecter)
  {
    this.character = charecter;
    this.children = new LinkedList();
  }

  public char getCharacter()
  {
    return character;
  }

  public void setCharacter(char character)
  {
    this.character = character;
  }

  public List<TrieNode> getChildren()
  {
    return children;
  }

  public void setChildren(List children)
  {
    this.children = children;
  }
}
