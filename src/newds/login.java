package newds;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JLabel lblNewLabel_1;
	private JPasswordField pass;
	private JLabel lblNewLabel_1_1;
	private Button button_1;
	private JLabel lblNewLabel;
	private JTextField code;
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					 try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						login frame = new login();
						frame.setVisible(true);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 379);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignIn = new JLabel("Sign in");
		lblSignIn.setForeground(Color.YELLOW);
		lblSignIn.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
		lblSignIn.setBounds(34, 190, 137, 29);
		contentPane.add(lblSignIn);
		
		JLabel lblNewLabel_1_4 = new JLabel("User: ");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setBounds(34, 229, 86, 14);
		contentPane.add(lblNewLabel_1_4);
		
		user = new JTextField();
		user.setColumns(10);
		user.setBounds(33, 244, 109, 20);
		contentPane.add(user);
		
		lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(166, 229, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		pass = new JPasswordField();
		pass.setColumns(10);
		pass.setBounds(166, 244, 109, 20);
		contentPane.add(pass);
		
		Button button = new Button("Sign in");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserData data = new UserData();
				
				
				
				String userName = user.getText();
				String passWord = pass.getText();
				int userCode = Integer.parseInt(code.getText().toString());
				try {
					boolean verified = data.loginVerification(userName, passWord, userCode);
					if(!verified) {
						JOptionPane.showMessageDialog(null, "Invalid input, incorrect username/password, or invalid code.", "ERROR", JOptionPane.ERROR_MESSAGE);
						user.setText("");
						pass.setText("");
						code.setText("");
						
					}else {
						
					
						mainPanel main = new mainPanel(userCode);
						main.getFrame().setVisible(true);
					        dispose();
						
						
					}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			
				
			}
		});
		button.setBounds(34, 281, 66, 29);
		contentPane.add(button);
		
		lblNewLabel_1_1 = new JLabel("Click here to create an account");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(166, 300, 127, 14);
		contentPane.add(lblNewLabel_1_1);
		
		button_1 = new Button("Sign up");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				sd s = new sd();
				s.setVisible(true);
			}
				
		});
		button_1.setFont(new Font("Dialog", Font.PLAIN, 9));
		button_1.setBackground(new Color(0, 0, 0));
		button_1.setForeground(new Color(255, 255, 0));
		button_1.setBounds(236, 315, 57, 14);
		contentPane.add(button_1);
		
		lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/loginpicc.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(35, 10, 240, 170);
		contentPane.add(lblNewLabel);
		
		code = new JTextField();
		code.setColumns(10);
		code.setBounds(166, 275, 109, 20);
		contentPane.add(code);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Code:");
		lblNewLabel_1_4_1.setForeground(Color.WHITE);
		lblNewLabel_1_4_1.setBounds(133, 275, 38, 20);
		contentPane.add(lblNewLabel_1_4_1);
	}
}
