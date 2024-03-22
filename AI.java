import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.lang.Math;
import java.util.Scanner;
import java.util.Set;

public class AI {
    //ArrayList<Node> closed = new ArrayList<Node>();
    //ArrayList<Node> open = new ArrayList<Node>();
    Node currentNode;
    Node startNode;
    Board board;
    Node[][] boardArr;
    Node goalNode;
    ArrayList<Node> victoryPathInOrder = new ArrayList<Node>();

    Set<Node> open = new HashSet<Node> ();
    Set<Node> closed = new HashSet<Node> ();
    

    AI(Node startNode, Board board, Node[][] boardArr, Node goalNode) {
        currentNode = startNode;
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.board = board;
        this.boardArr = boardArr;
        open.add(currentNode);
    }

    public void findNextNode() {
        
        while (!open.isEmpty()) {

            
            open.remove(currentNode);

            closed.add(currentNode);
            
            ArrayList<Node> adjNodes = getAdjNodes(currentNode);


            if (generateSuccesors(adjNodes)) {
                break;
            }

            currentNode = findLowestCostNode();

        }
        // if (currentNode.getType() != 3) {
        //     System.out.println("no path found");
        // }
        Node iterNode = currentNode;
        ArrayList<Node> victoryPath = new ArrayList<Node>();
        
        while (iterNode.getParent() != null) {

            
            victoryPath.add(iterNode);
            iterNode = iterNode.getParent();

        }

        for(int i = victoryPath.size(); i>0; i-- ){
            Node node = victoryPath.get(i-1);
            boardArr[node.getRow()][node.getCol()].setOnVictoryPath(true);
            boardArr[node.getRow()][node.getCol()].setVictoryPathValue(victoryPath.size() - i + 1);
            victoryPathInOrder.add(node);
        }
        board.printBoard();
        System.out.println("victory path: " + victoryPathInOrder);
    }

    public Boolean generateSuccesors(ArrayList<Node> adjNodes) {
        for (Node node : adjNodes) {

            node.setParent(currentNode);

            if (node.getCol() == goalNode.getCol() && node.getRow() == goalNode.getRow()) {
                return true;
            } else {
                // calculates cost
                node.setG(currentNode.getG() + 1);

                int colDistance = Math.abs(node.getCol() - goalNode.getCol());
                int rowDistance = Math.abs(node.getRow() - goalNode.getRow());
                int estimatedCost = colDistance + rowDistance;
                node.setH(estimatedCost);
                ArrayList<Node> nodesToAdd = new ArrayList<Node>();

                if (open.size() == 0) {
                    
                    open.add(node);
                } else {

                    for (Node nodeInOpen : open) {
                        if (node.getCol() == nodeInOpen.getCol() && node.getRow() == nodeInOpen.getRow()
                                && node.getF() > nodeInOpen.getF()) {
                            continue;
                        } else {

                            if(!nodesToAdd.contains(node)){
                                nodesToAdd.add(node);
                            }
                        }
                    }
                    open.addAll(nodesToAdd);
                    nodesToAdd.clear();
                }

            }

        }
        return false;
    }

    public Node findLowestCostNode() {
        
        Node lowestCostNode = open.iterator().next();
        // lowestCostNode = q
        for (Node node : open) {
            if (lowestCostNode.getF() > node.getF()) {
                lowestCostNode = node;
            }
        }

        return lowestCostNode;
    }

    public boolean validateNodeRange(int row, int col) {
        boolean traversable = false;

        boolean rowRangeValid = 0 <= row && row <= 14;
        boolean colRangeValid = 0 <= col && col <= 14;

        if (!rowRangeValid || !colRangeValid) {
            return false;
        }

        // if (open.contains(boardArr[row][col])) {
        // return false;
        // }

        if (closed.contains(boardArr[row][col])) {
            return false;
        }

        traversable = boardArr[row][col].getType() != 1;

        return rowRangeValid && colRangeValid && traversable;
    }

    public ArrayList<Node> getAdjNodes(Node node) {
        ArrayList<Node> adjNodes = new ArrayList<Node>();
        if (validateNodeRange(node.getRow() + 1, node.getCol())) {
            adjNodes.add(boardArr[node.getRow() + 1][node.getCol()]);
        }

        if (validateNodeRange(node.getRow(), node.getCol() + 1)) {
            adjNodes.add(boardArr[node.getRow()][node.getCol() + 1]);
        }

        if (validateNodeRange(node.getRow() - 1, node.getCol())) {
            adjNodes.add(boardArr[node.getRow() - 1][node.getCol()]);
        }

        if (validateNodeRange(node.getRow(), node.getCol() - 1)) {
            adjNodes.add(boardArr[node.getRow()][node.getCol() - 1]);
        }

        

        return adjNodes;
    }

}

