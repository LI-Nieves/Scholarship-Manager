

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

public class Guii extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameField;
	private JTextField textField_1;
	private static Guii frame = new Guii();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Guii frame = new Guii();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Guii() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1217, 731);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
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
		
		UsernameField = new JTextField();
		UsernameField.setBounds(493, 388, 147, 22);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		//textField_1 = new JTextField();
		//textField_1.setBounds(493, 423, 147, 22);
		//contentPane.add(textField_1);
		//textField_1.setColumns(10);
		
		JPasswordField password = new JPasswordField();
		password.setBounds(493, 423, 147, 22);
		contentPane.add(password);
		
		
		JLabel lblPassword = new JLabel("Password:\r\n");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPassword.setBounds(383, 414, 98, 38);
		contentPane.add(lblPassword);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBounds(931, 369, 120, 61);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register hello = new Register();
				//contentPane.removeAll();
				//contentPane.revalidate();
				//contentPane.repaint();
				//contentPane.setVisible(false);
				
				//contentPane.removeAll();
				//contentPane.revalidate();
				hello.setVisible(true);
				frame.setVisible(false);
				
				
				
				
			}
		});
		contentPane.add(btnRegister);
		
		JLabel lblNewLabel_1 = new JLabel("Don't have an account?");
		lblNewLabel_1.setBounds(926, 335, 154, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblRegisterUsingYour = new JLabel("Register Using your Student ID!");
		lblRegisterUsingYour.setBounds(902, 436, 199, 16);
		contentPane.add(lblRegisterUsingYour);
		
		
		
		JCheckBox chckbxStudent = new JCheckBox("Student");
		JCheckBox chckbxCoordinator = new JCheckBox("Coordinator");
		chckbxStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxCoordinator.setSelected(false);
			}
		});
		chckbxStudent.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		chckbxStudent.setBackground(new Color(0, 102, 0));
		chckbxStudent.setBounds(671, 387, 113, 25);
		contentPane.add(chckbxStudent);
		
		//JCheckBox chckbxCoordinator = new JCheckBox("Coordinator");
		chckbxCoordinator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxStudent.setSelected(false);
			}
			
		});
		chckbxCoordinator.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		chckbxCoordinator.setBackground(new Color(0, 102, 0));
		chckbxCoordinator.setBounds(671, 422, 113, 25);
		contentPane.add(chckbxCoordinator);
		
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartGui Lets_Login = new StartGui();
				String Login_User = UsernameField.getText();
				char[] Login_Password = password.getPassword();
				String Login_Password1 = new String(Login_Password);
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
				boolean Login_Work = Lets_Login.loginGui(Login_User, Login_Password1, role);
				if(Login_Work) {
					AfterLogin Loggedin = new AfterLogin(Login_User);
					Loggedin.setVisible(true);
					frame.setVisible(false);
				}
				else {
					//AfterLogin Loggedin = new AfterLogin();
					//Loggedin.setVisible(true);
					
					frame.setVisible(false);
					try {
						TimeUnit.SECONDS.sleep((long)0.1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.setVisible(true);
					IncorrectLogin.setVisible(true);
				}
				//Lets_Login.log
				
			}
		});
		btnNewButton.setBounds(517, 458, 98, 49);
		contentPane.add(btnNewButton);
		
		
	}
}


// https://www.techiedelight.com/convert-char-array-string-java/
// https://stackoverflow.com/questions/15619682/uncheck-checkboxes-in-java
