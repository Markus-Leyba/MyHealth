package controllers;



import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem; 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Profile; 

public class MainController {

	
	// FXML objects
	@FXML
	Stage stage; // removed private
	@FXML
	private Scene scene;
	@FXML
	private Parent root; 
	
	
	@FXML 
	Label nameLabel; // should this be private? 
	@FXML
	private MenuBar menuBar; 
	@FXML
	private MenuItem logoutMenuItem;
	@FXML
	private BorderPane borderPane; // main pane. This wraps around gridPane
	@FXML 
	private GridPane gridPane;
	@FXML
	private MenuItem viewRecordsMenuItem;
	@FXML
	private MenuItem editRecordsMenuItem;
	@FXML
	private MenuItem viewProfileMenuItem;
	@FXML
	private MenuItem updateProfileMenuItem;
	@FXML
	private MenuItem exportMenuItem;
	@FXML
	private Button testProfileButton;
	
	
	
	// Profile has logged in user as field. 
	private static Profile userProfile;
	
	
	
	
	
	// method originally from source: demoLogin/application/loginController. 
	// used to set stage after controller is called. 
		public void setStage(Stage stage) {
			this.stage = stage;
		}
			
	
	// CONSTRUCTOR NOT USED
	
	
	// test 
	public void printUserProfileDetails() {
		
		if(userProfile != null) {System.out.println("### USER PROFILE IS NOT NULL: MAIN CONTROLLER: TEST METHOD ####");}
		
		System.out.println("### BELOW ARE THE USER PROFILE DETAILS OF MAIN CONTROLLER ####");
		System.out.println(userProfile.getUser().getFirstName());
		System.out.println(userProfile.getUser().getLastName());
		System.out.println(userProfile.getUser().getUsername());
		System.out.println("### " + userProfile.getUser().getPassword());
		
	}// end test method

	
	
	
	//#### This sets username as the text value to nameLabel element in view/fmxl
		public void displayFirstName (){
		
		// sets static variable username. // assumes one user per application 
		
		nameLabel.setText(MainController.getUserProfile().getUser().getFirstName()); 
			
			
		}// end displayUserName
	
	
	@FXML 
	public void logout (ActionEvent event) {
		
		// alert object 
		// automatically has OK and Cancel button. 
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Would you like to save before exiting?: ");
		
		// if they press OK button then continue to logout. 
		if(alert.showAndWait().get() == ButtonType.OK) {
		
		// gets the current stage via getWindow. 
		stage = (Stage) borderPane.getScene().getWindow(); 
		System.out.println("You successfully logged out!");
		
		// closes stage
		stage.close();
		
		} //end IF
		
	}// end logout method	
		
		
		
	
	// I'm not really sure if these are scenes but that's what the tutorial said. 
	
	// !!! REFACTOR: what is the point of this method in the controller it switches to? I suppose each controller can create main controller object but there's got to be a better way...
	@FXML
	public void switchToMainScene (ActionEvent event) throws IOException {
		
	// in a nutshell this loads Main.fxml into loader instance > initializes fxml to 
	// code for this method taken from Main class. 
		
		// Create a new FXMLLoader instance
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml")); //  in same package
							
		// Load an object hierarchy from a FXML document
		Parent root = loader.load(); 
		
		// initializes stage
		// explanation: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		// initializes scene 
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Home Page");
		stage.show();
		
	}// end switchToMainScene
	
	
	// @@@MENU - DELETE RECORD MENU ITEM -  switches to DeleteRecordsTableView.FXML
	@FXML
	public boolean deleteRecordView(ActionEvent event) {
		
		// Create a new FXMLLoader instance
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeleteRecordTableView.fxml")); //  in same package
							
				// Load an object hierarchy from a FXML document
				try {
					root = loader.load();
				} catch (IOException e) {
					System.out.println("#### EXCEPTION: in deleteRecordView method: in Maincontroller: root try block.");
					e.printStackTrace();
					return false;
				} 
				
				// initializes stage
				// explanation: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
				stage = (Stage) borderPane.getScene().getWindow();
				
				// initializes scene 
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Choose record to delete");
				stage.show();
		
				// if no exception return true;
				return true;
				
	}// end deleteRecordView
	
	
	

