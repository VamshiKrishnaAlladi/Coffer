package com.avk.coffer;

import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CofferPasswordEntryLabel extends JLabel {

	public CofferPasswordEntryLabel(CofferPasswordEntry p){
		
		super(p.getTitle()+" ["+p.getUsername()+"]", CofferReferences.COFFER_KEY, SwingConstants.LEFT);
		
		setForeground(CofferReferences.CofferLightGrey);
		setFont(CofferReferences.Comfortaa_Plain_15);
		
		addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					CofferReferences.SYS_CLIPBOARD.setContents(new StringSelection(p.getPassword()), null);
					Coffer.setStatus("Password copied to clipboard");
				}
				if(e.isAltDown())
				{
					CofferReferences.SYS_CLIPBOARD.setContents(new StringSelection(p.getUsername()), null);
					Coffer.setStatus("Username copied to clipboard");
				}
				
			}
			
		});
	}
	
}
