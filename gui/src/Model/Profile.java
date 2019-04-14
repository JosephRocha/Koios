package Model;

import javafx.scene.image.Image;

public class Profile {
	
	private String fistName;
	private String lastName;
	private int id;
	private Image getUserImage = null;
	private final double maxPercentage = 100;
	private final double lowPercentage = 0;
	private double currentPercentage;
	private Data profileData;
	
	
	public Profile(String fN,String lN, Image im){ // Specific
			this.fistName = fN;
			this.lastName = lN;
			this.getUserImage = im;
			this.currentPercentage = this.lowPercentage;
			this.id = SystemDeclarations.currentId;
			SystemDeclarations.currentId++;
	}
	
	public Profile() { // Default
		
		this.fistName = "John";
		this.lastName = "Doe";
		this.getUserImage = null;
		this.currentPercentage = this.lowPercentage;
		
	}
	
	public Data getDataProfile() {
		return this.profileData;
	}
	
	public void setDataProfile(Data dp) {
		this.profileData = dp;
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
		if((this.currentPercentage >= this.lowPercentage) && 
				(this.currentPercentage <= this.maxPercentage)){
			return this.currentPercentage;
		}
		System.err.println("Error: Size is invalid, Profile.java: 51 ");
		return 00;
	}
	
	public String getPerString() {
		return this.currentPercentage + "%";
	}
	
	public int getUserId() {
		return this.id;
	}
	
	

}
