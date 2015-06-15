package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CofferMenuItem extends JLabel {

	public CofferMenuItem(String text) {
		super(text);
		
		setIconTextGap(10);
		setBorder(new EmptyBorder(2, 10, 2, 5));
		setFont(CofferReferences.Comfortaa_Plain_13);
		setForeground(Color.WHITE);
		setPreferredSize(new Dimension(200, 40));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setOpaque(true);
				setBackground(CofferReferences.CofferDarkGrey);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setOpaque(false);
				setBackground((Color) null);
			}
		});
	}

}
