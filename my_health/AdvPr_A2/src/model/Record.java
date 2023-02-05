package model;

public class Record {

	// changed values to String and Integer objects because of youtube resource
	// source - https://www.youtube.com/watch?v=A5fQbsJ-iF8
	
	private Integer id; // autoincremented in database. No need to add in constructor. 
	
	private String username; 
	
	private String date; 
	
	private Double weight; 
	
	private Double temperature;
	
	private Double bpLow;
	
	private Double bpHigh; 
	
	private String note; 
	
	
	// CONSTRUCTOR
	
	public Record ( String username, String date, Double weight, Double temperature, Double low, Double high, String note ) {
		
	
		this.setUsername(username);
		this.setDate(date); // string
		this.setWeight(weight);
		this.setTemperature(temperature);
		this.setBpLow(low);
		this.setBpHigh(high);
		this.setNote(note);
		
		// refactor!! date issue is still unresolved. 
		
		
	}// end constructor


	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}


	/**
	 * @param date2 the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}


	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}


	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}


	/**
	 * @return the temperature
	 */
	public Double getTemperature() {
		return temperature;
	}


	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}


	/**
	 * @return the bpLow
	 */
	public Double getBpLow() {
		return bpLow;
	}


	/**
	 * @param bpLow the bpLow to set
	 */
	public void setBpLow(Double bpLow) {
		this.bpLow = bpLow;
	}


	/**
	 * @return the bpHigh
	 */
	public Double getBpHigh() {
		return bpHigh;
	}


	/**
	 * @param bpHigh the bpHigh to set
	 */
	public void setBpHigh(Double bpHigh) {
		this.bpHigh = bpHigh;
	}


	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}


	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
} // end class
