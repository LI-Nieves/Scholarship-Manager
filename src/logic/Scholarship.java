package logic;

import java.util.*;

public class Scholarship {
	
	// Instance variables
	private String name;
	private Double acceptanceRate;
	private ArrayList<String> criteria; 
	private ArrayList<ArrayList<String>> formFormat;
		// The form will be entered as an ArrayList. For example, if they want "What are your hobbies?" and a textbox, the 
		// ArrayList will look like: [["What are your hobbies?",textbox]]
	
	// Constructor
	public Scholarship(String inputName, Double inputAcceptanceRate, 
			ArrayList<String> inputCriteria, ArrayList<ArrayList<String>> inputFormFormat) {
		setName(inputName);
		setAcceptanceRate(inputAcceptanceRate);
		setCriteria(inputCriteria);
		setFormFormat(inputFormFormat);
	}
	
	// GETTERS
	public String getName() {
		String newName = this.name;
		return newName;
	}
	
	public Double getAcceptanceRate() {
		Double newAcceptanceRate = this.acceptanceRate;
		return newAcceptanceRate;
	}	
	
	public ArrayList<String> getCriteria() {
		ArrayList<String> newCriteria = null;
		for (String i:(this.criteria)) {
			newCriteria.add(i);
		}
		return newCriteria;
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
	
	public void setCriteria(ArrayList<String> inputCriteriaList) {
		this.criteria = new ArrayList<String>(inputCriteriaList);
	}
	
	public void addCriteria(String newCriteria) {
		this.criteria.add(new String (newCriteria));
	}
	
	public void setFormFormat(ArrayList<ArrayList<String>> inputFormFormatList) {
		this.formFormat = new ArrayList<ArrayList<String>>(inputFormFormatList);
	}	
	
	public void addToFormFormat(ArrayList<String> newFormFormat) {
		this.formFormat.add(new ArrayList<String>(newFormFormat));
	}
	
}
