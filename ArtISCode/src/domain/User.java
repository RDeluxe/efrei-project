package domain;

public class User {

	private long id;
	private String firstname;
	private String lastname;
	private String password;
	
	
	void setID(long id) {
		this.id = id;
	}
	long getID() {
		return id;
	}

	void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	String getFirstName() {
		return firstname;
	}
	
	void setLastName(String lastname) {
		this.lastname = lastname;
	}
	String getLastName() {
		return lastname;
	}

	void setPassword(String password) {
		this.password = password;
	}
	String getPassword() {
		return password;
		
	}


}
