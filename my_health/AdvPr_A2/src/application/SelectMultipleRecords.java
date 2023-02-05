package application;

import java.util.ArrayList;

import model.Record;


// purpose of this class is to allow access by multiple controller to the same ArrayList of selected records. 

public class SelectMultipleRecords {

	// static list
	private static ArrayList <Record> selectedRecords;

	
	// clears list
	public static void clearSelectedRecords () {
		
		SelectMultipleRecords.selectedRecords.clear();
		
	}// end clear
	/**
	 * @return the selectedRecords
	 */
	public static ArrayList <Record> getSelectedRecords() {
		return selectedRecords;
	}

	/**
	 * @param selectedRecords the selectedRecords to set
	 */
	public static void setSelectedRecords(ArrayList <Record> selectedRecords) {
		SelectMultipleRecords.selectedRecords = selectedRecords;
	}
	
}// end class
