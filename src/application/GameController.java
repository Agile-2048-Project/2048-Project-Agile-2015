package application;

import java.net.URL;
import java.util.ResourceBundle;

import bl.Data;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class GameController implements Initializable,ControlledScreen{
	@FXML private Label nameLabel;
	ScreensController myController;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		
		myController = screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Data db = Data.getInstance();
    	nameLabel.setText("WELCOME BACK , " + db.getPlayerName().toUpperCase());
	}
}
