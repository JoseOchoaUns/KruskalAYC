package Kruskal;

import java.util.HashMap;
import java.util.Iterator;

public class DisjointSetConHeuristicas {
	
	private static class Node {
		int rank;
		int parent;

		Node(int parent, int rank) {
			this.parent = parent;
			this.rank = rank;
		}
	}

	private final HashMap<Integer, Node> objectsToNodes = new HashMap <Integer, Node>();
	private int cantSet = 0;

	public int findSet(int o) {
		DisjointSetConHeuristicas.Node node = (DisjointSetConHeuristicas.Node) objectsToNodes.get(o);
		if (o != node.parent)
			node.parent = findSet(node.parent);
		return node.parent;
	}

	public void makeSet(int o) {
		objectsToNodes.put(o, new Node(o, 0));
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
		/*int setActual = this.findSet(0);
		for(int i = 1; i < this.objectsToNodes.size(); i ++){
			if(this.findSet(i) != setActual){
				return false;
			}
		}
        return true;*/
    	return this.cantSet == 1;
    }

	public void union(int x, int y) {
		int setX = findSet(x);
		int setY = findSet(y);
		DisjointSetConHeuristicas.Node nodeX = objectsToNodes.get(setX);
		DisjointSetConHeuristicas.Node nodeY = objectsToNodes.get(setY);
		if (nodeX.rank > nodeY.rank) {
			nodeY.parent = x;
		} else {
			nodeX.parent = y;
			if (nodeX.rank == nodeY.rank)
				nodeY.rank++;
		}
		this.cantSet--;
	}

	
}