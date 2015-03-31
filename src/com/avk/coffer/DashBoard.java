package com.avk.coffer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DashBoard extends JPanel {

	static JPanel dashBoardPanel;
	static CardLayout crdLayout;
	
	static JLabel lbltabs;
	
	ImageIcon tab1 = new ImageIcon(this.getClass().getResource("/tab1.png"));
	ImageIcon tab2 = new ImageIcon(this.getClass().getResource("/tab2.png"));
	ImageIcon tab3 = new ImageIcon(this.getClass().getResource("/tab3.png"));
	
	/**
	 * Create the panel.
	 */
	public DashBoard() {
		setOpaque(false);
		setLayout(null);

		JLabel lbltabs = new JLabel(tab1);
		lbltabs.setBounds(0, 0, 750, 40);

		JLabel lblMyPasswords = new JLabel("All My Passwords");
		lblMyPasswords.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyPasswords.setIcon(new ImageIcon(this.getClass().getResource("/keys.png"))); ;
		lblMyPasswords.setBounds(0, 0, 250, 35);
		lblMyPasswords.setFont(new Font("Comfortaa", Font.BOLD, 15));
		lblMyPasswords.setForeground(new Color(0,175,210));
		lblMyPasswords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { lblMyPasswords.setForeground(Color.white); }
			public void mouseExited(MouseEvent e) { lblMyPasswords.setForeground(new Color(0,175,210)); }
			@Override
			public void mouseClicked(MouseEvent e) {
				lbltabs.setIcon(tab1);
				DashBoard.swapTo("MyPasswords");
				Coffer.setStatus("Double-Click on any password to copy it to ClipBoard");
			}
		});
		add(lblMyPasswords);

		JLabel lblAddEntry = new JLabel("Add a Password");
		lblAddEntry.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddEntry.setFont(new Font("Comfortaa", Font.BOLD, 15));
		lblAddEntry.setIcon(new ImageIcon(this.getClass().getResource("/addKey.png")));
		lblAddEntry.setForeground(new Color(0,175,210));
		lblAddEntry.setBounds(250, 0, 250, 35);
		lblAddEntry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { lblAddEntry.setForeground(Color.white); }
			public void mouseExited(MouseEvent e) { lblAddEntry.setForeground(new Color(0,175,210)); }
			@Override
			public void mouseClicked(MouseEvent e) {
				lbltabs.setIcon(tab2);
				DashBoard.swapTo("AddEntry");
				Coffer.setStatus("Make an entry....");
			}
		});
		add(lblAddEntry);
		
		JLabel lblPasswordGen = new JLabel("Generate a Password");
		lblPasswordGen.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordGen.setIcon(new ImageIcon(this.getClass().getResource("/keyGen.png")));
		lblPasswordGen.setBounds(500, 0, 250, 35);
		lblPasswordGen.setFont(new Font("Comfortaa", Font.BOLD, 15));
		lblPasswordGen.setForeground(new Color(0,175,210));
		lblPasswordGen.addMouseListener(new MouseAdapter() { 
			@Override
			public void mouseEntered(MouseEvent e) { lblPasswordGen.setForeground(Color.white); }
			public void mouseExited(MouseEvent e) { lblPasswordGen.setForeground(new Color(0,175,210)); }
			@Override
			public void mouseClicked(MouseEvent e) {
				lbltabs.setIcon(tab3);
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
		
		JLabel lblBackground = new JLabel(new ImageIcon(this.getClass().getResource("/backgroundLayer2.png")));
		lblBackground.setBounds(0, -60, 750, 550);
		add(lblBackground);
		
	}
	
	public static void swapTo(String page){
//		dashBoardPanel.removeAll();
		if(page.equals("MyPasswords"))
			dashBoardPanel.add(new AllPasswords(),"MyPasswords");
		crdLayout.show(dashBoardPanel, page);
	}
}