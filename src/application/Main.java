package application;
	
import java.security.Principal;
import java.util.Enumeration;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static String MainID = "main";
	public static String MainFile = "Main.fxml";
	public static String logInID = "login";
	public static String LogInFile = "LogIn.fxml";
	public static String GameID = "game";
	public static String GameFile = "Game.fxml";
	
	@Override
	public void start(Stage primaryStage) {
		
		ScreensController mainContainer = new ScreensController();
		mainContainer.loadScreen(Main.MainID, Main.MainFile);
		mainContainer.loadScreen(Main.logInID, Main.LogInFile);
		//mainContainer.loadScreen(Main.GameID, Main.GameFile);
		
		mainContainer.setScreen(Main.logInID);
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root,454,580);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
