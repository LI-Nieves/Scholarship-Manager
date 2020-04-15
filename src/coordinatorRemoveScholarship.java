package frontend;
import backend.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This class is used to create the GUI for the coordinator to remove scholarships from the database
 */
public class CoordinatorRemoveScholarship extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * main function
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CoordinatorRemoveScholarship frame = new CoordinatorRemoveScholarship();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * function used to create GUI components 
	 */
	public CoordinatorRemoveScholarship(Coordinator a, Start b, String scholar) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setForeground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//creating the labels
		JLabel lblWouldYouLike = new JLabel("Would you like to remove the Following Scholarship?");
		lblWouldYouLike.setForeground(Color.WHITE);
		lblWouldYouLike.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblWouldYouLike.setBounds(29, 36, 374, 16);
		contentPane.add(lblWouldYouLike);
		
		JLabel removedScholarship = new JLabel(scholar);
		removedScholarship.setForeground(Color.LIGHT_GRAY);
		removedScholarship.setFont(new Font("Tahoma", Font.BOLD, 14));
		removedScholarship.setBounds(67, 89, 302, 25);
		contentPane.add(removedScholarship);
		
		JLabel lblNewLabel = new JLabel("The Scholarship has Successfully been removed.");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(12, 216, 408, 16);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		//button creation
		JButton btnYes = new JButton("Yes");
		btnYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String getScholarName ="";
				//loop to get the name of scholarship that corrdinator inputs
				for(int i=0 ;i<scholar.length();i++) {
					if(scholar.charAt(i)!='(') {
						getScholarName = getScholarName+scholar.charAt(i);
					}
					else {
						break;
					}
				}
				
			
				getScholarName = getScholarName.substring(0,getScholarName.length()-1);
		        ArrayList<Scholarship> updated = new ArrayList<Scholarship>();
		        updated = b.getAllScholarships();
		        
		        //find the schoalrship in the database
				Scholarship scholSelected = a.findScholarship(getScholarName, b.getAllScholarships());
				if (scholSelected.getName() != null) {
					// if the scholarship has already been granted to students, it shouldn't be removed - that's messed up
					if (scholSelected.getGranted().size() > 0) {
						lblNewLabel.setText("This scholarship has already been granted. You cannot remove/edit it.");
					}
					// if students have already applied to the scholarship...
					else if (scholSelected.getApplicants().size() > 0) {
						lblNewLabel.setText("Students have already applied to this scholarship. You cannot remove/edit it.");
					}
					// otherwise...
					else {
						int count = 0;
						int indexOfSchol = 0;
						//loop to remove the scholarship from database
						for (Scholarship s : updated) {
							if (s.getName().equals(scholSelected.getName())) {
								s.remove();
								indexOfSchol = count;
							}
							count++;
						}
						//update the database
						updated.remove(indexOfSchol);
						lblNewLabel.setText(getScholarName + " has been removed.");
					}
				}
				else {
				}
			    b.setAllScholarships(updated);
				b.storeScholarships();
				
				lblNewLabel.setVisible(true);
			}
		});
		btnYes.setBounds(48, 178, 97, 25);
		contentPane.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNo.setBounds(287, 178, 97, 25);
		contentPane.add(btnNo);
	}
}
