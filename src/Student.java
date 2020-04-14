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
	
	/**
	 * This starts the process of applying to scholarships. This specific function is used to choose the term and year of the scholarships they're gonna apply for.
	 * Helper functions: apply(), alreadyApplied(), applyLimit().
	 * 
	 * */
	public void chooseTerm(Student inputStudent, ArrayList<Scholarship> inputS) {

		// Asking for desired term to apply for
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

		// Asking for desired year to apply for 
		System.out.println("Which year would you like to see the scholarships of?");
		Scanner yearInput = new Scanner(System.in);
		int year = yearInput.nextInt();

		// no error checking for other entries...		
		
		// this is an ArrayList of all scholarships in the term + year the user indicated
		ArrayList<Scholarship> potentialScholar = new ArrayList<Scholarship>();
		for (Scholarship s : inputS) {
			if (term.contentEquals(s.getSemester()) && (year == s.getYear())) {
				potentialScholar.add(s);
			}
		}
		
		// if there are no scholarships of that term + year indicated...
		if (potentialScholar.size() <= 0) {
			System.out.println("Sorry, there are no scholarships for that term and/or year. Please try again.");
			chooseTerm(inputStudent, inputS);
		}
		else if (applyLimit(term, year, inputS)) { // checking if the student has already applied to the maximum # of scholarships for that year
			System.out.println("You have already applied to the maximum of FOUR (4) scholarships for " + year + ".");
		}
		else {
			// this initiates the application process
			apply(inputStudent, potentialScholar);
		}
		
	}
	
	/**
	 * Used to apply for the scholarships
	 * Variation needed for GUI
	 * @param inputStudent
	 * @param inputS
	 */
	public void apply(Student inputStudent, ArrayList<Scholarship> inputS) {
		System.out.println("Which scholarship would you like to apply to?");
		
		boolean notApplied = true;
		
		// loop doesn't quit while an invalid name is entered
		while (notApplied) {
			Scanner scholInput = new Scanner(System.in);
			String toApply = scholInput.nextLine();
			
			for (Scholarship s : inputS) {	// looking through all scholarships that matches the input...
				if (s.getName().equals(toApply)) {
					if (alreadyApplied(inputStudent, s)) {	// if the student has already applied for the scholarhship...
						System.out.println("You have already applied for this scholarship.");
					}
					else {
						s.addApplicant(inputStudent.getUsername());	// the scholarship adds the name of the user to its own list of applicants
						inputStudent.scholarshipsAppliedTo.add(s.getName());	// the student adds the name of the scholarship to their own list of scholarships applied to
						System.out.println("Congratulations! You have applied. Good luck.");
					}
					notApplied = false;	// quits the loop
				}
			}
			
			if (notApplied) {
				System.out.println("A scholarship with that name has not been found. Please enter the name of the scholarship you'd like to apply to.");
			}
		}
	}
	
	/**
	 * Checks if the student has already applied to the indicated scholarship (using the student's list of scholarships applied to)
	 * Will need this for the GUI
	 * @param inputStudent the student indicated
	 * @param inputS	allScholarships
	 * @return	true if they have already applied, false otherwise
	 */
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

	/**
	 * User to see if the student has already applied to the max amount of scholarships (4) per year
	 * Will need this for the GUI
	 * @param year		indicated year
	 * @param inputS	allScholarships
	 * @return			true if they have already applied to the max amount, false otherwise
	 */
	public boolean applyLimit(String term, int year, ArrayList<Scholarship> inputS) {
		ArrayList<Integer> yearsAppliedTo = new ArrayList<Integer>();

		for (String s : this.scholarshipsAppliedTo) {
			int yearToAdd = 0;
			Scholarship potentialSchol = findScholarship(s, inputS);
			if (potentialSchol.getSemester().equals("Winter")) {
				yearToAdd = potentialSchol.getYear() - 1;
			}
			else {
				yearToAdd = potentialSchol.getYear();
			}
			yearsAppliedTo.add(yearToAdd);
		}

		int pendingYear = year;
		boolean toReturn = false;
		
		if (term.equals("Fall") || term.equals("Fall and Winter")) {
			pendingYear = year;
		}
		else if (term.equals("Winter")) {
			pendingYear = year-1;
		}

		int applyInYear = 0;
		for (int i : yearsAppliedTo) {
			if (i == pendingYear) {
				applyInYear++;
			}
		}
		if (applyInYear == 4) {
			return toReturn = true;
		}
		return toReturn;
	}
	
	/**
	 * Method used to view the scholarships they applied for
	 * You'll need a variation of this for the GUI
	 */
	public void viewMyScholarships() {
		System.out.println("Student " + super.getUsername() + " " + super.getPassword() + " has applied to " + scholarshipsAppliedTo.size() + " scholarships.");
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
		String fileDirectory = upload.nextLine();
		
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
	 * Method to view the names of the files they've uploaded.
	 * Need variation for GUI.
	 */
	public void viewUploaded() {
		System.out.println("Files you've uploaded:");
		for (String a : studentFiles) {
			System.out.println(a);
		}
	}

	/**
	 * Used to view scholarships that have been granted to the student, and to accept them (if they want to)
	 * Helper functions: findScholarships(), alreadyAccepted(), yearAccepted(), checkAccepted()
	 * Need a variation for the GUI
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

			// if ENTER is entered, loop ends
			if (schol.equals("")) {
				break;
			}

			Scholarship selectedSchol = findScholarship(schol, inputS);

			// check the student accepted the indicated scholarship already
			if (alreadyAccepted(selectedSchol.getName())) {
				System.out.println("You've already accepted this scholarship.");
			}

			// check the student has already accepted a scholarship for that academic year
			else if (yearAccepted(selectedSchol.getSemester(), selectedSchol.getYear())) {
				System.out.println("You've already accepted a scholarship for this academic year.");
			}

			// accepts the scholarship
			else {
				addStudentAccepted(selectedSchol.getName());	// add to list of scholarships they've accepted
				addToTermYear(selectedSchol.getSemester(), selectedSchol.getYear());	// add the term + year of the scholarship they accepted to the list of terms + years they've accepted
				System.out.println("Congratulations! You've accepted the " + selectedSchol.getName() + ".");
			}
		}
	}

	/**
	 * Checks if the student had already accepted this scholarship
	 * Will need this for the GUI
	 * @param name	student indicated
	 * @return		true if they have, false otherwise
	 */
	public boolean alreadyAccepted (String name) {
		boolean toReturn = false;
		for (String a : getStudentAccepted()) {
			if (a.equals(name)) {
				toReturn = true;
			}
		}
		return toReturn;
	}

	/**
	 * Checks if the student had already accepted a scholarship from the indicated academic year
	 * Helper function: checkAccepted()
	 * Will need this for the GUI
	 * @param term	the term(s) of the scholarship they wanna accept
	 * @param year	the year of the scholarship they wanna accept
	 * @return		true if they have, false otherwise
	 */
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

	/**
	 * Checks if the student has already accepted scholarship from either of the terms (that make up the academic year) indicated
	 * Will need this for the GUI
	 * @param term1	the first term (Fall, usually)
	 * @param term2	the second term (Winter, usually)
	 * @return		true if they have, false otherwise
	 */
	public boolean checkAccepted (String term1, String term2) {
		boolean toReturn = false;
		for (String a : getStudentTermsYearAccept()) {
			if (a.equals(term1) || a.equals(term2)) {
				toReturn = true;
			}
		}
		return toReturn;
	}
}

