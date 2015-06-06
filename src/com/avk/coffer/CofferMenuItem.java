package com.avk.coffer;

import java.awt.Color;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class CofferMenuItem extends JMenuItem {

	public CofferMenuItem(String string) {
		super(string);
		setBorder(null);
		setIconTextGap(10);
		setBackground(Color.WHITE);
		setForeground(CofferReferences.CofferLightGrey);
		setFont(CofferReferences.Comfortaa_Plain_13);
	}
	
}
