package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import Controller.UserController;
import Model.user;

public class FrameAddUser extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public FrameAddUser(MainFrame mainFrame) {
		setTitle("Create new account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 452, 429);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("User ID:");
		lblId.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblId.setBounds(23, 35, 100, 14);
		panel.add(lblId);
		
		JLabel txtId = new JLabel("");
		txtId.setFont(new Font("Consolas", Font.BOLD, 12));
		txtId.setBounds(160, 35, 100, 14);
		txtId.setText(Integer.toString(user.getNextId()));
		panel.add(txtId);
				
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblUsername.setBounds(23, 80, 100, 14);
		panel.add(lblUsername);
		
		JTextField txtUserName = new JTextField();
		txtUserName.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtUserName.setBounds(160, 80, 270, 30);
		panel.add(txtUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblPassword.setBounds(23, 125, 100, 14);
		panel.add(lblPassword);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtPassword.setBounds(160, 115, 270, 30);
		panel.add(txtPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblConfirmPassword.setBounds(23, 170, 119, 14);
		panel.add(lblConfirmPassword);
		
		JPasswordField txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtConfirmPassword.setBounds(160, 160, 270, 30);
		panel.add(txtConfirmPassword);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblType.setBounds(23, 215, 46, 14);
		panel.add(lblType);
		
		JComboBox<String> cbbType = new JComboBox<String>();
		cbbType.setFont(new Font("Consolas", Font.PLAIN, 13));
		cbbType.setModel(new DefaultComboBoxModel<String>(new String[] {"Standard", "Admin"}));
		cbbType.setBounds(160, 215, 270, 30);
		panel.add(cbbType);
		
		JButton btnCreate = new JButton("Create");
		getRootPane().setDefaultButton(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int userid = user.getNextId();
				String username = txtUserName.getText();
				char[] password = txtPassword.getPassword();
				char[] confirmPassword = txtConfirmPassword.getPassword();
				String type = cbbType.getSelectedItem().toString();
				
				if (username.indexOf(" ") != -1) {
					JOptionPane.showMessageDialog(contentPane, "Username is not valid!");
				}
					else if (!Arrays.equals(password, confirmPassword)) {
						JOptionPane.showMessageDialog(contentPane, "Confirm password is wrong!");
					}
						else {
							if (!UserController.addUser(userid, username, password, type)) {
								JOptionPane.showMessageDialog(contentPane, "This username has already existed!");
							}
							else {
								JOptionPane.showMessageDialog(contentPane, "Create successfully!");
								getFrame().dispose();
							}
						}
			}
		});
		btnCreate.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCreate.setBounds(231, 387, 88, 30);
		panel.add(btnCreate);
		
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
				if ((txtPassword.getPassword().length != 0) && !txtUserName.getText().equals(""))
					btnCreate.setEnabled(true);
				else btnCreate.setEnabled(false);
			}
		};
		txtUserName.getDocument().addDocumentListener(documentListener);
		txtPassword.getDocument().addDocumentListener(documentListener);
	}

	public FrameAddUser getFrame() {
		return this;
	}
}
