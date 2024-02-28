import java.util.Random;
public class Board {
    
    Random rand = new Random();
    Node[][] boardArr = new Node[15][15];
    
    Board(){
        initFill();
    }

    public void printBoard(){
        for(int row = 0; row < 15; row++){
            for(int col = 0; col < 15; col++){
                if(boardArr[row][col].getType() == 1){
                System.out.print( "X ");
                }else{
                    System.out.print( "O ");
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
                }else{
                    this.boardArr[row][col] = new Node(row, col, 0);
                }
            }
            
        }

    }
}
