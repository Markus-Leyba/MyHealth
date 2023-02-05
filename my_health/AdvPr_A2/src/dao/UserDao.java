package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

// DAO LAYER

// source - https://stackoverflow.com/questions/65115606/way-to-store-mysql-data-into-java-objects

public class UserDao {

	private final String TABLE_NAME = "users";
	
	
	// V2 gets user object from db
	public User getUserObject(String username) throws SQLException {
		
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
		try (Connection connection = MySqlConnection.ConnectDb();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, username);
			

			try (ResultSet resultset = statement.executeQuery()) {
				if (resultset.next()) {
					User user = new User(resultset.getString("firstName"), resultset.getString("lastName"), resultset.getString("userName"),
							resultset.getString("passWord"));
					
					// TEST BLOCK ONLY
					// test marker B: result set does not print here either. 
					System.out.println("### resultset firstname = " + resultset.getString("firstName"));
					System.out.println("### resultset lastname = " + resultset.getString("lastName"));
					System.out.println("### resultset username = " + resultset.getString("userName"));
					System.out.println("### resultset password = " + resultset.getString("passWord"));
					return user;
				}
				return null;
			}
		}
		
	}//end getuserobject
	
	// creates a user
	public User addUser(String firstName, String lastName, String userName, String passWord)throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?)";
		try (Connection connection = MySqlConnection.ConnectDb();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, userName);
			statement.setString(4, passWord);
		

			statement.executeUpdate();
			
			// create user object
			User user = new User(firstName, lastName, userName, passWord);
			
			return user;
		}
	} // end createUser
	
	
	// updates user info
	public boolean updateUser(User user) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME + " SET firstName=?, lastName=? WHERE userName=?";
		try (Connection connection = MySqlConnection.ConnectDb();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUsername());
			
			statement.executeUpdate();
			
			return true; 
		} catch (Exception e) {
			System.out.println("### EXCEPTION: in updateUser method ####");
			e.printStackTrace();
			return false;
		}//end catch
	}
	
	
	
	
		
		// checks if user exists in db 
		// source - https://stackoverflow.com/questions/56734690/validate-a-value-that-already-exists-in-database-in-javafx
		public boolean checkUserExists(String userName) throws SQLException{
			
	        //boolean memberExists = false;
	        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE userName = ?";
	        
	        try (Connection conn= MySqlConnection.ConnectDb();
	        PreparedStatement stm = conn.prepareStatement(sql);){
	        	stm.setString(1, userName);
	        	
	        	ResultSet rst = stm.executeQuery();
	    

	        if (rst.next()){
	          {
	               return true;
	            }
	        } else {return false;
	        }}catch (Exception e) {
	        	System.out.println("### EXCEPTION in checkUserExists method");
	        	e.printStackTrace();
	        	
	        	return false;
	        }// end catch
	       
	        	
		}// end checkMemberExists
		
		// V1 - checks if password matches username - Relies on getUser method which is having issues for me
		public boolean checkUserPassword(String userName, String passWord) throws SQLException {
			
			User user = getUserObject(userName);
			String correctPassWord = user.getPassword();
			
			System.out.println("This is passWord " + passWord);
			System.out.println("This is correctPassWord " + correctPassWord);
		
			// if passwords matches
			if (passWord.equals(correctPassWord)) {
				System.out.println("### Password matches ####");
				return true; 
			} else { 
				System.out.println("### Password does not match ####");
				return false;
			}
		
		}//end checkUserPassword
		
		// V2 - checks if password matches username
		public boolean checkPasswordMatches(String userName, String PassWord) throws SQLException{
			
	        //boolean memberExists = false;
	        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE userName = ? AND passWord = ?";
	        
	        try (Connection conn= MySqlConnection.ConnectDb();
	        PreparedStatement stm = conn.prepareStatement(sql);){
	        	stm.setString(1, userName);
	        	stm.setString(2, PassWord);
	        	ResultSet rst = stm.executeQuery();
	        	
	    

	        if (rst.next()){
	          {
	               return true;
	            }
	        } else {return false;
	        }//end try
	        }
		}
}//end class
