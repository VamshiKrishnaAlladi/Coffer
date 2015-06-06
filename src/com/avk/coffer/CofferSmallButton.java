package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CofferSmallButton extends JPanel {

	private JLabel buttonImg;
	private JLabel buttonText;
	
	public CofferSmallButton(String text) {
		setPreferredSize(new Dimension(100,30));
		setVisible(true);
		setLayout(null);
		setOpaque(false);
		
		buttonText = new JLabel(text);
		buttonText.setForeground(Color.WHITE);
		buttonText.setFont(CofferReferences.Comfortaa_Plain_13);
		buttonText.setHorizontalAlignment(SwingConstants.CENTER);
		buttonText.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonText.setBounds(0, 0, 100, 30);
		add(buttonText);
				
		buttonImg = new JLabel(CofferReferences.SMALL_BUTTON_IMG);
		buttonImg.setBounds(new Rectangle(0, 0, 100, 30));
		buttonImg.setBounds(0, 0, 100, 30);
		add(buttonImg);
	}

}
