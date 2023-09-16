/*
 * Nathalie Marie Villa
 * CS 114 021
 */


import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    private static char[][] maze;
    private static int startRow = 2; 
    private static int startCol = 0;
    private static int numRows, numCols;

    public static void main(String[] args){
        readFile("maze.dat");
        if (traverseMaze(startRow, startCol)){
            System.out.println("The maze was solved!");
            printMaze();
        } else {
            System.out.println("The maze could not be solved.");
        }
    }

    public static void readFile(String fileName){
        ArrayList<String> mazeArray = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner scan = new Scanner(f);
            while (scan.hasNext()) {
                String data = scan.nextLine();
                mazeArray.add(data);
                if (data.length() > numCols)
                    numCols = data.length();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }

        numRows = mazeArray.size();
        maze = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            String r = mazeArray.get(i);
            for (int j = 0; j < r.length(); j++) {
                if (r.length() >= j){
                    maze[i][j] = r.charAt(j++);
                }else{
                    maze[i][j] = 'X';
                }    
            }          
        }
    }

    private static boolean traverseMaze(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            return false; 
        }
        if (maze[row][col] == 'X' || maze[row][col] == '.') {
            return false;
        }
        if (maze[row][col] == '-') {
            return true; 
        }
        maze[row][col] = '.';
        if(traverseMaze(row - 1, col) || traverseMaze(row + 1, col) || traverseMaze(row, col - 1) || traverseMaze(row, col + 1)){
            maze[row][col] = '+';
            return true;
        }
        return false;
    }

    private static void printMaze() {
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }

    }
}
