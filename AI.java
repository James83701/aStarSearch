import java.util.ArrayList;
public class AI {
    ArrayList<Node> explored = new ArrayList<Node>();
    ArrayList<Node> frontier = new ArrayList<Node>();
    Node currentNode;
    Board board;
    Node[][] boardArr;
    
    AI(Node startNode, Board board, Node[][] boardArr){
        currentNode = startNode;
        this.board = board;
        this.boardArr = boardArr; 
        frontier.add(currentNode);
    }

    public boolean validateNodeRange(int row, int col){
        boolean traversable = false;

        boolean rowRangeValid = 0 <= row && row <= 14;
        boolean colRangeValid = 0 <= col && col <= 14;
        
        if(!rowRangeValid || !colRangeValid){
            return false;
        }

        traversable = boardArr[row][col].getType()!=0;

        return rowRangeValid && colRangeValid && traversable;
    }

    public ArrayList<Node> getAdjNodes(Node node){
        ArrayList<Node> adjNodes = new ArrayList<Node>();
        if(validateNodeRange(node.getRow()+1, node.getCol())){
            adjNodes.add(boardArr[node.getRow()+1][node.getCol()]);
        }

        if(validateNodeRange(node.getRow(), node.getCol()+1)){
            adjNodes.add(boardArr[node.getRow()][node.getCol()+1]);
        }

        if(validateNodeRange(node.getRow()-1, node.getCol())){
            adjNodes.add(boardArr[node.getRow()-1][node.getCol()]);
        }

        if(validateNodeRange(node.getRow(), node.getCol()-1)){
            adjNodes.add(boardArr[node.getRow()][node.getCol()-1]);
        }
        
        return adjNodes;
    }

    public Node findLowestFScoreNodeInFrontier(){
        if (frontier.get(0) == null) {
            frontier.addAll(getAdjNodes(currentNode));
        }
        calculateScores()////
        Node lowestCostNode = frontier.get(0);
        for (Node node : frontier) {
            if(node.getF()<lowestCostNode.getF()){
                lowestCostNode = node;
            }
        }
        frontier.remove(lowestCostNode);
        
        return lowestCostNode;
    }

    public void NorthNode(){

    }

    public void nextStep(){
        System.out.println((findLowestFScoreNodeInFrontier()));


        
    }
}
