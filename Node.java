public class Node {
	
	private int row, col, f, g, h, type;
	private Node parent;
	private Boolean onVictoryPath;
	int victoryPathValue;
    
	public Node(int r, int c, int t){
		row = r;
		col = c;
		type = t;
		parent = null;
		g = 999;
		onVictoryPath = false;
		victoryPathValue = 0;

		//type 0 is traverseable, 1 is not, 2 is start, 3 is goal
	}
	
	//mutator methods to set values
	
	public void setG(int value){
		g = value;
	}

	public void setVictoryPathValue(int value){
		victoryPathValue = value;
	}

	public void setH(int value){
		h = value;
	}
	public void setF(int value){
		f = value;
	}
	public void setParent(Node n){
		parent = n;
	}

	public void setType(int value){
		type = value;
	}

	
	//accessor methods to get values
	public int getVictoryPathValue(){
		return victoryPathValue;
	}

	public int getF(){
		return g + h;
	}
	public int getG(){
		return g;
	}
	public int getH(){
		return h;
	}
	public Node getParent(){
		return parent;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}

	public int getType(){
		return type;
	}

	public Boolean getOnVictoryPath(){
		return onVictoryPath;
	}
	
	public boolean equals(Object in){
		//typecast to Node
		Node n = (Node) in;
		
		return row == n.getRow() && col == n.getCol();
	}
    
	public String toString(){
		return "Node: " + row + "_" + col;
	}

	public void setOnVictoryPath(Boolean value){
		onVictoryPath = value;
	}
	
}
