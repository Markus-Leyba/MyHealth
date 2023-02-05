package controllers;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Record;


public class DeleteRecordTableController extends TableViewController{


	// DELETE RECORD FROM DATABASE AND TABLEVIEW.
	// different deleteRecord from dao layer
	public boolean deleteRecord (ActionEvent event) throws SQLException{
		
		// gets selected record from tableview. 
		Record record = getRecordTableView().getSelectionModel().getSelectedItem();
		
		// alert object 
				// automatically has OK and Cancel button. 
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Delete record");
				alert.setHeaderText("You're about to permanently delete a record.");
				alert.setContentText("Continue to delete record? ");
				
				// if they press OK button then continue to logout. 
				if(alert.showAndWait().get() == ButtonType.OK) {
				
				// delete record from database
				
				getRecordDao().deleteRecord(record.getId());
				
		
		} // end if statement from alert object. 
				
				// Injects new data into tableview
				try {
					getRecordTableView().setItems(getRecordDao().getUsersRecords(MainController.getUserProfile().getUser().getUsername())); // ### adjusted
				} catch (SQLException e) {
					System.out.println("#### EXCEPTION: DeleteRecordTableController: in recordTableView.setItems(getAllRecords()) try block #### ");
					e.printStackTrace();
					return false;
				}		
				
		//if no exceptions
				return true;
				
	}// end delete method
	


	// Derrived from initializable interface.  
	@FXML
	@Override
	public void initialize() { // initialize method has superceded the previous initializable interface. 
		

		// sets data properties to tableview columns
		getDateColumn().setCellValueFactory(new PropertyValueFactory<Record, String>("date"));
		getWeightColumn().setCellValueFactory(new PropertyValueFactory<Record, Double>("weight"));
		getTempColumn().setCellValueFactory(new PropertyValueFactory<Record, Double>("temperature"));
		getBpLowColumn().setCellValueFactory(new PropertyValueFactory<Record, Double>("bpLow"));
		getBpHighColumn().setCellValueFactory(new PropertyValueFactory<Record, Double>("bpHigh"));
		getNotesColumn().setCellValueFactory(new PropertyValueFactory<Record, String>("note"));
		
		// get user records
		try {
			getRecordTableView().setItems(getRecordDao().getUsersRecords(MainController.getUserProfile().getUser().getUsername()));
		} catch (SQLException e) {
			System.out.println("#### EXCEPTION: DeleteRecordTableController in recordTableView.setItems(recordDao.getAllRecords()) try block #### ");
			e.printStackTrace();
		}
		
	} // end initialize
	
} // end class
