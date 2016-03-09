import java.util.List;
import java.util.ArrayList;

public class Sort{
	Graph g = new Graph(0, 0, 0);
	static int timeToRun = 0;
	static int totalWeight = 0;
	static int[][] adjacencyList;
	static int n;
	static List<Edge> edges = new ArrayList<Edge>();

	public Sort(Graph graph){
		g = graph;
		adjacencyList = g.getList();
		n = g.getN();
		for (int x = 0; x < n; x++){
			for (int y = 0; y < n; y++){
				if((x > y) && (adjacencyList[x][y]!=0)){
					edges.add(new Edge(x, y, adjacencyList[x][y]));
				}
			}
		}
		printSort();
	}
	
	public static List<Edge> insertionSort(List<Edge> e){
        Edge temp;
        for (int i = 1; i < e.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(e.get(j).getW() < e.get(j-1).getW()){
                    temp = e.get(j);
                    e.set(j , e.get(j-1));
                    e.set(j-1 , temp);
                }
            }
        }
        return e;
	}

	public static void countSort(Edge[] e){
		
	}

	public static void quickSort(Edge[] e){
		
	}

	public static void printSort(){
		System.out.println("\n===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING INSERTION SORT");
		long startTime = System.currentTimeMillis();
		List<Edge> sorted = insertionSort(edges);
		for(Edge e: sorted){
			System.out.println(e.getR() + " " + e.getL() + " weight = " + e.getW());
			totalWeight += e.getW();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Total weight = " + totalWeight);
		System.out.println("Runtime: " + (endTime-startTime) + " milliseconds \n");
	}

}