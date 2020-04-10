

import java.util.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class StartGui {
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

	// Setters
	public void addtoAllScholarships(Scholarship s) {
		this.allScholarships.add(s);
	}
	
	/**
	 * This is the first method executed by the main() (other than the loading and storing mechanisms).
	 * This prompts the user to log in or register.
	 * (Will probably not be used by GUI)
	 */
	public void initiateLogin() {
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("Welcome to USask's Scholarship Center!\n Please type \"login\" to log in or \"register\" if you're a new user.");

		Scanner loginPrompt = new Scanner(System.in);
		String prompt1 = loginPrompt.nextLine();
		
		if (prompt1.contentEquals("register")) {
			register();
			
		}
		else if (prompt1.contentEquals("login")) {
			login();
		}
		else {
			System.out.println("Input invalid. Plese try again.");
			initiateLogin();
		}
		
	}
	
	/**
	 * This is the function called if the user decides to register themselves.
	 * Helper function: userAlreadyExists()
	 * (Will need a variation for the GUI)
	 */
	public void register() {
		System.out.println("Please register by typing your desired <username> <password> <Student, Coordinator>. To cancel, type \"Please cancel registration\".");
		
		// Opening up for printing (I/O)
		PrintWriter outputStream = null;
        try { outputStream = new PrintWriter(new FileOutputStream (userInfoDatabase, true)); }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + userInfoDatabase);
            System.exit(0);
        }
        
        // Registering them (for the GUI, use text boxes)
		Scanner register = new Scanner(System.in);
		String username = register.next();
		String password = register.next();
		String role = register.next();
	
		// if the entered role is invalid (for the GUI, this might not be needed - use tick boxes)
		if (!(role.contentEquals("Student")) && !(role.contentEquals("Coordinator"))) {
			System.out.println("Registration failed. Please select a valid role (Student, Staff, Coordinator). Role is case-sensitive.");
			outputStream.close();
		}
		else if (userAlreadyExists(username)) {
			System.out.println("This username already exists in the server. Please try again.");
			register();
		}
		// if everything is valid (minor variation needed for GUI)
		else {
			outputStream.print("\n" + username + " " + password + " " + role);
			if (role.equals("Student")) {
				allStudents.add(new Student(username,password)); // if the newly registered person is a student, create an object for them
			}
			System.out.println("Registration successful.");
	        outputStream.close();	
		}

		initiateLogin();
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
	 * This is the function called when the user decides to log in.
	 * (Will need a variation for the GUI)
	 */
	public void login() {
		System.out.println("Please log in by typing <username> <password> <one of: Student, Coordinator>.");
		
		// attempt to parse user input (for the GUI, use text boxes)
		Scanner login = new Scanner(System.in);
		String pendingUsername = login.next();
		String pendingPassword = login.next();
		String pendingRole = login.next();
		
		// in case of error in reading from file
        Scanner inputStream = null;
        try { inputStream = new Scanner(new File(userInfoDatabase)); }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + userInfoDatabase);
            System.exit(0);
        }
		
		boolean loginSuccess = false;
        // parse file to see if user exists (will need this for the GUI)
        while (inputStream.hasNextLine()) {
			  try {
		        	String readUser = inputStream.next();
		        	String readPass = inputStream.next();
		        	String readRole = inputStream.next();
				  
				  if (readUser.contentEquals(pendingUsername) && readPass.contentEquals(pendingPassword) && readRole.contentEquals(pendingRole)) { // if username, password, and role match
					  System.out.println("Login successful.");
					  loginSuccess = true;
					  break;
				  } 
			  } 
			  catch (NoSuchElementException e) {
				  System.out.println("Login error."); // should print this error on the GUI!
			  }
        }     
		
		// if the login was successful (need variation for GUI)
        if (loginSuccess) {
			String username = pendingUsername;
			String password = pendingPassword;
			String role = pendingRole;
        	
        	if (role.contentEquals("Student")) {	// if the user is a student...
				for (Student a : allStudents) {	// find the student from the known list of students, then use their object to do more actions
					if (username.equals(a.getUsername()) && password.equals(a.getPassword())) {
						studentCommands(a);
					}
				}
        		
        	}
        	else if (role.contentEquals("Coordinator")) {	// if the user is a coordinator...
        		Coordinator newCoordinator = new Coordinator(username,password);	// a new coordinator object is made - this is because coordinators don't retain profiles
        		coordinatorCommands(newCoordinator);
        	}
        	else {
        		System.out.println("User error.");
        		initiateLogin();
        	}
        }
        else {
        	System.out.println("Login failed.");
        	initiateLogin();
        }
        
        inputStream.close();
        
		// try-catch: if parsing fails, spit out error
	}
	
	/**
	 * Once a student logs in, these are the actions they can do
	 * (You won't need this method for the GUI - just use buttons or something.
	 * 	However, note ALL the functions that are called for each actions - all are important.) 
	 * */
	public void studentCommands(Student inputStudent) {
		System.out.println("***********************************************************************************************");
		System.out.println("Here are your options: \n <View all scholarships> \n <Apply for scholarships> "
				+ "\n <View scholarships I've applied to> \n <Upload transcripts, certificates, essays> " +
				"\n <View my uploaded files> \n <View and accept granted scholarships> \n <Log out>");
		System.out.println("Please type the option of your choosing EXACTLY as it is shown. It is case-sensitive.");
		
		Scanner newCommand = new Scanner(System.in);
		String command = newCommand.nextLine();
		
		if (command.contentEquals("View all scholarships")) {
			inputStudent.viewScholarships(allScholarships);
			studentCommands(inputStudent);
		}
		
		else if (command.contentEquals("Apply for scholarships")) {
			inputStudent.chooseTerm(inputStudent, getAllScholarships());
			storeScholarshipApplicants();
			storeStudentApplied();
			studentCommands(inputStudent);
		}
		
		else if (command.contentEquals("View scholarships I've applied to")) {
			inputStudent.viewMyScholarships();
			studentCommands(inputStudent);
		}		
		
		else if (command.contentEquals("Upload transcripts, certificates, essays")) {
			inputStudent.upload();
			storeStudentFiles();
			studentCommands(inputStudent);
		}
		
		else if (command.contentEquals("View my uploaded files")) {
			inputStudent.viewUploaded();
			studentCommands(inputStudent);
		}

		else if (command.contentEquals("View and accept granted scholarships")) {
			inputStudent.viewGranted(allScholarships);
			storeStudentAccepted();
			storeStudentTermYear();
			studentCommands(inputStudent);
		}
		
		else if (command.contentEquals("Log out")) {
			System.out.println("Thank you for using USask's Scholarship Center!");
			initiateLogin();
		}
		
		else {
			System.out.println("Invalid command.");
			studentCommands(inputStudent);
		}
	}
	
	/**
	 * Once a coordinator logs in, these are the actions they can do
	 * (You won't need this method for the GUI - just use buttons or something.
	 * 	However, note ALL the functions that are called for each actions - all are important.) 
	 * */
	public void coordinatorCommands(Coordinator inputCoordinator) {
		System.out.println("***********************************************************************************************");
		System.out.println("Here are your options: \n <View all scholarships> \n <View statistics for all scholarships> \n <Add scholarships> "
				+ "\n <Edit scholarships> \n <Remove scholarships> \n <Grant scholarships> \n <View student profiles> \n <Log out>");
		System.out.println("Please type the option of your choosing EXACTLY as it is shown. It is case-sensitive.");
		
		Scanner newCommand = new Scanner(System.in);
		String command = newCommand.nextLine();
		
		if (command.contentEquals("View all scholarships")) {
			inputCoordinator.viewScholarships(allScholarships);
			coordinatorCommands(inputCoordinator);
		}

		else if (command.contentEquals("View statistics for all scholarships")) {
			inputCoordinator.viewStatistics(allScholarships);
			coordinatorCommands(inputCoordinator);
		}
		
		else if (command.contentEquals("Add scholarships")) {
			Scholarship newS = inputCoordinator.addScholarship();
			addtoAllScholarships(newS);
			storeScholarships();
			coordinatorCommands(inputCoordinator);
		}
		
		else if (command.contentEquals("Edit scholarships")) {
			// Somewhat questionable implementation - I noticed that editing a scholarship would take a 
			// ridiculous amount of time, so instead it just allows them to remove the indicated one and
			// create a new one. You can do this differently in the GUI if you want.
			System.out.println("To edit a scholarship, you will remove the scholarship then add it back in with new information.");
			inputCoordinator.removeScholarships(allScholarships);
			Scholarship newS = inputCoordinator.addScholarship();
			addtoAllScholarships(newS);
			storeScholarships();
			coordinatorCommands(inputCoordinator);
		}		
		
		// errors to consider: what if several scholarships have the same name?
		else if (command.contentEquals("Remove scholarships")) {
			this.allScholarships = inputCoordinator.removeScholarships(allScholarships);
			storeScholarships();
			coordinatorCommands(inputCoordinator);
		}	
		
		else if (command.contentEquals("Grant scholarships")) {
			inputCoordinator.grantScholarship(allScholarships, allStudents);
			storeScholarshipGrant();
			storeStudentGranted();
			coordinatorCommands(inputCoordinator);
		}

		else if (command.contentEquals("View student profiles")) {
			inputCoordinator.viewProfiles(allStudents);
			coordinatorCommands(inputCoordinator);
		}
		
		else if (command.contentEquals("Log out")) {
			System.out.println("Thank you for using USask's Scholarship Center!");
			initiateLogin();
		}
		
		else {
			System.out.println("Invalid command.");
			coordinatorCommands(inputCoordinator);
		}
	}

	/**
	 * These are the loading and storing functions!! They're very important and you WILL need them for the GUI
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
		
/* 		// For debugging
        for (Scholarship s : allScholarships) {
			System.out.println(s.getName()); System.out.println(s.getRewardAmount()); System.out.println(s.getSemester()); System.out.println(s.getYear());
			System.out.println(s.getReceive()); System.out.println(s.getGPAreq()); System.out.println(s.getWonTranscript()); System.out.println(s.getDeptSpecific());
			System.out.println(s.getFacultySpecific()); System.out.println(s.getUniSpecific()); System.out.println(s.getDegreeSpecific()); System.out.println(s.getExtraCriteria());
			System.out.println();
		} */
        
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
		
		// For debugging
