package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CofferScrollbarUI extends BasicScrollBarUI {

	private Image trackImg;
	
	@SuppressWarnings("serial")
	private JButton b = new JButton() {

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 0);
        }

    };
	
	public CofferScrollbarUI() {
		trackImg = new ImageIcon(this.getClass().getResource("/scrollbarTrack.png")).getImage();
	}
	
	@Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		g.translate(trackBounds.x, trackBounds.y);
        ((Graphics2D)g).drawImage(trackImg,AffineTransform.getScaleInstance(1,(double)trackBounds.height/trackImg.getHeight(null)),null);
        g.translate( -trackBounds.x, -trackBounds.y );
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
    	g.translate(thumbBounds.x, thumbBounds.y);
        g.setColor( new Color(0,175,210) );
        g.fillRect( 2, 6, thumbBounds.width - 4, thumbBounds.height - 12 );
        g.translate( -thumbBounds.x, -thumbBounds.y );
    }
    
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return b;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return b;
    }
}
