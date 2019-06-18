package Kruskal;

import java.util.Comparator;

public class Comparador implements Comparator<Pesado> {
	@Override
	public int compare(Pesado p1, Pesado p2) {
		return Integer.compare(p1.getPeso(), p2.getPeso());
	}
}