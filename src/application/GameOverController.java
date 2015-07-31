package application;

import java.net.URL;
import java.util.ResourceBundle;

import bl.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class GameOverController implements Initializable,ControlledScreen{
	ScreensController myController;
	@FXML private Label score;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	@FXML protected void handlePlayButtonAction(ActionEvent event) {
		myController.loadScreen(Main.GameID, Main.GameFile);
		myController.setScreen(Main.GameID);
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Data db = Data.getInstance();
		score.setText(Integer.toString(db.getLastScore()));
	}

}
