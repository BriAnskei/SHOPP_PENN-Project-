package newds;

import java.awt.EventQueue;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;


public class code_login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private  static String accountId;
	private JTextField txtFirstname;
	private UserData userData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					code_login frame = new code_login();
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
	 
	public code_login() {
		 userData = new UserData();
		
		
		setBounds(100, 100, 380, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		Image m = new ImageIcon(this.getClass().getResource("/lala.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(m));
		lblNewLabel.setBounds(10, 11, 85, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblThank = new JLabel("Thank you for signing in!");
		lblThank.setForeground(Color.YELLOW);
		lblThank.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 15));
		lblThank.setBounds(193, 0, 171, 29);
		contentPane.add(lblThank);
		
		JLabel lblNewLabel_1 = new JLabel("This serves as your login code:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(125, 75, 147, 20);
		contentPane.add(lblNewLabel_1);


		
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setForeground(new Color(0, 0, 0));
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setText(accountId);
		textField.setBounds(268, 77, 86, 15);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		
		JLabel lblNewLabel_1_1 = new JLabel(" Please ensure its safekeeping and refrain from sharing it with others.");
		lblNewLabel_1_1.setForeground(new Color(255, 128, 128));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1_1.setBounds(51, 93, 313, 7);
		contentPane.add(lblNewLabel_1_1);
		
		Button button = new Button("Get ID");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtFirstname.getText();
				
				
				  System.out.println("name inputed in the login frame: " + txtFirstname.getText().toString());
				  try {
					System.out.println("Expected iD in idgenerator: " + userData.getAccountId(name));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				    try {
						accountId = String.valueOf(userData.getAccountId(name));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // Update accountId
				    textField.setText(accountId);
			}
		});
		button.setBounds(175, 31, 80, 20);
		contentPane.add(button);
		
		txtFirstname = new JTextField();
		txtFirstname.setBackground(new Color(255, 255, 255));
		txtFirstname.setForeground(new Color(0, 0, 0));
		txtFirstname.setEnabled(true);
		txtFirstname.setEditable(true);
		txtFirstname.addFocusListener(new FocusListener() {
			  @Override
	            public void focusGained(FocusEvent e) {
	                // Clear the default text when the user interacts with the JTextField
	                if (txtFirstname.getText().equals("FirstName")) {
	                	txtFirstname.setText("");
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                // Set the default text back if the user didn't enter anything
	                if (txtFirstname.getText().isEmpty()) {
	                	txtFirstname.setText("FirstName");
	                }
	            }
		});
		txtFirstname.setText("FirstName");
		txtFirstname.setColumns(10);
		txtFirstname.setBounds(268, 31, 86, 20);
		textField.setBackground(new Color(255, 255, 255));
		txtFirstname.setHorizontalAlignment(JTextField.CENTER);
		txtFirstname.setForeground(new Color(0, 0, 0));
		txtFirstname.setColumns(10);
		contentPane.add(txtFirstname);
		
		JLabel lblNewLabel_1_2 = new JLabel("Please enter the username you used during registration to get your ID.");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1_2.setBounds(43, 57, 354, 15);
		contentPane.add(lblNewLabel_1_2);
		
		Button button_1 = new Button("Login");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				login l = new login();
				l.setVisible(true);
			}
		});
		button_1.setForeground(Color.YELLOW);
		button_1.setFont(new Font("Dialog", Font.PLAIN, 9));
		button_1.setBackground(Color.BLACK);
		button_1.setBounds(62, 75, 57, 14);
		contentPane.add(button_1);
	}
}
