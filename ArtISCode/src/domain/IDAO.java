package domain;

public interface IDAO {
	
	void AddUser(String firstname, String lastname, String Email);
	void ModifyProfile();
	
	Artist SearchArtistByName(String firstname,String lastnae);
	Artist SearchArtistByType(String type);
	Artist SearchArtistByLocation(String location);
	
	
	
	
	
	
	
	
	
	
}
