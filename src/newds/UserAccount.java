package newds;

public class UserAccount {
	private int userid;
	private String username;
	private String password;
	
	
	public void setUserD(int userid, String username, String password) {
		this.userid = userid;
		this.username = username;
		this.password = password;
	}
	
	
	
	public int getUserId() {
		return userid;
	}
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	
}
