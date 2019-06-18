package DisjoinSet;

public interface DisjoinSet {
	public int findSet(int x);
	public void makeSet(int x);
	public void union(int x,int y);

}
