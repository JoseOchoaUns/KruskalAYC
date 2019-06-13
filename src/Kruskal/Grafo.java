package Kruskal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



public class Grafo {
	private int[] nodos;
	private ArrayList<Pesado> arcos;
	private String[] color;

	private static final String BLANCO = "Blanco";
	private static final String GRIS = "Gris";
	private static final String NEGRO = "Negro";

	private class Pesado {
		private Arco arco;
		private int peso;
		
		private Pesado(ArrayList<Integer> arcoLista, int peso) {
			// TODO Auto-generated constructor stub
			this.arco = new Arco(arcoLista.get(0), arcoLista.get(1));
			this.peso = peso;
		}

		private class Arco {
			private int nodo1;
			private int nodo2;
			
			public Arco(int i, int j) {
				// TODO Auto-generated constructor stub
				this.nodo1 = i;
				this.nodo2 = j;
			}
		}
	}

	public ArrayList<Pesado> getArcosAdyacentes(int nodo){
		ArrayList<Pesado> adyacentes = new ArrayList<Pesado>();
		for (Pesado pesado : this.arcos) {
			if(pesado.arco.nodo1 == nodo || pesado.arco.nodo2 == nodo){
				adyacentes.add(pesado);
			}
		}
		return adyacentes;
	}
	
	public int getNodosCount(){
		return this.nodos.length;
	}
	
	public int getArcosCount(){
		return this.arcos.size();
	}
	
	@SuppressWarnings("rawtypes")
	public Grafo(GrafoObj grafoJson){
		this.nodos = grafoJson.nodos;
		this.arcos = new ArrayList<Pesado>();
		this.color = new String[this.nodos.length];
		
		Object[][] arcosJson = grafoJson.arcos;
		
		for (int i = 0; i<arcosJson.length; i++){
			
			ArrayList<Integer> arcoLista = new ArrayList<>(); 
			arcoLista.add(((Double) ((ArrayList) arcosJson[i][0]).get(0)).intValue());
			arcoLista.add(((Double) ((ArrayList) arcosJson[i][0]).get(1)).intValue());
			Pesado pesado = new Pesado(arcoLista, ((Double) arcosJson[i][1]).intValue());
			this.arcos.add(pesado); 
		}
	}
	
	private void inicializarColores(){
		for(int i = 0; i < this.color.length ; i++){
			this.color[i] = BLANCO;
		}
	}

	public static class GrafoObj {
		int[] nodos;
		Object[][] arcos;
	}

	private int getNodoAdyacente(int nodoActual, Pesado pesado){
		if(pesado.arco.nodo1 == nodoActual){
			return pesado.arco.nodo2;
		}
		else{
			return pesado.arco.nodo1;
		}
	}

	public boolean esConexoBFS(){
		this.inicializarColores();
		Queue<Integer> cola= new LinkedList<Integer>();

		this.color[nodos[0]] = GRIS;
		cola.add(nodos[0]);
		visitarBFS(cola);

		for(Integer nodo: this.nodos){
			if(this.color[nodo].equals(BLANCO)){
				return false; //Si no es conexo entrara aca
			}
		}		
		return true; //Si es conexo, nunca entro en el else
	}

	private void visitarBFS(Queue<Integer> cola) {
		while(!cola.isEmpty()){
			int nodoActual = cola.peek();
			ArrayList<Pesado> adyacentes = this.getArcosAdyacentes(nodoActual);
			for(Pesado pesado : adyacentes){
				int nodoAdyacente = this.getNodoAdyacente(nodoActual, pesado);
				if(color[nodoAdyacente].equals(BLANCO)){
					color[nodoAdyacente] = GRIS;
					cola.add(nodoAdyacente);
				}
			}
			color[nodoActual] = NEGRO;
			cola.poll();
		}
	}

	public boolean esConexoDisjointSet(){
		DisjointSetConHeuristicas ds = new DisjointSetConHeuristicas();
		for(Integer nodo: this.nodos){
			ds.makeSet(nodo);
		}

		for(Pesado pesado : this.arcos){
			if(ds.findSet(pesado.arco.nodo1) != ds.findSet(pesado.arco.nodo2)){
				ds.union(pesado.arco.nodo1, pesado.arco.nodo2);
			}
		}
		
		return ds.unicoSet();
	}
}