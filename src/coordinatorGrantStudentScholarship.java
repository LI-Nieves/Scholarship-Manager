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


public class coordinatorGrantStudentScholarship extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//coordinatorGrantStudentScholarship frame = new coordinatorGrantStudentScholarship();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public coordinatorGrantStudentScholarship(Scholarship a , ArrayList<Student> b , String studentName, Start c, Coordinator d ) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 541, 304);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWouldYouLike = new JLabel("Would you like to grant this student the following Scholarship? ");
		lblWouldYouLike.setForeground(Color.LIGHT_GRAY);
		lblWouldYouLike.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblWouldYouLike.setBounds(50, 13, 446, 45);
		contentPane.add(lblWouldYouLike);
		
		JLabel lblStudent = new JLabel("Student:" + studentName);
		lblStudent.setForeground(Color.WHITE);
		lblStudent.setBounds(50, 52, 412, 34);
		contentPane.add(lblStudent);
		
		JLabel lblScholarship = new JLabel("Scholarship: " + a.getName());
		lblScholarship.setForeground(Color.WHITE);
		lblScholarship.setBounds(50, 99, 412, 34);
		contentPane.add(lblScholarship);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String verdict=d.chooseApplicantsGui(a, b, studentName);
				c.storeScholarshipGrant();
				c.storeStudentGranted();
				System.out.println(verdict);
			}
		});
		btnYes.setBounds(59, 185, 97, 25);
		contentPane.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNo.setBounds(346, 185, 97, 25);
		contentPane.add(btnNo);
	}
}
