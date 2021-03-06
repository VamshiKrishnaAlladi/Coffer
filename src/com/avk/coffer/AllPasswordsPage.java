package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AllPasswordsPage extends JPanel {

	private static final int pageWidth = CofferSettings.COFFER_PAGE_SIZE.width;
	private static final int pageHeight = CofferSettings.COFFER_PAGE_SIZE.height;

	public AllPasswordsPage() {
		try {
			setPreferredSize(new Dimension(pageWidth, pageHeight));
			setOpaque(false);
			setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(75, 100, pageWidth - 150, 300);
			scrollPane.setOpaque(false);
			scrollPane.getViewport().setOpaque(false);
			scrollPane.setViewportBorder(null);

			JScrollBar vsb = scrollPane.getVerticalScrollBar();
			vsb.setUI(new CofferScrollbarUI());
			vsb.setPreferredSize(new Dimension(10, 300));
			vsb.setOpaque(false);
			vsb.setUnitIncrement(10);

			JLabel lblTitle = new JLabel("Password Almanac");
			lblTitle.setBorder(new EmptyBorder(0, 10, 0, 0));
			lblTitle.setFont(CofferReferences.Comfortaa_Bold_Italic_20);
			lblTitle.setForeground(CofferReferences.CofferBlue);
			lblTitle.setBounds(50, 25, 300, 50);
			add(lblTitle);
			add(scrollPane);

			JPanel displayPanel = new JPanel();
			displayPanel.setBorder(null);
			displayPanel.setOpaque(false);
			scrollPane.setViewportView(displayPanel);
			displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

			String user_coffer = CofferCrypt.decryptFromFile_Index(CofferCrypt.getCofferKeyIndex(), new File("./Coffer/user's.coffer"));
			StringTokenizer cofferTokens = new StringTokenizer(user_coffer, "\n");
			while (cofferTokens.hasMoreTokens()) {
				String entry = cofferTokens.nextToken();
				if (!entry.equals("no_passwords")) {
					StringTokenizer st = new StringTokenizer(entry, "|");

					// do not alter the order of following 2 statements.
					int f = Integer.parseInt(st.nextToken());
					int i = Integer.parseInt(st.nextToken());

					CofferPasswordEntry p = new CofferPasswordEntry();
					p.setValuesFromFile(new File("./Coffer/" + f + ".cofferpass"), i);

					CofferPasswordEntryElement element = new CofferPasswordEntryElement(p);
					displayPanel.add(element);
				} else {
					Coffer.setStatus("You can add entries using \"Add a Password\" tab.");
					JLabel noPass = new JLabel("You have'nt stored any passwords.");
					noPass.setForeground(Color.WHITE);
					noPass.setFont(CofferReferences.Comfortaa_Plain_15);
					displayPanel.add(noPass);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}