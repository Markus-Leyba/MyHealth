package controllers;

import java.io.IOException;
import java.sql.SQLException;

import application.Conditions;
import application.RecordConditions;
import dao.RecordDao;
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
import model.Record;

public class addRecordController extends RecordController{
	
	@FXML
	Stage stage; // removed private
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private AnchorPane anchorPane; 
	@FXML
	private TextField weightTextField;
	@FXML
	private TextField temperatureTextField;
	@FXML
	private TextField bloodPressureLowTextField;
	@FXML
	private TextField bloodPressureHighTextField;
	@FXML
	private TextField notesTextField;
	@FXML
	private TextField dateTextField; // change to datepicker later. 
	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Label label;
	
	// create dao class 
	private RecordDao recordDao = new RecordDao ();
	
	// creates a new record instance. Different to addRecord method that adds record to database. 
	@FXML
	public boolean createRecord (ActionEvent event) {
		//change is there is an exception
		boolean error = false;
		
		String date;
		Double weight = null;
		Double temp = null;
		Double bpLow = null;
		Double bpHigh = null;
		String notes;
		// get username from MainController (or whatever class it is stored in later). 
		String username = MainController.getUserProfile().getUser().getUsername(); // username is static field in MainController. I think this field should be moved later. 
		
		// get text info from textfields
		date = dateTextField.getText();
		System.out.println("### THIS IS DATE: "+ date +": createRecord method");
		try {
			//if weight textfield is empty then empty set value to 0
			if (weightTextField.getText().isEmpty()) {
				weight = 0d;
				System.out.println("### THIS IS WEIGHT: "+ weight +": createRecord method");
			} else {
				weight = Double.parseDouble(weightTextField.getText());
				System.out.println("### THIS IS WEIGHT: "+ weight +": createRecord method");
			}
		}catch(NumberFormatException e1) {
			label.setText("ERROR: weight info needs to be numeric."); // #### IDEA: I could change this to a string variable name and the value of the string is sent by RecordConditions method. 
			error = true;
		}
		try {
			//if temp textfield is empty then empty set value to 0
			if (temperatureTextField.getText().isEmpty()) {
				temp = 0d;
				System.out.println("### THIS IS TEMPERATURE: "+ temp +": createRecord method");
			}else {
				temp = Double.parseDouble(temperatureTextField.getText());
				System.out.println("### THIS IS TEMPERATURE: "+ temp +": createRecord method");
			}
		}catch(NumberFormatException e2) {
			label.setText("ERROR: temperature info needs to be numeric.");
			error = true;
		}
		try {
			//if bpLow textfield is empty then empty set value to 0
			if (bloodPressureLowTextField.getText().isEmpty()) {	
		bpLow = 0d;
		System.out.println("### THIS IS BPLOW: "+ bpLow +": createRecord method");
			} else {
				bpLow = Double.parseDouble(bloodPressureLowTextField.getText());
				System.out.println("### THIS IS BPLOW: "+ bpLow +": createRecord method");
			}
		}catch(NumberFormatException e3) {
			label.setText("ERROR: blood pressure low info needs to be numeric.");
			error = true;
		}
		try {
			//if bpHigh textfield is empty then empty set value to 0
			if (bloodPressureHighTextField.getText().isEmpty()) {	
		bpHigh = 0d;
		System.out.println("### THIS IS BPHIGH: "+ bpHigh +": createRecord method");
			} else {
				bpHigh = Double.parseDouble(bloodPressureHighTextField.getText());
				System.out.println("### THIS IS BPHIGH: "+ bpHigh +": createRecord method");
			}
		}catch(NumberFormatException e4) {
			label.setText("ERROR: blood pressure high info needs to be numeric.");
			error = true;
		}
		notes = notesTextField.getText();
		//test
		System.out.println("### THIS IS NOTES: "+ notes +": createRecord method");
		if(!Conditions.wordCounter(notes)) {
			label.setText("ERROR: notes can be a maximum of 50 words.");
			error = true;
		}
		
		// creates new record object 
		Record newRecord = new Record(username, date, weight, temp, bpLow, bpHigh, notes);
		
		
		// validates record through conditions class and exception within method
		if (RecordConditions.validate(newRecord) && !error) {
			//test
			System.out.println("### RECORD PASSES CONDITIONS: addRecordController: createRecord method");
			// alert object 
			// automatically has OK and Cancel button. 
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Create new record");
			alert.setHeaderText("You're about to create a record.");
			alert.setContentText("Continue to create record? ");
			
			// if they press OK button then continue to logout. 
			if(alert.showAndWait().get() == ButtonType.OK) {
			
			// add new record to database
			try {
				recordDao.addRecord(newRecord);
				
			} catch (SQLException e) {
				System.out.println("### EXCEPTION: createRecord method in addRecordController calls addRecord method in RecordTableController instance #####");
				System.out.println("### Record was NOT added #####");
				e.printStackTrace();
				
				return false;
			}
			}// end if from alert object
			
			// gets new database data for TableView 
			try {
				recordDao.getUsersRecords(MainController.getUserProfile().getUser().getUsername()); 
			} catch (SQLException e) {
				System.out.println("### EXCEPTION: in addRecord method: rtc.getUsersRecords try block #### ");
				e.printStackTrace();
				
				return false;
			}
			
			// Next sections switch back to TableView
			backToTableView(event);
							
			// if no exceptions return true. 
			return true; // 
			
		} else {
			
			System.out.println("### RECORD FAILS CONDITIONS: addRecordController: createRecord method");//test
			
			// general catch all label message
			label.setText("ERROR: there was an issue with the information you provided.");
			return false;
		}
			
	}// end createRecord
	
	
	
	
	
	
	
	
	// go to back to record table view
	@Override
	public void backToTableView (ActionEvent event) {
		
		// clear the text fields
		weightTextField.clear();
		temperatureTextField.clear();
		bloodPressureLowTextField.clear();
		bloodPressureHighTextField.clear();
		notesTextField.clear();
		dateTextField.clear(); // change to datepicker later. 
		
		
		// Next sections switch back to Record Table View
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RecordTableView.fxml"));
		
		try {
			root = loader.load();
			System.out.println("### backToTableView method: in addRecordController: successfully initialized root in try block ####");
		} catch (IOException e) {
			System.out.println("### EXCEPTION: backToTableView method: in addRecordController ###");
			e.printStackTrace();
		} 
		
		// gets the current stage via getWindow. 
		stage = (Stage) anchorPane.getScene().getWindow();
		
		// initializes scene 
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Your Health Records");
				stage.show();
		
	}//end backToTableView
	
}// end class
