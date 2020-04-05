package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Student extends User {

	//private int NumberOfStudents=1000;
	//private String[] username = new String[getNumberOfStudents()];
	//private String[] password = new String[getNumberOfStudents()];
	//private int current_limit=0;
	
	private String username;
	private String password;
	
	private String fileDirectory;
	private String uploadDir = "uploadedFiles\\";
	private String fileName;
	private ArrayList<String> studentFiles = new ArrayList<String>();
	
	// We can put all the data in a text
	
	// Constructor
	public Student(String inputUsername, String inputPassword) {
		setUsername(inputUsername);
		setPassword(inputPassword);
	}
	
	// GETTERS
	public String getFileDir() {
		return new String(this.fileDirectory);
	}
	public String getFileName() {
		return new String(this.fileName);
	}
	public String getUploadDir() {
		return new String (this.uploadDir);
	}
	
	
	// SETTERS
	public void setFileDir(String inputFileDir) {
		this.fileDirectory = new String(inputFileDir);
	}	
	public void setFileName(String inputFileName) {
		this.fileName = new String(inputFileName);
	}
	
	/* Method used to apply to a scholarship (incomplete) */
	public void chooseTerm(Student inputStudent, ArrayList<Scholarship> inputS) {
		// Asking for desired term and year
		System.out.println("Which term and year would you like to see the scholarships of? Possible terms: Fall, Winter, Fall and Winter");
		Scanner termYearInput = new Scanner(System.in);
		String term = termYearInput.next();
		int year = termYearInput.nextInt();
		
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
		
		apply(inputStudent, potentialScholar);
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
						s.addApplicant(inputStudent);
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
		for (Student a : inputS.getApplicants()) {
			if (inputStudent.equals(a)) {
				applied = true;
			}
		}
		return applied;
	}
	
	/* Method used to view the scholarships they applied for (incomplete) */
	public void viewMyScholarships() {
		
	}
	
	/* 
	 * Method used to upload transcripts, resumes, etc.
	 * Basically, you give it the directory (including the file name) of the file you'd like to upload
	 * "uploading" is just the function parsing the file and copying the exact same thing in another 
	 * file that's created in a designated folder accessible to the program
	 */
	public void upload() {
		System.out.println("Please type in the directory that your file is in.");
		
		// taking in where the file is
		Scanner upload = new Scanner(System.in);
		setFileDir(upload.nextLine());
		
		System.out.println("What would you like to call the uploaded file?");
		
		// taking in desired file name
		Scanner inputName = new Scanner(System.in);
		setFileName(upload.nextLine());
		
		
		// add this file to the student's list of files they've uploaded
		studentFiles.add(getFileName() + ".txt");
		
		// trying to open file to read
        Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(getFileDir()));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + getFileDir());
            System.exit(0);
        }
        
        String completeUploadDir = getUploadDir() + getFileName() + ".txt";
        
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
	
	/*
	 * public void increaseCurrentLimit() { this.current_limit+=1;
	 * 
	 * } public int getCurrentLimit() {
	 * 
	 * return current_limit; }
	 * 
	 * public int getNumberOfStudents() { return NumberOfStudents;
	 * 
	 * }
	 * 
	 * public void setNumberOfStudents(int Students) { this.NumberOfStudents =
	 * Students;
	 * 
	 * }
	 * 
	 * public String[] getUsername() { return username; }
	 * 
	 * public String[] getPasswords() { return password; }
	 */
	
	/*
	 * public boolean studentLogin(String user, String pass) { int starting=0;
	 * String[] users = getUsername(); String[] passwords = getPasswords();
	 * while(starting<=getCurrentLimit()) { String lookingUser = users[starting];
	 * String lookingPass = passwords[starting]; if(lookingUser.equals(user) &&
	 * lookingPass.equals(pass)) { return true; }
	 * 
	 * starting+=1;
	 * 
	 * } return false;
	 * 
	 * }
	 */
	
	// Already extends a class with these methods
	/*
	 * public String getUsername() { return new String(this.username); }
	 * 
	 * public String getPassword() { return new String(this.password); }
	 * 
	 * public void setUsername(String inputUsername) { this.username = new
	 * String(inputUsername); }
	 * 
	 * public void setPassword(String inputPassword) { this.password = new
	 * String(inputPassword); }
	 */
}

// REFERENCES:
/**
 * https://www.w3schools.com/java/java_encapsulation.asp
 * https://www.javatpoint.com/how-to-return-an-array-in-java
 * 
 * 
 * 
 * 
 */

