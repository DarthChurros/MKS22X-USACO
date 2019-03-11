import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class USACO {
  public static int makelake(String filename) throws FileNotFoundException{
    int[][] pasture;
    //read first line

    int[] specs = readBronzeSpecs(filename);
    int rows = specs[0];
    int cols = specs[1];
    int elevation = specs[2];
    int numRules = specs[3];
    //System.out.println(rows + "\n"+cols+"\n"+elevation+"\n"+numRules);

    pasture = readBronzeBoard(filename, rows, cols);
    /*
    for (int i = 0; i < pasture.length; i++) {
      for (int j = 0; j < pasture[i].length; j++) {
        System.out.print(pasture[i][j] + " ");
      }
      System.out.println();
    }
    */

    ArrayList<Instruction> instructions = readBronzeInstructions(filename, rows);
    //System.out.println(instructions);

    //for (int i = 0; i < instructions.size(); i++) {
    //
    //}

    for (int i = 0; i < instructions.size(); i++) {
      stomp(pasture, instructions.get(i));
    }

    int volume = 0;

    for (int i = 0; i < pasture.length; i++) {
      for (int j = 0; j < pasture[i].length; j++) {
        volume += Math.max(0, elevation - pasture[i][j]);
      }
    }
    volume *= 5184;
    return volume;
  }

  private static int[] readBronzeSpecs(String filename) throws FileNotFoundException {
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
    return new int[] {rows, cols, elevation, numRules};
  }

  private static int[][] readBronzeBoard(String filename, int r, int c) throws FileNotFoundException {
    int[][] ans = new int[r][c];
    Scanner in = new Scanner(new File(filename));
    in.nextLine();
    for (int i = 0; i < r; i++) {
      String row = in.nextLine();
      for (int j = 0; j < c - 1; j++) {
        int space = row.indexOf(' ');
        ans[i][j] = Integer.parseInt(row.substring(0, space));
        row = row.substring(space+1);
      }
      ans[i][c-1] = Integer.parseInt(row);
    }
    return ans;
  }

  private static ArrayList<Instruction> readBronzeInstructions(String filename, int r) throws FileNotFoundException {
    ArrayList<Instruction> ans = new ArrayList<Instruction>();
    Scanner in = new Scanner(new File(filename));
    for (int i = 0; i <= r; i++) in.nextLine();
    while (in.hasNextLine()) {
      ans.add(new Instruction(in.nextLine()));
    }
    return ans;
  }

  private static void stomp(int[][] pasture, Instruction inst) {
    int r = inst.row();
    int c = inst.col();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (pasture[inst.row()+i][inst.col()+j] > pasture[r][c]){
          r = inst.row()+i;
          c = inst.col()+j;
        }
      }
    }

    int elev = pasture[r][c] - inst.depth();

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (pasture[inst.row()+i][inst.col()+j] > elev) {
          pasture[inst.row()+i][inst.col()+j] = elev;
        }
      }
    }
  }

  public static int ctravel(String filename) throws FileNotFoundException {
    int[][] pasture;

    int[] specs = readSilverSpecs(filename);
    int rows = specs[0];
    int cols = specs[1];
    int time = specs[2];

    int[] coords = readSilverCoords(filename, rows);
    int r1 = coords[0]-1;
    int c1 = coords[1]-1;
    int r2 = coords[2]-1;
    int c2 = coords[3]-1;

    pasture = readSilverBoard(filename, rows, cols);

    pasture[r1][c1] = 1;

    for (int t = 1; t <= time; t++) {
      for (int i = 0; i < rows; i++) {
        for (int j = (t+i+r1+c1) % 2; j < cols; j+=2) {
          if (pasture[i][j] >= 0) {
            if (i > 0 && pasture[i-1][j] >= 0) {
              pasture[i][j] += pasture[i-1][j];
            }
            if (i < rows - 1 && pasture[i+1][j] >= 0) {
              pasture[i][j] += pasture[i+1][j];
            }
            if (j > 0 && pasture[i][j-1] >= 0) {
              pasture[i][j] += pasture[i][j-1];
            }
            if (j < cols - 1 && pasture[i][j+1] >= 0) {
              pasture[i][j] += pasture[i][j+1];
            }
          }
        }
      }
      for (int i = 0; i < rows; i++) {
        for (int j = (t+i+r1+c1+1) % 2; j < cols; j+=2) {
          if (pasture[i][j] >= 0) pasture[i][j] = 0;
        }
      }
    }
    return pasture[r2][c2];
  }

  private static int[] readSilverSpecs(String filename) throws FileNotFoundException {
    Scanner in = new Scanner(new File(filename));
    String dimensions = in.nextLine();
    int index = dimensions.indexOf(' ');
    int rows = Integer.parseInt(dimensions.substring(0, index));
    dimensions = dimensions.substring(index+1);
    index = dimensions.indexOf(' ');
    int cols = Integer.parseInt(dimensions.substring(0, index));
    int time = Integer.parseInt(dimensions.substring(index+1));
    return new int[] {rows, cols, time};
  }

  private static int[] readSilverCoords(String filename, int r) throws FileNotFoundException {
    Scanner in = new Scanner(new File(filename));
    for (int i = 0; i <= r; i++) {
      in.nextLine();
    }
    String line = in.nextLine();
    int index = line.indexOf(' ');
    int r1 = Integer.parseInt(line.substring(0, index));
    line = line.substring(index+1);
    index = line.indexOf(' ');
    int c1 = Integer.parseInt(line.substring(0, index));
    line = line.substring(index+1);
    index = line.indexOf(' ');
    int r2 = Integer.parseInt(line.substring(0, index));
    int c2 = Integer.parseInt(line.substring(index+1));
    return new int[] {r1, c1, r2, c2};
  }

  private static int[][] readSilverBoard(String filename, int r, int c) throws FileNotFoundException {
    int[][] ans =  new int[r][c];
    Scanner in = new Scanner(new File(filename));
    in.nextLine();
    for (int i = 0; i < r; i++) {
      String line = in.nextLine();
      for (int j = 0; j < c; j++) {
        switch (line.charAt(j)) {
          case '.': ans[i][j] = 0;
          break;
          case '*': ans[i][j] = -1;
          break;
          default: throw new IllegalStateException("illegal character");
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    try {
      System.out.println("makelake: "+makelake("testCases/makelake."+args[0]+".in"));
      System.out.println("ctravel: "+ctravel("testCases/ctravel."+args[0]+".in"));
    } catch (FileNotFoundException e) {}
  }
}


class Instruction {
  private int r;
  private int c;
  private int depth;

  public Instruction (String rule) {
    int i = rule.indexOf(' ');
    r = Integer.parseInt(rule.substring(0, i))-1;
    rule = rule.substring(i+1);
    i = rule.indexOf(' ');
    c = Integer.parseInt(rule.substring(0, i))-1;
    depth = Integer.parseInt(rule.substring(i+1));
  }

  public int row() {
    return r;
  }

  public int col() {
    return c;
  }

  public int depth() {
    return depth;
  }

  public String toString() {
    return r + " " + c + " " + depth;
  }
}
