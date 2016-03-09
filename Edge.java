

public class Edge{
	int left_vertex;
	int right_vertex;
	int weight;
	
	public Edge(int l, int r, int w){
		left_vertex = l;
		right_vertex = r;
		weight = w;
	}

	public int getL(){
		return left_vertex;
	}

	public int getR(){
		return right_vertex;
	}

	public int getW(){
		return weight;
	}

}