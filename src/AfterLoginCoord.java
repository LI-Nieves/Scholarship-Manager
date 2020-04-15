package frontend;
import backend.*;

/**
 * @Author Kamalpreet Mundi, Lana Nieves, Edward Mah, Navjeet Hundal, Eric Wu
 * This JFrame has a single function, that is accessed once the Coordinator logs in.
 * It is basically the homepage for the Coordinator, allowing them to access and use 
 * their functions.
 * 
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.JTextField;

public class AfterLoginCoord extends JFrame {

	// Global variables for the Inteface
	private JPanel contentPane;
	private JTextField txtHi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AfterLoginCoord frame = new AfterLoginCoord("Kam");	// Used this for error Testing
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @param String a represents the Username of the Coordinator
	 * This Frame opens up Once a Coordinator has logged in. The Coordinator can pick from the following functions:
	 * 
	 * View all scholarships
	 * Remove scholarships
	 * View all Statistics
	 * View Student Profiles
	 * Remove Scholarship
	 * Grant Scholarships
	 * Add Scholarship
	 * Edit Scholarship
	 * 
	 * The Coordinator controls all these functions. A student will not be able to access these functions
	 * Since their login credentials leads to their own login screen
	 */
	public AfterLoginCoord(String a) {
		// Setting up overall frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 892, 676);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Setting up Saskatchewan image at the top left of the frame
		JLabel logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/images/rsz_usask.png")).getImage();
		logo.setIcon(new ImageIcon (img));
		logo.setBounds(0, 0, 179, 161);
		contentPane.add(logo);
		
		// Button for logging out
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	// When cLicked
				LogOut loggingOut = new LogOut();	// Open up another frame
				loggingOut.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnLogOut.setBounds(777, 0, 97, 25);
		contentPane.add(btnLogOut);
		
		// JPanel for formating
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(179, 0, 598, 25);
		contentPane.add(panel);
		
		// Label located at the top of the interface
		JLabel lblHelloWelcomeTo = new JLabel("Hello! Welcome to your Home page "+a);
		panel.add(lblHelloWelcomeTo);
		lblHelloWelcomeTo.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblHelloWelcomeTo.setBackground(new Color(204, 0, 153));
		
		// Panel for formating
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(0, 204, 51));
		panel_1.setBounds(179, 26, 695, 135);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		// Label for image located close to the top middle of the interface
		// Of Students holding/throwing their caps
		JLabel lblNewLabel = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/images/cap_gown.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon (img1));
		lblNewLabel.setBounds(0, 0, 695, 135);
		panel_1.add(lblNewLabel);
		
		

		// JPanel for formating		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 162, 179, 467);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		// Scroll Panel used to allow for scrolling inside the textPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 174, 673, 442);
		contentPane.add(scrollPane);
		
		// Initalizing the textPane
		JTextPane textPane = new JTextPane();
		
		// Label for view Scholarships
		JLabel lblViewScholarships = new JLabel("View Scholarships");
		lblViewScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblViewScholarships.setForeground(Color.CYAN);	// Mouse hovers over text, set colour to Cyan
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblViewScholarships.setForeground(Color.BLACK);	// Mouse exits text, set colour to black
			}
			@Override
			public void mouseClicked(MouseEvent e) {// Click on the label
				scrollPane.setVisible(true);	// Set Scroll Panel visible
				Start getScholarships = new Start();
				Student students = new Student();
				getScholarships.loadScholarships();	// Loading Scholarships
				String scholarshipInfo =students.viewScholarshipsGui(getScholarships.getAllScholarships());	// Get scholarships as a string
				textPane.setText(scholarshipInfo);	// Set text onto the Panel
				
			}
		});
		
		lblViewScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewScholarships.setBounds(12, 32, 155, 16);
		panel_2.add(lblViewScholarships);
		
		// View all statistics Label
		JLabel lblViewAllStatistics = new JLabel("View All Statistics");
		lblViewAllStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	// CLick on label
				Start s = new Start();	// Load all information
				s.loadScholarships();
				s.loadScholarshipApplicants();
				s.loadStudents();
				s.loadStudentFiles();
				s.loadStudentApplied();
				s.loadScholarshipGrant();
				s.loadStudentGranted();
				s.loadStudentAccepted();
				s.loadStudentTermYear();
				Coordinator cord = new Coordinator(a);
				String output=cord.viewAllStatisticsGui(s.getAllScholarships());	// Only coordinator can get results, thus use coordinator funciton
				textPane.setText(output);		// Set text to textPane
				scrollPane.setVisible(true);		// Show it
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblViewAllStatistics.setForeground(Color.CYAN);	// Mouse hovers over text, set colour to Cyan
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblViewAllStatistics.setForeground(Color.BLACK);		// Mouse exits text, set colour to black
			}
		});
		lblViewAllStatistics.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewAllStatistics.setBounds(12, 95, 155, 16);
		panel_2.add(lblViewAllStatistics);
		
		
		// Label to view Student Portfolios
		JLabel lblViewStudentProfiles = new JLabel("View Student Portfolios");
		lblViewStudentProfiles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblViewStudentProfiles.setForeground(Color.CYAN);	// Mouse hovers over text, Set Colour to Cyan
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblViewStudentProfiles.setForeground(Color.BLACK);	//Mouse leave text, set colour to black
			}
			@Override
			public void mouseClicked(MouseEvent c) {// Click on Text
				textPane.setText("Please enter a student username:\n");	// Ask for Student username, who's profile you would like to see
				txtHi = new JTextField();		// TextField for askng
				txtHi.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {	// Set up TextFiled when clicked on, making sure to clear everything from before
						txtHi.setForeground(Color.BLACK);
						txtHi.setText("");
					}
				});
				JButton btnSearch = new JButton("Search");	// Once search is pressed, we need to find the Student and get their portfolio
				btnSearch.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						textPane.setEditable(false);
						textPane.setCaretColor(textPane.getBackground());
						String search = txtHi.getText();	// Get student username
						Start loading = new Start();
						Student students = new Student();
						loading.loadStudents();				// Loading information
						loading.loadStudentFiles();
						students = loading.findStudent(search);		// Find the student
						textPane.setFont(new Font("Tahoma",Font.PLAIN,13));
						if(loading.checkStudentGui(search)) {	// If Student found
							String file = students.viewUploadedGui();			// Get his/her portfolio and show it
							String output = students.openFileGui(file);
							textPane.setText(students.getUsername()+"'s Portfolio:\n"+output);
						}
						else {		// Otherwise print an error message and ask for input again
							textPane.setFont(new Font("Tahoma", Font.BOLD, 13));
							textPane.setText("The student \"" + search + "\" does not exit. Please type in a different username:\n");
							//textPane.setFont(new Font("Tahoma",Font.PLAIN,13));
						}
						textPane.insertComponent(txtHi);
						textPane.insertComponent(btnSearch);
						scrollPane.setVisible(true);
					}
				});
				txtHi.setForeground(Color.GRAY);	// Ghost Text
				txtHi.setText("StudentUserName");
				textPane.insertComponent(txtHi);
				textPane.insertComponent(btnSearch);
				scrollPane.setVisible(true);
			}
		});
		lblViewStudentProfiles.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewStudentProfiles.setBounds(12, 161, 155, 16);
		panel_2.add(lblViewStudentProfiles);
		
		// Label for Remove Scholarship
		JLabel lblRemoveScholarships = new JLabel("Remove Scholarships");
		lblRemoveScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblRemoveScholarships.setForeground(Color.CYAN);	// Set to Cyan, when label Hovered
			}	
			@Override
			public void mouseExited(MouseEvent e) {
				lblRemoveScholarships.setForeground(Color.BLACK);	// Set to black, When label exited with mouse
			}
			@Override
			public void mouseClicked(MouseEvent e) {	// When label clicked get all scholarships that can be removed
				textPane.setText("Please click on the Scholarship you would like to remove:\n");
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				Start loading = new Start();	// Loading all the information
				loading.loadScholarships();
				loading.loadScholarshipApplicants();
				loading.loadStudents();
				loading.loadStudentFiles();
				loading.loadStudentApplied();
				loading.loadScholarshipGrant();
				loading.loadStudentGranted();
				loading.loadStudentAccepted();
				loading.loadStudentTermYear();
				Coordinator cord  = new Coordinator(a);
				String info = cord.getAllScholarshipsGui(loading.getAllScholarships());// Get all scholarships
				String[] splitting = info.split("\n");	// Split up the scholarships from the string
				for(String scholar: splitting ) {		// For each scholarship make a label that is clickable
					JLabel l=new JLabel(scholar);
					l.setFont(new Font("Times New Roman", Font.BOLD, 13));
					l.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {	// Set to Cyan
						l.setForeground(Color.CYAN);
					}
					@Override
					public void mouseExited(MouseEvent e) {		// Set to black
						l.setForeground(Color.BLACK);
					}
					@Override
					public void mouseClicked(MouseEvent e) {	// If click a scholarship you want to remove
						
						CoordinatorRemoveScholarship remove = new CoordinatorRemoveScholarship(cord,loading,scholar);	// Open another frame to confirm
						remove.setVisible(true);
					}
					});
					// For formating the TextPane
					Document doc = textPane.getDocument();
					try {
						doc.insertString(doc.getLength(), "\n", null);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textPane.insertComponent(l);

				}
				scrollPane.setVisible(true);
			}
		});
		lblRemoveScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRemoveScholarships.setBounds(12, 229, 155, 16);
		panel_2.add(lblRemoveScholarships);
		
		// Label for GrantScholarships
		JLabel lblGrantScholarships = new JLabel("Grant Scholarships");
		lblGrantScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGrantScholarships.setForeground(Color.CYAN);	// Mouse hovers over label, change colour to cyan
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblGrantScholarships.setForeground(Color.BLACK);	// Mouse exits over label, change back to black
			}
			@Override
			public void mouseClicked(MouseEvent e) {	// Click on label, ask for the Scholarship name
				textPane.setFont(new Font("Times New Roman", Font.BOLD, 14));
				textPane.setText("Please type in the scholarship name:\n");
				JTextField txtBox = new JTextField();				// Text field to input Scholarship name
				txtBox.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						txtBox.setForeground(Color.BLACK);
						txtBox.setText("");
					}
				});
				JButton btnSearch = new JButton("Search");
				btnSearch.addMouseListener(new MouseAdapter() {	// Once search is pressed, search for all students who applied for that scholarship
					@Override
					public void mouseClicked(MouseEvent e) {
						String input = txtBox.getText();	// Get scholarship name
						textPane.setText("The following students have applied for " +input +". Please click on them to accept: \n");
						textPane.setEditable(false);
						textPane.setCaretColor(textPane.getBackground());
						Start loading = new Start();	// Load all information
						loading.loadScholarships();
						loading.loadScholarshipApplicants();
						loading.loadStudents();
						loading.loadStudentFiles();
						loading.loadStudentApplied();
						loading.loadScholarshipGrant();
						loading.loadStudentGranted();
						loading.loadStudentAccepted();
						loading.loadStudentTermYear();
						textPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						Coordinator cord  = new Coordinator(a);
						
						String info ="";
						// Error checking to make sure a valid Scholarship is entered
						try {
						info = cord.grantScholarshipGui(loading.getAllScholarships(), loading.getAllStudents(), input); // Get names of all the Students who applied for that scholarship
						}
						catch(Exception E) {
							Document doc = textPane.getDocument();
							try {
								doc.insertString(doc.getLength(), "\nScholarship: "+ input+ " is not a valid Scholarship.", null);
							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (info.equals("There are no applicants to this scholarship.")) {
							JLabel l = new JLabel(info);
							l.setFont(new Font("Times New Roman", Font.BOLD, 15));
							// For formating TextPane purposes
							Document doc = textPane.getDocument();
							try {
								doc.insertString(doc.getLength(), "\n", null);
							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							textPane.insertComponent(l);
						}
						else {
							String[] splitting = info.split("\n");
							for(String student: splitting ) {	// Convert their names into clickable text, so that if clicked they can be granted the scholarship
								JLabel l=new JLabel(student);
								l.setFont(new Font("Times New Roman", Font.BOLD, 15));
								l.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent e) {
									l.setForeground(Color.CYAN);		// Hover over name, change colour to Cyan
								}
								@Override
								public void mouseExited(MouseEvent e) {	// Lave name, change back to Black
									l.setForeground(Color.BLACK);
								}
								@Override
								public void mouseClicked(MouseEvent e) {	// Click on name
									Scholarship scholar = new Scholarship();	
									scholar = cord.findScholarship(input, loading.getAllScholarships());	// Get the Scholarship
									
									CoordinatorGrantStudentScholarship grant = new CoordinatorGrantStudentScholarship(scholar, loading.getAllStudents(),student,loading,cord);
									grant.setVisible(true);	// Open another Frame to confirm their final result
									
								}
								});
								// For formating TextPane purposes
								Document doc = textPane.getDocument();
								try {
									doc.insertString(doc.getLength(), "\n", null);
								} catch (BadLocationException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								textPane.insertComponent(l);
	
							}
						}
					}
				});
				txtBox.setForeground(Color.LIGHT_GRAY);
				txtBox.setText("Scholarship name");
				textPane.insertComponent(txtBox);
				textPane.insertComponent(btnSearch);
				
				scrollPane.setVisible(true);
			}
			
		});
		lblGrantScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGrantScholarships.setBounds(12, 296, 138, 16);
		panel_2.add(lblGrantScholarships);
		
		//added in the edit Scholarship label and event mouse handlers.
		JLabel lblEditScholarships = new JLabel("Edit Scholarships");
		lblEditScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblEditScholarships.setForeground(Color.CYAN);	// Hover over label, change colour to Cyan
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEditScholarships.setForeground(Color.BLACK);	// Hover over label, change colour to black
			}
			@Override
			public void mouseClicked(MouseEvent e) {	// If Label clicked, want to show all scholarships, and then Coordinator picks the one they want to edit
				textPane.setText("Please click on the Scholarship you would like to edit:\n");
				textPane.setEditable(false);
				textPane.setCaretColor(textPane.getBackground());
				Start loading = new Start();		// Loading all information
				loading.loadScholarships();
				loading.loadScholarshipApplicants();
				loading.loadStudents();
				loading.loadStudentFiles();
				loading.loadStudentApplied();
				loading.loadScholarshipGrant();
				loading.loadStudentGranted();
				loading.loadStudentAccepted();
				loading.loadStudentTermYear();
				Coordinator cord  = new Coordinator(a);
				String info = cord.getAllScholarshipsGui(loading.getAllScholarships());	// Get string with all scholarships
				String[] splitting = info.split("\n");
				for(String scholar: splitting ) {		// Split up string, to get independent scholarships
					JLabel l=new JLabel(scholar);
					l.setFont(new Font("Times New Roman", Font.BOLD, 13));
					l.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						l.setForeground(Color.CYAN);			// Set to Cyan
					}
					@Override
					public void mouseExited(MouseEvent e) {
						l.setForeground(Color.BLACK);			// Set to Black
					}
					@Override
					public void mouseClicked(MouseEvent e) {	// Remove scholarship dates
						String getScholarName ="";
						for(int i=0 ;i<scholar.length();i++) {
							if(scholar.charAt(i)!='(') {
								getScholarName = getScholarName+scholar.charAt(i);
							}
							else {
								break;
							}
						}
						getScholarName = getScholarName.substring(0,getScholarName.length()-1);	// Remove space at the end
						Scholarship found= cord.findScholarship(getScholarName, loading.getAllScholarships());	// Find Scholarship
						CoordinatorEditScholarship edit = new CoordinatorEditScholarship(cord,loading,found);	// Open another panel to edit scholarship contents
						edit.setVisible(true);
						
					}
					});
					// For formating the textPane
					Document doc = textPane.getDocument();
					try {
						doc.insertString(doc.getLength(), "\n", null);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					textPane.insertComponent(l);

				}
				scrollPane.setVisible(true);
			}
		});
		lblEditScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblEditScholarships.setBounds(12, 425, 138, 18);
		panel_2.add(lblEditScholarships);
		//end of the additional stuff.
					
		scrollPane.setViewportView(textPane);
		scrollPane.setVisible(false);
		textPane.setEditable(false);
				
		//add scholarships
		//creating all the required gui components for add scholarship
		JLabel addScholar = new JLabel("Add Scholarship");
		addScholar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				addScholar.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addScholar.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setFont(new Font("Times New Roman", Font.BOLD, 14));
				textPane.setText("Please fill in the information with the correct types:\n");
				
				//take all the inputs as text fields
				JTextField nameOfScholarship = new JTextField();
				nameOfScholarship.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						nameOfScholarship.setForeground(Color.BLACK);
						nameOfScholarship.setText("");
					}
				});
				// Setting up all the textfields, and initalizing them
				JTextField money = new JTextField();
				money.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						money.setForeground(Color.BLACK);
						money.setText("");
					}
				});
			
				JTextField year = new JTextField();
				year.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						year.setForeground(Color.BLACK);
						year.setText("");
					}
				});
			
				JTextField GPA = new JTextField();
				GPA.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						GPA.setForeground(Color.BLACK);
						GPA.setText("");
					}
				});
			
				
				JTextField amount = new JTextField();
				amount.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						amount.setForeground(Color.BLACK);
						amount.setText("");
					}
				});
			
				JTextField dept = new JTextField();
				dept.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dept.setForeground(Color.BLACK);
						dept.setText("");
					}
				});
				
				JTextField wNeeded = new JTextField();
				wNeeded.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						wNeeded.setForeground(Color.BLACK);
						wNeeded.setText("");
					}
				});
			
				JTextField deg = new JTextField();
				deg.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						deg.setForeground(Color.BLACK);
						deg.setText("");
					}
				});
				
			
				JTextField uni = new JTextField();
				uni.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						uni.setForeground(Color.BLACK);
						uni.setText("");
					}
				});
				
			
				JTextField fac = new JTextField();
				fac.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						fac.setForeground(Color.BLACK);
						fac.setText("");
					}
				});
				
	
				JTextField crit = new JTextField();
				crit.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						crit.setForeground(Color.BLACK);
						crit.setText("");
					}
				});
				
				
				JTextField sem = new JTextField();
				sem.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						sem.setForeground(Color.BLACK);
						sem.setText("");
					}
				});
				
				JButton btnEnter = new JButton("Enter");
				
				//Once the user presses enter save the textfields to variables 
				btnEnter.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						String Name = nameOfScholarship.getText();
						String semester = sem.getText();
						int rec=0;
						int reward=0;
						int inputYear=0;
						double gpa=0;
						try {	// Getting values from textFields and parser them as integers, and GPA as a double
							rec = Integer.parseInt(amount.getText());	
							reward = Integer.parseInt(money.getText());
							inputYear = Integer.parseInt(year.getText());
							gpa = Double.parseDouble(GPA.getText()); 
						}
							
						catch(Exception E){
							Document doc = textPane.getDocument();
							String wrong = "\nPlease input the correct Values:\n Receive = int\n RewardAmount= int\n Year = int \n Gpa = double \n The rest are normal strings";
						
							try {
								doc.insertString(doc.getLength(), wrong, null);
							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						String w = wNeeded.getText();
						String department = dept.getText();
						String inputDeg = deg.getText();
						String inputUni = uni.getText();
						String inputFac = fac.getText();
						String inputCrit = crit.getText();
						
						//new objects to add the new scholarship
						Scholarship update = new Scholarship();
						Start setting = new Start();
						
						//load all current scholarships
						setting.loadScholarships();
						
						//update all the varaibles in Scholarship class
						
						update.setName(Name);
						update.setReceive(rec);
						update.setRewardAmount(reward);
						update.setYear(inputYear);
						update.setGPAreq(gpa);
						update.setWonTranscript(w);
						update.setDeptSpecific(department);
						update.setDegreeSpecific(inputDeg);
						update.setUniSpecific(inputUni);
						update.setFacultySpecific(inputFac);
						update.setCriteria(inputCrit);
						update.setSemester(semester);
						
						//add the new Scholarship into the list
						setting.addtoAllScholarships(update);
						
						setting.setAllScholarships(setting.getAllScholarships());
						
						//store to the database
						setting.storeScholarships();
						textPane.setText("Scholarship has been added");
					}
				});
				btnEnter.setFont(new Font("Times New Roman",Font.BOLD,13));
				
					
				//textfield setting
				GPA.setForeground(Color.LIGHT_GRAY);
				year.setForeground(Color.LIGHT_GRAY);
				money.setForeground(Color.LIGHT_GRAY);
				nameOfScholarship.setForeground(Color.LIGHT_GRAY);
				wNeeded.setForeground(Color.LIGHT_GRAY);
				dept.setForeground(Color.LIGHT_GRAY);
				uni.setForeground(Color.LIGHT_GRAY);
				fac.setForeground(Color.LIGHT_GRAY);
				crit.setForeground(Color.LIGHT_GRAY);
				deg.setForeground(Color.LIGHT_GRAY);
				sem.setForeground(Color.LIGHT_GRAY);
				amount.setForeground(Color.LIGHT_GRAY);
				
				GPA.setText("GPA");
				year.setText("Year");
				money.setText("Money Reward");
				amount.setText("Amount of students to receive");
				nameOfScholarship.setText("Name of scholarship");
				wNeeded.setText("true or false");
				dept.setText("Across all departments");
				uni.setText("Across all universities");
				fac.setText("Across all faculties");
				crit.setText("None");
				deg.setText("Across all degrees");
				sem.setText("Fall and Winter");
				
				//used to set text in text pane, simply formating the textPane
				Document doc = textPane.getDocument();
				
				try {
					doc.insertString(doc.getLength(), "Please type in the scholarship name: \n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
				textPane.insertComponent(nameOfScholarship);
				
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Please type in the reward amount: \n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textPane.insertComponent(money);
				
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Please type in the minimum GPA requirement: \n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textPane.insertComponent(GPA);
				
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Please type in the academic year the scholarship applies to: \n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textPane.insertComponent(year);
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Is a \"W\" allowed on the applicant's transcript? <true, false>\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textPane.insertComponent(wNeeded);
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Please type in the specific department the scholarship is for (leave empty if none):\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textPane.insertComponent(dept);
				// Formatting
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Please type in the specific university the scholarship is for (leave empty if none):\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textPane.insertComponent(uni);
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Please type in the specific faculty the scholarship is for (leave empty if none):\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textPane.insertComponent(fac);
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Please type in any extra criteria you have for the student (leave empty if none):\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textPane.insertComponent(crit);
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Please type in the specific degree the scholarship is for (leave empty if none):\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textPane.insertComponent(deg);
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Please type in the semester the scholarship will be for (leave empty if none): <Fall, Winter>\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textPane.insertComponent(sem);
				try {
					doc.insertString(doc.getLength(), "\n\n", null);
					doc.insertString(doc.getLength(), "Please type in the amount of students that will receive the scholarship: \n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textPane.insertComponent(amount);
				// Formatting
				try {
					
					doc.insertString(doc.getLength(), "\n\n", null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textPane.insertComponent(btnEnter);
				
				scrollPane.setVisible(true);
			}
				
		});
						
		addScholar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		addScholar.setBounds(12, 363, 155, 16);
		panel_2.add(addScholar);
	}
}
