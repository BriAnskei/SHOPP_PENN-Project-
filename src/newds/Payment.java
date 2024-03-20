package newds;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class Payment extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JLabel lblTotalPayment;
	private JTextField userId;
	private JTextField Payment;
	
	private int userid;
	static int LoggedID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment(LoggedID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Payment(int userid
			) throws SQLException {
		this.userid = userid;
		 LoggedID = userid;
		 initialize();
	}
	
	
	private void initialize() throws SQLException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 280);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
	
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(JTextField.CENTER);
		textField_1.setText(totalPayment());
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		textField_1.setBackground(Color.BLACK);
		textField_1.setEditable(false);
		textField_1.setEnabled(false);
		textField_1.setForeground(Color.BLACK);
		textField_1.setColumns(10);
		textField_1.setBounds(248, 15, 76, 33);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("PAY");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserData userData = new UserData();
				mainPanel main = null;

				String userIdInput = userId.getText();
				String paymentInput = Payment.getText();

				boolean emptyId = userIdInput == null || userIdInput.isEmpty();
				boolean emptyAmount = paymentInput == null || paymentInput.isEmpty();

				if (emptyId || emptyAmount) {
				    JOptionPane.showMessageDialog(null, "Apologies, there seems to be an ERROR. Please enter the payment amount or ensure that you've input a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
				    try {
				        double payment = Double.parseDouble(paymentInput);
				        main = new mainPanel(LoggedID);
				        double totalPayment = main.totalPayment();
				        double accountBalance = main.getBalance();
				       

				        if (totalPayment > payment || accountBalance < payment) {
				            JOptionPane.showMessageDialog(null, "Your account balance is insufficient to cover the payment you entered, or the PAYMENT intput is not enough to pay the total Payment.", "Error", JOptionPane.ERROR_MESSAGE);
				        }else if(payment > totalPayment) {
				        	JOptionPane.showMessageDialog(null, "Invalid payment input. Please enter the correct total payment amount.", "Error", JOptionPane.ERROR_MESSAGE);
				        }else {
				            int userIdValue = Integer.parseInt(userIdInput);
				            boolean verified = userData.paymentVerification(payment, userIdValue);
				            if (verified) {
				                userData.updateBalance(userIdValue, payment, 'P');
				                JOptionPane.showMessageDialog(null, "Your payment has been successfully processed. We deeply appreciate your choice to trust us.", "Success", JOptionPane.INFORMATION_MESSAGE);
				               
								try {
									main = new mainPanel(LoggedID);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								main.getFrame().setVisible(true);
							        dispose();
							
				            } else {
				                JOptionPane.showMessageDialog(null, "Error in Payment. Incorrect Account ID or insufficient payment input", "Error", JOptionPane.ERROR_MESSAGE);
				            }
				        }
				    } catch (NumberFormatException e2) {
				        JOptionPane.showMessageDialog(null, "Invalid payment amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
				    } catch (SQLException e1) {
				        JOptionPane.showMessageDialog(null, "An error occurred while processing your request. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
				        e1.printStackTrace();
				    } 
				}

				
			}
		});
		btnNewButton.setBounds(26, 207, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel main = null;
				try {
					main = new mainPanel(LoggedID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				main.getFrame().setVisible(true);
			        dispose();
			}
		});
		btnNewButton_1.setBounds(212, 207, 89, 23);
		contentPane.add(btnNewButton_1);
		
		lblTotalPayment = new JLabel("Total Payment");
		lblTotalPayment.setForeground(Color.YELLOW);
		lblTotalPayment.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblTotalPayment.setBounds(131, 13, 119, 33);
		contentPane.add(lblTotalPayment);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "PAYMENT", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(39, 77, 256, 108);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Account ID: ");
		lblNewLabel_1_3_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3_1.setBounds(25, 20, 70, 25);
		panel_1.add(lblNewLabel_1_3_1);
		
		userId = new JTextField();
		userId.setHorizontalAlignment(SwingConstants.CENTER);
		userId.setColumns(10);
		userId.setBounds(137, 23, 86, 20);
		panel_1.add(userId);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Payment:");
		lblNewLabel_1_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3_1_1.setBounds(25, 61, 70, 25);
		panel_1.add(lblNewLabel_1_3_1_1);
		
		Payment = new JTextField();
		Payment.setHorizontalAlignment(SwingConstants.CENTER);
		Payment.setColumns(10);
		Payment.setBounds(137, 64, 86, 20);
		panel_1.add(Payment);
		
		JLabel Icon = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Main.png")).getImage();
		Icon.setIcon(new ImageIcon(img));
		Icon.setBounds(26, 11, 57, 62);
		contentPane.add(Icon);
	}
	
	public String totalPayment() throws SQLException {
		UserData userdata = new UserData();
		 NumberFormat numberFormat = NumberFormat.getInstance();
	    	return numberFormat.format(userdata.calculateTotalPrice(LoggedID));
		
	}
}
