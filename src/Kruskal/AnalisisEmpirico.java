package Kruskal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

	public class AnalisisEmpirico{
		
		public static void main(String[] args) throws IOException {
				Grafo grafo,grafo2,grafo3,grafo4;
				long start,fin;
				int[] nodos = {50, 50, 50,250, 250,250, 500, 500, 500};
				int[] arcos = {49, 500,1200, 249,2500 ,20000, 499, 5000, 124000};
				
				try{
				System.out.println("------------------------------");
				System.out.println("Evaluamos la ejecucion de esConexo en sus 2 variantes: ");
				for (int i = 0; i < nodos.length;i++) {
					System.out.println("Caso de prueba: Grafo con "+nodos[i]+" nodos y "+arcos[i]+" arcos");
					grafo = getGrafo(nodos[i],arcos[i],false);
					//Ver si es necesario hacer la copia aca tambien. Hablar con pp
					start = System.nanoTime();
					grafo.esConexoBFS();
					fin = System.nanoTime();
					System.out.println("Tiempo de ejecucion con BFS: "+(fin-start)+" ns, "+(fin-start)/1000000+" ms");
					start = System.nanoTime();
					grafo.esConexoDisjointSet();
					fin = System.nanoTime();
					System.out.println("Tiempo de ejecucion con disjointSet: "+(fin-start)+" ns, "+(fin-start)/1000000+" ms");
					
					
				}
				
				int[] nodos2 = {10,10,10,50,50,50,100,100,100,200,200,200,400,400,400,500,500,500};
				int[] arcos2 = {9,25,44,49,500,1100,99,1000,4900,199,2000,19000,399,4000,79000,499,5000,124000};
			
		
				
				
					System.out.println("------------------------------");
					System.out.println("Evaluamos la ejecucion de Kruskal en sus 4 variantes: ");
					for (int i = 0; i < nodos2.length;i++) {
						System.out.println("Caso de prueba: Grafo con "+nodos2[i]+" nodos y "+arcos2[i]+" arcos");
						grafo = getGrafo(nodos2[i],arcos2[i],true);
						//Ver si clona las cosas de adentro tambien
						grafo2 =  grafo.clone();
						grafo3 =  grafo.clone();
						grafo4 =  grafo.clone();
					
						start = System.nanoTime();
						grafo.kruskalOrdenandoConHeuristicas();
						fin = System.nanoTime();
						System.out.println("Tiempo de ejecucion con Heuristicas y Ordenado: "+(fin-start)+" ns, "+(fin-start)/1000000+" ms");
						start = System.nanoTime();
						grafo2.kruskalOrdenandoSinHeuristicas();
						fin = System.nanoTime();						
						System.out.println("Tiempo de ejecucion sin Heuristicas y Ordenado: "+(fin-start)+" ns, "+(fin-start)/1000000+" ms");
						start = System.nanoTime();
						grafo3.kruskalMinHeapConHeuristicas();
						fin = System.nanoTime();
						System.out.println("Tiempo de ejecucion con Heuristicas y con heap: "+(fin-start)+" ns, "+(fin-start)/1000000+" ms");
						start = System.nanoTime();
						grafo4.kruskalMinHeapSinHeuristicas();
						fin = System.nanoTime();
						System.out.println("Tiempo de ejecucion sin Heuristicas y con heap: "+(fin-start)+" ns, "+(fin-start)/1000000+" ms");
					}
				
				
				
				
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
			
			
			/*
			 * Generar varios grafos de diferente configuración y buscar 
			 * árbol de cubrimiento minimal para cada uno. 
			 * 
			 * Medir el rendimiento usando timestamps.
			 * 
			 */
			
			
		}

		private static Grafo getGrafo(int nodos, int arcos, boolean conexo) throws Exception {
			// TODO Auto-generated method stub
			String consulta = "";
			if (conexo)
				consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos+"&conexo=1";
			else 
				consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos;
			
			//System.out.println(consulta);
			Process process = Runtime.getRuntime().exec(consulta);
			InputStream inputSt = process.getInputStream();
			@SuppressWarnings("resource")
			Scanner s = new Scanner(inputSt).useDelimiter("\\A");
			String jsonString = s.hasNext() ? s.next() : "";
            //System.out.println("Tengo el grafo en formato JSON. Lo convierto...");
			Gson gson = new GsonBuilder().create();
			try{
				Grafo.GrafoObj gr = gson.fromJson(jsonString, Grafo.GrafoObj.class);
				return new Grafo(gr);
			} catch (Exception e) {
				throw new Exception(jsonString);
			}
		}
	}