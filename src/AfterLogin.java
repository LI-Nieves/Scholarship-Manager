/**
 * @author Kamalpreet Mundi, Lana Nieves, Edward Mah, Eric Wu, Navjeet Hundal
 * 
 * This program has a single function, used for after a student logins.
 * Once the student has logged in, they can use all the functions they need to allow them to apply
 * view scholarships, as well as edit scholarships, and accepting scholarships.
 * It is the HomePage for the Student
 */
package frontend;
import backend.*;

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

import com.sun.glass.ui.Cursor;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	 * @param String a: This string is the username of the student who has logged in. We need this since we associate this based on Username.
	 * This is the frame that opens up once a student has logged in. The student has multiple options they can pick from:
	 * Apply for scholarship
	 * View all schoalrships
	 * View Portfolio
	 * Edit Portfolio
	 * Applied Scholarships
	 * View Granted Scholarships
	 * 
	 */
	public AfterLogin(String a) {
		// Setting up the frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 892, 676);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Setting the image for the Saskatchewan logo at the top left
		JLabel logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/images/rsz_usask.png")).getImage();
		logo.setIcon(new ImageIcon (img));
		logo.setBounds(0, 0, 179, 161);
		contentPane.add(logo);
		
		// Setting up the logout button
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LogOut loggingOut =  new LogOut();
				loggingOut.setVisible(true);	// Open up new Frame
			}
		});
		btnLogOut.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnLogOut.setBounds(777, 0, 97, 25);
		contentPane.add(btnLogOut);
		
		// Setting up  a Panel
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(179, 0, 598, 25);
		contentPane.add(panel);
		
		// Label located at the very top
		JLabel lblHelloWelcomeTo = new JLabel("Hello! Welcome to your Home page "+a);
		panel.add(lblHelloWelcomeTo);
		lblHelloWelcomeTo.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblHelloWelcomeTo.setBackground(new Color(204, 0, 153));
		
		// ANother JPanel (JPanels just used to help format the page)
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(0, 204, 51));
		panel_1.setBounds(179, 26, 695, 135);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		// Date located at bottom left for students
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		// Label for image located at the top middle portion of the interface
		JLabel lblNewLabel = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/images/cap_gown.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon (img1));
		lblNewLabel.setBounds(0, 0, 695, 135);
		panel_1.add(lblNewLabel);
		
		
		// Another JPanel for formatting purposes
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 162, 179, 467);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 174, 673, 442);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();	// THE TEXT PANE, THIS PANEL IS VERY IMPORTANT
			
		scrollPane.setViewportView(textPane);		//Textpane is inside scroll Panel
		scrollPane.setVisible(false);			// TextPane only visible if Scroll pane is visible
		textPane.setEditable(false);
		
		// Label for View Scholarships
		JLabel lblViewScholarships = new JLabel("View Scholarships");
		lblViewScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblViewScholarships.setForeground(Color.CYAN);	// When mouse hovers over text change colour to cyan
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblViewScholarships.setForeground(Color.BLACK);		// When mouse exits text change colour to black
			}
			@Override
			public void mouseClicked(MouseEvent e) {	// If clicked, we change textPane to show all scholarships
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				scrollPane.setVisible(true);
				Start getScholarships = new Start();
				Student students = new Student();
				getScholarships.loadScholarships();
				String scholarshipInfo =students.viewScholarshipsGui(getScholarships.getAllScholarships());	// Getting the whole string for all scholarships
				textPane.setText(scholarshipInfo);	// Setting the text of the string onto the textPane
			}
		});
		
		
		//l.setCursor(new Cursor(Cursor.CURSOR_CLOSED_HAND));
		
		lblViewScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewScholarships.setBounds(12, 32, 155, 16);
		panel_2.add(lblViewScholarships);
		
		// Label for Applied scholarships
		JLabel lblAppliedScholarships = new JLabel("Applied Scholarships");
		lblAppliedScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAppliedScholarships.setForeground(Color.CYAN);	// Mouse hovers over text, change colour to Cyan
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAppliedScholarships.setForeground(Color.BLACK);	// Mouse exits text, change colour to black
			}
			@Override
			public void mouseClicked(MouseEvent e) {	// If Clicked
				//textPane.setText("You have applied to the following Scholarships:");
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				Student student = new Student();	// Initalizing Student
				Start loading = new Start();	// Initializing start, since we need to load all information from start first
				loading.loadStudents();			
				loading.loadStudentApplied();
				student =loading.findStudent(a);	// Get the Student
				String scholarships=student.viewMyScholarshipsGui();	// Retrieve string for scholarships student applied to
				textPane.setText(scholarships);		// Set text to String
				scrollPane.setVisible(true);		// Show it
			}
		});
			
		lblAppliedScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAppliedScholarships.setBounds(12, 166, 155, 16);
		panel_2.add(lblAppliedScholarships);
		
		// Label for View Portfolio
		JLabel lblViewPortfolio = new JLabel("View Portfolio");
		lblViewPortfolio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblViewPortfolio.setForeground(Color.CYAN);	// Mouse hovers over text, change colour to Cyan
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblViewPortfolio.setForeground(Color.BLACK);	// Mouse exits text, change colour to black
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {	// Click on label
				textPane.setEditable(false);	
				textPane.setCaretColor(textPane.getBackground());
				Start loading = new Start();
				Student students = new Student();
				loading.loadStudents();		// Load info
				loading.loadStudentFiles();		// Load info
				students = loading.findStudent(a);	// Find student
				String file = students.viewUploadedGui();	// Get studentsfile name
				String output = students.openFileGui(file);	//	Open file and get information
				textPane.setText(output);		// Set text onto panel
				scrollPane.setVisible(true);	// Make panel visible so we can see it
			}
		});
		lblViewPortfolio.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewPortfolio.setBounds(12, 247, 155, 16);
		panel_2.add(lblViewPortfolio);
		
		// Label for Edit Portfolio
		JLabel lblEditPortfolio = new JLabel("Edit Portfolio");
		lblEditPortfolio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEditPortfolio.setForeground(Color.CYAN);	// Mouse hovers over label, change colour to Cyan
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEditPortfolio.setForeground(Color.BLACK);	// Mouse exits over label, change colour to black
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Start loading = new Start();
				Student students = new Student();
				loading.loadStudents();		// Load	Students
				loading.loadStudentFiles();	// Load Students File
				students = loading.findStudent(a);		// Find Student (remember a is the username)
				String file = students.viewUploadedGui();	// Get name of students portfolio
				String output = students.openFileGui(file);		// Get information from file
				textPane.setText(output);				// Set text to portfolio
				textPane.setEditable(true);
				textPane.setCaretColor(null);
				JButton btnSave = new JButton("Save");
				btnSave.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {	// If press on save button, simply update the information of the students portfolio
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
		lblEditPortfolio.setBounds(12, 333, 155, 16);
		panel_2.add(lblEditPortfolio);
		
		// JPanel for formating
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 441, 143, 26);
		panel_2.add(panel_3);
		JLabel TIME = new JLabel(dtf.format(now));
		panel_3.add(TIME);
		
		//Label for view granted Scholarships
		JLabel lblViewGrantedScholarships = new JLabel("View Granted Scholarships");
		lblViewGrantedScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblViewGrantedScholarships.setForeground(Color.CYAN);	// Mouse hovers over text, change colour to Cyan
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblViewGrantedScholarships.setForeground(Color.BLACK);	// Mouse exits text, change colour back to black
			}	
			@Override
			public void mouseClicked(MouseEvent e) {	// If Label is clicked want the user to view granted scholarships, and accept whichever scholarship they want
				textPane.setText("You have been Granted the following Scholarships. Please click on them to accept: \n");
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				Start loading = new Start();
				// Loading information
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
				grantedInfo = loading.findStudent(a);	// Fetch the student
				String info = grantedInfo.viewGrantedGui(loading.getAllScholarships());	
				
				
				String[] splitting = info.split("\n");
				for(String scholar: splitting ) {	// Setting up jLabels for each scholarship, so that they are clickable
					JLabel l=new JLabel(scholar);
					l.setFont(new Font("Times New Roman", Font.BOLD, 13));
					l.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {	// When hover over Jlabel, change colour to cyan
						l.setForeground(Color.CYAN);
					}
					@Override
					public void mouseExited(MouseEvent e) {	// When exit JLabel, change colour to black
						l.setForeground(Color.BLACK);
					}
					@Override
					public void mouseClicked(MouseEvent e) {	// When user clicks on a granted scholarship, as if they want to accept it
						
						Student lookUp = new Student();
						lookUp = loading.findStudent(a);
						StudentAcceptGrantedScholarship accept= new StudentAcceptGrantedScholarship(lookUp, loading, scholar);	// OPen another jframe to ask
						accept.setVisible(true);
					}
					});
					// Formating TextPane
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
				
				
				
			}
		});
		lblViewGrantedScholarships.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblViewGrantedScholarships.setBounds(12, 412, 167, 16);
		panel_2.add(lblViewGrantedScholarships);
		
		// Label for apply for scholarships
		JLabel lblApplyForScholarships = new JLabel("Apply For Scholarships");
		lblApplyForScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblApplyForScholarships.setForeground(Color.CYAN);	// Mouse hovers over text, change colour to Cyan
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblApplyForScholarships.setForeground(Color.BLACK);	// Mouse exits label, change colour back to black
			}
			@Override
			public void mouseClicked(MouseEvent e) {	// Click on Apply for Scholarships
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				scrollPane.setVisible(true);
				Start loading = new Start();	// Loading information
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
				lookUp = loading.findStudent(a);	// Find Student
				StudentApplyForScholarship apply= new StudentApplyForScholarship(lookUp, loading);	// Open up another frame for them to apply
				apply.setVisible(true);
			}
		});
		lblApplyForScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblApplyForScholarships.setBounds(12, 91, 155, 16);
		panel_2.add(lblApplyForScholarships);
		
		
		
		
		
		
		
		
	}
	
	
}



/** REFERENCES
 * 
 * https://www.javatpoint.com/java-get-current-date
 * 
 * https://stories.uiowa.edu/sites/stories.uiowa.edu/files/2019_05_03-cap_and_gown_commercial_shoot_jatorner_-0255-web.jpg
 * https://stackoverflow.com/questions/14735085/clicking-a-jlabel-to-open-a-new-frame
 * https://stackoverflow.com/questions/31928306/how-to-create-and-use-a-jtextpane
 */
