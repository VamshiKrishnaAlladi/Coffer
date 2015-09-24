package com.avk.coffer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.border.AbstractBorder;

@SuppressWarnings("serial")
public class CofferRoundBorder extends AbstractBorder {

	Color borderColor;
	int radius;
	int thickness;

	public CofferRoundBorder(Color borderColor, int thickness, int radius) {
		this.borderColor = borderColor;
		this.thickness = thickness;
		this.radius = radius;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.setStroke(new BasicStroke(thickness));
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(borderColor);
		graphics.drawRoundRect(x, y, width - thickness / 2, height - thickness / 2, radius, radius);
	}
}
