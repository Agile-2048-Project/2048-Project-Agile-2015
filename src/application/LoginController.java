package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LoginController implements Initializable,ControlledScreen {
	
	ScreensController myController;
	
	@FXML protected void handleSubmitButtonAction(ActionEvent event) {
		myController.setScreen(Main.MainID);
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent; 
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
