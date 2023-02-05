package application;

import model.User;

// sole function of this class is to hold selected user object to be accessed by different controllers. 
// NOTE: this might be redundant as profile object already exists in the profile and main controller as field. 
public class SelectedUserUtility {

	private static User selectedUser;
	
	public static void clearSelectedUser() {
		
		setSelectedUser(null);
		
	}// end clear

	/**
	 * @return the selectedUser
	 */
	public static User getSelectedUser() {
		return selectedUser;
	}

	/**
	 * @param selectedUser the selectedUser to set
	 */
	public static void setSelectedUser(User selectedUser) {
		SelectedUserUtility.selectedUser = selectedUser;
	}
	
}
