package application;

import com.qa.cool.app.CoreLogicFacade;
import com.qa.cool.app.InterestingSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*; 
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.*;

public class SceneOneController {
	
	private InterestingSession session;
	private CoreLogicFacade facade;
	// receive a reference to the second scene
	// ... we can retrieve any of its inputs if we need to
	private Scene secondScene;
	private SceneTwoController s2c;

	
	@FXML
	public TextField usefulText;
	

	
	public void setSession(InterestingSession session) {
		this.session = session;
	}


	public void setFacade(CoreLogicFacade facade) {
		this.facade = facade;
	}
	
	public void setSecondScene(Scene secondScene) {
		this.secondScene = secondScene;
	}

	public void setSceneTwoController(SceneTwoController controller) {
		s2c = controller;
	}
	
	// get the contents of the input-capable text field
	public String getThoughtForTheDay() {
		return usefulText.getText();
	}
	
	public void proceedToSecondScene(ActionEvent ae) {
		// get hold of the stage
		Node node = (Node) ae.getSource();
		Stage primaryStage = (Stage) node.getScene().getWindow();
		// we need to hide the stage, set an event handler, change the scene, then show the stage once more:
		primaryStage.hide();
		// if we need setup code to run (even though the second controller initialize() ran earlier), do this:
		primaryStage.setOnShown(e -> s2c.doExtraSetup());
		// set the scene
		primaryStage.setScene(secondScene);
		primaryStage.show();
	}
		
}







