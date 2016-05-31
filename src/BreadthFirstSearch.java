import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstSearch implements Search{
	
	private Graph graph;
	private int numNodes;

	public BreadthFirstSearch(Graph graph, int numNodes){
		this.graph = graph;
		this.numNodes = numNodes;
		
	}
		
	// an integer queue implented in a linkedlist will always
								  // be able to cast to an integer list
	@Override
	public void vertexAtXPosition(int originalVertex, int maxDistance, int[] res) {
		Queue<Integer> waiting = new ArrayDeque<Integer>(numNodes);
		waiting.add(originalVertex);
		boolean[] found = new boolean[numNodes+1];// because we are using the number of the node,
												  // instead of number-1
		found[originalVertex] = true;
		int distance = 0;
		int numberOfParents = 0;
		int doneNodes = 0;
		boolean finishedNodes = true;
		while(!waiting.isEmpty() && distance < maxDistance){
			int node = waiting.poll();
			if(!finishedNodes)
				doneNodes++;
			for(int outNode : graph.associatedEdges(node)){
				if(!found[outNode]){
					waiting.add(outNode);
					//temp.add(outNode);
					found[outNode] = true;
					finishedNodes = false;
					
				}
			}
			if(numberOfParents == doneNodes && doneNodes != 0){
				numberOfParents = 0;
				doneNodes = 0;
			}
			if(!finishedNodes && numberOfParents == 0){
				distance++;
				numberOfParents = waiting.size();
			}
			
			
			/*if(waiting.isEmpty() && !temp.isEmpty()){
				waiting.addAll(temp);
				distance++;
				temp.clear();
			}*/
		}
		//waiting.addAll(temp);
		
		for(int i : waiting)
			res[i]++;
	}

}
