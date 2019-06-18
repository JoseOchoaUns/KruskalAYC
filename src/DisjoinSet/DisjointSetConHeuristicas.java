package DisjoinSet;

import java.util.HashMap;
import java.util.Iterator;

public class DisjointSetConHeuristicas implements DisjoinSet{

	private final HashMap<Integer, NodeH> objectsToNodes = new HashMap <Integer, NodeH>();
	private int cantSet = 0;

	public int findSet(int o) {
		NodeH node = (NodeH) objectsToNodes.get(o);

		if (o != node.getParent())
			node.setParent(findSet(node.getParent()));
		return node.getParent();
	}

	public void makeSet(int o) {
		objectsToNodes.put(o, new NodeH(o, 0));
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

    public boolean unicoSet() {
    	return this.cantSet == 1;
    }

	public void union(int x, int y) {
		int setX = findSet(x);
		int setY = findSet(y);
		NodeH nodeX = objectsToNodes.get(setX);
		NodeH nodeY = objectsToNodes.get(setY);
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