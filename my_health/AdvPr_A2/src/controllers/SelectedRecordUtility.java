package controllers;

import model.Record;

// sole function of this class is to hold selected record object to be accessed by controller. 
public class SelectedRecordUtility {

	private static Record selectedRecord;

	// sets selected record back to null. 
	public static void clearSelectedRecord () {
		
		selectedRecord = null;
		
	}// end clear
	
	/**
	 * @return the selectedRecord
	 */
	public static Record getSelectedRecord() {
	
		
		return selectedRecord;
	}

	/**
	 * @param selectedRecord the selectedRecord to set
	 */
	public static void setSelectedRecord(Record selectedRecord) {
		SelectedRecordUtility.selectedRecord = selectedRecord;
	} 
	
}
