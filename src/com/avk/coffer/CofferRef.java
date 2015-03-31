package com.avk.coffer;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public static long COFFER_SEED;
	public static int COFFER_KEY_INDEX;
	
	
	public static Scanner seedFileScanner;
	
    public static final Clipboard SYS_CLIPBOARD =  Toolkit.getDefaultToolkit().getSystemClipboard();

	static{
	    try {
		    COFFER_LOGOS.add(COFFER_SAFE_LOGO_128X128.getImage());
		    COFFER_LOGOS.add(COFFER_SAFE_LOGO_64X64.getImage());
		    COFFER_LOGOS.add(COFFER_SAFE_LOGO_32X32.getImage());
		    COFFER_LOGOS.add(COFFER_SAFE_LOGO_16X16.getImage());
		    COFFER_LOGOS.add(COFFER_SAFE_BLACK_LOGO_16X16.getImage());
		    
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static long getCofferSeed() throws Exception{
		seedFileScanner = new Scanner(new File("./Coffer/.cofferseed"));
		COFFER_SEED = Long.parseLong(seedFileScanner.next());
		seedFileScanner.close();
		return COFFER_SEED;
	}
	
	public static int getCofferKeyIndex() throws Exception{
		COFFER_KEY_INDEX = (int) getCofferSeed()%100000;
		return COFFER_KEY_INDEX;
	}
}