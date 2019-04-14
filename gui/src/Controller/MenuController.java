package Controller;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MenuController implements EventHandler, Initializable{
	
	//@FXML 
	//Button update;
	@FXML
	ImageView screenCapid;
	
	boolean toggle;
	
	public MenuController() {
		Timeline timer = new Timeline(new KeyFrame(Duration.millis(10), 
				new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					// Every 3 seconds. 
					updateValues();
				}
			}));
		
			Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!Thread.currentThread().isInterrupted()) {
					try {			
						timer.play();
						Thread.currentThread().sleep(10);
					}catch(Exception e) {
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			}
		});
		
		t.start();
		//timer.play();
	}
	
	public void updateValues() {
		System.out.println("Prints every 3 seconds");
		/*if(toggle == true) {
			//update.setText("No");
			this.toggle = false;
		}else {
			//update.setText("Yes!!");
			this.toggle = true;
		}*/
		
		try {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage bf = new java.awt.Robot().createScreenCapture(screenRect);
		
			javafx.scene.image.Image i =  SwingFXUtils.toFXImage(bf, null);
			this.screenCapid.setImage(i);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void updateOnAction(Event e) {
		// TODO Auto-generated method stub
		System.out.println("update");
		//this.update.fire();
		
	}
 
	@Override
	public void handle(Event arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// just in case...
		
	}

}
