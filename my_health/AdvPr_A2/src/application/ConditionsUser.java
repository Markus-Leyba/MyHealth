package application;

public class ConditionsUser extends Conditions {

// validates model object
public static boolean validate() {
		
		boolean verified = true; 
		
		return verified;
		
	}// end validate method
	

// checks to see if username is unique in the database. 
public static boolean isUnique(String Username) {
			
		boolean verified = true;
			
		return verified; 
			
		}// end is Unique
}
