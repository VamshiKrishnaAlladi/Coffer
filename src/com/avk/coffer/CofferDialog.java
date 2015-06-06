package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class CofferDialog extends JDialog {

	// CofferDialog Button pane - Options
	public final static int CLOSE_OPTION = 0;
	public final static int YES_OPTION = 1;
	public final static int NO_OPTION = 2;
	public final static int CANCEL_OPTION = 3;
	public final static int OK_OPTION = 4;
		
	
	// CofferDialog Button pane - Option Types
	public final static int YES_NO_CANCEL_OPTIONS = 123;
	public final static int YES_NO_OPTIONS = 12;
	public final static int OK_CANCEL_OPTIONS = 43;
	
	public int selectedOption;
	private int xPressed;
	private int yPressed;

	public CofferDialog(boolean isModal, String title, String[] messages, int Option_type) {
		super(Coffer.frmcoffer, isModal);
		
		selectedOption = 0;
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new LineBorder(CofferReferences.CofferDarkGrey));
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(97, 92, 200, 50);
		contentPanel.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setForeground(CofferReferences.CofferBlue);
		lblX.setFont(CofferReferences.Antipasto_Bold_15);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setBounds(410, 5, 30, 30);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CofferDialog.this.dispose();
			}
		});
		contentPanel.add(lblX);

		JLabel titleLbl = new JLabel(title);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(0, 0, 450, 40);
		titleLbl.setOpaque(true);
		titleLbl.setBackground(CofferReferences.CofferDarkGrey);
		titleLbl.setForeground(CofferReferences.CofferBlue);
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

				CofferDialog.this.setLocation( ( x - xPressed), ( y - yPressed) );				
			}
		});
		contentPanel.add(titleLbl);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		int length = messages.length;
		for(int i = 0 ; i < length ; i++)
		{
			JLabel lbl = new JLabel(messages[i]);
			if(i % 2 == 0)
				lbl.setForeground(CofferReferences.CofferLightGrey);
			else
				lbl.setForeground(CofferReferences.CofferBlue);
			lbl.setFont(CofferReferences.Comfortaa_Plain_13);
			panel.add(lbl);
			panel.add(Box.createRigidArea(new Dimension(0,5)));
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(25, 60, 410,(length < 3)? 50 : 125);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setViewportBorder(null);
		
		JScrollBar vsb = scrollPane.getVerticalScrollBar();
		vsb.setUI(new CofferScrollbarUI(null));
		vsb.setPreferredSize(new Dimension(10,125));
		vsb.setOpaque(false);
		vsb.setUnitIncrement(10);
		
		contentPanel.add(scrollPane);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setBackground(Color.WHITE);
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.TRAILING);
		buttonPanel.setBounds(10, (length < 3)? 105 : 200, 430, 40);
		contentPanel.add(buttonPanel);
		
		CofferSmallButton okButton = new CofferSmallButton("OK");
		okButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent me){ selectedOption = OK_OPTION; CofferDialog.this.dispose();}
		});
		buttonPanel.add(okButton);

		CofferSmallButton yesButton = new CofferSmallButton("Yes");
		yesButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent me){ selectedOption = YES_OPTION; CofferDialog.this.dispose();}
		});
		buttonPanel.add(yesButton);
		
		CofferSmallButton noButton = new CofferSmallButton("No");
		noButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent me){ selectedOption = NO_OPTION; CofferDialog.this.dispose();}
		});
		buttonPanel.add(noButton);
		
		CofferSmallButton cancelButton = new CofferSmallButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter(){ 
			@Override
			public void mouseClicked(MouseEvent me){ selectedOption = CANCEL_OPTION; CofferDialog.this.dispose();}
		});
		buttonPanel.add(cancelButton);		
		
		switch( Option_type ){
		
			case YES_NO_CANCEL_OPTIONS:{
				okButton.setVisible(false);
				break;
			}
			
			case YES_NO_OPTIONS:{
				okButton.setVisible(false);
				cancelButton.setVisible(false);
				break;
			}
			
			case OK_CANCEL_OPTIONS:{
				yesButton.setVisible(false);
				noButton.setVisible(false);
				break;
			}
			
			case OK_OPTION:{
				yesButton.setVisible(false);
				noButton.setVisible(false);
				cancelButton.setVisible(false);
				break;
			}
			
		}

		
		setContentPane(contentPanel);
		
		setBounds(new Rectangle(0, 0, 450,(length < 3)? 150 : 250));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setUndecorated(true);
		setVisible(true);
	
	}

}