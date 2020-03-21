package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class User {
	
	// Instance variables
	private String username;
	private String password;
	
	private String scholarshipDatabase = "ScholarshipDatabase.txt";
	
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
	
	public void viewScholarships() {
		// in case of error in reading from file
        Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(scholarshipDatabase));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + scholarshipDatabase);
            System.exit(0);
        }
        
        // parse file to see if user exists
        if (inputStream.hasNextLine()) {
	        while (inputStream.hasNextLine()) {
				  try {
					  System.out.println(inputStream.nextLine());
				  } 
				  catch (NoSuchElementException e) {
					  System.out.println("Error encountered while reading file."); 
				  }
	        }
        }
        else {
        	System.out.println("There are no scholarships available.");
        }

	}
	
	//public abstract void login();

}
