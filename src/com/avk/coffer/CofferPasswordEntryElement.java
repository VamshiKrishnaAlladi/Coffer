package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class CofferPasswordEntryElement extends JPanel {
	
	private JLabel entry, edit, delete;
	private JPanel optionsPanel;
	
	public CofferPasswordEntryElement(CofferPasswordEntry p){
		
		super();
		setBackground(CofferReferences.CofferDarkGrey);
		setOpaque(false);
		setPreferredSize(new Dimension(600, 40));
		setMaximumSize(new Dimension(600, 40));
		setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		setSize(new Dimension(600,40));
		
		entry = new JLabel(p.getTitle()+" ["+p.getUsername()+"]", CofferReferences.SINGLE_KEY, SwingConstants.LEFT);
		entry.setForeground(Color.WHITE);
		entry.setFont(CofferReferences.Comfortaa_Plain_15);
		entry.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseEntered(MouseEvent arg0) { optionsPanel.setVisible(true); }

			@Override
			public void mouseExited(MouseEvent arg0) { optionsPanel.setVisible(false); }

			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
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
		add(entry);
		
		optionsPanel = new JPanel();
		optionsPanel.setVisible(false);
		optionsPanel.setOpaque(false);
		
				
		edit = new JLabel(CofferReferences.EDIT);
		edit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent arg0) { optionsPanel.setVisible(true); }

			@Override
			public void mouseExited(MouseEvent arg0) { optionsPanel.setVisible(false); }

			@Override
			public void mouseClicked(MouseEvent e) {
				new CofferEditPasswordFrame(p);
			}
		});
//		optionsPanel.add(edit);
		
				
		delete = new JLabel(CofferReferences.DELETE);
		delete.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent arg0) { optionsPanel.setVisible(true); }

			@Override
			public void mouseExited(MouseEvent arg0) { optionsPanel.setVisible(false); }

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					String[] msgs = {"You are about to delete a password entry.","Do you want to continue?"};
					CofferDialog deleteDialog = new CofferDialog(true, "Delete Confirmation", msgs , CofferDialog.YES_NO_OPTIONS);
					
					if(deleteDialog.selectedOption==CofferDialog.YES_OPTION)
					{
						String new_user_coffer="", user_coffer = CofferCrypt.decryptFromFile_Index(CofferCrypt.getCofferKeyIndex(), new File("./Coffer/user's.coffer"));
						StringTokenizer st = new StringTokenizer(user_coffer,"\n");
						
						while(st.hasMoreTokens()){
							String token =st.nextToken();
							if(token.contains(p.getId())){
								StringTokenizer strTknzr = new StringTokenizer(token,"|");
								String fileNo = strTknzr.nextToken();
								new File("./Coffer/" + fileNo + ".cofferpass").delete();
							}
							else{
								new_user_coffer = new_user_coffer.equals("")? token : new_user_coffer + "\n" + token; 
							}
						}
						
						if(new_user_coffer.equals(""))
							new_user_coffer = "no_passwords";
						
						CofferCrypt.encrypt2File_Index(CofferCrypt.getCofferKeyIndex(), new_user_coffer, new File("./Coffer/user's.coffer"));
						DashBoard.setSelection(DashBoard.all_passwords_page, true);
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
			
		});
		optionsPanel.add(delete);
		
		add(optionsPanel);
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent arg0) { optionsPanel.setVisible(true); }

			@Override
			public void mouseExited(MouseEvent arg0) { optionsPanel.setVisible(false); }
			
		});
	}
	
}
