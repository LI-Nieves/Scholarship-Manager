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

public class studentApplyForScholarship2 extends JFrame {

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
	 * Create the frame.
	 */
	public studentApplyForScholarship2(Student a, Start b, ArrayList<Scholarship> potentialScholar) {
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
		for(int i = 0; i < potentialScholar.size(); i++) 
		{   
			scholarships[i] = potentialScholar.get(i).getName();
		}
		
		JComboBox<String> comboBox = new JComboBox<String>(scholarships);
		comboBox.setBounds(99, 51, 236, 27);
		contentPane.add(comboBox);
		
		
		JLabel lblErrorMessage = new JLabel("");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(6, 192, 438, 53);
		contentPane.add(lblErrorMessage);
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedScholarship = (String) comboBox.getSelectedItem();
				String errorMessage = a.applyGui(a, potentialScholar, selectedScholarship);
				for(int i = 0; i < potentialScholar.size(); i++) {   
				    System.out.println(potentialScholar.get(i).getName());
				}  
				if(errorMessage.equals("<html><p>Congratulations! You have applied. Good luck.</p></html>")) 
				{
					b.storeScholarshipApplicants();
					b.storeStudentApplied();
					lblErrorMessage.setText(errorMessage);
					
				}
				else
				{
					lblErrorMessage.setText(errorMessage);
				}
			}
		});
		btnNewButton.setBounds(154, 109, 117, 29);
		contentPane.add(btnNewButton);
		
		
		
		
		
	}
}
