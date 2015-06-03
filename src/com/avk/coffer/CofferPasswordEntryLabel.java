package com.avk.coffer;

import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class CofferPasswordEntryLabel extends JLabel {

	public CofferPasswordEntryLabel(CofferPasswordEntry p){
		
		super(p.getTitle()+" ["+p.getUsername()+"]", CofferReferences.COFFER_KEY, SwingConstants.LEFT);
		
		setForeground(CofferReferences.CofferLightGrey);
		setFont(CofferReferences.Comfortaa_Plain_15);
		
		addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e)){
					CofferPasswordPopupMenu popup = new CofferPasswordPopupMenu(p);
					popup.show(CofferPasswordEntryLabel.this, e.getX(), e.getY());
				}
				else if(e.getClickCount() == 2){
					CofferReferences.SYS_CLIPBOARD.setContents(new StringSelection(p.getPassword()), null);
					Coffer.setStatus("Password copied to clipboard");
				}
				else if(e.isAltDown() && SwingUtilities.isLeftMouseButton(e))
				{
					CofferReferences.SYS_CLIPBOARD.setContents(new StringSelection(p.getUsername()), null);
					Coffer.setStatus("Username copied to clipboard");
				}
				
			}
			
		});
	}
	
}
