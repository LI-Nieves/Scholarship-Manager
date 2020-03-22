package logic;

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
	
	public void viewScholarships(ArrayList<Scholarship> inputScholarship) {
		if (inputScholarship.size() <= 0) {
			System.out.println("There are no scholarships available.");
		}
		for (Scholarship s : inputScholarship) {
			System.out.println(s.getName() + "\nReward: $" + s.getRewardAmount() + "\nGPA requirement: " + s.getGPAreq() + "\n" + printWonTranscript(s.getWonTranscript()));
			System.out.println(s.getDeptSpecific() + "\n " + s.getFacultySpecific() + "\n " + s.getUniSpecific() + "\n " + s.getDegreeSpecific());
			printExtraCriteria(s);
			System.out.println();
		}

	}
	
	public void printExtraCriteria(Scholarship inputScholarship) {
		ArrayList<String> newExtraCriteria = inputScholarship.getExtraCriteria();
		
		System.out.println("Extra criteria:");
		
		for (String c : newExtraCriteria) {
			System.out.println(c);
		}
		
	}
	
	public String printWonTranscript(boolean inputW) {
		String returnString;
		if (inputW) {
			returnString = "Applicants may have a \"W\" on their transcript.";
		}
		else {
			returnString = "Applicants may not have a \"W\" on their transcript.";
		}
		return returnString;
	}
	
	//public abstract void login();

}
