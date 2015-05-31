package com.avk.coffer;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CofferTextField extends JPanel {
	private JTextField textField;
	private JLabel textFieldImg;
	private JLabel exclaimRed;
	private String placeHolder = "";

	public CofferTextField(String placeHolder, String text) {
		setOpaque(false);
		setBackground(null);
		setLayout(null);
		setPreferredSize(new Dimension(360,40));
		
		this.placeHolder = placeHolder;
		
		textField = new JTextField((text != null)? text : this.placeHolder);
		textField.setBorder(null);
		textField.setOpaque(false);
		textField.setCaretColor(CofferReferences.CofferDarkGrey);
		textField.setForeground((text != null)? CofferReferences.CofferDarkGrey : CofferReferences.CofferLightGrey);
		textField.setBackground(null);
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(CofferReferences.Comfortaa_Plain_14);
		textField.setBounds(20, 5, 280, 30);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textField.getText().equals(CofferTextField.this.placeHolder)){
					textField.setForeground(CofferReferences.CofferDarkGrey);
					textField.setText("");
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textField.getText().equals("")){
					textField.setForeground(CofferReferences.CofferLightGrey);
					textField.setText(CofferTextField.this.placeHolder);
				}
			}
			
		});
		add(textField);
		
		textFieldImg = new JLabel(CofferReferences.TEXTFIELD_IMG);
		textFieldImg.setSize(320, 40);
		add(textFieldImg);
		
		exclaimRed = new JLabel(CofferReferences.EXCLAIM_RED);
		exclaimRed.setBounds(320,0,40,40);
		exclaimRed.setVisible(false);
		add(exclaimRed);

	}
	
	public void setExclaim(boolean flag){
		exclaimRed.setVisible(flag);
	}
	
	public String getText(){
		return textField.getText();
	}
	
	public void setText(String text){
		textField.setText(text);
	}
	
	public void addMouseListener(MouseListener ml){
		textField.addMouseListener(ml);
	}
	
	public void addExclaimMouseListener(MouseListener l){
		exclaimRed.addMouseListener(l);
	}
	
	public void grabFocus(){
		textField.grabFocus();
	}
}
