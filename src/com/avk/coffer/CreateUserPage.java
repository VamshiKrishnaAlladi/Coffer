package com.avk.coffer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.security.MessageDigest;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CreateUserPage extends JPanel {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private String defaultStatus;
//	private BufferedWriter fileWriter;
	
	/**
	 * Create the panel.
	 */
	public CreateUserPage() {
		setOpaque(false);
		setLayout(null);

		JButton focusGrab = new JButton("");
		focusGrab.setBounds(0, 0, 0, 0);
		focusGrab.grabFocus();
		add(focusGrab);

		JLabel lblUserExclaim = new JLabel(new ImageIcon(this.getClass().getResource("/exclaimRed.png")));
		lblUserExclaim.setVisible(false);
		lblUserExclaim.setBounds(530, 200, 40, 40);
		add(lblUserExclaim);

		JLabel lblPassExclaim = new JLabel(new ImageIcon(this.getClass().getResource("/exclaimRed.png")));
		lblPassExclaim.setVisible(false);
		lblPassExclaim.setBounds(530, 250, 40, 40);
		add(lblPassExclaim);

		JLabel lblConPassExclaim = new JLabel(new ImageIcon(this.getClass().getResource("/exclaimRed.png")));
		lblConPassExclaim.setVisible(false);
		lblConPassExclaim.setBounds(530, 300, 40, 40);
		add(lblConPassExclaim);

		usernameField = new JTextField("Username");
		usernameField.setFont(new Font("Comfortaa", Font.PLAIN, 14));
		usernameField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameField.setForeground(new Color(75,75,75));
		usernameField.setOpaque(false);
		usernameField.setBackground(null);
		usernameField.setBorder(null);
		usernameField.setBounds(235, 205, 280, 30);
		usernameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(usernameField.getText().equalsIgnoreCase("username")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Enter your Username here...");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		usernameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(usernameField.getText().equalsIgnoreCase("")){
					usernameField.setText("Username");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(usernameField.getText().equalsIgnoreCase("username")){
					usernameField.setText("");
				}
			}
		});
		add(usernameField);

		JLabel lblTextfieldImg = new JLabel(new ImageIcon(this.getClass().getResource("/longerRoundTextField.png")));
		lblTextfieldImg.setBounds(215, 200, 320, 40);
		add(lblTextfieldImg);

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setOpaque(false);
		passwordField.setText("Password");
		passwordField.setFont(new Font("Comfortaa", Font.PLAIN, 14));
		passwordField.setForeground(new Color(75,75,75));
		passwordField.setEchoChar((char)0);
		passwordField.setBackground(null);
		passwordField.setBorder(null);
		passwordField.setBounds(235, 255, 280, 30);
		passwordField.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseEntered(MouseEvent e) {
				if(passwordField.getText().equalsIgnoreCase("Password")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Enter your Password here...");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		passwordField.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if(passwordField.getText().equalsIgnoreCase("")){
					passwordField.setEchoChar((char) 0);
					passwordField.setText("Password");
				}
			}
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(passwordField.getText().equalsIgnoreCase("Password")){
					passwordField.setEchoChar('#');
					passwordField.setText("");
				}
			}
		});
		add(passwordField);

		JLabel lblPassfieldImg = new JLabel(new ImageIcon(this.getClass().getResource("/longerRoundTextField.png")));
		lblPassfieldImg.setBounds(215, 250, 320, 40);
		add(lblPassfieldImg);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setHorizontalAlignment(SwingConstants.LEFT);
		confirmPasswordField.setOpaque(false);
		confirmPasswordField.setText("Confirm Password");
		confirmPasswordField.setFont(new Font("Comfortaa", Font.PLAIN, 14));
		confirmPasswordField.setForeground(new Color(75,75,75));
		confirmPasswordField.setEchoChar((char)0);
		confirmPasswordField.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseEntered(MouseEvent e) {
				if(confirmPasswordField.getText().equalsIgnoreCase("Confirm Password")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Retype your password here...");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		confirmPasswordField.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if(confirmPasswordField.getText().equalsIgnoreCase("")){
					confirmPasswordField.setEchoChar((char) 0);
					confirmPasswordField.setText("Confirm Password");
				}
			}
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(confirmPasswordField.getText().equalsIgnoreCase("Confirm Password")){
					confirmPasswordField.setEchoChar('#');
					confirmPasswordField.setText("");
				}
			}
		});
		confirmPasswordField.setBackground(null);
		confirmPasswordField.setBorder(null);
		confirmPasswordField.setBounds(235, 305, 280, 30);
		add(confirmPasswordField);
		confirmPasswordField.setColumns(10);

		JLabel lblConfirmPassfieldImg = new JLabel(new ImageIcon(this.getClass().getResource("/longerRoundTextField.png")));
		lblConfirmPassfieldImg.setBounds(215, 300, 320, 40);
		add(lblConfirmPassfieldImg);

		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(new Font("Comfortaa", Font.PLAIN, 14));
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSubmit.setBounds(275, 350, 200, 40);
		lblSubmit.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				try 
				{
					focusGrab.grabFocus();
					defaultStatus = Coffer.getStatus();
					String username = usernameField.getText().trim();
					String password = passwordField.getText().trim();
					String conPass = confirmPasswordField.getText().trim();
					
					
					if(username.equalsIgnoreCase("username") || username.equalsIgnoreCase("") )
					{
						Coffer.setStatus("Pick a username.");
						lblUserExclaim.setVisible(true);
						lblPassExclaim.setVisible(false);
						lblConPassExclaim.setVisible(false);
					}
					else if(password.equalsIgnoreCase("password") || password.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Pick a password.");
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(true);
						lblConPassExclaim.setVisible(false);
					}
					else if(password.length()<10)
					{
						Coffer.setStatus("Password should be atleast 10 characters.");
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(true);
						lblConPassExclaim.setVisible(false);
					}
					else if(conPass.equalsIgnoreCase("confirm password") || conPass.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Confirm your password.");
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(false);
						lblConPassExclaim.setVisible(true);
					}
					else if(!password.equals(conPass)){
						Coffer.setStatus("Passwords did not match. :(");
						passwordField.setText("");
						passwordField.grabFocus();
						confirmPasswordField.setText("");
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(true);
						lblConPassExclaim.setVisible(true);
					}
					else
					{

						MessageDigest digest = MessageDigest.getInstance("SHA-256");
						byte[] hash = digest.digest(password.getBytes("UTF-8"));
						
						Coffer.setStatus("Credentials Submited...");
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(false);
						lblConPassExclaim.setVisible(false);
	
						long timeStamp = System.currentTimeMillis();
						int key =(int)timeStamp%100000;
						
						CofferCrypt.encrypt2File_Key(new String(hash).substring(0, 16), username + "|" + timeStamp , new File("./Coffer/.cofferkey"));
						CofferRef.setCofferSeed(timeStamp);
						CofferCrypt.encrypt2File_Index(key, "no_passwords", new File("./Coffer/user's.coffer"));

						Coffer.swapTo("DashBoard");
						Coffer.setStatus("You can make entries in \"Add a Password\" tab.");
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		add(lblSubmit);

		JLabel submitButtonImg = new JLabel(new ImageIcon(this.getClass().getResource("/button.png")));
		submitButtonImg.setBounds(275, 350, 200, 40);
		add(submitButtonImg);

		JLabel lblCofferlogo = new JLabel();
		lblCofferlogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCofferlogo.setVerticalTextPosition(SwingConstants.TOP);
		lblCofferlogo.setForeground( new Color(150,150,150));
		lblCofferlogo.setIconTextGap(90);
		lblCofferlogo.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblCofferlogo.setFont(new Font("Comfortaa", Font.BOLD , 80));
		lblCofferlogo.setText("Coffer");
		lblCofferlogo.setBounds(10, 80, 730, 100);
		add(lblCofferlogo);
		
		JLabel lblBackground = new JLabel(new ImageIcon(this.getClass().getResource("/backgroundLayer.png")));
		lblBackground.setBounds(0, -60, 750, 550);
		add(lblBackground);
	}
}
