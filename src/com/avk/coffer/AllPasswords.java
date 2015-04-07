package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class AllPasswords extends JPanel {

	private static HashMap<String, Password> allPasswords;
	/**
	 * Create the panel.
	 */
	public AllPasswords() {
		try{
			setOpaque(false);
			setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(100, 100, 550, 300);
			scrollPane.setOpaque(false);
			scrollPane.getViewport().setOpaque(false);
			scrollPane.setViewportBorder(null);
			
			JScrollBar vsb = scrollPane.getVerticalScrollBar();
			vsb.setUI(new CofferScrollbarUI());
			vsb.setPreferredSize(new Dimension(10,300));
			vsb.setOpaque(false);
			vsb.setUnitIncrement(10);
			
			JLabel lblTitle = new JLabel("Password Almanac");
			lblTitle.setFont(new Font("Comfortaa", Font.ITALIC|Font.BOLD, 20));
			lblTitle.setForeground(new Color(0,175,210));
			lblTitle.setBounds(50, 25, 240, 50);
			add(lblTitle);
			add(scrollPane);

			JPanel displayPanel = new JPanel();
			displayPanel.setBorder(null);
			displayPanel.setOpaque(false);
			scrollPane.setViewportView(displayPanel);
			displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));

			allPasswords = new HashMap<String,Password>();
			String user_coffer = CofferCrypt.decryptFromFile_Index(CofferRef.getCofferKeyIndex(), new File("./Coffer/user's.coffer"));
			Scanner cofferScanner = new Scanner(user_coffer);
			cofferScanner.useDelimiter("\n");
			while(cofferScanner.hasNext())
			{	
				String entry =  cofferScanner.next();
				if(!entry.equals("no_passwords"))
				{
					StringTokenizer st = new StringTokenizer(entry,"|");
					Password p = new Password();
					p.setTitle(st.nextToken());
					int i = Integer.parseInt(st.nextToken());
					int f = Integer.parseInt(st.nextToken());
					st = new StringTokenizer(CofferCrypt.decryptFromFile_Index(i, new File("./Coffer/"+ f +".cofferpass")),"|");
					p.setUsername(st.nextToken());
					p.setPassword(st.nextToken());
					allPasswords.put(p.getTitle(), p);
					displayPanel.add(new PasswordEntry(p.getTitle()+ " ["+p.getUsername()+"]",new ImageIcon(this.getClass().getResource("/key.png")), javax.swing.SwingConstants.LEFT));					
				}
				else{
					Coffer.setStatus("You can add entries in \"Add a Password\" tab.");
					JLabel noPass = new JLabel("You have'nt stored any passwords yet");
					noPass.setForeground(new Color(100, 100, 100));
					noPass.setFont(new Font("Comfortaa", Font.PLAIN, 15));
					displayPanel.add(noPass);						
				}
			}
			cofferScanner.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public static Password getPasswordObj(String key){
		return allPasswords.get(key);
	}
}