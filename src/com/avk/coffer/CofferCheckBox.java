package com.avk.coffer;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CofferCheckBox extends JLabel {

	private boolean checked = false;
	private String text = "";
	private ImageIcon uncheckedImg = new ImageIcon(this.getClass().getResource("/checkBox.png")); 
	private ImageIcon checkedImg = new ImageIcon(this.getClass().getResource("/checkedBox.png"));

	public CofferCheckBox() {
		super();
		createUI(false, "");
	}
	
	public CofferCheckBox(boolean check, String text){
		super();
		this.text = text;
		this.checked = check;
		createUI(check,text);
	}
	
	private void createUI( boolean checked, String text ){
		if(checked){ setIcon(checkedImg); }else{ setIcon(uncheckedImg);}
		this.setText(text);
		setForeground(new Color(0, 175, 210));
		setFont(CofferRef.Comfortaa_Plain_15);
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				toggleCheck();
			}
		});		
	}
	
	public void setText(String text){
		this.text = text;
		super.setText(text);
	}
	
	public String getText(){ return text; }
	
	public void toggleCheck(){
		if(checked)
		{
			setIcon(uncheckedImg);
			checked = false;
		}
		else
		{
			setIcon(checkedImg);
			checked = true;
		}
	}
	
	public boolean isChecked(){	return checked;	}
}
