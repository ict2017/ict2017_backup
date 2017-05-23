package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import Model.user;

public class UserController {
	public static ArrayList<user> list = Process.getUserListFromDB();
	
	private static boolean checkUser(int id) {
		for (user u: list) {
			if (u.getUserid() == id) {
				return false; 
			}
		}
		return true;
	}
	
	public static void deleteUser(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserid() == id) {
				list.remove(i);
				deleteUserDB(id);
				break;
		}
	}
}
	public static void deleteUserDB(int id){
		Statement stmt = null;
		Connection c = Process.connectDB();
		try {
		stmt = c.createStatement();
		stmt.executeUpdate( "DELETE FROM login WHERE userid = '" + id + "';");
        stmt.close();
         c.close();
} catch (Exception e) {
	e.printStackTrace();
}
	}
	
	public static boolean addUser(int id, String username, char[] password, String type) {
		if (checkUser(id)) {
    	user u = new user();
    	u.setUserid(id);
    	u.setUsername(username);
    	u.setPassword(password);
		u.setAccountType(type);
    	list.add(u);
    	addUserDB(id, username, password, type);
    	return true;
		}
		else
			return false;
}
	
	public static void addUserDB(int id, String username, char[] password, String type) {
		Connection c = Process.connectDB();
		PreparedStatement stmt = null;
		try {
		stmt = c.prepareStatement("INSERT INTO login VALUES (?, ?, ?, ?)");
		stmt.setInt(1,id);
		stmt.setString(2,username);  
		stmt.setString(3,String.valueOf(password));
		stmt.setString(4,type);
		stmt.executeUpdate(); 
        stmt.close();
         c.close();
} catch (Exception e) {
	e.printStackTrace();
}
	}
	
	public static void updateUser(int userid, String username, char[] password, String type) {
		for (user u: list) {
			if (u.getUserid() == userid) {
				u.setUsername(username);
				u.setPassword(password);
				u.setAccountType(type);
}
    	updateUserDB(userid, username, password, type);
	}
}
	
	public static void updateUserDB(int userid, String username, char[] password, String type) {
		Connection c = Process.connectDB();
		PreparedStatement stmt = null;
		try {
		stmt = c.prepareStatement("UPDATE login SET username = ?, password = ?, type = ? WHERE userid = ?");
		stmt.setString(1,username);  
		stmt.setString(2,String.valueOf(password));
		stmt.setString(3,type);
		stmt.setInt(4,userid);
		stmt.executeUpdate(); 
        stmt.close();
         c.close();
} catch (Exception e) {
	e.printStackTrace();
}
	}
}
