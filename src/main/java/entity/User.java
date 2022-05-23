package entity;

public class User{
	private String loginId;
	private String passWord;
	private String name;
	
	public User(){
		
	}
	
	public User(String loginId, String passWord, String name) {
		this.loginId = loginId;
		this.passWord = passWord;
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPass() {
		return passWord;
	}

	public void setPass(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}