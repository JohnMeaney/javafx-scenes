package application;

import com.qa.cool.app.CoreLogicFacade;
import com.qa.cool.app.InterestingSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.*;

public class SceneTwoController {
	
	private InterestingSession session;
	private CoreLogicFacade facade;
	private SceneOneController s1c;
	
	// receive a reference to the first scene
	// ... we can retrieve any of its inputs if we need to
	private Scene firstScene;
	
	@FXML
	public TextArea outputArea;
	
	public void setSession(InterestingSession session) {
		this.session = session;
	}


	public void setFacade(CoreLogicFacade facade) {
		this.facade = facade;
	}


	public void setSceneOneController(SceneOneController controller) {
		s1c = controller;
	}
	
	public Scene getFirstScene() {
		return firstScene;
	}

	public void setFirstScene(Scene firstScene) {
		this.firstScene = firstScene;
	}

	public void initialize() {
		outputArea.setText("A useful message will appear here...");
	}
	
	public void doExtraSetup() {
		// called by the onShowing event handler for the stage
		// does any extra setup required, immediately before showing itself to the user
		// control.setText(s1c.getSomething()); // ... sort of thing...
		String thoughtForTheDay = s1c.getThoughtForTheDay();
		outputArea.setText(thoughtForTheDay);
		
		
	}
	

}
