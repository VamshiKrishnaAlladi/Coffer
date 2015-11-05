package com.avk.animation;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

public class Animation {

	private static Timer movementTimer;

	public static void moveComponentLeft( JComponent component, int initialPosition, int finalPosition, int interval, int pixelSteps ) {
		Rectangle R = component.getBounds();

		if ( R.x >= initialPosition ) {
			movementTimer = new Timer( interval, new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {
					if ( R.x > finalPosition ) {
						R.x -= pixelSteps;
						component.setBounds( R );
						component.getParent().repaint();
					}
					else
						movementTimer.stop();
				}
			} );
		}
		else if ( R.x <= finalPosition ) {
			movementTimer = new Timer( interval, new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {
					if ( R.x < initialPosition ) {
						R.x += pixelSteps;
						component.setBounds( R );
						component.getParent().repaint();
					}
					else
						movementTimer.stop();
				}
			} );
		}

		movementTimer.start();
	}

	public static void moveComponentRight( JComponent component, int initialPosition, int finalPosition, int interval, int pixelSteps ) {
		Rectangle R = component.getBounds();

		if ( R.x <= initialPosition ) {
			movementTimer = new Timer( interval, new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {
					if ( R.x < finalPosition ) {
						R.x += pixelSteps;
						component.setBounds( R );
						component.getParent().repaint();
					}
					else
						movementTimer.stop();
				}
			} );
		}
		else if ( R.x >= finalPosition ) {
			movementTimer = new Timer( interval, new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {
					if ( R.x > initialPosition ) {
						R.x -= pixelSteps;
						component.setBounds( R );
						component.getParent().repaint();
					}
					else
						movementTimer.stop();
				}
			} );
		}

		movementTimer.start();
	}

	public static void moveComponentUp( JComponent component, int initialPosition, int finalPosition, int interval, int pixelSteps ) {
		Rectangle R = component.getBounds();

		if ( R.y >= initialPosition ) {
			movementTimer = new Timer( interval, new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {
					if ( R.y > finalPosition ) {
						R.y -= pixelSteps;
						component.setBounds( R );
						component.getParent().repaint();
					}
					else
						movementTimer.stop();
				}
			} );
		}
		else if ( R.y <= finalPosition ) {
			movementTimer = new Timer( interval, new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {
					if ( R.y < initialPosition ) {
						R.y += pixelSteps;
						component.setBounds( R );
						component.getParent().repaint();
					}
					else
						movementTimer.stop();
				}
			} );
		}

		movementTimer.start();
	}

	public static void moveComponentDown( JComponent component, int initialPosition, int finalPosition, int interval, int pixelSteps ) {
		Rectangle R = component.getBounds();

		if ( R.y <= initialPosition ) {
			movementTimer = new Timer( interval, new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {
					if ( R.y < finalPosition ) {
						R.y += pixelSteps;
						component.setBounds( R );
						component.getParent().repaint();
					}
					else
						movementTimer.stop();
				}
			} );
		}
		else if ( R.y >= finalPosition ) {
			movementTimer = new Timer( interval, new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ) {
					if ( R.y > initialPosition ) {
						R.y -= pixelSteps;
						component.setBounds( R );
						component.getParent().repaint();
					}
					else
						movementTimer.stop();
				}
			} );
		}
		movementTimer.start();
	}
}
