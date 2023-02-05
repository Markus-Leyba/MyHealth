package application;
 

import java.io.IOException;

import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

 



// SELF-REFERENCE: 1. Main method > launch method (auto detects class it's being launched from) > start method (Runs javafx application and automatically creates a stage - which is primary stage



public class Main extends Application {
	
	

	@Override
	public void start(Stage primaryStage) { // stage is created automatically by JavaFx runtime. We control the scenes that go into this stage.) > FXMLLOADER instance loads FXML file, content of which is attributed to root variable (which is a parent node) > Instantiate LoginController instance > setStage method from controller > instantiate new Scene instance and initialize with root (parent node) and dimensions > setScene method to set scene of Primary stage as the scene instance > set resizable to false > set title to 'Welcome' > use show method to show stage and it's scene contents.  
		try {
		
			// Create a new FXMLLoader instance
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml")); //  in same package
			
			// Load an object hierarchy from a FXML document
			Parent root = loader.load(); 
		
			// Return the controller associated with the root object
			LoginController loginController = loader.getController(); // 44
			loginController.setStage(primaryStage);
			
			// test
			System.out.println("WAYPOINT 1"); // ## REMOVE THIS LATER: reaches here now. 
			
			Scene scene = new Scene(root, 500, 300);
			// Get an observable list of string URLs linking to the stylesheets
			// to use with this scene's contents
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			// Set the value of the property resizable
			primaryStage.setResizable(false);
			primaryStage.setTitle("Welcome");
			primaryStage.show();
			
			// closes the program if user presses 'red x'. Behaves the same as pressing logout button from menubar. 
			primaryStage.setOnCloseRequest(event -> {
				event.consume(); //event will be consumed. Prevents program from closing user preses red X then presses the cancel button in alert object.  
				logout(primaryStage);
					
			});
			
		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("#### Exception in start method. ####");
		}// end catch
	}// end start method

	
	// logs out
	public void logout (Stage stage) {
		
		// similar to MainController.logout except stage is not instantiated as start method already has stage | argument is stage instead of actionevent | The logout method is MainController will be changed later to switch to LoginView, right now it closes window. 
		// this method is called within the start method. So that red X on a window functions the same as the logout button. 
		
		
		// alert object 
		// automatically has OK and Cancel button. 
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Would you like to save before exiting?: ");
		
		// if they press OK button then continue to logout. 
		if(alert.showAndWait().get() == ButtonType.OK) {
		
		System.out.println("You successfully logged out!");
		
		// closes stage
		stage.close();
		
		} //end IF
		
	}// end logout method	
	
	
	

	// MAIN METHOD
	public static void main(String[] args) {
		launch(args); // launch method detects which class it is launched from. 
	}

	
	
}// end class