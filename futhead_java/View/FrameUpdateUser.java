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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import Controller.UserController;
import Model.user;

public class FrameUpdateUser extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel txtId;
	private JLabel txtUserName;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JComboBox<String> cbbType;

	public FrameUpdateUser(MainFrame mainFrame) {
		setTitle("Update User Account");
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
		
		Font normalFont = new Font("Consolas", Font.PLAIN, 13);
		Font boldFont = new Font("Consolas", Font.BOLD, 14);
		
		JLabel lblId = new JLabel("User ID:");
		lblId.setFont(normalFont);
		lblId.setBounds(23, 35, 100, 14);
		panel.add(lblId);
		
		txtId = new JLabel("");
		txtId.setFont(boldFont);
		txtId.setBounds(160, 35, 100, 14);
		panel.add(txtId);
				
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(normalFont);
		lblUsername.setBounds(23, 80, 100, 14);
		panel.add(lblUsername);
		
		txtUserName = new JLabel();
		txtUserName.setFont(boldFont);
		txtUserName.setBounds(160, 80, 200, 14);
		panel.add(txtUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(normalFont);
		lblPassword.setBounds(23, 125, 100, 14);
		panel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(normalFont);
		txtPassword.setBounds(160, 115, 270, 30);
		panel.add(txtPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setFont(normalFont);
		lblConfirmPassword.setBounds(23, 170, 119, 14);
		panel.add(lblConfirmPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(normalFont);
		txtConfirmPassword.setBounds(160, 160, 270, 30);
		panel.add(txtConfirmPassword);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(normalFont);
		lblType.setBounds(23, 215, 46, 14);
		panel.add(lblType);
		
		cbbType = new JComboBox<String>();
		cbbType.setFont(normalFont);
		cbbType.setModel(new DefaultComboBoxModel<String>(new String[] {"Standard", "Admin"}));
		cbbType.setBounds(160, 215, 270, 30);
		panel.add(cbbType);
		
		JButton btnUpdate = new JButton("Update");
		getRootPane().setDefaultButton(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int userid = Integer.parseInt(txtId.getText());
				String username = txtUserName.getText();
				char[] password = txtPassword.getPassword();
				char[] confirmPassword = txtConfirmPassword.getPassword();
				String type = cbbType.getSelectedItem().toString();
				
				if (Arrays.equals(password, confirmPassword)) {
					UserController.updateUser(userid, username, password, type);

					getFrame().dispose();
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Confirm password is wrong. Please retype!");
				}
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
					btnUpdate.setEnabled(true);
				else btnUpdate.setEnabled(false);
			}
		};
		txtPassword.getDocument().addDocumentListener(documentListener);
	}
	
	public FrameUpdateUser getFrame() {
		return this;
	}
	
	public void displayAccountInformation(user u) {
		txtId.setText(Integer.toString(u.getUserid()));
		txtUserName.setText(u.getUsername());
		txtPassword.setText(new String(u.getPassword()));
		txtConfirmPassword.setText(new String(u.getPassword()));
		cbbType.setSelectedItem(u.getAccountType());
	}
}
