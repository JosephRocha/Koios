package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	
	
	
	//Reads a file you passed in... 
	public Data readFile(String filename) {
		try {
			File file = new File(filename);
			Scanner scan;
			scan = new Scanner(file);
			Data dot = new Data();
		
			while (scan.hasNextLine()) {
			
				// read the line of file
				
				String[] token = scan.nextLine().split(",");
				
				// split by delimiter into token char []
				// populate 
				//TODO: Do Something based on the format...
			
				
			}
		scan.close(); //close
		
		return dot;
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} // open file stream...
		return null;
	}
}
