package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Results {
	
	public static void main(JLabel label) {
		JFrame res = new JFrame();
		res.setLayout(new BorderLayout());
		res.setSize(300, 300);
		res.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		res.setResizable(false);	
		res.add(label,BorderLayout.CENTER);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		res.setLocation((dim.width-res.getSize().width)/2,(dim.height-res.getSize().height)/2);
		res.setVisible(true);
	} 

}
