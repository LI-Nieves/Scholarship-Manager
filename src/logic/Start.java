package logic;

import java.util.*;
import java.io.*;

/*
 * This will be our main class that starts everything.
 */

public class Start {
	
	String database = "Database.txt";
	/*
	 * String username; String password; String role;
	 */
	
	
	public void register() {
		System.out.println("Please register by typing your desired <username> <password> <Student, Staff, Coordinator>");
		
		// Opening up for printing (I/O)
		PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream (database, true));  //for append
		}
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + database);
            System.exit(0);
        }
        
        // Registering them
		Scanner register = new Scanner(System.in);
		
		String username = register.next();
		String password = register.next();
		String role = register.next();
		
		if (!(role.contentEquals("Student")) && !(role.contentEquals("Staff")) && !(role.contentEquals("Coordinator"))) {
			System.out.println("Registration failed. Please select a valid role (Student, Staff, Coordinator). Role is case-sensitive.");
			start();
		}
		
		// error in case person is already in the system
		
		outputStream.print("\n" + username + " " + password + " " + role);
        
        outputStream.close();	
        
        start();
	}
	
	public void login() {
		System.out.println("Please log in by typing <username> <password> <one of: Student, Staff, Coordinator>");
		
		// attempt to parse user input
		Scanner login = new Scanner(System.in);
		
		String username = login.next();
		String password = login.next();
		String role = login.next();
		
		
        Scanner inputStream = null;
        try {
           inputStream = new Scanner(new File(database));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening the file " + database);
            System.exit(0);
        }
        
        // looking for 
        boolean success = false;
        
        while (inputStream.hasNextLine()) {
			  try {
		        	String readUser = inputStream.next();
		        	String readPass = inputStream.next();
		        	String readRole = inputStream.next();
				  
				  if (readUser.contentEquals(username) && readPass.contentEquals(password) && readRole.contentEquals(role)) {
					  System.out.println("Login successful.");
					  
					  success = true;
					  break;
				  } 
			  } 
			  catch (NoSuchElementException e) {
				  System.out.println("Login error."); 
			  }
        }     
        
        if (success) {
        	// what to do after login
        }
        else {
        	System.out.println("Login failed.");
        	start();
        }
        
        inputStream.close();
        
		// try-catch: if parsing fails, spit out error

	}
	
	public void start() {
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
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
			start();
		}
		
	}

	public static void main(String[] args) {
		Start s = new Start();
		s.start();
		
		

	}

}
