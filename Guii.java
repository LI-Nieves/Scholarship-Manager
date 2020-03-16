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

public class Guii extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guii frame = new Guii();
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
		
		JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setBackground(Color.WHITE);
		Image img = new ImageIcon(this.getClass().getResource("/sask.jpg")).getImage();
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
				
				
				
				
			}
		});
		contentPane.add(btnRegister);
		
		JLabel lblNewLabel_1 = new JLabel("Don't have an account?");
		lblNewLabel_1.setBounds(926, 335, 154, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblRegisterUsingYour = new JLabel("Register Using your Student ID!");
		lblRegisterUsingYour.setBounds(902, 436, 199, 16);
		contentPane.add(lblRegisterUsingYour);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(517, 458, 98, 49);
		contentPane.add(btnNewButton);
	}
}
