package Model;

import java.util.ArrayList;

import Controller.PlayerController;


public class player {
	private int id;
	private String name;
	private String nation;
	private String position;
	private int ovr;
	private String teamid;
	private int squadnum;
	
	public static int getNextId() {
		int id = 1;
		ArrayList<player> list = PlayerController.list;
		if (list.size() < 1)
			id = 1;
		else {
			id = list.get(list.size() - 1).getId() + 1;
		}
		return id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.trim();
		//this.name = name;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getOvr() {
		return ovr;
	}
	public void setOvr(int ovr) {
		this.ovr = ovr;
	}
	public String getTeamid() {
		return teamid;
	}
	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}
	public int getSquadnum() {
		return squadnum;
	}
	public void setSquadnum(int squadnum) {
		this.squadnum = squadnum;
	}
	
}
