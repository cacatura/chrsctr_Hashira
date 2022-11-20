package application;

public class User {
	private String userName, passWord; 
	
	public User (String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getPassWord() {
		return this.passWord;
	}
}
