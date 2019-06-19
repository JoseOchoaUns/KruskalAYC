package MinHeap;

import Kruskal.Pesado;

public class ColaCP implements MinHeap{
	
	private Pesado[] heap;
	private int size;
	
	public ColaCP(int i) {
		heap=new Pesado[i+1];
		size=0;
	}
	@Override
	public Pesado min() {
		if(size>0)
			return heap[1];
		else
			return null;
	}

	@Override
	public Pesado removeMin() {
		Pesado p=heap[1];
		heap[1]=heap[size];
		size--;
		boolean finish=false;
		int pos=1;
		boolean tienehi;
		boolean tienehd;
		
		while(!finish) {
			tienehi=(pos*2)<=size;
			tienehd=(pos*2+1)<=size;
			int min=pos;
			
			if(tienehi && tienehd) {
				if(heap[pos*2].getPeso()<=heap[pos*2+1].getPeso()) { //el izquierdo es mas chico que el derecho
					min=pos*2;
				}else {				
					min=pos*2+1;
				}
					
			}
			if(tienehi && !tienehd) {
				min=pos*2;
			}
			if(!tienehi && tienehd) {
				min=pos*2+1;
			}
			
			if(heap[min].getPeso()<heap[pos].getPeso()) {
				swap(min,pos);
				pos=min;
			}else {
				finish=true;
			}
			
		}
		
		
		return p;
	}

	@Override
	public void add(Pesado p) {
		size++;
		heap[size]=p;
		int pos=size;
		int padre;
		boolean finish=false;
		while(pos>0 && !finish) {
			padre=(pos/2);
			if(padre>0 && p.getPeso()<heap[padre].getPeso()) {
				swap(pos,padre);
				pos=padre;
			}else
				finish=true;
		}
		
	}
	private void swap(int x, int y) {
		Pesado p=heap[x];
		heap[x]=heap[y];
		heap[y]=p;
	}
	public boolean isEmpty() {return size==0;}
	public String toString() {
		String s="";
		
		for(int i=1;i<=size;i++) {
			s=s+" "+heap[i].getPeso();
		}
		return s;
	}

}
