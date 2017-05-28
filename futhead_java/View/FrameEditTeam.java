package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controller.TeamController;
import Model.team;

public class FrameEditTeam extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtId;
	private JLabel txtOldId;
	private JTextField txtName;
	private JTextField txtLeague;
	
	public FrameEditTeam(MainFrame mainFrame) {
		setTitle("Edit team information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Team Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 452, 429);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Font normalFont = new Font("Consolas", Font.PLAIN, 13);
		Font boldFont = new Font("Consolas", Font.BOLD, 14);
		

		JLabel lblId = new JLabel("Team's ID:");
		lblId.setFont(normalFont);
		lblId.setBounds(23, 35, 100, 14);
		panel.add(lblId);
		
		txtOldId = new JLabel("");
		
		txtId = new JTextField();
		txtId.setFont(new Font("Consolas", Font.BOLD, 12));
		txtId.setBounds(160, 35, 100, 26);
		panel.add(txtId);
		
		JLabel lblName = new JLabel("Team name:");
		lblName.setFont(normalFont);
		lblName.setBounds(23, 125, 100, 14);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(boldFont);
		txtName.setBounds(160, 115, 270, 30);
		panel.add(txtName);
		
		JLabel lblLeague = new JLabel("League:");
		lblLeague.setFont(normalFont);
		lblLeague.setBounds(23, 215, 67, 14);
		panel.add(lblLeague);
		
		txtLeague = new JTextField();
		txtLeague.setFont(boldFont);
		txtLeague.setBounds(160, 205, 270, 30);
		panel.add(txtLeague);
		
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
		
		JButton btnUpdate = new JButton("Update");
		getRootPane().setDefaultButton(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String id = txtId.getText();
				String name = txtName.getText();
				String league = txtLeague.getText();
				String oldteamid = txtOldId.getText();
				TeamController.updateTeam(id, oldteamid, name, league);
				mainFrame.displayTeamTable(TeamController.list);
				getFrame().dispose();
			}
		});
		btnUpdate.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnUpdate.setBounds(231, 387, 88, 30);
		panel.add(btnUpdate);
	}
	public FrameEditTeam getFrame() {
		return this;
	}
	public void displayTeamInformation(team tm) {
		txtOldId.setText(tm.getTeamid());
		txtId.setText(tm.getTeamid());
		txtName.setText(tm.getName());
		txtLeague.setText(tm.getLeague());
	}
}
