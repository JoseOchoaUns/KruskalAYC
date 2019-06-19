package Kruskal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;

import DisjoinSet.DisjointSetConHeuristicas;
import DisjoinSet.DisjointSetSinHeuristicas;
import MinHeap.ColaCP;
import MinHeap.MinHeap;



public class actividad {
	protected Grafo grafo;
	private String[] color;
	private static final String BLANCO = "Blanco";
	private static final String GRIS = "Gris";
	private static final String NEGRO = "Negro";
	
	public actividad(Grafo g) {
		grafo= g;
		
	}
	
	private void inicializarColores(){
		this.color = new String[this.grafo.getNodos().length];
		for(int i = 0; i < this.color.length ; i++){
			this.color[i] = BLANCO;
		}
	}
	private int getNodoAdyacente(int nodoActual, Pesado pesado){
		int[] n1=pesado.getNodes();
		int ret=nodoActual;
		
		if(n1[0]==nodoActual)
			ret=n1[1];
		
		if(n1[1]==nodoActual)
			ret=n1[0];
		
		return ret;
	}
	
	public void setGrafo(Grafo g) {grafo=g;}
	
	public boolean esConexoBFS(){
		this.inicializarColores();
		Queue<Integer> cola= new LinkedList<Integer>();
		int[] nodos=grafo.getNodos();
		this.color[nodos[0]] = GRIS;
		cola.add(nodos[0]);
		visitarBFS(cola);

		for(int nodo: nodos){
			if(this.color[nodo].equals(BLANCO)){
				return false; //Si no es conexo entrara aca
			}
		}		
		return true; //Si es conexo, nunca entro en el else
	}

	private void visitarBFS(Queue<Integer> cola) {
		while(!cola.isEmpty()){
			int nodoActual = cola.peek();
			Vector<Pesado> adyacentes = grafo.getArcosAdyacentes(nodoActual);
			for(Pesado pesado : adyacentes){
				int nodoAdyacente = getNodoAdyacente(nodoActual, pesado);
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
		DisjointSetConHeuristicas ds = new DisjointSetConHeuristicas(grafo.getNodosCount());
		int[] nodos=grafo.getNodos();
		for(int nodo: nodos){
			ds.makeSet(nodo);
		}

		for(Pesado pesado : grafo.getArcos()){
			if(ds.findSet(pesado.getN1()) != ds.findSet(pesado.getN2())){
				ds.union(pesado.getN1(), pesado.getN2());
			}
		}

		return ds.unicoSet();
	}

	private ArrayList<Pesado> heapsort(ArrayList<Pesado> l) {
		MinHeap minh=new ColaCP(l.size());
		ArrayList<Pesado> aux=new ArrayList();
		for(Pesado p:l)
			minh.add(p);
		while(!minh.isEmpty()) {
			aux.add(minh.removeMin());
		}
		
		return aux;
	}
	public ArrayList<Pesado> kruskalOrdenandoConHeuristicas(){
		ArrayList<Pesado> arbol = new ArrayList<Pesado>();
		Comparador comparador = new Comparador();
		DisjointSetConHeuristicas ds = new DisjointSetConHeuristicas(grafo.getNodosCount());
		int[] nodos=grafo.getNodos();
		ArrayList<Pesado> arcos=grafo.getArcos();
		for(Integer nodo: nodos){
			ds.makeSet(nodo);
		}
		
		arcos=heapsort(arcos);

		for (Pesado arcoActual : arcos) {
			if(ds.findSet(arcoActual.getN1()) != ds.findSet(arcoActual.getN2())){
				arbol.add(arcoActual);
				ds.union(arcoActual.getN1(), arcoActual.getN2());
				if(arbol.size() == nodos.length - 1){
					return arbol;
				}
			}
		}

		return null;
	}

	public ArrayList<Pesado> kruskalOrdenandoSinHeuristicas(){
		ArrayList<Pesado> arbol = new ArrayList<Pesado>();
		Comparador comparador = new Comparador();
		DisjointSetSinHeuristicas ds = new DisjointSetSinHeuristicas(grafo.getNodosCount());
		int[] nodos=grafo.getNodos();
		ArrayList<Pesado> arcos=grafo.getArcos();
		
		for(Integer nodo: nodos){
			ds.makeSet(nodo);
		}

		arcos=heapsort(arcos);

		for (Pesado arcoActual : arcos) {
			if(ds.findSet(arcoActual.getN1()) != ds.findSet(arcoActual.getN2())){
				arbol.add(arcoActual);
				ds.union(arcoActual.getN1(), arcoActual.getN2());
				if(arbol.size() == nodos.length - 1){
					return arbol;
				}
			}
		}

		return null;
	}

	public ArrayList<Pesado> kruskalMinHeapConHeuristicas(){
		ArrayList<Pesado> arbol = new ArrayList<Pesado>();
		DisjointSetConHeuristicas ds = new DisjointSetConHeuristicas(grafo.getNodosCount());
		Comparador comparador = new Comparador();
		
		Pesado arcoActual;
		int[] nodos=grafo.getNodos();
		ArrayList<Pesado> arcos=grafo.getArcos();
		MinHeap minHeap = new ColaCP(arcos.size());
		
		for(Integer nodo: nodos){
			ds.makeSet(nodo);
		}

		for(Pesado pesado : arcos){
			minHeap.add(pesado);
		}
		
		while(!minHeap.isEmpty()){
			arcoActual = minHeap.removeMin();
			if(ds.findSet(arcoActual.getN1()) != ds.findSet(arcoActual.getN2())){
				arbol.add(arcoActual);
				ds.union(arcoActual.getN1(), arcoActual.getN2());
				if(arbol.size() == nodos.length - 1){
					return arbol;
				}
			}
		}

		return null;
	}

	public ArrayList<Pesado> kruskalMinHeapSinHeuristicas(){
		ArrayList<Pesado> arbol = new ArrayList<Pesado>();
		DisjointSetSinHeuristicas ds = new DisjointSetSinHeuristicas(grafo.getNodosCount());
		Pesado arcoActual;
		int[] nodos=grafo.getNodos();
		ArrayList<Pesado> arcos=grafo.getArcos();
		MinHeap minHeap = new ColaCP(arcos.size());
		
		for(Integer nodo: nodos){
			ds.makeSet(nodo);
		}

		for(Pesado pesado : arcos){
			minHeap.add(pesado);
		}
		
		while(!minHeap.isEmpty()){
			arcoActual = minHeap.removeMin();
			if(ds.findSet(arcoActual.getN1()) != ds.findSet(arcoActual.getN2())){
				arbol.add(arcoActual);
				ds.union(arcoActual.getN1(), arcoActual.getN2());
				if(arbol.size() == nodos.length - 1){
					return arbol;
				}
			}
		}

		return null;
	}
}
