package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Model.player;
import Model.team;
import Model.user;

public class Process {
	public static Connection connectDB(){
		Connection c = null;
        String url = "jdbc:postgresql://localhost:5432/futhead";
        //String driver = "com.mysql.jdbc.Driver";
        String user = "postgres";
        String pass = "a";
		try {
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection(url,user,pass);

		//System.out.println("Opened database successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static ArrayList<player> getPlayerListFromDB() {
		ArrayList<player> playerList = new ArrayList<player>();
		
		Statement stmt = null;
		player player = new player();
		Connection c = connectDB();
		try {
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "SELECT * FROM player left outer join playfor ON player.id = playfor.id ORDER BY player.*" );
	    while ( rs.next() ) {
            	player.setId(rs.getInt("id"));
            	player.setName(rs.getString("name"));
            	player.setOvr(rs.getInt("ovr"));
            	player.setNation(rs.getString("nation"));
            	player.setPosition(rs.getString("position"));
            	player.setTeamid(rs.getString("teamid"));
            	player.setSquadnum(rs.getInt("squadnum"));
            	playerList.add(player);
            	player = new player();
		         }
		         rs.close();
		         stmt.close();
		         c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playerList;
	}
	
	public static ArrayList<team> getTeamListFromDB() {
		ArrayList<team> teamList = new ArrayList<team>();
		
		Statement stmt = null;
		team team = new team();
		Connection c = connectDB();
		try {
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "SELECT * FROM team ORDER BY team.*" );
	    while ( rs.next() ) {
            	team.setTeamid(rs.getString("teamid"));
            	team.setName(rs.getString("team name"));
            	team.setLeague(rs.getString("league"));
            	teamList.add(team);
            	team = new team();
		         }
		         rs.close();
		         stmt.close();
		         c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teamList;
	}

	public static ArrayList<user> getUserListFromDB() {
		ArrayList<user> userList = new ArrayList<user>();
		
		Statement stmt = null;
		user u = new user();
		Connection c = connectDB();
		try {
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "SELECT * FROM login " );
	    while ( rs.next() ) {
            	u.setUserid(rs.getInt("userid"));
            	u.setUsername(rs.getString("username"));
            	u.setPassword(rs.getString("password").toCharArray());
            	u.setAccountType(rs.getString("type"));
            	userList.add(u);
            	u = new user();
		         }
		         rs.close();
		         stmt.close();
		         c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
}
