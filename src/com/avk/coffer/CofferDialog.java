package com.avk.coffer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
public class CofferDialog extends JDialog  {

	private final JPanel contentPanel = new JPanel();

	public CofferDialog(String title, String[] strs) {
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(CofferReferences.CofferBlue, 1));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel titleLbl = new JLabel(title);
		titleLbl.setBounds(20, 10, 365, 30);
		titleLbl.setForeground(CofferReferences.CofferBlue);
		titleLbl.setFont(CofferReferences.Comfortaa_Bold_16);
		contentPanel.add(titleLbl);
		
		JLabel lblX = new JLabel("X");
		contentPanel.add(lblX);
		lblX.setForeground(CofferReferences.CofferBlue);
		lblX.setFont(CofferReferences.Antipasto_Bold_15);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setBounds(375, 5, 20, 20);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CofferDialog.this.dispose();
			}
		});

	
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		for(int i = 0 ; i < strs.length; i++)
		{
			JLabel lbl = new JLabel(strs[i]);
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
		scrollPane.setBounds(20, 50, 350, 190);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setViewportBorder(null);
		
		JScrollBar vsb = scrollPane.getVerticalScrollBar();
		vsb.setUI(new CofferScrollbarUI(null));
		vsb.setPreferredSize(new Dimension(10,190));
		vsb.setOpaque(false);
		vsb.setUnitIncrement(10);
		
		contentPanel.add(scrollPane);
	}
}
