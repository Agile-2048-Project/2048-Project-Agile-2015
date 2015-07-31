package application;
	

import bl.Data;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;


public class Main extends Application {
	
	public static String MainID = "main";
	public static String MainFile = "Main.fxml";
	public static String logInID = "login";
	public static String LogInFile = "LogIn.fxml";
	public static String GameID = "game";
	public static String GameFile = "Game.fxml";
	public static String GameOverID = "gameover";
	public static String GameOverFile = "GameOver.fxml";
	
	@Override
	public void start(Stage primaryStage) {
		
		ScreensController mainContainer = new ScreensController();
		mainContainer.loadScreen(Main.MainID, Main.MainFile);
		mainContainer.loadScreen(Main.logInID, Main.LogInFile);
		Data db = Data.getInstance();
		
		if(db.getPlayerName() == "") {
			mainContainer.setScreen(Main.logInID);
		} else {
			mainContainer.setScreen(Main.MainID);
		}
		
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root,454,620);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
