package newds;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollBar;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class sd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_1_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<Object> comboBox_1;
	
	 static String[] maritalStatusOptions, genDer;
	
	 

	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					sd frame = new sd();
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
	public sd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 295);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(278, 78, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sign up");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
		lblNewLabel.setBounds(278, 21, 137, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(278, 60, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Middle name:");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(383, 61, 86, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(383, 78, 86, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(490, 78, 86, 20);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Last Name:");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setBounds(491, 61, 85, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Age:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setBounds(278, 115, 29, 14);
		contentPane.add(lblNewLabel_1_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(278, 132, 86, 20);
		contentPane.add(textField_3);
		
		lblNewLabel_1_3 = new JLabel("Status: ");
		lblNewLabel_1_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_3.setBounds(383, 115, 86, 14);
		contentPane.add(lblNewLabel_1_3);
		
		 maritalStatusOptions = new String[]{"", "Single", "Married"};
	        JComboBox<Object> comboBox = new JComboBox<Object>();
	        comboBox.setBackground(new Color(128, 128, 128));
	        comboBox.setModel(new DefaultComboBoxModel<Object>(maritalStatusOptions));
	        comboBox.setBounds(383, 132, 109, 18);
	        contentPane.add(comboBox);		
	       
		
		Button button = new Button("CREATE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			String  USER , PASSWORD, FIRST_NAME , MID_NAME, LAST_NAME, S_AGE, STATUS, SEX;
				// USER, PASSWORD, FIRST_NAME, MID_NAME, LAST_NAME, AGE, STATUS;
				int indexGender = comboBox_1.getSelectedIndex();
				int indexSTatus = comboBox.getSelectedIndex();
					
				FIRST_NAME = textField.getText();
				MID_NAME = textField_1.getText();
				LAST_NAME = textField_2.getText();
				S_AGE = textField_3.getText();
				USER = textField_4.getText();
				PASSWORD = textField_5.getText();
				
				if(FIRST_NAME.equals("") && MID_NAME.equals("") && LAST_NAME.equals("") && S_AGE.equals("") && USER.equals("") && PASSWORD.equals("") && indexGender == 0 && indexSTatus ==0)
					JOptionPane.showMessageDialog(null, "Please fill in your info", "Error", JOptionPane.ERROR_MESSAGE);
				
				else if(FIRST_NAME.equals("") || MID_NAME.equals("") || LAST_NAME.equals("") || S_AGE.equals("") || indexGender == 0 || indexSTatus ==0)
					JOptionPane.showMessageDialog(null, "Please complete your info", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
				
				else if(USER.equals("") || PASSWORD.equals(""))
					JOptionPane.showMessageDialog(null, "Input your username/password", "Error", JOptionPane.INFORMATION_MESSAGE);
				else {
					
					
					
					
					  textField.setText("");
			            textField_1.setText("");
			            textField_2.setText("");
			            textField_3.setText("");
			            textField_4.setText("");
			            textField_5.setText("");
			            comboBox.setSelectedIndex(0);
					


			           
			            String staTus = (indexSTatus > 0) ? maritalStatusOptions[indexSTatus] : null;
			         String Gender = (indexGender > 0) ?  genDer[indexGender] : null;
					SEX = Gender;
					STATUS = staTus;
					
					
					
					int AGE = 0;
					
					try {
						AGE = Integer.valueOf(S_AGE);
						System.out.println("convert successfull");
						
					} catch (NumberFormatException nfe) {
						System.err.println("Eror converting: " + nfe);
					}

					UserData userData = new UserData();
				
					try {
						
						//change this into dowhile
						boolean duplicateDetected = 	userData.addAccount(FIRST_NAME, MID_NAME, LAST_NAME, AGE, SEX, STATUS, USER, PASSWORD);
						if(duplicateDetected) {
							System.out.println("Duplicate name detected");
							JOptionPane.showMessageDialog(null, "Duplicate name detected", "Error", JOptionPane.ERROR_MESSAGE);
							
					           
					            button.setEnabled(true);
								boolean disable = false;
								 textField.setText(FIRST_NAME);
								 textField_1.setText(MID_NAME);
						            textField_2.setText(LAST_NAME);
						            textField_3.setText(String.valueOf(AGE));
						            textField_4.setText("");
						            textField_5.setText(PASSWORD);
						            comboBox.setSelectedIndex(indexSTatus);
						            comboBox_1.setSelectedIndex(indexGender);  
						            
						            textField_1.setEditable(disable);
						            textField_2.setEditable(disable);
						            textField_3.setEditable(disable);
						          
						            textField_5.setEditable(disable);
						            
						            String updatedUSername = textField.getText();
						           
						            try {
										userData.addAccount(updatedUSername, MID_NAME, LAST_NAME, AGE, SEX, STATUS, USER, PASSWORD);
										System.out.println("Succesfully updated the user_username");
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
						            
						}else {
							code_login login = new code_login();
							login.setVisible(true);
							dispose();
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
				
				
				}
			}
		});
		button.setBounds(302, 220, 80, 20);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/haha.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(10, 11, 250, 234);
		contentPane.add(label);
		
		
		
		JLabel lblNewLabel_1_4 = new JLabel("User: ");
		lblNewLabel_1_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_4.setBounds(278, 170, 86, 14);
		contentPane.add(lblNewLabel_1_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(278, 187, 109, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(429, 187, 109, 20);
		contentPane.add(textField_5);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Password:");
		lblNewLabel_1_4_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_4_1.setBounds(429, 170, 86, 14);
		contentPane.add(lblNewLabel_1_4_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		Image m = new ImageIcon(this.getClass().getResource("/lala.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(m));
		
		lblNewLabel_2.setBounds(475, 11, 85, 40);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Sex");
		lblNewLabel_1_3_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_3_1.setBounds(517, 115, 59, 14);
		contentPane.add(lblNewLabel_1_3_1);
		
		
		genDer = new String[] {"", "F", "M", "other"};
		comboBox_1 = new JComboBox<Object>();
		comboBox_1.setModel(new DefaultComboBoxModel<Object>(genDer));
		comboBox_1.setBackground(Color.GRAY);
		comboBox_1.setBounds(517, 132, 39, 18);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Click here to Sign in");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1_2.setBounds(420, 225, 77, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		Button button_1 = new Button("Sign in");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				login l = new login();
				l.setVisible(true);
			}
		});
		button_1.setActionCommand("Sign in");
		button_1.setForeground(Color.YELLOW);
		button_1.setFont(new Font("Dialog", Font.PLAIN, 9));
		button_1.setBackground(Color.BLACK);
		button_1.setBounds(503, 225, 57, 14);
		contentPane.add(button_1);
	}
	
}
