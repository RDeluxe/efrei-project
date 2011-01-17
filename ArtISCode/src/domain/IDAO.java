package domain;

public interface IDAO {
	
	void CreatUser(String firstname, String lastname, String Email);
	void ModifyProfile();
	
	Artist SearchArtistByName(String firstname,String lastnae);
	Artist SearchArtistByType(String type);
	Artist SearchArtistByLocation(String location);
	
	Boolean CheckPassword(String Email, String password);
	void ForgetPassword(String Email);
	
	
	
	
	
	
	
}
