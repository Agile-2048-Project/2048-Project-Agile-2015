package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.MultiplePressedKeysEventHandler.MultiKeyEvent;
import application.MultiplePressedKeysEventHandler.MultiKeyEventHandler;
import bl.Data;
import bl.Direction;
import bl.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class GameController implements Initializable,ControlledScreen{
	@FXML private Label nameLabel;
	@FXML private AnchorPane mainPane;
	@FXML private Label TILE1;
	@FXML private Label TILE2;
	@FXML private Label TILE3;
	@FXML private Label TILE4;
	@FXML private Label TILE5;
	@FXML private Label TILE6;
	@FXML private Label TILE7;
	@FXML private Label TILE8;
	@FXML private Label TILE9;
	@FXML private Label TILE10;
	@FXML private Label TILE11;
	@FXML private Label TILE12;
	@FXML private Label TILE13;
	@FXML private Label TILE14;
	@FXML private Label TILE15;
	@FXML private Label TILE16;
	@FXML private Label score;
	@FXML private Label best;
	private Game game;
	private Data db;
	ScreensController myController;
	private boolean gameOver;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		
		myController = screenParent;
		game = new Game();
		gameOver = false;
		fillBord();
		score.setText(Integer.toString(game.getScore()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		db = Data.getInstance();
    	nameLabel.setText("WELCOME BACK , " + db.getPlayerName().toUpperCase());
    	best.setText(Integer.toString(db.getBestScore()));
    	
    	initKeyEventHandler();
	}
	
	@FXML protected void handleMenuButtonAction(ActionEvent event) {
		myController.setScreen(Main.MainID);
		
	}
	
	private void initKeyEventHandler() {
	       final MultiplePressedKeysEventHandler keyHandler = 
	         new MultiplePressedKeysEventHandler(new MultiKeyEventHandler() {
	 
	           public void handle(MultiKeyEvent ke) {
	 
	               if (ke.isPressed(KeyCode.POWER) || ke.isPressed(KeyCode.X)) {
	                   Platform.exit();
	               }
	               if (ke.isPressed(KeyCode.LEFT)  || ke.isPressed(KeyCode.A)) {
	            	   game.move(Direction.Left);
	               }
	               if (ke.isPressed(KeyCode.RIGHT) || ke.isPressed(KeyCode.D)) {
	            	   game.move(Direction.Right);
	               }
	 
	               if (ke.isPressed(KeyCode.UP) || ke.isPressed(KeyCode.W)) {
	            	   game.move(Direction.Up);
	               }
	               if (ke.isPressed(KeyCode.DOWN) || ke.isPressed(KeyCode.S)) {
	            	   game.move(Direction.Down);
	               }
	               
	               fillBord();
	               score.setText(Integer.toString(game.getScore()));
	               
	               
	               if(game.gameOver() && !gameOver) {
	            	   
	            	   gameOver = true;
	            	   db.setLastScore(game.getScore());
	            	   db.setScore(game.getScore());
	            	   myController.loadScreen(Main.GameOverID, Main.GameOverFile);
	            	   myController.setScreen(Main.GameOverID);
	            	   
	               } else if(game.won()) {
	            	   
	            	   System.out.println("Win");
	               }
	           }
	       });
	      
	       
	       mainPane.setFocusTraversable(true);
	       mainPane.setOnKeyPressed(keyHandler);
	       mainPane.setOnKeyReleased(keyHandler);
	   }
	
	private void fillBord() {
		
		fillTill(TILE1,0);
		fillTill(TILE2,1);
		fillTill(TILE3,2);
		fillTill(TILE4,3);
		fillTill(TILE5,4);
		fillTill(TILE6,5);
		fillTill(TILE7,6);
		fillTill(TILE8,7);
		fillTill(TILE9,8);
		fillTill(TILE10,9);
		fillTill(TILE11,10);
		fillTill(TILE12,11);
		fillTill(TILE13,12);
		fillTill(TILE14,13);
		fillTill(TILE15,14);
		fillTill(TILE16,15);
	}
	
	private void fillTill(Label l,int index) {
		
		if(!game.getBord()[index].isEmpty()) {
			
			l.setText(game.getBord()[index].getValue());
			
		} else {
			l.setText("");
		}
		
		l.setStyle("-fx-background-color:" + game.getBord()[index].getBackground() + ";-fx-background-radius:3;");
	}
}
