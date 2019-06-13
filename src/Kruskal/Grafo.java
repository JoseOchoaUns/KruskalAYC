package Kruskal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Comparator;



public class Grafo {
	private int[] nodos;
	private ArrayList<Pesado> arcos;
	private String[] color;

	private static final String BLANCO = "Blanco";
	private static final String GRIS = "Gris";
	private static final String NEGRO = "Negro";

	public class Comparador implements Comparator<Pesado> {
		@Override
		public int compare(Pesado p1, Pesado p2) {
			return Integer.compare(p1.peso, p2.peso);
		}
	}

	public class Pesado {
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


	public ArrayList<Pesado> kruskalOrdenandoConHeuristicas(){
		ArrayList<Pesado> arbol = new ArrayList<Pesado>();
		Comparador comparador = new Comparador();
		DisjointSetConHeuristicas ds = new DisjointSetConHeuristicas();

		for(Integer nodo: this.nodos){
			ds.makeSet(nodo);
		}
		this.arcos.sort(comparador);

		for (Pesado arcoActual : this.arcos) {
			if(ds.findSet(arcoActual.arco.nodo1) != ds.findSet(arcoActual.arco.nodo2)){
				arbol.add(arcoActual);
				ds.union(arcoActual.arco.nodo1, arcoActual.arco.nodo2);
				if(arbol.size() == this.nodos.length - 1){
					return arbol;
				}
			}
		}

		return null;
	}

	public ArrayList<Pesado> kruskalOrdenandoSinHeuristicas(){
		ArrayList<Pesado> arbol = new ArrayList<Pesado>();
		Comparador comparador = new Comparador();
		DisjointSetSinHeuristicas ds = new DisjointSetSinHeuristicas();

		for(Integer nodo: this.nodos){
			ds.makeSet(nodo);
		}

		this.arcos.sort(comparador);

		for (Pesado arcoActual : this.arcos) {
			if(ds.findSet(arcoActual.arco.nodo1) != ds.findSet(arcoActual.arco.nodo2)){
				arbol.add(arcoActual);
				ds.union(arcoActual.arco.nodo1, arcoActual.arco.nodo2);
				if(arbol.size() == this.nodos.length - 1){
					return arbol;
				}
			}
		}

		return null;
	}

	public ArrayList<Pesado> kruskalMinHeapConHeuristicas(){
		ArrayList<Pesado> arbol = new ArrayList<Pesado>();
		DisjointSetConHeuristicas ds = new DisjointSetConHeuristicas();
		Comparador comparador = new Comparador();
		PriorityQueue <Pesado> minHeap = new PriorityQueue <Pesado>(comparador);
		Pesado arcoActual;

		for(Integer nodo: this.nodos){
			ds.makeSet(nodo);
		}

		for(Pesado pesado : this.arcos){
			minHeap.add(pesado);
		}
		
		while(!minHeap.isEmpty()){
			arcoActual = minHeap.remove();
			if(ds.findSet(arcoActual.arco.nodo1) != ds.findSet(arcoActual.arco.nodo2)){
				arbol.add(arcoActual);
				ds.union(arcoActual.arco.nodo1, arcoActual.arco.nodo2);
				if(arbol.size() == this.nodos.length - 1){
					return arbol;
				}
			}
		}

		return null;
	}

	public ArrayList<Pesado> kruskalMinHeapSinHeuristicas(){
		ArrayList<Pesado> arbol = new ArrayList<Pesado>();
		DisjointSetSinHeuristicas ds = new DisjointSetSinHeuristicas();
		Comparador comparador = new Comparador();
		PriorityQueue <Pesado> minHeap = new PriorityQueue <Pesado>(comparador);
		Pesado arcoActual;

		for(Integer nodo: this.nodos){
			ds.makeSet(nodo);
		}

		for(Pesado pesado : this.arcos){
			minHeap.add(pesado);
		}
		
		while(!minHeap.isEmpty()){
			arcoActual = minHeap.remove();
			if(ds.findSet(arcoActual.arco.nodo1) != ds.findSet(arcoActual.arco.nodo2)){
				arbol.add(arcoActual);
				ds.union(arcoActual.arco.nodo1, arcoActual.arco.nodo2);
				if(arbol.size() == this.nodos.length - 1){
					return arbol;
				}
			}
		}

		return null;
	}

	public void mostrarArbol(ArrayList<Pesado> lista){
		for(Pesado p : lista){
			System.out.println("("+ p.arco.nodo1+" , "+ p.arco.nodo2 + ") peso:" + p.peso);
		}
	}
}