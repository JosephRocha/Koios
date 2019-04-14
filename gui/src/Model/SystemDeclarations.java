package Model;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class SystemDeclarations {
	
	public static int currentId = 0;
	public static ArrayList<Integer> XsOut = new ArrayList<Integer>();
	public static Image xImage = new Image("/Resource/red-cross.png");
	public static ArrayList<Integer> getXsList(){
		return XsOut;
	}
	
	
}
