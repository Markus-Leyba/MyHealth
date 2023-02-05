package controllers;

import java.io.IOException;
import java.sql.SQLException;

import dao.RecordDao;
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
import model.User;

public class SignupController {
	
	// database layer instances
	UserDao userdao = new UserDao ();
	RecordDao recordDao = new RecordDao ();
	
	// model layer instances
	User newUser;
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private TextField userNameTextField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private Button createUserButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Label signUpMessage;
	
	
	
	// signup method 
	
	@FXML
	public boolean signUp (ActionEvent event) {
		
		boolean signUp = true; 
		
		// extract info from textfields
		String userName = userNameTextField.getText();
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String passWord = passwordTextField.getText();
		
		// alert object 
		// automatically has OK and Cancel button. 
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Signing Up");
		alert.setHeaderText("You're about to create a profile.");
		alert.setContentText("Continue? ");
		
		// if they press OK button then continue to logout. 
		if(alert.showAndWait().get() == ButtonType.OK) {
		
		// add user to database
		try {
			// calls dao layer method. User object is created and returned in that method.
			userdao.addUser(firstName, lastName, userName, passWord); // ### adjustment
			System.out.println("### success in signUp method: recordDao.addUser() tryblock ###");
			
		} catch (SQLException e) {
			System.out.println("### EXCEPTION: in signUp method: userdao.addUser catch block ###");
			e.printStackTrace();
			signUp = false;
			return signUp;
		}
		
		// get updated data from db
		try {
			recordDao.getUsersRecords(userName);
			System.out.println("### success in signUp method: recordDao.getUsersRecords(userName) tryblock ###");
		} catch (SQLException e) {
			System.out.println("### EXCEPTION: in signUp method: recordDao.getUsersRecords(userName) catchblock ###");
			e.printStackTrace();
			signUp = false;
			return signUp;
		}
		
		}// end if statement from alert object
		
		// sets message
		if (!signUp) {
			signUpMessage.setText("Signup Unsuccessful");
		} else {
			signUpMessage.setText("Signup Successful");
		}// end else
		
		return signUp;//placeholder
		
		
		// !!! REFACTOR: this part of the method ideally should automatically go back to main. However then the message does not get to display. So might not be neccessary. I could use something like an alert object. 
		
	}//end signup
	
	
	
	
	// close window method
	
	
	// goes back to login
	public void backToLogin(ActionEvent event) {
		

		 // Create a new FXMLLoader instance
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml")); //  in same package
								
			// Load an object hierarchy from a FXML document
			try {
				root = loader.load();
			} catch (IOException e) {
				System.out.println("#### EXCEPTION: in backToLogin method: in Maincontroller: root try block.");
				e.printStackTrace();
			} 
			
			// initializes stage
			// explanation: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
			stage = (Stage) anchorPane.getScene().getWindow();
			// initializes scene 
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Login");
			stage.show();
	}// end backToLogin
	
	// relevant instances are created and relevant data is loaded. 
	public void initialize () {
		
		// nothing yet. 
		
	}// end initialize
	
}
