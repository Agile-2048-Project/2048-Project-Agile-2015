package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainController implements Initializable,ControlledScreen {
	ScreensController myController;
	
	@FXML protected void handlePlayButtonAction(ActionEvent event) {
		myController.loadScreen(Main.GameID, Main.GameFile);
		myController.setScreen(Main.GameID);
		
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
