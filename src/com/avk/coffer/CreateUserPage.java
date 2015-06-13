package com.avk.coffer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.security.MessageDigest;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CreateUserPage extends JPanel {
	private CofferTextField usernameField;
	private CofferPasswordField passwordField;
	private CofferPasswordField confirmPasswordField;
	private String defaultStatus;
	
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

		usernameField = new CofferTextField("Username", null);
		usernameField.setBounds(215, 200, 360, 40);
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
		add(usernameField);

		passwordField = new CofferPasswordField("Password", null);
		passwordField.setBounds(215, 250, 360, 40);
		passwordField.addMouseListener(new MouseAdapter() {
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
		add(passwordField);

		confirmPasswordField = new CofferPasswordField("Confirm Password", null);
		confirmPasswordField.addMouseListener(new MouseAdapter() {
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
		confirmPasswordField.setBounds(215, 300, 360, 40);
		add(confirmPasswordField);


		CofferButton submit = new CofferButton("Submit");
		submit.setBounds(275, 350, 200, 40);
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try 
				{
					focusGrab.grabFocus();
					defaultStatus = Coffer.getStatus();
					String username = usernameField.getText().trim();
					String password = passwordField.getText().trim();
					String conPass = confirmPasswordField.getText().trim();
					String passPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=|\\/,.;:])(?=\\S+$).{10,}$";
					
					
					if(username.equalsIgnoreCase("username") || username.equalsIgnoreCase("") )
					{
						Coffer.setStatus("Pick a username.");
						usernameField.setExclaim(true);
						passwordField.setExclaim(false);
						confirmPasswordField.setExclaim(false);
					}
					else if(password.equalsIgnoreCase("password") || password.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Pick a password.");
						usernameField.setExclaim(false);
						passwordField.setExclaim(true);
						confirmPasswordField.setExclaim(false);
					}
					else if(!password.matches(passPattern))
					{
						Coffer.setStatus("Click on the Exclaimation for password policy.");
						usernameField.setExclaim(false);
						passwordField.setExclaim(true);
						confirmPasswordField.setExclaim(false);
						passwordField.setText("");
						passwordField.grabFocus();
						passwordField.addExclaimMouseListener(new MouseAdapter() {
							
							@Override
							public void mouseClicked(MouseEvent e) {
								Coffer.setDisable(true);
								String[] strs = {"-> Master password should be atleast 10 characters.", "-> It should include atleast 1 for each of","        * Upper Case Letter","        * Lower Case Letter","        * Number and","        * Symbol"};
								new CofferDialog(true,"Password Policy", strs, CofferDialog.OK_OPTION);
								Coffer.setDisable(false);
							}
						});
					}
					else if(conPass.equalsIgnoreCase("confirm password") || conPass.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Confirm your password.");
						usernameField.setExclaim(false);
						passwordField.setExclaim(false);
						confirmPasswordField.setExclaim(true);
					}
					else if(!password.equals(conPass)){
						Coffer.setStatus("Passwords did not match. :(");
						passwordField.setText("");
						passwordField.grabFocus();
						confirmPasswordField.setText("");
						usernameField.setExclaim(false);
						passwordField.setExclaim(true);
						confirmPasswordField.setExclaim(true);
					}
					else
					{

						MessageDigest digest = MessageDigest.getInstance("SHA-256");
						byte[] hash = digest.digest(password.getBytes("UTF-8"));
						
						Coffer.setStatus("Credentials Submited...");
						usernameField.setExclaim(false);
						passwordField.setExclaim(true);
						confirmPasswordField.setExclaim(false);
	
						long timeStamp = System.currentTimeMillis();
						int key =(int)timeStamp%100000;
						
						CofferCrypt.encrypt2File_Key(new String(hash).substring(0, 16), username + "|" + timeStamp , new File("./Coffer/.cofferkey"));
						CofferReferences.setCofferSeed(timeStamp);
						CofferCrypt.encrypt2File_Index(key, "no_passwords", new File("./Coffer/user's.coffer"));

						Coffer.swapTo("DashBoard");
						Coffer.setStatus("You can make entries in \"Add a Password\" tab.");
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		add(submit);

		JLabel lblCofferlogo = new JLabel();
		lblCofferlogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCofferlogo.setVerticalTextPosition(SwingConstants.TOP);
		lblCofferlogo.setForeground( CofferReferences.CofferVeryLightGrey);
		lblCofferlogo.setIconTextGap(90);
		lblCofferlogo.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblCofferlogo.setFont(CofferReferences.Comfortaa_Bold_80);
		lblCofferlogo.setText("Coffer");
		lblCofferlogo.setBounds(10, 80, 730, 100);
		add(lblCofferlogo);
		
		JLabel lblBackground = new JLabel(CofferReferences.COFFER_BACKGROUND_LAYER);
		lblBackground.setBounds(0, -60, 750, 550);
		add(lblBackground);
	}
}
