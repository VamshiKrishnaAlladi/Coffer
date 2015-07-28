package com.avk.coffer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CofferPopupFrame extends JDialog {

	private final JPanel popupFrame;
	private int xPressed , yPressed;
	private int frameWidth = 300, frameHeight = 300;

	private JPanel contentPanel;
	private JLabel titleLbl;
	
	
	public CofferPopupFrame(boolean isModal, String title, Dimension d) {
		super(Coffer.frmcoffer, isModal);
		setMinimumSize(new Dimension(300, 300));
		
		Coffer.setDisable(true);
		
		frameWidth = d.width;
		frameHeight = d.height;
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) { Coffer.setDisable(false);}
		});

		setUndecorated(true);
		setSize(frameWidth, frameHeight);

		popupFrame = new JPanel();
		popupFrame.setBackground(CofferReferences.CofferLightGrey);
		popupFrame.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setForeground(Color.WHITE);
		lblX.setFont(CofferReferences.Antipasto_Bold_15);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setBounds(frameWidth - 35, 5, 30, 30);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Coffer.setDisable(false);
				CofferPopupFrame.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(CofferReferences.CofferBlue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.WHITE);
			}
		});
		popupFrame.add(lblX);

		titleLbl = new JLabel(title);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(0, 0, frameWidth, 40);
		titleLbl.setOpaque(true);
		titleLbl.setBackground(CofferReferences.CofferDarkGrey);
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(CofferReferences.Comfortaa_Bold_15);
		titleLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xPressed = e.getX();
				yPressed = e.getY();
			}
		});
		titleLbl.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				CofferPopupFrame.this.setLocation( ( x - xPressed), ( y - yPressed) );				
			}
		});
		popupFrame.add(titleLbl);

		
		CofferPopupFrame.this.setContentPane(popupFrame);
		
		contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setBounds(0, 40, frameWidth, frameHeight-40);
		popupFrame.add(contentPanel);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setUndecorated(true);
		setVisible(true);

	}


	@Override
	public Container getContentPane(){
		return contentPanel;
	}


	public void setContentPanel(JPanel contentPane) {
		contentPane.setBounds(0, 40, frameWidth, frameHeight-40);
		contentPanel = contentPane;
		popupFrame.add(contentPanel);
		CofferPopupFrame.this.repaint();
	}
	
	
	
}
