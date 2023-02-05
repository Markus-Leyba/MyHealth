package controllers;



import java.io.IOException;
import java.sql.SQLException;

import dao.RecordDao;
import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Profile;
import model.User;

public class LoginController {
	
	// the FXML annotations injects the FXMLOADER Info to this controller class. 
	@FXML
	TextField nameTextField; // removed private
	@FXML
	PasswordField password; // removed private
	@FXML
	private Label message;
	@FXML
	private Button login;
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	Parent root; // removed private
	@FXML
	private BorderPane borderPane;
	@FXML 
	private Hyperlink signUpHyperLink;
	
	
	// database layer instances
	UserDao userdao = new UserDao ();
	RecordDao recordDao = new RecordDao ();
	
	// controller instances
	MainController maincontroller = new MainController();
	
	
	
	// initialize method automatically loads when class is instantiated. 
	@FXML
	public void initialize() throws LoadException {
		login.setOnAction(event -> { // takes event in lamda function. 
			
			// start IF - valid password and username - validation yet to be implemented. 
			try {
				if (!nameTextField.getText().isEmpty() && !password.getText().isEmpty()) { // if name and password is not empty
					
					// string variables from textfield input 
					String userName = nameTextField.getText();
					String passWord = password.getText();
					
					// test
					message.setText("Step 1: Login success");
					
					// check if username exists
					if (userdao.checkUserExists(userName)) {
						System.out.println("### user exists! ###");
					} else {System.out.println("### Username verification failed ###");}
					
					// check if password matches
					if (userdao.checkPasswordMatches(userName,passWord )) {
						
						System.out.println("### password matches! ###");
					}else {System.out.println("### password verification failed ###");}
					
					// - VALIDATION IF STATEMENT: if statement allows only valid login
					if (userdao.checkUserExists(userName) && userdao.checkPasswordMatches(userName,passWord )) {
					
					// create user object from db
					User currentUser = userdao.getUserObject(userName);
					
					
					// create profile object
					Profile userprofile = new Profile(currentUser);
					
					// test values of currentUser
					System.out.println("First Name of currentUser in login controller is " + currentUser.getFirstName());
					
					// test values of profile object
					System.out.println("First name of user in userProfile in login controller is " + userprofile.getUser().getFirstName());
					
					// Create a new FXMLLoader instance that loads fxml info to class via FXMLLOADER instance. info from that fxml file will be accessible via loader. 
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml")); 
					
					
					
					// load object hierarchy from fxml file. 
					try {
						root = loader.load();
						System.out.println(" ### PAST ROOT. LOGIN CONTROLLER. INITIALIZE. ###");
					} catch (IOException e1) {
						System.out.println(" ### EXCEPTION: LOGINCONTROLLER.INITIALIZE ###");
						e1.printStackTrace();
					}
					
					
					// get controller from main fxml
					maincontroller = loader.getController(); // the reason I decided to go with the bro version is because this way I don't have to know the controller name. Only the fxml file name. If the controller for that file is changed, there will be no issue. 
					
					// set static profile field
					MainController.setUserProfile(userprofile);
					
					
					// display firstName of user
					maincontroller.displayFirstName();
					// once display name is set to username, switch to Main scene. 
					
					
					// switch to main view
					// initializes stage > set scene in stage > show stage
					// explanation of below line: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					// initializes scene 
					scene = new Scene(root);
					stage.setScene(scene);
					stage.setTitle("Home Page");
					stage.show();
					} // end validation if statement
					else {
						message.setText("Login Unsuccessful. Incorrect Info.");
					}
				} else {
					message.setText("Empty username or password");
				}
			} catch (SQLException e) {
				System.out.println("### SQL EXCEPTION: in login controller: initialize method ###");
				e.printStackTrace();
			}
			nameTextField.clear();
			password.clear();
		});
	}// end initialize method
	
	 
	// used in the Main class after the controller associated with root object is called. 
	// METHOD INFO: a stage is a window in a javafx application. The scene (which represents content displayed inside a window) is set in a stage. 
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	
	// third method - don't think this method is ever used. 
	//  !!!! REFACTOR - likely unused !!!!!!!
	public void switchToLoginScene (ActionEvent event) throws IOException {
		
		// code for this method taken from Main class. 
		
				// Create a new FXMLLoader instance // same as in Main method. 
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml")); 
							
							
				// Load an object hierarchy from a FXML document // does same thing as in Main method.
				Parent root = loader.load(); 
				
				// initializes stage: source - bro code. 
				// explanation: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
				// therefore (I think) this is a different stage to primary stage
				// scene is empty at this point. 
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				
				// initializes scene 
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
		
		
	}// end swtichToLoginScene
	
	// go to signup page
	public void signUpView(ActionEvent event) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Signup.fxml"));
		
		try {
			root = loader.load();
			System.out.println("### signup method: in UpdateRecordController: successfully initialized root in try block ####");
		} catch (IOException e) {
			System.out.println("### EXCEPTION: in signup method: in UpdateRecordController ###");
			e.printStackTrace();
		} 
		
		// gets the current stage via getWindow. 
		//stage = (Stage) borderPane.getScene().getWindow();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		// initializes scene 
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Enter details and signup");
				stage.show();
		
	}//end signUpView
	


}// end class


