package controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Profile;

public class ProfileController {

	// profile object with current user object and user settings object as field. 
	private static Profile userProfile;

	@FXML
	private Label userNameLabel;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Button okayButton;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Scene scene;
	@FXML
	private Stage stage;
	@FXML
	private Parent root;
	
	
	

	// No constructor used. Profile object is set via setter. 
	
	// test 
		public void printUserProfileDetails() {
			
			if(userProfile != null) {System.out.println("### USER PROFILE IS NOT NULL: MAIN CONTROLLER: TEST METHOD ####");}
			
			System.out.println("### BELOW ARE THE USER PROFILE DETAILS OF MAIN CONTROLLER ####");
			System.out.println(userProfile.getUser().getFirstName());
			System.out.println(userProfile.getUser().getLastName());
			System.out.println(userProfile.getUser().getUsername());
			System.out.println("### " + userProfile.getUser().getPassword());
			
		}// end test method
	
		
		
		public void displayUserInfo () {
			
			userNameLabel.setText(userProfile.getUser().getUsername());
			firstNameLabel.setText(userProfile.getUser().getFirstName());
			lastNameLabel.setText(userProfile.getUser().getLastName());
			
		}//end displayUserName
		
		public void backToMain(ActionEvent event) {
			
			 // GO BACK TO MAIN VIEW. 
		    
			 // Create a new FXMLLoader instance
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml")); //  in same package
					
				// Load an object hierarchy from a FXML document
				try {
					root = loader.load();
				} catch (IOException e) {
					System.out.println("#### EXCEPTION: in deleteRecordView method: in Maincontroller: root try block.");
					e.printStackTrace();
				} 
				
				// initializes stage
				// explanation: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
				stage = (Stage) anchorPane.getScene().getWindow();
				// initializes scene 
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
		}// end backToMain
		
	/**
	 * @return the userProfile
	 */
	public Profile getUserProfile() {
		return userProfile;
	}
	/**
	 * @param userProfile the userProfile to set
	 */
	public static void setUserProfile(Profile userProfile) {
		ProfileController.userProfile = userProfile;
	}
	
	
}
