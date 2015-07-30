package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class AllPasswordsPage extends JPanel {

//	private static HashMap<String, CofferPasswordEntry> allPasswords;
	
	private static final int pageWidth = CofferReferences.COFFER_FRAME_SIZE.width - 200;
	private static final int pageHeight = CofferReferences.COFFER_FRAME_SIZE.height - 100;

	/**
	 * Create the panel.
	 */
	public AllPasswordsPage() {
		try{
			setPreferredSize(new Dimension(pageWidth, pageHeight));
			setOpaque(false);
			setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds( 75, 100, pageWidth - 150, 300);
			scrollPane.setOpaque(false);
			scrollPane.getViewport().setOpaque(false);
			scrollPane.setViewportBorder(null);
			
			JScrollBar vsb = scrollPane.getVerticalScrollBar();
			vsb.setUI(new CofferScrollbarUI());
			vsb.setPreferredSize(new Dimension(10,300));
			vsb.setOpaque(false);
			vsb.setUnitIncrement(10);
			
			JLabel lblTitle = new JLabel("Password Almanac");
			lblTitle.setFont(CofferReferences.Comfortaa_Bold_Italic_20);
			lblTitle.setForeground(CofferReferences.CofferBlue);
			lblTitle.setBounds(50, 25, pageWidth - 50, 50);
			add(lblTitle);
			add(scrollPane);

			JPanel displayPanel = new JPanel();
			displayPanel.setBorder(null);
			displayPanel.setOpaque(false);
			scrollPane.setViewportView(displayPanel);
			displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

			String user_coffer = CofferCrypt.decryptFromFile_Index(CofferCrypt.getCofferKeyIndex(), new File("./Coffer/user's.coffer"));
			Scanner cofferScanner = new Scanner(user_coffer);
			cofferScanner.useDelimiter("\n");
			while(cofferScanner.hasNext())
			{	
				String entry =  cofferScanner.next();
				if(!entry.equals("no_passwords"))
				{
					StringTokenizer st = new StringTokenizer(entry,"|");
					
					//do not alter the order of following 2 statements.
					int f = Integer.parseInt(st.nextToken());
					int i = Integer.parseInt(st.nextToken());
					
					CofferPasswordEntry p = new CofferPasswordEntry();
					p.setValuesFromFile(new File("./Coffer/"+ f +".cofferpass"), i);

					CofferPasswordEntryElement element = new CofferPasswordEntryElement(p);
					displayPanel.add(element);
				}
				else{
					Coffer.setStatus("You can add entries using \"Add a Password\" tab.");
					JLabel noPass = new JLabel("You have'nt stored any passwords.");
					noPass.setForeground(Color.WHITE);
					noPass.setFont(CofferReferences.Comfortaa_Plain_15);
					displayPanel.add(noPass);						
				}
			}
			cofferScanner.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}