


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




public class UpdateRecordController extends RecordController{

	
	
	@FXML
	private Stage stage;
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
	private TextField dateTextField; 
	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Label label;
	
	// dao layer object
	private RecordDao recordDao = new RecordDao();
	
	// this is the record that gets initialized from the tableview.
	private Record selectedRecord; 
	
	// updates record according to new user input
	public boolean editRecord (ActionEvent event) {
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
	
		// gets id of selectedRecord. 
		Integer id = selectedRecord.getId();
		
		//
		// ### Following code block gets text info from textfields ###
		//
		
		// get date input
		date = dateTextField.getText();
		System.out.println("### THIS IS DATE: "+ date +": editRecord method");
		try {
			//if weight textfield is empty then empty set value to 0
			if (weightTextField.getText().isEmpty()) {
				weight = 0d;
				System.out.println("### THIS IS WEIGHT: "+ weight +": editRecord method");
			} else {
				weight = Double.parseDouble(weightTextField.getText());
				System.out.println("### THIS IS WEIGHT: "+ weight +": editRecord method");
			}
		}catch(NumberFormatException e1) {
			label.setText("ERROR: weight info needs to be numeric.");
			error = true;
			}
		try {
			//if temp textfield is empty then empty set value to 0
			if (temperatureTextField.getText().isEmpty()) {
				temp = 0d;
				System.out.println("### THIS IS TEMPERATURE: "+ temp +": editRecord method");
			}else {
				temp = Double.parseDouble(temperatureTextField.getText());
				System.out.println("### THIS IS TEMPERATURE: "+ temp +": editRecord method");
			}
		}catch(NumberFormatException e2) {
			label.setText("ERROR: temperature info needs to be numeric.");
			error = true;
			}
		try {
			//if bpLow textfield is empty then empty set value to 0
			if (bloodPressureLowTextField.getText().isEmpty()) {	
				bpLow = 0d;
				System.out.println("### THIS IS BPLOW: "+ bpLow +": editRecord method");
			} else {
				bpLow = Double.parseDouble(bloodPressureLowTextField.getText());
				System.out.println("### THIS IS BPLOW: "+ bpLow +": editRecord method");
				}
		}catch(NumberFormatException e3) {
			label.setText("ERROR: blood pressure low info needs to be numeric.");
			error = true;
			}
		try {
			//if bpHigh textfield is empty then empty set value to 0
			if (bloodPressureHighTextField.getText().isEmpty()) {	
				bpHigh = 0d;
				System.out.println("### THIS IS BPHIGH: "+ bpHigh +": editRecord method");
			} else {
				bpHigh = Double.parseDouble(bloodPressureHighTextField.getText());
				System.out.println("### THIS IS BPHIGH: "+ bpHigh +": editRecord method");
				}
		}catch(NumberFormatException e4) {
			label.setText("ERROR: blood pressure high info needs to be numeric.");
			error = true;
			}
		// get notes input
		notes = notesTextField.getText();
		
		//test
		System.out.println("### THIS IS NOTES: "+ notes +": createRecord method");
		if(!Conditions.wordCounter(notes)) {
			label.setText("ERROR: notes can be a maximum of 50 words.");
			error = true;
			}
	
		// creates new record object 
		Record newRecord = new Record(username, date, weight, temp, bpLow, bpHigh, notes);
		
		// set id 
		newRecord.setId(id);
	
	
		// validates record through conditions class and exception within method
		// START VALIDATE IF
		if (RecordConditions.validate(newRecord) && !error) {
			//test
			System.out.println("### RECORD PASSES CONDITIONS: addRecordController: editRecord method");
			// alert object 
			// automatically has OK and Cancel button. 
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Edit new record");
			alert.setHeaderText("You're about to edit a record.");
			alert.setContentText("Continue? ");
		
				// if they press OK button then continue to logout. 
				if(alert.showAndWait().get() == ButtonType.OK) {
		
				// add new record to database
				try {
					recordDao.updateRecord(newRecord);
			
				} catch (SQLException e) {
					System.out.println("### EXCEPTION: createRecord method in addRecordController calls editRecord method in RecordTableController instance #####");
					System.out.println("### Record was NOT added #####");
					e.printStackTrace();
			
					return false;
				}
				}// end if from alert object
		
			// gets new database data for TableView 
			try {
				recordDao.getUsersRecords(MainController.getUserProfile().getUser().getUsername()); 
			} catch (SQLException e) {
				System.out.println("### EXCEPTION: in editRecord method: rtc.getUsersRecords try block #### ");
				e.printStackTrace();
				return false;
			}
		
		// Switch back to tableview
			
		backToTableView(event);
						
		// if no exceptions return true. 
		return true; // 
		
		// END VALIDATE IF
	} else {
		
		System.out.println("### RECORD FAILS CONDITIONS: addRecordController: editRecord method");
		
		// general catch all label message
		label.setText("ERROR: there was an issue with the information you provided.");
		return false;
	}
		
}// end createRecord method
	
	
	
