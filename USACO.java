import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class USACO {
  public static int bronze(String filename) throws FileNotFoundException{
    int[][] pasture;
    Scanner in = new Scanner(new File(filename));
    String dimensions = in.nextLine();
    int i = dimensions.indexOf(' ');
    int rows = Integer.parseInt(dimensions.substring(0, i));
    dimensions = dimensions.substring(i+1);
    i = dimensions.indexOf(' ');
    int cols = Integer.parseInt(dimensions.substring(0, i));
    dimensions = dimensions.substring(i+1);
    i = dimensions.indexOf(' ');
    int elevation = Integer.parseInt(dimensions.substring(0, i));
    int numRules = Integer.parseInt(dimensions.substring(i+1));

    System.out.println(rows + '\n'+cols+'\n'+elevation+'\n'+numRules);
    return 0;
  }

  public static int silver(String filename) throws FileNotFoundException{
    return 0;
  }
}

class Instruction {
  private int r;
  private int c;
  private int depth;

  public Instruction (String rule) {
    int i = rule.indexOf(' ');
    r = Integer.parseInt(rule.substring(0, i));
    rule = rule.substring(i+1);
    i = rule.indexOf(' ');
    c = Integer.parseInt(rule.substring(0, i));
    depth = Integer.parseInt(rule.substring(i+1));
  }
}
