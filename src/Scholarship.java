package logic;

import java.util.*;

public class Scholarship {
	
	// Instance variables
	private double acceptanceRate;
	//private ArrayList<Student> applicants = new ArrayList<Student>();
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
	//private ArrayList<String> extraCriteria; 
	private String extraCriteria;
	
	//private ArrayList<ArrayList<String>> formFormat;
		// The form will be entered as an ArrayList. For example, if they want "What are your hobbies?" and a textbox, the 
		// ArrayList will look like: [["What are your hobbies?",textbox]]
	
	// Constructor
/* 	public Scholarship(String inputName, int inputReward, String inputSem, int inputYear, int inputRec, double inputGPA, boolean inputW, String inputDept, 
			String inputFac, String inputUni, String inputDeg, ArrayList<String> inputCrit) {
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
	} */

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
	
	
	/*
	 * public Scholarship(String inputName, Integer inputReward,
	 * ArrayList<ArrayList<String>> inputFormFormat) { setName(inputName);
	 * //setAcceptanceRate(inputAcceptanceRate); setCriteria(inputCriteria);
	 * setFormFormat(inputFormFormat); }
	 */
	
	// GETTERS
	public String getName() {
		return new String(this.name);
	}
	
	public Double getAcceptanceRate() {
		return new Double(this.acceptanceRate);
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
	
	//This is sketchy, fix this
	//public ArrayList<String> getExtraCriteria() {
	public String getExtraCriteria() {
		/*
		 * ArrayList<String> newCriteria = null; for (String i:(this.extraCriteria)) {
		 * newCriteria.add(i); } return newCriteria;
		 */
		return this.extraCriteria;
	}
	
	//This is sketchy, fix this
/* 	public ArrayList<Student> getApplicants() {
		return this.applicants;
	} */
	public ArrayList<String> getApplicants() {
		return this.applicants;
	}
	
	/*
	 * public ArrayList<ArrayList<String>> getFormFormat() {
	 * ArrayList<ArrayList<String>> newFormFormat = null; for (ArrayList<String>
	 * i:(this.formFormat)) { newFormFormat.add(i); } return newFormFormat; }
	 */
	
	// SETTERS
	public void setName(String inputName) {
		this.name = new String(inputName);
	}
	
	public void setAcceptanceRate(Double inputAcceptanceRate) {
		this.acceptanceRate = new Double(inputAcceptanceRate);
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
	
/* 	public void setCriteria(ArrayList<String> inputCriteriaList) {
		this.extraCriteria = new ArrayList<String>(inputCriteriaList);
	}
	
	public void addCriteria(String newCriteria) {
		this.extraCriteria.add(new String (newCriteria));
	} */

	public void setCriteria(String inputCrit) {
		this.extraCriteria = new String(inputCrit);
	}
	
/* 	public void addApplicant(Student newApplicant) {
		this.applicants.add(newApplicant);
	} */

	public void addApplicant(String newApplicant) {
		this.applicants.add(newApplicant);
	}
	
	/*
	 * public void setFormFormat(ArrayList<ArrayList<String>> inputFormFormatList) {
	 * this.formFormat = new ArrayList<ArrayList<String>>(inputFormFormatList); }
	 * 
	 * public void addToFormFormat(ArrayList<String> newFormFormat) {
	 * this.formFormat.add(new ArrayList<String>(newFormFormat)); }
	 */
	
}
