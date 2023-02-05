package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import application.SelectMultipleRecords;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Record;


public class ExportRecordTableController extends RecordTableController {



	// gets selected records
	// Refactor: not sure if this method belongs on it's own. If so, maybe it's a good idea to add it to the abstract class. 
	// source: https://stackoverflow.com/questions/17388866/getting-selected-item-from-a-javafx-tableview
	@FXML
	public ArrayList<Record> getSelectedRecords(){
		
		ArrayList<Record> selectedRecords = new ArrayList<>(getRecordTableView().getSelectionModel().getSelectedItems());
		
		// for loop is just for testing. Records are already added to list. 
		for (Record record : selectedRecords) {     
		    System.out.println(record.getDate());
		    System.out.println(record.getWeight()); 
		    System.out.println(record.getTemperature()); 
		    System.out.println(record.getBpLow()); 
		    System.out.println(record.getBpHigh()); 
		    System.out.println(record.getNote()); 
		}
		
		return selectedRecords;
		
	}//end getSelectedRecords
	
	
	
	// goes to exportDirectoryView
	@FXML 
	public void exportDirectoryView (ActionEvent event) {
	
	// get selected rows from tableview
	ArrayList <Record> selectedRecords = getSelectedRecords();
	
	// sets utility class static ArrayList with selectedRecords values
	SelectMultipleRecords.setSelectedRecords(selectedRecords);
	
	// switch to ExportView
	
	 // Create a new FXMLLoader instance
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Export.fxml")); //  in same package	
		// Load an object hierarchy from a FXML document
		try {
			setRoot(loader.load());
		} catch (IOException e) {
			System.out.println("#### EXCEPTION: in exportDirectoryView method: in ExportRecordTableController: root try block.");
			e.printStackTrace();
		} 
		// initializes stage
		// explanation: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
		setStage((Stage) getRecordTableView().getScene().getWindow());
		// initializes scene 
		setScene(new Scene(getRoot()));
		getStage().setScene(getScene());
		getStage().setTitle("Export csv file");
		getStage().show();
		
	}// end exportDirectoryView
	
		


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
			
			// get user records
			try { 
				getRecordTableView().setItems(getRecordDao().getUsersRecords(MainController.getUserProfile().getUser().getUsername()));
			} catch (SQLException e) {
				System.out.println("#### EXCEPTION: ExportRecordTableController: in recordTableView.setItems(recordDao.getAllRecords()) try block #### ");
				e.printStackTrace();
			}
			
			// allow selection of multiple rows in tableview 
			getRecordTableView().getSelectionModel().setSelectionMode(
				    SelectionMode.MULTIPLE
				);
	
			
		}// end initialize
	
}
