package environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class addI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img = Toolkit.getDefaultToolkit().getImage("back.jpg");
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        this.setOpaque(true);
        this.setBackground(Color.black);
        g.drawImage(img, 0, 0, getWidth(), getHeight(),null);
        repaint();
	}
}
