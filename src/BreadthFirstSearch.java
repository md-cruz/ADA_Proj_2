import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch implements Search{
	
	private Graph graph;
	private int numNodes;

	public BreadthFirstSearch(Graph graph, int numNodes){
		this.graph = graph;
		this.numNodes = numNodes;
		
	}
		
	@SuppressWarnings("unchecked")// an integer queue implented in a linkedlist will always
								  // be able to cast to an integer list
	@Override
	public List<Integer> vertexAtXPosition(int originalVertex, int maxDistance) {
		Queue<Integer> waiting = new LinkedList<Integer>();
		Queue<Integer> temp = new LinkedList<Integer>();
		waiting.add(originalVertex);
		boolean[] found = new boolean[numNodes+1];// because we are using the number of the node,
												  // instead of number-1
		found[originalVertex] = true;
		int distance = 0;
		while(!waiting.isEmpty() && distance < maxDistance){
			int node = waiting.poll();
			
			for(int outNode : graph.associatedEdges(node)){
				if(!found[outNode]){
					//waiting.add(outNode);
					temp.add(outNode);
					found[outNode] = true;
					//max++;
					// TODO: optimize, remove the auxiliary list if possible
				}
			}
			if(waiting.isEmpty() && !temp.isEmpty()){
				waiting.addAll(temp);
				distance++;
				temp.clear();
			}
		}
		waiting.addAll(temp);
		return (List<Integer>) waiting;
	}

}
