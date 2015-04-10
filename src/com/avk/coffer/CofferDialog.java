package com.avk.coffer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

	public CofferDialog(String str1, String str2) {
		setBounds(0, 0, 400, 300);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(0,175,210), 1));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblX = new JLabel("X");
		contentPanel.add(lblX);
		lblX.setForeground(new Color(0,175,210));
		lblX.setFont(new Font("Antipasto", Font.BOLD, 15));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setBounds(370, 10, 20, 20);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CofferDialog.this.dispose();
			}
		});

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JLabel lbl1 = new JLabel(str1);
		lbl1.setBounds(20, 10, 360, 30);
		lbl1.setForeground(new Color(0,175,210));
		lbl1.setFont(new Font("Comfortaa", Font.PLAIN, 13));
		panel.add(lbl1);
		{
			JLabel lbl2 = new JLabel(str2);
			lbl2.setBounds(20, 40, 360, 30);
			lbl2.setForeground(new Color(100,100,100));
			lbl2.setFont(new Font("Comfortaa", Font.PLAIN, 13));
			panel.add(lbl2);
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 20, 340, 300);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setViewportBorder(null);
		
		JScrollBar vsb = scrollPane.getVerticalScrollBar();
		vsb.setUI(new CofferScrollbarUI());
		vsb.setPreferredSize(new Dimension(10,300));
		vsb.setOpaque(false);
		vsb.setUnitIncrement(10);
		
		contentPanel.add(scrollPane);
	}
}
