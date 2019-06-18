package Kruskal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

import DisjoinSet.DisjointSetConHeuristicas;
import DisjoinSet.DisjointSetSinHeuristicas;

import java.util.Comparator;
import java.util.HashMap;



public class Grafo {
	
	private GrafoObj g;
	private Map<Integer,Vector<Pesado>> adyacentes;
	private ArrayList<Pesado> arcos;
	private int[] nodos;

	

	

	public Vector<Pesado> getArcosAdyacentes(int nodo){
		
		return adyacentes.get((Integer)nodo);
	}
	
	public int getNodosCount(){
		return nodos.length;
	}
	
	public int getArcosCount(){
		return arcos.size();
	}
	public int[] getNodos() {
		return nodos;
	}
	public ArrayList<Pesado> getArcos(){
		return arcos;
	}
	
	@SuppressWarnings("rawtypes")
	public Grafo(GrafoObj grafoJson){
		g=grafoJson;
		Object[][] arcosJson = grafoJson.arcos;
		adyacentes=new HashMap();
		nodos=grafoJson.nodos;
		arcos=new ArrayList();
		
		for(int i : grafoJson.nodos) {
			adyacentes.put(new Integer(i),new Vector());
		}	
		
		for (int i = 0; i<arcosJson.length; i++){
			int aux1=((Double) ((ArrayList) arcosJson[i][0]).get(0)).intValue();
			int aux2=((Double) ((ArrayList) arcosJson[i][0]).get(1)).intValue();
			Pesado p=new Pesado(aux1,aux2,((Double) arcosJson[i][1]).intValue());
			adyacentes.get(aux1).add(p);
			adyacentes.get(aux2).add(p);
			arcos.add(p);
		}
		
	}
	
	
	

	public static class GrafoObj {
		int[] nodos;
		Object[][] arcos;
	}


	public void mostrarArbol(ArrayList<Pesado> lista){
		for(Pesado p : lista){
			System.out.println("("+ p.getN1()+" , "+ p.getN2() + ") peso:" + p.getPeso());
		}
	}
	
	public Grafo clone(){
		
		Grafo salida = new Grafo(g);
		return salida;  
		}
}