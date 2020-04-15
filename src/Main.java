package frontend;
import backend.*;

/**
 * @author Kamalpreet Mundi, Navjeet Hundal, Edward Mah, Eric Wu, Lana Nieves
 * 
 * This Program is used when a user wants to log into the university System. 
 * Using their login credentials. If they do not have login credentials they can simply Register for one.
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

import java.util.concurrent.TimeUnit;

public class Main extends JFrame {
	// Initalizing variables to hold the username and password(login credentials)
	String Login_Username;
	String Login_Password;
	
	// Initialzing some important components for the interface
	private JPanel contentPane;
	private JTextField UsernameField;
	private JTextField textField_1;
	private static Main frame = new Main();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Main frame = new Main();
					frame.setVisible(true);		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This function is the doorway to logging into the Scholarship system. The Main program is the program that is the only program that needs
	 * to be executed in order to start up and run the system. Everything else follows after this program.
	 * This Main involves a login part and a register part, allowing a user to login or register for the system.  
	 */
	public Main() {
		// Initalizing the whole frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1217, 731);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Setting up some labels for the program, just basic stuff so far
		JLabel IncorrectLogin = new JLabel("The Username Or Password entered was Incorrect. Please try again");
		IncorrectLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		IncorrectLogin.setForeground(new Color(255, 0, 0));
		IncorrectLogin.setBounds(361, 353, 441, 16);
		IncorrectLogin.setVisible(false);
		contentPane.add(IncorrectLogin);
		
		JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setBackground(Color.WHITE);
		Image img = new ImageIcon(this.getClass().getResource("/images/sask.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon (img));
		lblNewLabel.setBounds(177, 33, 812, 307);
		lblNewLabel.setBackground(Color.BLUE);
		contentPane.add(lblNewLabel);
		
		JLabel lblUs = new JLabel("Username: ");
		lblUs.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUs.setBounds(383, 379, 98, 38);
		contentPane.add(lblUs);
		
		// Initializng textfield boxes
		
		// This is the textField for loggin in with your Username
		UsernameField = new JTextField();
		UsernameField.setBounds(493, 388, 147, 22);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		
		// This is the password filed for logging in with your password
		JPasswordField password = new JPasswordField();
		password.setBounds(493, 423, 147, 22);
		contentPane.add(password);
		
		// Another label for password
		JLabel lblPassword = new JLabel("Password:\r\n");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPassword.setBounds(383, 414, 98, 38);
		contentPane.add(lblPassword);
		
		
		// This is the button you press to register a new user. A new frame will open when this button is pressed.
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBounds(931, 369, 120, 61);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Once the button is pressed open the register frame, in order to allow the user to register
				Register hello = new Register();
				hello.setVisible(true);
				frame.setVisible(false);
				
				
				
				
			}
		});
		contentPane.add(btnRegister);
		
		// Just another label
		JLabel lblNewLabel_1 = new JLabel("Don't have an account?");
		lblNewLabel_1.setBounds(926, 335, 154, 38);
		contentPane.add(lblNewLabel_1);
		
		
		// Checkbox used to login to diffrentiate between a student or Coordinator, bot boxes can't be checked at once
		// We take in account for this
		JCheckBox chckbxStudent = new JCheckBox("Student");
		JCheckBox chckbxCoordinator = new JCheckBox("Coordinator");
		chckbxStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxCoordinator.setSelected(false);	// If student is checked, uncheck coordinator
			}
		});
		chckbxStudent.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		chckbxStudent.setBackground(new Color(0, 102, 0));
		chckbxStudent.setBounds(671, 387, 113, 25);
		contentPane.add(chckbxStudent);
		
		//JCheckBox chckbxCoordinator = new JCheckBox("Coordinator");
		chckbxCoordinator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxStudent.setSelected(false);	// If coordinator is checked, uncheck Student
			}
			
		});
		chckbxCoordinator.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		chckbxCoordinator.setBackground(new Color(0, 102, 0));
		chckbxCoordinator.setBounds(671, 422, 113, 25);
		contentPane.add(chckbxCoordinator);
		
		
		// This is the button you press when you actually want to login
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Start Lets_Login = new Start();		// Start has some functions we need to use, thus we initalize it
				String Login_User = UsernameField.getText();	// Get username
				Login_Username = Login_User;					// Set global variable
				char[] Login_Pass = password.getPassword();
				String Login_Pass1 = new String(Login_Pass);	// Get password
				Login_Password = Login_Pass1;					// Set Global variable
				String role;
				if(chckbxCoordinator.isSelected()) {
					role = "Coordinator";
				}
				else if(chckbxStudent.isSelected()) {
					role = "Student";
					
					
				}
				else {
					role = "";
				}
				// Chcek to see if that Username and password is actually present in the system
				boolean Login_Work = Lets_Login.loginGui(Login_User, Login_Pass1, role);
				if(Login_Work & role.equals("Student")) {
					AfterLogin Loggedin = new AfterLogin(Login_User);		// Next JFRAME opens (Student)
					Loggedin.setVisible(true);
					frame.setVisible(false);
				}
				else if(Login_Work & role.equals("Coordinator")) {
					AfterLoginCoord logginCord = new AfterLoginCoord(Login_Username);	// Next Jframe opens (Coordinator)
					logginCord.setVisible(true);
					frame.setVisible(false);
					
				}
				else {
					
					// Otherwise throw an error messaging, say the correct Username and password was not provided
					frame.setVisible(false);
					try {
						TimeUnit.SECONDS.sleep((long)0.1);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					frame.setVisible(true);
					IncorrectLogin.setVisible(true);
				}
			
				
			}
		});
		btnNewButton.setBounds(517, 458, 98, 49);
		contentPane.add(btnNewButton);
		
		
	}
}


// Resources Used:
// https://www.techiedelight.com/convert-char-array-string-java/
// https://stackoverflow.com/questions/15619682/uncheck-checkboxes-in-java
