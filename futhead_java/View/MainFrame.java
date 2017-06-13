package View;

//import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.PlayerController;
import Controller.TeamController;
//import Controller.UserController;

import Model.player;
import Model.team;
import Model.user;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public user currentUser;
	private DefaultTableModel playerTableModel;
	private DefaultTableModel teamTableModel;
	private DefaultTableModel atkPlayerTableModel;
	private DefaultTableModel midPlayerTableModel;
	private DefaultTableModel defPlayerTableModel;
	
	private JButton btnDeletePlayer;
	private JButton btnAddPlayer;
	private JButton btnUpdatePlayer;
	private JButton btnDeleteTeam;
	private JButton btnAddTeam;
	private JButton btnUpdateTeam;
	private JButton btnDeleteAtkPlayer;
	private JButton btnAddAtkPlayer;
	private JButton btnUpdateAtkPlayer;
	private JButton btnDeleteMidPlayer;
	private JButton btnAddMidPlayer;
	private JButton btnUpdateMidPlayer;
	private JButton btnDeleteDefPlayer;
	private JButton btnAddDefPlayer;
	private JButton btnUpdateDefPlayer;
	
	public MainFrame() {
	setTitle("FIFA Player Database");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 1000, 600);
	
	// content pane
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	
	// Define Tabbed Pane
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane.setFont(new Font("Consolas", Font.BOLD, 13));
	tabbedPane.setBounds(10, 11, 964, 515);
	contentPane.add(tabbedPane);
	
	// menu bar
	JMenuBar menuBar = new JMenuBar();
	JMenu menu1 = new JMenu("Account");
	setJMenuBar(menuBar);
	menuBar.add(menu1);
	JMenuItem register = new JMenuItem("Register");
	register.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			FrameAddUser frame = new FrameAddUser(getFrame());
			frame.setVisible(true);
		}
	});
	menu1.add(register);
	JMenuItem changePassword = new JMenuItem("Change password");
	changePassword.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			FrameUpdateUser frame = new FrameUpdateUser(getFrame());
			frame.displayAccountInformation(currentUser);
			frame.setVisible(true);
		}
	});
	menu1.add(changePassword);
	JMenuItem logout = new JMenuItem("Log out");
	logout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			FrameLogIn frame = new FrameLogIn();
			frame.setVisible(true);
			getFrame().dispose();
		}
	});
	menu1.add(logout);
	JMenuItem exit = new JMenuItem("Exit");
	exit.addActionListener(new CancelActionListener());
	menu1.add(exit);
	
	//Panel Player
	JPanel playerPanel = new JPanel();
	playerPanel.setLayout(null);
	tabbedPane.addTab("Player Management", null, playerPanel, null);
	//search box
	JLabel lblSearchPlayer = new JLabel("Search:");
	lblSearchPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	lblSearchPlayer.setBounds(30, 24, 60, 30);
	playerPanel.add(lblSearchPlayer);
	
	JTextField txtSearchPlayer = new JTextField();
	txtSearchPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	txtSearchPlayer.setBounds(100, 25, 250, 30);
	playerPanel.add(txtSearchPlayer);		JScrollPane playerScrollPane = new JScrollPane();
	playerScrollPane.setBounds(30, 80, 900, 330);
	
	JLabel lblFilterPlayer = new JLabel("Filter:");
	lblFilterPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	lblFilterPlayer.setBounds(397, 24, 60, 30);
	playerPanel.add(lblFilterPlayer);
	
	JComboBox<String> cbbFilterPlayer = new JComboBox<String>();
	cbbFilterPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	cbbFilterPlayer.setModel(new DefaultComboBoxModel<String>(new String[] {"Player ID", "Name", "Nation", "Team", "Squad Number", "OVR"}));
	cbbFilterPlayer.setBounds(467, 24, 113, 30);
	playerPanel.add(cbbFilterPlayer);
	
	JButton btnSearchPlayer = new JButton("Search");
	btnSearchPlayer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event){
			String filter = cbbFilterPlayer.getSelectedItem().toString();
			String key = txtSearchPlayer.getText();
			ArrayList<player> list = new ArrayList<player>();
			for (player p: PlayerController.list) {
				if (filter.equals("Player ID")) {
					if (Integer.toString(p.getId()).equals(key.trim().toLowerCase()))
						list.add(p);
				} 
				else if (filter.equals("Name")) {
					if (p.getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Nation")) {
					if (p.getNation().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Team")) {
					if (TeamController.getTeamById(p.getTeamid()).getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Squad Number")) {
					if (Integer.toString(p.getSquadnum()).equals(key.trim().toLowerCase())) {
						list.add(p);
					}
				}
				else if (filter.equals("OVR")) {
					if (Integer.toString(p.getOvr()).equals(key.trim().toLowerCase())) {
						list.add(p);
					}
				}
			}
			displayPlayerTable(list);
		}
		});
	btnSearchPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnSearchPlayer.setBounds(612, 24, 80, 30);
	playerPanel.add(btnSearchPlayer);
	
	JButton btnRefreshPlayerTable = new JButton("Refresh");
	btnRefreshPlayerTable.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			displayPlayerTable(PlayerController.list);
			txtSearchPlayer.setText(null);
		}
	});
	btnRefreshPlayerTable.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnRefreshPlayerTable.setBounds(714, 24, 85, 30);
	playerPanel.add(btnRefreshPlayerTable);
	
	//bang chinh
	String[] playerColumnNames = {"ID", "Name", "Nation", "Position", "Team", "Squad Number", "League" ,"OVR"};
	playerTableModel = new DefaultTableModel(playerColumnNames, 0) {
		private static final long serialVersionUID = 1L;
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	JTable playerTable = new JTable(playerTableModel);
	displayPlayerTable(PlayerController.list);
	playerTable.setFont(new Font("Consolas", Font.PLAIN, 12));
	playerScrollPane.setViewportView(playerTable);
	playerPanel.add(playerScrollPane);
	//
	
	JButton btnCancel = new JButton("Exit");
	btnCancel.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnCancel.addActionListener(new CancelActionListener());
	btnCancel.setBounds(850, 435, 80, 30);
	playerPanel.add(btnCancel);
	
	btnDeletePlayer = new JButton("Delete");
	btnDeletePlayer.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent event) {
		int[] selectedRows = playerTable.getSelectedRows();
		if (selectedRows.length == 0) {
			JOptionPane.showMessageDialog(contentPane, "Please select rows to delete!");
		}
		else {
			for (int i = 0; i < selectedRows.length; i++) {
				int id = Integer.parseInt(playerTable.getValueAt(selectedRows[i], 0).toString());
				PlayerController.deletePlayer(id);
			}
			displayPlayerTable(PlayerController.list);
		}
	}
});
	btnDeletePlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnDeletePlayer.setBounds(760, 435, 80, 30);
	playerPanel.add(btnDeletePlayer);

	btnAddPlayer = new JButton("Add");
	btnAddPlayer.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent event) {
		FrameAddPlayer frame = new FrameAddPlayer(getFrame());
		frame.setVisible(true);
	}
});
	btnAddPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnAddPlayer.setBounds(580, 435, 80, 30);
	playerPanel.add(btnAddPlayer);

	
	btnUpdatePlayer = new JButton("Edit");
	btnUpdatePlayer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			int[] selectedRows = playerTable.getSelectedRows();
			if (selectedRows.length != 1) {
				JOptionPane.showMessageDialog(contentPane, "Please select exactly one row to edit!");
			}
			else {
					int id = Integer.parseInt(playerTable.getValueAt(selectedRows[0], 0).toString());
					FrameEditPlayer frame = new FrameEditPlayer(getFrame());
					frame.displayPlayerInformation(PlayerController.getPlayerById(id));
					frame.setVisible(true);
			}
		}
	});
	btnUpdatePlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnUpdatePlayer.setBounds(670, 435, 80, 30);
	playerPanel.add(btnUpdatePlayer);
	
	//Team Panel
	JPanel teamPanel = new JPanel();
	tabbedPane.addTab("Team Management", null, teamPanel, null);
	teamPanel.setLayout(null);
	
	JLabel lblSearchTeam = new JLabel("Search:");
	lblSearchTeam.setFont(new Font("Consolas", Font.PLAIN, 12));
	lblSearchTeam.setBounds(30, 24, 60, 30);
	teamPanel.add(lblSearchTeam);
	
	JTextField txtSearchTeam = new JTextField();
	txtSearchTeam.setFont(new Font("Consolas", Font.PLAIN, 12));
	txtSearchTeam.setBounds(100, 25, 250, 30);
	teamPanel.add(txtSearchTeam);
	
	JLabel lblFilterTeam = new JLabel("Filter:");
	lblFilterTeam.setFont(new Font("Consolas", Font.PLAIN, 12));
	lblFilterTeam.setBounds(397, 24, 60, 30);
	teamPanel.add(lblFilterTeam);
	
	JComboBox<String> cbbFilterTeam = new JComboBox<String>();
	cbbFilterTeam.setFont(new Font("Consolas", Font.PLAIN, 12));
	cbbFilterTeam.setModel(new DefaultComboBoxModel<String>(new String[] {"Team ID", "Team Name", "League", "Number of Players"}));
	cbbFilterTeam.setBounds(467, 24, 113, 30);
	teamPanel.add(cbbFilterTeam);
	
	JButton btnSearchTeam = new JButton("Search");
	btnSearchTeam.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String filter = cbbFilterTeam.getSelectedItem().toString();
			String key = txtSearchTeam.getText();
			ArrayList<team> list = new ArrayList<team>();
			for (team tm: TeamController.list) {
				if (filter.equals("Team ID")) {
					if (tm.getTeamid().toLowerCase().indexOf(key.trim().toLowerCase()) != -1)
						list.add(tm);
				} 
				else if (filter.equals("Team Name")) {
					if (tm.getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(tm);
					}
				}
				else if (filter.equals("League")) {
					if (tm.getLeague().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(tm);
					}
				}
				else if (filter.equals("Number of Players")) {
					if (Integer.toString(PlayerController.getPlayerNumberOfTeam(tm.getTeamid())).equals(key.trim().toLowerCase())) {
						list.add(tm);
					}
				}
			}
			displayTeamTable(list);
		}
	});
	btnSearchTeam.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnSearchTeam.setBounds(612, 24, 80, 30);
	teamPanel.add(btnSearchTeam);
	
	JButton btnRefreshTeamTable = new JButton("Refresh");
	btnRefreshTeamTable.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			displayTeamTable(TeamController.list);
			txtSearchTeam.setText(null);
		}
	});
	btnRefreshTeamTable.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnRefreshTeamTable.setBounds(714, 24, 85, 30);
	teamPanel.add(btnRefreshTeamTable);

	//main table
	JScrollPane teamScrollPane = new JScrollPane();
	teamScrollPane.setBounds(30, 80, 900, 330);
	teamPanel.add(teamScrollPane);
	
	String[] teamColumnNames = {"Team ID", "Team Name", "League", "Number of Players"};
	teamTableModel = new DefaultTableModel(teamColumnNames, 0){
		private static final long serialVersionUID = 1L;
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	JTable teamTable = new JTable(teamTableModel);
	teamTable.setFont(new Font("Consolas", Font.PLAIN, 13));
	displayTeamTable(TeamController.list);
	teamScrollPane.setViewportView(teamTable);
	
	btnDeleteTeam = new JButton("Delete");
	btnDeleteTeam.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			int[] selectedRows = teamTable.getSelectedRows();
			if (selectedRows.length == 0) {
				JOptionPane.showMessageDialog(contentPane, "Please select rows to delete!");
			}
			else {
				for (int i = 0; i < selectedRows.length; i++) {
					String id = teamTable.getValueAt(selectedRows[i], 0).toString();
					TeamController.deleteTeam(id);
				}
				displayTeamTable(TeamController.list);
			}
		}
	});
	btnDeleteTeam.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnDeleteTeam.setBounds(760, 435, 80, 30);
	teamPanel.add(btnDeleteTeam);
	
	btnAddTeam = new JButton("Add");
	btnAddTeam.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			FrameAddTeam frame = new FrameAddTeam(getFrame());
			frame.setVisible(true);
		}
	});
	btnAddTeam.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnAddTeam.setBounds(580, 435, 80, 30);
	teamPanel.add(btnAddTeam);
	
	JButton btnCancel2 = new JButton("Exit");
	btnCancel2.setFont(new Font("Consolas", Font.PLAIN, 13));
	btnCancel2.setBounds(850, 435, 80, 30);
	btnCancel2.addActionListener(new CancelActionListener());
	teamPanel.add(btnCancel2);
	
	btnUpdateTeam = new JButton("Edit");
	btnUpdateTeam.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			int[] selectedRows = teamTable.getSelectedRows();
			if (selectedRows.length != 1) {
				JOptionPane.showMessageDialog(contentPane, "Please select exactly one row to update!");
			}
			else {
				String id = teamTable.getValueAt(selectedRows[0], 0).toString();
				FrameEditTeam frame = new FrameEditTeam(getFrame());
				frame.displayTeamInformation(TeamController.getTeamById(id));
				frame.setVisible(true);
			}
		}
	});
	btnUpdateTeam.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnUpdateTeam.setBounds(670, 435, 80, 30);
	teamPanel.add(btnUpdateTeam);
	
	
	
	//Panel Attacking Position
	JPanel atkPlayerPanel = new JPanel();
	atkPlayerPanel.setLayout(null);
	tabbedPane.addTab("Attacking Position", null, atkPlayerPanel, null);
	//search box
	JLabel lblSearchAtkPlayer = new JLabel("Search:");
	lblSearchAtkPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	lblSearchAtkPlayer.setBounds(30, 24, 60, 30);
	atkPlayerPanel.add(lblSearchAtkPlayer);
	
	JTextField txtSearchAtkPlayer = new JTextField();
	txtSearchAtkPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	txtSearchAtkPlayer.setBounds(100, 25, 250, 30);
	atkPlayerPanel.add(txtSearchAtkPlayer);		JScrollPane atkPlayerScrollPane = new JScrollPane();
	atkPlayerScrollPane.setBounds(30, 80, 900, 330);
	
	JLabel lblFilterAtkPlayer = new JLabel("Filter:");
	lblFilterAtkPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	lblFilterAtkPlayer.setBounds(397, 24, 60, 30);
	atkPlayerPanel.add(lblFilterAtkPlayer);
	
	JComboBox<String> cbbFilterAtkPlayer = new JComboBox<String>();
	cbbFilterAtkPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	cbbFilterAtkPlayer.setModel(new DefaultComboBoxModel<String>(new String[] {"Player ID", "Name", "Nation", "Team", "Squad Number", "OVR"}));
	cbbFilterAtkPlayer.setBounds(467, 24, 113, 30);
	atkPlayerPanel.add(cbbFilterAtkPlayer);
	
	JButton btnSearchAtkPlayer = new JButton("Search");
	btnSearchAtkPlayer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event){
			String filter = cbbFilterAtkPlayer.getSelectedItem().toString();
			String key = txtSearchAtkPlayer.getText();
			ArrayList<player> list = new ArrayList<player>();
			for (player p: PlayerController.list) {
				if (filter.equals("Player ID")) {
					if (Integer.toString(p.getId()).equals(key.trim().toLowerCase()))
						list.add(p);
				} 
				else if (filter.equals("Name")) {
					if (p.getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Nation")) {
					if (p.getNation().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Team")) {
					if (TeamController.getTeamById(p.getTeamid()).getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Squad Number")) {
					if (Integer.toString(p.getSquadnum()).equals(key.trim().toLowerCase())) {
						list.add(p);
					}
				}
				else if (filter.equals("OVR")) {
					if (Integer.toString(p.getOvr()).equals(key.trim().toLowerCase())) {
						list.add(p);
					}
				}
			}
			displayAtkPlayerTable(list);
		}
		});
	btnSearchAtkPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnSearchAtkPlayer.setBounds(612, 24, 80, 30);
	atkPlayerPanel.add(btnSearchAtkPlayer);
	
	JButton btnRefreshAtkPlayerTable = new JButton("Refresh");
	btnRefreshAtkPlayerTable.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			displayAtkPlayerTable(PlayerController.list);
			txtSearchAtkPlayer.setText(null);
		}
	});
	btnRefreshAtkPlayerTable.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnRefreshAtkPlayerTable.setBounds(714, 24, 85, 30);
	atkPlayerPanel.add(btnRefreshAtkPlayerTable);
	
	//bang chinh
	String[] atkPlayerColumnNames = {"ID", "Name", "Nation", "Position", "Team", "Squad Number", "League" ,"OVR"};
	atkPlayerTableModel = new DefaultTableModel(atkPlayerColumnNames, 0) {
		private static final long serialVersionUID = 1L;
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	JTable atkPlayerTable = new JTable(atkPlayerTableModel);
	displayAtkPlayerTable(PlayerController.list);
	atkPlayerTable.setFont(new Font("Consolas", Font.PLAIN, 12));
	atkPlayerScrollPane.setViewportView(atkPlayerTable);
	atkPlayerPanel.add(atkPlayerScrollPane);
	//
	
	JButton btnCancel3 = new JButton("Exit");
	btnCancel3.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnCancel3.addActionListener(new CancelActionListener());
	btnCancel3.setBounds(850, 435, 80, 30);
	atkPlayerPanel.add(btnCancel3);
	
	btnDeleteAtkPlayer = new JButton("Delete");
	btnDeleteAtkPlayer.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent event) {
		int[] selectedRows = atkPlayerTable.getSelectedRows();
		if (selectedRows.length == 0) {
			JOptionPane.showMessageDialog(contentPane, "Please select rows to delete!");
		}
		else {
			for (int i = 0; i < selectedRows.length; i++) {
				int id = Integer.parseInt(atkPlayerTable.getValueAt(selectedRows[i], 0).toString());
				PlayerController.deletePlayer(id);
			}
			displayAtkPlayerTable(PlayerController.list);
		}
	}
});
	btnDeleteAtkPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnDeleteAtkPlayer.setBounds(760, 435, 80, 30);
	atkPlayerPanel.add(btnDeleteAtkPlayer);

	btnAddAtkPlayer = new JButton("Add");
	btnAddAtkPlayer.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent event) {
		FrameAddPlayer frame = new FrameAddPlayer(getFrame());
		frame.setVisible(true);
	}
});
	btnAddAtkPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnAddAtkPlayer.setBounds(580, 435, 80, 30);
	atkPlayerPanel.add(btnAddAtkPlayer);

	
	btnUpdateAtkPlayer = new JButton("Edit");
	btnUpdateAtkPlayer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			int[] selectedRows = atkPlayerTable.getSelectedRows();
			if (selectedRows.length != 1) {
				JOptionPane.showMessageDialog(contentPane, "Please select exactly one row to edit!");
			}
			else {
					int id = Integer.parseInt(atkPlayerTable.getValueAt(selectedRows[0], 0).toString());
					FrameEditPlayer frame = new FrameEditPlayer(getFrame());
					frame.displayPlayerInformation(PlayerController.getPlayerById(id));
					frame.setVisible(true);
			}
		}
	});
	btnUpdateAtkPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnUpdateAtkPlayer.setBounds(670, 435, 80, 30);
	atkPlayerPanel.add(btnUpdateAtkPlayer);
	
	
	//Panel Midfield Position
	JPanel midPlayerPanel = new JPanel();
	midPlayerPanel.setLayout(null);
	tabbedPane.addTab("Midfielder Position", null, midPlayerPanel, null);
	//search box
	JLabel lblSearchMidPlayer = new JLabel("Search:");
	lblSearchMidPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	lblSearchMidPlayer.setBounds(30, 24, 60, 30);
	midPlayerPanel.add(lblSearchMidPlayer);
	
	JTextField txtSearchMidPlayer = new JTextField();
	txtSearchMidPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	txtSearchMidPlayer.setBounds(100, 25, 250, 30);
	midPlayerPanel.add(txtSearchMidPlayer);		JScrollPane midPlayerScrollPane = new JScrollPane();
	midPlayerScrollPane.setBounds(30, 80, 900, 330);
	
	JLabel lblFilterMidPlayer = new JLabel("Filter:");
	lblFilterMidPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	lblFilterMidPlayer.setBounds(397, 24, 60, 30);
	midPlayerPanel.add(lblFilterMidPlayer);
	
	JComboBox<String> cbbFilterMidPlayer = new JComboBox<String>();
	cbbFilterMidPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	cbbFilterMidPlayer.setModel(new DefaultComboBoxModel<String>(new String[] {"Player ID", "Name", "Nation", "Team", "Squad Number", "OVR"}));
	cbbFilterMidPlayer.setBounds(467, 24, 113, 30);
	midPlayerPanel.add(cbbFilterMidPlayer);
	
	JButton btnSearchMidPlayer = new JButton("Search");
	btnSearchMidPlayer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event){
			String filter = cbbFilterMidPlayer.getSelectedItem().toString();
			String key = txtSearchMidPlayer.getText();
			ArrayList<player> list = new ArrayList<player>();
			for (player p: PlayerController.list) {
				if (filter.equals("Player ID")) {
					if (Integer.toString(p.getId()).equals(key.trim().toLowerCase()))
						list.add(p);
				} 
				else if (filter.equals("Name")) {
					if (p.getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Nation")) {
					if (p.getNation().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Team")) {
					if (TeamController.getTeamById(p.getTeamid()).getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Squad Number")) {
					if (Integer.toString(p.getSquadnum()).equals(key.trim().toLowerCase())) {
						list.add(p);
					}
				}
				else if (filter.equals("OVR")) {
					if (Integer.toString(p.getOvr()).equals(key.trim().toLowerCase())) {
						list.add(p);
					}
				}
			}
			displayMidPlayerTable(list);
		}
		});
	btnSearchMidPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnSearchMidPlayer.setBounds(612, 24, 80, 30);
	midPlayerPanel.add(btnSearchMidPlayer);
	
	JButton btnRefreshMidPlayerTable = new JButton("Refresh");
	btnRefreshMidPlayerTable.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			displayMidPlayerTable(PlayerController.list);
			txtSearchMidPlayer.setText(null);
		}
	});
	btnRefreshMidPlayerTable.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnRefreshMidPlayerTable.setBounds(714, 24, 85, 30);
	midPlayerPanel.add(btnRefreshMidPlayerTable);
	
	//bang chinh
	String[] midPlayerColumnNames = {"ID", "Name", "Nation", "Position", "Team", "Squad Number", "League" ,"OVR"};
	midPlayerTableModel = new DefaultTableModel(midPlayerColumnNames, 0) {
		private static final long serialVersionUID = 1L;
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	JTable midPlayerTable = new JTable(midPlayerTableModel);
	displayMidPlayerTable(PlayerController.list);
	midPlayerTable.setFont(new Font("Consolas", Font.PLAIN, 12));
	midPlayerScrollPane.setViewportView(midPlayerTable);
	midPlayerPanel.add(midPlayerScrollPane);
	//
	
	JButton btnCancel4 = new JButton("Exit");
	btnCancel4.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnCancel4.addActionListener(new CancelActionListener());
	btnCancel4.setBounds(850, 435, 80, 30);
	midPlayerPanel.add(btnCancel4);
	
	btnDeleteMidPlayer = new JButton("Delete");
	btnDeleteMidPlayer.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent event) {
		int[] selectedRows = midPlayerTable.getSelectedRows();
		if (selectedRows.length == 0) {
			JOptionPane.showMessageDialog(contentPane, "Please select rows to delete!");
		}
		else {
			for (int i = 0; i < selectedRows.length; i++) {
				int id = Integer.parseInt(midPlayerTable.getValueAt(selectedRows[i], 0).toString());
				PlayerController.deletePlayer(id);
			}
			displayMidPlayerTable(PlayerController.list);
		}
	}
});
	btnDeleteMidPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnDeleteMidPlayer.setBounds(760, 435, 80, 30);
	midPlayerPanel.add(btnDeleteMidPlayer);

	btnAddMidPlayer = new JButton("Add");
	btnAddMidPlayer.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent event) {
		FrameAddPlayer frame = new FrameAddPlayer(getFrame());
		frame.setVisible(true);
	}
});
	btnAddMidPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnAddMidPlayer.setBounds(580, 435, 80, 30);
	midPlayerPanel.add(btnAddMidPlayer);

	
	btnUpdateMidPlayer = new JButton("Edit");
	btnUpdateMidPlayer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			int[] selectedRows = midPlayerTable.getSelectedRows();
			if (selectedRows.length != 1) {
				JOptionPane.showMessageDialog(contentPane, "Please select exactly one row to edit!");
			}
			else {
					int id = Integer.parseInt(midPlayerTable.getValueAt(selectedRows[0], 0).toString());
					FrameEditPlayer frame = new FrameEditPlayer(getFrame());
					frame.displayPlayerInformation(PlayerController.getPlayerById(id));
					frame.setVisible(true);
			}
		}
	});
	btnUpdateMidPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnUpdateMidPlayer.setBounds(670, 435, 80, 30);
	midPlayerPanel.add(btnUpdateMidPlayer);
	
	//Panel Defending Position
	JPanel defPlayerPanel = new JPanel();
	defPlayerPanel.setLayout(null);
	tabbedPane.addTab("Defending Position", null, defPlayerPanel, null);
	//search box
	JLabel lblSearchDefPlayer = new JLabel("Search:");
	lblSearchDefPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	lblSearchDefPlayer.setBounds(30, 24, 60, 30);
	defPlayerPanel.add(lblSearchDefPlayer);
	
	JTextField txtSearchDefPlayer = new JTextField();
	txtSearchDefPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	txtSearchDefPlayer.setBounds(100, 25, 250, 30);
	defPlayerPanel.add(txtSearchDefPlayer);		JScrollPane defPlayerScrollPane = new JScrollPane();
	defPlayerScrollPane.setBounds(30, 80, 900, 330);
	
	JLabel lblFilterDefPlayer = new JLabel("Filter:");
	lblFilterDefPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	lblFilterDefPlayer.setBounds(397, 24, 60, 30);
	defPlayerPanel.add(lblFilterDefPlayer);
	
	JComboBox<String> cbbFilterDefPlayer = new JComboBox<String>();
	cbbFilterDefPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	cbbFilterDefPlayer.setModel(new DefaultComboBoxModel<String>(new String[] {"Player ID", "Name", "Nation", "Team", "Squad Number", "OVR"}));
	cbbFilterDefPlayer.setBounds(467, 24, 113, 30);
	defPlayerPanel.add(cbbFilterDefPlayer);
	
	JButton btnSearchDefPlayer = new JButton("Search");
	btnSearchDefPlayer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event){
			String filter = cbbFilterDefPlayer.getSelectedItem().toString();
			String key = txtSearchDefPlayer.getText();
			ArrayList<player> list = new ArrayList<player>();
			for (player p: PlayerController.list) {
				if (filter.equals("Player ID")) {
					if (Integer.toString(p.getId()).equals(key.trim().toLowerCase()))
						list.add(p);
				} 
				else if (filter.equals("Name")) {
					if (p.getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Nation")) {
					if (p.getNation().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Team")) {
					if (TeamController.getTeamById(p.getTeamid()).getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
						list.add(p);
					}
				}
				else if (filter.equals("Squad Number")) {
					if (Integer.toString(p.getSquadnum()).equals(key.trim().toLowerCase())) {
						list.add(p);
					}
				}
				else if (filter.equals("OVR")) {
					if (Integer.toString(p.getOvr()).equals(key.trim().toLowerCase())) {
						list.add(p);
					}
				}
			}
			displayDefPlayerTable(list);
		}
		});
	btnSearchDefPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnSearchDefPlayer.setBounds(612, 24, 80, 30);
	defPlayerPanel.add(btnSearchDefPlayer);
	
	JButton btnRefreshDefPlayerTable = new JButton("Refresh");
	btnRefreshDefPlayerTable.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			displayDefPlayerTable(PlayerController.list);
			txtSearchDefPlayer.setText(null);
		}
	});
	btnRefreshDefPlayerTable.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnRefreshDefPlayerTable.setBounds(714, 24, 85, 30);
	defPlayerPanel.add(btnRefreshDefPlayerTable);
	
	//bang chinh
	String[] defPlayerColumnNames = {"ID", "Name", "Nation", "Position", "Team", "Squad Number", "League" ,"OVR"};
	defPlayerTableModel = new DefaultTableModel(defPlayerColumnNames, 0) {
		private static final long serialVersionUID = 1L;
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	JTable defPlayerTable = new JTable(defPlayerTableModel);
	displayDefPlayerTable(PlayerController.list);
	defPlayerTable.setFont(new Font("Consolas", Font.PLAIN, 12));
	defPlayerScrollPane.setViewportView(defPlayerTable);
	defPlayerPanel.add(defPlayerScrollPane);
	//
	
	JButton btnCancel5 = new JButton("Exit");
	btnCancel5.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnCancel5.addActionListener(new CancelActionListener());
	btnCancel5.setBounds(850, 435, 80, 30);
	defPlayerPanel.add(btnCancel5);
	
	btnDeleteDefPlayer = new JButton("Delete");
	btnDeleteDefPlayer.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent event) {
		int[] selectedRows = defPlayerTable.getSelectedRows();
		if (selectedRows.length == 0) {
			JOptionPane.showMessageDialog(contentPane, "Please select rows to delete!");
		}
		else {
			for (int i = 0; i < selectedRows.length; i++) {
				int id = Integer.parseInt(defPlayerTable.getValueAt(selectedRows[i], 0).toString());
				PlayerController.deletePlayer(id);
			}
			displayDefPlayerTable(PlayerController.list);
		}
	}
});
	btnDeleteDefPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnDeleteDefPlayer.setBounds(760, 435, 80, 30);
	defPlayerPanel.add(btnDeleteDefPlayer);

	btnAddDefPlayer = new JButton("Add");
	btnAddDefPlayer.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent event) {
		FrameAddPlayer frame = new FrameAddPlayer(getFrame());
		frame.setVisible(true);
	}
});
	btnAddDefPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnAddDefPlayer.setBounds(580, 435, 80, 30);
	defPlayerPanel.add(btnAddDefPlayer);

	
	btnUpdateDefPlayer = new JButton("Edit");
	btnUpdateDefPlayer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			int[] selectedRows = defPlayerTable.getSelectedRows();
			if (selectedRows.length != 1) {
				JOptionPane.showMessageDialog(contentPane, "Please select exactly one row to edit!");
			}
			else {
					int id = Integer.parseInt(defPlayerTable.getValueAt(selectedRows[0], 0).toString());
					FrameEditPlayer frame = new FrameEditPlayer(getFrame());
					frame.displayPlayerInformation(PlayerController.getPlayerById(id));
					frame.setVisible(true);
			}
		}
	});
	btnUpdateDefPlayer.setFont(new Font("Consolas", Font.PLAIN, 12));
	btnUpdateDefPlayer.setBounds(670, 435, 80, 30);
	defPlayerPanel.add(btnUpdateDefPlayer);
	
	
	
	
	
}
	public MainFrame getFrame() {
		return this;
	}
	
	private void removeAllRows(DefaultTableModel d) {
	for (int i = d.getRowCount() - 1; i >= 0; i--) {
		d.removeRow(i);
	}
}


