package com.avk.coffer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AddEntryPage extends JPanel {
	private CofferTextField usernameField;
	private CofferTextField titleField;
	private CofferPasswordField passwordField;
	private CofferPasswordField confirmPasswordField;
	private String defaultStatus;
	
	/**
	 * Create the panel.
	 */
	public AddEntryPage() {
		setOpaque(false);
		setLayout(null);

		JButton focusGrab = new JButton("");
		focusGrab.setBounds(0, 0, 0, 0);
		focusGrab.grabFocus();
		add(focusGrab);
		
		titleField = new CofferTextField("Title",null);
		titleField.setBounds(215, 115, 320, 40);
		titleField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(titleField.getText().equalsIgnoreCase("title")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Enter the Title of entry here...");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		add(titleField);
		
		usernameField = new CofferTextField("Username",null);
		usernameField.setBounds(215, 165, 320, 40);
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
		passwordField.setBounds(215, 215, 320, 40);
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

		confirmPasswordField = new CofferPasswordField("Confirm Password",null);
		confirmPasswordField.setBounds(215, 265, 320, 40);
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
		add(confirmPasswordField);
		
		CofferButton submit = new CofferButton("Submit");
		submit.setBounds(275, 315, 200, 40);
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try 
				{
					focusGrab.grabFocus();
					defaultStatus = Coffer.getStatus();
					String title = titleField.getText().trim();
					String username = usernameField.getText().trim();
					String password = passwordField.getText().trim();
					String conPass = confirmPasswordField.getText().trim();
					if(title.equalsIgnoreCase("title")|| title.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Oops! Title is important and so mandatory.");
						titleField.setValid(false);
						usernameField.setValid(true);
						passwordField.setValid(true);
						confirmPasswordField.setValid(true);
					}
					else if( username.equalsIgnoreCase("") )
					{
						Coffer.setStatus("Pick a username.");
						titleField.setValid(true);
						usernameField.setValid(false);
						passwordField.setValid(true);
						confirmPasswordField.setValid(true);
					}
					else if( password.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Pick a password.");
						titleField.setValid(true);
						usernameField.setValid(true);
						passwordField.setValid(false);
						confirmPasswordField.setValid(true);
					}
					else if( conPass.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Confirm your password.");
						titleField.setValid(true);
						usernameField.setValid(true);
						passwordField.setValid(true);
						confirmPasswordField.setValid(false);
					}
					else if(!password.equals(conPass)){
						Coffer.setStatus("Passwords did not match. :(");
						passwordField.setText("");
						passwordField.grabFocus();
						confirmPasswordField.setText("");
						titleField.setValid(true);
						usernameField.setValid(true);
						passwordField.setValid(false);
						confirmPasswordField.setValid(false);
					}
					else
					{
						titleField.setValid(true);
						usernameField.setValid(true);
						passwordField.setValid(true);
						confirmPasswordField.setValid(true);
						
						Random r = new Random();
						int index;
						int fileNo;
						String user_coffer = CofferCrypt.decryptFromFile_Index(CofferCrypt.getCofferKeyIndex(), new File("./Coffer/user's.coffer"));
						do{
							index = r.nextInt(CofferCrypt.MAX_KEY_INDEX);
						}
						while(user_coffer.contains(Integer.toString(index).subSequence(0, 4)));
						
						do{
							fileNo = r.nextInt(100000000);
						}
						while(user_coffer.contains(Integer.toString(fileNo).subSequence(0, 8)));
						
						if(user_coffer.equals("no_passwords")){
							user_coffer = title + "|" + Integer.toString(fileNo) + "|" + Integer.toString(index);
						}
						else
						{
							user_coffer += "\n" + title + "|" + Integer.toString(fileNo) + "|" + Integer.toString(index);
						}
						CofferCrypt.encrypt2File_Index(CofferCrypt.getCofferKeyIndex(), user_coffer, new File("./Coffer/user's.coffer"));
						CofferCrypt.encrypt2File_Index(index, username + "|" + password, new File("./Coffer/" + fileNo + ".cofferpass"));

						titleField.setText("");
						usernameField.setText("");
						passwordField.setText("");
						confirmPasswordField.setText("");
						confirmPasswordField.grabFocus();
						passwordField.grabFocus();
						usernameField.grabFocus();
						titleField.grabFocus();
						focusGrab.grabFocus();
						Coffer.swapTo(Coffer.AllPasswordsPage);
						Coffer.setStatus("Entry made into your coffer.");
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		add(submit);

		JLabel lblTitle = new JLabel("Password Registry");
		lblTitle.setFont(CofferReferences.Comfortaa_Bold_Italic_20);
		lblTitle.setForeground(CofferReferences.CofferBlue);
		lblTitle.setBounds(50, 25, 300, 50);
		add(lblTitle);
		
	}
}
