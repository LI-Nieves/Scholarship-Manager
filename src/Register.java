package frontend;
import backend.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The Register class is used to register a user into the database it will ask for username and password with textbox
 * and ask for which type of user though checkbox
 */
public class Register extends JFrame {

	private JPanel contentPane;
	private JButton btnRegister;
	private JTextField Username;
	private JTextField Password;

	/**
	 * Launch the application.
	 * main function
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * function that deisplayes the register gui
	 * @return
	 * @param
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 904, 687);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/images/rsz_usask.png")).getImage();
		logo.setIcon(new ImageIcon (img));
		logo.setBounds(0, 0, 179, 161);
		contentPane.add(logo);
		
		//checkbox for determining what type of user
		JCheckBox chckbxCoordinator = new JCheckBox("Coordinator");
		JCheckBox chckbxStudent = new JCheckBox("Student");
		chckbxCoordinator.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chckbxStudent.setSelected(false);
				
			}
		});
		chckbxStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chckbxCoordinator.setSelected(false);
			}
		});
		chckbxStudent.setBounds(389, 257, 113, 25);
		contentPane.add(chckbxStudent);
		
		
		chckbxCoordinator.setBounds(389, 294, 113, 25);
		contentPane.add(chckbxCoordinator);
		
		Username = new JTextField();
		Username.setBounds(386, 166, 116, 22);
		contentPane.add(Username);
		Username.setColumns(10);
		
		//text fields used to grab username and password of the new user
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(272, 169, 79, 16);
		contentPane.add(lblUsername);
		
		Password = new JTextField();
		Password.setBounds(388, 216, 116, 22);
		contentPane.add(Password);
		Password.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(272, 218, 79, 16);
		contentPane.add(lblPassword);
		
		JLabel errorLabel = new JLabel("New label");
		errorLabel.setVisible(false);
		
		//button to complete register
		btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Start lets_Register = new Start();
				String username1 = Username.getText();
				String password1 = Password.getText();
				String role1 = " ";
				
				//check checkboxes
				if(chckbxStudent.isSelected()) {
					role1 = "Student";
					
				}
				else if(chckbxCoordinator.isSelected()) {
					role1 = "Coordinator";
				}
				int result = lets_Register.registerGui(username1, password1, role1);
				//display messages based on result
				if(result==1) {
					errorLabel.setText("Registration failed. Please select a valid role (Student,Coordinator)");
					errorLabel.setForeground(Color.RED);
					errorLabel.setVisible(true);
				}
				else if(result==2) {
					errorLabel.setText("This username already exists in the server. Please try again.");
					errorLabel.setForeground(Color.RED);
					errorLabel.setVisible(true);
				}
				else if(result==3) {
					errorLabel.setText("Your New account has been succesfully created, please try logging in now");
					errorLabel.setForeground(Color.CYAN);
					errorLabel.setVisible(true);
					Main ChangeToLogin = new Main();
					ChangeToLogin.setVisible(true);
					
				}
				
								
			}
		});
		btnRegister.setBounds(389, 371, 113, 54);
		contentPane.add(btnRegister);
		
		//JLabel errorLabel = new JLabel("New label");
		
		errorLabel.setBounds(272, 121, 480, 16);
		contentPane.add(errorLabel);
		
		
	}
}
