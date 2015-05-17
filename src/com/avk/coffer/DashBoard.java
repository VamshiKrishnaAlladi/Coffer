package com.avk.coffer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DashBoard extends JPanel {

	static JPanel dashBoardPanel;
	static CardLayout crdLayout;
	
	static JLabel lbltabs;
		
	/**
	 * Create the panel.
	 */
	public DashBoard() {
		setOpaque(false);
		setLayout(null);

		JLabel lbltabs = new JLabel(CofferRef.DASHBOARD_TAB1);
		lbltabs.setBounds(0, 0, 750, 40);

		JLabel lblMyPasswords = new JLabel("All My Passwords");
		lblMyPasswords.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyPasswords.setIcon(CofferRef.DASHBOARD_TAB_KEYS);
		lblMyPasswords.setBounds(0, 0, 250, 35);
		lblMyPasswords.setFont(CofferRef.Comfortaa_Bold_15);
		lblMyPasswords.setForeground(CofferRef.CofferBlue);
		lblMyPasswords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { lblMyPasswords.setForeground(Color.white); }
			public void mouseExited(MouseEvent e) { lblMyPasswords.setForeground(CofferRef.CofferBlue); }
			@Override
			public void mouseClicked(MouseEvent e) {
				lbltabs.setIcon(CofferRef.DASHBOARD_TAB1);
				Coffer.setStatus("Double-Click on any entry to copy its password to ClipBoard");
				DashBoard.swapTo("MyPasswords");
			}
		});
		add(lblMyPasswords);

		JLabel lblAddEntry = new JLabel("Add a Password");
		lblAddEntry.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddEntry.setFont(CofferRef.Comfortaa_Bold_15);
		lblAddEntry.setIcon(CofferRef.DASHBOARD_TAB_ADD_KEY);
		lblAddEntry.setForeground(CofferRef.CofferBlue);
		lblAddEntry.setBounds(250, 0, 250, 35);
		lblAddEntry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { lblAddEntry.setForeground(Color.white); }
			public void mouseExited(MouseEvent e) { lblAddEntry.setForeground(CofferRef.CofferBlue); }
			@Override
			public void mouseClicked(MouseEvent e) {
				lbltabs.setIcon(CofferRef.DASHBOARD_TAB2);
				DashBoard.swapTo("AddEntry");
				Coffer.setStatus("Make a password entry.");
			}
		});
		add(lblAddEntry);
		
		JLabel lblPasswordGen = new JLabel("Generate a Password");
		lblPasswordGen.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordGen.setIcon(CofferRef.DASHBOARD_TAB_KEY_GEN);
		lblPasswordGen.setBounds(500, 0, 250, 35);
		lblPasswordGen.setFont(CofferRef.Comfortaa_Bold_15);
		lblPasswordGen.setForeground(CofferRef.CofferBlue);
		lblPasswordGen.addMouseListener(new MouseAdapter() { 
			@Override
			public void mouseEntered(MouseEvent e) { lblPasswordGen.setForeground(Color.white); }
			public void mouseExited(MouseEvent e) { lblPasswordGen.setForeground(CofferRef.CofferBlue); }
			@Override
			public void mouseClicked(MouseEvent e) {
				lbltabs.setIcon(CofferRef.DASHBOARD_TAB3);
				DashBoard.swapTo("PasswordGen");
				Coffer.setStatus("Picking passwords made easy. :)");
			}
		});
		add(lblPasswordGen);

		add(lbltabs);

		crdLayout = new CardLayout(0,0);
		dashBoardPanel = new JPanel(crdLayout);
		dashBoardPanel.setOpaque(false);
		dashBoardPanel.setBounds(0, 40, 750, 420);
		dashBoardPanel.add(new AllPasswords(),"MyPasswords");
		dashBoardPanel.add(new AddEntryPage(),"AddEntry");
		dashBoardPanel.add(new PasswordGen(),"PasswordGen");
		crdLayout.show(dashBoardPanel, "MyPasswords");
		add(dashBoardPanel);
		
		JLabel lblBackground = new JLabel(CofferRef.COFFER_BACKGROUND_LAYER_2);
		lblBackground.setBounds(0, -60, 750, 550);
		add(lblBackground);
		
	}
	
	public static void swapTo(String page){
		if(page.equals("MyPasswords"))
			dashBoardPanel.add(new AllPasswords(),"MyPasswords");
		crdLayout.show(dashBoardPanel, page);
	}
}