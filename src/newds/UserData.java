package newds;



import java.sql.*;
import java.text.NumberFormat;
import java.util.Random;

import javax.swing.JOptionPane;


class UserData {
	 private static final String DATABASE_URL = "jdbc:mysql://localhost/shopp_penn";
	 private static final String DATABASE_USER = "root";
	 
	 private int loggedInId;
	 
	
	
	static boolean dupliation;
    PreparedStatement preparedStatement;
    Connection connection;
    ResultSet result;
    
    
  

   static {
       try {
           Class.forName("com.mysql.jdbc.Driver");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
   }
   
   public void setLoggedInID(int loggedInId) {
	   this.loggedInId = loggedInId;
   }
   
   public void printLoggedId() {
	   System.out.println("Active ID: " + loggedInId);
   }
 
    
   public boolean  addAccount(String user_firstName, String user_middleName, String user_lastName, int user_Age, String user_sex, String user_Status, String user_username, String user_Password) throws SQLException {
	   Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, "");
	    int expectedId = 0;
	   

	    String query =  "INSERT INTO users (user_id, user_firstName, user_middleName, user_lastName, user_Age, user_sex, user_Status, user_username, user_Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query ,Statement.RETURN_GENERATED_KEYS)) {
	    	  int accountId = generateAccountId();
	            preparedStatement.setInt(1, accountId);
	            preparedStatement.setString(2, user_firstName);
	            preparedStatement.setString(3, user_middleName);
	            preparedStatement.setString(4, user_lastName);
	            preparedStatement.setInt(5, user_Age);
	            preparedStatement.setString(6, user_sex);
	            preparedStatement.setString(7, user_Status);
	            preparedStatement.setString(8, user_username);
	            preparedStatement.setString(9, user_Password);  

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {       
                System.out.println("Data successfully inserted into the table.");
                expectedId = getAccountId(user_username);
                addUserAccount(expectedId, user_username, user_Password, 0.0);
                System.out.println("Expected id: " + expectedId);
            } else {
                System.out.println("Error inserting data into the table.");
            }
        }catch (SQLException e) {
            // Handle the UNIQUE constraint violation error
        	if (e.getErrorCode() == 1062) { // MySQL error code for duplicate entry
        		String error = e.getMessage();
        		if(error.contains("user_id "))
        			addAccount( user_firstName, user_middleName, user_lastName, user_Age,user_sex, user_Status, user_username, user_Password);
        		//Generate a new Id recursively 
        		
        			// return true if the condition is correct(Duplicate Username)
        			return error.contains("user_username");
        	}

        }finally {
            // Close resources in the finally block
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
          
            
            
        }
	    //default false
	 return false;
    }
   
   public void addUserAccount(int id, String username, String password, double balance) throws SQLException {
	   String addUserAccount = "INSERT INTO user_accounts (account_id, username, password, account_balance) VALUES (?, ?, ?, ?)";
	   Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, "");
	   try{PreparedStatement preparedStatement = connection.prepareStatement(addUserAccount ,Statement.RETURN_GENERATED_KEYS);
	   preparedStatement.setInt(1, id);
	   preparedStatement.setString(2, username);
	   preparedStatement.setString(3, password);
	   preparedStatement.setDouble(4, balance);
	    int rowsAffected = preparedStatement.executeUpdate();
	    if(rowsAffected > 0) {
	    	System.out.println("user_accoutn added");
	    }else {
	    	System.out.println("Error adding an user_account");
	    }
	   }catch(SQLException e) {
		   e.getMessage();
	   }
	   return;
   }
   
   public void updateBalance(int accountId, double amount, char option) throws SQLException {
	  
	    
	    try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, "")) {
	       if(option == 'D') {
	    	   //for deposit
	    	   String addBalanceQuery = "UPDATE user_accounts SET account_balance = account_balance + ? WHERE account_id = ?";
	    	   try (PreparedStatement preparedStatement = connection.prepareStatement(addBalanceQuery)) {
		            preparedStatement.setDouble(1, amount);
		            preparedStatement.setInt(2, accountId);
		            
		            int rowsAffected = preparedStatement.executeUpdate();
		            
		            if (rowsAffected > 0) {
		                System.out.println("Balance added successfully to account " + accountId);
		            } else {
		                System.out.println("No account found with ID " + accountId);
		            }
		        }
	       }else if(option == 'P') {
	    	   //for Payment
	    	   String paymentQuery = "UPDATE user_accounts SET account_balance = account_balance - ? WHERE account_id = ?";
	    	   try (PreparedStatement preparedStatement = connection.prepareStatement(paymentQuery)) {
		            preparedStatement.setDouble(1, amount);
		            preparedStatement.setInt(2, accountId);
		            
		            int rowsAffected = preparedStatement.executeUpdate();
		            
		            if (rowsAffected > 0) {
		                System.out.println("Payment successfully to account " + accountId);
		                System.out.println("Deleting the all the products....");
		                String delPaidPrdcts = "DELETE FROM products WHERE user_id = ?";
		                try (PreparedStatement pst = connection.prepareStatement(delPaidPrdcts)) {
		                	pst.setInt(1, accountId);
		                	
		                	int rowsAff = pst.executeUpdate();
		                	if(rowsAff > 0) {
		                		System.out.println("products succesfully Deleted");
		                	}else {
		                		System.out.println("Error Deleting: No ros affected");
		                	}
		                }
		                
		            } else {
		                System.out.println("No account found with ID " + accountId);
		            }
		        }
	       }else {
	    	   System.out.println("Error on updating");
	    	 return;
	       }
	    } catch (SQLException e) {
	        // Handle the exception appropriately, either log it or re-throw it
	        e.printStackTrace();
	        throw e;
	    }
	}

   
   public double getBalance(int id) throws SQLException {
	   double balance = 0;
	   String getBalance = "SELECT account_balance FROM user_accounts WHERE account_id = ?";
	   Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, "");
	   try{PreparedStatement preparedStatement = connection.prepareStatement(getBalance ,Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, id);
       try (ResultSet result = preparedStatement.executeQuery()) {
           if (result.next()) {
        	   balance = result.getInt(1);
           } else {
               System.out.println("account not found");
           }
       }
	   
	   }catch(SQLException e) {
		   e.getMessage();
	   }
	   return balance;
   }
	   
   
   
   
   public boolean loginVerification(String username, String password, int id) throws SQLException {
	   UserAccount account = new UserAccount();
	   Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, "");
		String query = "SELECT * FROM users WHERE user_id  = ? AND user_username = ? AND user_Password = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, username);
		preparedStatement.setString(3, password);
		
		
		
		ResultSet result = preparedStatement.executeQuery();
		if(result.next()) {
			account.setUserD(id, username, password);
		
	
			return true;
			
		}
			 	
		
		return false;
		
   }
   
   
  
   
   
   //Generate a random number for user_id
    public static  int generateAccountId(){
        Random random = new Random();
        int min = 1000, max = 2000, generateAccountId = random.nextInt(max) + min;
       
        
       
        return generateAccountId;
    }
    
    

    public int getAccountId(String user_username) throws SQLException {
    	  Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, "");
        int accountId = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_id FROM users WHERE user_username = ?")) {
            preparedStatement.setString(1, user_username);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    accountId = result.getInt(1);
                } else {
                    System.out.println("Name not found");
                }
            }
        } catch (SQLException e) {
            // Handle the SQLException or propagate it to the calling method
            e.printStackTrace();
            throw e;
        }

        return accountId;
    }
    
    
    public double calculateTotalPrice(int useId) throws SQLException {
    	System.out.println("Calculating payment in ID: " + useId);
    	double totalPayment = 0;
    	String query = "SELECT SUM(product_price * product_quantity) AS total_payment FROM products WHERE user_id = ?;";
    	Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, "");
    	PreparedStatement pst = connection.prepareStatement(query);
    	pst.setDouble(1, useId);
    	ResultSet result = pst.executeQuery();
    	if(result.next()) {
    		totalPayment = result.getDouble("total_payment");
    	}else {
    		System.out.println("Invalid");
    	}
    	System.out.println("Calculate price value returend: " + totalPayment);
    	
    	 return totalPayment;
    	
    	
    }
    
    
    public boolean paymentVerification(double payment, int userid) throws SQLException {
    	
    	
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, "");

        // Check if the user with the specified ID exists
        String checkUserQuery = "SELECT user_id FROM users WHERE user_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(checkUserQuery)) {
            pst.setInt(1, userid);
            ResultSet result = pst.executeQuery();

            if (result.next() && payment >= calculateTotalPrice(userid)) {
            	return true;
               
               
            } 
                
     
            return false;
        }
    }

  
 
   
}



