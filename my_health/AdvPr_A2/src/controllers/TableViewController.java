package controllers;

import java.io.IOException;
import dao.RecordDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Record;

public abstract class TableViewController {

	// refactor: perhaps it might be good to further decouple by implementing tableview elements in an interface. Then again, that could easily be done in the future so doing so could just be premature optimization. 
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TableView <Record> recordTableView;
	@FXML
	private Label message;
	@FXML 
	private Button selectButton;
	@FXML
	private Button cancelButton;
	
	// columns for table view
	// changed values to String and Integer objects because of youtube resource
	// source - https://www.youtube.com/watch?v=A5fQbsJ-iF8
	@FXML
	private TableColumn <Record, String> dateColumn;
	@FXML
	private TableColumn <Record, Double> weightColumn;
	@FXML
	private TableColumn <Record, Double> tempColumn;
	@FXML
	private TableColumn <Record, Double> bloodPressureColumn;
	@FXML
	private TableColumn <Record, Double> bpLowColumn;
	@FXML
	private TableColumn <Record, Double> bpHighColumn;
	@FXML
	private TableColumn <Record, String> notesColumn;
	
	
	// dao layer objects
	private RecordDao recordDao = new RecordDao();
	
	
	// back to main view
	public void backToMain(ActionEvent event) {
			    
		// Create a new FXMLLoader instance
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml")); //  in same package
					
		// Load an object hierarchy from a FXML document
		try {
		setRoot(loader.load());
		} catch (IOException e) {
		  System.out.println("#### EXCEPTION: in backToMain method: in RecordTableController: root try block.");
		  e.printStackTrace();
					} 
					
		 // initializes stage
		 // explanation: source of event > cast to node > getScene and getWindow are called > everything is cast to a stage
		 setStage((Stage) getRecordTableView().getScene().getWindow());
		 // initializes scene 
		 setScene(new Scene(getRoot()));
		 getStage().setScene(getScene());
		 getStage().setTitle("Home Page");
		 getStage().show();	
		 } //end backToMain 


	/**
	* @return the recordDao
	*/
	public RecordDao getRecordDao() {
			return recordDao;
			}


	/**
	* @param recordDao the recordDao to set
	*/
	public void setRecordDao(RecordDao recordDao) {
		   this.recordDao = recordDao;
		   }
	
	// ABSTRACT: initialize method - supercedes initializable interface. 		
	public void initialize () {
		
		// implement in concrete class
	} 


	/**
	 * @return the dateColumn
	 */
	public TableColumn <Record, String> getDateColumn() {
		return dateColumn;
	}


	/**
	 * @param dateColumn the dateColumn to set
	 */
	public void setDateColumn(TableColumn <Record, String> dateColumn) {
		this.dateColumn = dateColumn;
	}


	/**
	 * @return the weightColumn
	 */
	public TableColumn <Record, Double> getWeightColumn() {
		return weightColumn;
	}


	/**
	 * @param weightColumn the weightColumn to set
	 */
	public void setWeightColumn(TableColumn <Record, Double> weightColumn) {
		this.weightColumn = weightColumn;
	}


	/**
	 * @return the tempColumn
	 */
	public TableColumn <Record, Double> getTempColumn() {
		return tempColumn;
	}


	/**
	 * @param tempColumn the tempColumn to set
	 */
	public void setTempColumn(TableColumn <Record, Double> tempColumn) {
		this.tempColumn = tempColumn;
	}


	/**
	 * @return the bpLowColumn
	 */
	public TableColumn <Record, Double> getBpLowColumn() {
		return bpLowColumn;
	}


	/**
	 * @param bpLowColumn the bpLowColumn to set
	 */
	public void setBpLowColumn(TableColumn <Record, Double> bpLowColumn) {
		this.bpLowColumn = bpLowColumn;
	}


	/**
	 * @return the bpHighColumn
	 */
	public TableColumn <Record, Double> getBpHighColumn() {
		return bpHighColumn;
	}


	/**
	 * @param bpHighColumn the bpHighColumn to set
	 */
	public void setBpHighColumn(TableColumn <Record, Double> bpHighColumn) {
		this.bpHighColumn = bpHighColumn;
	}


	/**
	 * @return the notesColumn
	 */
	public TableColumn <Record, String> getNotesColumn() {
		return notesColumn;
	}


	/**
	 * @param notesColumn the notesColumn to set
	 */
	public void setNotesColumn(TableColumn <Record, String> notesColumn) {
		this.notesColumn = notesColumn;
	}


	/**
	 * @return the recordTableView
	 */
	public TableView <Record> getRecordTableView() {
		return recordTableView;
	}


	/**
	 * @param recordTableView the recordTableView to set
	 */
	public void setRecordTableView(TableView <Record> recordTableView) {
		this.recordTableView = recordTableView;
	}


	/**
	 * @return the root
	 */
	public Parent getRoot() {
		return root;
	}


	/**
	 * @param root the root to set
	 */
	public void setRoot(Parent root) {
		this.root = root;
	}


	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}


	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}


	/**
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;
	}


	/**
	 * @param scene the scene to set
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}
			
			
}// end class
