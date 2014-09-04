import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public class Tester
{
  private String words[];
  private Trie trie;

  public void initializeTrie()
  {
    trie = new Trie();

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
      trie.insert(word);
    }
  }

  public void printMatches(String prefix)
  {
    // List all the matches for the given prefix.
    Collection<String> matches = trie.autoComplete(prefix);

    if(matches != null)
    {
      for(String word : matches)
       System.out.println(word);
    }
    else System.out.println("-1");
  }

  public static void main(String args[])
  {
    Tester tester = new Tester();
    tester.initializeTrie();

    tester.printMatches("ooo");
  }
}
