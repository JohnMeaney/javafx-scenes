package application;
	
/*
 * 2 scenes, 2 controllers, 1 session object to hold interesting state, 1 facade object that front-ends a 
 * potentially complex application.
 * 
 * Each controller holds a reference to the other controller, the other controller's scene and to the session state.
 * 
 * One of the event handlers in the first controller causes the transition to the second scene.
 * 
 * This introduces a lot of dependencies among the controllers in particular.
 * 
 * (N.B. We make no use of JavaFX Java Bean processing.)
 * 
 * 
 */
 

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import com.qa.cool.app.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// start with the end in mind... set up a controlled shutdown event handler
			// (you can always have a Close button to do the same thing also)
			primaryStage.setOnCloseRequest(evt -> performControlledShutdown(primaryStage));
			// set up a session object to hold session state
			InterestingSession session = new InterestingSession();
			// set up a facade/controller that links to the core business app
			CoreLogicFacade facade = new CoreLogicFacade();
			// set up scene one
			//BorderPane rootOne = FXMLLoader.load(getClass().getResource("TwinUISceneOne.fxml"));
			FXMLLoader loaderOne = new FXMLLoader(getClass().getResource("TwinUISceneOne.fxml"));
			BorderPane rootOne = loaderOne.load();
			Scene sceneOne = new Scene(rootOne,400,400);
			sceneOne.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// set up scene two
			FXMLLoader loaderTwo = new FXMLLoader(getClass().getResource("TwinUISceneTwo.fxml"));
			BorderPane rootTwo = loaderTwo.load();
			Scene sceneTwo = new Scene(rootTwo,400,400);
			sceneTwo.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// get the two controller objects
			SceneOneController soc = loaderOne.getController();
			SceneTwoController stc = loaderTwo.getController();
			// give scene one controller a reference to the session state and facade, and to the second scene and second controller
			soc.setSession(session);
			soc.setFacade(facade);
			soc.setSecondScene(sceneTwo);
			soc.setSceneTwoController(stc);
			// give the scene two controller a reference to the session and facade, and to the first scene and first controller
			stc.setSession(session);
			stc.setFacade(facade);
			stc.setFirstScene(sceneOne);
			stc.setSceneOneController(soc);
			// show scene one
			primaryStage.setScene(sceneOne);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void performControlledShutdown(Stage primaryStage) {
		// close various other resources as appropriate, then...
		primaryStage.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
