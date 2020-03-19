package logic;

public abstract class User {
	
	// Instance variables
	private String username;
	private String password;
	
	// Can't be instantiated lmao
	/*
	 * public User(String inputUsername, String inputPassword) {
	 * setUsername(inputUsername); setPassword(inputPassword); }
	 */
	

	public String getUsername() {
		return new String(this.username);
	}
	
	public String getPassword() {
		return new String(this.password);		
	}
	
	public void setUsername(String inputUsername) {
		this.username = new String(inputUsername);
	}
	
	public void setPassword(String inputPassword) {
		this.password = new String(inputPassword);
	}
	
	//public abstract void login();

}
