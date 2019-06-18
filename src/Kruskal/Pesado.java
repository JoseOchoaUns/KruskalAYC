package Kruskal;

import java.util.ArrayList;



public class Pesado {
	private int nodo1;
	private int nodo2;
	private int peso;
	
	public Pesado(int n1,int n2, int peso) {
		// TODO Auto-generated constructor stub
		nodo1=n1;
		nodo2=n2;
		this.peso = peso;
	}
	public int getPeso() {return peso;}
	public int getN1() {
		return nodo1;
	}
	public int getN2() {
		return nodo2;
	}
	public int[] getNodes() {
		int [] ret=new int[2];
		ret[0]=nodo1;
		ret[1]=nodo2;
		return ret;
	}
	
}