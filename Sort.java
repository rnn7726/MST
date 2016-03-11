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
		printInsertionSort(edges);
		printCountSort(edges);
		printQuickSort(edges);
	}
	
	public static List<Edge> insertionSort(List<Edge> e){
        Edge temp;
        List<Edge> result = e;
        for(int i = 1; i < result.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(result.get(j).getW() < result.get(j-1).getW()){
                    temp = result.get(j);
                    result.set(j , result.get(j-1));
                    result.set(j-1 , temp);
                }
            }
        }
        return result;
	}

	public static Edge[] countSort(List<Edge> e){
		int high = 0;
		int low = 0;
		//get high and low values
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
		//make edge count array - histogram of weights
		int[] edgeCount = new int[high + 1];
		for(int i = 0 ; i < e.size() ; i++){
			edgeCount[e.get(i).getW()] += 1;
		}
		//calculate the starting index for each key
		int total = 0;
		for(int i = 0 ; i < edgeCount.length ; i++){
			int addThisToTotal = edgeCount[i];
			total += addThisToTotal;
			edgeCount[i] = total;
		}
		//ADD TO FINAL ARRAY
		Edge[] result = new Edge[e.size()+1];
		for(int i = 0 ; i < e.size() ; i++){
			result[edgeCount[e.get(i).getW()]] = e.get(i);
			edgeCount[e.get(i).getW()] =  edgeCount[e.get(i).getW()] - 1;
		}
		return result;
	}

	public static List<Edge> quickSort(List<Edge> e){
		return e;
	}

	public static void printInsertionSort(List<Edge> edgeList){
		int totalWeight = 0;
		System.out.println("\n===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING INSERTION SORT");
		long startTime = System.currentTimeMillis();
		List<Edge> sorted = insertionSort(edgeList);
		for(Edge e: sorted){
			if(n<10){
				System.out.println(e.getR() + " " + e.getL() + " weight = " + e.getW());
			}
			totalWeight += e.getW();	
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total weight = " + totalWeight);
		System.out.println("Runtime: " + (endTime-startTime) + " milliseconds");
	}

	public static void printCountSort(List<Edge> edgeList){
		int totalWeight = 0;
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING COUNT SORT");
		long startTime = System.currentTimeMillis();
		Edge[] sorted = countSort(edgeList);
		for(int i = 1 ; i < sorted.length ; i++){
			if(n<10){
				System.out.println(sorted[i].getR() + " " + sorted[i].getL() + " weight = " + sorted[i].getW());
			}
			totalWeight += sorted[i].getW();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Total weight = " + totalWeight);
		System.out.println("Runtime: " + (endTime-startTime) + " milliseconds");
	}

	public static void printQuickSort(List<Edge> edgeList){
		int totalWeight = 0;
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING QUICK SORT");
		long startTime = System.currentTimeMillis();
		List<Edge> sorted = quickSort(edgeList);
		if(n<10){
			for(Edge e: sorted){
				System.out.println(e.getR() + " " + e.getL() + " weight = " + e.getW());
				totalWeight += e.getW();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Total weight = " + totalWeight);
		System.out.println("Runtime: " + (endTime-startTime) + " milliseconds \n");
	}

}