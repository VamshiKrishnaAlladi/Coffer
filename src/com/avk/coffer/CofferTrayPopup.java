package com.avk.coffer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class CofferTrayPopup extends JPopupMenu {
	public CofferTrayPopup() {
		
		setBorder(new LineBorder(new Color(0,175,210)));
		setBackground(Color.WHITE);
		
		JMenuItem lockItem = new JMenuItem("Lock Coffer");
		lockItem.setIconTextGap(15);
		lockItem.setBorder(new LineBorder(new Color(0,175,210)));
		lockItem.setBackground(Color.WHITE);
		lockItem.setForeground(new Color(0,175,210));
		lockItem.setFont(new Font("Comfortaa", Font.PLAIN, 15));
		lockItem.setIcon(new ImageIcon(this.getClass().getResource("/lock.png")));
		lockItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	if(!Coffer.KEY_FILE.exists()){ Coffer.swapTo("CreateUserPage"); }
				else{ Coffer.swapTo("LoginPage"); }
            }
        });
		add(lockItem);
		
		JMenuItem restoreItem = new JMenuItem("Restore");
		restoreItem.setIconTextGap(15);
		restoreItem.setBorder(new LineBorder(new Color(0,175,210)));
		restoreItem.setBackground(Color.WHITE);
		restoreItem.setForeground(new Color(0,175,210));
		restoreItem.setIcon(new ImageIcon(this.getClass().getResource("/restore.png")));
		restoreItem.setFont(new Font("Comfortaa", Font.PLAIN, 15));
		restoreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { Coffer.makeAppear(); }
        });
		add(restoreItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setBorder(new LineBorder(new Color(0,175,210)));
		exitItem.setBackground(Color.WHITE);
		exitItem.setForeground(new Color(0,175,210));
		exitItem.setFont(new Font("Comfortaa", Font.PLAIN, 15));
		exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { Coffer.clearAndExit(); }
        });
		add(exitItem);
		
	}
}
