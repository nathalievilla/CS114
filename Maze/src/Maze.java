import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
    private static char[][] maze;
    private static int numRows, numCols;
    private static int startRow = 2;
    private static int startCol = 0;

    //main method
    public static void main(String[] args){
        readFile("maze.dat");
        if(traverse(startRow, startCol)){
            System.out.println("the maze was solved");
            printMaze();
        }else{
            System.out.println("maze not solved");
        }


    }

    //readFile method
    private static void readFile(String fileName){
        ArrayList<String> mazeArray = new ArrayList<String>(); 
        try{
            File f = new File(fileName);
            Scanner scan = new Scanner(f);
            while(scan.hasNext()){
                String data = scan.nextLine();
                mazeArray.add(data);
                if(data.length() > numCols){
                    numCols = data.length();
                }
            }
            scan.close();
        }catch (FileNotFoundException e){
            System.out.print("file not found");
            e.printStackTrace();
        }

        numRows = mazeArray.size();
        maze = new char[numRows][numCols];
        for(int i = 0; i < numRows; i++){
            String r = mazeArray.get(i);
            for(int j = 0; j < r.length(); j++){
                if(r.length() >= j){
                    maze[i][j] = r.charAt(j++);
                }else{
                    maze[i][j] = 'X';
                }
            }
        }

    }

    //traverse method
    private static boolean traverse(int row, int col){
        if(row < 0 || col < 0 || row >= numRows || col >= numCols){
            return false;
        }
        if(maze[row][col] == 'X' || maze[row][col] == '.'){
            return false;
        }
        if(maze[row][col] == '-'){
            return true; 
        }
        maze[row][col] = '.';
        if(traverse(row + 1, col) || traverse(row - 1, col) || traverse(row, col + 1) || traverse(row, col - 1)){
            maze[row][col] = '+';
            return true; 
        }
        return false;

    }

    //printMaze method

    private static void printMaze(){
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}
