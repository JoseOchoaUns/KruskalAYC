package MinHeap;

import Kruskal.Pesado;

public class CPTest {
	public static void main(String[] args) {
		Pesado p1=new Pesado(1,2,5);
		Pesado p2=new Pesado(1,2,6);
		Pesado p3=new Pesado(1,2,7);
		Pesado p4=new Pesado(1,2,8);
		Pesado p5=new Pesado(1,2,8);
		Pesado p6=new Pesado(1,2,9);
		Pesado p7=new Pesado(1,2,10);
		Pesado p8=new Pesado(1,2,1);
		Pesado p9=new Pesado(1,2,11);
		Pesado p10=new Pesado(1,2,12);
		Pesado p11=new Pesado(1,2,111);
		
		MinHeap m=new ColaCP(11);
		m.add(p1);
		System.out.println(m.min().getPeso());
		m.add(p2);
		System.out.println(m.min().getPeso());
		m.add(p3);
		System.out.println(m.min().getPeso());
		m.add(p4);
		System.out.println(m.min().getPeso());
		m.add(p5);
		System.out.println(m.min().getPeso());
		m.add(p6);
		System.out.println(m.min().getPeso());
		m.add(p7);
		System.out.println(m.min().getPeso());
		m.add(p8);
		System.out.println(m.min().getPeso());
		m.add(p9);
		System.out.println(m.min().getPeso());
		m.add(p10);
		System.out.println(m.min().getPeso());
		m.add(p11);
		System.out.println(m.min().getPeso());
		System.out.println(m.toString());

		System.out.println(m.removeMin().getPeso());
		System.out.println(m.toString());
		System.out.println(m.removeMin().getPeso());
		System.out.println(m.toString());
		System.out.println(m.removeMin().getPeso());
		System.out.println(m.toString());
		System.out.println(m.removeMin().getPeso());
		System.out.println(m.toString());
		System.out.println(m.removeMin().getPeso());
		System.out.println(m.toString());
		System.out.println(m.removeMin().getPeso());
		System.out.println("Agrego gilada");
		System.out.println(m.toString());
		System.out.println(m.min().getPeso());
		m.add(p7);
		System.out.println(m.min().getPeso());
		m.add(p8);
		System.out.println(m.min().getPeso());
		System.out.println("vuelvo a sacar");
		System.out.println(m.toString());
		while(!m.isEmpty())
			System.out.println(m.removeMin().getPeso());

		
	}

}
