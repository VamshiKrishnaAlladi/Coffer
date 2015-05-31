package com.avk.coffer;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CofferPasswordField extends JPanel {

	private JPasswordField passwordField;
	private JLabel passwordFieldImg;
	private JLabel exclaimRed;
	private String placeHolder = "";

	public CofferPasswordField(String placeHolder, String password) {
		setOpaque(false);
		setBackground(null);
		setLayout(null);
		setPreferredSize(new Dimension(360,40));
		
		this.placeHolder = placeHolder;
		
		passwordField = new JPasswordField((password != null)? password : this.placeHolder);
		passwordField.setBorder(null);
		passwordField.setOpaque(false);
		passwordField.setCaretColor(CofferReferences.CofferDarkGrey);
		passwordField.setForeground((password != null)? CofferReferences.CofferDarkGrey : CofferReferences.CofferLightGrey);
		passwordField.setBackground(null);
		passwordField.setEchoChar((char)0);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setFont(CofferReferences.Comfortaa_Plain_14);
		passwordField.setBounds(20, 5, 280, 30);
		passwordField.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(passwordField.getText().equals(CofferPasswordField.this.placeHolder)){
					passwordField.setForeground(CofferReferences.CofferDarkGrey);
					passwordField.setEchoChar('#');
					passwordField.setText("");
				}
			}
			
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if(passwordField.getText().equals("")){
					passwordField.setEchoChar((char) 0);
					passwordField.setForeground(CofferReferences.CofferLightGrey);
					passwordField.setText(CofferPasswordField.this.placeHolder);
				}
			}
			
		});
		add(passwordField);
		
		passwordFieldImg = new JLabel(CofferReferences.TEXTFIELD_IMG);
		passwordFieldImg.setSize(320, 40);
		add(passwordFieldImg);
		
		exclaimRed = new JLabel(CofferReferences.EXCLAIM_RED);
		exclaimRed.setBounds(320,0,40,40);
		exclaimRed.setVisible(false);
		add(exclaimRed);

	}
	
	public void setExclaim(boolean flag){
		exclaimRed.setVisible(flag);
	}
	
	@SuppressWarnings("deprecation")
	public String getText(){
		return passwordField.getText();
	}
	
	public void setText(String text){
		passwordField.setText(text);
	}
	
	public void addMouseListener(MouseListener ml){
		passwordField.addMouseListener(ml);
	}
	
	public void addExclaimMouseListener(MouseListener l){
		exclaimRed.addMouseListener(l);
	}
	
	public void grabFocus(){
		passwordField.grabFocus();
	}

}
