package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;

import Controller.TeamController;
//import Model.team;

public class FrameAddTeam extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public FrameAddTeam(MainFrame mainFrame) {
		setTitle("Add new team");
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
		
		JLabel lblId = new JLabel("Team ID:");
		lblId.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblId.setBounds(23, 35, 100, 14);
		panel.add(lblId);
		
		JTextField txtId = new JTextField();
		txtId.setFont(new Font("Consolas", Font.BOLD, 12));
		txtId.setBounds(160, 35, 100, 26);
		//txtId.setText(Integer.toString(team.getNextId()));
		panel.add(txtId);
		
		JLabel lblName = new JLabel("Team name:");
		lblName.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblName.setBounds(23, 125, 100, 14);
		panel.add(lblName);
		
		JTextField txtName = new JTextField();
		txtName.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtName.setBounds(160, 115, 270, 30);
		panel.add(txtName);
		
		JLabel lblLeague = new JLabel("League:");
		lblLeague.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblLeague.setBounds(23, 215, 67, 14);
		panel.add(lblLeague);
		
		JTextField txtLeague = new JTextField();
		txtLeague.setFont(new Font("Consolas", Font.PLAIN, 13));
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
		
		JButton btnAdd = new JButton("Add");
		getRootPane().setDefaultButton(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String id = txtId.getText();
				String name = txtName.getText();
				String league = txtLeague.getText();
				if (id.indexOf(" ") != -1)
					JOptionPane.showMessageDialog(contentPane, "Team ID is not valid!");
				else {
					if (name.length() <= 0)
						JOptionPane.showMessageDialog(contentPane, "Team Name is not valid!");
					else{
						if (league.length() <= 0)
						JOptionPane.showMessageDialog(contentPane, "League is not valid!");
						else {
							if (!TeamController.addTeam(id, name, league))
								JOptionPane.showMessageDialog(contentPane, "This team has already existed!");
							else {
								JOptionPane.showMessageDialog(contentPane, "Added successfully!");
								if (mainFrame != null)
									mainFrame.displayTeamTable(TeamController.list);
								getFrame().dispose();
							}
						}
					}
				}
			}
		});
		btnAdd.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnAdd.setBounds(231, 387, 88, 30);
		panel.add(btnAdd);
		
	}
	public FrameAddTeam getFrame() {
		return this;
	}
}
