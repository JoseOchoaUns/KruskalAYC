package MinHeap;

import Kruskal.Pesado;

public interface MinHeap {
	public Pesado min();
	public Pesado removeMin();
	public void add(Pesado p);
	public boolean isEmpty();
	public String toString();
}
