package application;

import java.net.URL;
import java.util.ResourceBundle;

import bl.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class LoginController implements Initializable,ControlledScreen {
	@FXML private TextField name;
	
	ScreensController myController;
	
	@FXML protected void handleSubmitButtonAction(ActionEvent event) {

		Data db = Data.getInstance();
		db.setPlayerName(name.getText());
		
		Data db1 = Data.getInstance();
		System.out.println(db1.getPlayerName());
		
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
