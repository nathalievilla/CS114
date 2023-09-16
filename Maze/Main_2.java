import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main_2 {

    private static char[][] maze;
    private static int[] start;
    private static int numRows;
    private static int numColumns;

    public static void main(String[] args) {
        readFile();
        if (traverse(start[0], start[1])) {
            System.out.println("The maze was solved!");
            printMaze();
        } else {
            System.out.println("The maze could not be solved.");
        }
    }

    private static void readFile() {
        File file = new File("maze.dat");
        try {
            Scanner scanner = new Scanner(file);
            numRows = scanner.nextInt();
            numColumns = scanner.nextInt();
            maze = new char[numRows][numColumns];
            scanner.nextLine();
            int rowIndex = 0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for(int columnIndex = 0; columnIndex < line.length(); columnIndex++) {
                    maze[rowIndex][columnIndex] = line.charAt(columnIndex);
                    if(line.charAt(columnIndex) == '+') {
                        start = new int[]{rowIndex, columnIndex};
                    }
                }
                rowIndex++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static boolean traverse(int i, int j) {
        if (i < 0 || j < 0 || i >= numRows || j >= numColumns) {
            return false;
        }
        if (maze[i][j] == 'X' || maze[i][j] == '.') {
            return false;
        }
        if (maze[i][j] == '-') {
            return true;
        }
        maze[i][j] = '.';
        if (traverse(i - 1, j) || traverse(i + 1, j) || traverse(i, j - 1) || traverse(i, j + 1)) {
            maze[i][j] = '+';
            return true;
        }
        return false;
    }

    private static void printMaze() {
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}
