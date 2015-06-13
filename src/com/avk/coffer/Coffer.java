package com.avk.coffer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class Coffer {

	public static JFrame frmcoffer;
	private static JPanel contentPanel, disablePanel;
	private static CofferMenu menuPanel;
	private static CardLayout cl;
	private static JLabel titleBar, frmTitleLabel, frmStatusLabel;
	private static Thread Sleeper;


	private static File MUTEX_FILE = new File("./Coffer/Coffer.mutex");
	public static File KEY_FILE = new File("./Coffer/.cofferkey");
	private static Scanner MUTEX_SCANNER;

	private static TrayIcon trayIcon;
	private static SystemTray tray;

	private int xPressed, yPressed;

	private JLabel lbl_, lblX, frmIconImg, frmDragger;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					if(!MUTEX_FILE.exists())
					{
						File cofferFolders = new File("./Coffer");
						cofferFolders.mkdirs();
						MUTEX_FILE.createNewFile();
						MUTEX_SCANNER = new Scanner(MUTEX_FILE);
						MUTEX_FILE.deleteOnExit();
						new Coffer();
					}
					else{
						new CofferExtraInstance();
					}

				}
				catch (Exception e) { e.printStackTrace(); }
			}
		});
	}


	/**
	 * Create the application.
	 */
	public Coffer() { initialize(); }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try{
			if(SystemTray.isSupported())
			{
				tray=SystemTray.getSystemTray();
				Image image=CofferReferences.COFFER_SAFE_BLACK_LOGO_16X16.getImage();

				CofferTrayPopup popup=new CofferTrayPopup();
				JDialog hiddenDialog = new JDialog();
				hiddenDialog.addWindowFocusListener(new WindowAdapter() {
					@Override
					public void windowLostFocus (WindowEvent we ) {
						hiddenDialog.setVisible(false);
					}
				});
				hiddenDialog.setSize(10, 10);
				hiddenDialog.setUndecorated(true);

				trayIcon=new TrayIcon(image, "Coffer - A portable password manager.", null);
				trayIcon.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent e) {
						if (e.isPopupTrigger()) {
							popup.setLocation(e.getX(), e.getY());
							hiddenDialog.setLocation(e.getX(), e.getY());
							popup.setInvoker(hiddenDialog);
							hiddenDialog.setVisible(true);
							popup.setVisible(true);
						}
					}
				});
				trayIcon.setImageAutoSize(true);
			}
			else{ System.out.println("system tray not supported"); }


			frmcoffer = new JFrame();
			frmcoffer.setIconImages(CofferReferences.COFFER_LOGOS);
			frmcoffer.setTitle("Coffer");
			frmcoffer.setUndecorated(true);
			frmcoffer.setBounds(0, 0, 750, 550);
			frmcoffer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frmcoffer.getContentPane().setLayout(null);
			frmcoffer.setLocationRelativeTo(null);
			frmcoffer.addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) { Coffer.setStatus("Hey there! Welcome to Coffer. :)"); }
				@Override
				public void windowDeiconified(WindowEvent e) { Coffer.setStatus("Welcome Back. :)"); }
				@Override
				public void windowClosing(WindowEvent e) { clearAndExit();}
			});
			frmcoffer.addWindowStateListener(new WindowStateListener() {
				public void windowStateChanged(WindowEvent e) {
					if(e.getNewState()==JFrame.ICONIFIED){
						try {
							tray.add(trayIcon);
							frmcoffer.setVisible(false);
							Sleeper = new Thread(new Runnable(){
								@Override
								public void run()
								{
									try {
										Thread.sleep(60 * 1000);
										if(frmcoffer.getState() == JFrame.ICONIFIED)
											lockCoffer();
									}
									catch(InterruptedException e){ e.printStackTrace(); }
								} 
							});
							Sleeper.start(); 
						} 
						catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					if(e.getNewState()==JFrame.NORMAL){
						Coffer.makeAppear();
					}
				}
			});
			frmcoffer.setVisible(true);

			lbl_ = new JLabel("_");
			lbl_.setVerticalAlignment(SwingConstants.TOP);
			lbl_.setForeground(Color.WHITE);
			lbl_.setFont(CofferReferences.Antipasto_Plain_26);
			lbl_.setHorizontalTextPosition(SwingConstants.CENTER);
			lbl_.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String[] msgs = {"Your Coffer will be locked in a minute.", "Do you want to lock it right away?"};
					CofferDialog lockDialog = new CofferDialog(true,"Lock Confirmation",msgs,CofferDialog.YES_NO_CANCEL_OPTIONS);

					switch(lockDialog.selectedOption){
					case CofferDialog.YES_OPTION:{
						lockCoffer();
					}

					case CofferDialog.NO_OPTION:{
						frmcoffer.setState(Frame.ICONIFIED);
						break;
					}
					}
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					lbl_.setForeground(CofferReferences.CofferBlue);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lbl_.setForeground(Color.WHITE);
				}
			});

			lbl_.setBounds(660, 15, 40, 40);


			lblX = new JLabel("X");
			lblX.setForeground(Color.WHITE);
			lblX.setHorizontalAlignment(SwingConstants.CENTER);
			lblX.setFont(CofferReferences.Antipasto_Plain_26);
			lblX.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					String[] msgs = {"You are about to exit.", "Do you want to continue?"};
					CofferDialog exitDialog = new CofferDialog(true, "Exit Confirmation", msgs , CofferDialog.YES_NO_OPTIONS);
					if(exitDialog.selectedOption==CofferDialog.YES_OPTION)
						clearAndExit();					
				}
				@Override
				public void mouseEntered(MouseEvent e) { lblX.setForeground(CofferReferences.CofferBlue); }
				@Override
				public void mouseExited(MouseEvent e) { lblX.setForeground(Color.WHITE); }
			});
			lblX.setBounds(700, 10, 40, 40);

			
			frmIconImg = new JLabel(CofferReferences.COFFER_LOGO_SMALL);
			frmIconImg.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					menuPanel.toggleMenu();
				}
			});
			frmIconImg.setMinimumSize(new Dimension(30, 30));
			frmIconImg.setBounds(10, 10, 40, 40);
			frmIconImg.setBackground(Color.WHITE);


			frmDragger = new JLabel();
			frmDragger.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					xPressed = e.getX();
					yPressed = e.getY();
				}
			});
			frmDragger.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();

					frmcoffer.setLocation( ( x - xPressed), ( y - yPressed) );				
				}
			});		
			frmDragger.setBounds(0, 0, 750, 60);

			frmTitleLabel = new JLabel();
			frmTitleLabel.setBackground(CofferReferences.CofferDarkGrey);
			frmTitleLabel.setOpaque(true);
			frmTitleLabel.setBounds(10, 10, 730, 40);
			frmTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
			frmTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frmTitleLabel.setForeground(Color.WHITE);
			frmTitleLabel.setFont(CofferReferences.Comfortaa_Bold_16);
			frmTitleLabel.setText("Coffer");

			frmStatusLabel = new JLabel();
			frmStatusLabel.setBackground(CofferReferences.CofferDarkGrey);
			frmStatusLabel.setOpaque(true);
			frmStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frmStatusLabel.setBounds(0, 520, 750, 30);
			frmStatusLabel.setFont(CofferReferences.Comfortaa_Bold_Italic_15);
			frmStatusLabel.setForeground(Color.white);

			titleBar = new JLabel();
			titleBar.setOpaque(true);
			titleBar.setBackground(CofferReferences.CofferLightGrey);
			titleBar.setBounds(0, 0, 750, 60);

			menuPanel = new CofferMenu();
			menuPanel.setMenuLocation(CofferMenu.HORIZONTAL, CofferMenu.TOP, new Rectangle(0, -90, 750, 152));
			menuPanel.setToggleConstraints(148, 1, 4);
			menuPanel.setBorder(new MatteBorder(0, 0, 2, 0, CofferReferences.CofferBlue));

			disablePanel = new JPanel();
			disablePanel.setBackground(new Color(75,75,75,90));
			disablePanel.setBounds(0, 60, 750, 460);
			disablePanel.setVisible(false);

			cl=new CardLayout(0, 0);
			contentPanel= new JPanel(cl);
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBounds(0, 60, 750, 460);

			
			
			frmcoffer.getContentPane().add(lbl_);
			frmcoffer.getContentPane().add(lblX);
			frmcoffer.getContentPane().add(frmIconImg);
			frmcoffer.getContentPane().add(frmDragger);
			frmcoffer.getContentPane().add(frmTitleLabel);
			frmcoffer.getContentPane().add(frmStatusLabel);
			frmcoffer.getContentPane().add(titleBar);
			frmcoffer.getContentPane().add(menuPanel);
			frmcoffer.getContentPane().add(disablePanel);			
			frmcoffer.getContentPane().add(contentPanel);

			if(!KEY_FILE.exists()){ Coffer.swapTo("CreateUserPage"); }
			else{ Coffer.swapTo("LoginPage"); }

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void setStatus(String msg){ frmStatusLabel.setText(msg); }

	public static String getStatus(){ return frmStatusLabel.getText(); }

	public static void setTitle(String title){ frmTitleLabel.setText(title); }

	public static String getTitle(){ return frmTitleLabel.getText(); }

	public static void setDisable(boolean flag){ disablePanel.setVisible(flag); }

	public static boolean isDisabled(){return disablePanel.isVisible();}
	
	public static boolean isMenuShown(){ return menuPanel.isMenuShown();}

	public static void toggleMenu() { menuPanel.toggleMenu(); }

	public static void swapTo(String page){
		contentPanel.removeAll();
		switch(page){
		case "DashBoard":{
			Coffer.setStatus("On board at DashBoard. ;)");
			contentPanel.add(new DashBoard(),"DashBoard");
			break;
		}
		case "LoginPage":{
			Coffer.setStatus("Prove this coffer is your's.");
			contentPanel.add(new LoginPage(),"LoginPage");
			break;
		}
		case "CreateUserPage":{
			Coffer.setStatus("Register credentials for creating a new Coffer");
			contentPanel.add(new CreateUserPage(),"CreateUserPage");
			break;
		}
		}
		cl.show(contentPanel, page);
	}

	public static void makeAppear(){
		frmcoffer.setState(Frame.NORMAL);
		frmcoffer.setVisible(true);
		frmcoffer.setAlwaysOnTop(true);
		frmcoffer.setAlwaysOnTop(false);
		tray.remove(trayIcon);
	}

	public static void writeText2File(String text, String outputFilePath){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
			bw.write(text);
			bw.flush();
			bw.close();
		} catch (Exception e) { e.printStackTrace(); }
	}

	public static void writeBytes2File(byte[] textBytes, String outputFilePath){
		try {
			FileOutputStream outputStream = new FileOutputStream(new File(outputFilePath));
			outputStream.write(textBytes);
			outputStream.close();
		} catch (Exception e) { e.printStackTrace(); }
	}

	public static byte[] readBytesFromFile(File inputFile) throws Exception{
		FileInputStream inputStream = new FileInputStream(inputFile);
		byte[] textBytes = new byte[(int) inputFile.length()];
		inputStream.read(textBytes);
		inputStream.close();
		return textBytes;
	}

	public static void clearAndExit(){
		if(MUTEX_FILE.exists())
		{
			CofferReferences.SYS_CLIPBOARD.setContents(new StringSelection(""), null);
			MUTEX_SCANNER.close();
			MUTEX_FILE.deleteOnExit();
			System.exit(0);
		}
	}

	public static void lockCoffer(){
		if(!Coffer.KEY_FILE.exists()){
			Coffer.swapTo("CreateUserPage"); 
		}
		else{
			Coffer.swapTo("LoginPage");
		}
	}
}