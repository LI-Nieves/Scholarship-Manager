package logic;

import java.util.*;

public class Coordinator extends User {

/* 	private String username;
	private String password; */
	
	// We can put all the data in a text file
	
	// Constructor
	public Coordinator(String inputUsername, String inputPassword) {
		setUsername(inputUsername);
		setPassword(inputPassword);
	}
	
	/* Method used to add a scholarship */
	public Scholarship addScholarship() {
		boolean repeat; // this will help for catching errors
		String dummy;
		
		Scanner input = new Scanner(System.in);
		
		// GETTING THE NAME 
		System.out.println("What is the name of the scholarship?");
		
		String sName = input.nextLine();

		// GETTING THE REWARD AMOUNT
		System.out.println("How much is the reward? Please enter your amount only, with only numbers."); 
		
		int sReward = 0;
		repeat = true;
		
		while (repeat) {
			Scanner inputRew = new Scanner(System.in);
			try {
				sReward = inputRew.nextInt();
				repeat = false;
			}
			catch (Exception e) {
				System.out.println("Invalid input. Please enter it again.");
			}
		}
		
		// GETTING THE SEMESTER IT APPLIES TO
		System.out.println("Does this scholarship apply to a certain semester <Fall, Winter>? If not, press ENTER."); 
		
		String sSem = "Fall and Winter";
		repeat = true;
		
		while (repeat) {
			Scanner inputSem = new Scanner(System.in);
			dummy = inputSem.nextLine();
			if (dummy.contentEquals("Fall") || dummy.contentEquals("Winter")) {
				sSem = dummy;
				repeat = false;
			}
			else if (dummy.contentEquals("")) {
				repeat = false;
			}
			else {
				System.out.println("Invalid input. Please enter it again.");
			}
		}
		
		// GETTING THE YEAR IT APPLIES TO
		System.out.println("Which year does this scholarship apply to?"); 
		
		int sYear = 0;
		repeat = true;
		
		while (repeat) {
			Scanner inputYear = new Scanner(System.in);
			try {
				sYear = inputYear.nextInt();
				repeat = false;
			}
			catch (Exception e) {
				System.out.println("Invalid input. Please enter it again.");
			}
		}
		
		// GETTING HOW MANY STUDENTS CAN RECEIVE THIS SCHOLARSHIP
		System.out.println("How many students can receive this scholarship during the designated term?"); 
		
		int sRec = 0;
		repeat = true;
		
		while (repeat) {
			Scanner inputRec = new Scanner(System.in);
			try {
				sRec = inputRec.nextInt();
				repeat = false;
			}
			catch (Exception e) {
				System.out.println("Invalid input. Please enter it again.");
			}
		}
		
		// GETTING THE GPA REQ
		System.out.println("What is the GPA requirement? Please enter the decimal number only.");
		
		double sGPA = 0.0;
		repeat = true;
		
		while (repeat) {
			Scanner inputGPA = new Scanner(System.in);
			try {
				sGPA = inputGPA.nextDouble();
				repeat = false;
			}
			catch (Exception e) {
				System.out.println("Invalid input. Please enter it again.");
			}
		}
		
		// GETTING THE W REQ
		System.out.println("Could the applicant have a \"W\" on their transcript? Please input \"true\" or \"false\".");
		
		boolean sW = false;
		repeat = true;
		
		while (repeat) {
			Scanner inputW = new Scanner(System.in);
			try {
				sW = inputW.nextBoolean();
				repeat = false;
			}
			catch (Exception e) {
				System.out.println("Invalid input. Please enter it again.");
			}
		}
		
		// GETTING DEPT REQ
		System.out.println("Is this department-specific? If so, please type the name of the department. If not, press ENTER.");
		
		String sDept = "Across all departments";
		
		Scanner inputDept = new Scanner(System.in);
		
		dummy = inputDept.nextLine();

		if (!(dummy.equals(""))) {
			sDept = dummy;
		}
		
		// GETTING FAC REQ
		System.out.println("Is this faculty-specific? If so, please type the name of the faculty. If not, press ENTER.");
		
		String sFac = "Across all faculties";
		
		Scanner inputFac = new Scanner(System.in);
		
		dummy = inputFac.nextLine();

		if (!(dummy.equals(""))) {
			sFac = dummy;
		}
		
		// GETTING UNI REQ
		System.out.println("Is this university-specific? If so, please type the name of the university. If not, press ENTER.");
		
		String sUni = "Across all universities";
		
		Scanner inputUni = new Scanner(System.in);
		
		dummy = inputUni.nextLine();

		if (!(dummy.equals(""))) {
			sUni = dummy;
		}
		
		// GETTING FAC REQ
		System.out.println("Is this degree-specific? If so, please type the name of the degree. If not, press ENTER.");
		
		String sDeg = "Across all degrees (BA, BSc, MS, MBA, PhD, etc.)";
		
		Scanner inputDeg = new Scanner(System.in);
		
		dummy = inputDeg.nextLine();

		if (!(dummy.equals(""))) {
			sDeg = dummy;
		}
		
		// GETTING EXTRA CRITERIA
		System.out.println("Do you have extra criteria you'd like to add? After typing each one, press ENTER. Once you have none, press ENTER without typing anything.");
		
		ArrayList<String> inputCrit = new ArrayList<String>();
		
		while (true) {
			String a = inputFac.nextLine();
			if (a.contentEquals("")) {
				break;
			}
			inputCrit.add(a);
		}
		 
		Scholarship newS = new Scholarship(sName, sReward, sSem, sYear, sRec, sGPA, sW, sDept, sFac, sUni, sDeg, inputCrit);
		
		return newS;
	}
	
	/* Method used to edit a scholarship (incomplete) */
	public void editScholarship() {
		
	}
	
	/*
	 * public void removeScholarship() { }
	 */
	
	/* Method used to grant a scholarship (incomplete) */
	public void grantScholarship() {
		
	}
}


