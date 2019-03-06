import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class USACO {
  public static int bronze(String filename) throws FileNotFoundException{
    int[][] pasture;
    //read first line
    Scanner in = new Scanner(new File(filename));
    String dimensions = in.nextLine();
    int index = dimensions.indexOf(' ');
    int rows = Integer.parseInt(dimensions.substring(0, index));
    dimensions = dimensions.substring(index+1);
    index = dimensions.indexOf(' ');
    int cols = Integer.parseInt(dimensions.substring(0, index));
    dimensions = dimensions.substring(index+1);
    index = dimensions.indexOf(' ');
    int elevation = Integer.parseInt(dimensions.substring(0, index));
    int numRules = Integer.parseInt(dimensions.substring(index+1));

    pasture = new int[rows][cols];

    //System.out.println(rows + "\n"+cols+"\n"+elevation+"\n"+numRules);

    for (int i = 0; i < rows; i++) {
      String row = in.nextLine();
      for (int j = 0; j < cols - 1; j++) {
        int space = row.indexOf(' ');
        pasture[i][j] = Integer.parseInt(row.substring(0, space));
        row = row.substring(space+1);
      }
      pasture[i][cols-1] = Integer.parseInt(row);
    }

    /*
    for (int i = 0; i < pasture.length; i++) {
      for (int j = 0; j < pasture[i].length; j++) {
        System.out.print(pasture[i][j] + " ");
      }
      System.out.println();
    }
    */
    return 0;
  }

  public static int silver(String filename) throws FileNotFoundException{
    return 0;
  }

  public static void main(String[] args) {
    try {
      bronze("makelake.in");
    } catch (FileNotFoundException e) {}
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
