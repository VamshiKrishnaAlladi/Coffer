package com.avk.coffer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class CofferTrayPopup extends CofferPopupMenu {

	public CofferTrayPopup() {

		CofferMenuItem lockItem = new CofferMenuItem("Lock Coffer");
		lockItem.setIcon(CofferReferences.COFFER_LOCK);
		lockItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	Coffer.lockCoffer();
            }
        });
		add(lockItem);
		
		CofferMenuItem restoreItem = new CofferMenuItem("Restore");
		restoreItem.setIcon(CofferReferences.COFFER_FRAME_RESTORE);
		restoreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { Coffer.makeAppear(); }
        });
		add(restoreItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { Coffer.clearAndExit(); }
        });
		add(exitItem);
	}
}
