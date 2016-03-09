import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Sort{
	Graph g = new Graph(0, 0, 0);
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
		printInsertionSort();
		printCountSort();
		printQuickSort();
	}
	
	public static List<Edge> insertionSort(List<Edge> e){
        Edge temp;
        for(int i = 1; i < e.size(); i++) {
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

	public static List<Edge> countSort(List<Edge> e){
		int high = 0;
		int low = 0;
		for(int i = 0 ; i < e.size() ; i++){
			if(high == 0){
				high = e.get(i).getW();
			}
			if(low == 0){
				low = e.get(i).getW();
			}
			if(e.get(i).getW() > high){
				high = e.get(i).getW();
			}
			if(e.get(i).getW() < low){
				low = e.get(i).getW();
			}
		}

		return e;
	}

	public static List<Edge> quickSort(List<Edge> e){
		return e;
	}

	public static void printInsertionSort(){
		int totalWeight = 0;
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

	public static void printCountSort(){
		int totalWeight = 0;
		System.out.println("\n===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING COUNT SORT");
		long startTime = System.currentTimeMillis();
		List<Edge> sorted = countSort(edges);
		for(Edge e: sorted){
			System.out.println(e.getR() + " " + e.getL() + " weight = " + e.getW());
			totalWeight += e.getW();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Total weight = " + totalWeight);
		System.out.println("Runtime: " + (endTime-startTime) + " milliseconds \n");
	}

	public static void printQuickSort(){
		int totalWeight = 0;
		System.out.println("\n===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING QUICK SORT");
		long startTime = System.currentTimeMillis();
		List<Edge> sorted = quickSort(edges);
		for(Edge e: sorted){
			System.out.println(e.getR() + " " + e.getL() + " weight = " + e.getW());
			totalWeight += e.getW();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Total weight = " + totalWeight);
		System.out.println("Runtime: " + (endTime-startTime) + " milliseconds \n");
	}

}