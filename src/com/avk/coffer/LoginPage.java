package com.avk.coffer;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.security.MessageDigest;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LoginPage extends JPanel {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private String defaultStatus;

	/**
	 * Create the panel.
	 */
	public LoginPage() {
		setOpaque(false);
		setLayout(null);
		defaultStatus = Coffer.getStatus();
		
		JButton focusGrab = new JButton("");
		focusGrab.setBounds(0, 0, 0, 0);
		focusGrab.grabFocus();
		add(focusGrab);
		
		JLabel lblUserExclaim = new JLabel(CofferRef.EXCLAIM_RED);
		lblUserExclaim.setVisible(false);
		lblUserExclaim.setBounds(530, 240, 40, 40);
		add(lblUserExclaim);
		
		JLabel lblPassExclaim = new JLabel(CofferRef.EXCLAIM_RED);
		lblPassExclaim.setVisible(false);
		lblPassExclaim.setBounds(530, 290, 40, 40);
		add(lblPassExclaim);
		
		usernameField = new JTextField("Username");
		usernameField.setFont(CofferRef.Comfortaa_Plain_14);
		usernameField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameField.setForeground(CofferRef.CofferDarkGrey);
		usernameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(usernameField.getText().equalsIgnoreCase("username")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Your Username please...");
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
		usernameField.setOpaque(false);
		usernameField.setBackground(null);
		usernameField.setBorder(null);
		usernameField.setBounds(235, 245, 280, 30);
		add(usernameField);
		usernameField.setColumns(10);		
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setOpaque(false);
		passwordField.setText("Password");
		passwordField.setFont(CofferRef.Comfortaa_Plain_14);
		passwordField.setForeground(CofferRef.CofferDarkGrey);
		passwordField.setEchoChar((char)0);
		passwordField.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseEntered(MouseEvent e) {
				if(passwordField.getText().equalsIgnoreCase("Password")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Your Password please...");
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
		passwordField.setBackground(null);
		passwordField.setBorder(null);
		passwordField.setBounds(235, 295, 280, 30);
		add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblTextfieldimg = new JLabel(CofferRef.TEXTFIELD_IMG);
		lblTextfieldimg.setBounds(215, 240, 320, 40);
		add(lblTextfieldimg);
		
		JLabel lblPassfieldimg = new JLabel(CofferRef.TEXTFIELD_IMG);
		lblPassfieldimg.setBounds(215, 290, 320, 40);
		add(lblPassfieldimg);
		
		JLabel lblSubmit = new JLabel("Submit");
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
					
					if(username.equalsIgnoreCase("username") || username.equalsIgnoreCase("") )
					{
						Coffer.setStatus("Username please...");
						lblUserExclaim.setVisible(true);
						lblPassExclaim.setVisible(false);
					}
					else if(password.equalsIgnoreCase("password") || password.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Did you forget entering password?");
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(true);
					}
					else if(password.length()<10)
					{
						Coffer.setStatus("Password should be atleast 10 characters.");
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(true);
					}
					else
					{
						MessageDigest digest = MessageDigest.getInstance("SHA-256");
						byte[] hash = digest.digest(password.getBytes("UTF-8"));
						
						StringTokenizer st = new StringTokenizer(CofferCrypt.decryptFromFile_Key(new String(hash).substring(0, 16), new File("./Coffer/.cofferkey")),"|");
						String actUsername = st.nextToken();
						
						CofferRef.setCofferSeed(Long.parseLong(st.nextToken()));
						
						if(username.equals(actUsername))
						{
							Coffer.swapTo("DashBoard");
							Coffer.setStatus("Good to see you back :)");
						}
						else
						{
							usernameField.setText("");
							passwordField.setText("");
							usernameField.grabFocus();
							lblUserExclaim.setVisible(true);
							lblPassExclaim.setVisible(true);
							Coffer.setStatus("Sorry can't log you in. Wrong credentials...");
						}
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(CofferRef.Comfortaa_Plain_14);
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSubmit.setBounds(275, 340, 200, 40);
		add(lblSubmit);
		
		JLabel submitButtonImg = new JLabel(CofferRef.BUTTON_IMG);
		submitButtonImg.setBounds(275, 340, 200, 40);
		add(submitButtonImg);
		
		JLabel lblCoffer = new JLabel();
		lblCoffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoffer.setVerticalTextPosition(SwingConstants.TOP);
		lblCoffer.setForeground( CofferRef.CofferVeryLightGrey);
		lblCoffer.setIconTextGap(90);
		lblCoffer.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblCoffer.setFont(CofferRef.Comfortaa_Bold_80);
		lblCoffer.setText("Coffer");
		lblCoffer.setBounds(10, 80, 730, 100);
		add(lblCoffer);
		
		JLabel lblBackground = new JLabel(CofferRef.COFFER_BACKGROUND_LAYER);
		lblBackground.setBounds(0, -60, 750, 550);
		add(lblBackground);

	}
}
