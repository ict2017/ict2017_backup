package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.UserController;
import Model.user;
import java.awt.Color;
import java.awt.SystemColor;

public class FrameLogIn extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogIn frame = new FrameLogIn();
					frame.setVisible(true);
				} catch (Exception u) {
					u.printStackTrace();
				}
			}
		});
	}

	public FrameLogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 727, 262);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to FIFA Player Database");
		lblWelcome.setFont(new Font("Bookman Old Style", Font.BOLD, 27));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(0, 5, 733, 54);
		panel.add(lblWelcome);
		
		JLabel lblLogIn = new JLabel("Log in");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setFont(new Font("Consolas", Font.BOLD, 25));
		lblLogIn.setBounds(0, 90, 733, 44);
		panel.add(lblLogIn);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblUsername.setBounds(183, 146, 80, 20);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblPassword.setBounds(183, 185, 80, 20);
		panel.add(lblPassword);

		JTextField txtUsername = new JTextField();
		txtUsername.setBackground(SystemColor.menu);
		txtUsername.setFont(new Font("Consolas", Font.PLAIN, 15));
		txtUsername.setBounds(285, 140, 280, 30);
		panel.add(txtUsername);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBackground(SystemColor.menu);
		txtPassword.setFont(new Font("Consolas", Font.PLAIN, 15));
		txtPassword.setBounds(285, 180, 280, 30);
		panel.add(txtPassword);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.setFont(new Font("Consolas", Font.PLAIN, 12));
		getRootPane().setDefaultButton(btnLogIn);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = txtUsername.getText();
				char[] password = txtPassword.getPassword();
				
				boolean isDone = false;
				
				for (user u: UserController.list) {
					if (u.getUsername().equals(username)) {
						if (Arrays.equals(u.getPassword(), password)) {
							JOptionPane.showMessageDialog(contentPane, "Hello " + u.getUsername());
							isDone = true;
							MainFrame frame = new MainFrame();
							frame.setCurrentUser(u);
							frame.setVisible(true);
							frame.validateButtons();
							getFrame().dispose();
							break;
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Wrong password!");
							txtPassword.setText(null);
							isDone = true;
						}
					}
				}
				if (!isDone) {
					JOptionPane.showMessageDialog(contentPane, "Username not found.");
					txtUsername.setText(null);
					txtPassword.setText(null);
				}
			}
		});
		btnLogIn.setBounds(395, 221, 80, 25);
		btnLogIn.setEnabled(false);
		panel.add(btnLogIn);
		
		JButton btnCancel = new JButton("Exit");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit") == 0) {
					getFrame().dispose();
				}
			}
		});
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnCancel.setBounds(485, 221, 80, 25);
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
				if (!txtUsername.getText().equals("") && (txtPassword.getPassword().length != 0))
					btnLogIn.setEnabled(true);
				else btnLogIn.setEnabled(false);
			}
		};
		txtUsername.getDocument().addDocumentListener(documentListener);
		txtPassword.getDocument().addDocumentListener(documentListener);
	}

	public FrameLogIn getFrame() {
		return this;
	}
}
