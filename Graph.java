import java.util.Random;


/**
 * @author RichardNgo
 * Graph makes the graph, including the adjacency list, as well as checks for 
 * validity in terms of having all parts of the tree connected with one another.
 */
public class Graph {
	static int[][] adjList;
	int n;
	int seed;
	float p;
	Integer[] vertices;
	Integer[] predecessors;
	Random isConnected = new Random();
	Random isWeight = new Random();
	int count = 1;
	
	/**
	 * @param n number of vertices
	 * @param seed seed number
	 * @param p probability of 1 vertex being connected to another vertex
	 */
	public Graph( int n, int seed, float p){
		//initialize the graph
		this.n = n;
		this.seed = seed; 
		this.p = p;
		adjList = new int[n][n];
		vertices = new Integer[n];
		predecessors = new Integer[n];
		//isConnected = new Random(seed);
		//isWeight = new Random(2*seed);
		isConnected.setSeed(seed);
		isWeight.setSeed(2*seed);
	}
	
	/**
	 * @param n
	 * @param seed
	 * @param p
	 * generates the graph, by adding to the adjacency list what the 
	 * value of the weight between the edges are, represented by both 
	 * the x and y axis
	 */
	public void generateGraph(int n, int seed, float p){
		//fill the adjacency list with connections between the vertices

		for (int x = 0; x < n; x++){
			for (int y = 0; y < n; y++){
				//only need to fill up half of the list, since the other side will be the mirror of this
				//example a->b's edge weight will be the same as b->a, since this isn't a directed graph
				if (x < y){
					//To determine if two vertices should be connected by an edge use the following procedure: 
					//for each pair of vertices, generate a random number between 0 and 1 using the Java Random 
					//class and the seed value specified in the input file for creating the random number generator object
					double randomValue = isConnected.nextDouble();
					//System.out.println("rvalue " + randomValue);
					//If that number is less than or equal to p (also from the input file),
					if (randomValue <= p){
						//then the pair of vertices are connected with weight w (another random number between 1 and n using a different random 
						//number generator, with 2 x the seed value specified in the input file), otherwise they are not connected.
						//this denotes the connection
						int randomWeight = isWeight.nextInt(n)+1;
						//System.out.println("rweight " + randomWeight);
						adjList[x][y] = randomWeight;
						//mirror the coordinate, since it's denoting the same pair of vertices
						adjList[y][x] = randomWeight;
					}
				}
			}	
		}
	}
	
	public void printMatrix(){
		//print the matrix only if there are FEWER than 10 vertices
		if(n<10){
			System.out.println("The graph as an adjacency matrix:");
			for (int x = 0; x < n; x++){
				for (int y = 0; y < n; y++){
					System.out.print(adjList[x][y] + "   ");
				}
				System.out.print("\n");
			}
		}
	}
	
	public void printList(){
		//print the list only if there are FEWER than 10 vertices
		if(n<10){
			System.out.println("The graph as an adjacency list:");
			for (int x = 0; x < n; x++){
				System.out.print(x + "-> ");
				for (int y = 0; y < n; y++){
					if(adjList[x][y] != 0){
						System.out.print(y + "("+  adjList[x][y] + ") ");	
					}
				}
				System.out.print("\n");
			}
		}
	}
	
	/**
	 * @param v the vertex you start at in the dfs, in this case it's just 0
	 * most of the time
	 * @param parent the parent or predecessor of the current vertex
	 */
	public void dfs(int v, int parent){
		//1  procedure DFS(G,v):
		//2      label v as discovered
		//3      for all edges from v to w in G.adjacentEdges(v) do
		//4          if vertex w is not labeled as discovered then
		//5              recursively call DFS(G,w)
		//label v as discovered
		vertices[v] = 1;
		predecessors[v] = parent;
		//for all edges from v to w in adjacent edges(v)
		for (int x = v; x < n;){
			for (int y = 0; y < n; y++){
				if(adjList[x][y] != 0){
					if (vertices[y] == null){
						dfs(y, v);
						count++;
					} 
				} 
			} 
			return;
		}
	}
	
	public void printDFS(){
		if (n<10){
			System.out.println("Vertices: \n");
			for (int i = 0; i < n; i++){
				System.out.print(i + " ");
			}
			System.out.println("\nPredecessors: \n");
			for (int i = 0; i < n; i++){
				System.out.print(predecessors[i] + " ");
			}
		}
	}
	
}
