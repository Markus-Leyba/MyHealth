package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Record;


public class UpdateRecordTableController extends TableViewController {

// this column is unique to the update tableview
// could be better to add column to abstract class and add id column to related FXML files. 
@FXML
private TableColumn <Record, Integer> idColumn;




// ### SWITCHES TO UPDATE RECORD FXML 
public void switchToUpdateView (ActionEvent event) {
			
			// extracts record object from tableview 
			Record sr = getRecordTableView().getSelectionModel().getSelectedItem();
			
			// passes record object to utility class (set as a static variable). 
			// This enables UpdateRecordController to access record object via utility class. 
			SelectedRecordUtility.setSelectedRecord(sr);
			
			// Create a new FXMLLoader instance
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateRecord.fxml")); //  in same package
						
						
			// Load an object hierarchy from a FXML document
			
			try {
				setRoot(loader.load());
				System.out.println("### UpdateRecordTableController: successfully initialized root in try block ####");
			} catch (IOException e) {
				System.out.println("### EXCEPTION: in switchToUpdateView: UpdateRecordTableController class ###");
				e.printStackTrace();
			} 
			
			setStage((Stage) getRecordTableView().getScene().getWindow()); 
			
			// initializes scene 
			setScene(new Scene(getRoot()));
			
			getStage().setScene(getScene());
			getStage().setTitle("Update health record");
			getStage().show();
			
		}// end switchUpdateView
		
			
		// REMOVE TEST METHOD
		public void testMethod () {
			
			System.out.println("Test method in RecordTableController");
			
			
		}// end test method
		
	
		
// Derrived from initializable interface.  
@FXML
@Override
public void initialize() { // initialize method has superceded the previous initializable interface. 
			

			//sets data properties to tableview columns
			getDateColumn().setCellValueFactory(new PropertyValueFactory<Record, String>("date"));
			getWeightColumn().setCellValueFactory(new PropertyValueFactory<Record, Double>("weight"));
			getTempColumn().setCellValueFactory(new PropertyValueFactory<Record, Double>("temperature"));
			getBpLowColumn().setCellValueFactory(new PropertyValueFactory<Record, Double>("bpLow"));
			getBpHighColumn().setCellValueFactory(new PropertyValueFactory<Record, Double>("bpHigh"));
			getNotesColumn().setCellValueFactory(new PropertyValueFactory<Record, String>("note"));
			idColumn.setCellValueFactory(new PropertyValueFactory<Record, Integer>("id"));
			
			// get user records
			try { 
				getRecordTableView().setItems(getRecordDao().getUsersRecords(MainController.getUserProfile().getUser().getUsername()));
			} catch (SQLException e) {
				System.out.println("#### EXCEPTION: in UpdateRecordTableView.setItems(recordDao.getAllRecords()) try block #### ");
				e.printStackTrace();
			}
		
		}
		
	
}// end class
