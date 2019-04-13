package Controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController implements EventHandler{
	
	@FXML 
	Button update;

	public void updateOnAction(Event e) {
		// TODO Auto-generated method stub
		System.out.println("update");
		//this.update.fire();
		
	}
 
	@Override
	public void handle(Event arg0) {
		// TODO Auto-generated method stub
		
	}

}
