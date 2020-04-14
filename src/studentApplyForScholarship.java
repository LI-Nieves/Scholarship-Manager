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
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class studentApplyForScholarship extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//coordinatorRemoveScholarship frame = new coordinatorRemoveScholarship();
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
	public studentApplyForScholarship(Student a, Start b) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setForeground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("What term would you like to apply for ? ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(102, 6, 274, 16);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(135, 35, 184, 27);
		comboBox.addItem("Fall");
		comboBox.addItem("Winter");
		comboBox.addItem("Fall and Winter");
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("What year would do like to apply for ?");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(102, 86, 265, 16);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(135, 114, 184, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblErrorMessage = new JLabel("");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(6, 210, 438, 41);
		contentPane.add(lblErrorMessage);
		//lblErrorMessage.setVisible(false);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedTerm = comboBox.getSelectedIndex();
				int selectedYear = Integer.parseInt(textField.getText());
				String returnMessage = a.chooseTermGui(a, b.getAllScholarships(), selectedTerm, selectedYear);
				if(returnMessage.equals("found"))
				{
					ArrayList<Scholarship> potentialScholar = a.getPotentialScholar(a, b.getAllScholarships(), selectedTerm, selectedYear);
					studentApplyForScholarship2 apply2 = new studentApplyForScholarship2(a,b,potentialScholar);
					dispose();
					apply2.setVisible(true);
				}
				else
				{
					lblErrorMessage.setText(returnMessage);
					
				}
				System.out.println(selectedTerm);
				System.out.println(selectedYear);
				System.out.println(returnMessage);
			}
		});
		btnNewButton.setBounds(135, 169, 184, 29);
		contentPane.add(btnNewButton);
		
		
		
		
		
		
	}
}
