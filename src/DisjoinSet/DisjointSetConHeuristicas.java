package DisjoinSet;

public class DisjointSetConHeuristicas implements DisjoinSet{

	private int cantSet = 0;
	private NodeH[] objectsToNodes;

	public DisjointSetConHeuristicas(int size){
		this.objectsToNodes = new NodeH[size];
	}

	public int findSet(int o) {
		NodeH node = objectsToNodes[o];

		if (o != node.getParent())
			node.setParent(findSet(node.getParent()));
		return node.getParent();
	}

	public void makeSet(int o) {
		this.objectsToNodes[o] = new NodeH(o,0);
		this.cantSet++;
	}

    public boolean unicoSet() {
    	return this.cantSet == 1;
    }

	public void union(int x, int y) {
		int setX = findSet(x);
		int setY = findSet(y);
		NodeH nodeX = objectsToNodes[setX];
		NodeH nodeY = objectsToNodes[setY];
		if (nodeX.rank > nodeY.rank) {
			nodeY.setParent(x);
		} else {
			nodeX.setParent(y);
			if (nodeX.rank == nodeY.rank)
				nodeY.rank++;
		}
		this.cantSet--;
	}
}