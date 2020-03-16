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

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnRegister;
	private JTextField textField_2;
	private JLabel lblFirstName;
	private JTextField textField_3;
	private JLabel lblLastName;

	/**
	 * Launch the application.
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
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 687);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setBounds(364, 189, 145, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmailAddress.setBounds(228, 190, 145, 16);
		contentPane.add(lblEmailAddress);
		
		textField_1 = new JTextField();
		textField_1.setBounds(364, 232, 145, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblStudentid = new JLabel("StudentId:");
		lblStudentid.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblStudentid.setBounds(228, 233, 145, 16);
		contentPane.add(lblStudentid);
		
		JLabel logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/rsz_usask.png")).getImage();
		logo.setIcon(new ImageIcon (img));
		logo.setBounds(0, 0, 179, 161);
		contentPane.add(logo);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(388, 388, 97, 54);
		contentPane.add(btnRegister);
		
		textField_2 = new JTextField();
		textField_2.setBounds(364, 275, 145, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFirstName.setBounds(228, 281, 145, 16);
		contentPane.add(lblFirstName);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(364, 318, 145, 22);
		contentPane.add(textField_3);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLastName.setBounds(228, 324, 145, 16);
		contentPane.add(lblLastName);
	}

}
