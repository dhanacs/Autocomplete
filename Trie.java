// Trie Insert and Search.

import java.util.*;
import java.util.Map.Entry;

public class Trie
{
  private final Map<Character, Trie> children;
  private String value;
  private boolean terminal = false;

  public Trie()
  {
    this(null);
  }

  private Trie(String value)
  {
    this.value = value;
    children = new HashMap<Character, Trie>();
  }

  private void add(char character)
  {
    String value;

    if(this.value == null) value = Character.toString(character);
    else value = this.value + character;

    children.put(character, new Trie(value));
  }

  public void insert(String word)
  {
    if(word == null)
    {
      System.out.println("-1");;
    }

    Trie node = this;
    for(char c : word.toCharArray())
    {
      if(!node.children.containsKey(c))
      {
        node.add(c);
      }

      node = node.children.get(c);
    }

    node.terminal = true;
  }

  public String find(String word)
  {
    Trie node = this;
      
    for(char c : word.toCharArray())
    {
      if(!node.children.containsKey(c))
      {
        return "";
      }

      node = node.children.get(c);
    }

    return node.value;
  }

  public Collection<String> autoComplete(String prefix)
  {
    Trie node = this;
        
    for(char c : prefix.toCharArray())
    {
      if(!node.children.containsKey(c))
      {
        return Collections.emptyList();
      }
      node = node.children.get(c);
    }

    return node.allPrefixes();
  }

  private Collection<String> allPrefixes()
  {
    List<String> results = new ArrayList<String>();

    if(this.terminal)
    {
      results.add(this.value);
    }

    for(Entry<Character, Trie> entry : children.entrySet())
    {
      Trie child = entry.getValue();
      Collection<String> childPrefixes = child.allPrefixes();
      results.addAll(childPrefixes);
    }

    return results;
  }
}
