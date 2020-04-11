import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Game_play extends JPanel{

	static Image img = Toolkit.getDefaultToolkit().createImage("bg.jpg");;
	private static final long serialVersionUID = 1L;
	private static int posX=120;
	static Obs obs1= new Obs(450);
	static Obs obs2= new Obs(600);
	static Obs obs3= new Obs(750);
	int counte=0;
	public static boolean stop=false;
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
        this.setOpaque(true);
		g.drawImage(img, 0, 10, null);
		g.setColor(Color.cyan);
		g.fillOval(100,posX, 18, 20);
		g.setColor(Color.orange);
		if(obs1.getPosY()<109 && obs1.getPosY()>91){if((posX-10)<=obs1.getPart() || (posX+10)>=(obs1.getPart()+100)) {Game_fr.end();stop=true;}}
		if(obs2.getPosY()<109 && obs2.getPosY()>91){if((posX-10)<=obs2.getPart() || (posX+10)>=(obs2.getPart()+100)) {Game_fr.end();stop=true;}}
		if(obs3.getPosY()<109 && obs3.getPosY()>91){if((posX-10)<=obs3.getPart() || (posX+10)>=(obs3.getPart()+100)) {Game_fr.end();stop=true;}}
		g.fillRect(obs1.getPosY(), 10, obs1.lenght, obs1.getPart());
		g.fillRect(obs1.getPosY(), obs1.getPart()+100, obs1.lenght, obs1.width-obs1.getPart());
		g.fillRect(obs2.getPosY(), 10, obs2.lenght, obs2.getPart());
		g.fillRect(obs2.getPosY(), obs2.getPart()+100, obs2.lenght, obs2.width-obs2.getPart());
		g.fillRect(obs3.getPosY(), 10, obs3.lenght, obs3.getPart());
		g.fillRect(obs3.getPosY(), obs3.getPart()+100, obs3.lenght, obs3.width-obs3.getPart());
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 450, 10);
		g.fillRect(0, 260, 450, 20);
		
		if(stop) {
			g.setColor(Color.red);
			Font fontr = new Font("Arial",12,30);
			g.setFont(fontr);
			g.drawString("Game Over", 150, 130);
			g.setColor(Color.black);
			Font fontr2 = new Font("Arial",10,12);
			g.setFont(fontr2);
			g.drawString("Press space to restart !", 160, 180);
		}
		
		repaint();
	}
	
	public static int getPosX() {return posX;}
	public static void setPosX(int x) {posX=x;}
	
	public static void moveObs() {
		obs1.moveObs();
		obs2.moveObs();
		obs3.moveObs();
	}
}
