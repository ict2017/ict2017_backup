package Model;

import java.util.ArrayList;

import Controller.UserController;

public class user {
	private int userid;
	private String username;
	private char[] password;
	private String accountType;
	
	public static int getNextId() {
		int id = 1;
		ArrayList<user> list = UserController.list;
		if (list.size() < 1)
			id = 1;
		else {
			id = list.get(list.size() - 1).getUserid() + 1;
		}
		return id;
	}
	
	public int getUserid(){
		return userid;
	}
	public void setUserid(int userid){
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
