package controllers;


import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Record;


public class RecordTableController extends TableViewController {
	

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
		
		// get user records - inject into tableview
		try {
			getRecordTableView().setItems(getRecordDao().getUsersRecords(MainController.getUserProfile().getUser().getUsername()));
		} catch (SQLException e) {
			System.out.println("#### EXCEPTION: in recordTableView.setItems(recordDao.getAllRecords()) try block #### ");
			e.printStackTrace();
		}
		
		
		
	}
	
}
