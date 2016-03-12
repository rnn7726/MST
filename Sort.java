import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Sort{
	Graph g = new Graph(0, 0, 0);
	static int[][] adjacencyList;
	static int n;
	static List<Edge> edges = new ArrayList<Edge>();
	static int high = 0;
	static int low = 0;

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
		//get high and low values
		for(int i = 0 ; i < edges.size() ; i++){
			if(high == 0){
				high = edges.get(i).getW();
			}
			if(low == 0){
				low = edges.get(i).getW();
			}
			if(edges.get(i).getW() > high){
				high = edges.get(i).getW();
			}
			if(edges.get(i).getW() < low){
				low = edges.get(i).getW();
			}
		}
		printInsertionSort(edges, "MATRIX");
		printCountSort(edges, "MATRIX");
		printQuickSort(edges, "MATRIX");
		printInsertionSort(edges, "LIST");
		printCountSort(edges, "LIST");
		printQuickSort(edges, "LIST");
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

	public static List<Edge> quickSort(List<Edge> e, int low, int high){
		if (low < high){
			int pivot = e.get(low + (high - low) / 2).getW();
			int i = low;
			int j = high;
			while(i <= j){
				while(e.get(i).getW() < pivot){
					i++;
				}
				while(e.get(j).getW() > pivot){
					j--;
				}
				if(i <= j){
					Edge temp = e.get(i);
					e.set(i, e.get(j));
					e.set(j, temp);
					i++;
					j--;
				}
			}
			if(low < j){
				quickSort(e, low, j);
			}
			if(high > i){
				quickSort(e, i, high);
			}
		}
		return e;
	}

	public static void printInsertionSort(List<Edge> edgeList, String s){
		int totalWeight = 0;
		System.out.println("\n===================================");
		System.out.println("SORTED EDGES WITH " + s + " USING INSERTION SORT");
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

	public static void printCountSort(List<Edge> edgeList, String s){
		int totalWeight = 0;
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH " + s + " USING COUNT SORT");
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

	public static void printQuickSort(List<Edge> edgeList, String s){
		int totalWeight = 0;
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH " + s + " USING QUICK SORT");
		long startTime = System.currentTimeMillis();
		List<Edge> sorted = quickSort(edgeList, low, high);
		for(Edge e: sorted){
			if(n<10){
				System.out.println(e.getR() + " " + e.getL() + " weight = " + e.getW());
			}
			totalWeight += e.getW();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Total weight = " + totalWeight);
		System.out.println("Runtime: " + (endTime-startTime) + " milliseconds \n");
	}

}