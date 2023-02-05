package model;

public class User {


	
	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;
	
	
	
	public User(String firstName, String lastName, String userName, String passWord) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.passWord = passWord;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return userName;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.userName = username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return passWord;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.passWord = password;
	}
	
	
}
