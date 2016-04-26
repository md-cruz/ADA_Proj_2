import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	private static final String NOTSOLVABLE = "NO SOLUTION";

	public static void main(String[] args) {
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int numberOfDistrictLocations = Integer.parseInt(reader.readLine());
			long totalTime = System.nanoTime(); // start counting after inserting the data
			Graph graph = new GraphImpl(numberOfDistrictLocations);
			long totalDataRegistration = System.nanoTime();
			for(int i = 0; i < numberOfDistrictLocations; i++){
				String[] line = reader.readLine().trim().split("\\s+");
				// perguntar se e preferivel utilizar apenas read
				// e passar para a linha seguinte ao retornar 0
				int[] lineNumbers = new int[line.length];
				for(int k = 0; k<line.length; k++)
					lineNumbers[k] = Integer.parseInt(line[k]);
				for(int j : lineNumbers)
					if(j!=0 && j!= i)
						graph.addEdge(i+1, j);
			}
			int numberOfClues = Integer.parseInt(reader.readLine());
			Map<Integer,Integer> clues = new HashMap<Integer,Integer>();
			for(int l = 0; l < numberOfClues; l++){
				String[] line = reader.readLine().trim().split("\\s+");
				clues.put(Integer.parseInt(line[0]), Integer.parseInt(line[1]));				
			}
			double timeTakenDataReg =(double)((System.nanoTime()-totalDataRegistration)/1000000.0);
			Search s = new BreadthFirstSearch(graph, numberOfDistrictLocations);
			List<Integer> possibleLocations = null;
			
			long startTime = System.nanoTime();
			for(int crime : clues.keySet()){
				if(possibleLocations == null)
					possibleLocations = s.vertexAtXPosition(crime, clues.get(crime));
				else
					possibleLocations.retainAll(s.vertexAtXPosition(crime,clues.get(crime)));
				
			}
			double timeTaken =(double)((System.nanoTime()-startTime)/1000000.0);
			Collections.sort(possibleLocations);
			if(possibleLocations.isEmpty())
				System.out.println(NOTSOLVABLE);
			else
				System.out.println(possibleLocations.toString().replaceAll("[^\\w\\s]",""));
			reader.close();
			double totalTimeTaken =(double)((System.nanoTime()-totalTime)/1000000.0);
			System.out.println();
			
			//System.out.println("\nTime = " + timeTaken + " ms\nData parsing = " + timeTakenDataReg + " ms\nTotal time = " + totalTimeTaken + " ms");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
