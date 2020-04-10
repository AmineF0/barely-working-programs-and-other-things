package design;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;

import javax.swing.*;

import front.Menu;
import front.home;

public class Frames {
	
	public static Info info = new Info("hi...");
	
	
	public static JFrame setHome() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		Menu mb = new Menu();
		frame.setJMenuBar(mb);
		JPanel panel =Panels.setPanel();
		//panel.setLayout(new BorderLayout());
		frame.setLocation(200,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(750, 520));
		frame.add(panel,BorderLayout.CENTER);
		frame.add(info,BorderLayout.SOUTH);
		panel.setBackground(Color.lightGray);
		frame.setVisible(true);
		frame.pack();
		return frame;
	}

	public static String askName() {
		String name=JOptionPane.showInputDialog(null,"Choose a name for your project :"); 
		
		if(name == null) {
			return null;      
		}
		else if(name.equals("")) {
			alert("unsupported","unsupported file name");
			return askName();
		}
		else if(TST.Files.exist(home.special.getReprisatory()+ File.separator+name)) {
			alert("dublicate",
						"<html>file name already exists"
						+ "<html><h5>hint : if you are persistant to choose this name for the file you can change repisatory</h5></html>");
			return askName();
		}
		else {
			return name;
		}
	}	


	public static void alert(String string, String string2) {
		JOptionPane.showMessageDialog(null,
			    string2,
			    string,
			    JOptionPane.ERROR_MESSAGE);	
	}

	public static boolean askYesNo(String title , String text) {
		boolean yes=true; 
		int n =JOptionPane.showConfirmDialog(
				 null, 
				 text,
				 title,
				 JOptionPane.YES_NO_OPTION);
		if(n==1) yes=false;
		return yes;
	}

}

class Info extends JLabel{
	
	private static final long serialVersionUID = 1L;
	public Info(String s){
		super(s,SwingConstants.LEFT);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(0,18));
	    this.setForeground(Color.GRAY);
	  }

		public void paintComponent(Graphics g) {
		       super.paintComponent(g);
		       int height = getHeight()+10;
		       int shadowGap = this.shadowGap;
		       Graphics2D graphics = (Graphics2D) g;
		       graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		       RenderingHints.VALUE_ANTIALIAS_ON);
		       graphics.setColor(getForeground());
		       graphics.drawRoundRect(-6, 2, this.getWidth()-50,
		       height - shadowGap, arcs.width, arcs.height);
		       graphics.setStroke(new BasicStroke());
		}
		
		
	    protected int strokeSize = 1;
	    protected Dimension arcs = new Dimension(15, 30);
	    protected int shadowGap = 1;
		
	
}