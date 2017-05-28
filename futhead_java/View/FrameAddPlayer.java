package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Arrays;

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

public class FrameAddPlayer extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public FrameAddPlayer(MainFrame mainFrame) {
		setTitle("Add new player");
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
		
		JLabel lblId = new JLabel("Player ID:");
		lblId.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblId.setBounds(23, 35, 100, 14);
		panel.add(lblId);
		
		JLabel txtId = new JLabel("");
		txtId.setFont(new Font("Consolas", Font.BOLD, 12));
		txtId.setBounds(160, 35, 100, 14);
		txtId.setText(Integer.toString(player.getNextId()));
		panel.add(txtId);
		
		JLabel lblName = new JLabel("Player Name:");
		lblName.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblName.setBounds(23, 80, 100, 14);
		panel.add(lblName);
		
		JTextField txtName = new JTextField();
		txtName.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtName.setBounds(160, 70, 270, 30);
		panel.add(txtName);
		
		JLabel lblNation = new JLabel("Nation:");
		lblNation.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblNation.setBounds(23, 125, 100, 14);
		panel.add(lblNation);
		
		JTextField txtNation = new JTextField();
		txtNation.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtNation.setBounds(160, 115, 270, 30);
		panel.add(txtNation);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblPosition.setBounds(23, 170, 100, 14);
		panel.add(lblPosition);
		
		JComboBox<String> cbbPosition = new JComboBox<String>();
		cbbPosition.setFont(new Font("Consolas", Font.PLAIN, 13));
		cbbPosition.setModel(new DefaultComboBoxModel<String>(new String[] {"GK", "CB", "LB", "RB", "LWB", "RWB", "CM", "CDM", "LM", "RM", "CAM", "CF", "ST", "LW", "RW", "LF", "RF"}));
		cbbPosition.setBounds(160, 160, 270, 30);
		panel.add(cbbPosition);
		
		JLabel lblTeam = new JLabel("Team:");
		lblTeam.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblTeam.setBounds(23, 215, 67, 14);
		panel.add(lblTeam);
		
		JComboBox<String> cbbTeam = new JComboBox<String>();
		cbbTeam.setFont(new Font("Consolas", Font.PLAIN, 13));
		cbbTeam.setModel(new DefaultComboBoxModel<String>(TeamController.getTeamList()));
		cbbTeam.setBounds(160, 205, 270, 30);
		panel.add(cbbTeam);
		
		JLabel lblSquadnum = new JLabel("Squad Number:");
		lblSquadnum.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblSquadnum.setBounds(23, 260, 100, 14);
		panel.add(lblSquadnum);
		
		JTextField txtSquadnum = new JTextField();
		txtSquadnum.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtSquadnum.setBounds(160, 250, 270, 30);
		panel.add(txtSquadnum);
		
		JLabel lblOvr = new JLabel("OVR:");
		lblOvr.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblOvr.setBounds(23, 305, 119, 14);
		panel.add(lblOvr);
		
		JTextField txtOvr = new JTextField();
		txtOvr.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtOvr.setBounds(160, 295, 270, 30);
		panel.add(txtOvr);
		
		JButton btnAdd = new JButton("Add");
		getRootPane().setDefaultButton(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int id = player.getNextId();
				String name = txtName.getText();
				String nation = txtNation.getText();
				String position = cbbPosition.getSelectedItem().toString();
				String teamid = TeamController.getTeamByName(cbbTeam.getSelectedItem().toString()).getTeamid();

				int squadnum = Integer.parseInt(txtSquadnum.getText());
				int ovr = Integer.parseInt(txtOvr.getText());
				if (name.length() <= 0)
					JOptionPane.showMessageDialog(contentPane, "Player Name is not valid!");
				else {
					if (nation.length() <= 0)
						JOptionPane.showMessageDialog(contentPane, "Nation is not valid!");

						else {
							if (!PlayerController.addPlayer(id, name, nation, position, ovr, teamid, squadnum))
								JOptionPane.showMessageDialog(contentPane, "This player has already existed!");
							else {
								JOptionPane.showMessageDialog(contentPane, "Added successfully!");
								if (mainFrame != null)
									mainFrame.displayPlayerTable(PlayerController.list);
								getFrame().dispose();
							}
						}
				}
			}
		})
		;
		btnAdd.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnAdd.setBounds(231, 387, 88, 30);
		panel.add(btnAdd);
		//Cancel
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
		/*an nut add
		DocumentListener documentListener = new DocumentListener() {
			public void changedUpdate(DocumentEvent event) {
				change();
			}
			
			public void insertUpdate(DocumentEvent event) {
				change();
			}
			
			public void removeUpdate(DocumentEvent event) {
				change();
			}
			
			public void change() {
				if (!txtName.getText().equals("") && !txtNation.getText().equals("") && !txtTeamid.getText().equals("") && !txtSquadnum.getText().equals("") && !txtOvr.getText().equals(""))					
					btnAdd.setEnabled(true);
				else btnAdd.setEnabled(false);
			}
		};
		txtName.getDocument().addDocumentListener(documentListener);
		txtNation.getDocument().addDocumentListener(documentListener);
		txtTeamid.getDocument().addDocumentListener(documentListener);
		txtSquadnum.getDocument().addDocumentListener(documentListener);
		txtOvr.getDocument().addDocumentListener(documentListener);*/
	}
	public FrameAddPlayer getFrame() {
		return this;
	}
	
	
}
