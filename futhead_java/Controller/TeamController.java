package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import Model.team;

public class TeamController {
	public static ArrayList<team> list = Process.getTeamListFromDB();
	private static boolean checkTeam(String teamid) {
		for (team tm: list) {
			if (tm.getTeamid().equals(teamid)) {
				return false; 
			}
		}
		return true;
	}
	
	public static team getTeamById (String teamid) {
		for (team tm: list) {
			if (tm.getTeamid().equals(teamid)) {
				return tm;
			}
		}
		return null;
	}
	
	public static team getTeamByName (String tname) {
		for (team tm: list) {
			if (tm.getName().equals(tname)) {
				return tm;
			}
		}
		return null;
	}
	
	public static String[] getTeamList(){
		String[] teamList = new String[list.size()];
		for(int i = 0; i < teamList.length; i++) {
				teamList[i] = list.get(i).getName();
			}
		return teamList;
	}
	
	public static void deleteTeam(String teamid) {
		for (team tm: list) {
			if (tm.getTeamid().equals(teamid)) {
				list.remove(tm);
				deleteTeamDB(teamid);
				break;
		}
	}
}
	public static void deleteTeamDB(String teamid){
		Statement stmt = null;
		Connection c = Process.connectDB();
		try {
		stmt = c.createStatement();
		stmt.executeUpdate( "DELETE FROM team WHERE teamid = '" + teamid + "';");
        stmt.close();
         c.close();
} catch (Exception e) {
	e.printStackTrace();
}
	}
	
	public static boolean addTeam(String teamid, String tname, String league) {
		if (checkTeam(teamid)) {
    	team tm = new team();
    	tm.setTeamid(teamid);
    	tm.setName(tname);
    	tm.setLeague(league);
    	list.add(tm);
    	addTeamDB(teamid, tname, league);
    	return true;
		}
		else
			return false;
}
	
	public static void addTeamDB(String teamid, String tname, String league) {
		Connection c = Process.connectDB();
		PreparedStatement stmt = null;
		try {
		stmt = c.prepareStatement("INSERT INTO team VALUES (?, ?, ?)");
		stmt.setString(1,teamid);  
		stmt.setString(2,tname);
		stmt.setString(3,league);
		stmt.executeUpdate(); 
        stmt.close();
         c.close();
} catch (Exception e) {
	e.printStackTrace();
}
	}
	public static void updateTeam(String teamid, String oldteamid,  String tname, String league) {
		for (team tm: list) {
			if (tm.getTeamid().equals(oldteamid)) {
				tm.setTeamid(teamid);
				tm.setName(tname);
				tm.setLeague(league);
}
    	updateTeamDB(teamid, oldteamid, tname, league);
	}
}
	
	public static void updateTeamDB(String teamid, String oldteamid,  String tname, String league){
		Connection c = Process.connectDB();
		PreparedStatement stmt = null;
		try {
		stmt = c.prepareStatement("UPDATE team SET teamid = ?, league = ?, \"team name\" = ? WHERE teamid = ?");
		stmt.setString(1,teamid);  
		stmt.setString(2,league);
		stmt.setString(3,tname);
		stmt.setString(4,oldteamid);
		stmt.executeUpdate(); 
        stmt.close();
		stmt = c.prepareStatement("UPDATE playfor SET teamid = ? WHERE teamid = ?");
		stmt.setString(1,teamid);  
		stmt.setString(2,oldteamid);
		stmt.executeUpdate(); 
        stmt.close();
         c.close();
} catch (Exception e) {
	e.printStackTrace();
}
	}
}
