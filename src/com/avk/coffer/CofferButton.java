package com.avk.coffer;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class CofferButton extends JPanel {

	private JLabel buttonImg;
	private JLabel buttonText;
	
	public CofferButton(String text) {
		setLayout(null);
		setOpaque(false);
		
		buttonText = new JLabel(text);
		buttonText.setForeground(Color.WHITE);
		buttonText.setFont(CofferReferences.Comfortaa_Plain_14);
		buttonText.setHorizontalAlignment(SwingConstants.CENTER);
		buttonText.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonText.setBounds(0, 0, 200, 40);
		add(buttonText);
				
		buttonImg = new JLabel(CofferReferences.BUTTON_IMG);
		buttonImg.setBounds(new Rectangle(0, 0, 200, 40));
		buttonImg.setBounds(0, 0, 200, 40);
		add(buttonImg);
	}

}
