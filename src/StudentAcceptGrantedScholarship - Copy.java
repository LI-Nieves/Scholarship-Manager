package frontend;
import backend.*;

/*
 * This file contains all the code that is used for the GUI portion of the Accept Granted Scholarship
 * in view granted scholarships button for the student role.
 */

//imports the following libraries
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentAcceptGrantedScholarship extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Runs the GUI window of the view granted scholarship button
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//StudentAcceptGrantedScholarship frame = new StudentAcceptGrantedScholarship();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * This constructor is used for defining and creating the GUI window of acceptGrantedScholarships after the view Granted Scholarships operation.
	 * 
	 * @param a this is a Coordinator object class variable is used to get the information of the current coordinator object
	 *
	 * @param b this is used to save the current information contained in the start object passed into it, and used to store the info
	 * of the updated scholarship info.
	 * 
	 * @param scholarship which is used string of the name of the scholarship, which is to determine which scholarship was selected to be accepted
	 */
	public StudentAcceptGrantedScholarship(Student a,Start b, String scholarship) {
		//Defines and creates the window and its properties
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//defines and creates the label lblWouldYouLike and its properties in the window
		//used for a confirmation message of saying if you would like to accept the selected scholarship or not
		JLabel lblWouldYouLike = new JLabel("Would you like to accept the Following Scholarship?");
		lblWouldYouLike.setForeground(Color.WHITE);
		lblWouldYouLike.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWouldYouLike.setBounds(23, 37, 397, 25);
		contentPane.add(lblWouldYouLike);
		
		//defines and creates the label lblScholar and its properties in the window
		//used for a confirmation of if the following scholarship is what you are willing to accept to take
		JLabel lblScholar = new JLabel(scholarship);
		lblScholar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScholar.setForeground(Color.LIGHT_GRAY);
		lblScholar.setBounds(77, 93, 308, 25);
		contentPane.add(lblScholar);
		//lblanswer.setVisible(false);
		
		//defines and creates the label lblVerdictFinal and its properties in the window
		//used for a confirmation message of saying you accepted the scholarship, or you have already accepted this scholarship.
		JLabel lblVerdictFinal = new JLabel("Hi");
		lblVerdictFinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVerdictFinal.setBounds(23, 213, 397, 27);
		contentPane.add(lblVerdictFinal);
		lblVerdictFinal.setVisible(false);
		
		//creates a new JButton btnYes and defines it properties. And adds a mouse click event
		//this button is used for accepting a selected scholarship that you had selected
		JButton btnYes = new JButton("Yes");
		btnYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String getScholarName ="";
				//loops through each character contained in the parameter variable scholarship
				//and saves it the variable getScholarName
				for(int i=0 ;i<scholarship.length();i++) {
					if(scholarship.charAt(i)!='(') {
						getScholarName = getScholarName+scholarship.charAt(i);
					}
					else {
						break;
					}
				}
				//stores and saves the accepted scholarships data into the respective object classes
				getScholarName = getScholarName.substring(0,getScholarName.length()-1);
				String verdict = a.checkGrantedGui(getScholarName, b.getAllScholarships());
				b.storeStudentAccepted();
				b.storeStudentTermYear();
				lblVerdictFinal.setText(verdict);
				lblVerdictFinal.setFont(new Font("Times New Roman", Font.BOLD, 14));
				lblVerdictFinal.setForeground(Color.LIGHT_GRAY);
				lblVerdictFinal.setVisible(true);
				
				
			}
		});
		btnYes.setBounds(44, 173, 97, 25);
		contentPane.add(btnYes);
		
		//creates a new JButton btnNo and defines it properties. And adds a mouse click event
		//this button is used for not accepting the current scholarship that was selected by exiting out of the acceptance window
		JButton btnNo = new JButton("No");
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNo.setBounds(288, 173, 97, 25);
		contentPane.add(btnNo);
	}
}
