package Controller;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import Model.SystemDeclarations;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class MenuController implements EventHandler, Initializable{
	
	//@FXML 
	//Button update;
	@FXML
	ImageView screenCapid;
	
	@FXML
	ScrollPane mouseListid;
	
	@FXML
	ScrollPane keyboardListid;
	
	@FXML
	ListView profileid;
	
	@FXML
	Label percentid;

	//Mouse Input Variables
	@FXML
	Label MousePos;

	@FXML
	Label Velocity_Min;

	@FXML
	Label Velocity_Max;

	@FXML
	Label Velocity_Std;

	@FXML
	Label Velocity_Mean;

	@FXML
	Label Velocity_Median;

	@FXML
	ScrollPane Input_Scroll;
	
	@FXML 
	Image test;
	boolean toggle;
	int maxScrollValue;
	int minScrollValue;
	int lastNum = -1;
	
	public class Wrapper{
			
		public Image pr;
		public Label lp;
		public ImageView iv;
		
		public Wrapper(Image i, String l) {
			this.pr = i;
			this.iv = new ImageView(this.pr);
			this.lp = new Label(l);
		}
	}
	
	ArrayList<Wrapper> dataEnc = new ArrayList();
	
	public MenuController() {
		try {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage bf = new java.awt.Robot().createScreenCapture(screenRect);
			test =  SwingFXUtils.toFXImage(bf, null);
			//test = SystemDeclarations.xImage;
			//this.screenCapid.setImage(i);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i  = 0; i < 10; i++) {
			this.dataEnc.add(new Wrapper(test,"hello"));
			SystemDeclarations.getXsList().add(i);
		}
		Collections.shuffle(SystemDeclarations.XsOut);
		
		// size of static
		
		
		Timeline ScrollVal = new Timeline(new KeyFrame(Duration.millis(1000), 
				new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					// Every 3 seconds. 
					ScrollValues();
				}
			}));
		
			Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!Thread.currentThread().isInterrupted()) {
					try {			
						ScrollVal.play();
						Thread.currentThread().sleep(1000);
					}catch(Exception e) {
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			}
		});
		
		t2.start();
		
		Timeline ScreenCaptureTimer = new Timeline(new KeyFrame(Duration.millis(10), 
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
						ScreenCaptureTimer.play();
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
	
/*	public Image overlayImage(Image one, Image two) {
		Image main = null;
		
		main.
			
			
			
			
			
			return main;
	}*/
	
	public void ScrollValues() {
		
		// index / size = \
		if(this.lastNum != SystemDeclarations.getXsList().get(0) && SystemDeclarations.XsOut.size() != 0) {
			profileid.scrollTo((int)((SystemDeclarations.getXsList().get(0)/SystemDeclarations.getXsList().size()) * 100));
			
			//Blend b = new Blend(BlendMode.MULTIPLY);
			//b.
			//b.setMode(BlendMode);
			
			ColorAdjust ca = new ColorAdjust();
			ca.setSaturation(100);
			this.dataEnc.get(SystemDeclarations.getXsList().get(0)).iv.setEffect(ca);
			
			
			
			
			
			
			
			
			SystemDeclarations.getXsList().remove(0);
		}else {
			Thread.currentThread().interrupt();
		}
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
		
		
		for(int i = 0; i < this.dataEnc.size(); i++) {
			BorderPane p = new BorderPane();
			//Image ik = this.dataEnc.get(i).pr;
			//ik..resize(10, 10);
			//ImageView iv = new ImageView(ik);
			this.dataEnc.get(i).iv.setFitHeight(100);
			this.dataEnc.get(i).iv.setFitWidth(100);
			Line line = new Line(0,100, 3, 250);
		//	line.
			p.setRight(line);
			p.setCenter(this.dataEnc.get(i).iv);
			this.dataEnc.get(i).lp.setText("Person: " + i);
			p.setBottom(this.dataEnc.get(i).lp);
			this.profileid.getItems().add(p);
		}
		
		
	}

}
