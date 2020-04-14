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

public class studentAcceptGrantedScholarship extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//studentAcceptGrantedScholarship frame = new studentAcceptGrantedScholarship();
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
	public studentAcceptGrantedScholarship(Student a,Start b, String scholarship) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWouldYouLike = new JLabel("Would you like to accept the Following Scholarship?");
		lblWouldYouLike.setForeground(Color.WHITE);
		lblWouldYouLike.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWouldYouLike.setBounds(23, 37, 397, 25);
		contentPane.add(lblWouldYouLike);
		
		JLabel lblScholar = new JLabel(scholarship);
		lblScholar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScholar.setForeground(Color.LIGHT_GRAY);
		lblScholar.setBounds(77, 93, 308, 25);
		contentPane.add(lblScholar);
		//lblanswer.setVisible(false);
		
		
		JLabel lblVerdictFinal = new JLabel("Hi");
		lblVerdictFinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVerdictFinal.setBounds(23, 213, 397, 27);
		contentPane.add(lblVerdictFinal);
		lblVerdictFinal.setVisible(false);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String getScholarName ="";
				for(int i=0 ;i<scholarship.length();i++) {
					if(scholarship.charAt(i)!='(') {
						getScholarName = getScholarName+scholarship.charAt(i);
					}
					else {
						break;
					}
				}
				getScholarName = getScholarName.substring(0,getScholarName.length()-1);
				//System.out.println(getScholarName);
				String verdict = a.checkGrantedGui(getScholarName, b.getAllScholarships());
				b.storeStudentAccepted();
				System.out.println(a.getStudentAccepted().size());
				System.out.println(verdict);
				lblVerdictFinal.setText(verdict);
				lblVerdictFinal.setFont(new Font("Times New Roman", Font.BOLD, 14));
				lblVerdictFinal.setForeground(Color.LIGHT_GRAY);
				lblVerdictFinal.setVisible(true);
				
				
			}
		});
		btnYes.setBounds(44, 173, 97, 25);
		contentPane.add(btnYes);
		
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
