package logic;

public abstract class User {
	
	// Instance variables
	private String username;
	private String password;
	
	public User(String inputUsername, String inputPassword) {
		setUsername(inputUsername);
		setPassword(inputPassword);
	}
	
	public String getUsername() {
		String newUsername = this.username;
		return newUsername;
	}
	
	public String getPassword() {
		String newPassword = this.password;
		return newPassword;		
	}
	
	public void setUsername(String inputUsername) {
		this.username = new String(inputUsername);
	}
	
	public void setPassword(String inputPassword) {
		this.password = new String(inputPassword);
	}
	
	public abstract void login();

}
