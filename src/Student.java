// DONE

package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * This class is for all the functions a student can execute.
 */

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
	public ArrayList<String> getStudentFiles() {
		ArrayList<String> dummy = new ArrayList<String>();
		for (String s : this.studentFiles) {
			dummy.add(s);
		}
		return dummy;
	}
	public ArrayList<String> getStudentApplied() {
		ArrayList<String> dummy = new ArrayList<String>();
		for (String s : this.scholarshipsAppliedTo) {
			dummy.add(s);
		}
		return dummy;
	}
	public ArrayList<String> getStudentGranted() {
		ArrayList<String> dummy = new ArrayList<String>();
		for (String s : this.scholarshipsGranted) {
			dummy.add(s);
		}
		return dummy;
	}
	public ArrayList<String> getStudentAccepted() {
		ArrayList<String> dummy = new ArrayList<String>();
		for (String s : this.scholarshipsAccepted) {
			dummy.add(s);
		}
		return dummy;
	}
	public ArrayList<String> getStudentTermsYearAccept() {
		ArrayList<String> dummy = new ArrayList<String>();
		for (String s : this.termsAndYearAccepted) {
			dummy.add(s);
		}
		return dummy;
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
	 * @param inputStudent	The student that's applying
	 * @param inputS		allScholarships
	 * @param termInt		The selected term
	 * @param year			The selected year
	 * @return				The error message
	 */
	public String chooseTermGui(Student inputStudent, ArrayList<Scholarship> inputS, int termInt, int year) {
		
		String term = "";

		if (termInt == 0) { term = "Fall"; }
		else if (termInt == 1) { term = "Winter"; }
		else if (termInt == 2) { term = "Fall and Winter"; }	

		// this is an ArrayList of all scholarships in the term + year the user indicated
		ArrayList<Scholarship> potentialScholar = new ArrayList<Scholarship>();
		for (Scholarship s : inputS) {
			if (term.contentEquals(s.getSemester()) && (year == s.getYear())) {
			potentialScholar.add(s);
			}
		}
	
		// if there are no scholarships of that term + year indicated...
		if (potentialScholar.size() <= 0) {
			String ErrorMessage = "<html><p>Sorry, there are no scholarships for that term and/or year. Please try again.</p></html>";
			return ErrorMessage;
		
		}
		else if (applyLimit(termInt,year, inputS)) { // checking if the student has already applied to the maximum # of scholarships for that year
			String ErrorMessage = "<html><p>You have already applied to the maximum of FOUR (4) scholarships for " + year + "." + "</p></html>";
			return ErrorMessage; 
		}

		// if there are scholarships of that term + year indicated...
		String ErrorMessage = "found";
		return ErrorMessage;
	}
	
	/**
	 * This is the function that allows returns an ArrayList of scholarships of the indicated term and year
	 * @param inputStudent	the student that's applying
	 * @param inputS		allScholarships
	 * @param termInt		the indicated term
	 * @param year			the indicated year
	 * @return				the ArrayList of all scholarships of the indicated term and year
	 */
	public ArrayList<Scholarship> getPotentialScholar(Student inputStudent, ArrayList<Scholarship> inputS, int termInt, int year) {
	
		String term = "";
		if (termInt == 0) { term = "Fall"; }
		else if (termInt == 1) { term = "Winter"; }
		else if (termInt == 2) { term = "Fall and Winter"; }		
	
		// this is an ArrayList of all scholarships in the term + year the user indicated
		ArrayList<Scholarship> potentialScholar = new ArrayList<Scholarship>();
		for (Scholarship s : inputS) {
			if (term.contentEquals(s.getSemester()) && (year == s.getYear())) {
			potentialScholar.add(s);
			}
		}
	
		return potentialScholar;
	}
	
	/**
	 * This function is used to apply for scholarships.
	 * @param inputStudent
	 * @param allScholar
	 * @param inputS
	 * @param toApply
	 * @param term
	 * @param year
	 * @return
	 */
	public String applyGui(Student inputStudent, ArrayList<Scholarship> allScholar, ArrayList<Scholarship> inputS, String toApply, int term, int year) {
		
		boolean notApplied = true;
		String ErrorMessage = "";

			for (Scholarship s : inputS) {	// looking through all scholarships that matches the input...
				if (s.getName().equals(toApply)) {
					// if the student has already applied for the scholarhship...
					if (alreadyApplied(inputStudent, s)) {	
						ErrorMessage = "<html><p>You have already applied for this scholarship.</p></html>";
						return ErrorMessage;
					}
					// if the student has already applied to the max amount of scholarships for an academic year
					else if (applyLimit(term, year, allScholar)) {
						ErrorMessage = "<html><p>You have already applied to the maximum of FOUR (4) scholarships for " + year + "." + "</p></html>";
						return ErrorMessage;
					}
					// otherwise, they can apply for the scholarship
					else {
						s.addApplicant(inputStudent.getUsername());				// the scholarship adds the name of the user to its own list of applicants
						inputStudent.scholarshipsAppliedTo.add(s.getName());	// the student adds the name of the scholarship to their own list of scholarships applied to
						ErrorMessage = "<html><p>Congratulations! You have applied. Good luck.</p></html>";
						return ErrorMessage;
					}
					
				}
			}
			if (notApplied) {
				ErrorMessage = "<html><p>A scholarship with that name has not been found. Please enter the name of the scholarship you'd like to apply to.</p></html>";
				return ErrorMessage;
			}
			
		return ErrorMessage;
		
	}
	
	/**
	 * Checks if the student has already applied to the indicated scholarship (using the student's list of scholarships applied to)

	 * @param inputStudent	the student indicated
	 * @param inputS		allScholarships
	 * @return				true if they have already applied, false otherwise
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
	 * @param term		indicated term
	 * @param year		indicated year
	 * @param inputS	allScholarships
	 * @return			true if they have already applied to the max amount, false otherwise
	 */
	public boolean applyLimit(int term, int year, ArrayList<Scholarship> inputS) {
		
		// first checks to see which academic years the student has applied to
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

		// then checks the year of the desired scholarship against that list
		int pendingYear = year;
		boolean toReturn = false;
		
		if (term == 0 || term == 2) {
			pendingYear = year;
		}
		else if (term == 1) {
			pendingYear = year-1;
		}

		int applyInYear = 0;
		for (int i : yearsAppliedTo) {
			if (i == pendingYear) {
				applyInYear++;
			}
		}
		if (applyInYear >= 4) {
			return toReturn = true;
		}
		return toReturn;
	}
	
	/**
	 * Method used to view the scholarships they applied for
	 */
	public String viewMyScholarshipsGui() {
		String finals = "You have applied to the following Scholarships: \n\n";
		for (String s : scholarshipsAppliedTo) {
			String temp =" * " + s + "\n\n";
			finals = finals.concat(temp);
		}
		return finals;
	}
	
	/**
	 * Method used to upload transcripts, resumes, etc.
	 * @param fileName	the desired name of the file
	 * @param portfolio	the portfolio of the student
	 */	
	public void uploadGui(String fileName, String portfolio) {;				
		// add this file to the student's list of files they've uploaded
		studentFiles.clear();
		studentFiles.add(fileName + ".txt");
		
		// path to place file
        String completeUploadDir = "uploadedFiles/" + fileName + ".txt";
        
		// Opening up for printing (I/O)
		PrintWriter outputStream = null;
        try { outputStream = new PrintWriter(new FileOutputStream (completeUploadDir,false)); } //for append }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + completeUploadDir);
            System.exit(0);
        }
		
        // parsing file, reading each line from source and writing it to destination 
		try { outputStream.print(portfolio); } 
		catch (NoSuchElementException e) {
			System.out.println("File read error."); 
		}
		
		outputStream.close();
	}

	/**
	 * Method to view the names of the files they've uploaded.
	 */	
	public String viewUploadedGui() {
		String returnThis = "";
		for(String b: studentFiles) {
			returnThis = returnThis + b;
		}
		return returnThis;
	}

	/**
	 * Used to view scholarships that have been granted to the student, and to accept them (if they want to)
	 * @param inputS	allScholarships
	 * @return			the String that has the name of the scholarships
	 */
	public String viewGrantedGui(ArrayList<Scholarship> inputS) {
		String granted = "";
		for (String a : getStudentGranted()) {
			granted = granted + a + " (" + findScholarship(a, inputS).getSemester() + " " + findScholarship(a, inputS).getYear() + ")\n\n";
		}
		return granted;
	}
	
	/**
	 * Either 1) tells the student they've already accepted the scholarship, 2) tell them they already have
	 * accepted a scholarship for a the desired scholarship's academic year, 3) allows the student to accept
	 * the scholarship
	 * @param schol		the desired scholarship
	 * @param inputS	allScholarships
	 * @return
	 */
	public String checkGrantedGui(String schol, ArrayList<Scholarship> inputS) {
		Scholarship selectedSchol = findScholarship(schol, inputS);

		// if the student has already accepted the scholarship
		if (alreadyAccepted(selectedSchol.getName())) {
			return "You've already accepted this scholarship.";
		}

		// check the student has already accepted a scholarship for that academic year
		else if (yearAccepted(selectedSchol.getSemester(), selectedSchol.getYear())) {
			return "You've already accepted a scholarship for this academic year.";
		}

		// accepts the scholarship
		else {
			addStudentAccepted(selectedSchol.getName());							// add to list of scholarships they've accepted
			addToTermYear(selectedSchol.getSemester(), selectedSchol.getYear());	// add the term + year of the scholarship they accepted to the list of terms + years they've accepted
			return "Congratulations! You've accepted the " + selectedSchol.getName() + ".";
		}
		
	}

	/**
	 * Checks if the student had already accepted this scholarship
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
	 * @param term	the term(s) of the scholarship they wanna accept
	 * @param year	the year of the scholarship they wanna accept
	 * @return		true if they have, false otherwise
	 */
	public boolean yearAccepted (String term, int year) {
		boolean toReturn = false;
		if (term.equals("Fall and Winter") || term.equals("Fall") ) {
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
 
	/**
	 * Opens an indicated file name
	 * @param	fName
	 * @return	the contents of the file
	 */
	public String openFileGui(String fName) {
		String returnThis = "";
		String fileDir = "uploadedFiles/" + fName;

		Scanner inputStream = null;
		try { inputStream = new Scanner(new File(fileDir)); }
		catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + fileDir);
			System.exit(0);
		}

		// this prints the lines of the file out
		while(inputStream.hasNextLine()) {
			returnThis = returnThis + inputStream.nextLine() + "\n";
		}
		return returnThis;
	}
}