	// EDIT RECORD calls updateRecord method for db. 
	@FXML
	public boolean editRecord1 (ActionEvent event) {
		
		// Gets updated data of selectedRecord from textfields. 
		
		// get username from MainController (or whatever class it is stored in later). 
		String username = MainController.getUserProfile().getUser().getUsername(); // username is static field in MainController. I think this field should be moved later. 
		
		// get text info from textfields
		String date = dateTextField.getText();
		Double weight = Double.parseDouble(weightTextField.getText());
		Double temp = Double.parseDouble(temperatureTextField.getText());
		Double bpLow = Double.parseDouble(bloodPressureLowTextField.getText());
		Double bpHigh = Double.parseDouble(bloodPressureHighTextField.getText());
		String notes = notesTextField.getText();
		
		// gets id of selectedRecord. 
		Integer id = selectedRecord.getId();
		
		// alert object 
		// automatically has OK and Cancel button. 
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Update Record");
		alert.setHeaderText("You're about to permanently update a record.");
		alert.setContentText("Continue to update record? ");
		
		// if they press OK button then continue to logout. 
		if(alert.showAndWait().get() == ButtonType.OK) {
		// creates new record object 
		Record newRecord = new Record( username, date, weight, temp, bpLow, bpHigh, notes);
		
		// set id 
		newRecord.setId(id);
		
		
		
		// update record in database
		try {
			recordDao.updateRecord(newRecord);
			
		} catch (SQLException e) {
			System.out.println("### EXCEPTION: updateRecord method in UpdateRecordController calls updateRecord method in UpdateRecordTableController instance #####");
			System.out.println("### Record was NOT updated #####");
			e.printStackTrace();
			
			return false;
		}
		
		}// end if statement from alert object
		
		
		// gets new database data for TableView 
		try {
			recordDao.getUsersRecords(MainController.getUserProfile().getUser().getUsername());
		} catch (SQLException e) {
			System.out.println("### EXCEPTION: in updateRecord method: recordDao.getUsersRecords try block #### ");
			e.printStackTrace();
			
			return false;
		}
		
		// Next sections switch back to Updates Records Tableview page
		
				backToTableView(event);
						
				// clears selectedRecord static variable. 
				   SelectedRecordUtility.clearSelectedRecord();
			
				// if no exceptions return true. 
					return true; // !!!! refactor after testing conditional ifs !!!!!!!!
					
	}// end editRecord
	
	// go to update records table view
	@Override
	public void backToTableView (ActionEvent event) {
		
		// clear the text fields
		weightTextField.clear();
		temperatureTextField.clear();
		bloodPressureLowTextField.clear();
		bloodPressureHighTextField.clear();
		notesTextField.clear();
		dateTextField.clear(); // change to datepicker later. 
		
		
		// Next sections switch back to TableView
		
		// Create a new FXMLLoader instance
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateRecordTableView.fxml"));
		
		// Load an object hierarchy from a FXML document
		try {
			root = loader.load();
			System.out.println("### backToTableView method: in UpdateRecordController: successfully initialized root in try block ####");
		} catch (IOException e) {
			System.out.println("### EXCEPTION: backToTableView method: in UpdateRecordController ###");
			e.printStackTrace();
		} 
		
		// gets the current stage via getWindow. 
		stage = (Stage) anchorPane.getScene().getWindow();
		
		// initializes scene 
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Your updated records. Select records to update again. ");
				stage.show();
		
	}//end backToTableView 
	
	@FXML
	public void initialize () {
		
		// gets selected Record object from util class
		selectedRecord = SelectedRecordUtility.getSelectedRecord();
		
		
		// sets textfield values according to the object. 
		weightTextField.setText(String.valueOf(selectedRecord.getWeight()));
		temperatureTextField.setText(String.valueOf(selectedRecord.getTemperature()));;
		bloodPressureLowTextField.setText(String.valueOf(selectedRecord.getBpLow()));
		bloodPressureHighTextField.setText(String.valueOf(selectedRecord.getBpHigh()));
		notesTextField.setText(selectedRecord.getNote());
		dateTextField.setText(selectedRecord.getDate()); 
		
	}//end initialize 
	
	
}// end class