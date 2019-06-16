package Kruskal;

import java.util.HashMap;
import java.util.Iterator;

public class DisjointSetSinHeuristicas {

	private static class Node {
		int parent;

		Node(int parent) {
			this.parent = parent;
		}
	}

	private final HashMap<Integer, Node> objectsToNodes = new HashMap <Integer, Node>();
	private int cantSet = 0;
	
	public int findSet(int o) {
		DisjointSetSinHeuristicas.Node node = (DisjointSetSinHeuristicas.Node) objectsToNodes.get(o);
		
		
		
		if (o != node.parent)
			return findSet(node.parent);
		return node.parent;
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
    	/*
		int setActual = this.findSet(0);
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
		DisjointSetSinHeuristicas.Node nodeX = objectsToNodes.get(setX);
		nodeX.parent = y;
		this.cantSet--;
	}
}