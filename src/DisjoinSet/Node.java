package DisjoinSet;

public class Node {
	
	protected int rank;
	protected int parent;

	Node(int parent) {
			this.parent = parent;
			
	}
	
	public int getParent() {return parent;}
	public void setParent(int p) {parent=p;}
	
}