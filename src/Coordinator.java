package backend;

import java.util.*;
import java.io.*;

/**
 * All the attributes of a Coordinator and the functions they may execute.
 */

public class Coordinator extends User {
	
	// Constructor
	public Coordinator(String inputUsername, String inputPassword) {
		super(inputUsername,inputPassword);
	}
	public Coordinator(String inputUsername) {	// I ADDED THIS -KAM
		super(inputUsername);
	}
	
	/**
	 * Prints how many students an inputted scholarship will be granted to, and how many students applied for that scholarship
	 * @param inputS allScholarships
	 */
	public String viewAllStatisticsGui(ArrayList<Scholarship> inputS) {
		String returnThis = "";
		for (Scholarship s : inputS) {
			returnThis = returnThis+s.getName()+":\nThis scholarship will be granted to " + s.getReceive() + " applicants.\n" + 
				s.getApplicants().size() + " students have applied to this scholarship:\n\n";
			for (String a : s.getApplicants()) {
				returnThis = returnThis+" * "+a+"\n";
			}
			returnThis = returnThis + "\n\n--------------------------------------------------------------------------------------\n\n";
		}
		return returnThis;
	}

	/**
	 * This is used to delete a scholarship.
	 * You will need this for the GUI, but with some slight tweaks on the input
	 * @param inputS	allScholarships
	 * @param delete	the scholarship to delete
	 * @return			allScholarships after the indicated scholarship is deleted
	 */
	public ArrayList<Scholarship> removeScholarshipsGui(ArrayList<Scholarship> inputS, String delete) {
		ArrayList<Integer> indicesToDelete = new ArrayList<Integer>(); // this is where we will store the indices of the scholarships with the indicated name
		int i = 0;
		
		for (Scholarship s : inputS) { // looking through all scholarships for the name...
			if (s.getName().equals(delete)) {
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
	 * @param inputS	allScholarships
	 * @param inputA	allStudents
	 * @param scholName	the indicated scholarship
	*/
	public String grantScholarshipGui(ArrayList<Scholarship> inputS, ArrayList<Student> inputA, String scholName) {
		String returnThis = "";
		Scholarship selectedS = findScholarship(scholName, inputS);	// find the Scholarship associated with the name of the indicated scholarship
		// if the input is valid...
		if (selectedS.getName() != null) {		
			// if no students have applied to this scholarship
			if (selectedS.getApplicants().size() == 0) {			
				System.out.println("There are no applicants to this scholarship yet.");
				return "There are no applicants to this scholarship";
			} 
			// otherwise, show the applicants
			else {
				for (String a : selectedS.getApplicants()) {
					returnThis = returnThis + a + "\n";
				}
			}
		}
		return returnThis;
	}

	/**
	 * Used to find the Students that the coordinator wants to grant the scholarship to
	 * @param schol		allScholarships
	 * @param inputA	allStudents
	 * @param student	the indicated student
	 */
	public String chooseApplicantsGui(Scholarship schol, ArrayList<Student> inputA, String student) {
		String returnThis = "";
		// if the scholarship hasn't been granted to the max amount of students yet
		if (schol.getGranted().size() <= schol.getReceive()) {
			Student foundStu = findStudent(student,inputA);
			// if the student has already been granted the scholarship
			if (alreadyGranted(student, schol.getGranted())) {
				return"This scholarship has already been granted to this student.";
			}
			// otherwise, grant the scholarship to the student
			else {
				foundStu.addStudentGranted(schol.getName());
				schol.addGranted(foundStu.getUsername());
				returnThis = returnThis + "Student: " + foundStu.getUsername()+ " Has been granted " + schol.getName();
			}
		}
		// if the scholarship has been granted to the max amount of students yet
		else {
			returnThis = returnThis+ "This scholarship has been granted to the maximum (" + schol.getReceive() + ") amount of student(s).";
		}
		return returnThis;
	}

	/**
	 * Used to find the Student object associated with the indicated student name
	 * @param name		indicated student name
	 * @param inputS	allStudents
	 * @return			the Student object
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
	 * @param name		indicated student
	 * @param inputA	allStudents
	 * @return			true if they have, false otherwise
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
	 * Used to print names of all the scholarships in a specified ArrayList
	 * @param inputScholarship	specified ArrayList
	 * @return					all the names
	 */
	public String getAllScholarshipsGui(ArrayList<Scholarship> inputScholarship) {
		
		String returnThis = "";

		for (Scholarship s : inputScholarship) {
			String eachScholarship = "";
			eachScholarship = eachScholarship +s.getName()+ " (" + s.getSemester() + " " + s.getYear() + ")\n\n";
			returnThis = returnThis.concat(eachScholarship);
		}
		return returnThis;
	}

}


