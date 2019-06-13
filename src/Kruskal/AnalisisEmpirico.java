package Kruskal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

	public class AnalisisEmpirico{
		
		public static void main(String[] args) throws IOException {
				try{
				int nodos = 10;
				int arcos = 15;
                Grafo grafo = getGrafo(nodos,arcos,1);
				System.out.println(grafo.esConexoDisjointSet());
				System.out.println(grafo.esConexoBFS());
				if(grafo.esConexoBFS()){
					grafo.mostrarArbol(grafo.kruskalMinHeapConHeuristicas());
					System.out.println("------------------------------");
					System.out.println("------------------------------");
					System.out.println("------------------------------");
					grafo.mostrarArbol(grafo.kruskalMinHeapSinHeuristicas());
					System.out.println("------------------------------");
					System.out.println("------------------------------");
					System.out.println("------------------------------");
					grafo.mostrarArbol(grafo.kruskalOrdenandoConHeuristicas());
					System.out.println("------------------------------");
					System.out.println("------------------------------");
					System.out.println("------------------------------");
					grafo.mostrarArbol(grafo.kruskalOrdenandoSinHeuristicas());
				}
				System.out.println("Grafo conexo con "+ grafo.getNodosCount() + " nodos y "+ grafo.getArcosCount() + " arcos construido");
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

		private static Grafo getGrafo(int nodos, int arcos, int conexo) throws Exception {
			// TODO Auto-generated method stub
			String consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos+"&conexo="+conexo;
			System.out.println(consulta);
			Process process = Runtime.getRuntime().exec(consulta);
			InputStream inputSt = process.getInputStream();
			@SuppressWarnings("resource")
			Scanner s = new Scanner(inputSt).useDelimiter("\\A");
			String jsonString = s.hasNext() ? s.next() : "";
            System.out.println("Tengo el grafo en formato JSON. Lo convierto...");
			Gson gson = new GsonBuilder().create();
			try{
				Grafo.GrafoObj gr = gson.fromJson(jsonString, Grafo.GrafoObj.class);
				return new Grafo(gr);
			} catch (Exception e) {
				throw new Exception(jsonString);
			}
		}
	}