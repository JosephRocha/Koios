package Model;

public class Scan {
	
	private static Scan instance;
	
	
	private Scan() {
		
	}
	
	
	
	public static Scan getInstance() {
		if(instance == null) {
			instance = new Scan();
		}
		return instance;
	}


	// use scanner to read in data.. 
	
	
	
	public void readFile(String file) {
		
		
		
		
	}
	
 }
