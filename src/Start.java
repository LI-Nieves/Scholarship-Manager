package backend;

import java.util.*;
import java.io.*;

/*
 * This class allows for login, registering, and loading from and updating to the databases.
 */

public class Start {
	
	private String userInfoDatabase		= "databases/userInfo.txt";
	private String scholarshipDir		= "databases/scholarships/scholarships.txt";
	private String scholarshipAppDir	= "databases/scholarships/applicants.txt";
	private String scholarshipGrantDir	= "databases/scholarships/granted.txt";
	private String studentsAppliedDir	= "databases/students/applied.txt";
	private String studentsFilesDir		= "databases/students/files.txt";
	private String studentsGrantedDir	= "databases/students/granted.txt";
	private String studentsAcceptedDir	= "databases/students/accepted.txt";
	private String studentsTermYearDir	= "databases/students/termYear.txt";
	
	private ArrayList<Scholarship> allScholarships = new ArrayList<Scholarship>();
	private ArrayList<Student> allStudents = new ArrayList<Student>();
	
	// Getters
	public ArrayList<Scholarship> getAllScholarships(){ 
		return new ArrayList<Scholarship>(this.allScholarships);
	}
	public ArrayList<Student> getAllStudents(){ 
		return new ArrayList<Student>(this.allStudents);
	}

	// Setters
	public void addtoAllScholarships(Scholarship s) {
		this.allScholarships.add(s);
	}
	public void setAllScholarships(ArrayList<Scholarship> change) {
		this.allScholarships=change;
	}
	
