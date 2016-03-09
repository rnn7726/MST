import java.util.List;
import java.util.ArrayList;

public class Sort{
	Graph g = new Graph(0, 0, 0);
	int timeToRun = 0;
	int totalWeight = 0;
	static int[][] adjacencyList;
	static int n;
	static List<Edge> edges = new ArrayList<Edge>();

	public Sort(Graph graph){
		g = graph;
		adjacencyList = g.getList();
		n = g.getN();
		printSort();
	}
	
	public static void insertionSort(int[][] adjList){

		for (int x = 0; x < n; x++){
			for (int y = 0; y < n; y++){
				if((x < y) && (adjList[x][y]!=0)){
					edges.add(new Edge(x, y, adjList[x][y]));
					System.out.println(x + " " + y + " weight = " + adjList[x][y]);	
				}
			}
		}
	}

	public static void countSort(){
		
	}

	public static void quickSort(){
		
	}

	public static void printSort(){
		System.out.println("\n===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING INSERTION SORT");
		insertionSort(adjacencyList);
		System.out.println("Total weight = ");
		System.out.println("Runtime: " + "milliseconds \n");
	}

}