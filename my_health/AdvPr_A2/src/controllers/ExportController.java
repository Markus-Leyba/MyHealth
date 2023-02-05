package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import application.SelectMultipleRecords;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Record;



public class ExportController extends RecordController {

	
@FXML
private TextField filePathTextField;
@FXML
private TextField fileNameTextField;
@FXML
private Button chooseDirectoryButton;
@FXML
private Button confirmButton;
@FXML
private Button cancelButton;
@FXML
private AnchorPane anchorPane;
@FXML
Stage stage;
@FXML
Parent root;
@FXML
Scene scene;
@FXML
private Label label;

// choose directory location to save csv
public File chooseDirectory(ActionEvent event) {
	
	// create DirectoryChooser object
	DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setInitialDirectory(new File("src"));

    
    	// initialize file object (or just instantiate... not sure)
        File selectedDirectory = directoryChooser.showDialog(stage);
        
        // test
        System.out.println("### This is the file path " + selectedDirectory.getAbsolutePath());
  

	
	// set text of textfield to absolute path. 
	filePathTextField.setText(selectedDirectory.getAbsolutePath());
	
	return selectedDirectory; // what's the point of returning this? 
}//end chooseDirectory
	

// create csv file
// Method adjusted from source - https://www.youtube.com/watch?v=dHZaqMmQNO4
public boolean createCsv(ActionEvent event) {
	
	// gets file path (REMOVE - probably)
	String filePath = filePathTextField.getText(); // probably not used now. 
	// create filename
	String fileName = fileNameTextField.getText() + ".csv";
	// create csv file
	File csvFile = new File(fileName);
	
	// alert object 
	// automatically has OK and Cancel button. 
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Confirm file creation");
	alert.setHeaderText("You're about to create a csv file.");
	alert.setContentText("Continue to create file? ");
	
	// if they press OK button then continue to logout. 
	if(alert.showAndWait().get() == ButtonType.OK) {
	
	try (PrintWriter printWriter = new PrintWriter(csvFile)) {
		
		for (Record record : SelectMultipleRecords.getSelectedRecords() ) {
			
			//write to file  
			printWriter.print(record.getDate()+",");
			printWriter.print(record.getWeight()+",");
			printWriter.print(record.getTemperature()+",");
			printWriter.print(record.getBpLow()+",");
			printWriter.print(record.getBpHigh()+",");
			printWriter.print(record.getNote()+",");
		}// end loop
		
		// close print writer
		printWriter.close();
		
	} catch (FileNotFoundException e) {
		System.out.println("### EXCEPTION: createCsv method ###");
		e.printStackTrace();
		// !!!! ISSUE: this needs to deal with incorrect file path or issues with file name. 
		return false;
	} 
	
	label.setText("Export Successful");
	return true;
	
	} //end if statement from alert object
	
	return true;  
}// end createCsv

// back to main
@Override
public void backToTableView(ActionEvent event) {
    
	 // Create a new FXMLLoader instance
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ExportRecordTableView.fxml")); //  in same package
							
		// Load an object hierarchy from a FXML document
		try {
			root = loader.load();
		} catch (IOException e) {
			System.out.println("#### EXCEPTION: in backToTableView method: in Exportcontroller: root try block.");
			e.printStackTrace();
		} 
		
		// initializes stage
		// explanation: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
		stage = (Stage) anchorPane.getScene().getWindow();
		// initializes scene 
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Choose Records to Export");
		stage.show();
	
}// end backToMain method


}// end class


