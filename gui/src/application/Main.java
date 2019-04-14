package application;
	
import java.net.URL;

import Controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage)
	{
		try {
			//BorderPane root = new BorderPane();
		
			URL fxmlFile = this.getClass().getResource("/View/Menu.fxml");
			FXMLLoader loader = new FXMLLoader(fxmlFile);
			loader.setController(new MenuController());
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
