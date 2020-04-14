

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
	 public User(String inputUsername) {	//I ADDED THIS- KAM
		 setUsername(inputUsername);
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
	 * @param inputScholarship
	 */
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
	public String viewScholarshipsGui(ArrayList<Scholarship> inputScholarship) {
		String returnThis="";
		if (inputScholarship.size() <= 0) {
			System.out.println("There are no scholarships available.");
			returnThis = "There are no scholarships avaliable.";
			return returnThis;
		}
		for (Scholarship s : inputScholarship) {
			String eachScholarship="";
			System.out.println(s.getName() + "\nReward: $" + s.getRewardAmount() + "\nTerm(s) considered: " + s.getSemester() + " " + 
					s.getYear() + "\n" + s.getReceive() + " student(s) may receive this award for the indicated term(s)" + 
					"\nGPA requirement: " + s.getGPAreq() + " or above\nA student may have a \"W\" on their transcript: " + s.getWonTranscript());
			System.out.println(s.getDeptSpecific() + "\n" + s.getFacultySpecific() + "\n" + s.getUniSpecific() + "\n" + s.getDegreeSpecific());
			System.out.println("Extra criteria:\n" + s.getExtraCriteria());
			System.out.println();
			eachScholarship = s.getName() + "\nReward: $" + s.getRewardAmount() + "\nTerm(s) considered: " + s.getSemester() + " " + 
					s.getYear() + "\n" + s.getReceive() + " student(s) may receive this award for the indicated term(s)" + 
					"\nGPA requirement: " + s.getGPAreq() + " or above\nA student may have a \"W\" on their transcript: " + s.getWonTranscript()+ "\n"+
					s.getDeptSpecific() + "\n" + s.getFacultySpecific() + "\n" + s.getUniSpecific() + "\n" + s.getDegreeSpecific()+"\n"+
					"Extra criteria: " + s.getExtraCriteria() + "\n"+"--------------------------------------------------------------------------"+ "\n\n";
			returnThis = returnThis.concat(eachScholarship);
		}
		return returnThis;

	}
	

	/**
	 * find the Scholarship associated with the name of the indicated scholarship
	 * practically a duplicate function from Start, and this method is used in both Coordinator and Student
	 * @param name indicated scholarship
	 * @param inputS allScholarships
	 * @return	the Scholarship with the name of the indicated scholarship
	 */
	public Scholarship findScholarship(String name, ArrayList<Scholarship> inputS) {
		Scholarship toReturn = new Scholarship();
		for (Scholarship s : inputS) {
			if (s.getName().equals(name)) {
				toReturn = s;
			}
		}
		return toReturn;
	}

}
