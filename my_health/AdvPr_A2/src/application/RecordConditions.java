package application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.Record;
import java.util.HashMap;

// this class extends abstract Conditions. Any changes to conditions can be made to the relevant class. 
public class RecordConditions extends Conditions {

	// this is returned when a record is invalid. 
	String labelString = null;
	 
	// Map of strings to be applied to label element in FXML
	
	HashMap<String, String> mapOfStrings = new HashMap<String, String>();

	

		
	
	public static boolean validate(Record record) {
	
		boolean verified = true;
		String issue = "No issue";
		
		// bpLow should be lower than bpHigh
		if (record.getBpLow() > record.getBpHigh()) {
			verified = false;
			issue = "Issue 1";
			System.out.println("### EXCEPTION: bpLow should be lower than bpHigh: RecordConditions: validate method");
		}
		
		// weight, temp, bpLow or bpHigh should be numeric
		if (!isNumeric(record.getWeight().toString()) && !isNumeric(record.getTemperature().toString()) && !isNumeric(record.getBpLow().toString()) && !isNumeric(record.getBpHigh().toString())){
			verified = false;
			issue = "Issue 2";
			System.out.println("### EXCEPTION: A NUMERIC FIELD WAS NOT NUMERIC: RecordConditions: validate method");
			
		}//end if
		
		// a record should have at least 1 field
		if (isNull(record.getDate().toString()) && isNull(record.getWeight().toString()) && isNull(record.getTemperature().toString()) && isNull(record.getBpLow().toString()) && isNull(record.getBpHigh().toString()) && isNull(record.getNote().toString())){
			verified = false;
			issue = "Issue 3";
			System.out.println("### EXCEPTION: AT LEAST ONE RECORD FIELD SHOULD HAVE AN ENTRY: RecordConditions: validate method");
		}//end if			
		
		
		// bpLow and bpHigh must be entered together or not at all
		if(isNull(record.getBpLow().toString()) && !isNull(record.getBpHigh().toString())	
		 || (!isNull(record.getBpLow().toString()) && isNull(record.getBpHigh().toString()))){
			
			verified = false;
			issue = "Issue 4";
			System.out.println("### EXCEPTION: BOTH BP FIELDS MUST BE COMPLETE OR NONE OF THEM: RecordConditions: validate method");
		}//end if
		
		
		// notes should have less than 50 words
		if(!wordCounter(record.getNote())) {
			verified = false;
			issue = "Issue 5";
			System.out.println("### EXCEPTION: NOTES MUST BE LESS THAN 50 WORDS.");
			
		}
		// test
		System.out.println("### WORD COUNT OF NOTE LESS THAN 51 IS  "+ wordCounter(record.getNote()));
		return verified; 
		
		
		
	}// end validate

	// returns invalid string describing first record issue. 
	public String invalidRecordString (Record record) {
		
		 return labelString;
	}
	
	public void initialize () {
		// Add keys and values (Issue, prompt to user)
					mapOfStrings.put("Issue 1", "BPLOW SHOULD BE HIGHER THAN BPHIGH");
					mapOfStrings.put("Issue 2", "A NUMERIC FIELD WAS NOT NUMERIC");
					mapOfStrings.put("Issue 3", "AT LEAST ONE RECORD FIELD SHOULD HAVE AN ENTRY");
					mapOfStrings.put("Issue 4", "BOTH BP FIELDS MUST BE COMPLETE OR NONE OF THEM");
					mapOfStrings.put("Issue 5", "NOTES MUST BE LESS THAN 50 WORDS.");
				    System.out.println(mapOfStrings);
	}
	
}
