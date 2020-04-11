package environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Playground extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image btn=new ImageIcon("btn.jpg").getImage();
	Image back=new ImageIcon("gras01.jpg").getImage();
	Image land=new ImageIcon("land.jpg").getImage();
	Image box=new ImageIcon("box.jpg").getImage();
	BufferedImage antT;
	BufferedImage antR;
	BufferedImage antL;
	BufferedImage antD;
	BufferedImage ant;
	int posX=60;
	int posY=60;
	int countX=0,countY=0;
	ArrayList<Point> done= new ArrayList<Point>();
	
	public Playground(){
		try {
			antT = ImageIO.read(new File("ant.png"));
			ant=antT;
			AffineTransformOp op = null;
			antL=rot(90,op).filter(antT, null);
			antR=rot(-90,op).filter(antT, null);
			antD=rot(180,op).filter(antT, null);
		}catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void Move(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		switch (Process.getDecision()) {
		case 0:
			posX=posX+20*countX;
			posY=posY+20*countY;
			break;
		case 1:
			ant=antT;
			countX=0;
			countY=-1;
			break;
		case 2:
			ant=antR;
			posY=posY-20;
			countX=-1;
			countY=0;
			break;
		case 3:
			ant=antL;
			countX=1;
			countY=0;
			break;
		case 4:
			ant=antD;
			posX=posX-20;
			countX=0;
			countY=1;
			break;
		}
		g2d.drawImage(ant, posX, posY, null);
	}
	
	private AffineTransformOp rot(int rotation, AffineTransformOp op) {
		double rotationRequired = Math.toRadians (rotation);
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, 20,20);
		return op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	}	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        this.setOpaque(true);
		g.drawImage(back, 0, 0, this);
		g.drawImage(land, 260, 40, this);
		g.setColor(Color.gray);
		for(int x=0;x<30;x++) 
		{
			for(int y=0;y<19;y++)
			{
				g.drawRect(20*x, 20*y, 20, 20);
			}
		}
		g.drawImage(btn, 40, 160, this);
		g.setColor(Color.black);
		for(int i=0;i<Process.getStore().size();i++)
		{
			g.drawRect(20*(13+Process.getStore().get(i).getPosy()), 20*(2+Process.getStore().get(i).getPosx()), 20, 20);
		}
		for(int i=0;i<done.size();i++)
		{
			g.drawImage(box,20*(13+done.get(i).getPosy()), 20*(2+done.get(i).getPosx()), this);
		}
		Move(g);
	}
	
}
