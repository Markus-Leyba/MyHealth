package model;

import controllers.View_Options;

public class Profile {
	
	// each profile has a user 
	private User user; 
	
	// setting object for fonts etc 
	private View_Options UiSettings;

	// CONSTRUCTOR
	public Profile (User user) {
		
		this.user = user;
		
	}// end constructor
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the uiSettings
	 */
	public View_Options getUiSettings() {
		return UiSettings;
	}

	/**
	 * @param uiSettings the uiSettings to set
	 */
	public void setUiSettings(View_Options uiSettings) {
		UiSettings = uiSettings;
	}

}
