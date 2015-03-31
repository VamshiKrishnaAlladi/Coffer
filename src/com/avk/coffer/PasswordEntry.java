package com.avk.coffer;

import java.awt.Color;
import java.awt.Font;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

import javax.swing.Icon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PasswordEntry extends JLabel {

	String labelText;
	public PasswordEntry(String text) {
		super(text);
		labelText = text;
		setSettings();
	}
	
	public PasswordEntry(String text, Icon image, int horizontalAlignment){
		super(text, image, horizontalAlignment);
		labelText = new StringTokenizer(text,"[").nextToken().trim();
		setSettings();
	}
	
	void setSettings()
	{
		setForeground(new Color(100, 100, 100));
		setFont(new Font("Comfortaa", Font.PLAIN, 15));
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					Password p = AllPasswords.getPasswordObj(labelText);
					CofferRef.SYS_CLIPBOARD.setContents(new StringSelection(p.getPassword()), null);
					Coffer.setStatus("Password copied to clipboard");
				}
				if(e.isAltDown())
				{
					Password p = AllPasswords.getPasswordObj(labelText);
					CofferRef.SYS_CLIPBOARD.setContents(new StringSelection(p.getUsername()), null);
					Coffer.setStatus("Username copied to clipboard");
				}
				
			}
		});
	}
	
}
