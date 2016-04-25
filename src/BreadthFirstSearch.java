import java.util.ArrayDeque;
import java.util.ArrayList;
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
		Queue<Integer> waiting = new ArrayDeque<Integer>(numNodes);
		Queue<Integer> temp = new ArrayDeque<Integer>(numNodes);
		//Queue<Integer> waiting = new LinkedList<Integer>();
		//Queue<Integer> temp = new LinkedList<Integer>();
		waiting.add(originalVertex);
		boolean[] found = new boolean[numNodes+1];// because we are using the number of the node,
												  // instead of number-1
		found[originalVertex] = true;
		int distance = 0;
		int currNodes = 0;
		int doneNodes = 0;
		boolean finishedNodes = true;
		while(!waiting.isEmpty() && distance < maxDistance){
			int node = waiting.poll();
			if(!finishedNodes)
				doneNodes++;
			for(int outNode : graph.associatedEdges(node)){
				if(!found[outNode]){
					//waiting.add(outNode);
					temp.add(outNode);
					found[outNode] = true;
					//finishedNodes = false;
					// TODO: optimize, remove the auxiliary queue if possible
				}
			}
			/*if(currNodes == doneNodes && doneNodes != 0){
				finishedNodes = true;
				distance++;
				currNodes = 0;
				doneNodes = 0;
			}
			if(!finishedNodes && currNodes == 0)
				currNodes = waiting.size();
			*/
			
			if(waiting.isEmpty() && !temp.isEmpty()){
				waiting.addAll(temp);
				distance++;
				temp.clear();
			}
		}
		waiting.addAll(temp);
		//List<Integer> result = new ArrayList<Integer>(waiting);		
		return new ArrayList<Integer>(waiting);
	}

}
