package com.avk.coffer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		contentPanel.setBorder(new LineBorder(new Color(0,175,210), 1));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel titleLbl = new JLabel(title);
		titleLbl.setBounds(20, 10, 365, 30);
		titleLbl.setForeground(new Color(0,175,210));
		titleLbl.setFont(new Font("Comfortaa", Font.BOLD, 16));
		contentPanel.add(titleLbl);
		
		JLabel lblX = new JLabel("X");
		contentPanel.add(lblX);
		lblX.setForeground(new Color(0,175,210));
		lblX.setFont(new Font("Antipasto", Font.BOLD, 15));
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
				lbl.setForeground(new Color(100,100,100));
			else
				lbl.setForeground(new Color(0,175,210));
			lbl.setFont(new Font("Comfortaa", Font.PLAIN, 13));
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
