package DisjoinSet;

public class NodeH {
	
	protected int rank;
	protected int parent;

	NodeH(int parent, int rank) {
			this.parent = parent;
			this.rank = rank;
	}
	public int getRank() {return rank;}
	public int getParent() {return parent;}
	public void setRank(int r) {rank=r;}
	public void setParent(int p) {parent=p;}
}
