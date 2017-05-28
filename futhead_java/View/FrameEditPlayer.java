package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;

import Controller.PlayerController;
import Controller.TeamController;
import Model.player;

public class FrameEditPlayer extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel txtId;
	private JTextField txtName;
	private JTextField txtNation;
	private JComboBox<String> cbbPosition;
	private JComboBox<String> cbbTeam;
	private JTextField txtSquadnum;
	private JTextField txtOvr;
	
	public FrameEditPlayer(MainFrame mainFrame) {
		setTitle("Edit player information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Player Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 452, 429);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Font normalFont = new Font("Consolas", Font.PLAIN, 13);
		Font boldFont = new Font("Consolas", Font.BOLD, 14);
		
		JLabel lblId = new JLabel("Player's ID:");
		lblId.setFont(normalFont);
		lblId.setBounds(23, 35, 100, 14);
		panel.add(lblId);
		
		txtId = new JLabel("");
		txtId.setFont(boldFont);
		txtId.setBounds(160, 35, 100, 14);
		panel.add(txtId);
		
		JLabel lblName = new JLabel("Player name:");
		lblName.setFont(normalFont);
		lblName.setBounds(23, 80, 100, 14);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(boldFont);
		txtName.setBounds(160, 80, 200, 14);
		panel.add(txtName);
		
		JLabel lblNation = new JLabel("Nation:");
		lblNation.setFont(normalFont);
		lblNation.setBounds(23, 125, 100, 14);
		panel.add(lblNation);
		
		txtNation = new JTextField();
		txtNation.setFont(boldFont);
		txtNation.setBounds(160, 115, 270, 30);
		panel.add(txtNation);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setFont(normalFont);
		lblPosition.setBounds(23, 170, 100, 14);
		panel.add(lblPosition);
		
		cbbPosition = new JComboBox<String>();
		cbbPosition.setFont(boldFont);
		cbbPosition.setModel(new DefaultComboBoxModel<String>(new String[] {"GK", "CB", "LB", "RB", "LWB", "RWB", "CM", "CDM", "LM", "RM", "CAM", "CF", "ST", "LW", "RW", "LF", "RF"}));
		cbbPosition.setBounds(160, 160, 270, 30);
		panel.add(cbbPosition);
		
		JLabel lblTeamid = new JLabel("Team ID:");
		lblTeamid.setFont(normalFont);
		lblTeamid.setBounds(23, 215, 67, 14);
		panel.add(lblTeamid);
		
		cbbTeam = new JComboBox<String>();
		cbbTeam.setFont(boldFont);
		cbbTeam.setModel(new DefaultComboBoxModel<String>(TeamController.getTeamList()));
		cbbTeam.setBounds(160, 205, 270, 30);
		panel.add(cbbTeam);
		
		JLabel lblSquadnum = new JLabel("Squad Number:");
		lblSquadnum.setFont(normalFont);
		lblSquadnum.setBounds(23, 260, 100, 14);
		panel.add(lblSquadnum);
		
		txtSquadnum = new JTextField();
		txtSquadnum.setFont(boldFont);
		txtSquadnum.setBounds(160, 250, 270, 30);
		panel.add(txtSquadnum);
		
		JLabel lblOvr = new JLabel("OVR:");
		lblOvr.setFont(normalFont);
		lblOvr.setBounds(23, 305, 119, 14);
		panel.add(lblOvr);
		
		txtOvr = new JTextField();
		txtOvr.setFont(boldFont);
		txtOvr.setBounds(160, 295, 270, 30);
		panel.add(txtOvr);
		
		//update button
		JButton btnUpdate = new JButton("Update");
		getRootPane().setDefaultButton(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int id = Integer.parseInt(txtId.getText());
				String name = txtName.getText();
				String nation = txtNation.getText();
				String position = cbbPosition.getSelectedItem().toString();
				String teamid = TeamController.getTeamByName(cbbTeam.getSelectedItem().toString()).getTeamid();
				int squadnum = Integer.parseInt(txtSquadnum.getText());
				int ovr = Integer.parseInt(txtOvr.getText());
				
				PlayerController.updatePlayer(id, name, nation, position, ovr, teamid, squadnum);
				mainFrame.displayPlayerTable(PlayerController.list);
				getFrame().dispose();
			}
		});
		btnUpdate.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnUpdate.setBounds(231, 387, 88, 30);
		panel.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit") == 0) {
					getFrame().dispose();
				}
			}
		});
		btnCancel.setBounds(342, 387, 88, 30);
		panel.add(btnCancel);

	}

	public FrameEditPlayer getFrame() {
		return this;
	}
	public void displayPlayerInformation(player p) {
		txtId.setText(Integer.toString(p.getId()));
		txtName.setText(p.getName());
		txtNation.setText(p.getNation());
		cbbPosition.setSelectedItem(p.getPosition());
		cbbTeam.setSelectedItem(TeamController.getTeamById(p.getTeamid()).getName());
		txtSquadnum.setText(Integer.toString(p.getSquadnum()));
		txtOvr.setText(Integer.toString(p.getOvr()));
	}
}
