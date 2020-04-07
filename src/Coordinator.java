package logic;

import java.util.*;
import java.io.*;

public class Coordinator extends User {
	
	// Constructor
	public Coordinator(String inputUsername, String inputPassword) {
		super(inputUsername,inputPassword);
	}
	
	/**
	 * Prints how many students an inputted scholarship will be granted to, and how many students applied for that scholarship
	 * You will likely make a variation of this for the GUI
	 * @param inputS allScholarships
	 */
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

	/**
	 * Method used to create a scholarship
	 * You will likely make a variation of this for the GUI
	 * @return the scholarship that's created
	 */
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
		System.out.println("Do you have extra criteria you'd like to add? Please separate each one with a comma. Once you finish, press ENTER.");
		Scanner inputCrite = new Scanner(System.in);		
		String inputCrit = inputCrite.nextLine();
		 
		Scholarship newS = new Scholarship(sName, sReward, sSem, sYear, sRec, sGPA, sW, sDept, sFac, sUni, sDeg, inputCrit);
		
		return newS;
	}
	
	/**
	 * This is an empty function (see my note about this in Start.java)
	 * @param inputS
	 */
	public void editScholarship(ArrayList<Scholarship> inputS) {}
	
	/**
	 * This is used to delete a scholarship.
	 * You will need this for the GUI, but with some slight tweaks on the input
	 * @param inputS allScholarships
	 * @return allScholarships after the indicated scholarship is deleted
	 */
	public ArrayList<Scholarship> removeScholarships(ArrayList<Scholarship> inputS) {
		System.out.println("What is the name of the scholarship you'd like to delete?"); 
		Scanner input = new Scanner(System.in);
		String findName = input.nextLine(); // this is the name of the scholarship to delete

		ArrayList<Integer> indicesToDelete = new ArrayList<Integer>(); // this is where we will store the indices of the scholarships with the indicated name
		int i = 0;
		
		for (Scholarship s : inputS) { // looking through all scholarships for the name...
			if (s.getName().equals(findName)) {
				indicesToDelete.add(i);
			}
			i++;
		}
		
		for (int a : indicesToDelete) { // looking through all scholarships, deleting the ones we found and making them null
			inputS.set(a, null);
			inputS.remove(a);
		}
		return inputS;
	}
	
	/**
	 * This is used to grant scholarships
	 * Helper functions: findScholarship(), chooseApplicants(), findStudent(), alreadyGranted()
	 * grantScholarship() itself will need a variation of for the GUI, but the rest of the functions only need
	 * slight tweaks to work for the GUI (like printing and input). Their mechanisms should still apply to the
	 * GUI otherwise.
	 * @param inputS allScholarships
	 * @param inputA allStudents
	*/
	public void grantScholarship(ArrayList<Scholarship> inputS, ArrayList<Student> inputA) {
		System.out.println("Which scholarship would you like distribute?");
		Scanner scholInput = new Scanner(System.in);
		String scholName = scholInput.nextLine();

		Scholarship selectedS = findScholarship(scholName, inputS);	// find the Scholarship associated with the name of the indicated scholarship

		if (selectedS.getName() != null) {	// this specific if statement won't be used for the GUI
			if (selectedS.getApplicants().size() == 0) {	// THESE ARE IMPORTANT
				System.out.println("There are no applicants to this scholarship yet.");
			} 
			else {
				System.out.println("Here are the applicant(s) of " + selectedS.getName() + ":");
				int counter = 0;
				for (String a : selectedS.getApplicants()) {
					System.out.println("<" + counter + ">" + a);
					counter++;
					chooseApplicants(selectedS, inputA);
				}
			}
		}
		else {	// this probably will not be needed for the GUI
			System.out.println("No scholarship of this name was found.");
		}
	}

	/**
	 * find the Scholarship associated with the name of the indicated scholarship
	 * practically a duplicate function from Start
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

	/**
	 * Used to find the Students that the coordinator wants to grant the scholarship to
	 * @param schol
	 * @param inputA
	 */
	public void chooseApplicants(Scholarship schol, ArrayList<Student> inputA) {
		System.out.println("Please type down the corresponding number for the student you'd like to grant this scholarship to.\nKeep in mind that there are " 
		+ schol.getApplicants().size() + " applicants and this scholarship may only be distributed to " 
		+ schol.getReceive() + " students. Please type ENTER when you wish to exit the loop.");

		boolean loop = true;
		while (loop) {
			try {
				Scanner inputApp = new Scanner(System.in);
				String indexS = inputApp.nextLine();
				int index = Integer.parseInt(indexS);
		
				if (schol.getGranted().size() <= schol.getReceive()) {
					String student = schol.getApplicants().get(index);
					Student foundStu = findStudent(student,inputA);
					if (alreadyGranted(student, schol.getGranted())) {
						System.out.println("This scholarship has already been granted to this student.");
					}
					else {
						foundStu.addStudentGranted(schol.getName());
						schol.addGranted(foundStu.getUsername());
					}
				}
				else {
					System.out.println("This scholarship has been granted to the maximum (" + schol.getReceive() + ") amount of student(s).");
					loop = false;
				}
			}
			catch (Exception e) {
				loop = false;
				System.out.println("Exiting loop. Redirecting.");
			}

		}
	}

	/**
	 * Used to find the Student object associated with the indicated student name
	 * @param name
	 * @param inputS
	 * @return
	 */
	public Student findStudent(String name, ArrayList<Student> inputS) {
		Student toReturn = new Student();
		for (Student s : inputS) {
			if (s.getUsername().equals(name)) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	/**
	 * Used to check if the student has already been granted said scholarship
	 * @param name
	 * @param inputA
	 * @return
	 */
	public boolean alreadyGranted(String name, ArrayList<String> inputA) {
		boolean toReturn = false;
		for (String a : inputA) {
			if (a.equals(name)) {
				toReturn = true;
			}
		}
		return toReturn;
	}

	/**
	 * Used to read the files each student uploaded.
	 * Helper functions: viewFiles() and openFile()
	 * A variation of viewProfiles() will probably be needed for the GUI, but the
	 * other functions will only need minor tweaks
	 *  */
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

	/**
	 * Used to see which files the indicated student uploaded
	 * @param inputS
	 */
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

	/**
	 * Used to see the contents of an indicated file
	 * @param fName
	 */
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


