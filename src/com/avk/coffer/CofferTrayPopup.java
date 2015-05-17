package com.avk.coffer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class CofferTrayPopup extends JPopupMenu {

	public CofferTrayPopup() {
		setBorder(new LineBorder(CofferRef.CofferBlue));
		setBackground(Color.WHITE);
		
		JMenuItem lockItem = new JMenuItem("Lock Coffer");
		lockItem.setIconTextGap(10);
		lockItem.setBackground(Color.WHITE);
		lockItem.setForeground(CofferRef.CofferLightGrey);
		lockItem.setFont(CofferRef.Comfortaa_Plain_13);
		lockItem.setIcon(CofferRef.COFFER_LOCK);
		lockItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	Coffer.lockCoffer();
            }
        });
		add(lockItem);
		
		JMenuItem restoreItem = new JMenuItem("Restore");
		restoreItem.setIconTextGap(10);
		restoreItem.setBackground(Color.WHITE);
		restoreItem.setForeground(CofferRef.CofferLightGrey);
		restoreItem.setIcon(CofferRef.COFFER_FRAME_RESTORE);
		restoreItem.setFont(CofferRef.Comfortaa_Plain_13);
		restoreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { Coffer.makeAppear(); }
        });
		add(restoreItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setBackground(Color.WHITE);
		exitItem.setForeground(CofferRef.CofferLightGrey);
		exitItem.setFont(CofferRef.Comfortaa_Plain_13);
		exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { Coffer.clearAndExit(); }
        });
		add(exitItem);
	}
}
