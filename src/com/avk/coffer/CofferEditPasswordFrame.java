package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CofferEditPasswordFrame extends JDialog {
	
	private final JPanel popupFrame;
	private int xPressed , yPressed;

	private JPanel contentPanel;
	private JLabel titleLbl;

	private static CofferTextField titleField, usernameField,  urlField;
	private static CofferPasswordField passwordField, confirmPasswordField;
	private String defaultStatus;
	private static JButton focusGrab;

	private int frameWidth = 600, frameHeight = 500;

	public CofferEditPasswordFrame( CofferPasswordEntry p) {
		super(Coffer.frmcoffer, true);

		setMinimumSize(new Dimension(300, 300));
		
		Coffer.setDisable(true);
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) { Coffer.setDisable(false);}
		});

		setUndecorated(true);
		setSize(frameWidth, frameHeight);

		popupFrame = new JPanel();
		popupFrame.setBackground(CofferReferences.CofferLightGrey);
		popupFrame.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setForeground(Color.WHITE);
		lblX.setFont(CofferReferences.Antipasto_Bold_15);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setBounds(frameWidth - 35, 5, 30, 30);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Coffer.setDisable(false);
				CofferEditPasswordFrame.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(CofferReferences.CofferBlue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.WHITE);
			}
		});
		popupFrame.add(lblX);

		titleLbl = new JLabel("Editing " + p.getTitle() + " Entry");
		titleLbl.setOpaque(true);
		titleLbl.setBorder(new EmptyBorder(0, 25, 0, 0));
		titleLbl.setBounds(0, 0, frameWidth, 40);
		titleLbl.setBackground(CofferReferences.CofferDarkGrey);
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(CofferReferences.Comfortaa_Bold_Italic_16);
		titleLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xPressed = e.getX();
				yPressed = e.getY();
			}
		});
		titleLbl.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				CofferEditPasswordFrame.this.setLocation( ( x - xPressed), ( y - yPressed) );				
			}
		});
		popupFrame.add(titleLbl);

		
		CofferEditPasswordFrame.this.setContentPane(popupFrame);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setOpaque(false);
		contentPanel.setBounds(0, 40, frameWidth, frameHeight-40);

		focusGrab = new JButton("");
		focusGrab.setBounds(0, 0, 0, 0);
		focusGrab.grabFocus();
		getContentPane().add(focusGrab);
		
		titleField = new CofferTextField("Title",p.getTitle());
		titleField.setBounds(((frameWidth - 320) / 2), 100, 320, 40);
		titleField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(titleField.getText().equals("")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Enter the Title of entry here...");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		contentPanel.add(titleField);
		
		urlField = new CofferTextField("URL",p.getUrl());
		urlField.setBounds(((frameWidth - 320) / 2), 150, 320, 40);
		urlField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(urlField.getText().equals("")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Enter the login URL here...");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		contentPanel.add(urlField);

		usernameField = new CofferTextField("Username",p.getUsername());
		usernameField.setBounds(((frameWidth - 320) / 2), 200, 320, 40);
		usernameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(usernameField.getText().equals("")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Enter your Username here...");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		contentPanel.add(usernameField);

		passwordField = new CofferPasswordField("Password", p.getPassword());
		passwordField.setBounds(((frameWidth - 320) / 2), 250, 320, 40);
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(passwordField.getText().equals("")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Enter your Password here...");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		contentPanel.add(passwordField);

		confirmPasswordField = new CofferPasswordField("Confirm Password",p.getPassword());
		confirmPasswordField.setBounds(((frameWidth - 320) / 2), 300, 320, 40);
		confirmPasswordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(confirmPasswordField.getText().equals("")){
					defaultStatus = Coffer.getStatus();
					Coffer.setStatus("Retype your password here...");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Coffer.setStatus(defaultStatus);
			}
		});
		contentPanel.add(confirmPasswordField);
		
		CofferButton submit = new CofferButton("Submit");
		submit.setBounds(((frameWidth - 200) / 2), 350, 200, 40);
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
					String url = urlField.getText().trim();
					
					url = url.equals("")? "no_url" : url;
					
					if(title.equals(""))
					{
						Coffer.setStatus("I guess you don't know that Title is mandatory.");
						titleField.setValid(false);
						usernameField.setValid(true);
						passwordField.setValid(true);
						confirmPasswordField.setValid(true);
					}
					else if( username.equals("") )
					{
						Coffer.setStatus("Comm'on you forgot entering your username?    O.o");
						titleField.setValid(true);
						usernameField.setValid(false);
						passwordField.setValid(true);
						confirmPasswordField.setValid(true);
					}
					else if( password.equals(""))
					{
						Coffer.setStatus("Pick a strong password, empty ones don't serve the purpose.");
						titleField.setValid(true);
						usernameField.setValid(true);
						passwordField.setValid(false);
						confirmPasswordField.setValid(true);
					}
					else if( conPass.equals(""))
					{
						Coffer.setStatus("All that's needed is to retype your password!");
						titleField.setValid(true);
						usernameField.setValid(true);
						passwordField.setValid(true);
						confirmPasswordField.setValid(false);
					}
					else if(!password.equals(conPass)){
						Coffer.setStatus("Try again! Your passwords didn't match.    :(");
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
						
						p.setTitle(title);
						p.setUrl(url);
						p.setUsername(username);
						p.setPassword(password);
						
						p.writeToFile();
						
						Coffer.setDisable(false);
						CofferEditPasswordFrame.this.dispose();				
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		contentPanel.add(submit);

		popupFrame.add(contentPanel);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setUndecorated(true);
		setVisible(true);
	}

}
