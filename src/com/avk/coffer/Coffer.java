package com.avk.coffer;

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Coffer {

	private static JFrame frmcoffer;
	private static JPanel contentPanel;
	private static CardLayout cl;
	private static JLabel frmStatusLabel;
	private static JLabel frmTitleLabel;
	
	private static int xPressed;
	private int yPressed;

    private static File MUTEX_FILE = new File("./Coffer/Coffer.mutex");
    private static File KEY_FILE = new File("./Coffer/.cofferkey");
    private static Scanner MUTEX_SCANNER;

	private static TrayIcon trayIcon;
    private static SystemTray tray;
    
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
	            Image image=CofferRef.COFFER_SAFE_LOGO_16X16.getImage();
	            PopupMenu popup=new PopupMenu();
	            MenuItem item=new MenuItem("Restore");
	            item.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) { makeAppear(); }
	            });
	            popup.add(item);
	            popup.addSeparator();
	            item=new MenuItem("Exit");
	            item.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) { clearAndExit(); }
	            });
	            popup.add(item);		          
	            trayIcon=new TrayIcon(image, "Coffer - A portable password manager.", popup);
	            trayIcon.setImageAutoSize(true);
	        }
	        else{ System.out.println("system tray not supported"); }
	        
	        
			frmcoffer = new JFrame();
			frmcoffer.setIconImages(CofferRef.COFFER_LOGOS);
			frmcoffer.setTitle("Coffer");
			frmcoffer.setUndecorated(true);
			frmcoffer.setBounds(0, 0, 750, 550);
			frmcoffer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frmcoffer.getContentPane().setLayout(null);
			frmcoffer.setLocationRelativeTo(null);
			frmcoffer.addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) { Coffer.setStatus("Welcome to your Coffer. :)"); }
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
	                    } 
	                    catch (AWTException ex) {
	                        ex.printStackTrace();
	                    }
	                }
	                if(e.getNewState()==JFrame.NORMAL){
	                    Coffer.makeAppear();
	                }
	            }
			});
			frmcoffer.setVisible(true);

			JLabel lbl_ = new JLabel();
			lbl_.setVerticalAlignment(SwingConstants.TOP);
			lbl_.setFont(new Font("Antipasto", Font.BOLD, 26));
			lbl_.setHorizontalTextPosition(SwingConstants.CENTER);
			lbl_.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_.setText("");
			lbl_.setForeground(new Color(0,175,210));
			lbl_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						String username = CofferCrypt.decryptFromFile_Index(CofferRef.getCofferKeyIndex(), new File("./Coffer/.cofferkey"));
						if(username.equals("nouser")){ Coffer.swapTo("CreateUserPage"); }
						else{ Coffer.swapTo("LoginPage"); }
						frmcoffer.setState(Frame.ICONIFIED);
					} catch (Exception e) { e.printStackTrace(); }
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					lbl_.setText("_");				
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lbl_.setText("");
				}
			});
			lbl_.setBounds(669, 10, 40, 40);
			frmcoffer.getContentPane().add(lbl_);


			JLabel lblX = new JLabel();
			lblX.setText("");
			lblX.setHorizontalAlignment(SwingConstants.CENTER);
			lblX.setFont(new Font("Antipasto", Font.BOLD, 26));
			lblX.setForeground(new Color(0,175,210));
			lblX.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					clearAndExit();
				}
				@Override
				public void mouseEntered(MouseEvent e) { lblX.setText("X");	}
				@Override
				public void mouseExited(MouseEvent e) {	lblX.setText(""); }
			});
			lblX.setBounds(701, 9, 40, 40);
			frmcoffer.getContentPane().add(lblX);


			JLabel frmDragger = new JLabel();
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
			frmcoffer.getContentPane().add(frmDragger);

			frmTitleLabel = new JLabel();
			frmTitleLabel.setBounds(10, 10, 730, 40);
			frmTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
			frmTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frmTitleLabel.setForeground(Color.WHITE);
			frmTitleLabel.setFont(new Font("Comfortaa", Font.BOLD, 16));
			frmTitleLabel.setText("Coffer");
			frmcoffer.getContentPane().add(frmTitleLabel);

			JLabel frmIconImg = new JLabel(new ImageIcon(this.getClass().getResource("/Coffer_Logo_Blue.png")));
			frmIconImg.setMinimumSize(new Dimension(30, 30));
			frmIconImg.setBounds(10, 10, 40, 40);
			frmIconImg.setBackground(Color.WHITE);
			frmcoffer.getContentPane().add(frmIconImg);

			frmStatusLabel = new JLabel("");
			frmStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
			frmStatusLabel.setBounds(10, 520, 730, 30);
			frmStatusLabel.setFont(new Font("Comfortaa", Font.ITALIC|Font.BOLD, 15));
			frmStatusLabel.setForeground(Color.white);
			frmcoffer.getContentPane().add(frmStatusLabel);

			cl=new CardLayout(0, 0);
			contentPanel= new JPanel(cl);
			contentPanel.setOpaque(false);
			contentPanel.setBounds(0, 60, 750, 460);
			contentPanel.add(new LoginPage(),"LoginPage");
			contentPanel.add(new CreateUserPage(),"CreateUserPage");
//			contentPanel.add(new DashBoard(),"DashBoard");
			frmcoffer.getContentPane().add(contentPanel);

			if(!KEY_FILE.exists()){ Coffer.swapTo("CreateUserPage"); }
			else{ Coffer.swapTo("LoginPage"); }

			JLabel window_img = new JLabel(new ImageIcon(this.getClass().getResource("/CofferBackground.png")));
			window_img.setBounds(0, 0, 750, 550);
			frmcoffer.getContentPane().add(window_img);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void setStatus(String msg){ frmStatusLabel.setText(msg); }
	
	public static String getStatus(){ return frmStatusLabel.getText(); }
	
	public static void setTitle(String title){ frmTitleLabel.setText(title); }
	
	public static String getTitle(){ return frmTitleLabel.getText(); }
	
	public static void swapTo(String page){
		if(page == "DashBoard"){
			contentPanel.add(new DashBoard(),"DashBoard");
		}
		cl.show(contentPanel, page);
	}
	
	static void makeAppear(){
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

	static void clearAndExit(){
		CofferRef.SYS_CLIPBOARD.setContents(new StringSelection(""), null);
        MUTEX_SCANNER.close();
        MUTEX_FILE.deleteOnExit();
        System.exit(0);
	}
}