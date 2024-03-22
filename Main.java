import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        Scanner scnr = new Scanner(System.in);
        boolean continueLoop = true;
        while (continueLoop) {

            System.out.println("if you want to generate a path, type 'y': ");
            // char s = scnr.nextLine().charAt(0);
            // System.out.println(s=='y');
            continueLoop = scnr.nextLine().charAt(0) == 'y' ? true : false;
            Board board = new Board();
            board.enterStart();
            board.enterGoal();
            AI ai = new AI(board.getStartNode(), board, board.getBoardArr(), board.getGoalNode());
            ai.findNextNode();
            

        }

    }
}
