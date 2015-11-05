package com.avk.coffer;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AboutPage extends JPanel {

	private static final int pageWidth = CofferSettings.COFFER_PAGE_SIZE.width;
	private static final int pageHeight = CofferSettings.COFFER_PAGE_SIZE.height;

	public AboutPage() {
		setPreferredSize(new Dimension(pageWidth, pageHeight));
		setOpaque(false);
		setLayout(null);

		JLabel lblTitle = new JLabel("About Coffer");
		lblTitle.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblTitle.setForeground(CofferReferences.CofferBlue);
		lblTitle.setFont(CofferReferences.Comfortaa_Bold_Italic_20);
		lblTitle.setBounds(50, 25, 300, 50);
		add(lblTitle);

		JLabel line1 = new JLabel("Coffer : A Portable password manager, ");
		line1.setFont(CofferReferences.Comfortaa_Plain_13);
		line1.setForeground(CofferReferences.CofferVeryLightGrey);
		line1.setBounds(150, 120, 550, 20);
		add(line1);

		JLabel line2 = new JLabel("New label");
		line2.setForeground(CofferReferences.CofferVeryLightGrey);
		line2.setFont(CofferReferences.Comfortaa_Plain_13);
		line2.setBounds(100, 145, 600, 20);
		add(line2);

		JLabel line3 = new JLabel("New label");
		line3.setForeground(CofferReferences.CofferVeryLightGrey);
		line3.setFont(CofferReferences.Comfortaa_Plain_13);
		line3.setBounds(100, 170, 600, 20);
		add(line3);

		JLabel line4 = new JLabel("New label");
		line4.setForeground(CofferReferences.CofferVeryLightGrey);
		line4.setFont(CofferReferences.Comfortaa_Plain_13);
		line4.setBounds(100, 195, 600, 20);
		add(line4);

		JLabel line5 = new JLabel("New label");
		line5.setForeground(CofferReferences.CofferVeryLightGrey);
		line5.setFont(CofferReferences.Comfortaa_Plain_13);
		line5.setBounds(100, 215, 600, 20);
		add(line5);

		JLabel line6 = new JLabel("New label");
		line6.setForeground(CofferReferences.CofferVeryLightGrey);
		line6.setFont(CofferReferences.Comfortaa_Plain_13);
		line6.setBounds(100, 240, 600, 20);
		add(line6);

		JLabel line7 = new JLabel("New label");
		line7.setForeground(CofferReferences.CofferVeryLightGrey);
		line7.setFont(CofferReferences.Comfortaa_Plain_13);
		line7.setBounds(100, 265, 600, 20);
		add(line7);
	}
}