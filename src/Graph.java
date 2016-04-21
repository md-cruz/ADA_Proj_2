import java.util.List;

public interface Graph {
	
	int numNodes();
	
	List<Integer> nodes();
	
	List<Integer> associatedEdges(int node);
	
	void addEdge(int node1, int node2);
	
	boolean edgeExists(int node1, int node2);

}