/*         for (Scholarship s : allScholarships) {
			for (String a : s.getApplicants()) {
				System.out.println(s.getName() + " " + a);
			}
			System.out.println();
		} */
        
		inputStream.close();
		
	}

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
		
		// For debugging
/*         for (Student s : allStudents) {
			System.out.println("username: " + s.getUsername());
			System.out.println("password: " + s.getPassword());
			System.out.println();
		} */
        
		inputStream.close();
		
	}

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
		
		// For debugging
/*         for (Student s : allStudents) {
			for (String a : s.getStudentFiles()) {
				System.out.println(s.getUsername() + " " + a);
			}
			System.out.println();
		} */
        
		inputStream.close();
		
	}

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
		
		// For debugging
/*         for (Student s : allStudents) {
			for (String a : s.getStudentApplied()) {
				System.out.println(s.getUsername() + " " + a);
			}
			System.out.println();
		} */
        
		inputStream.close();
		
	}

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
		
		// For debugging
/*         for (Scholarship s : allScholarships) {
			for (String a : s.getGranted()) {
				System.out.println(s.getName() + " " + a);
			}
			System.out.println();
		} */
        
		inputStream.close();
		
	}

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
		
		// For debugging
/*         for (Student s : allStudents) {
			for (String a : s.getStudentApplied()) {
				System.out.println(s.getUsername() + " " + a);
			}
			System.out.println();
		} */
        
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
		
		// For debugging
