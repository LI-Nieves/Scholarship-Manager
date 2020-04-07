package logic;

import java.util.*;

public class User {
	
	// Instance variables
	private String username;
	private String password;
	
	public User() {}

	 public User(String inputUsername, String inputPassword) {
		setUsername(inputUsername); 
		setPassword(inputPassword); 
	}

	// Getters
	public String getUsername() {
		return new String(this.username);
	}
	public String getPassword() {
		return new String(this.password);		
	}
	
	// Setters
	public void setUsername(String inputUsername) {
		this.username = new String(inputUsername);
	}
	public void setPassword(String inputPassword) {
		this.password = new String(inputPassword);
	}
	
	/**
	 * Method used to view all scholarships available
	 * A variation of this will be needed for the GUI
	 *  */ 
	public void viewScholarships(ArrayList<Scholarship> inputScholarship) {
		if (inputScholarship.size() <= 0) {
			System.out.println("There are no scholarships available.");
		}
		for (Scholarship s : inputScholarship) {
			System.out.println(s.getName() + "\nReward: $" + s.getRewardAmount() + "\nTerm(s) considered: " + s.getSemester() + " " + 
					s.getYear() + "\n" + s.getReceive() + " student(s) may receive this award for the indicated term(s)" + 
					"\nGPA requirement: " + s.getGPAreq() + " or above\nA student may have a \"W\" on their transcript: " + s.getWonTranscript());
			System.out.println(s.getDeptSpecific() + "\n" + s.getFacultySpecific() + "\n" + s.getUniSpecific() + "\n" + s.getDegreeSpecific());
			System.out.println("Extra criteria:\n" + s.getExtraCriteria());
			System.out.println();
		}

	}

}
