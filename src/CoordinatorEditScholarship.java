package frontend;
import backend.*;

/*
 * This class is used for the GUI portion of Edit Scholarship for the Coordinator role
 * */

//Imports the following libraries
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;

/*defines the for this coordinator class*/
public class CoordinatorEditScholarship extends JFrame {
	
	/*Defines the following private variables for the class used to stored info from the declaration of this file.*/
	private JPanel contentPane;
	private JTextField Name;
	private JTextField Reward;
	private JTextField Semester;
	private JTextField Year;
	private JTextField Recieve;
	private JTextField GPArequirement;
	private JTextField WonTranscript;
	private JTextField Dept;
	private JTextField faculty;
	private JTextField Uni;
	private JTextField degree;
	private JTextField Extra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Runs the GUI window of the Edit scholarship button
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Coordinator a = new Coordinator("kam");
					Student b = new Student();
					String ye = "";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * This constructor creates the GUI window of edit scholarships, and runs the operations its respective functions done on that GUI window
	 * 
	 * @param a this is a Coordinator object class variable is used to get the information of the current coordinator object
	 *
	 * @param b this is used to save the current information contained in the start object passed into it, and used to store the info
	 * of the updated scholarship info.
	 * 
	 * @param scholar this is used to pass in which scholarship was selected to be edited, and retrieve and update it's info.
	 */
	public CoordinatorEditScholarship(Coordinator a, Start b, Scholarship scholar) {
		//Defines and creates the window and its properties
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 602, 922);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setForeground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//defines and creates the label lblRewardError and its properties in the window
		//used for the incorrect input message for entering a reward money value
		JLabel lblRewardError = new JLabel("Please type in a valid integer value!");
		lblRewardError.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRewardError.setForeground(Color.RED);
		lblRewardError.setBounds(161, 114, 315, 16);
		contentPane.add(lblRewardError);
		
		//defines and creates the label lblYearError and its properties in the window
		//used for the incorrect input message for entering a year value
		JLabel lblYearError = new JLabel("Please type in a valid integer value!");
		lblYearError.setForeground(Color.RED);
		lblYearError.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblYearError.setBounds(161, 259, 315, 16);
		contentPane.add(lblYearError);
		
		//defines and creates the label lblRecieveError its properties in the window
		//used for the incorrect input message for entering number of people who receive this award value
		JLabel lblRecieveError = new JLabel("Please type in a valid integer value!");
		lblRecieveError.setForeground(Color.RED);
		lblRecieveError.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRecieveError.setBounds(161, 332, 315, 16);
		contentPane.add(lblRecieveError);
		
		//defines and creates the label lblGPAError and its properties in the window
		//used for the incorrect input message for entering a GPA value
		JLabel lblGPAError = new JLabel("Please type in a valid decimal value!");
		lblGPAError.setForeground(Color.RED);
		lblGPAError.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGPAError.setBounds(161, 405, 315, 16);
		contentPane.add(lblGPAError);
		
		//sets the visible of the previously defined labels to be invisible
		lblRewardError.setVisible(false);
		lblYearError.setVisible(false);
		lblRecieveError.setVisible(false);
		lblGPAError.setVisible(false);
		
