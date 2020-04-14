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

	private JPanel contentPane;
	private JTextField txtHi;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AfterLoginCoord frame = new AfterLoginCoord("Kam");
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
	public AfterLoginCoord(String a) {
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
			public void mouseClicked(MouseEvent e) {
				LogOut loggingOut = new LogOut();
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
		
		
		
		//txtHi = new JTextField();
		//txtHi.setText("hi");
		//txtHi.setBounds(112, 82, 193, 22);
		//panel_1.add(txtHi);
		//txtHi.setColumns(10);
		
		
		
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
		
		JLabel lblViewAllStatistics = new JLabel("View All Statistics");
		lblViewAllStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Start s = new Start();
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
				String output=cord.viewAllStatisticsGui(s.getAllScholarships());
				textPane.setText(output);
				scrollPane.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblViewAllStatistics.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblViewAllStatistics.setForeground(Color.BLACK);
			}
		});
		lblViewAllStatistics.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewAllStatistics.setBounds(12, 95, 155, 16);
		panel_2.add(lblViewAllStatistics);
		
		JLabel lblViewStudentProfiles = new JLabel("View Student Portfolios");
		lblViewStudentProfiles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblViewStudentProfiles.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblViewStudentProfiles.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent c) {
				 //final JTextField textFieldA = new HintTextField("A hint here");
				textPane.setText("Please enter a student username:\n");
				txtHi = new JTextField();
				txtHi.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						txtHi.setForeground(Color.BLACK);
						txtHi.setText("");
					}
				});
				JButton btnSearch = new JButton("Search");
				btnSearch.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						textPane.setEditable(false);
						textPane.setCaretColor(textPane.getBackground());
						String search = txtHi.getText();
						Start loading = new Start();
						Student students = new Student();
						loading.loadStudents();
						loading.loadStudentFiles();
						students = loading.findStudent(search);
						textPane.setFont(new Font("Tahoma",Font.PLAIN,13));
						if(loading.checkStudentGui(search)) {
							String file = students.viewUploadedGui();
							String output = students.openFileGui(file);
							textPane.setText(students.getUsername()+"'s Portfolio:\n"+output);
						}
						else {
							textPane.setFont(new Font("Tahoma", Font.BOLD, 13));
							textPane.setText("Student: "+"\"search\""+ " does not exit. Please type in a different username:\n");
							//textPane.setFont(new Font("Tahoma",Font.PLAIN,13));
						}
						textPane.insertComponent(txtHi);
						textPane.insertComponent(btnSearch);
						scrollPane.setVisible(true);
					}
				});
				//txtHi = new HintTextField("hi");
				txtHi.setForeground(Color.GRAY);
				txtHi.setText("StudentUserName");
				textPane.insertComponent(txtHi);
				textPane.insertComponent(btnSearch);
				scrollPane.setVisible(true);
			}
		});
		lblViewStudentProfiles.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewStudentProfiles.setBounds(12, 161, 155, 16);
		panel_2.add(lblViewStudentProfiles);
		
		JLabel lblRemoveScholarships = new JLabel("Remove Scholarships");
		lblRemoveScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblRemoveScholarships.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRemoveScholarships.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("Please click on the Scholarship you would like to remove:\n");
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
				Coordinator cord  = new Coordinator(a);
				 // = loading.findStudent(a);
				String info = cord.getAllScholarshipsGui(loading.getAllScholarships());
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
						
						coordinatorRemoveScholarship remove = new coordinatorRemoveScholarship(cord,loading,scholar);
						remove.setVisible(true);
						//studentAcceptGrantedScholarship(Student a,Start b, String scholarship )
						//Student lookUp = new Student();
						//lookUp = loading.findStudent(a);
						//studentAcceptGrantedScholarship accept= new studentAcceptGrantedScholarship(lookUp, loading, scholar);
						//accept.setVisible(true);
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
			}
		});
		lblRemoveScholarships.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRemoveScholarships.setBounds(12, 229, 155, 16);
		panel_2.add(lblRemoveScholarships);
		
		JLabel lblGrantScholarships = new JLabel("Grant Scholarships");
		lblGrantScholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGrantScholarships.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblGrantScholarships.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setFont(new Font("Times New Roman", Font.BOLD, 14));
				textPane.setText("Please type in the scholarship name:\n");
				JTextField txtBox = new JTextField();
				txtBox.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						txtBox.setForeground(Color.BLACK);
						txtBox.setText("");
					}
				});
				JButton btnSearch = new JButton("Search");
				btnSearch.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String input = txtBox.getText();
						System.out.println(input);
						textPane.setText("The Following Students have applied for " +input +". Please click on them to accept: \n");
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
						textPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						Coordinator cord  = new Coordinator(a);
						 //grantedInfo = loading.findStudent(a);
						String info = cord.grantScholarshipGui(loading.getAllScholarships(), loading.getAllStudents(), input);
						//textPane.setText(info);
						//scrollPane.setVisible(true);
						String[] splitting = info.split("\n");
						for(String student: splitting ) {
							JLabel l=new JLabel(student);
							l.setFont(new Font("Times New Roman", Font.BOLD, 15));
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
								//coordinatorGrantStudentScholarship(Scholarship a , ArrayList<Student> b , String studentName, Start c, Coordinator d )
								Scholarship scholar = new Scholarship();
								scholar = cord.findScholarship(input, loading.getAllScholarships());
								
								coordinatorGrantStudentScholarship grant = new coordinatorGrantStudentScholarship(scholar, loading.getAllStudents(),student,loading,cord);
								grant.setVisible(true);
								//studentAcceptGrantedScholarship(Student a,Start b, String scholarship )
								//Student lookUp = new Student();
								//lookUp = loading.findStudent(a);
								//studentAcceptGrantedScholarship accept= new studentAcceptGrantedScholarship(lookUp, loading, scholar);
								//accept.setVisible(true);
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
			
		scrollPane.setViewportView(textPane);
		scrollPane.setVisible(false);
		textPane.setEditable(false);
	}
}
