package Model;

import javafx.scene.image.Image;

public class Profile {
	
	private String fistName;
	private String lastName;
	private Image getUserImage;
	private final double maxPercentage = 100;
	private final double lowPercentage = 0;
	private double currentPercentage ;
	
	
	public Profile(String fN,String lN, Image im){ // Specific
			this.fistName = fN;
			this.lastName = lN;
			this.getUserImage = im;
			this.currentPercentage = this.lowPercentage;
	}
	
	public Profile() { // Default
		
		this.fistName = "John";
		this.lastName = "Doe";
		this.getUserImage = null;
		this.currentPercentage = this.lowPercentage;
		
	}
	
	public String getFirstName() {
		return this.fistName;
	}
	
	public String getLastName() {
		return this.lastName;
	}

	public String getFullName() {
		return this.fistName + " " + this.lastName;
	}
	
	public double getPercentage() {
		return this.currentPercentage;
	}
	
	public String getPerString() {
		return this.currentPercentage + "%";
	}
	
	

}
