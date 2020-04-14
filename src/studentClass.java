import javafx.util.Pair;
import java.util.*;

public class studentClass {

	private int NumberOfStudents=1000;
	private String[] username = new String[getNumberOfStudents()];
	private String[] password = new String[getNumberOfStudents()];
	private int current_limit=0;
	
	
	

	public static void main(String[] args) {
		
		
		
		// TODO Auto-generated method stub

	}
	public void increaseCurrentLimit() {
		this.current_limit+=1;
		
	}
	public int getCurrentLimit() {
		
		return current_limit;
	}
	
	public int getNumberOfStudents() {
		return NumberOfStudents;
		
	}
	
	public void setNumberOfStudents(int Students) {
		this.NumberOfStudents = Students;
		
	}
	public String[] getUsername() {
		return username;
	}
	public String[] getPasswords() {
		return password;
	}
	
	public boolean studentLogin(String user, String pass) {
		int starting=0;
		String[] users = getUsername();
		String[] passwords = getPasswords();
		while(starting<=getCurrentLimit()) {
			String lookingUser = users[starting];
			String lookingPass = passwords[starting];
			if(lookingUser.equals(user) && lookingPass.equals(pass)) {
				return true;
			}
			
			
			starting+=1;
			
		}
		return false;
		
	}

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

