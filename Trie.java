// Trie Insert and Search.

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Trie
{
  private TrieNode root;
  private String words[];

  public void initializeTrie()
  {
    // Read the words from the file.
    try
    {
      Path filePath = new File("Words.txt").toPath();
      Charset charset = Charset.defaultCharset();
      List<String> stringList = Files.readAllLines(filePath, charset);
      words = stringList.toArray(new String[]{});
    }
    catch(Exception e)
    {
      System.out.println("Error reading words from the dictionary.");
    }

    // Initialize the Trie with words.
    for(String word : words)
    {
      addWord(word);
    }
  }

  public void addWord(String word)
  {
    if(root == null)
    {
      root = new TrieNode(' ');
    }

    TrieNode start = root;
    char[] characters = word.toCharArray();

    for(char c : characters)
    {
      if(start.getChildren().size() == 0)
      {
        TrieNode newNode = new TrieNode(c);
        start.getChildren().add(newNode);
        start = newNode;
      }
      else
      {
        ListIterator iterator = start.getChildren().listIterator();
        TrieNode node = null;

        while(iterator.hasNext())
        {
          node = (TrieNode) iterator.next();
          if(node.getCharacter() >= c) break;
        }
        if(node.getCharacter() == c)
        {
          start = node;
        }
        else
        {
          TrieNode newNode = new TrieNode(c);
          iterator.add(newNode);
          start = newNode;
        }
      }
    }
  }

  public List search(String prefix)
  {
    if(prefix == null || prefix.isEmpty()) return null;

    char[] chars = prefix.toCharArray();
    TrieNode start = root;
    boolean flag = false;
    for(char c : chars)
    {
      if(start.getChildren().size() > 0)
      {
        for(TrieNode node : start.getChildren())
        {
          if(node.getCharacter() == c)
          {
            start = node;
            flag = true;
            break;
          }
        }
      }
      else
      {
        flag = false;
        break;
      }
    }

    if(flag)
    {
      List matches = getAllWords(start, prefix);
      return matches;
    }

    return null;
  }

  private List getAllWords(TrieNode start, final String prefix)
  {
    if(start.getChildren().size() == 0)
    {
      List list = new LinkedList();
      list.add(prefix);
      return list;
    }
    else
    {
      List list = new LinkedList();
      for(TrieNode n : start.getChildren())
      {
        list.addAll(getAllWords(n, prefix + n.getCharacter()));
      }

      return list;
    }
  }
}
