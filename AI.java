import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class AI {
    ArrayList<Node> closed = new ArrayList<Node>();
    ArrayList<Node> open = new ArrayList<Node>();
    Node currentNode;
    Node startNode;
    Board board;
    Node[][] boardArr;
    Node goalNode;

    AI(Node startNode, Board board, Node[][] boardArr, Node goalNode) {
        currentNode = startNode;
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.board = board;
        this.boardArr = boardArr;
        open.add(currentNode);
    }

    public void findNextNode() {
        while (open.get(0) != null) {

            open.remove(currentNode);

            closed.add(currentNode);

            ArrayList<Node> adjNodes = getAdjNodes(currentNode);


            if (generateSuccesors(adjNodes)) {
                break;
            }

            currentNode = findLowestCostNode();

        }
        Node iterNode = currentNode;
        ArrayList<Node> victoryPath = new ArrayList<Node>();
        System.out.print(currentNode + " ");
        while (iterNode.getParent() != null) {

            System.out.print(iterNode.getParent() + " ");
            victoryPath.add(iterNode);
            iterNode = iterNode.getParent();

        }
        System.out.println(victoryPath);
    }

    public Boolean generateSuccesors(ArrayList<Node> adjNodes) {
        for (Node node : adjNodes) {

            node.setParent(currentNode);

            if (node.getCol() == goalNode.getCol() && node.getRow() == goalNode.getRow()) {
                System.out.println("GOAL!!!!!!!!!!!!");
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
        
        Node lowestCostNode = open.get(0);
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

