package com.avk.coffer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.security.MessageDigest;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class LoginPage extends JPanel {
	private CofferTextField usernameField;
	private CofferPasswordField passwordField;
	private String defaultStatus;

	/**
	 * Create the panel.
	 */
	public LoginPage() {
		setPreferredSize(new Dimension(750, 460));
		setOpaque(false);
		setLayout(null);
		defaultStatus = Coffer.getStatus();
		
		JButton focusGrab = new JButton("");
		focusGrab.setBounds(0, 0, 0, 0);
		focusGrab.grabFocus();
		add(focusGrab);

		JLabel lblCoffer = new JLabel("Coffer");
		lblCoffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoffer.setVerticalTextPosition(SwingConstants.TOP);
		lblCoffer.setForeground( CofferReferences.CofferVeryLightGrey);
		lblCoffer.setIconTextGap(90);
		lblCoffer.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblCoffer.setFont(CofferReferences.Comfortaa_Bold_80);
		lblCoffer.setBounds(0, 90, 750, 80);
		add(lblCoffer);

		usernameField = new CofferTextField("Username",null);
		usernameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(usernameField.getText().equals("")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Your Username please.    :|");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		usernameField.setBounds(215, 230, 320, 40);
		add(usernameField);
		
		
		passwordField = new CofferPasswordField("Password",null);
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(passwordField.getText().equals("")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Your Password please.    :|");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		passwordField.setBounds(215, 280, 320, 40);
		add(passwordField);
		
		
		CofferButton submit = new CofferButton("Submit");
		submit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try 
				{
					focusGrab.grabFocus();
					defaultStatus = Coffer.getStatus();
					String username = usernameField.getText().trim();
					String password = passwordField.getText().trim();
					String passPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=|\\/,.;:])(?=\\S+$).{10,}$";
					
					if( username.equalsIgnoreCase("") )
					{
						Coffer.setStatus("Username please.    :|");
						usernameField.setValid(false);
						passwordField.setValid(true);
					}
					else if( password.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Did you forget entering password?    :/");
						usernameField.setValid(true);
						passwordField.setValid(false);
					}
					else if(!password.matches(passPattern))
					{
						Coffer.setStatus("Don't you remember the Password Policy?    O.o");
						usernameField.setValid(true);
						passwordField.setValid(false);
						passwordField.setText("");
						passwordField.grabFocus();
					}
					else
					{
						MessageDigest digest = MessageDigest.getInstance("SHA-256");
						byte[] hash = digest.digest(password.getBytes("UTF-8"));
						
						StringTokenizer st = new StringTokenizer(CofferCrypt.decryptFromFile_Key(new String(hash).substring(0, 16), new File("./Coffer/.cofferkey")),"|");
						String actUsername = st.nextToken();
						
						CofferCrypt.setCofferSeed(Long.parseLong(st.nextToken()));
						
						if(username.equals(actUsername))
						{
							Coffer.user_logged_in = true;
							Coffer.swapTo(Coffer.AllPasswordsPage);
							Coffer.setStatus("Good to see you back.    :)");
						}
						else
						{
							usernameField.setText("");
							passwordField.setText("");
							usernameField.grabFocus();
							usernameField.setValid(false);
							passwordField.setValid(false);
							Coffer.setStatus("Sorry can't log you in. Wrong credentials.    :'(");
						}
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		submit.setBounds(275, 330, 200, 40);
		add(submit);
		
	}
}
