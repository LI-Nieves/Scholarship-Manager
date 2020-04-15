package frontend;
import backend.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
/**
 * 
 * @author navjeethundal
 *This class in the second window where you choose which scholarship you wish to apply, pretty much lets you apply 
 */
public class StudentApplyForScholarship2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This function is the gui part of applying for a scholarship where the student chooses the scholarship they wish to apply to
	 * @param a is the student 
	 * @param b is the reference to start
	 * @param potentialScholar is all the scholarships that the student may choose from
	 */
	public StudentApplyForScholarship2(Student a, Start b, ArrayList<Scholarship> allScholar, ArrayList<Scholarship> potentialScholar, int term, int year) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setForeground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please provide the scholarship name you wish to apply for:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(32, 16, 385, 16);
		contentPane.add(lblNewLabel);
		
		String[] scholarships = new String[potentialScholar.size()];
		//gets all the scholarship names from the arrayList and puts them into an array
		for(int i = 0; i < potentialScholar.size(); i++) 
		{   
			scholarships[i] = potentialScholar.get(i).getName();
		}
		//This combobox has a list of all scholarships that you could choose from based on the term and year you provided 
		JComboBox<String> comboBox = new JComboBox<String>(scholarships);
		comboBox.setBounds(99, 51, 236, 27);
		contentPane.add(comboBox);
		
		
		JLabel lblErrorMessage = new JLabel("");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(6, 192, 438, 53);
		contentPane.add(lblErrorMessage);
		
		//When this button is pressed it lets you apply for the scholarship you have chosen and adds it into the database
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedScholarship = (String) comboBox.getSelectedItem(); // makes a combobox with all the potential scholarships 
				String errorMessage = a.applyGui(a, allScholar, potentialScholar, selectedScholarship, term, year); // get the message back from the backend
				if(errorMessage.equals("<html><p>Congratulations! You have applied. Good luck.</p></html>"))  
				{
					// stores the new scholarships that the user applied for into the text files/ data base that we read
					b.storeScholarshipApplicants();
					b.storeStudentApplied();
					lblErrorMessage.setText(errorMessage); // give the message that they have applied 
					
				}
				else
				{
					lblErrorMessage.setText(errorMessage); //give back the error message of what went wrong
				}
			}
		});
		btnNewButton.setBounds(154, 109, 117, 29);
		contentPane.add(btnNewButton);
		
		
		
		
		
	}
}
