import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	private static final String NOTSOLVABLE = "NO SOLUTION";

	public static void main(String[] args) {
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int numberOfDistrictLocations = Integer.parseInt(reader.readLine());
			Graph graph = new GraphImpl(numberOfDistrictLocations);
			for (int i = 0; i < numberOfDistrictLocations; i++) {
				String[] line = reader.readLine().trim().split("\\s+");
				int[] lineNumbers = new int[line.length];
				for (int k = 0; k < line.length; k++)
					lineNumbers[k] = Integer.parseInt(line[k]);
				for (int j : lineNumbers)
					if (j != 0 )
						graph.addEdge(i + 1, j);
				
				
				
			}
			int numberOfClues = Integer.parseInt(reader.readLine());
			Map<Integer, Integer> clues = new HashMap<Integer, Integer>();
			for (int l = 0; l < numberOfClues; l++) {
				String[] line = reader.readLine().trim().split("\\s+");
				clues.put(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
			}
			reader.close();
			
			System.out.println(computeGraph(clues, numberOfDistrictLocations, graph));
			/*List<Double> sum = new ArrayList<Double>();
			int tries = 10;
			for(int i = 0; i < tries; i++){
				System.out.println(computeGraphMetrics(sum,clues,numberOfDistrictLocations,graph));
				System.gc();
			}
			double sums = 0.0;
			sum.remove(0);
			for(double d : sum)
				sums += d;
			System.out.println("Avg time = " + sums/tries  + " ms");*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String computeGraph(Map<Integer, Integer> clues, int numberOfDistrictLocations, Graph graph) {
		Search s = new BreadthFirstSearch(graph, numberOfDistrictLocations);
		String result = "";
		int[] res = new int[numberOfDistrictLocations + 1];
		int max = 0;
		for (int crime : clues.keySet()) {
			int crimeClue = clues.get(crime);
			s.vertexAtXPosition(crime, crimeClue, res);
			max++;
			
		}

		if (res.length == 0)
			result = NOTSOLVABLE;
		else {
			boolean gotResult = false;
			for (int k = 0; k < res.length; k++) {
				if (res[k] == max) {
					if (result.isEmpty())
						result += k;
					else
						result += " " + k;
					gotResult = true;
				}
			}
			if(!gotResult)
				result = NOTSOLVABLE;

		}
		return result;
	}

	private static String computeGraphMetrics(List<Double> times, Map<Integer, Integer> clues, int numberOfDistrictLocations,
			Graph graph) {
		Search s = new BreadthFirstSearch(graph, numberOfDistrictLocations);
		String result = "";

		long startTime = System.nanoTime();
		int[] res = new int[numberOfDistrictLocations + 1];
		for (int crime : clues.keySet()) {
			s.vertexAtXPosition(crime, clues.get(crime), res);

		}

		if (res.length == 0)
			System.out.println(NOTSOLVABLE);
		else {
			int max = res[0];
			for (int i : res)
				if (i > max)
					max = i;
			for (int k = 0; k < res.length; k++) {
				if (res[k] == max) {
					if (result.isEmpty())
						result += k;
					else
						result += " " + k;
				}
			}

		}
		double timeTaken = (double) ((System.nanoTime() - startTime) / 1000000.0);
		System.out.println("Time " + timeTaken + " ms - int array");
		times.add(timeTaken);
		return result;

	}
}