	/**
	 * Used to register a user.
	 * @param username	the desired username
	 * @param password	the desired password
	 * @param role		the desired role
	 * @return			success indicator
	 */
	public int registerGui(String username, String password, String role) {
		
		// Opening up for printing (I/O)
		PrintWriter outputStream = null;
        try { outputStream = new PrintWriter(new FileOutputStream (userInfoDatabase, true)); }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + userInfoDatabase);
            System.exit(0);
        }
     	
		// if the entered role is invalid
		if (!(role.contentEquals("Student")) && !(role.contentEquals("Coordinator"))) {
			outputStream.close();
			return 1;
		}
		// if the user already exists
		else if (userAlreadyExists(username)) {
			return 2;
		}
		// if everything is valid
		else {
			outputStream.print("\n" + username + " " + password + " " + role);
			if (role.equals("Student")) {
				loadStudents();
				loadStudentFiles();
				Student adding = new Student(username,password);
				adding.uploadGui(username,"");
		
				allStudents.add(adding); // if the newly registered person is a student, create an object for them
			
				storeStudentFiles();
			}
			outputStream.close();	
		}
		return 3;
	}

	/**
	 * Reads the database file to see if an indicated username already exists in the database
	 * @param name	the indicated username
	 * @return		true if it already exists, false otherwise
	 */
	public boolean userAlreadyExists(String name) {
		boolean toReturn = false;

		Scanner inputStream = null;
        try { inputStream = new Scanner(new File(userInfoDatabase)); }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + userInfoDatabase);
            System.exit(0);
		}
		
		while (inputStream.hasNextLine()) {
			String readUser = inputStream.next();
			inputStream.next(); inputStream.next();	// skipping over the password and role
			if (readUser.equals(name)) {
				toReturn = true;
			}
		}

		return toReturn;
	}
	
	/**
	 * Used to log a user in.
	 * @param user		their username
	 * @param Password	their password
	 * @param role		their role
	 * @return			success indicator
	 */
	public boolean loginGui(String user, String Password, String role) {
		
        Scanner inputStream = null;
        try { inputStream = new Scanner(new File(userInfoDatabase)); }
        catch(FileNotFoundException e ) {
            System.out.println("Error opening the file " + userInfoDatabase);
            System.exit(0);
        }
        
        while (inputStream.hasNextLine()) {
              try {
                    String readUser = inputStream.next();
                    String readPass = inputStream.next();
                    String readRole = inputStream.next();
                  
                  if (readUser.contentEquals(user) && readPass.contentEquals(Password) && readRole.contentEquals(role)) {
                      return true;
                  } 
              } 
              catch (NoSuchElementException e) { }
        }
		return false;
    }
	
	/**
	 * These are the loading and storing functions!!
	 * Essentially, storing all our information in mutable lists is not enough, because once the program stops running nothing is saved
	 * So everytime the program boots up, it loads information from a "database" using the load functions and into the mutable lists (to allow us to easily change info)
	 * and whenever something is changed, the information in the (appropriate) mutable lists are update in their respective databases
	 */

	/**
	 * This function loads the scholarship information
	 * Creates scholarship objects and adds them to allScholarships
	 */
	public void loadScholarships() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(scholarshipDir));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + scholarshipDir);
            System.exit(0);
        }
        
        // parse file to see get scholarship info
        while (inputStream.hasNextLine()) {
			String name = inputStream.nextLine();
			String rew1 = inputStream.nextLine();
			int reward = Integer.parseInt(rew1);
			String sem = inputStream.nextLine();
			int year = inputStream.nextInt();
			int receive = inputStream.nextInt();
			double gpa = inputStream.nextDouble();
			inputStream.nextLine(); // this is a dummy line, because the reading isn't cooperating...
			String w = inputStream.nextLine();
			String dept = inputStream.nextLine();
			String fac = inputStream.nextLine();
			String uni = inputStream.nextLine();
			String deg = inputStream.nextLine();
			String crit = inputStream.nextLine();
			// creation of Scholarship
			allScholarships.add(new Scholarship(name, reward, sem, year, receive, gpa, w, dept, fac, uni, deg, crit));
			if (inputStream.hasNextLine()) {
				inputStream.nextLine();
			}
        }     
		inputStream.close();
	}

	/**
	 * This function stores the scholarship information
	 * Each scholarship is read from allScholarships and stores onto the database
	 */
	public void storeScholarships() {
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (scholarshipDir, false));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + scholarshipDir);
            System.exit(0);
        }
		
		for (Scholarship s : allScholarships) {
			outputStream.println(s.getName());
			outputStream.println(s.getRewardAmount());
			outputStream.println(s.getSemester());
			outputStream.println(s.getYear());
			outputStream.println(s.getReceive());
			outputStream.println(s.getGPAreq());
			outputStream.println(s.getWonTranscript());
			outputStream.println(s.getDeptSpecific());
			outputStream.println(s.getFacultySpecific());
			outputStream.println(s.getUniSpecific());
			outputStream.println(s.getDegreeSpecific());
			outputStream.println(s.getExtraCriteria());
			outputStream.println();
		}     
		outputStream.close();
	}

	/**
	 * This function loads information of the applicants of each scholarship from the database
	 */
	public void loadScholarshipApplicants() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(scholarshipAppDir));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + scholarshipAppDir);
            System.exit(0);
        }
        
        // parse file for each applicant
        while (inputStream.hasNextLine()) {

			Scholarship s = findScholarship(inputStream.nextLine());

			while (inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				if (line.equals("")) {
					break;
				}
				else {
					s.addApplicant(line);
				}
			}
        }    
		inputStream.close();
	}

	/**
	 * This function stores information of the applicants of each scholarship to the database
	 */
	public void storeScholarshipApplicants() {
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (scholarshipAppDir, false));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + scholarshipAppDir);
            System.exit(0);
        }
		
		for (Scholarship s : allScholarships) {
			outputStream.println(s.getName());
			for (String a : s.getApplicants()) {
				outputStream.println(a);
			}
			outputStream.println();
		}     
		outputStream.close();
	}	

	/**
	 * This function loads information of the Student's accounts from the database
	 */
	public void loadStudents() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(userInfoDatabase));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + userInfoDatabase);
            System.exit(0);
        }
        
        // look for students
        while (inputStream.hasNextLine()) {

			String username = inputStream.next();
			String password = inputStream.next();
			String role = inputStream.next();

			if (role.equals("Student")) {
				allStudents.add(new Student(username,password));
			}
        }    
		inputStream.close();
	}

	/**
	 * This function loads information of the Student's files from the database
	 */
	public void loadStudentFiles() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(studentsFilesDir));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + studentsFilesDir);
            System.exit(0);
        }
        
        // parse file to see if user exists
        while (inputStream.hasNextLine()) {

			Student s = findStudent(inputStream.nextLine());

			while (inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				if (line.equals("")) {
					break;
				}
				else {
					s.addStudentFiles(line);
				}
			}
        }    
		inputStream.close();
	}

	/**
	 * This function stores information of the Student's files to the database
	 */
	public void storeStudentFiles() {
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (studentsFilesDir, false));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + studentsFilesDir);
            System.exit(0);
        }
		
		for (Student s : allStudents) {
			outputStream.println(s.getUsername());
			for (String a : s.getStudentFiles()) {
				outputStream.println(a);
			}
			outputStream.println();
		}     
		outputStream.close();
	}

	/**
	 * This function loads information of the scholarships the students have applied to from the database
	 */
	public void loadStudentApplied() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(studentsAppliedDir));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + studentsAppliedDir);
            System.exit(0);
        }
        
        // parse file to see if user exists
        while (inputStream.hasNextLine()) {

			Student s = findStudent(inputStream.nextLine());

			while (inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				if (line.equals("")) {
					break;
				}
				else {
					s.addStudentApplied(line);
				}
			}
        }    
		inputStream.close();
	}

	/**
	 * This function stores information of the scholarships the students have applied to to the database
	 */
	public void storeStudentApplied() {
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (studentsAppliedDir, false));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + studentsAppliedDir);
            System.exit(0);
        }
		
		for (Student s : allStudents) {
			outputStream.println(s.getUsername());
			for (String a : s.getStudentApplied()) {
				outputStream.println(a);
			}
			outputStream.println();
		}     
		outputStream.close();
	}

	/**
	 * This loads the information of students the scholarships have been granted to from the database
	 */
	public void loadScholarshipGrant() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(scholarshipGrantDir));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + scholarshipGrantDir);
            System.exit(0);
        }
        
        // parse file to see if user exists
        while (inputStream.hasNextLine()) {

			Scholarship s = findScholarship(inputStream.nextLine());

			while (inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				if (line.equals("")) {
					break;
				}
				else {
					s.addGranted(line);
				}
			}
        }    
		inputStream.close();
	}

		/**
	 * This stores the information of students the scholarships have been granted to to the database
	 */
	public void storeScholarshipGrant() {
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (scholarshipGrantDir, false));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + scholarshipGrantDir);
            System.exit(0);
        }
		
		for (Scholarship s : allScholarships) {
			outputStream.println(s.getName());
			for (String a : s.getGranted()) {
				outputStream.println(a);
			}
			outputStream.println();
		}     
		outputStream.close();
	}

	
	public void loadStudentGranted() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(studentsGrantedDir));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + studentsGrantedDir);
            System.exit(0);
        }
        
        // parse file to see if user exists
        while (inputStream.hasNextLine()) {

			Student s = findStudent(inputStream.nextLine());

			while (inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				if (line.equals("")) {
					break;
				}
				else {
					s.addStudentGranted(line);
				}
			}
        }    
        
		inputStream.close();
		
	}

	public void storeStudentGranted() {
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (studentsGrantedDir, false));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + studentsGrantedDir);
            System.exit(0);
        }
		
		for (Student s : allStudents) {
			outputStream.println(s.getUsername());
			for (String a : s.getStudentGranted()) {
				outputStream.println(a);
			}
			outputStream.println();
		}     
        
		outputStream.close();
		
	}

	public void loadStudentAccepted() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(studentsAcceptedDir));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + studentsAcceptedDir);
            System.exit(0);
        }
        
        // parse file to see if user exists
        while (inputStream.hasNextLine()) {

			Student s = findStudent(inputStream.nextLine());

			while (inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				if (line.equals("")) {
					break;
				}
				else {
					s.addStudentAccepted(line);
				}
			}
        }    
        
		inputStream.close();
		
	}

	public void storeStudentAccepted() {
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (studentsAcceptedDir, false));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + studentsAcceptedDir);
            System.exit(0);
        }
		
		for (Student s : allStudents) {
			outputStream.println(s.getUsername());
			for (String a : s.getStudentAccepted()) {
				outputStream.println(a);
			}
			outputStream.println();
		}     
        
		outputStream.close();
		
	}

	public void loadStudentTermYear() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(studentsTermYearDir));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + studentsTermYearDir);
            System.exit(0);
        }
        
        // parse file to see if user exists
        while (inputStream.hasNextLine()) {

			Student s = findStudent(inputStream.nextLine());

			while (inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				if (line.equals("")) {
					break;
				}
				else {
					s.addStudentTermYear(line);
				}
			}
        }    
        
		inputStream.close();
		
	}

	public void storeStudentTermYear() {
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (studentsTermYearDir, false));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + studentsTermYearDir);
            System.exit(0);
        }
		
		for (Student s : allStudents) {
			outputStream.println(s.getUsername());
			for (String a : s.getStudentTermsYearAccept()) {
				outputStream.println(a);
			}
			outputStream.println();
		}     
        
		outputStream.close();
		
	}

	// Similar function in Student
	public Scholarship findScholarship(String name) {
		Scholarship toReturn = new Scholarship();
		for (Scholarship s : allScholarships) {
			if (s.getName().equals(name)) {
				toReturn = s;
			}
		}
		return toReturn;
	}
	
	public Student findStudent(String name) {
		Student toReturn = new Student();
		for (Student s : allStudents) {
			if (s.getUsername().equals(name)) {
				toReturn = s;
			}
		}
		return toReturn;
	}
	public boolean checkStudentGui(String name) {
		Student toReturn = new Student();
		for (Student s : allStudents) {
			if (s.getUsername().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
}
