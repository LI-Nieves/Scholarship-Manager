package logic;

import java.util.*;
import java.io.*;

/*
 * This is our main class that starts everything.
 */

public class Start {
	
	private String userInfoDatabase = "databases/userInfo.txt";
	private String scholarshipDir = "databases/scholarships/scholarships.txt";
	private String scholarshipAppDir = "databases/scholarships/applicants.txt";
	private String studentsAppliedDir = "databases/students/applied.txt";
	private String studentsFilesDir = "databases/students/files.txt";
	private String studentsGrantedDir = "databases/students/granted.txt";

	private String username; 
	private String password; 
	private String role;
  
	private boolean loginSuccess = false;
	
	private ArrayList<Scholarship> allScholarships = new ArrayList<Scholarship>();
	private ArrayList<Student> allStudents = new ArrayList<Student>();
	
	// Getters
	public String getUsername() {
		return new String(this.username);
	}
	
	public String getPassword() {
		return new String(this.password);
	}
	
	public String getRole() {
		return new String(this.role);
	}
	
	public ArrayList<Scholarship> getAllScholarships(){ 
		return new ArrayList<Scholarship>(this.allScholarships);
	}

	// Setters
	public void setUsername(String inputUsername) {
		this.username = new String(inputUsername);
	}
	
	public void setPassword(String inputPassword) {
		this.password = new String(inputPassword);
	}
	
	public void setRole(String inputRole) {
		this.role = new String(inputRole);
	}
	
	public void addtoAllScholarships(Scholarship s) {
		this.allScholarships.add(s);
	}
	
	// FIRST METHOD EXECUTED; INITIATES LOGIN
	public void initiateLogin() {
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("Welcome to USask's Scholarship Center!\n Please type \"login\" to log in or \"register\" if you're a new user.");

		Scanner loginPrompt = new Scanner(System.in);
		String prompt1 = loginPrompt.nextLine();
		
		if (prompt1.contentEquals("register")) {
			register();
			loadStudents();
		}
		else if (prompt1.contentEquals("login")) {
			login();
		}
		else {
			System.out.println("Input invalid. Plese try again.");
			initiateLogin();
		}
		
	}
	
