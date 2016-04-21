import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */

/**
 * @author Duarte
 *
 */
public class GraphImpl implements Graph {

	private Map<Integer,List<Integer>> nodesEdge;
	private int numNodes;
	
	
	public GraphImpl(int numNodes){
		this.numNodes = numNodes;
		nodesEdge = new HashMap<Integer,List<Integer>>(numNodes);
	}
	
	
	@Override
	public int numNodes() {
		return this.numNodes;
	}

	
	@Override
	public List<Integer> nodes() {
		List<Integer> nodes = new ArrayList<Integer>(numNodes);
		for(int i : nodesEdge.keySet())
			nodes.add(i);
		return nodes;
	}


	@Override
	public List<Integer> associatedEdges(int node) {
		return nodesEdge.get(node);
	}

	@Override
	public void addEdge(int node1, int node2) {
		List<Integer> node1Edges = new ArrayList<Integer>();
		List<Integer> node2Edges = new ArrayList<Integer>();
		if(nodesEdge.containsKey(node1)){
			node1Edges = nodesEdge.get(node1);
			if(!node1Edges.contains(node2)){
				node1Edges.add(node2);
				nodesEdge.put(node1, node1Edges);
			}
		}else{
			node1Edges.add(node2);
			nodesEdge.put(node1, node1Edges);
		}
		if(nodesEdge.containsKey(node2)){
			node2Edges = nodesEdge.get(node2);
			if(!node2Edges.contains(node1)){
				node2Edges.add(node1);
				nodesEdge.put(node2, node2Edges);
			}
		}else{
			node2Edges.add(node1);
			nodesEdge.put(node2, node2Edges);
		}

	}

	@Override
	public boolean edgeExists(int node1, int node2) {
		if(nodesEdge.containsKey(node1))
			if(nodesEdge.get(node1).contains(node2))
				return true;
		
		if(nodesEdge.containsKey(node2))
			if(nodesEdge.get(node2).contains(node2))
				return true;
	
	return false;
	}
}
