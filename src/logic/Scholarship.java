package logic;

import java.util.*;

public class Scholarship {
	
	// Instance variables
	private String name;
	private Double acceptanceRate;
	
	private int rewardAmount;
	private float GPAreq;
	private boolean wOnTranscript;
	private String deptSpecific = "Across all departments";
	private String facultySpecific = "Across all faculty";
	private String uniSpecific = "Across all universities across the country";
	private String degreeSpecific = "Candidates to every degree (BSc, BA, MS, MBA, PhD, etc.)";
	private ArrayList<String> extraCriteria; 
	
	private ArrayList<ArrayList<String>> formFormat;
		// The form will be entered as an ArrayList. For example, if they want "What are your hobbies?" and a textbox, the 
		// ArrayList will look like: [["What are your hobbies?",textbox]]
	
	// Constructor
	public Scholarship(String inputName, int inputReward, float inputGPA, boolean inputW, String inputDept, String inputFac, String inputUni, String inputDeg, ArrayList<String> inputCrit) {
		setName(inputName);
		setRewardAmount(inputReward);
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
	
	public float getGPAreq() {
		return new Float(this.GPAreq);
	}
	
	public boolean getWonTranscript() {
		return new Boolean(this.wOnTranscript);
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
	public ArrayList<String> getExtraCriteria() {
		/*
		 * ArrayList<String> newCriteria = null; for (String i:(this.extraCriteria)) {
		 * newCriteria.add(i); } return newCriteria;
		 */
		return this.extraCriteria;
	}
	
	public ArrayList<ArrayList<String>> getFormFormat() {
		ArrayList<ArrayList<String>> newFormFormat = null;
		for (ArrayList<String> i:(this.formFormat)) {
			newFormFormat.add(i);
		}
		return newFormFormat;
	}
	
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
	
	public void setGPAreq(float inputGPAreq) {
		this.GPAreq = new Float(inputGPAreq);
	}
	
	public void setWonTranscript(boolean inputW) {
		this.wOnTranscript = new Boolean(inputW);
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
	
	public void setCriteria(ArrayList<String> inputCriteriaList) {
		this.extraCriteria = new ArrayList<String>(inputCriteriaList);
	}
	
	public void addCriteria(String newCriteria) {
		this.extraCriteria.add(new String (newCriteria));
	}
	
	public void setFormFormat(ArrayList<ArrayList<String>> inputFormFormatList) {
		this.formFormat = new ArrayList<ArrayList<String>>(inputFormFormatList);
	}	
	
	public void addToFormFormat(ArrayList<String> newFormFormat) {
		this.formFormat.add(new ArrayList<String>(newFormFormat));
	}
	
}