	//@@@MENU- VIEWPROFILE MENUT ITEM goes to profile view
	@FXML
	public void profileView(ActionEvent event) {
		
		
				// load fxml
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Profile.fxml"));
				
				try {
					root = loader.load();
					System.out.println("### profileView method: in MainController: successfully initialized root in try block ####");
				} catch (IOException e) {
					System.out.println("### EXCEPTION: profileView method: in MainController ###");
					e.printStackTrace();
				} 
				
				ProfileController profileController = loader.getController();
				// set userProfile of profileController. 
				ProfileController.setUserProfile(MainController.userProfile);
				// displays user details in profie view
				profileController.displayUserInfo();
				// gets the current stage via getWindow. 
						stage = (Stage) borderPane.getScene().getWindow();
						
				// initializes scene 
						scene = new Scene(root);
						stage.setScene(scene);
						stage.setTitle("View Profile");
						stage.show();
	}// end profileView
	
	
	//@@@MENU - updateProfileMenuItem - go to upateProfile view
	@FXML
	public void updateProfileView(ActionEvent event) {
		
		// load NewRecord.fxml
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateProfile.fxml"));
		
		try {
			root = loader.load();
			System.out.println("### updateprofileView method: in MainController: successfully initialized root in try block ####");
		} catch (IOException e) {
			System.out.println("### EXCEPTION: updateprofileView method: in MainController ###");
			e.printStackTrace();
		} 
		
		UpdateProfileController updateProfileController = loader.getController();
		// set userProfile of profileController. 
		updateProfileController.setUserProfile(MainController.userProfile);
		// displays user details in profie view
		updateProfileController.displayUserInfo();
		// gets the current stage via getWindow. 
				stage = (Stage) borderPane.getScene().getWindow();
				
		// initializes scene 
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Update profile info");
				stage.show();
		
	}// end updateProfileView
	
	
	// @@@MENU: VIEW ALL RECORDS: goes to creates new record window. 
	@FXML
	public void newRecordView (ActionEvent event) {
		
		// load fxml
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewRecord.fxml"));
		
		try {
			root = loader.load();
			System.out.println("### newRecordView method: in MainController: successfully initialized root in try block ####");
		} catch (IOException e) {
			System.out.println("### EXCEPTION: newRecordView method: in MainController ###");
			e.printStackTrace();
		} 
		
		// gets the current stage via getWindow. 
				stage = (Stage) borderPane.getScene().getWindow();
				
				// initializes scene 
						scene = new Scene(root);
						stage.setScene(scene);
						stage.setTitle("Create new record");
						stage.show();
						
		}// end newRecordView Method
	
	
	
	// @@@MENU: VIEW ALL RECORDS: viewRecordsMenuItem method - switches view to RecordTableView
	@FXML
	public void viewRecordsTable (ActionEvent event) {
		
				// Create a new FXMLLoader instance
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RecordTableView.fxml")); //  in same package
		
				// Load an object hierarchy from a FXML document
				try {
					root = loader.load();
					System.out.println("### viewRecordsTable method: successfully initialized root in try block ####");
				} catch (IOException e) {
					System.out.println("### EXCEPTION: in viewRecordsTable method ###");
					e.printStackTrace();
				} 
				
				// initializes stage
				stage = (Stage) borderPane.getScene().getWindow(); // borderPane is main element of mainview so this is supposed to extract the stage. I think that stage is set in the Main class. 
				// initializes scene 
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Your health records");
				stage.show();
		
		// test
		System.out.println("### Successfully loaded RecordTableView.fxml ###");
		
	}// end viewRecordsTable
	
	
	// @@@ MENU- EDIT RECORDS MENU ITEM -	VIEW EDIT RECORDS VIEW
	public void editRecordsView(ActionEvent event) {
		
		// Create a new FXMLLoader instance
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateRecordTableView.fxml")); //  in same package		
		
		// Load an object hierarchy from a FXML document
		try {
			root = loader.load();
			System.out.println("### editRecordsView method: successfully initialized root in try block ####");
		} catch (IOException e) {
			System.out.println("### EXCEPTION: in editRecordsView method method ###");
			e.printStackTrace();
		} 
		
		stage = (Stage) borderPane.getScene().getWindow(); 
		// initializes scene 
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Select records to update");
		stage.show();

		// test
		System.out.println("### Successfully loaded UpdateRecordTableView.fxml ###");
		
		
	}// end editRecordsView method
	
	
	
	//@@@ MENU exportMenuItem - goes to export Records Table view
	public void exportRecordTableView(ActionEvent event) {
		
		// Create a new FXMLLoader instance
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ExportRecordTableView.fxml")); //  in same package		
				
				// Load an object hierarchy from a FXML document
				try {
					root = loader.load();
					System.out.println("### exportRecordTableView method: successfully initialized root in try block ####");
				} catch (IOException e) {
					System.out.println("### EXCEPTION: in exportRecordTableView method method ###");
					e.printStackTrace();
				} 
				
				stage = (Stage) borderPane.getScene().getWindow(); 
				// initializes scene 
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Select records to export");
				stage.show();
		
	}// end exportRecordTableView
	
	// INITIALIZE: 
	// REFACTOR - if there's nothing by the end this needs to be removed. Not sure yet because I haven't attempted user settings. There is no database aspect for that either. 
	public void initialize() {
		
		// nothing yet
		
		
	}// end initialize


	


	/**
	 * @return the userProfile
	 */
	public static Profile getUserProfile() {
		return userProfile;
	}


	/**
	 * @param userProfile the userProfile to set
	 */
	public static void setUserProfile(Profile userProfile) {
		MainController.userProfile = userProfile;
	}
	
}// end class