public void displayPlayerTable(ArrayList<player> list) {
	removeAllRows(playerTableModel);
	for (player p: list) {
		int id = p.getId();
		String name = p.getName();
		String nation = p.getNation();
		String position = p.getPosition();
		String team = TeamController.getTeamById(p.getTeamid()).getName();
		String league = TeamController.getTeamById(p.getTeamid()).getLeague();
		int squadnum = p.getSquadnum();
		int ovr = p.getOvr();
		
		Object[] obj = {id, name, nation, position, team, squadnum, league, ovr};
		playerTableModel.addRow(obj);
	}
}
public void displayTeamTable(ArrayList<team> list) {
	removeAllRows(teamTableModel);
	for (team tm: list) {
		String teamid = tm.getTeamid();
		String tname = tm.getName();
		String league = tm.getLeague();
		int playernum = PlayerController.getPlayerNumberOfTeam(tm.getTeamid());
		Object[] obj = {teamid, tname, league, playernum};
		teamTableModel.addRow(obj);
	}
}

public void displayAtkPlayerTable(ArrayList<player> list) {
	removeAllRows(atkPlayerTableModel);
	List<String> atkPos = Arrays.asList( "ST", "CF", "LF" , "RF", "RW", "LW" );
	for (player p: list) {
		if(atkPos.contains(p.getPosition() )){
		int id = p.getId();
		String name = p.getName();
		String nation = p.getNation();
		String position = p.getPosition();
		String team = TeamController.getTeamById(p.getTeamid()).getName();
		String league = TeamController.getTeamById(p.getTeamid()).getLeague();
		int squadnum = p.getSquadnum();
		int ovr = p.getOvr();
		
		Object[] obj = {id, name, nation, position, team, squadnum, league, ovr};
		atkPlayerTableModel.addRow(obj);
	}
	}
}

