package com.avk.coffer;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AddEntryPage extends JPanel {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private String defaultStatus;
	private JTextField titleField;

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
		
		JLabel lblTitleExclaim = new JLabel(CofferRef.EXCLAIM_RED);
		lblTitleExclaim.setVisible(false);
		lblTitleExclaim.setBounds(530, 115, 40, 40);
		add(lblTitleExclaim);

		JLabel lblUserExclaim = new JLabel(CofferRef.EXCLAIM_RED);
		lblUserExclaim.setVisible(false);
		lblUserExclaim.setBounds(530, 165, 40, 40);
		add(lblUserExclaim);

		JLabel lblPassExclaim = new JLabel(CofferRef.EXCLAIM_RED);
		lblPassExclaim.setVisible(false);
		lblPassExclaim.setBounds(530, 215, 40, 40);
		add(lblPassExclaim);

		JLabel lblConPassExclaim = new JLabel(CofferRef.EXCLAIM_RED);
		lblConPassExclaim.setVisible(false);
		lblConPassExclaim.setBounds(530, 265, 40, 40);
		add(lblConPassExclaim);

		
		titleField = new JTextField("Title");
		titleField.setOpaque(false);
		titleField.setHorizontalAlignment(SwingConstants.LEFT);
		titleField.setForeground(CofferRef.CofferDarkGrey);
		titleField.setFont(CofferRef.Comfortaa_Plain_14);
		titleField.setBorder(null);
		titleField.setBounds(235, 119, 280, 30);
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
		titleField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(titleField.getText().equalsIgnoreCase("")){
					titleField.setText("Title");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(titleField.getText().equalsIgnoreCase("title")){
					titleField.setText("");
				}
			}
		});
		add(titleField);
		
		JLabel lblTitlefieldImg = new JLabel(CofferRef.TEXTFIELD_IMG);
		lblTitlefieldImg.setBounds(215, 115, 320, 40);
		add(lblTitlefieldImg);

		usernameField = new JTextField("Username");
		usernameField.setFont(CofferRef.Comfortaa_Plain_14);
		usernameField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameField.setForeground(CofferRef.CofferDarkGrey);
		usernameField.setOpaque(false);
		usernameField.setBackground(null);
		usernameField.setBorder(null);
		usernameField.setBounds(235, 170, 280, 30);
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

		JLabel lblUserfieldImg = new JLabel(CofferRef.TEXTFIELD_IMG);
		lblUserfieldImg.setBounds(215, 165, 320, 40);
		add(lblUserfieldImg);

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setOpaque(false);
		passwordField.setText("Password");
		passwordField.setFont(CofferRef.Comfortaa_Plain_14);
		passwordField.setForeground(CofferRef.CofferDarkGrey);
		passwordField.setEchoChar((char)0);
		passwordField.setBackground(null);
		passwordField.setBorder(null);
		passwordField.setBounds(235, 220, 280, 30);
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

		JLabel lblPassfieldImg = new JLabel(CofferRef.TEXTFIELD_IMG);
		lblPassfieldImg.setBounds(215, 215, 320, 40);
		add(lblPassfieldImg);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setHorizontalAlignment(SwingConstants.LEFT);
		confirmPasswordField.setOpaque(false);
		confirmPasswordField.setText("Confirm Password");
		confirmPasswordField.setFont(CofferRef.Comfortaa_Plain_14);
		confirmPasswordField.setForeground(CofferRef.CofferDarkGrey);
		confirmPasswordField.setEchoChar((char)0);
		confirmPasswordField.setBackground(null);
		confirmPasswordField.setBorder(null);
		confirmPasswordField.setBounds(235, 270, 280, 30);
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
		add(confirmPasswordField);
		
		JLabel lblConfirmPassfieldImg = new JLabel(CofferRef.TEXTFIELD_IMG);
		lblConfirmPassfieldImg.setBounds(215, 265, 320, 40);
		add(lblConfirmPassfieldImg);

		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(CofferRef.Comfortaa_Plain_14);
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSubmit.setBounds(275, 315, 200, 40);
		lblSubmit.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
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
						lblTitleExclaim.setVisible(true);
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(false);
						lblConPassExclaim.setVisible(false);
					}
					else if(username.equalsIgnoreCase("username") || username.equalsIgnoreCase("") )
					{
						Coffer.setStatus("Pick a username.");
						lblTitleExclaim.setVisible(false);
						lblUserExclaim.setVisible(true);
						lblPassExclaim.setVisible(false);
						lblConPassExclaim.setVisible(false);
					}
					else if(password.equalsIgnoreCase("password") || password.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Pick a password.");
						lblTitleExclaim.setVisible(false);
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(true);
						lblConPassExclaim.setVisible(false);
					}
					else if(conPass.equalsIgnoreCase("confirm password") || conPass.equalsIgnoreCase(""))
					{
						Coffer.setStatus("Confirm your password.");
						lblTitleExclaim.setVisible(false);
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(false);
						lblConPassExclaim.setVisible(true);
					}
					else if(!password.equals(conPass)){
						Coffer.setStatus("Passwords did not match. :(");
						passwordField.setText("");
						passwordField.grabFocus();
						confirmPasswordField.setText("");
						lblTitleExclaim.setVisible(false);
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(true);
						lblConPassExclaim.setVisible(true);
					}
					else
					{
						lblTitleExclaim.setVisible(false);
						lblUserExclaim.setVisible(false);
						lblPassExclaim.setVisible(false);
						lblConPassExclaim.setVisible(false);
						
						Random r = new Random();
						int index;
						int fileNo;
						String user_coffer = CofferCrypt.decryptFromFile_Index(CofferRef.getCofferKeyIndex(), new File("./Coffer/user's.coffer"));
						do{	index = r.nextInt(CofferCrypt.MAX_KEY_INDEX); }while(user_coffer.contains(Integer.toString(index).subSequence(0, 4)));
						do{	fileNo = r.nextInt(100000000); }while(user_coffer.contains(Integer.toString(fileNo).subSequence(0, 8)));
						if(user_coffer.equals("no_passwords")){
							user_coffer = title + "|" + Integer.toString(index) + "|" + Integer.toString(fileNo);
						}
						else
						{
							user_coffer += "\n"+ title + "|" + Integer.toString(index) + "|" + Integer.toString(fileNo);
						}
						CofferCrypt.encrypt2File_Index(CofferRef.getCofferKeyIndex(), user_coffer, new File("./Coffer/user's.coffer"));
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
						Coffer.setStatus("Entry made into your coffer.");
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		add(lblSubmit);

		JLabel lblTitle = new JLabel("Password Registry");
		lblTitle.setFont(CofferRef.Comfortaa_Bold_Italic_20);
		lblTitle.setForeground(CofferRef.CofferBlue);
		lblTitle.setBounds(50, 25, 300, 50);
		add(lblTitle);
		
		JLabel submitButtonImg = new JLabel(CofferRef.BUTTON_IMG);
		submitButtonImg.setBounds(275, 315, 200, 40);
		add(submitButtonImg);

	}
}
