package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CofferPasswordField extends JPanel {

	private JPasswordField passwordField;
	private JLabel passwordToggleImg;
	
	private String placeHolder = "";
	private int borderThickness = 2;
	private int borderRoundness = 5;

	public CofferPasswordField(String placeHolder, String password) {
		setOpaque(false);
		setBackground(null);
		setLayout(null);
		setPreferredSize(new Dimension(320,40));
		setBorder(new CofferRoundBorder(CofferReferences.CofferBlue, borderThickness, borderRoundness));

		
		this.placeHolder = placeHolder;
		
		passwordField = new JPasswordField((password != null)? password : this.placeHolder);
		passwordField.setBorder(null);
		passwordField.setOpaque(false);
		passwordField.setCaretColor(CofferReferences.CofferVeryLightGrey);
		passwordField.setForeground((password != null)? Color.WHITE : CofferReferences.CofferVeryLightGrey);
		passwordField.setBackground(null);
		passwordField.setEchoChar((password == null)? (char)0 : '#');
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setFont(CofferReferences.Comfortaa_Plain_14);
		passwordField.setBounds(20, 5, 280, 30);
		passwordField.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(passwordField.getText().equals(CofferPasswordField.this.placeHolder)){
					passwordField.setForeground(Color.WHITE);
					passwordField.setEchoChar('#');
					passwordField.setText("");
				}
			}
			
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if(passwordField.getText().equals("")){
					passwordField.setEchoChar((char) 0);
					passwordField.setForeground(CofferReferences.CofferVeryLightGrey);
					passwordField.setText(CofferPasswordField.this.placeHolder);
				}
			}
			
		});
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(passwordField.getPassword().length <= 0)
					passwordToggleImg.setVisible(false);
				else
					passwordToggleImg.setVisible(true);
			}

		});
		
		passwordToggleImg = new JLabel(CofferReferences.PASSWORD_TOGGLE_IMG);
		passwordToggleImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { passwordField.setEchoChar((char)0); }
			@Override
			public void mouseReleased(MouseEvent e) { passwordField.setEchoChar('#'); }
		});
		passwordToggleImg.setBounds(265, 5, 45, 30);
		passwordToggleImg.setVisible((password != null)? true : false);
		add(passwordToggleImg);
		add(passwordField);
		
	}
	
	public void setPlaceHolder(String placeholder){ this.placeHolder = placeholder;}
	
	public String getPlaceHolder(){	return placeHolder; }

	public void setValid(boolean flag){	setBorder(new CofferRoundBorder(flag ? CofferReferences.CofferBlue : CofferReferences.CofferRed, borderThickness, borderRoundness)); }
	
	public void setBorderThickness(int thickness){ this.borderThickness = thickness; }
	
	public int getBorderThickness(){ return borderThickness; }

	public int getBorderRoundness() { return borderRoundness; }

	public void setBorderRoundness(int borderRoundness) { this.borderRoundness = borderRoundness; }
	
	@Override
	public void addMouseListener(MouseListener ml){super.addMouseListener(ml); passwordField.addMouseListener(ml);}
	
	@SuppressWarnings("deprecation")
	public String getText(){
		String temp = passwordField.getText();
		if(temp.equals(this.placeHolder))
			return "";
		return temp;
	}
	
	public void setText(String text){ passwordField.setText(text); }
	
	public void grabFocus(){ passwordField.grabFocus(); }

}