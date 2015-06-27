package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CofferTabLabel extends JLabel {
	
	public boolean isSelected = false;

	public CofferTabLabel(String text, Icon icon){
		super(text);
		setHorizontalAlignment(SwingConstants.CENTER);
		setPreferredSize(new Dimension(750, 35));
		setOpaque(true);
		setIcon(icon);
		setIconTextGap(10);
		setFont(CofferReferences.Comfortaa_Plain_13);
		setBackground(CofferReferences.CofferDarkGrey);
		setForeground(Color.WHITE);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!isSelected){
					setBackground(CofferReferences.CofferLightGrey);					
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isSelected){
					setBackground((Color) null);					
				}
			}
		});
		
	}
}
