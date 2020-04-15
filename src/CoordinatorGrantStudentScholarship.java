package frontend;
import backend.*;

/*
 * This class is used for accepting a student's transcript in the GUI window for the Coordinator role
 * after pressing the Grant Scholarships label
 */

//imports the following libraries
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CoordinatorGrantStudentScholarship extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			//Runs the GUI window of granted scholarship button
			public void run() {
				try {
					//CoordinatorGrantStudentScholarship frame = new CoordinatorGrantStudentScholarship();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This constructor is used for defining and creating the GUI window after the user presses on the Grant Scholarships Label.
	 * 
	 * @param a, this is a Scholarship object variable that is used to reference which scholarship is to be granted to a selected student.
	 * @param b, this is a ArrayList of the object Student which contains a list all the students
	 * @param studentName, this String contains the name of the student that is to be granted this scholarship that was selected by the Coordinator
	 * @param c, this is a Start class object variable which contains a reference to all the information stored in the current Start object class
	 * @param d, this is a Coordinator class object variable which contains a reference to all the information stored in the current Coordinator class object
	 */
	public CoordinatorGrantStudentScholarship(Scholarship a , ArrayList<Student> b , String studentName, Start c, Coordinator d ) {
		//Defines and creates the window and its properties
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 541, 304);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//defines and creates the label lblVerdict and its properties in the window
		//used for a confirmation message of saying you have granted this scholarship, a message saying maximum amount of students granted,
		// or a message saying that the student has already been granted this scholarship.
		JLabel lblVerdict = new JLabel("New label");
		lblVerdict.setVisible(false);
		
		//defines and creates the label lblWouldYouLike and its properties in the window
		//used for a confirmation message of saying if you would like to grant the selected scholarship to the following student or not
		JLabel lblWouldYouLike = new JLabel("Would you like to grant this student the following Scholarship? ");
		lblWouldYouLike.setForeground(Color.LIGHT_GRAY);
		lblWouldYouLike.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblWouldYouLike.setBounds(50, 13, 446, 45);
		contentPane.add(lblWouldYouLike);
		
		//defines and creates the label lblStudent and its properties in the window
		//which is used to display which the name of the student that been selected in the confirmation window by the coordinator
		JLabel lblStudent = new JLabel("Student:" + studentName);
		lblStudent.setForeground(Color.WHITE);
		lblStudent.setBounds(50, 52, 412, 34);
		contentPane.add(lblStudent);
		
		//defines and creates the label lblScholarship and its properties in the window
		//which is used to display which the name of the scholarship that been selected in the confirmation window by the coordinator
		JLabel lblScholarship = new JLabel("Scholarship: " + a.getName());
		lblScholarship.setForeground(Color.WHITE);
		lblScholarship.setBounds(50, 99, 412, 34);
		contentPane.add(lblScholarship);
		
		//creates a new JButton btnYes and defines it properties. And adds a mouse click event
		//this button is used for granting a selected scholarship that you had selected to a student
		JButton btnYes = new JButton("Yes");
		btnYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//stores and saves the accepted scholarships data into the respective object classes
				String verdict=d.chooseApplicantsGui(a, b, studentName);
				c.storeScholarshipGrant();
				c.storeStudentGranted();
				lblVerdict.setText(verdict);
				lblVerdict.setVisible(true);
			}
		});
		btnYes.setBounds(59, 185, 97, 25);
		contentPane.add(btnYes);
		
		//creates a new JButton btnNo and defines it properties. And adds a mouse click event
		//this button is used for not granting the current scholarship that was selected to a student by exiting out of the acceptance window
		JButton btnNo = new JButton("No");
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNo.setBounds(346, 185, 97, 25);
		contentPane.add(btnNo);
		
		
		lblVerdict.setForeground(Color.WHITE);
		lblVerdict.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblVerdict.setBounds(12, 223, 484, 16);
		contentPane.add(lblVerdict);
	}
}
