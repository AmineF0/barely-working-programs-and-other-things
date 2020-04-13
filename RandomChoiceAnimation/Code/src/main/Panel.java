package main;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

import struct.fields;

public class Panel extends JPanel{
	

	private static final long serialVersionUID = 1L;
	Image background = Toolkit.getDefaultToolkit().createImage("Background.jpg");
	
	public Panel() {
		setLayout(new GridLayout((int) Math.pow(MyPanel.field.length, 0.5),0));
		for(fields fld : MyPanel.field) {
			fld.changeimg();
			add(fld.getLabel());
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
	}
}