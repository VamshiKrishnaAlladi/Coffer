package com.avk.coffer;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;

import com.avk.animation.Animation;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class CofferMenu extends JPanel {
	
	// constants for menu orientation 
	public final static int HORIZONTAL 	= 0;
	public final static int VERTICAL 	= 1;
	
	// constants for menu location
	public final static int TOP 	= 0;
	public final static int BOTTOM 	= 1;
	public final static int LEFT 	= 2;
	public final static int RIGHT 	= 3;
	
	private Rectangle initialBounds;
	private int location;
	private int orientation;
	private int toggleOffset;
	private int interval;
	private int pixelSteps;
	
	public CofferMenu() {
		super();
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(isMenuShown())
					toggleMenu();
			}
		});
		setLayout(null);
		setBackground(CofferReferences.CofferLightGrey);
		setPreferredSize(new Dimension(750,150));
	}
	
	public void toggleMenu() {
		if(orientation == CofferMenu.HORIZONTAL){
			int y =  initialBounds.y;
			if(location == CofferMenu.TOP){
				Animation.moveComponentDown(CofferMenu.this, y, y + toggleOffset, interval, pixelSteps );
			}
			else if(location == CofferMenu.BOTTOM)
				Animation.moveComponentUp(CofferMenu.this, y, y - toggleOffset, interval, pixelSteps );
		}
		else if(orientation == CofferMenu.VERTICAL){
			int x =  initialBounds.x;
			if(location == CofferMenu.LEFT)
				Animation.moveComponentRight(CofferMenu.this, x, x + toggleOffset, interval, pixelSteps );
			else if(location == CofferMenu.RIGHT)
				Animation.moveComponentRight(CofferMenu.this, x, x - toggleOffset, interval, pixelSteps );
		}
		CofferMenu.this.grabFocus();
		Coffer.setDisable(!Coffer.isDisabled());
	}

	public boolean isMenuShown(){
		boolean result = true;
		
		if(orientation == CofferMenu.VERTICAL)
			result = initialBounds.x != getBounds().x;
		else if(orientation == CofferMenu.HORIZONTAL)
			result = initialBounds.y != getBounds().y;
		
		return result;
	}


	public void setMenuLocation(int menuOrientation, int menuLocation, Rectangle initialBounds) {
		this.orientation = menuOrientation;
		this.location = menuLocation;
		this.initialBounds = initialBounds;
		this.setBounds(initialBounds);
	}
	
	public void setToggleConstraints(int toggleOffset, int interval, int pixelSteps){
		this.toggleOffset = toggleOffset;
		this.interval = interval;
		this.pixelSteps = pixelSteps;
	}

}
