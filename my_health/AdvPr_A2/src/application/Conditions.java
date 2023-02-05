package application;

import model.Record;

public abstract class Conditions {

	
	
	// this abstract class decouples conditions for valid actions with the methods that implement the actions. 
	
	public static boolean validate() {
		
		boolean verified = true; 
		
		return verified;
		
	}// end validate method
	
	// checks if string is numeric
	// source - https://www.baeldung.com/java-check-string-number
	public static boolean isNumeric(String strNum){
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	// null doesn't actually mean null. This tests for blank field values. 
	public static boolean isNull(String strNum) {
		
		
		if (strNum.equals("0.0") || strNum.equals("")) {
	        return true;
	    } else {
	    	return false;}
	}//end isNull

// overloaded
public static boolean isNull(Double Num) {
		
		
		if (Num.equals(0d)) {
	        return true;
	    } else {
	    	return false;}
	}//end isNull


// counts number of words. If 50 or less then returns true
// source: https://www.javatpoint.com/word-count-in-java
public static boolean wordCounter (String text) {
boolean valid;
String words[]=text.split("\\s");  
Integer length=words.length;//returns total number of words  

if (length <= 51) {
	valid = true;
	System.out.println("### WordCounter method: valid = "+ valid + " ####");
} else {
	valid = false;
	System.out.println("### WordCounter method: valid = "+ valid + " ####");
	}
 
return valid;
}//end wordCounter

}// end class
