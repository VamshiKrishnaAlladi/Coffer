package com.avk.coffer;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class CofferRef {

	public static final ImageIcon COFFER_SAFE_LOGO_16X16 = new ImageIcon(Coffer.class.getResource("/CofferSafeLogo16x16.png"));
	public static final ImageIcon COFFER_SAFE_LOGO_32X32 = new ImageIcon(Coffer.class.getResource("/CofferSafeLogo32x32.png"));
	public static final ImageIcon COFFER_SAFE_LOGO_64X64 = new ImageIcon(Coffer.class.getResource("/CofferSafeLogo64x64.png"));
	public static final ImageIcon COFFER_SAFE_LOGO_128X128 = new ImageIcon(Coffer.class.getResource("/CofferSafeLogo128x128.png"));
	public static final ImageIcon COFFER_SAFE_BLACK_LOGO_16X16 = new ImageIcon(Coffer.class.getResource("/CofferSafeBlackLogo16x16.png"));
	public static final ImageIcon COFFER_LOGO_64X64 = new ImageIcon(Coffer.class.getResource("/Coffer_Logo_64x64.png"));
	
	public static final ImageIcon EXCLAIM_RED = new ImageIcon(Coffer.class.getResource("/exclaimRed.png"));

	public static final ImageIcon TEXTFIELD_IMG = new ImageIcon(Coffer.class.getResource("/longerRoundTextField.png"));
	
	public static final ImageIcon BUTTON_IMG = new ImageIcon(Coffer.class.getResource("/button.png"));
	
	public static final ImageIcon SMALL_BUTTON_IMG = new ImageIcon(Coffer.class.getResource("/smallButton.png"));
	
	public static final ArrayList<Image> COFFER_LOGOS = new ArrayList<Image>();
	
	public static Font 	Comfortaa_Plain_13, Comfortaa_Plain_14,	Comfortaa_Plain_15,
						Comfortaa_Bold_15, Comfortaa_Bold_16, Comfortaa_Bold_80,
						Comfortaa_Bold_Italic_15, Comfortaa_Bold_Italic_20,
						
						Antipasto_Bold_15, Antipasto_Bold_26;
	
	
	private static long COFFER_SEED;
	private static int COFFER_KEY_INDEX;
	
	public static final Clipboard SYS_CLIPBOARD =  Toolkit.getDefaultToolkit().getSystemClipboard();
	
	static{
	    try {
	    	Font Comfortaa = Font.createFont(Font.TRUETYPE_FONT, new File("./resrc/Comfortaa.ttf"));
	    	Font Antipasto = Font.createFont(Font.TRUETYPE_FONT, new File("./resrc/Antipasto.otf"));
	    	
	    	Comfortaa_Plain_13 = Comfortaa.deriveFont( Font.PLAIN, 13);
	    	Comfortaa_Plain_14 = Comfortaa.deriveFont( Font.PLAIN, 14);
	    	Comfortaa_Plain_15 = Comfortaa.deriveFont( Font.PLAIN, 15);

	    	Comfortaa_Bold_15 = Comfortaa.deriveFont(Font.BOLD, 15);
	    	Comfortaa_Bold_16 = Comfortaa.deriveFont(Font.BOLD, 16);
	    	Comfortaa_Bold_80 = Comfortaa.deriveFont(Font.BOLD, 80);
	    	
	    	Comfortaa_Bold_Italic_15 = Comfortaa.deriveFont(Font.ITALIC|Font.BOLD, 15);
	    	Comfortaa_Bold_Italic_20  = Comfortaa.deriveFont(Font.ITALIC|Font.BOLD, 20);
	    	
	    	Antipasto_Bold_15 = Antipasto.deriveFont(Font.BOLD, 15);
	    	Antipasto_Bold_26 = Antipasto.deriveFont(Font.BOLD, 26);
		    
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