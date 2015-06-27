package com.avk.coffer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class CofferPasswordPopupMenu extends CofferPopupMenu {

	public CofferPasswordPopupMenu(CofferPasswordEntry p) {
		
		CofferPopupMenuItem loginMenuItem = new CofferPopupMenuItem("Go to Login Page");
		add(loginMenuItem);
		
		CofferPopupMenuItem editMenuItem = new CofferPopupMenuItem("Edit Entry");
		add(editMenuItem);
		
		CofferPopupMenuItem deleteMenuItem = new CofferPopupMenuItem("Delete Entry");
		deleteMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String[] msgs = {"You are about to delete the password entry.","Do you want to continue?"};
					CofferDialog deleteDialog = new CofferDialog(true, "Delete Confirmation", msgs , CofferDialog.YES_NO_OPTIONS);
					if(deleteDialog.selectedOption==CofferDialog.YES_OPTION)
					{
						String new_user_coffer="", user_coffer = CofferCrypt.decryptFromFile_Index(CofferCrypt.getCofferKeyIndex(), new File("./Coffer/user's.coffer"));
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
						CofferCrypt.encrypt2File_Index(CofferCrypt.getCofferKeyIndex(), new_user_coffer, new File("./Coffer/user's.coffer"));
						DashBoard.setSelection(DashBoard.all_passwords_page);
					}
				} catch (Exception e1) { e1.printStackTrace(); }				
			}
		});
		add(deleteMenuItem);
		
	}
}
