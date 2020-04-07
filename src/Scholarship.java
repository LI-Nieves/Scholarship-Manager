package logic;

import java.util.*;

/**
 * You'll need this whole class for the GUI - no modifications needed
 */

public class Scholarship {
	
	// Instance variables
	private ArrayList<String> applicants = new ArrayList<String>();
	private ArrayList<String> grantedTo = new ArrayList<String>();
	
	private String name;
	private int rewardAmount;
	private String semester;
	private int year;
	private int receive;
	private double GPAreq;
	private String wOnTranscript;
	private String deptSpecific = "Across all departments";
	private String facultySpecific = "Across all faculty";
	private String uniSpecific = "Across all universities across the country";
	private String degreeSpecific = "Candidates to every degree (BSc, BA, MS, MBA, PhD, etc.)";
	private String extraCriteria;
	
	// Constructors
	public Scholarship() {}

	public Scholarship(String inputName, int inputReward, String inputSem, int inputYear, int inputRec, double inputGPA, String inputW, String inputDept, 
		String inputFac, String inputUni, String inputDeg, String inputCrit) {
		setName(inputName);
		setRewardAmount(inputReward);
		setSemester(inputSem);
		setYear(inputYear);
		setReceive(inputRec);
		setGPAreq(inputGPA);
		setWonTranscript(inputW);
		setDeptSpecific(inputDept);
		setFacultySpecific(inputFac);
		setUniSpecific(inputUni);
		setDegreeSpecific(inputDeg);
		setCriteria(inputCrit);
	}
	
	// GETTERS
	public String getName() {
		return new String(this.name);
	}		
	public int getRewardAmount() {
		return new Integer(this.rewardAmount);
	}	
	public String getSemester() {
		return new String(this.semester);
	}	
	public int getYear() {
		return new Integer(this.year);
	}	
	public int getReceive() {
		return new Integer(this.receive);
	}		
	public double getGPAreq() {
		return new Double(this.GPAreq);
	}	
	public String getWonTranscript() {
		return new String(this.wOnTranscript);
	}	
	public String getDeptSpecific() {
		return new String(this.deptSpecific);
	}	
	public String getFacultySpecific() {
		return new String(this.facultySpecific);
	}	
	public String getUniSpecific() {
		return new String(this.uniSpecific);
	}		
	public String getDegreeSpecific() {
		return new String(this.degreeSpecific);
	}		
	// This is sketchy, fix this
	public String getExtraCriteria() {
		return this.extraCriteria;
	}	
	// This is sketchy, fix this
	public ArrayList<String> getApplicants() {
		return this.applicants;
	}
	// This is sketchy, fix this
	public ArrayList<String> getGranted() {
		return this.grantedTo;
	}
	
	// SETTERS
	public void setName(String inputName) {
		this.name = new String(inputName);
	}	
	public void setRewardAmount(int inputReward) {
		this.rewardAmount = new Integer(inputReward);
	}	
	public void setSemester(String inputSem) {
		this.semester = new String(inputSem);
	}
	public void setYear(int inputYear) {
		this.year = new Integer(inputYear);
	}
	public void setReceive(int inputRec) {
		this.receive = new Integer(inputRec);
	}
	public void setGPAreq(double inputGPAreq) {
		this.GPAreq = new Double(inputGPAreq);
	}
	public void setWonTranscript(String inputW) {
		this.wOnTranscript = new String(inputW);
	}
	public void setDeptSpecific(String inputDept) {
		this.deptSpecific = new String(inputDept);
	}
	public void setFacultySpecific(String inputFac) {
		this.facultySpecific = new String(inputFac);
	}
	public void setUniSpecific(String inputUni) {
		this.uniSpecific = new String(inputUni);
	}	
	public void setDegreeSpecific(String inputDeg) {
		this.degreeSpecific = new String(inputDeg);
	}
	public void setCriteria(String inputCrit) {
		this.extraCriteria = new String(inputCrit);
	}
	public void addApplicant(String newApplicant) {
		this.applicants.add(newApplicant);
	}
	public void addGranted(String newGranted) {
		this.grantedTo.add(newGranted);
	}	
}
