package DisjoinSet;

import java.util.HashMap;
import java.util.Iterator;

public class DisjointSetSinHeuristicas implements DisjoinSet{


	private final HashMap<Integer, Node> objectsToNodes = new HashMap <Integer, Node>();
	private int cantSet = 0;
	
	public int findSet(int o) {
		Node node = (Node) objectsToNodes.get(o);
		
		if (node.getParent()!= o)
			return findSet(node.getParent());
		return node.getParent();
	}

	public void makeSet(int o) {
		objectsToNodes.put(o, new Node(o));
		this.cantSet++;
	}

	public void removeSet(int o) {
		int set = findSet(o);
		for (Iterator<Integer> it = objectsToNodes.keySet().iterator(); it.hasNext();) {
			int next = it.next();
			//remove the set representative last, otherwise findSet will fail
			if (next != set && findSet(next) == set)
				it.remove();
		}
		objectsToNodes.remove(set);
	}

    public boolean unicoSet(){
    	return this.cantSet == 1;
    }

	public void union(int x, int y) {
		int setX = findSet(x);
		Node nodeX = objectsToNodes.get(setX);
		nodeX.setParent(y);
		this.cantSet--;
	}
}