public void displayMidPlayerTable(ArrayList<player> list) {
	removeAllRows(midPlayerTableModel);
	List<String> midPos = Arrays.asList( "CDM", "CM", "LM" , "RM", "CAM" );
	for (player p: list) {
		if(midPos.contains(p.getPosition() )){
		int id = p.getId();
		String name = p.getName();
		String nation = p.getNation();
		String position = p.getPosition();
		String team = TeamController.getTeamById(p.getTeamid()).getName();
		String league = TeamController.getTeamById(p.getTeamid()).getLeague();
		int squadnum = p.getSquadnum();
		int ovr = p.getOvr();
		
		Object[] obj = {id, name, nation, position, team, squadnum, league, ovr};
		midPlayerTableModel.addRow(obj);
	}
	}
}

public void displayDefPlayerTable(ArrayList<player> list) {
	removeAllRows(defPlayerTableModel);
	List<String> defPos = Arrays.asList("CB", "GK", "LB" , "RB", "RWB", "LWB" );
	for (player p: list) {
		if(defPos.contains(p.getPosition() )){
		int id = p.getId();
		String name = p.getName();
		String nation = p.getNation();
		String position = p.getPosition();
		String team = TeamController.getTeamById(p.getTeamid()).getName();
		String league = TeamController.getTeamById(p.getTeamid()).getLeague();
		int squadnum = p.getSquadnum();
		int ovr = p.getOvr();
		
		Object[] obj = {id, name, nation, position, team, squadnum, league, ovr};
		defPlayerTableModel.addRow(obj);
	}
	}
}

		public void setCurrentUser(user u) {
			this.currentUser = u;
}

		protected void validateButtons() {
			if (currentUser.getAccountType().equals("Admin")) {
				btnDeletePlayer.setEnabled(true);
				btnAddPlayer.setEnabled(true);
				btnUpdatePlayer.setEnabled(true);
				btnDeleteTeam.setEnabled(true);
				btnAddTeam.setEnabled(true);
				btnUpdateTeam.setEnabled(true);
				btnDeleteAtkPlayer.setEnabled(true);
				btnAddAtkPlayer.setEnabled(true);
				btnUpdateAtkPlayer.setEnabled(true);
				btnDeleteMidPlayer.setEnabled(true);
				btnAddMidPlayer.setEnabled(true);
				btnUpdateMidPlayer.setEnabled(true);
				btnDeleteDefPlayer.setEnabled(true);
				btnAddDefPlayer.setEnabled(true);
				btnUpdateDefPlayer.setEnabled(true);
			}
			else {
				btnDeletePlayer.setEnabled(false);
				btnAddPlayer.setEnabled(false);
				btnUpdatePlayer.setEnabled(false);
				btnDeleteTeam.setEnabled(false);
				btnAddTeam.setEnabled(false);
				btnUpdateTeam.setEnabled(false);
				btnDeleteAtkPlayer.setEnabled(false);
				btnAddAtkPlayer.setEnabled(false);
				btnUpdateAtkPlayer.setEnabled(false);
				btnDeleteMidPlayer.setEnabled(false);
				btnAddMidPlayer.setEnabled(false);
				btnUpdateMidPlayer.setEnabled(false);
				btnDeleteDefPlayer.setEnabled(false);
				btnAddDefPlayer.setEnabled(false);
				btnUpdateDefPlayer.setEnabled(false);
			}
}
	

	public class CancelActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit") == 0) {
				getFrame().dispose();
			}
		}
	}
	
	
	
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
}
