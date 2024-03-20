package newds;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;

public class Balance extends JFrame {

	private JPanel contentPane;
	private JTextField amount;
	private JTextField textField_1;
	
	private int Id;
	static int userid;

	
	public Balance(int Id) throws SQLException {
		this.Id = Id;
		
		userid = Id;
		
		
		MainClass();
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Balance frame = new Balance(userid);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void getBalance() throws SQLException {
		UserData userdata = new UserData();
		textField_1.setText(String.valueOf(userdata.getBalance(userid)));
	}

	
	public void MainClass() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 324, 248);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Icon = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Main.png")).getImage();
		Icon.setIcon(new ImageIcon(img));
		Icon.setBounds(25, 11, 57, 40);
		contentPane.add(Icon);
		
		JLabel lblAccountBalance = new JLabel("SHOPP");
		lblAccountBalance.setForeground(Color.GRAY);
		lblAccountBalance.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblAccountBalance.setBounds(77, 11, 57, 33);
		contentPane.add(lblAccountBalance);
		
		JLabel lblPenn = new JLabel("PENN");
		lblPenn.setForeground(Color.YELLOW);
		lblPenn.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblPenn.setBounds(133, 18, 57, 33);
		contentPane.add(lblPenn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ACCOUNT", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(25, 55, 257, 107);
		contentPane.add(panel_1);
		
		amount = new JTextField();
		amount.setHorizontalAlignment(SwingConstants.CENTER);
		amount.setColumns(10);
		amount.setBounds(147, 70, 86, 20);
		panel_1.add(amount);
		
		JLabel lblNewLabel_1_3_1_1_2 = new JLabel("Amount  to CASH IN:");
		lblNewLabel_1_3_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_3_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3_1_1_2.setBounds(10, 67, 127, 25);
		panel_1.add(lblNewLabel_1_3_1_1_2);
		
		JLabel lblTotalBalance = new JLabel("TOTAL BALANCE: ");
		lblTotalBalance.setForeground(Color.YELLOW);
		lblTotalBalance.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 12));
		lblTotalBalance.setBounds(40, 23, 119, 33);
		panel_1.add(lblTotalBalance);
		
		
		mainPanel main = new mainPanel(userid);
		textField_1 = new JTextField();
		textField_1.setText(main.TPayment("B"));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		textField_1.setBackground(Color.BLACK);
		textField_1.setBounds(138, 27, 76, 25);
		panel_1.add(textField_1);
		
		JButton btnNewButton_1 = new JButton("CASH IN");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(amount.getText() == null || amount.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter the total amount you'd like to top up.", "INPUT AMOUNT", JOptionPane.ERROR_MESSAGE);
				}else {
					double userAmount = Double.parseDouble(amount.getText());
					UserData userdata = new UserData();
					try {
						userdata.updateBalance(userid, userAmount, 'D');
						 JOptionPane.showMessageDialog(null, "Successfully added amount!", "Success", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally{
						try {
							textField_1.setText(main.TPayment("B"));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
		
			}
		});
		btnNewButton_1.setBounds(25, 173, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("BACK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainPanel main = null;
				try {
					main = new mainPanel(userid);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				main.getFrame().setVisible(true);
			        dispose();
				
			}
		});
		btnNewButton_2.setBounds(189, 173, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
