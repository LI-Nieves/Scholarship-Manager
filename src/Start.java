package logic;

import java.util.*;
import java.io.*;

/*
 * This is our main class that starts everything.
 */

public class Start {
	
	private String userInfoDatabase = "UserInfoDatabase.txt";

	private String username; 
	private String password; 
	private String role;
  
	private boolean loginSuccess = false;
	
	private ArrayList<Scholarship> allScholarships = new ArrayList<Scholarship>();
	
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
        		Student newStudent = new Student(getUsername(),getPassword());
        		studentCommands(newStudent);
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
			studentCommands(inputStudent);
		}
		
		else if (command.contentEquals("View scholarships I've applied to")) {
			inputStudent.viewMyScholarships();
			studentCommands(inputStudent);
		}		
		
		else if (command.contentEquals("Upload transcripts, certificates, essays")) {
			inputStudent.upload();
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
		// edit scholarships
			// define requirements/restrictions for each scholarships
		// grant scholarships
			// view applications to scholarship
			// view each student's accept/reject rate
		// add scholarships - DONE
		// remove scholarships - DONE
		// view all scholarships available - DONE
			// view # of applicants to scholarship

		System.out.println("***********************************************************************************************");
		System.out.println("Here are your options: \n <View all scholarships> \n <Add scholarships> "
				+ "\n <Edit scholarships> \n <Remove scholarships> \n <Grant scholarships> \n <Log out>");
		System.out.println("Please type the option of your choosing EXACTLY as it is shown. It is case-sensitive.");
		
		Scanner newCommand = new Scanner(System.in);
		String command = newCommand.nextLine();
		
		if (command.contentEquals("View all scholarships")) {
			inputCoordinator.viewScholarships(allScholarships);
			coordinatorCommands(inputCoordinator);
		}
		
		else if (command.contentEquals("Add scholarships")) {
			Scholarship newS = inputCoordinator.addScholarship();
			addtoAllScholarships(newS);
			coordinatorCommands(inputCoordinator);
		}
		
		else if (command.contentEquals("Edit scholarships")) {
			inputCoordinator.editScholarship();
			coordinatorCommands(inputCoordinator);
		}		
		
		// errors to consider: what if several scholarships have the same name?
		else if (command.contentEquals("Remove scholarships")) {
			removeScholarships();
			coordinatorCommands(inputCoordinator);
		}	
		
		else if (command.contentEquals("Grant scholarships")) {
			inputCoordinator.grantScholarship();
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
		s.initiateLogin();
	}

}
