package controllers;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Profile;
import model.User;

public class UpdateProfileController {

	
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private Button okButton; // standardize later to selectButton
	@FXML
	private Button cancelButton;
	@FXML
	private Label message;
	@FXML 
	private Label userNameLabel;
	
	// profile object has user object as field 
	private Profile userProfile;
	
	// database layer instances
	private UserDao userdao = new UserDao ();
	
	// sets user info to fxml text elements. 
	public void displayUserInfo () {
		
		userNameLabel.setText(userProfile.getUser().getUsername());
		firstNameTextField.setText(userProfile.getUser().getFirstName());
		lastNameTextField.setText(userProfile.getUser().getLastName());
		
	}//end displayUserName

	// 
	public void updateProfileInfo(ActionEvent event) throws SQLException {
		
		String userName = userNameLabel.getText();
		String newFirstName = firstNameTextField.getText();
		String newLastName = lastNameTextField.getText();
		String passWordPlaceHolder = "";
		
		// alert object 
				// automatically has OK and Cancel button. 
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Update record");
				alert.setHeaderText("You're about to update a record.");
				alert.setContentText("Continue? ");
				
				// if they press OK button then continue to logout. 
				if(alert.showAndWait().get() == ButtonType.OK) {
		
		// create new user object with updated details. 
		User userUpdate = new User(newFirstName, newLastName, userName, passWordPlaceHolder);
		
		// updates user object from database
		boolean userUpdated = userdao.updateUser(userUpdate);
		
		// test 
		if (userUpdated) {System.out.println("### User was updated from UpdateProfileController ### ");}
		
		// updated user object from db
		User finalUpdatedUser = userdao.getUserObject(userName);
		
		// create profile object
		Profile newProfile = new Profile(finalUpdatedUser);
		
		// updates profile field in main controller
		MainController.setUserProfile(newProfile);
		
		// updates static profile field in main controller  
		ProfileController.setUserProfile(newProfile);
		
		// GO TO VIEW PROFILE
	    
		 // Create a new FXMLLoader instance
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Profile.fxml")); //  in same package
								
			// Load an object hierarchy from a FXML document
			try {
				root = loader.load();
			} catch (IOException e) {
				System.out.println("#### EXCEPTION: in updateProfileInfo method: root try block. ###");
				e.printStackTrace();
			} 
			
			// display updated info in profile view
			ProfileController profileController = loader.getController();
			profileController.displayUserInfo();
			
			// initializes stage
			// explanation: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
			stage = (Stage) anchorPane.getScene().getWindow();
			// initializes scene 
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Your Profile Info");
			stage.show();
				} //end alert object if statement
	}//end updateProfileInfo

	
	
	
	
	public void backToMain(ActionEvent event) {
		
		 // GO BACK TO MAIN VIEW. 
	    
		 // Create a new FXMLLoader instance
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml")); //  in same package
			
			// display firstName of user
			MainController maincontroller = new MainController();
			maincontroller.displayFirstName();
			// Load an object hierarchy from a FXML document
			try {
				root = loader.load();
			} catch (IOException e) {
				System.out.println("#### EXCEPTION: in backToMain method: in UpdateProfileController: root try block.");
				e.printStackTrace();
			} 
			
			// initializes stage
			// explanation: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
			stage = (Stage) anchorPane.getScene().getWindow();
			// initializes scene 
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Home Page");
			stage.show();
	}// end backToMain
	
	// setter
	public void setUserProfile(Profile userProfile) {
		this.userProfile = userProfile;
		
	}
	//getter
	public Profile getUserProfile() {
		
		return userProfile;
	}
	
}
