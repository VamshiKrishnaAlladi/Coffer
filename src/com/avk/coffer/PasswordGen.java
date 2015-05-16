package com.avk.coffer;

import java.awt.Color;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PasswordGen extends JPanel {
	private JTextField passwordDisp;
	private CofferNumberField numChars;

	/**
	 * Create the panel.
	 */
	public PasswordGen() {
		setOpaque(false);
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Password Brewery");
		lblTitle.setFont(CofferRef.Comfortaa_Bold_Italic_20);
		lblTitle.setForeground(new Color(0,175,210));
		lblTitle.setBounds(50, 25, 240, 50);
		add(lblTitle);

		JLabel lblText1 = new JLabel("Your Password will have ");
		lblText1.setForeground(new Color(0,175,210));
		lblText1.setFont(CofferRef.Comfortaa_Plain_15);
		lblText1.setBounds(105, 99, 185, 30);
		add(lblText1);
		
		numChars = new CofferNumberField();
		numChars.setBounds(290,90,140,30);
		numChars.setNum(10);
		numChars.setMinNum(5);
		numChars.setMaxNum(50);
		add(numChars);
		
		JLabel lblText2 = new JLabel("characters and will include");
		lblText2.setForeground(new Color(0,175,210));
		lblText2.setFont(CofferRef.Comfortaa_Plain_15);
		lblText2.setBounds(430, 99, 216, 30);
		add(lblText2);		
		
		CofferCheckBox caps = new CofferCheckBox(true, "Uppercase letters");
		caps.setBounds(175,140,200,50);
		add(caps);
		
		CofferCheckBox lower = new CofferCheckBox(true, "Lowercase letters");
		lower.setBounds(375,140,200,50);
		add(lower);
		
		CofferCheckBox nums = new CofferCheckBox(true, "Digits");
		nums.setBounds(175,190,200,50);
		add(nums);
		
		CofferCheckBox syms = new CofferCheckBox(true, "Special Characters");
		syms.setBounds(375,190,200,50);
		add(syms);
		
		passwordDisp = new JTextField();
		passwordDisp.setText("Your Password will be shown here :)");
		passwordDisp.setEditable(false);
		passwordDisp.setForeground(new Color(0,175,210));
		passwordDisp.setFont(CofferRef.Comfortaa_Bold_15);
		passwordDisp.setHorizontalAlignment(SwingConstants.CENTER);
		passwordDisp.setOpaque(false);
		passwordDisp.setBorder(null);
		passwordDisp.setBounds(175, 270, 400, 30);
		add(passwordDisp);
		
		JLabel passwordBlank = new JLabel(new ImageIcon(this.getClass().getResource("/textBlank.png")));
		passwordBlank.setBounds(175, 290, 400, 10);
		add(passwordBlank);
		
		
		JLabel lblClipBoard = new JLabel(new ImageIcon(this.getClass().getResource("/clipBoard.png")));
		lblClipBoard.setBounds(585, 270, 30, 30);
		lblClipBoard.setVisible(false);
		lblClipBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CofferRef.SYS_CLIPBOARD.setContents(new StringSelection(passwordDisp.getText()), null);
				Coffer.setStatus("Password saved to clipboard.");
			}
		});
		add(lblClipBoard);

		JLabel lblGen = new JLabel("Generate");
		lblGen.setForeground(Color.WHITE);
		lblGen.setFont(CofferRef.Comfortaa_Plain_15);
		lblGen.setHorizontalAlignment(SwingConstants.CENTER);
		lblGen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGen.setBounds(275, 340, 200, 40);
		lblGen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(caps.isChecked()||lower.isChecked()||nums.isChecked()||syms.isChecked()){
					int n = numChars.getNum();
					Random random = new Random();
					String ranPass = "";
					while(ranPass.length() < n)
					{
						int ran = random.nextInt(126);
						if(ran<33) continue;
						else if((!caps.isChecked())&&Character.isUpperCase(ran)){ continue; }
						else if((!lower.isChecked())&&Character.isLowerCase(ran)){ continue; }
						else if((!nums.isChecked())&&Character.isDigit(ran)){ continue; }
						else if((!syms.isChecked())&& !Character.isLetterOrDigit(ran)){ continue; }
						ranPass += (char)ran;
					}
					passwordDisp.setText(ranPass);
					lblClipBoard.setVisible(true);
					Coffer.setStatus("Click on the icon to save the password to clipboard.");
				}
				else
				{
					Coffer.setStatus("Choose atleast one of the checkboxes.");
				}
			}
		});
		add(lblGen);
		
		JLabel genButtonImg = new JLabel(new ImageIcon(this.getClass().getResource("/button.png")));
		genButtonImg.setBounds(275, 340, 200, 40);
		add(genButtonImg);		
	}
}
