package Controller;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import Model.Overwatch;
import Model.Profile;
import Model.SystemDeclarations;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;

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
	ListView input;
	
	@FXML
	ListView profileid;
	
	@FXML
	Label percentid;

	@FXML
	private Label Mouse_Pos;

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
	
	ArrayList<Double> val = new ArrayList<Double>();
	
	int percentage = 0;
	
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

	public MenuController()
	{
		Overwatch watcher = new Overwatch();
		
		watcher.addUser(new Profile("Todd","Howard",new Image("/Resource/286.jpg")));
		watcher.addUser(new Profile("Mustache","Howard",new Image("/Resource/3-30-pdt-newsroom-breaking-news-at-11-video-game-reviewer-38499655.png")));
		watcher.addUser(new Profile("Sailor","Howard",new Image("/Resource/5xelm7semsr11.png")));
		watcher.addUser(new Profile("Lord","Howard",new Image("/Resource/7Y7MrLf.png")));
		watcher.addUser(new Profile("Cowboy","Howard",new Image("/Resource/400.jpg")));
		watcher.addUser(new Profile("Snape","Howard",new Image("/Resource/aqSwFnU.jpg")));
		watcher.addUser(new Profile("Detective","Howard",new Image("/Resource/Letrollzorusedrollpictureletrollzorrolledimagewhatmakesmeblush_9ec902dda5f2436e7aec03926ff70786.jpg")));
		watcher.addUser(new Profile("FaceLess","Howard",new Image("/Resource/OHyrJ8g.jpg")));
		watcher.addUser(new Profile("Nice","Howard",new Image("/Resource/s00nsif0quuydyolyndr.jpg")));
		watcher.addUser(new Profile("General","Howard",new Image("/Resource/tumblr_oyg814xR791r9qoglo3_r1_500.jpg")));
		
		
		
		
		Timeline input = new Timeline(new KeyFrame(Duration.millis(4000), 
				new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					// Every 3 seconds. 
					for(int i = 0;i < 10; i++) {
						//val.add();
						double mtt =(Double)Math.random() * 99 +1 ;
						//input.getItems().add(mtt);
					}
					
					int mat = (int) Math.random() * 7 +1;
					percentage += mat;
					percentid.setText(percentage+"");
				}
			}));
		
			Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!Thread.currentThread().isInterrupted()) {
					try {			
						input.play();
						Thread.currentThread().sleep(4000);
					}catch(Exception e) {
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			}
		});
		
		t3.start();
		
		
		
		
		
		//DataCollection();
		try {
			/*Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage bf = new java.awt.Robot().createScreenCapture(screenRect);
			test =  SwingFXUtils.toFXImage(bf, null);*/
			//test = SystemDeclarations.xImage;
			//this.screenCapid.setImage(i);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i  = 0; i < 10; i++) {
			this.dataEnc.add(new Wrapper(watcher.getUser(i).getImage(), watcher.getUser(i).getFullName()));
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
					//DataCollection();
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
	
	
	public void reinit() {
		
		
		
	}


	@FXML
	public void DataCollection()
	{
				this.Mouse_Pos.setText("276, 295");

				System.out.println("Inside DataCollection");

						try
						{
							BufferedInputStream reader = new BufferedInputStream(new FileInputStream("C:\\Users\\Marvin\\IdeaProjects\\Koios\\gui\\MouseInput.txt"));
							StringBuilder s = new StringBuilder();
							char newRand = (char)reader.read();
							char lastRand = 'X';
							int iTest = -1;
							boolean running = true;
							boolean run = true;
							int iVal = 0;
							while(iVal < 5)
							{
								if(reader.available() > 0)
								{
									iTest = reader.read();
									if(iTest != 42 && newRand != lastRand)
									{
										char cTest = (char)iTest;
										run = true;
										System.out.print(cTest + " ");
										s.append(cTest);
									}
									else if(run == true)
									{
										System.out.println("String = " + s.toString());
		//								String myVal = Mouse_Pos.getText();

		//								System.out.println("String = " + myVal);

										this.Mouse_Pos.setText(s.toString());
										s = new StringBuilder();
										run = false;
										iVal++;
									}

								}
								else
								{
									reader = new BufferedInputStream(new FileInputStream("C:\\Users\\Marvin\\IdeaProjects\\Koios\\gui\\MouseInput.txt"));
									lastRand = newRand;
									newRand = (char)reader.read();
									System.out.println("Reset cRead = " + newRand);
									Thread.sleep(50);
								}
							}
						}
						catch(Exception e)
						{
							System.out.println("Exeception");
							e.printStackTrace();
						}

	}

	public void updates()
	{
		/*
		try
		{
		//	wait(5000);
		//	this.Velocity_Min;
		//	this.Velocity_Max;
		//	this.Velocity_Mean;
		//	this.Velocity_Median;
		//	this.Velocity_Std;
		}
		catch(InterruptedException e)
		{
			System.out.println("Error");
		}*/
	}


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
		
		int mat = (int) Math.random() * 7 +1;
		percentage += mat;
		percentid.setText(percentage + "");
		
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
			//this.dataEnc.get(i).lp.setText( + i);
			p.setBottom(this.dataEnc.get(i).lp);
			this.profileid.getItems().add(p);
		}
		
		for(int i = 0;i < 10; i++) {
			//val.add();
			double mtt = (Double)Math.random() * 99 + 1;
			input.getItems().add(mtt);
		}
		
		
		
		
	}

}
