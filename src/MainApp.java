import java.util.Scanner;

/**
 * Created by Dannyang27 on 14/11/17.
 */
public class MainApp {

    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select which maze you want to be solved: 0 - example.txt , " +
                "1 - input.txt , 2 - small.txt , 3 - sparse_medium.txt, " +
                "4 - medium_input.txt, 5 - large_input.txt");

        try {
            int num = scanner.nextInt();
            if (num >= 0 && num < 6) {
                MazeFinal mf = new MazeFinal(num);
                mf.readFile();
                if (mf.solveMaze(1, 1, " ")) {
                    System.out.println("Problem solved");
                } else {
                    System.out.println("No solutions");
                }
                mf.displayMaze();
            }
        }catch (Exception e){
            System.out.println("Choose Integer between 0 <= X < 6");
        }

    }
}
