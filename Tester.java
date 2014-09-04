import java.util.List;

public class Tester
{
  public static void main(String args[])
  {
    Trie trie = new Trie();
    trie.initializeTrie();

    // List all the matches for the given prefix.
    List<String> matches = trie.search("try");

    if(matches != null)
    {
      for(String word : matches)
      {
        System.out.println(word);
      }
    }
  }
}
