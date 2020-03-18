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

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class AfterLogin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AfterLogin frame = new AfterLogin();
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
	public AfterLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 676);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/rsz_usask.png")).getImage();
		logo.setIcon(new ImageIcon (img));
		logo.setBounds(0, 0, 179, 161);
		contentPane.add(logo);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnLogOut.setBounds(777, 0, 97, 25);
		contentPane.add(btnLogOut);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(179, 0, 598, 25);
		contentPane.add(panel);
		
		JLabel lblHelloWelcomeTo = new JLabel("Hello! Welcome to your Home page NAME...");
		panel.add(lblHelloWelcomeTo);
		lblHelloWelcomeTo.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblHelloWelcomeTo.setBackground(new Color(204, 0, 153));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(0, 204, 51));
		panel_1.setBounds(179, 26, 695, 135);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		//System.out.println(dtf.format(now));
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		JLabel lblNewLabel = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/rsz_2019_05_03-cap_and_gown_commercial_shoot_jatorner_-0255-web.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon (img1));
		lblNewLabel.setBounds(0, 0, 695, 135);
		panel_1.add(lblNewLabel);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 162, 179, 467);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblViewScholarships = new JLabel("View Scholarships");
		lblViewScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewScholarships.setBounds(12, 32, 155, 16);
		panel_2.add(lblViewScholarships);
		
		JLabel lblAppliedScholarships = new JLabel("Applied Scholarships");
		lblAppliedScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAppliedScholarships.setBounds(12, 95, 155, 16);
		panel_2.add(lblAppliedScholarships);
		
		JLabel lblViewPortfolio = new JLabel("View Portfolio");
		lblViewPortfolio.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewPortfolio.setBounds(12, 161, 155, 16);
		panel_2.add(lblViewPortfolio);
		
		JLabel lblEditPortfolio = new JLabel("Edit Portfolio");
		lblEditPortfolio.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblEditPortfolio.setBounds(12, 226, 155, 16);
		panel_2.add(lblEditPortfolio);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 441, 143, 26);
		panel_2.add(panel_3);
		JLabel TIME = new JLabel(dtf.format(now));
		panel_3.add(TIME);
	}
}
/** REFERENCES
 * 
 * https://www.javatpoint.com/java-get-current-date
 * 
 * https://stories.uiowa.edu/sites/stories.uiowa.edu/files/2019_05_03-cap_and_gown_commercial_shoot_jatorner_-0255-web.jpg
 */
