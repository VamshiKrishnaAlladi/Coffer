package com.avk.coffer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CofferScrollbarUI extends BasicScrollBarUI {

	private Image trackImg = null;
	
	@SuppressWarnings("serial")
	private JButton b = new JButton() {

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 0);
        }

    };
	
	public CofferScrollbarUI(Image trackImg) {
		this.trackImg = trackImg;
	}
	
	@Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		g.translate(trackBounds.x, trackBounds.y);
		if(this.trackImg != null)
			((Graphics2D)g).drawImage(trackImg,AffineTransform.getScaleInstance(1,(double)trackBounds.height/trackImg.getHeight(null)),null);
		else{
			g.setColor( CofferRef.CofferBlue );
			g.drawRect( 0, 4, trackBounds.width - 1 , trackBounds.height - 9 );
	    }
		g.translate( -trackBounds.x, -trackBounds.y );
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
    	g.translate(thumbBounds.x, thumbBounds.y);
        g.setColor( CofferRef.CofferBlue );
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
