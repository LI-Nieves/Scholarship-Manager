package logic;

import java.util.*;
import java.io.*;

public class Coordinator extends User {

/* 	private String username;
	private String password; */
	
	// We can put all the data in a text file
	
	// Constructor
	public Coordinator(String inputUsername, String inputPassword) {
		setUsername(inputUsername);
		setPassword(inputPassword);
	}
	
	public void viewStatistics(ArrayList<Scholarship> inputS) {
		System.out.println("For which scholarship would you like to see statistics for?");

		Scanner inputStat = new Scanner(System.in);
		String statSchol = inputStat.nextLine();

		for (Scholarship s : inputS) {
			if (s.getName().equals(statSchol)) {
				System.out.println("This scholarship will be granted to " + s.getReceive() + " applicants.\n" + 
				s.getApplicants().size() + " students have applied to this scholarship:");
				for (String a : s.getApplicants()) {
					System.out.println(a);
				}
			}
		}
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
		
		String sW = "false";
		repeat = true;
		
		while (repeat) {
			Scanner inputW = new Scanner(System.in);
			dummy = inputW.nextLine();
			if (dummy.contentEquals("true") || dummy.contentEquals("false")) {
				sW = dummy;
				repeat = false;
			}
			else {
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
		
		String inputCrit;
		
		Scanner inputCrite = new Scanner(System.in);
		
		inputCrit = inputCrite.nextLine();

/* 		ArrayList<String> inputCrit = new ArrayList<String>();
		
		while (true) {
			String a = inputFac.nextLine();
			if (a.contentEquals("")) {
				break;
			}
			inputCrit.add(a);
		} */
		 
		Scholarship newS = new Scholarship(sName, sReward, sSem, sYear, sRec, sGPA, sW, sDept, sFac, sUni, sDeg, inputCrit);
		
		return newS;
	}
	
	/* Method used to edit a scholarship (PENDING) */
	public void editScholarship(ArrayList<Scholarship> inputS) {
/* 		System.out.println("Which scholarship would you like to edit?");
		
		Scanner inputEdit = new Scanner(System.in);
		String editSchol = inputEdit.nextLine();

		Scholarship targetS;
		for (Scholarship s : inputS) {
			if (s.getName().equals(editSchol)) {
				targetS = s;
			}
		} */

/* 		System.out.println("What would you like to edit? Enter \n<1> for the name \n<2> for the reward amount \n<3> for the semester it's offered in
			\n<4> for the year it's offered in \n<5> for the number of students to receive this scholarship \n<6> for the GPA required
			\n<7> for whether the ") */
	}
	
	/*
	 * public void removeScholarship() { }
	 */
	
	/* Method used to grant a scholarship (incomplete) */
	public void grantScholarship(ArrayList<Scholarship> inputS) {
		System.out.println("Which scholarship would you like distribute?");
		Scanner scholInput = new Scanner(System.in);
		String scholName = scholInput.nextLine();

		Scholarship selectedS = findScholarship(scholName, inputS);

		if (selectedS.getName() != null) {
			System.out.println("Here are the applicant(s) of " + selectedS.getName() + ":");
			int counter = 0;
			for (String a : selectedS.getApplicants()) {
				System.out.println("<" + counter + ">" + a);
				counter++;
				chooseApplicants(selectedS);
			}
		}
		else {
			System.out.println("No scholarship of this name was found.");
		}
	}

	// practically a duplicate from Start
	public Scholarship findScholarship(String name, ArrayList<Scholarship> inputS) {
		Scholarship toReturn = new Scholarship();
		for (Scholarship s : inputS) {
			if (s.getName().equals(name)) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	public void chooseApplicants(Scholarship schol) {
		System.out.println("Please type down the corresponding number(s) for the students you'd like to grant this scholarship to.\nKeep in mind that there are " 
		+ schol.getApplicants().size() + " applicants and this scholarship may only be distributed to " 
		+ schol.getReceive() + " students. Please format your reply as shown: 0 1 2 ..");

		Scanner inputApp = new Scanner(System.in);

	}

	public void viewProfiles(ArrayList<Student> inputS) {
		System.out.println("For which student would you like to view the profile of?");

		Scanner inputName = new Scanner(System.in);
		String name = inputName.nextLine();

		boolean studentNotFound = true;
		for (Student a : inputS) {
			if (a.getUsername().equals(name)) {
				viewFiles(a);
				studentNotFound = false;
			}
		}
		
		if (studentNotFound) {
			System.out.println("No student of that name has been found.");
		}
	}

	public void viewFiles(Student inputS) {

		if (inputS.getStudentFiles().size() == 0) {
			System.out.println("The student " + inputS.getUsername() + " has not uploaded any files. Redirecting.");
		}

		else {
			System.out.println("The student " + inputS.getUsername() + " has uploaded " + inputS.getStudentFiles().size() + " files. Type the name of the file to view or press ENTER to quit:");
			for (String a : inputS.getStudentFiles()) {
				System.out.println(a);
			}
			Scanner inputF = new Scanner(System.in);
			String fName = inputF.nextLine();
	
			// if the file's name is "" this doesn't work lmao
			if (fName.equals("")) {}
			else {
				boolean fileMissing = true;
				for (String a : inputS.getStudentFiles()) {
					if (a.equals(fName)) {
						openFile(fName);
						fileMissing = false; 
					}
				}
				if (fileMissing) {
					System.out.println("The file was not found. Try again.");
					viewFiles(inputS);
				}
			}
		}
	}

	public void openFile(String fName) {
		String fileDir = "uploadedFiles/" + fName;

		Scanner inputStream = null;
		try {
		inputStream = new Scanner(new File(fileDir));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + fileDir);
			System.exit(0);
		}

		while(inputStream.hasNextLine()) {
			System.out.println(inputStream.nextLine());
		}
	}

}


