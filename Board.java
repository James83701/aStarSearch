import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Board {
    
    Random rand = new Random();
    Scanner scnr = new Scanner(System.in);

    Node[][] boardArr = new Node[15][15];
    Node start;
    Node goal;
    Board(){
        initFill();
    }

    public void printBoard(){
        for(int row = 0; row < 15; row++){
            for(int col = 0; col < 15; col++){
                if(boardArr[row][col].getOnVictoryPath()){
                    System.out.print(boardArr[row][col].getVictoryPathValue() + " ");
                    if(boardArr[row][col].getVictoryPathValue() < 10){
                        System.out.print(" ");
                    }
                }else if(boardArr[row][col].getType() == 1){
                System.out.print( "X  ");
                }else if(boardArr[row][col].getType()==2){
                    System.out.print( "S  ");
                }else if(boardArr[row][col].getType()==3){
                    System.out.print( "G  ");
                }else{
                    System.out.print( "O  ");
                }
            }
            System.out.println("");
        }
    }

    public void printBoardOpenAndClosed(ArrayList<Node> open, ArrayList<Node> closed, Node currentNode){ 
        for(int row = 0; row < 15; row++){
            for(int col = 0; col < 15; col++){
                if(boardArr[row][col].getOnVictoryPath()){
                    System.out.print(boardArr[row][col].getVictoryPathValue() + " ");
                    if(boardArr[row][col].getVictoryPathValue() < 10){
                        System.out.print(" ");
                    }
                }else if(currentNode.getRow() == boardArr[row][col].getRow() && currentNode.getCol() == boardArr[row][col].getCol()){
                    System.out.print("!  ");
                }else if(open.contains(boardArr[row][col]) && boardArr[row][col].getType() != 2){
                    System.out.print("E  ");
                }else if(closed.contains(boardArr[row][col]) && boardArr[row][col].getType() != 2){
                    System.out.print("P  ");
                }else if(boardArr[row][col].getType() == 1){
                System.out.print( "X  ");
                }else if(boardArr[row][col].getType()==2){
                    System.out.print( "S  ");
                }else if(boardArr[row][col].getType()==3){
                    System.out.print( "G  ");
                }else{
                    System.out.print( "O  ");
                }
            }
            System.out.println("");
        }
    }
    
    

    

    public void initFill(){
        for(int row = 0; row < 15; row++){
            for(int col = 0; col < 15; col++){
                if(rand.nextInt(10) == 1){
                    this.boardArr[row][col] = new Node(row, col, 1);
                    boardArr[row][col].setG(999);
                }else{
                    this.boardArr[row][col] = new Node(row, col, 0);
                    boardArr[row][col].setG(999);
                }
            }
            
        }

    }

    public void enterStart(){
        System.out.print("please enter a int for starting node column between 0 and 14: ");
        int col = scnr.nextInt();
        System.out.print("please enter a int for starting node row between 0 and 14: ");
        int row = scnr.nextInt();

        // int col = rand.nextInt(15);
        // int row = rand.nextInt(15);
        boardArr[row][col].setType(2);
        start = boardArr[row][col];
        boardArr[row][col].setF(0);
        boardArr[row][col].setG(0);
    }

    public void enterGoal(){
        //System.out.println("please enter a int for goal node row:");
        //int col = scnr.nextInt();

        System.out.print("please enter a int for goal node column between 0 and 14: ");
        int col = scnr.nextInt();
        System.out.print("please enter a int for goal node row between 0 and 14: ");
        int row = scnr.nextInt();

        // int col = rand.nextInt(15);
        // int row = rand.nextInt(15);
        boardArr[row][col].setType(3);
        goal = boardArr[row][col];
    }      

    public Node getStartNode(){
        return start;
    }

    public Node getGoalNode(){
        return goal;
    }

    public Node[][] getBoardArr(){
        return boardArr;
    }
}
