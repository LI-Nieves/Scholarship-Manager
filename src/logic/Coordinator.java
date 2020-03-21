package logic;

import java.util.*;

public class Coordinator extends User {

	private String username;
	private String password;
	
	// We can put all the data in a text file
	
	// Constructor
	public Coordinator(String inputUsername, String inputPassword) {
		setUsername(inputUsername);
		setPassword(inputPassword);
	}
	
	public Scholarship addScholarship() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("What is the name of the scholarship?");
		
		String sName = input.nextLine();
		
		System.out.println("How much is the reward? Please enter your amount only, with only numbers.");
		
		int sReward = input.nextInt();
		
		System.out.println("What is the GPA requirement? Please enter the decimal number only.");
		
		float sGPA = input.nextFloat();
		
		System.out.println("Could the applicant have a \"W\" on their transcript? Please input \"true\" or \"false\".");
		
		boolean sW = input.nextBoolean();
		
		System.out.println("Is this department-specific? If so, please type the name of the department. If not, please type \"Across all departments\".");
		
		String sDept = input.nextLine();
		
		System.out.println("Is this faculty-specific? If so, please type the name of the faculty. If not, please type \"Across all faculties\".");		
		
		String sFac = input.nextLine();
		
		System.out.println("Is this university-specific? If so, please type the name of the university. If not, please type \"Across all universities\".");
		
		String sUni = input.nextLine();
		
		System.out.println("Is this degree-specific? If so, please type the name of the degree. If not, please type \"Across all degrees\".");
		
		String sDeg = input.nextLine();
		
		System.out.println("Do you have extra criteria you'd like to add? After typing each one, press ENTER. Once you have none, press ENTER without typing anything.");
		
		/*
		 * ArrayList<String> inputCrit = new ArrayList<String>();
		 * 
		 * while (input.hasNextLine()) { inputCrit.add(input.nextLine()); }
		 */
		
		ArrayList<String> inputCrit = new ArrayList<String>();
		
		String a = input.nextLine();
		
		inputCrit.add(a);
		
		Scholarship newS = new Scholarship(sName, sReward, sGPA, sW, sDept, sFac, sUni, sDeg, inputCrit);
		
		return newS;
		
	}
	
	public void editScholarship() {
		
	}
	
	public void removeScholarship() {
		
	}
	
	public void grantScholarship() {
		
	}
}


