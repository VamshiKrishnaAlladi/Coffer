package com.avk.coffer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class DashBoard extends JPanel {

	protected static final String all_passwords_page = "AllPasswordsPage";
	protected static final String add_entry_page = "AddEntryPage";
	protected static final String password_generator_page = "PasswordGeneratorPage";

	private static JPanel tabsDisplay, displayPanel;
	private static CofferTabLabel allPasswords, addPassword, generatePassword;
	private static CardLayout cl;
	
	public DashBoard() {
		super();
		setOpaque(false);
		setPreferredSize(new Dimension(750, 460));
		setLayout(null);
		
		tabsDisplay = new JPanel();
		tabsDisplay.setBackground(CofferReferences.CofferDarkGrey);
		tabsDisplay.setBounds(0, 0, 750, 35);
		tabsDisplay.setLayout(null);
		add(tabsDisplay);
		
		allPasswords = new CofferTabLabel("All My Passwords", CofferReferences.MULTIPLE_KEYS);
		allPasswords.setBounds(0,0,250,35);
		allPasswords.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				setSelection(all_passwords_page);
			}
		});
		tabsDisplay.add(allPasswords);
		
		
		addPassword = new CofferTabLabel("Add a Password", CofferReferences.ADD_KEY);
		addPassword.setBounds(250,0,250,35);
		addPassword.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				setSelection(add_entry_page);
			}
		});
		tabsDisplay.add(addPassword);
		
		generatePassword = new CofferTabLabel("Password Generator", CofferReferences.KEY_GEN);
		generatePassword.setBounds(500,0,250,35);
		generatePassword.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				setSelection(password_generator_page);
			}
		});
		tabsDisplay.add(generatePassword);
		
		cl = new CardLayout(0, 0);
		
		displayPanel = new JPanel();
		displayPanel.setOpaque(false);
		displayPanel.setBounds(0, 35, 750, 425);
		displayPanel.setLayout(cl);
		add(displayPanel);
		
		displayPanel.add(new AllPasswordsPage(),all_passwords_page);
		displayPanel.add(new AddEntryPage(),add_entry_page);
		displayPanel.add(new PasswordGeneratorPage(),password_generator_page);
		
		setSelection(all_passwords_page);

	}
	
	private static void swapTo(String page){
		if(page == all_passwords_page)
			displayPanel.add(new AllPasswordsPage(),page);
		
		cl.show(displayPanel, page);
		displayPanel.revalidate();
	}
	
	public static void setSelection(String page){
		switch(page){
			case all_passwords_page :{
				Coffer.setStatus("All your Passwords are here Boss.");
				allPasswords.isSelected = true;
				allPasswords.setBorder(new MatteBorder(0, 0, 3, 0, CofferReferences.CofferBlue));
				allPasswords.setBackground(CofferReferences.CofferLightGrey);
				if(addPassword.isSelected){
					addPassword.isSelected = false;
					addPassword.setBorder(new MatteBorder(0, 0, 3, 0, (Color)null));
					addPassword.setBackground((Color) null);					
				}
				else if(generatePassword.isSelected){
					generatePassword.isSelected = false;
					generatePassword.setBorder(new MatteBorder(0, 0, 3, 0, (Color)null));
					generatePassword.setBackground((Color) null);
				}
				break;
			}
			case add_entry_page:{
				Coffer.setStatus("Make a password entry into your coffer here.");
				addPassword.isSelected = true;
				addPassword.setBorder(new MatteBorder(0, 0, 3, 0, CofferReferences.CofferBlue));
				addPassword.setBackground(CofferReferences.CofferLightGrey);
				if(allPasswords.isSelected){
					allPasswords.isSelected = false;
					allPasswords.setBorder(new MatteBorder(0, 0, 3, 0, (Color)null));
					allPasswords.setBackground((Color) null);					
				}
				else if(generatePassword.isSelected){
					generatePassword.isSelected = false;
					generatePassword.setBorder(new MatteBorder(0, 0, 3, 0, (Color)null));
					generatePassword.setBackground((Color) null);					
				}
				break;
			}
			case password_generator_page:{
				Coffer.setStatus("Password picking is now lot more easier.");
				generatePassword.isSelected = true;
				generatePassword.setBorder(new MatteBorder(0, 0, 3, 0, CofferReferences.CofferBlue));
				generatePassword.setBackground(CofferReferences.CofferLightGrey);
				if(allPasswords.isSelected){
					allPasswords.isSelected = false;
					allPasswords.setBorder(new MatteBorder(0, 0, 3, 0, (Color)null));
					allPasswords.setBackground((Color) null);					
				}
				else if(addPassword.isSelected){
					addPassword.isSelected = false;
					addPassword.setBorder(new MatteBorder(0, 0, 3, 0, (Color)null));
					addPassword.setBackground((Color) null);					
				}
				break;
			}
		}
		swapTo(page);
	}
}