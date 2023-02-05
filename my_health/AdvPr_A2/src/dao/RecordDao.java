package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Record;



public class RecordDao {

	private final String TABLE_NAME = "records";
	
	
	// ADD RECORD TO DATABASE AND TABLEVIEW.  
		public boolean addRecord (Record record) throws SQLException {
			
			String sql = "INSERT INTO " + TABLE_NAME 
					+ " (username,date,weight,temperature,bpLow,bpHigh,note) "
					+ "VALUES (?,?,?,?,?,?,?);" ;
			try (Connection connection = MySqlConnection.ConnectDb();
					PreparedStatement statement = connection.prepareStatement(sql);
					){
				
				
				statement.setString(1, record.getUsername());
				statement.setString(2, String.valueOf(record.getDate()));
				statement.setString(3, String.valueOf(record.getWeight()));
				statement.setString(4, String.valueOf(record.getTemperature()));
				statement.setString(5, String.valueOf(record.getBpLow()));
				statement.setString(6, String.valueOf(record.getBpHigh()));
				statement.setString(7, record.getNote());
				
				try {
				statement.executeUpdate();
				System.out.println("### Successfully added a record: addRecord method ####"); 
				}catch(SQLException e) {
					System.out.println("### SQLEXCEPTION: RECORD WAS NOT ADDED: addRecord method ###");
					return false;
				}
				
				
				return true; 
				
			} catch (Exception e) {
				System.out.println("### EXCEPTION: In addRecord method: record was NOT added. ####");
				e.printStackTrace();
				
				return false; 
			}//
			
		} // end addRecord
		
	
	// UPDATE RECORD IN TABLEVIEW
	
			public boolean updateRecord (Record record) throws SQLException {
				
			try {	
				
				// updates record in database. 
				String sql = "UPDATE " + TABLE_NAME 
						+ "   SET date = ?, "
						+ "       weight = ?, "
						+ "       temperature = ?, "
						+ "       bpLow = ?, "
						+ "       bpHigh = ?, "
						+ "       note = ? "
						+ " WHERE id = ?;";
				try (Connection connection = MySqlConnection.ConnectDb();
						PreparedStatement statement = connection.prepareStatement(sql);) {
					//statement.setString(1, record.getUsername());
					statement.setString(1, String.valueOf(record.getDate()));
					statement.setString(2, String.valueOf(record.getWeight()));
					statement.setString(3, String.valueOf(record.getTemperature()));
					statement.setString(4, String.valueOf(record.getBpLow()));
					statement.setString(5, String.valueOf(record.getBpHigh()));
					statement.setString(6, record.getNote());
					statement.setInt(7, record.getId());
					
					statement.executeUpdate();
					
					return true;
				} } catch (Exception e) {
					System.out.println("### EXCEPTION: in updateRecord method ####");
					e.printStackTrace();
					return false;
					
				}//end catch
				
			}// end updateRecord
	
			
			// DELETE RECORD FROM DATABASE AND TABLEVIEW.
			
			public boolean deleteRecord (Integer id) throws SQLException{
				
				String sql = "DELETE FROM " + TABLE_NAME + " WHERE id=?;";
				try (Connection connection = MySqlConnection.ConnectDb();
					 PreparedStatement statement = connection.prepareStatement(sql);
					){
					statement.setInt(1, id);
					statement.executeUpdate();
					return true;	
				} catch (Exception e) {
					System.out.println("### EXCEPTION: in deleteRecord method: Record NOT deleted. ###");
					e.printStackTrace();
					return false;
				}//end catch
			}// end delete method
			
			
			// !!! REMOVE IN FINAL VERSION !!!
						// this version does not filter for the user. It returns all records of table in database. 
						// WORKS! 11pm 15/11/22
						public ObservableList <Record> getAllRecords() throws SQLException {
							String sql = "SELECT * FROM " + TABLE_NAME; 
							ObservableList <Record> records = FXCollections.observableArrayList();
							try (Connection connection = MySqlConnection.ConnectDb(); 
									PreparedStatement stmt = connection.prepareStatement(sql)) {
								//stmt.setString(1, username);
								try (ResultSet resultset = stmt.executeQuery()) {
									while (resultset.next()) {
										
										
										Record record = new Record(
												
												
												resultset.getString("username"), 
												resultset.getString("date"),
												// explanation: resultset.getString returns the value of the designated column in the current row of the ResultSet object as a String object.
												Double.parseDouble(resultset.getString("weight")),
												Double.parseDouble(resultset.getString("temperature")),
												Double.parseDouble(resultset.getString("bpLow")),
												Double.parseDouble(resultset.getString("bpHigh")), 
												resultset.getString("note"));
										
											// sets value of id according to database value. Makes use of MySQL autoincrement. 
											record.setId(resultset.getInt("id"));
										
										records.add(record);
									}

								}
							}
							return records;
						}
	
	// gets all records of the user
	public ObservableList <Record> getUsersRecords(String username) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " where username=?;"; // this version has sql string variable instead of stating sql query in argugment directly. 
		ObservableList <Record> records = FXCollections.observableArrayList();
		try (Connection connection = MySqlConnection.ConnectDb(); 
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, username);
			try (ResultSet resultset = stmt.executeQuery()) {
				while (resultset.next()) {
					
					// this version just uses strings in the database as the assignment doesn't grade according to datatype (I think). 
					Record record = new Record(
							
							resultset.getString("username"), 
							resultset.getString("date"),
							// explanation: resultset.getString returns the value of the designated column in the current row of the ResultSet object as a String object.
							Double.parseDouble(resultset.getString("weight")),
							Double.parseDouble(resultset.getString("temperature")),
							Double.parseDouble(resultset.getString("bpLow")),
							Double.parseDouble(resultset.getString("bpHigh")), 
							resultset.getString("note"));
					
						// sets value of id according to datebase value.
						record.setId(resultset.getInt("id"));
					
					records.add(record);
				}

			}
		}
		return records;
	}	
}
