package com.avk.coffer;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings( "serial" )
public class SettingsPage extends JPanel {

	private static final int pageWidth = CofferSettings.COFFER_PAGE_SIZE.width;
	private static final int pageHeight = CofferSettings.COFFER_PAGE_SIZE.height;

	public SettingsPage() {
		setPreferredSize( new Dimension( pageWidth, pageHeight ) );
		setOpaque( false );
		setLayout( null );

		JLabel lblTitle = new JLabel( "Settings" );
		lblTitle.setBorder( new EmptyBorder( 0, 10, 0, 0 ) );
		lblTitle.setForeground( CofferReferences.CofferBlue );
		lblTitle.setFont( CofferReferences.Comfortaa_Bold_Italic_20 );
		lblTitle.setBounds( 50, 25, 300, 50 );
		add( lblTitle );

	}

}
