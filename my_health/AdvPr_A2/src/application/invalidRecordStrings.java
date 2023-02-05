package application;

import java.util.HashMap;

public interface invalidRecordStrings {
	
	// this interface is where strings which are displayed in the javafx label elements are hardcorded. 
	
	// Map of strings to be applied to label element in FXML
	
		HashMap<String, String> mapOfStrings = new HashMap<String, String>();

		 // Add keys and values (Issue, prompt to user)
		mapOfStrings.put("England", "London");
		mapOfStrings.put("Germany", "Berlin");
		mapOfStrings.put("Norway", "Oslo");
		mapOfStrings.put("USA", "Washington DC");
	    System.out.println(mapOfStrings);
}
