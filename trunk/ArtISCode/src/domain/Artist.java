package domain;

public class Artist {
	
	private long id;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private String type;
	private String description;
	
	public void setID(long id) {
		this.id = id;
	}
	public long getID() {
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
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	
	
}
