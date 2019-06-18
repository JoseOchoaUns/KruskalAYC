package DisjoinSet;

public class DisjointSetSinHeuristicas implements DisjoinSet{


	private Node[] objectsToNodes;
	private int cantSet = 0;
	
	public DisjointSetSinHeuristicas(int size){
		this.objectsToNodes = new Node[size];
	}


	public int findSet(int o) {
		Node node = objectsToNodes[o];
		
		if (node.getParent()!= o)
			return findSet(node.getParent());
		return node.getParent();
	}

	public void makeSet(int o) {
		this.objectsToNodes[o] = new Node(o);
		this.cantSet++;
	}

    public boolean unicoSet(){
    	return this.cantSet == 1;
    }

	public void union(int x, int y) {
		int setX = findSet(x);
		Node nodeX = objectsToNodes[setX];
		nodeX.setParent(y);
		this.cantSet--;
	}
}