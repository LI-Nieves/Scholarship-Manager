

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

//import com.sun.glass.ui.Cursor;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.event.MouseMotionAdapter;

public class AfterLogin extends JFrame{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AfterLogin frame = new AfterLogin("Kam");
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
	public AfterLogin(String a) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 892, 676);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/images/rsz_usask.png")).getImage();
		logo.setIcon(new ImageIcon (img));
		logo.setBounds(0, 0, 179, 161);
		contentPane.add(logo);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LogOut loggingOut =  new LogOut();
				loggingOut.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnLogOut.setBounds(777, 0, 97, 25);
		contentPane.add(btnLogOut);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(179, 0, 598, 25);
		contentPane.add(panel);
		
		JLabel lblHelloWelcomeTo = new JLabel("Hello! Welcome to your Home page "+a);
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
		Image img1 = new ImageIcon(this.getClass().getResource("/images/cap_gown.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon (img1));
		lblNewLabel.setBounds(0, 0, 695, 135);
		panel_1.add(lblNewLabel);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 162, 179, 467);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 174, 673, 442);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
			
		
			
		scrollPane.setViewportView(textPane);
		scrollPane.setVisible(false);
		textPane.setEditable(false);
		
		JLabel lblViewScholarships = new JLabel("View Scholarships");
		lblViewScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblViewScholarships.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblViewScholarships.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				scrollPane.setVisible(true);
				Start getScholarships = new Start();
				Student students = new Student();
				getScholarships.loadScholarships();
				String scholarshipInfo =students.viewScholarshipsGui(getScholarships.getAllScholarships());
				System.out.println(scholarshipInfo);
				textPane.setText(scholarshipInfo);
				//scrollPane.getVerticalScrollBar().setValue(0);
			}
		});
		
		
		//l.setCursor(new Cursor(Cursor.CURSOR_CLOSED_HAND));
		
		lblViewScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewScholarships.setBounds(12, 32, 155, 16);
		panel_2.add(lblViewScholarships);
		
		JLabel lblAppliedScholarships = new JLabel("Applied Scholarships");
		lblAppliedScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAppliedScholarships.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAppliedScholarships.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//textPane.setText("You have applied to the following Scholarships:");
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				Student student = new Student();
				Start loading = new Start();
				loading.loadStudents();
				loading.loadStudentApplied();
				student =loading.findStudent(a);
				String scholarships=student.viewMyScholarshipsGui();
				textPane.setText(scholarships);
				//textPane.insertComponent(l);
				scrollPane.setVisible(true);
				//textPane.setText(null);
			}
		});
			
		lblAppliedScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAppliedScholarships.setBounds(12, 116, 155, 16);
		panel_2.add(lblAppliedScholarships);
		
		JLabel lblViewPortfolio = new JLabel("View Portfolio");
		lblViewPortfolio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblViewPortfolio.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblViewPortfolio.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				Start loading = new Start();
				Student students = new Student();
				loading.loadStudents();
				loading.loadStudentFiles();
				students = loading.findStudent(a);
				String file = students.viewUploadedGui();
				String output = students.openFileGui(file);
				textPane.setText(output);
				scrollPane.setVisible(true);
			}
		});
		lblViewPortfolio.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewPortfolio.setBounds(12, 161, 155, 16);
		panel_2.add(lblViewPortfolio);
		
		JLabel lblEditPortfolio = new JLabel("Edit Portfolio");
		lblEditPortfolio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEditPortfolio.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEditPortfolio.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Start loading = new Start();
				Student students = new Student();
				loading.loadStudents();
				loading.loadStudentFiles();
				students = loading.findStudent(a);
				String file = students.viewUploadedGui();
				String output = students.openFileGui(file);
				textPane.setText(output);
				textPane.setEditable(true);
				textPane.setCaretColor(null);
				JButton btnSave = new JButton("Save");
				btnSave.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Student students1 = new Student();
						students1 = loading.findStudent(a);
						students1.uploadGui(a, textPane.getText());
						loading.storeStudentFiles();
						//System.out.println(textPane.getText());
					}
				});
				textPane.insertComponent(btnSave);
				scrollPane.setVisible(true);
			}
		});
		lblEditPortfolio.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblEditPortfolio.setBounds(12, 206, 155, 16);
		panel_2.add(lblEditPortfolio);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 441, 143, 26);
		panel_2.add(panel_3);
		JLabel TIME = new JLabel(dtf.format(now));
		panel_3.add(TIME);
		
		JLabel lblViewGrantedScholarships = new JLabel("View Granted Scholarships");
		lblViewGrantedScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblViewGrantedScholarships.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblViewGrantedScholarships.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("You have been Granted the following Scholarships. Please click on them to accept: \n");
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				Start loading = new Start();
				loading.loadScholarships();
				loading.loadScholarshipApplicants();
				loading.loadStudents();
				loading.loadStudentFiles();
				loading.loadStudentApplied();
				loading.loadScholarshipGrant();
				loading.loadStudentGranted();
				loading.loadStudentAccepted();
				loading.loadStudentTermYear();
				Student grantedInfo  = new Student();
				 grantedInfo = loading.findStudent(a);
				String info = grantedInfo.viewGrantedGui(loading.getAllScholarships());
				//textPane.setText(info);
				//scrollPane.setVisible(true);
				String[] splitting = info.split("\n");
				for(String scholar: splitting ) {
					JLabel l=new JLabel(scholar);
					l.setFont(new Font("Times New Roman", Font.BOLD, 13));
					l.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						l.setForeground(Color.CYAN);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						l.setForeground(Color.BLACK);
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						//studentAcceptGrantedScholarship(Student a,Start b, String scholarship )
						Student lookUp = new Student();
						lookUp = loading.findStudent(a);
						studentAcceptGrantedScholarship accept= new studentAcceptGrantedScholarship(lookUp, loading, scholar);
						accept.setVisible(true);
					}
					});
					Document doc = textPane.getDocument();
					try {
						doc.insertString(doc.getLength(), "\n", null);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println(scholar);
					textPane.insertComponent(l);

				}
				scrollPane.setVisible(true);
				
				//s.loadScholarships();
				//s.loadScholarshipApplicants();
				//s.loadStudents();
				//s.loadStudentFiles();
				//s.loadStudentApplied();
				//s.loadScholarshipGrant();
				//s.loadStudentGranted();
				//s.loadStudentAccepted();
				//s.loadStudentTermYear();
				
			}
		});
		lblViewGrantedScholarships.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblViewGrantedScholarships.setBounds(12, 252, 195, 16);
		panel_2.add(lblViewGrantedScholarships);
		
		JLabel lblApplyForScholarships = new JLabel("Apply For Scholarships");
		lblApplyForScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblApplyForScholarships.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblApplyForScholarships.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				scrollPane.setVisible(true);
				Start loading = new Start();
				loading.loadScholarships();
				loading.loadScholarshipApplicants();
				loading.loadStudents();
				loading.loadStudentFiles();
				loading.loadStudentApplied();
				loading.loadScholarshipGrant();
				loading.loadStudentGranted();
				loading.loadStudentAccepted();
				loading.loadStudentTermYear();
				Student lookUp = new Student();
				lookUp = loading.findStudent(a);
				studentApplyForScholarship apply= new studentApplyForScholarship(lookUp, loading);
				apply.setVisible(true);
			}
		});
		lblApplyForScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblApplyForScholarships.setBounds(12, 74, 155, 16);
		panel_2.add(lblApplyForScholarships);
		
		
		
		//JScrollPane sp = new JScrollPane(JScrollPane.VERTICAL);
		//contentPane.add(sp);
	}
}
/** REFERENCES
 * 
 * https://www.javatpoint.com/java-get-current-date
 * 
 * https://stories.uiowa.edu/sites/stories.uiowa.edu/files/2019_05_03-cap_and_gown_commercial_shoot_jatorner_-0255-web.jpg
 */
