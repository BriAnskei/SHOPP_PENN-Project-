package newds;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.SwingConstants;

public class mainPanel {

	private JFrame frame;
	private JTable table;
	private JTextField quantity;
	private JTextField price;
	private JTextField name;
	private JTextField id;
	private JTextField quantityEdit;
	private JButton save;
	
	private int  userid;
	
	static int LoggedID;
	static double totalPayment;
	
	private static NumberFormat nf = NumberFormat.getInstance();
	private static  NumberFormat nfUS = NumberFormat.getInstance(Locale.US);

	
	


	public mainPanel(int userid) throws SQLException {
		this.userid = userid;
		 LoggedID = userid;

		

			 //Initialize panel
			 initialize();
		
		
		//execute JDBC
		Connect();
		table_load();
	}
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainPanel window = new mainPanel(LoggedID);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	

	private JTextField textField;
	private JTextField textField_1;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	//Connection to MYSQL database using JDBC(Java Database Connectivity).
		public void Connect() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/shopp_penn", "root", "");
		}	catch(ClassNotFoundException ex) {
			ex.getMessage();
		}	catch(SQLException ex) {
			ex.getMessage();

			}
		}
		
		///MEthod for the jtable
		public void table_load() throws SQLException {
		    try {
		     
		        pst = con.prepareStatement("SELECT product_id, product_name, product_quantity, product_price FROM products WHERE user_id = ?");
		        pst.setInt(1, getLoggedUserID());
		        rs = pst.executeQuery();

		        String[] columnNames = {"ID", "NAME", "PRICE", "QUANTITY"};

		        // Convert ResultSet to DefaultTableModel with custom column names
		        DefaultTableModel model = new DefaultTableModel(null, columnNames);

		        if (rs.next()) {
		            do {
		                Object[] row = {
		                    rs.getInt("product_id"),
		                    rs.getString("product_name"),
		                    rs.getDouble("product_price"),
		                    rs.getInt("product_quantity")
		                };
		                model.addRow(row);
		            } while (rs.next());
		        }

		        // Set the model to your JTable
		        table.setModel(model);

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		    	updatePayment();
				 textField.setText(TPayment("P"));
				 totalPayment = totalPayment();
		    }
		}

	
    public JFrame getFrame() {
        return frame;
    }
    public void closeFrame() {
        frame.dispose();
    }

    
    
    public int getLoggedUserID() {
    	return userid;
    }


	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		
	
		
		
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 751, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setForeground(Color.WHITE);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),  new Color(160, 160, 160)), "ADD PRODUCT", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panel.setBounds(73, 87, 240, 230);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(75, 42, 22, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("NAME:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(59, 78, 38, 25);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("PRICE:");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(58, 114, 39, 25);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("QUANTITY:");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(34, 156, 68, 25);
		panel.add(lblNewLabel_1_3);
		
		quantity = new JTextField();
		quantity.setColumns(10);
		quantity.setBounds(101, 159, 86, 20);
		quantity.setHorizontalAlignment(JTextField.CENTER);
		panel.add(quantity);
		quantity.setColumns(10);
		
		price = new JTextField();
		price.setColumns(10);
		price.setHorizontalAlignment(JTextField.CENTER);
		price.setBounds(101, 117, 86, 20);
		panel.add(price);
		
		name = new JTextField();
		name.setColumns(10);
		name.setHorizontalAlignment(JTextField.CENTER);
		name.setBounds(101, 81, 86, 20);
		panel.add(name);
		
		id = new JTextField();
		id.setColumns(10);
		id.setHorizontalAlignment(JTextField.CENTER);
		id.setBounds(101, 45, 86, 20);
		panel.add(id);
		
		JButton add = new JButton("ADD");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int productId = Integer.parseInt(id.getText());
				String productName = name.getText();
				double productPrice = Double.parseDouble(price.getText());
				int productQuantity = Integer.parseInt(quantity.getText());
				
				double balance = 0;
				
				try {
					balance = getBalance();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println("This is the expected Id from this account: " + getLoggedUserID() + "Payment: " + totalPayment + "BBalance: " + balance + "Price: " + productPrice + 
						"Quantity: " + productQuantity);
				if (balance == 0) {
				    JOptionPane.showMessageDialog(null, "Your account currently holds no balance. Kindly deposit funds to facilitate product purchase.", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else if (totalPayment > balance || balance < (productPrice * productQuantity)) {
				    JOptionPane.showMessageDialog(null, "Your account balance is insufficient to add more products, or the price exceeds your current balance. Please consider topping up your balance to purchase the desired product.", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else if (balance >= totalPayment) {
				    try {
				        pst = con.prepareStatement("insert into products(user_id, product_id, product_name, product_price, product_quantity)values(?, ?, ?, ?, ?)");
				        pst.setInt(1, getLoggedUserID());
				        pst.setInt(2, productId);
				        pst.setString(3, productName);
				        pst.setDouble(4, productPrice);
				        pst.setInt(5, productQuantity);
				        pst.executeUpdate();
				      
				        // Load the data to the table
				        table_load();
				    } catch (SQLException e1) {
				        e1.printStackTrace();
				    }
				} else {
				    JOptionPane.showMessageDialog(null, "Please enter the product you'd like to add.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				// Reset fields
				id.setText("");
				name.setText("");
				price.setText("");
				quantity.setText("");

				
				
			}	
		});
		add.setBounds(37, 192, 80, 20);
		panel.add(add);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				price.setText("");
				quantity.setText("");
			}
		});
		btnClear.setBounds(133, 192, 80, 20);
		panel.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(384, 49, 319, 234);
		frame.getContentPane().add(scrollPane);
		

	
		
		
		table = new JTable();
		table.setBackground(Color.BLACK);
		table.getTableHeader().setBackground(Color.GRAY);
		table.setForeground(Color.WHITE);
		scrollPane.getViewport().setBackground(Color.BLACK);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("PRODUCT LIST");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		lblNewLabel.setBounds(384, 24, 115, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("EDIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int selectedRow = table.getSelectedRow();

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String bookID = model.getValueAt(selectedRow, 0).toString();
				if(selectedRow != -1) {
					quantityEdit.setEnabled(true);
					quantityEdit.setEditable(true);
					save.setEnabled(true);
					try {
						
						pst = con.prepareStatement("SELECT product_id, product_name , product_price, product_quantity FROM products WHERE product_id  = ?");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						pst.setString(1, bookID);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ResultSet rs = null;
					try {
						rs = pst.executeQuery();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						if(rs.next() == true) {
							String productId = rs.getString(1);
							String productName = rs.getString(2);
							String productPrice = rs.getString(3);
							String productQuantity = rs.getString(4);
							
							id.setText(productId);
							name.setText(productName);
							price.setText(productPrice);
							quantity.setText(productQuantity);
							id.setEditable(false);
							name.setEditable(false);
							price.setEditable(false);
							quantity.setEditable(false);
							add.setEnabled(false);
							btnClear.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnNewButton.setBounds(384, 294, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("REMOVE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int selectedRow = table.getSelectedRow();
				if(selectedRow != -1) {
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int bookID = (int) model.getValueAt(selectedRow, 0);
					String command = "DELETE FROM products WHERE product_id = ?";
					try {
						pst = con.prepareStatement(command);
						pst.setInt(1, bookID);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Deleted");
						//Update ScrollPane
						table_load();
						
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					
				
				}
			}
		});
		btnNewButton_1.setBounds(503, 294, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "EDIT PRODUCT", TitledBorder.LEADING, TitledBorder.TOP, null, Color.YELLOW));
		panel_1.setBounds(73, 342, 256, 62);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("QUANTITY:");
		lblNewLabel_1_3_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1.setBounds(10, 20, 68, 25);
		panel_1.add(lblNewLabel_1_3_1);
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		quantityEdit = new JTextField();
		quantityEdit.setEnabled(false);
		quantityEdit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				quantityEdit.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				 if (quantityEdit.getText().isEmpty()) {
					 quantityEdit.setText("EDIT");
	                }
			}
		});
		quantityEdit.setText("EDIT");
		quantityEdit.setHorizontalAlignment(JTextField.CENTER);
		quantityEdit.setColumns(10);
		quantityEdit.setBounds(78, 23, 86, 20);
		panel_1.add(quantityEdit);
		
		 save = new JButton("SAVE");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int updatedquantity;
				int productid; 
				
				productid = Integer.parseInt(id.getText());
				updatedquantity = 	Integer.parseInt(quantityEdit.getText());
				try {
					pst = con.prepareStatement("UPDATE products SET product_quantity=? WHERE product_id =?");
					pst.setInt(1, updatedquantity);
					pst.setInt(2, productid);
					
					int updated = pst.executeUpdate();
					if(updated > 0) {
			
						//run table
						table_load();
						id.setText("");
						name.setText("");
						price.setText("");
						quantity.setText("");
						
						boolean edit = true;
						id.setEditable(edit);
						name.setEditable(edit);
						price.setEditable(edit);
						quantity.setEditable(edit);
						
						
						add.setEnabled(edit);
						btnClear.setEnabled(edit);
						
						save.setEnabled(false);
						quantityEdit.setEditable(false);
					}
					
				
					
					
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				quantityEdit.setText("EDIT");
				quantityEdit.setEnabled(false);
				save.setEnabled(false);
			}
		});
		save.setEnabled(false);
		save.setBounds(171, 22, 75, 23);
		panel_1.add(save);
		
		JButton btnNewButton_2 = new JButton("PAYMENT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pay = null;
				try {
					pay = new Payment(LoggedID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pay.setVisible(true);
				closeFrame();
			
				
				
			
			}
		});
		btnNewButton_2.setBounds(614, 294, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel Icon = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Main.png")).getImage();
		Icon.setIcon(new ImageIcon(img));
		Icon.setBounds(35, 8, 57, 62);
		frame.getContentPane().add(Icon);
		
		JLabel LogoName = new JLabel("SHOPP");
		LogoName.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 37));
		LogoName.setForeground(Color.GRAY);
		LogoName.setBounds(109, 24, 115, 33);
		frame.getContentPane().add(LogoName);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLACK);
			textField.setText(TPayment("P"));
		
		textField.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 14));
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		textField.setBackground(Color.BLACK);
		textField.setBounds(634, 25, 69, 19);
		frame.getContentPane().add(textField);
		
		JLabel lblTotalPayment_1 = new JLabel("TOTAL:");
		lblTotalPayment_1.setForeground(Color.YELLOW);
		lblTotalPayment_1.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
		lblTotalPayment_1.setBounds(579, 24, 57, 20);
		frame.getContentPane().add(lblTotalPayment_1);
		
		JLabel lblPenn = new JLabel("PENN");
		lblPenn.setForeground(Color.YELLOW);
		lblPenn.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 37));
		lblPenn.setBounds(234, 23, 115, 33);
		frame.getContentPane().add(lblPenn);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ACCOUNT BALANCE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panel_1_1.setBackground(Color.BLACK);
		panel_1_1.setBounds(359, 342, 344, 62);
		frame.getContentPane().add(panel_1_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("BALANCE:");
		lblNewLabel_1_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3_1_1.setBounds(10, 20, 68, 25);
		panel_1_1.add(lblNewLabel_1_3_1_1);
		
		JButton save_1 = new JButton("CASH IN");
		save_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Balance user = null;
				try {
					user = new Balance(LoggedID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				user.setVisible(true);
				 closeFrame();
				
			}
		});
		save_1.setBounds(171, 22, 75, 23);
		panel_1_1.add(save_1);
		
		textField_1 = new JTextField();
		textField_1.setText(TPayment("B"));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 14));
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		textField_1.setBackground(Color.BLACK);
		textField_1.setBounds(74, 23, 87, 21);
		panel_1_1.add(textField_1);
		
		Button Logout = new Button("LOG OUT");
		Logout.setBounds(252, 22, 75, 23);
		panel_1_1.add(Logout);
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login log = new login();
				log.setVisible(true);
				LoggedID = 0;
				 closeFrame();
			}
		});
		Logout.setForeground(Color.BLACK);
		Logout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Logout.setBackground(Color.WHITE);
	}
	
	public void updatePayment() throws SQLException {
		UserData userdata = new UserData();
		userdata.calculateTotalPrice(LoggedID);
	}
	
	public double totalPayment() throws SQLException {
		UserData userdata = new UserData();
	    return userdata.calculateTotalPrice(LoggedID);
	}
	public String TPayment(String type) throws SQLException {
		
		switch(type){ 
		
			case "B":
				   return formatAmount(getBalance(), nf, nfUS);
			case "P":
				 return formatAmount(totalPayment(), nf, nfUS);
			default: 
		}
		return null;
	}
	private String formatAmount(double amount, NumberFormat nf, NumberFormat nfUS) {
	    if (amount <= 999) {
	        return nf.format(amount);
	    } else {
	        return nfUS.format(amount);
	    }
	}
	
	public double getBalance() throws SQLException {
		UserData userdata = new UserData();
	    return userdata.getBalance(LoggedID);
	}
}