	// For registering a new user
	public void register() {
		System.out.println("Please register by typing your desired <username> <password> <Student, Coordinator>");
		
		// Opening up for printing (I/O)
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (userInfoDatabase, true));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + userInfoDatabase);
            System.exit(0);
        }
        
        // Registering them
		Scanner register = new Scanner(System.in);
		
		String username = register.next();
		String password = register.next();
		String role = register.next();
	
		
		if (!(role.contentEquals("Student")) && !(role.contentEquals("Coordinator"))) {
			System.out.println("Registration failed. Please select a valid role (Student, Staff, Coordinator). Role is case-sensitive.");
			outputStream.close();
			initiateLogin();
		}
		
		else {
			outputStream.print("\n" + username + " " + password + " " + role);
			System.out.println("Registration successful.");
	        outputStream.close();	
	        initiateLogin();
		}
		
		// error in case person is already in the system
	
	}
	
	// To log in existing user
	public void login() {
		System.out.println("Please log in by typing <username> <password> <one of: Student, Coordinator>.");
		
		// attempt to parse user input
		Scanner login = new Scanner(System.in);
		
		String pendingUsername = login.next();
		String pendingPassword = login.next();
		String pendingRole = login.next();
		
		// in case of error in reading from file
        Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(userInfoDatabase));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + userInfoDatabase);
            System.exit(0);
        }
        
        // parse file to see if user exists
        while (inputStream.hasNextLine()) {
			  try {
		        	String readUser = inputStream.next();
		        	String readPass = inputStream.next();
		        	String readRole = inputStream.next();
				  
				  if (readUser.contentEquals(pendingUsername) && readPass.contentEquals(pendingPassword) && readRole.contentEquals(pendingRole)) {
					  System.out.println("Login successful.");
					  loginSuccess = true;
					  break;
				  } 
			  } 
			  catch (NoSuchElementException e) {
				  System.out.println("Login error."); 
			  }
        }     
        
        if (loginSuccess) {
        	// Setting the instance variables!
        	setUsername(pendingUsername);
        	setPassword(pendingPassword);
        	setRole(pendingRole);
        	
        	if (getRole().contentEquals("Student")) {
				//Student newStudent = new Student(getUsername(),getPassword());
				for (Student a : allStudents) {
					if (username.equals(a.getUsername()) && password.equals(a.getPassword())) {
						studentCommands(a);
					}
				}
        		
        	}
        	else if (getRole().contentEquals("Coordinator")) {
        		Coordinator newCoordinator = new Coordinator(getUsername(),getPassword());
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
	
	/* Once a student logs in and a student object is created, these are the commands it can use */
	public void studentCommands(Student inputStudent) {
		// apply for scholarships - DONE
			// choose semester for which scholarships to apply for - DONE
			// see requirements of scholarships - DONE
			// see scholarship stats - DONE
			// submit prof's referral
		// upload transcripts, certificates, essays - DONE 
		// see scholarships i've applied to - IMPLEMENT AFTER APPLY FOR SCHOLARSHIPS
		// receive email notification to see what scholarships granted to me - HOW??
		// view all scholarships available - DONE
		
		System.out.println("***********************************************************************************************");
		System.out.println("Here are your options: \n <View all scholarships> \n <Apply for scholarships> "
				+ "\n <View scholarships I've applied to> \n <Upload transcripts, certificates, essays> \n <Log out>");
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
		
		else if (command.contentEquals("Log out")) {
			System.out.println("Thank you for using USask's Scholarship Center!");
			initiateLogin();
		}
		
		else {
			System.out.println("Invalid command.");
			studentCommands(inputStudent);
		}
	}
	
	/* One a coordinator logs in and a coordinator object is created, these are the commands it can use */
	public void coordinatorCommands(Coordinator inputCoordinator) {
		// edit scholarships - DONE (KINDA)
			// define requirements/restrictions for each scholarships - DONE
		// grant scholarships
			// view applications to scholarship
			// view each student's accept/reject rate
		// add scholarships - DONE
		// remove scholarships - DONE
		// view all scholarships available - DONE
		// view # of applicants to scholarship - DONE

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
			//inputCoordinator.editScholarship(allScholarships);
			System.out.println("To edit a scholarship, you will remove the scholarship then add it back in with new information.");
			removeScholarships();
			Scholarship newS = inputCoordinator.addScholarship();
			addtoAllScholarships(newS);
			storeScholarships();
			coordinatorCommands(inputCoordinator);
		}		
		
		// errors to consider: what if several scholarships have the same name?
		else if (command.contentEquals("Remove scholarships")) {
			removeScholarships();
			storeScholarships();
			coordinatorCommands(inputCoordinator);
		}	
		
		else if (command.contentEquals("Grant scholarships")) {
			inputCoordinator.grantScholarship(allScholarships);
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
	
	public void removeScholarships() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("What is the name of the scholarship you'd like to delete?"); 
		
		String findName = input.nextLine(); // this is the name of the scholarship to delete
		ArrayList<Integer> indicesToDelete = new ArrayList<Integer>(); // this is where we will store the indices of the scholarships with the indicated name
		int i = 0;
		
		for (Scholarship s : allScholarships) { // looking through all scholarships for the name...
			if (s.getName().equals(findName)) {
				indicesToDelete.add(i);
			}
			i++;
		}
		
		for (int a : indicesToDelete) { // looking through all scholarships, deleting the ones we found and making them null
			allScholarships.set(a, null);
			allScholarships.remove(a);
		}
	}

	public void loadScholarships() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(scholarshipDir));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + scholarshipDir);
            System.exit(0);
        }
        
        // parse file to see if user exists
        while (inputStream.hasNextLine()) {
			String name = inputStream.nextLine();
			//System.out.println("name" + name);
			String rew1 = inputStream.nextLine();
			int reward = Integer.parseInt(rew1);
			//System.out.println("rew" + reward);
			String sem = inputStream.nextLine();
			//System.out.println("sem" + sem);
			int year = inputStream.nextInt();
			//System.out.println("y" + year);
			int receive = inputStream.nextInt();
			//System.out.println("rec" + receive);
			double gpa = inputStream.nextDouble();
			inputStream.nextLine(); // this is a dummy line, because the reading isn't cooperating...
			//System.out.println("g" + gpa);
			String w = inputStream.nextLine();
			//System.out.println("w" + w);
			String dept = inputStream.nextLine();
			//System.out.println("dep" + dept);
			String fac = inputStream.nextLine();
			//System.out.println("fac" + fac);
			String uni = inputStream.nextLine();
			//System.out.println("uni" + uni);
			String deg = inputStream.nextLine();
			//System.out.println("deg" + deg);
			String crit = inputStream.nextLine();
			//System.out.println("crit" + crit);
			allScholarships.add(new Scholarship(name, reward, sem, year, receive, gpa, w, dept, fac, uni, deg, crit));
			if (inputStream.hasNextLine()) {
				inputStream.nextLine();
			}
        }     
		
/* 		// For debugging
        for (Scholarship s : allScholarships) {
			System.out.println(s.getName());
			System.out.println(s.getRewardAmount());
			System.out.println(s.getSemester());
			System.out.println(s.getYear());
			System.out.println(s.getReceive());
			System.out.println(s.getGPAreq());
			System.out.println(s.getWonTranscript());
			System.out.println(s.getDeptSpecific());
			System.out.println(s.getFacultySpecific());
			System.out.println(s.getUniSpecific());
			System.out.println(s.getDegreeSpecific());
			System.out.println(s.getExtraCriteria());
			System.out.println();
		} */
        
		inputStream.close();
		
	}

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

	public void loadScholarshipApplicants() {
		Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(scholarshipAppDir));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + scholarshipAppDir);
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
			System.out.println("size: " + s.getStudentApplied().size());
			for (String a : s.getStudentApplied()) {
				outputStream.println(a);
			}
			outputStream.println();
		}     
        
		outputStream.close();
		
	}

	// Duplicate function in Student
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
	/*
	 * public boolean returnToMenu() { boolean returnToMenu = false;
	 * 
	 * System.out.println("Return to main menu? <yes,no>");
	 * 
	 * Scanner input = new Scanner(System.in); String response = input.nextLine();
	 * 
	 * if (response.contentEquals("yes")) { returnToMenu = true; } else if
	 * (response.contentEquals("no")) {
	 * 
	 * }
	 * 
	 * return returnToMenu; }
	 */
	
	public static void main(String[] args) {
		Start s = new Start();
		s.loadScholarships();
		s.loadScholarshipApplicants();
		s.loadStudents();
		s.loadStudentFiles();
		s.loadStudentApplied();
/* 		Student a = new Student("u","p");
		s.allStudents.add(a);
		a.upload();
		s.storeStudentFiles(); */
		s.initiateLogin();
		//s.allScholarships.add(new Scholarship("s",1,"Fall",2000,1,1.0,"true","dept","fac","uni","deg","aa"));
		//s.storeScholarships();
		//s.loadScholarships();
	}

}