/*         for (Student s : allStudents) {
			for (String a : s.getStudentApplied()) {
				System.out.println(s.getUsername() + " " + a);
			}
			System.out.println();
		} */
        
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
		
		// For debugging
/*         for (Student s : allStudents) {
			for (String a : s.getStudentApplied()) {
				System.out.println(s.getUsername() + " " + a);
			}
			System.out.println();
		} */
        
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
    
    public boolean loginGui(String user, String Password, String role) {
		
        Scanner inputStream =null;
        try {
            inputStream = new Scanner(new File(userInfoDatabase));
            //File a = new File("");
            //System.out.println(this.getClass().getResource("UserInfoDatabase.txt"));
            //inputStream = new Scanner(a);
    
            //URL url = getClass().getResource(userInfoDatabase);
            //File a = new File(url.toURI());
            //inputStream = new Scanner(a);
    
            //System.out.println(file.getAbsoloutePath);
            //File check = new File(userInfoDatabase);
        }
        catch(FileNotFoundException e ) {
            System.out.println("Error opening the file " + userInfoDatabase);
            //System.out.println(inputStream);
            //System.out.println(a.getAbsoloutePath);
            System.exit(0);
        }
    /* 		catch(URISyntaxException e) {
            System.out.println("URI EXCEPTION");
            System.exit(0);
        } */
        
        
         while (inputStream.hasNextLine()) {
              try {
                    String readUser = inputStream.next();
                    String readPass = inputStream.next();
                    String readRole = inputStream.next();
                  
                  if (readUser.contentEquals(user) && readPass.contentEquals(Password) && readRole.contentEquals(role)) {
                      System.out.println("Login successful.");
                      return true;
                  } 
              } 
              catch (NoSuchElementException e) {
                  System.out.println("Login error."); 
              }
       }
         return false;
        
        
    }
    public int registerGui(String username, String password, String role) {
		//System.out.println("Please register by typing your desired <username> <password> <Student, Coordinator>. To cancel, type \"Please cancel registration\".");
		
		// Opening up for printing (I/O)
		PrintWriter outputStream = null;
        try { outputStream = new PrintWriter(new FileOutputStream (userInfoDatabase, true)); }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + userInfoDatabase);
            System.exit(0);
        }
        
        // Registering them (for the GUI, use text boxes)
		//Scanner register = new Scanner(System.in);
		//String username = register.next();
		//String password = register.next();
		//String role = register.next();
	
		// if the entered role is invalid (for the GUI, this might not be needed - use tick boxes)
		if (!(role.contentEquals("Student")) && !(role.contentEquals("Coordinator"))) {
			System.out.println("Registration failed. Please select a valid role (Student,Coordinator). Role is case-sensitive.");
			outputStream.close();
			return 1;
		}
		else if (userAlreadyExists(username)) {
			System.out.println("This username already exists in the server. Please try again.");
			return 2;
			//register();
		}
		// if everything is valid (minor variation needed for GUI)
		else {
			outputStream.print("\n" + username + " " + password + " " + role);
			if (role.equals("Student")) {
				allStudents.add(new Student(username,password)); // if the newly registered person is a student, create an object for them
			}
			System.out.println("Registration successful.");
	        outputStream.close();	
		}
		return 3;

		//initiateLogin();
	}
}