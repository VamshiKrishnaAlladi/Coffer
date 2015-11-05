package com.avk.coffer;

import javax.swing.JPopupMenu;
import java.awt.Color;
import javax.swing.border.LineBorder;

@SuppressWarnings( "serial" )
public class CofferPopupMenu extends JPopupMenu {

	public CofferPopupMenu() {
		setBorder( new LineBorder( CofferReferences.CofferBlue ) );
		setBackground( Color.WHITE );
	}
}
