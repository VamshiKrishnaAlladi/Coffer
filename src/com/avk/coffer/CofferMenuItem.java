package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CofferMenuItem extends JPanel {

	private static JLabel label;
	private static JLabel icon;
	
	
	public CofferMenuItem(String text, Icon icn) {
		
		label = new JLabel(text);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(CofferReferences.Comfortaa_Plain_13);
		label.setForeground(Color.WHITE);
		label.setBounds(0, 0, 160, 40);

		icon = new JLabel(icn);
		
		icon.setBounds(160,0,40,40);
		setLayout(null);
		
		add(label);
		add(icon);
		
		setBackground((Color) null);
		setBorder(null);
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
