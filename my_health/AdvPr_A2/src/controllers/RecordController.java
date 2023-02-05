package controllers;

import javafx.event.ActionEvent;

public abstract class RecordController {

	
	public void backToTableView(ActionEvent event) {
		//implement in concrete class as tableView and tableview controller will differ depending on the class. 
		// if tableView class is refactored so that the same class is shared by by different functionalities (e.g delete, add etc) then this method could then be implemented here.
	}
	
}
