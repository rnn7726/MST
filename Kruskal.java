import java.util.ArrayList;
import java.util.List;

public class Kruskal{
	//list of trees
	List<boolean[]> visited;
	//list of edges
	List<Edge> edges;
	int nodes;
	
	public Kruskal(int numNodes, List<Edge> e){
		visited = new ArrayList<boolean[]>();
		edges = e;
		nodes = numNodes;
	}

	public int totalMST(){
		int totalEdges = 0;
		for(Edge e : edges){
			//edges should be sorted by this point
			//go through each edge, and add the edge weight to the totalMST
			//check to make sure both sides are not already in visited list
			//if visited is empty, just put the first edge into a new boolean array
			if (visited.isEmpty()){
				boolean[] b = new boolean[nodes];
				visited.add(b);
				if (visited.get(0).length < 10){
					System.out.println(e.getR() + " " + e.getL() + " weight = " + e.getW());
				}
				totalEdges += e.getW();
				visited.get(0)[e.getR()] = true;
				visited.get(0)[e.getL()] = true;
				System.out.println();
				
				System.out.println(checkAllPresent(b));
				printArray(b);

			} else {
				for (int i = 0; i < visited.size(); i++){
					boolean[] b = visited.get(i);
					//add nodes used to the list of visited nodes and check for loops
					//then remove the edge from list of edges
					if (b[e.getR()] && !b[e.getL()]){
						//check all other trees for the second node, if it exists, then union
						//the boolean arrays to be one
						for (int j = 0; j < visited.size(); j++){
							boolean[] check = visited.get(j);
							//tree should never be the same as one another, so we ignore equal arrays
							if (b == check){
								//do nothing
							} else {
								if (check[e.getL()]){
									b = unionArrays(b,check);
									check = b;
								}
							}
						}
						//then add it to this visited boolean[]
						if (b.length < 10){
							System.out.println(e.getR() + " " + e.getL() + " weight = " + e.getW());
						}
						totalEdges += e.getW();
						b[e.getR()] = true;
						b[e.getL()] = true;

						System.out.println(checkAllPresent(b));
						printArray(b);

						break;
					} else if (b[e.getL()] && !b[e.getR()]){
						//check all other trees for the second node, if it exists, then union
						//the boolean arrays to be one
						for (int j = 0; j < visited.size(); j++){
							boolean[] check = visited.get(j);
							//tree should never be the same as one another, so we ignore equal arrays
							if (b == check){
								//do nothing
							} else {
								if (check[e.getR()]){
									b = unionArrays(b,check);
									check = b;
								}
							}
						}
						//then add it to this visited boolean[]
						if (b.length < 10){
							System.out.println(e.getR() + " " + e.getL() + " weight = " + e.getW());
						}
						totalEdges += e.getW();
						b[e.getR()] = true;
						b[e.getL()] = true;

						System.out.println(checkAllPresent(b));
						printArray(b);

						break;
					} else if ( !b[e.getR()] && !b[e.getL()] ){
						boolean[] newTree = new boolean[nodes];
						//if it connects 2 trees together, then add it to the tree
						if (newTree.length < 10){
							System.out.println(e.getR() + " " + e.getL() + " weight = " + e.getW());
						}
						totalEdges += e.getW();
						newTree[e.getR()] = true;
						newTree[e.getL()] = true;
						visited.add(newTree);

						System.out.println(checkAllPresent(b));
						printArray(b);
						
						break;
					//otherwise, do nothing since it would create a cycle
						//if the edge has 1 vertex in one tree and 1 in another tree, then combine them
						//union both arrays and then remove both as individual boolean[]
					}
				}
			}
			//KRUSKAL WITH MATRIX USING INSERTION SORT
			//2 3 weight = 1
			//0 3 weight = 2
			//1 4 weight = 2
			//1 5 weight = 4
			//3 4 weight = 4
			//1 6 weight = 5

			//Total weight of MST using Kruskal: 18
			//Runtime: 1 milliseconds
		}
		return totalEdges;
	}

	public boolean checkAllPresent(boolean[] b){
		for(boolean bo:b){
			if (!bo){
				return false;
			}
		}
		return true;
	}

	public void printArray(boolean[] b){
		for (boolean bo:b){
			if (bo){
				System.out.print("True ");
			} else {System.out.print("False ");}
		}
		System.out.println();
	}

	public boolean[] unionArrays(boolean[] original, boolean[] compare){
		//union the arrays
		for (int x = 0; x < original.length; x++){
			if ( original[x] || compare[x]){
				original[x] = true;
			}
		}
		return original;
	}

}