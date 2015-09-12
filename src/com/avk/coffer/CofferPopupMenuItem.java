package com.avk.coffer;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.JMenuItem;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class CofferPopupMenuItem extends JMenuItem {

	public CofferPopupMenuItem(String string) {
		super(string);
		setBorder(null);
		setIconTextGap(10);
		setBackground(Color.WHITE);
		setForeground(CofferReferences.CofferLightGrey);
		setFont(CofferReferences.Comfortaa_Plain_13);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(CofferReferences.CofferBlue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.WHITE);
			}
		});
	}

}
