import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Dannyang27 on 14/11/17.
 */
public class MazeFinal {
    //File Path
    private static String FILEPATH = "src/files/";

    String [] [] maze;
    int width, height, startX, startY, endX, endY;
    boolean solved = false;

    public MazeFinal(int num) {
        switch (num){
            case 0:{
                FILEPATH += "example.txt";
                break;
            }
            case 1:{
                FILEPATH += "input.txt";
                break;
            }
            case 2:{
                FILEPATH += "small.txt";
                break;
            }
            case 3:{
                FILEPATH += "sparse_medium.txt";
                break;
            }
            case 4:{
                FILEPATH += "medium_input.txt";
                break;
            }
            case 5:{
                FILEPATH += "large_input.txt";
                break;
            }
            default: break;
        }
    }


    public void readFile(){

        BufferedReader breader = null;
        FileReader freader = null;

        File relative = new File(FILEPATH);
        try {
            freader = new FileReader(relative);
            breader = new BufferedReader(freader);

            String sCurrentLine;

            int i = 0;
            while ((sCurrentLine = breader.readLine()) != null) {

                if(i < 3) {
                    fillVariable(sCurrentLine, i);
                }else{
                    fillMaze(sCurrentLine, i - 3);
                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            maze[startY][startX] = "S";
            maze[endY][endX] = "E";
            try {
                if (breader != null)
                    breader.close();

                if (freader != null)
                    freader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Check whether next move is possible
    public boolean isReachable(int x, int y){
        return (x >= 0 &&  x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != "#" );

    }

    //Recursive method in order to solve the maze problem
    public boolean solveMaze(int x, int y, String direction){

        if (maze[x][y] == "E"){
            solved = true;
            return solved;

        }

        if(!(maze[x][y].equals("S") || maze[x][y].equals("E"))){
            maze[x][y] = "X";
        }

        if(direction != "up" && isReachable(x+1, y)){
            solveMaze(x + 1, y, "down");
            if(solved) return true;
        }
        if(direction != "left" && isReachable(x, y+ 1)){
            solveMaze(x, y + 1, "right");
            if(solved) return true;
        }
        if(direction != "down" && isReachable(x-1, y)){
            solveMaze(x - 1 , y, "up");
            if(solved) return true;
        }
        if(direction != "right" && isReachable(x, y -1)){
            solveMaze(x, y - 1, "left");
            if(solved) return true;
        }

        //If cannot move, do backtracking
        if(!solved) {
            maze[x][y] = " ";
            return false;
        }

        return false;
    }

    //Change 1's to # and 0' to " "

    private void fillMaze(String sCurrentLine, int i) {

        String [] splitted = sCurrentLine.split(" ");

        for(int j = 0; j < maze[0].length; j++){
            if(splitted[j].equals("1")) {
                maze[i][j] = "#";
            }else{
                maze[i][j] = " ";
            }
        }


    }

    //Set value to variables width, height, startX .... endY
    public void fillVariable(String str, int i){

        String [] splitted = str.split(" ");

        switch (i){
            case 0 : {
                width = Integer.parseInt(splitted[0]);
                height = Integer.parseInt(splitted[1]);
                maze = new String[height][width];

                break;
            }
            case 1 :{
                startX = Integer.parseInt(splitted[0]);
                startY = Integer.parseInt(splitted[1]);
                break;
            }
            case 2 :{
                endX = Integer.parseInt(splitted[0]);
                endY = Integer.parseInt(splitted[1]);
                break;
            }
            default: break;
        }


    }

    //Display the maze on the console
    public void displayMaze(){
        System.out.println("---- SOLUTION------");
        for(int j = 0; j < maze.length; j++){
            for(int k = 0; k < maze[0].length; k++){
                System.out.print(maze[j] [k]);
            }
            System.out.println();

        }
    }


}
