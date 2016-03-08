import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author RichardNgo
 * This has the main method for the application, reading in the file and
 * also creating the graph until it is completely connected
 *
 */
public class MST {
    private static int n;
    private static int seed;
    private static float p;
	//opens the file and then parses the information
    /**
     * @param args arguments should be the file and only the file
     */
    public static void main(String[] args){
		if (args.length == 0){
			System.out.println("Input file not found");
			System.exit(0);
		}
		File file = new File(args[0]);
		if (file.isFile()){
			openFile(args[0]);
		} else{
			//error condition 1
			//missing input file
			System.out.println("Input file not found");
			System.exit(0);
		}
		System.out.println("TEST: n=" + n + ", seed=" + seed + ", p=" + p);
		Graph g = new Graph(n, seed, p);

		long startTime = System.currentTimeMillis();
		while (g.count != n){
			g.generateGraph(n, seed, p);
			g.dfs(0, -1);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time to generate the graph: " + (endTime - startTime) + " milliseconds");
		g.printMatrix();
		g.printList();
		g.printDFS();
		Sort s = new Sort(g);
		s.printSort();
	}
	/**
	 * @param filename the name of the file being opened
	 */
	public static void openFile(String filename){
		try {
			File file = new File(filename);
			InputStream input = new FileInputStream(file.getAbsoluteFile());
			BufferedInputStream b = new BufferedInputStream(input);
			BufferedReader br = new BufferedReader(new InputStreamReader(b, StandardCharsets.UTF_8));
		    String line = null;
		    int lineNum = 0;
		    //set values and check for the other 4 error conditions
		    while ((line = br.readLine()) != null) {
		    	if (lineNum == 0){
		    		isInteger(line);
		    		n = Integer.parseInt(line);
		    		lessTwo(n);
		    	} else if (lineNum == 1) {
		    		isInteger(line);
		    		seed = Integer.parseInt(line);
		    	} else if (lineNum == 2) {
		    		isRealNumber(line);
		    		p = Float.parseFloat(line);
		    		betweenZeroOne(p);
		    	} lineNum++;
		    }
		    br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param line the line to see if it is an integer
	 */
	public static void isInteger(String line){
		//error condition 2
		//if n or seed is not an integer
		try{
			Integer.parseInt(line);
		} catch (Exception e) {
			System.out.println("n and seed must be integers");
			System.exit(0);
		}
	}
	
	/**
	 * @param value the value to check if it is less than 2
	 */
	public static void lessTwo(int value){
		//error condition 3
		//n is less than 2
		if(value < 2){
			System.out.println("n must be greater than 1");
			System.exit(0);
		} else { return; }
	}
	
	/**
	 * @param line the line to see if it is a number that may be used
	 */
	public static void isRealNumber(String line){
		//error condition 4
		//p is not a real number
		try{
			Float.parseFloat(line);
		} catch (Exception e) {
			System.out.println("p must be a real number");
			System.exit(0);
		}
	}
	
	/**
	 * @param p needs to be between 0 < p < 1
	 */
	public static void betweenZeroOne(float p){
		//error condition 5
		//p is less than 0 or greater than 1
		if(p < 0 || p > 1){
			System.out.println("p must be between 0 and 1");
			System.exit(0);
		} else { return; }
	}
	
}