		//defines the JTextField Name, and its properties. And adds a mouse click event to it
		//used for assigning a new name to a Scholarship you want to edit
		Name = new JTextField();
		Name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Name.setForeground(Color.BLACK);
				Name.setText("");
			}
		});
		Name.setForeground(Color.LIGHT_GRAY);
		Name.setText(scholar.getName());
		Name.setBounds(161, 23, 315, 22);
		contentPane.add(Name);
		Name.setColumns(10);
		
		//defines the label lblScholarshipName and its properties.
		//used to distinguish which textField is the one that contains the name of the Scholarship.
		JLabel lblScholarshipName = new JLabel("Scholarship Name:");
		lblScholarshipName.setForeground(Color.LIGHT_GRAY);
		lblScholarshipName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblScholarshipName.setBounds(12, 26, 126, 16);
		contentPane.add(lblScholarshipName);
		
		//defines the label lblRward and its properties.
		//used to distinguish which textField is the one that contains the reward money of the Scholarship.
		JLabel lblRward = new JLabel("Reward Amount:");
		lblRward.setForeground(Color.LIGHT_GRAY);
		lblRward.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRward.setBounds(12, 93, 126, 16);
		contentPane.add(lblRward);
		
		//defines the JTextField Reward, and its properties. And adds a mouse click event to it
		//used for assigning a new reward money value to a Scholarship you want to edit
		Reward = new JTextField();
		Reward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Reward.setForeground(Color.BLACK);
				Reward.setText("");
			}
		});
		Reward.setForeground(Color.LIGHT_GRAY);
		Reward.setText(Integer.toString(scholar.getRewardAmount()));
		Reward.setColumns(10);
		Reward.setBounds(161, 90, 315, 22);
		contentPane.add(Reward);
		
		//defines the label lblSemester and its properties.
		//used to distinguish which textField is the one that represents the semester the Scholarship is.
		JLabel lblSemester = new JLabel("Semester:");
		lblSemester.setForeground(Color.LIGHT_GRAY);
		lblSemester.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSemester.setBounds(12, 166, 126, 16);
		contentPane.add(lblSemester);
		
		//defines the JTextField Semester, and its properties. And adds a mouse click event to it
		//used for assigning a new Semester to the Scholarship you want to edit
		Semester = new JTextField();
		Semester.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Semester.setForeground(Color.BLACK);
				Semester.setText("");
			}
		});
		Semester.setForeground(Color.LIGHT_GRAY);
		Semester.setText(scholar.getSemester());
		Semester.setColumns(10);
		Semester.setBounds(161, 163, 315, 22);
		contentPane.add(Semester);
		
		//defines the label lblYear, and its properties.
		//used to distinguish which textField is the one that represents what the Year the Scholarship is.
		JLabel lblYear = new JLabel("Year:");
		lblYear.setForeground(Color.LIGHT_GRAY);
		lblYear.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblYear.setBounds(12, 239, 126, 16);
		contentPane.add(lblYear);
		
		//defines the label lblRecieve, and its properties.
		//used to distinguish which textField is the one that represents what the number of people who can receive the Scholarship is.
		JLabel lblRecieve = new JLabel("Receive:");
		lblRecieve.setForeground(Color.LIGHT_GRAY);
		lblRecieve.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRecieve.setBounds(12, 312, 126, 16);
		contentPane.add(lblRecieve);
		
		//defines the label lblGparequirement, and its properties.
		//used to distinguish which textField is the one that represents what the Gpa requirement the Scholarship is.
		JLabel lblGparequirement = new JLabel("GPArequirement:");
		lblGparequirement.setForeground(Color.LIGHT_GRAY);
		lblGparequirement.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblGparequirement.setBounds(12, 385, 126, 16);
		contentPane.add(lblGparequirement);
		
		//defines the label lblWontranscript, and its properties.
		//used to distinguish which textField is the one that represents what the W on transcript the Scholarship is.
		JLabel lblWontranscript = new JLabel("WonTranscript:");
		lblWontranscript.setForeground(Color.LIGHT_GRAY);
		lblWontranscript.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblWontranscript.setBounds(12, 458, 126, 16);
		contentPane.add(lblWontranscript);
		
		//defines the label lblDeptspecification, and its properties.
		//used to distinguish which textField is the one that represents what the department specifications on the Scholarship is.
		JLabel lblDeptspecification = new JLabel("Dept Specification:");
		lblDeptspecification.setForeground(Color.LIGHT_GRAY);
		lblDeptspecification.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDeptspecification.setBounds(12, 531, 126, 16);
		contentPane.add(lblDeptspecification);
		
		//defines the label lblFaculitySpecification, and its properties.
		//used to distinguish which textField is the one that represents what the faculty specifications on the Scholarship is.
		JLabel lblFaculitySpecification = new JLabel("Faculty Specification:");
		lblFaculitySpecification.setForeground(Color.LIGHT_GRAY);
		lblFaculitySpecification.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFaculitySpecification.setBounds(12, 604, 150, 16);
		contentPane.add(lblFaculitySpecification);
		
		//defines the label lblUniSpecification, and its properties.
		//used to distinguish which textField is the one that represents what the university specifications on the Scholarship is.
		JLabel lblUniSpecification = new JLabel("Uni Specification:");
		lblUniSpecification.setForeground(Color.LIGHT_GRAY);
		lblUniSpecification.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblUniSpecification.setBounds(12, 677, 150, 16);
		contentPane.add(lblUniSpecification);
		
		//defines the label lblDegreeSpecification, and its properties.
		//used to distinguish which textField is the one that represents what the degree specifications on the Scholarship is.
		JLabel lblDegreeSpecification = new JLabel("Degree Specification:");
		lblDegreeSpecification.setForeground(Color.LIGHT_GRAY);
		lblDegreeSpecification.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDegreeSpecification.setBounds(12, 750, 150, 16);
		contentPane.add(lblDegreeSpecification);
		
		//defines the label lblExtraCriteria, and its properties.
		//used to distinguish which textField is the one that represents what the extra criteria on the Scholarship is.
		JLabel lblExtraCriteria = new JLabel("Extra Criteria:");
		lblExtraCriteria.setForeground(Color.LIGHT_GRAY);
		lblExtraCriteria.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblExtraCriteria.setBounds(12, 823, 150, 16);
		contentPane.add(lblExtraCriteria);
		
		//defines the JTextField Year, and its properties. And adds a mouse click event to it
		//used for assigning a new value for the year of the Scholarship you want to edit
		Year = new JTextField();
		Year.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Year.setForeground(Color.BLACK);
				Year.setText("");
			}
		});
		Year.setForeground(Color.LIGHT_GRAY);
		Year.setText(Integer.toString(scholar.getYear()));
		Year.setColumns(10);
		Year.setBounds(161, 236, 315, 22);
		contentPane.add(Year);
		
		//defines the JTextField Recieve, and its properties. And adds a mouse click event to it
		//used for assigning a new value for the amount of reward money of the Scholarship you want to edit
		Recieve = new JTextField();
		Recieve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Recieve.setForeground(Color.BLACK);
				Recieve.setText("");
			}
		});
		Recieve.setForeground(Color.LIGHT_GRAY);
		Recieve.setText(Integer.toString(scholar.getReceive()));
		Recieve.setColumns(10);
		Recieve.setBounds(161, 309, 315, 22);
		contentPane.add(Recieve);
		
		//defines the JTextField GPArequirement, and its properties. And adds a mouse click event to it
		//used for assigning a new value for the gpa requirement of the Scholarship you want to edit
		GPArequirement = new JTextField();
		GPArequirement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GPArequirement.setForeground(Color.BLACK);
				GPArequirement.setText("");
			}
		});
		GPArequirement.setForeground(Color.LIGHT_GRAY);
		GPArequirement.setText(Double.toString(scholar.getGPAreq()));
		GPArequirement.setColumns(10);
		GPArequirement.setBounds(161, 382, 315, 22);
		contentPane.add(GPArequirement);
		
		//defines the JTextField WonTranscript, and its properties. And adds a mouse click event to it
		//used for assigning a new value for the w on the transcript of the Scholarship you want to edit
		WonTranscript = new JTextField();
		WonTranscript.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WonTranscript.setForeground(Color.BLACK);
				WonTranscript.setText("");
			}
		});
		WonTranscript.setForeground(Color.LIGHT_GRAY);
		WonTranscript.setText(scholar.getWonTranscript());
		WonTranscript.setColumns(10);
		WonTranscript.setBounds(161, 455, 315, 22);
		contentPane.add(WonTranscript);
		
		//defines the JTextField Dept, and its properties. And adds a mouse click event to it
		//used for assigning a new department specification for the Scholarship you want to edit
		Dept = new JTextField();
		Dept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dept.setForeground(Color.BLACK);
				Dept.setText("");
			}
		});
		Dept.setForeground(Color.LIGHT_GRAY);
		Dept.setText(scholar.getDeptSpecific());
		Dept.setColumns(10);
		Dept.setBounds(161, 528, 315, 22);
		contentPane.add(Dept);
		
		//defines the JTextField faculty, and its properties. And adds a mouse click event to it
		//used for assigning a new faculty specification for the Scholarship you want to edit
		faculty = new JTextField();
		faculty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				faculty.setForeground(Color.BLACK);
				faculty.setText("");
			}
		});
		faculty.setForeground(Color.LIGHT_GRAY);
		faculty.setText(scholar.getFacultySpecific());
		faculty.setColumns(10);
		faculty.setBounds(161, 601, 315, 22);
		contentPane.add(faculty);
		
		//defines the JTextField Uni, and its properties. And adds a mouse click event to it
		//used for assigning a new university specification for the Scholarship you want to edit
		Uni = new JTextField();
		Uni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Uni.setForeground(Color.BLACK);
				Uni.setText("");
			}
		});
		Uni.setForeground(Color.LIGHT_GRAY);
		Uni.setText(scholar.getUniSpecific());
		Uni.setColumns(10);
		Uni.setBounds(161, 674, 315, 22);
		contentPane.add(Uni);
		
		//defines the JTextField degree, and its properties. And adds a mouse click event to it
		//used for assigning a new degree specification for the Scholarship you want to edit
		degree = new JTextField();
		degree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				degree.setForeground(Color.BLACK);
				degree.setText("");
			}
		});
		degree.setForeground(Color.LIGHT_GRAY);
		degree.setText(scholar.getDegreeSpecific());
		degree.setColumns(10);
		degree.setBounds(161, 747, 315, 22);
		contentPane.add(degree);
		
		//defines the JTextField Extra, and its properties. And adds a mouse click event to it
		//used for assigning a new Extra criteria specification for the Scholarship you want to edit
		Extra = new JTextField();
		Extra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Extra.setForeground(Color.BLACK);
				Extra.setText("");
			}
		});
		Extra.setForeground(Color.LIGHT_GRAY);
		Extra.setText(scholar.getExtraCriteria());
		Extra.setColumns(10);
		Extra.setBounds(161, 820, 315, 22);
		contentPane.add(Extra);
		
		//defines the following JButton btnName and its properties. And adds a mouse click event to it
		//this button is used to save the new name of the scholarship
		JButton btnName = new JButton("Apply");
		btnName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String input =Name.getText();
				scholar.setName(input);
				b.storeScholarships();
			}
		});
		btnName.setBounds(488, 22, 84, 25);
		contentPane.add(btnName);
		
		//defines the following JButton btnReward and its properties. And adds a mouse click event to it
		//this button is used to save the new value of reward money amount of the scholarship
		JButton btnReward = new JButton("Apply");
		btnReward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = Reward.getText();
				int answer =0;
				try {
					answer = Integer.parseInt(input);
					scholar.setRewardAmount(answer);
					lblRewardError.setVisible(false);
					b.storeScholarships();
				}
				catch(Exception E) {
					lblRewardError.setVisible(true);
				}
			}
		});
		btnReward.setBounds(488, 84, 84, 25);
		contentPane.add(btnReward);
		
		//defines the following JButton btnSemester and its properties. And adds a mouse click event to it
		//this button is used to save the new value of semester of the scholarship
		JButton btnSemester = new JButton("Apply");
		btnSemester.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = Semester.getText();
				scholar.setSemester(input);
				b.storeScholarships();
			}
		});
		btnSemester.setBounds(488, 162, 84, 25);
		contentPane.add(btnSemester);
		
		//defines the following JButton btnYear and its properties. And adds a mouse click event to it
		//this button is used to save the new value of year of the scholarship
		JButton btnYear = new JButton("Apply");
		btnYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = Year.getText();
				int answer=0;
				try{
					answer = Integer.parseInt(input);
					scholar.setYear(answer);
					lblYearError.setVisible(false);
					b.storeScholarships();
				}
				catch(Exception E) {
					lblYearError.setVisible(true);
				}
			}
		});
		btnYear.setBounds(488, 235, 84, 25);
		contentPane.add(btnYear);
		
		//defines the following JButton btnRecieve and its properties. And adds a mouse click event to it
		//this button is used to save the new value of the amount of people who can receive this scholarship
		JButton btnRecieve = new JButton("Apply");
		btnRecieve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = Recieve.getText();
				int answer=0;
				try {
					answer =Integer.parseInt(input);
					scholar.setReceive(answer);
					lblRecieveError.setVisible(false);
					b.storeScholarships();
				}
				catch(Exception E) {
					lblRecieveError.setVisible(true);
				}
			}
		});
		btnRecieve.setBounds(488, 308, 84, 25);
		contentPane.add(btnRecieve);
		
		//defines the following JButton btnGPA and its properties. And adds a mouse click event to it
		//this button is used to save the new value of the gpa requirement for this scholarship
		JButton btnGPA = new JButton("Apply");
		btnGPA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = GPArequirement.getText();
				double answer =0;
				try {
					answer = Double.parseDouble(input);
					scholar.setGPAreq(answer);
					lblGPAError.setVisible(false);
					b.storeScholarships();
				}
				catch(Exception E) {
					lblGPAError.setVisible(true);
				}
			}
		});
		btnGPA.setBounds(488, 381, 84, 25);
		contentPane.add(btnGPA);
		
		//defines the following JButton btnWon and its properties. And adds a mouse click event to it
		//this button is used to save the new boolean value for if the scholarship allows w's on the transcript
		JButton btnWon = new JButton("Apply");
		btnWon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = WonTranscript.getText();
				scholar.setWonTranscript(input);
				b.storeScholarships();
			}
		});
		btnWon.setBounds(488, 454, 84, 25);
		contentPane.add(btnWon);
		
		//defines the following JButton btnDept and its properties. And adds a mouse click event to it
		//this button is used to save the new department specification for the scholarship
		JButton btnDept = new JButton("Apply");
		btnDept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = Dept.getText();
				scholar.setDeptSpecific(input);
				b.storeScholarships();
			}
		});
		btnDept.setBounds(488, 527, 84, 25);
		contentPane.add(btnDept);
		
		//defines the following JButton btnDept and its properties. And adds a mouse click event to it
		//this button is used to save the new department specification for the scholarship
		JButton btnFaculty = new JButton("Apply");
		btnFaculty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = faculty.getText();
				scholar.setFacultySpecific(input);
				b.storeScholarships();
			}
		});
		btnFaculty.setBounds(488, 600, 84, 25);
		contentPane.add(btnFaculty);
		
		//defines the following JButton btnUni and its properties. And adds a mouse click event to it
		//this button is used to save the new university specification for the scholarship
		JButton btnUni = new JButton("Apply");
		btnUni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = Uni.getText();
				scholar.setUniSpecific(input);
				b.storeScholarships();
			}
		});
		btnUni.setBounds(488, 673, 84, 25);
		contentPane.add(btnUni);
		
		//defines the following JButton btnDegree and its properties. And adds a mouse click event to it
		//this button is used to save the new degree specification for the scholarship
		JButton btnDegree = new JButton("Apply");
		btnDegree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = degree.getText();
				scholar.setDegreeSpecific(input);
				b.storeScholarships();
			}
		});
		btnDegree.setBounds(488, 746, 84, 25);
		contentPane.add(btnDegree);
		
		//defines the following JButton btnExtra and its properties. And adds a mouse click event to it
		//this button is used to save the new extra criteria specification for the scholarship
		JButton btnExtra = new JButton("Apply");
		btnExtra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = Extra.getText();
				scholar.setCriteria(input);
				b.storeScholarships();
			}
		});
		btnExtra.setBounds(488, 814, 84, 25);
		contentPane.add(btnExtra);
	}
}
