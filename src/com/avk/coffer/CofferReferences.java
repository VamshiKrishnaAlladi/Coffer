package com.avk.coffer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class CofferReferences {

	// Images used for Coffer logos
	public static final ImageIcon COFFER_SAFE_LOGO_16X16 = new ImageIcon(Coffer.class.getResource("/CofferSafeLogo16x16.png"));
	public static final ImageIcon COFFER_SAFE_LOGO_32X32 = new ImageIcon(Coffer.class.getResource("/CofferSafeLogo32x32.png"));
	public static final ImageIcon COFFER_SAFE_LOGO_64X64 = new ImageIcon(Coffer.class.getResource("/CofferSafeLogo64x64.png"));
	public static final ImageIcon COFFER_SAFE_LOGO_128X128 = new ImageIcon(Coffer.class.getResource("/CofferSafeLogo128x128.png"));
	public static final ImageIcon COFFER_SAFE_BLACK_LOGO_16X16 = new ImageIcon(Coffer.class.getResource("/CofferSafeBlackLogo16x16.png"));
	
	public static final ImageIcon COFFER_LOGO_64X64 = new ImageIcon(Coffer.class.getResource("/Coffer_Logo_64x64.png"));
	public static final ImageIcon COFFER_LOGO_SMALL = new ImageIcon(Coffer.class.getResource("/Coffer_Logo_Blue.png"));

	public static final ArrayList<Image> COFFER_LOGOS = new ArrayList<Image>();
	
	// Images used for Frame
	public static final ImageIcon COFFER_BACKGROUND_LAYER = new ImageIcon(Coffer.class.getResource("/backgroundLayer.png"));
	
	// Images used in DashBoard
	public static final ImageIcon DASHBOARD_TAB1 = new ImageIcon(Coffer.class.getResource("/tab1.png"));
	public static final ImageIcon DASHBOARD_TAB2 = new ImageIcon(Coffer.class.getResource("/tab2.png"));
	public static final ImageIcon DASHBOARD_TAB3 = new ImageIcon(Coffer.class.getResource("/tab3.png"));
	public static final ImageIcon DASHBOARD_TAB_KEYS = new ImageIcon(Coffer.class.getResource("/keys.png"));
	public static final ImageIcon DASHBOARD_TAB_ADD_KEY = new ImageIcon(Coffer.class.getResource("/addKey.png"));
	public static final ImageIcon DASHBOARD_TAB_KEY_GEN = new ImageIcon(Coffer.class.getResource("/keyGen.png"));
	
	// Images used in DashBoard - Password Almanac
	public static final ImageIcon COFFER_SCROLLBAR_TRACK = new ImageIcon(Coffer.class.getResource("/scrollbarTrack.png"));
	public static final ImageIcon COFFER_KEY = new ImageIcon(Coffer.class.getResource("/key.png"));

	public static final ImageIcon EXCLAIM_RED = new ImageIcon(Coffer.class.getResource("/exclaimRed.png"));
	public static final ImageIcon CLIPBOARD = new ImageIcon(Coffer.class.getResource("/clipBoard.png"));

	// Images used for Custom Components
	public static final ImageIcon CHECKBOX = new ImageIcon(Coffer.class.getResource("/checkBox.png")); 
	public static final ImageIcon CHECKEDBOX = new ImageIcon(Coffer.class.getResource("/checkedBox.png"));
	public static final ImageIcon TEXTFIELD_IMG = new ImageIcon(Coffer.class.getResource("/longerRoundTextField.png"));
	public static final ImageIcon PASSWORD_TOGGLE_IMG = new ImageIcon(Coffer.class.getResource("/passwordToggle.png"));
	public static final ImageIcon BUTTON_IMG = new ImageIcon(Coffer.class.getResource("/button.png"));
	public static final ImageIcon SMALL_BUTTON_IMG = new ImageIcon(Coffer.class.getResource("/smallButton.png"));
	public static final ImageIcon TEXT_BLANK = new ImageIcon(Coffer.class.getResource("/textBlank.png"));
	public static final ImageIcon MEDIUM_TEXT_BLANK = new ImageIcon(Coffer.class.getResource("/mediumTextBlank.png"));
	public static final ImageIcon PLUS = new ImageIcon(Coffer.class.getResource("/plus.png"));
	public static final ImageIcon MINUS = new ImageIcon(Coffer.class.getResource("/minus.png"));	
	
	// Images used in System's Tray Icon
	public static final ImageIcon COFFER_LOCK = new ImageIcon(Coffer.class.getResource("/lock.png"));
	public static final ImageIcon COFFER_FRAME_RESTORE =new ImageIcon(Coffer.class.getResource("/restore.png"));
	
	
	public static Font 	Comfortaa_Plain_13, Comfortaa_Plain_14,	Comfortaa_Plain_15,
						Comfortaa_Bold_15, Comfortaa_Bold_16, Comfortaa_Bold_80,
						Comfortaa_Bold_Italic_15, Comfortaa_Bold_Italic_20,
						
						Antipasto_Bold_15, Antipasto_Plain_15, Antipasto_Plain_26;
	
	public static Color CofferBlue = new Color(0,175,210);
	public static Color CofferDarkGrey = new Color(75, 75, 75);
	public static Color CofferLightGrey = new Color(100,100,100);
	public static Color CofferVeryLightGrey = new Color(150,150,150);
	
	private static long COFFER_SEED;
	private static int COFFER_KEY_INDEX;
	
	public static final Clipboard SYS_CLIPBOARD =  Toolkit.getDefaultToolkit().getSystemClipboard();
	
	static{
	    try {
	    	Font Comfortaa = Font.createFont(Font.TRUETYPE_FONT, Coffer.class.getResourceAsStream("/Comfortaa.ttf"));
	    	Font Antipasto = Font.createFont(Font.TRUETYPE_FONT, Coffer.class.getResourceAsStream("/Antipasto.otf"));
	    	
	    	Comfortaa_Plain_13 = Comfortaa.deriveFont( Font.PLAIN, 13);
	    	Comfortaa_Plain_14 = Comfortaa.deriveFont( Font.PLAIN, 14);
	    	Comfortaa_Plain_15 = Comfortaa.deriveFont( Font.PLAIN, 15);

	    	Comfortaa_Bold_15 = Comfortaa.deriveFont(Font.BOLD, 15);
	    	Comfortaa_Bold_16 = Comfortaa.deriveFont(Font.BOLD, 16);
	    	Comfortaa_Bold_80 = Comfortaa.deriveFont(Font.BOLD, 80);
	    	
	    	Comfortaa_Bold_Italic_15 = Comfortaa.deriveFont(Font.ITALIC|Font.BOLD, 15);
	    	Comfortaa_Bold_Italic_20  = Comfortaa.deriveFont(Font.ITALIC|Font.BOLD, 20);
	    	
	    	Antipasto_Bold_15 = Antipasto.deriveFont(Font.BOLD, 15);
	    	Antipasto_Plain_15 = Antipasto.deriveFont(Font.PLAIN, 15);
	    	Antipasto_Plain_26 = Antipasto.deriveFont(Font.PLAIN, 26);
		    
	    	COFFER_LOGOS.add(COFFER_SAFE_LOGO_128X128.getImage());
		    COFFER_LOGOS.add(COFFER_SAFE_LOGO_64X64.getImage());
		    COFFER_LOGOS.add(COFFER_SAFE_LOGO_32X32.getImage());
		    COFFER_LOGOS.add(COFFER_SAFE_LOGO_16X16.getImage());
		    COFFER_LOGOS.add(COFFER_SAFE_BLACK_LOGO_16X16.getImage());
		    
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static long getCofferSeed(){ return COFFER_SEED; }
	
	public static void setCofferSeed(long seed){ COFFER_SEED = seed; }
	
	public static int getCofferKeyIndex(){
		COFFER_KEY_INDEX = (int) COFFER_SEED % 100000;
		return COFFER_KEY_INDEX;
	}
}