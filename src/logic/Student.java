package logic;

import java.util.*;

public class Student extends User {

	//private int NumberOfStudents=1000;
	//private String[] username = new String[getNumberOfStudents()];
	//private String[] password = new String[getNumberOfStudents()];
	//private int current_limit=0;
	
	private String username;
	private String password;
	
	// We can put all the data in a text
	
	// Constructor
	public Student(String inputUsername, String inputPassword) {
		setUsername(inputUsername);
		setPassword(inputPassword);
	}
	
	/*
	 * public void increaseCurrentLimit() { this.current_limit+=1;
	 * 
	 * } public int getCurrentLimit() {
	 * 
	 * return current_limit; }
	 * 
	 * public int getNumberOfStudents() { return NumberOfStudents;
	 * 
	 * }
	 * 
	 * public void setNumberOfStudents(int Students) { this.NumberOfStudents =
	 * Students;
	 * 
	 * }
	 * 
	 * public String[] getUsername() { return username; }
	 * 
	 * public String[] getPasswords() { return password; }
	 */
	
	/*
	 * public boolean studentLogin(String user, String pass) { int starting=0;
	 * String[] users = getUsername(); String[] passwords = getPasswords();
	 * while(starting<=getCurrentLimit()) { String lookingUser = users[starting];
	 * String lookingPass = passwords[starting]; if(lookingUser.equals(user) &&
	 * lookingPass.equals(pass)) { return true; }
	 * 
	 * starting+=1;
	 * 
	 * } return false;
	 * 
	 * }
	 */
	
	// Already extends a class with these methods
	/*
	 * public String getUsername() { return new String(this.username); }
	 * 
	 * public String getPassword() { return new String(this.password); }
	 * 
	 * public void setUsername(String inputUsername) { this.username = new
	 * String(inputUsername); }
	 * 
	 * public void setPassword(String inputPassword) { this.password = new
	 * String(inputPassword); }
	 */
}

// REFERENCES:
/**
 * https://www.w3schools.com/java/java_encapsulation.asp
 * https://www.javatpoint.com/how-to-return-an-array-in-java
 * 
 * 
 * 
 * 
 */

