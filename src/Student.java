package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Student extends User {
	
	private ArrayList<String> scholarshipsAppliedTo = new ArrayList<String>();
	private ArrayList<String> studentFiles = new ArrayList<String>();
	private ArrayList<String> scholarshipsGranted = new ArrayList<String>();
	private ArrayList<String> scholarshipsAccepted = new ArrayList<String>();
	private ArrayList<String> termsAndYearAccepted = new ArrayList<String>();
	
	
	// Constructors
	public Student() {}

	public Student(String inputUsername, String inputPassword) {
		super(inputUsername,inputPassword);
	}
	
	// GETTERS
	// sketchy
	public ArrayList<String> getStudentFiles() {
		return this.studentFiles;
	}
	// sketchy
	public ArrayList<String> getStudentApplied() {
		return this.scholarshipsAppliedTo;
	}
	// sketchy
	public ArrayList<String> getStudentGranted() {
		return this.scholarshipsGranted;
	}
	// sketchy
	public ArrayList<String> getStudentAccepted() {
		return this.scholarshipsAccepted;
	}
	// sketchy
	public ArrayList<String> getStudentTermsYearAccept() {
		return this.termsAndYearAccepted;
	}

	// SETTERS
	public void addStudentFiles(String inputFileName) {
		this.studentFiles.add(inputFileName);
	}
	public void addStudentApplied(String inputSchol) {
		this.scholarshipsAppliedTo.add(inputSchol);
	}
	public void addStudentGranted(String inputSchol) {
		this.scholarshipsGranted.add(inputSchol);
	}
	public void addStudentAccepted(String inputSchol) {
		this.scholarshipsAccepted.add(inputSchol);
	}
	public void addStudentTermYear(String inputTY) {
		this.termsAndYearAccepted.add(inputTY);
	}	
	
	/* Method used to apply to a scholarship (incomplete) */
	public void chooseTerm(Student inputStudent, ArrayList<Scholarship> inputS) {

		// Asking for desired term and year
		System.out.println("Which term would you like to see the scholarships of?\nType <1> for Fall, <2> for Winter, and <3> for Fall and Winter.");
		Scanner termInput = new Scanner(System.in);
		
		int termInt = termInput.nextInt();
		String term = "";

		if (termInt == 1) {
			term = "Fall";
		}
		else if (termInt == 2) {
			term = "Winter";
		}
		else if (termInt == 3) {
			term = "Fall and Winter";
		}

		// no error checking for other entries...

		System.out.println("Which year would you like to see the scholarships of?");
		Scanner yearInput = new Scanner(System.in);
		
		int year = yearInput.nextInt();

		// no error checking for other entries...		

		System.out.println("term: " + term);
		System.out.println("year: " + year);
		
		ArrayList<Scholarship> potentialScholar = new ArrayList<Scholarship>();
		
		for (Scholarship s : inputS) {
			if (term.contentEquals(s.getSemester()) && (year == s.getYear())) {
				potentialScholar.add(s);
			}
		}
		
		if (potentialScholar.size() <= 0) {
			System.out.println("Sorry, there are no scholarships for that term and/or year. Please try again.");
			chooseTerm(inputStudent, inputS);
		}
		else if (applyLimit(year, inputS)) { // checking if the student has already applied to the maximum # of scholarships for that year
			System.out.println("You have already applied to the maximum of FOUR (4) scholarships for " + year + ".");
		}
		else {
			apply(inputStudent, potentialScholar);
		}
		
	}
	
	public void apply(Student inputStudent, ArrayList<Scholarship> inputS) {
		System.out.println("Which scholarship would you like to apply to?");
		
		boolean notApplied = true;
		
		while (notApplied) {
			Scanner scholInput = new Scanner(System.in);
			String toApply = scholInput.nextLine();
			
			for (Scholarship s : inputS) { // looking through all scholarships for the name...
				if (s.getName().equals(toApply)) {
					if (alreadyApplied(inputStudent, s)) {
						System.out.println("You have already applied for this scholarship.");
					}
					else {
						//s.addApplicant(inputStudent);
						s.addApplicant(inputStudent.getUsername());
						inputStudent.scholarshipsAppliedTo.add(s.getName());
						System.out.println("Congratulations! You have applied. Good luck.");
					}
					
					notApplied = false;
				}
			}
			
			if (notApplied) {
				System.out.println("A scholarship with that name has not been found. Please enter the name of the scholarship you'd like to apply to.");
			}
		}
	}
	
	public boolean alreadyApplied(Student inputStudent, Scholarship inputS) {
		boolean applied = false;
		String studentName = inputStudent.getUsername();

		for (String a : inputS.getApplicants()) {
			if (studentName.equals(a)) {
				applied = true;
			}
		}
		return applied;
	}

	public boolean applyLimit(int year, ArrayList<Scholarship> inputS) {
		int applyInYear = 0;
		for (Scholarship s : inputS) {
			if (s.getYear() == year) {
				applyInYear++;
			}
		}
		if (applyInYear == 4) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Method used to view the scholarships they applied for
	 * You'll need a variation of this for the GUI
	 */
	public void viewMyScholarships() {
		System.out.println("Student " + super.getUsername() + " " + super.getPassword() + " has applied to "
				+ scholarshipsAppliedTo.size() + " scholarships.");
		System.out.println("Scholarships applied to:");
		for (String s : scholarshipsAppliedTo) {
			System.out.println(s);
		}
	}
	
	/* 
	 * Method used to upload transcripts, resumes, etc.
	 * Basically, you give it the directory (including the file name) of the file you'd like to upload
	 * "uploading" is just the function parsing the file and copying the exact same thing in another 
	 * file that's created in a designated folder accessible to the program
	 * You'll just need to tweak this for the GUI
	 */
	public void upload() {
		System.out.println("Please type in the directory that your file is in.");
		
		// taking in where the file is
		Scanner upload = new Scanner(System.in);
		String fileDirectory = upload.nextLine());
		
		System.out.println("What would you like to call the uploaded file?");
		
		// taking in desired file name
		Scanner inputName = new Scanner(System.in);
		String fileName = upload.nextLine();
		
		
		// add this file to the student's list of files they've uploaded
		studentFiles.add(fileName + ".txt");
		
		// trying to open file to read
        Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(fileDirectory));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + fileDirectory);
            System.exit(0);
        }
		
		// path to place file
        String completeUploadDir = "uploadedFiles/" + fileName + ".txt";
        
		// Opening up for printing (I/O)
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (completeUploadDir, true));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + completeUploadDir);
            System.exit(0);
        }
		
        // parsing file, reading each line from source and writing it to destination 
        while (inputStream.hasNextLine()) {
			  try {
		        	outputStream.println(inputStream.nextLine());
			  } 
			  catch (NoSuchElementException e) {
				  System.out.println("File read error."); 
			  }
        }     
 
        inputStream.close();
		outputStream.close();
		
	}

	/**
	 * Used to view scholarships that have been granted to the student, and to accept them (if they want to)
	 * Helper functions: findScholarships(), alreadyAccepted(), yearAccepted(), checkAccepted(), addToTermYear()
	 * @param inputS
	 */
	public void viewGranted(ArrayList<Scholarship> inputS) {
		for (String a : getStudentGranted()) {
			System.out.println(a + " (" + findScholarship(a, inputS).getSemester() + " " + findScholarship(a, inputS).getYear() + ")");
		}

		System.out.println("Which scholarship(s) would you like to accept? Keep in mind you can only accept ONE (1) per academic year." + 
		"(Fall and Winter 2019 = Fall 2019 and Winter 2020). Press ENTER to return to the main menu.");

		while (true) {
			Scanner input = new Scanner(System.in);
			String schol = input.nextLine();

			if (schol.equals("")) {
				break;
			}

			Scholarship selectedSchol = findScholarship(schol, inputS);

			// check if accepted already
			if (alreadyAccepted(selectedSchol.getName())) {
				System.out.println("You've already accepted this scholarship.");
			}

			// check if academic year accepted already
			else if (yearAccepted(selectedSchol.getSemester(), selectedSchol.getYear())) {
				System.out.println("You've already accepted a scholarship for this academic year.");
			}

			// accept
				
				// add to list of accepted terms
			else {
				// add to list of accepted
				addStudentAccepted(selectedSchol.getName());
				addToTermYear(selectedSchol.getSemester(), selectedSchol.getYear());
				System.out.println("Congratulations! You've accepted the " + selectedSchol.getName() + ".");
			}


		}
	}

	public Scholarship findScholarship(String name, ArrayList<Scholarship> inputS) {
		Scholarship toReturn = new Scholarship();
		for (Scholarship s : inputS) {
			if (s.getName().equals(name)) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	public boolean alreadyAccepted (String name) {
		boolean toReturn = false;
		for (String a : getStudentAccepted()) {
			if (a.equals(name)) {
				toReturn = true;
			}
		}
		return toReturn;
	}

	public boolean yearAccepted (String term, int year) {
		boolean toReturn = false;
		if (term.equals("Fall and Winter") || term.equals("Fall")) {
			if (checkAccepted("Fall " + year, "Winter " + (year+1))) {
				toReturn = true;
			}
		}
		else if (term.equals("Winter")) {
			if (checkAccepted("Fall " + (year-1), "Winter " + year)) {
				toReturn = true;
			}
		}
		return toReturn;
	}

	public boolean checkAccepted (String term1, String term2) {
		boolean toReturn = false;
		for (String a : getStudentTermsYearAccept()) {
			if (a.equals(term1) || a.equals(term2)) {
				toReturn = true;
			}
		}
		return toReturn;
	}

	public void addToTermYear (String term, int year) {
		if (term.equals("Fall and Winter") || term.equals("Fall")) {
			addStudentTermYear("Fall " + year);
			addStudentTermYear("Winter " + (year+1));
		}
		else if (term.equals("Winter")) {
			addStudentTermYear("Fall " + (year-1));
			addStudentTermYear("Winter " + year);
		}
	}
}

