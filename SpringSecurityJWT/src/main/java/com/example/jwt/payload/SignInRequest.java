package com.example.jwt.payload;

public class SignInRequest {
	
	private String userName;
	
	private String passWord;

	public SignInRequest(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public SignInRequest() {}

	@Override
	public String toString() {
		return "SignInRequest [userName=" + userName + ", passWord=" + passWord + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
