package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CofferButton extends JLabel {

	public CofferButton(String text) {
		super(text);
		setPreferredSize(new Dimension(200,40));
		setVisible(true);
		setLayout(null);
		setOpaque(false);
		
		setBorder(new CofferRoundBorder(CofferReferences.CofferBlue, 2, 10));
		setBackground(CofferReferences.CofferBlue);
		setOpaque(true);
		setForeground(Color.WHITE);
		setFont(CofferReferences.Comfortaa_Plain_14);
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setBounds(0, 0, 200, 40);
				
	}
	
}