public class Main {
    public static void main(String args[]){
        Board board = new Board();
        
        board.enterStart();
        board.enterGoal();

        board.printBoard();
        
        AI ai = new AI(board.getStartNode(), board, board.getBoardArr(), board.getGoalNode());
        ai.findNextNode();
        
        
    }
}
