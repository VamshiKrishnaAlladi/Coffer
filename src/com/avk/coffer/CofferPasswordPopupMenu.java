package com.avk.coffer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
public class CofferPasswordPopupMenu extends JPopupMenu {

	public CofferPasswordPopupMenu(CofferPasswordEntry p) {
		setBackground(Color.WHITE);
		
		JMenuItem loginMenuItem = new JMenuItem("Go to Login Page");
		loginMenuItem.setFont(CofferReferences.Comfortaa_Plain_13);
		loginMenuItem.setForeground(CofferReferences.CofferLightGrey);
		loginMenuItem.setBackground(Color.WHITE);
		add(loginMenuItem);
		
		JMenuItem editMenuItem = new JMenuItem("Edit Entry");
		editMenuItem.setBackground(Color.WHITE);
		editMenuItem.setFont(CofferReferences.Comfortaa_Plain_13);
		editMenuItem.setForeground(CofferReferences.CofferLightGrey);
		add(editMenuItem);
		
		JMenuItem deleteMenuItem = new JMenuItem("Delete Entry");
		deleteMenuItem.setBackground(Color.WHITE);
		deleteMenuItem.setFont(CofferReferences.Comfortaa_Plain_13);
		deleteMenuItem.setForeground(CofferReferences.CofferLightGrey);
		deleteMenuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(JOptionPane.showConfirmDialog(deleteMenuItem, "You are about to delete the password entry.\nDo you want to continue?",
							"Delete password Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					{
						String new_user_coffer="", user_coffer = CofferCrypt.decryptFromFile_Index(CofferReferences.getCofferKeyIndex(), new File("./Coffer/user's.coffer"));
						StringTokenizer st = new StringTokenizer(user_coffer,"\n");
						while(st.hasMoreTokens()){
							String token =st.nextToken();
							if(token.contains(p.getTitle())){
								StringTokenizer strTknzr = new StringTokenizer(token,"|");
								strTknzr.nextToken();
								String fileNo = strTknzr.nextToken();
								new File("./Coffer/" + fileNo + ".cofferpass").delete();
							}
							else{ new_user_coffer += st.hasMoreTokens()? token + "\n" : token; }
						}
						CofferCrypt.encrypt2File_Index(CofferReferences.getCofferKeyIndex(), new_user_coffer, new File("./Coffer/user's.coffer"));
						DashBoard.swapTo("AllPasswordsPage");
					}
				} catch (Exception e1) { e1.printStackTrace(); }				
			}
		});
		add(deleteMenuItem);
		
	}
}
