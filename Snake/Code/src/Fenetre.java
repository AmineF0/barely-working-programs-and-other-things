package partone;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;;

public class Fenetre extends JFrame implements KeyListener{

panel pan=new panel();
private int directionX=1 , directionY=0;
private int lastdirection=1;
static boolean pause=false;
static boolean pausecopie=false;

public Fenetre()
	{
		this.setTitle("SNAKE_home");
		addKeyListener(this);
	    addWindowListener(new WindowAdapter()
	    {
	      public void windowClosing(WindowEvent e)
	      {   
	        System.exit(0);    
	      }  
	    });
		this.setSize(32*home.longueur, 40*home.largeur);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0,0);
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setContentPane(pan);
		this.setVisible(true);
		move();
	}
	
	public void move() 
	{
		
		int x=pan.getsposx();
		int y=pan.getsposy();
		while(true) {System.out.print(1);
			while(pause) {System.out.print(1);
				do {
				pan.setsposx(x);
				pan.setsposy(y);
				pan.repaint();
				try {
					Thread.sleep(250);
					} catch (InterruptedException e) {
					e.printStackTrace();
					}
				x=x+directionX;
				y=y+directionY;
			}while(x<30 && y<30 && x>0 && y>0 && pause==true);
			switch(x) 
			{
				case -1 : x=30; break;
				case 31 : x=0; break;
			}
			switch(y) 
			{
				case -1 : y=30; break;
				case 31 : y=0; break;
			}}
		}
}

	@Override
	public void keyPressed(KeyEvent event) 
	{
		switch(event.getKeyCode())
		{
			case KeyEvent.VK_SPACE:
				pause=!pause;
				
				break;
			case KeyEvent.VK_UP : 
				if (lastdirection==3) {}
				else {
				pause=true;
				directionX=0;
				directionY=-1;
				lastdirection=4;}
				break;
			case KeyEvent.VK_DOWN : 
				if(lastdirection==4) {}
				else {
				pause=true;
				directionX=0;
				directionY=1;
				lastdirection=3;}
				break;
			case KeyEvent.VK_LEFT :
				if(lastdirection==1) {}
				else {
				pause=true;
				directionX=-1;
				directionY=0;
				lastdirection=2;
				}
				break;
			case KeyEvent.VK_RIGHT :
				if(lastdirection==2) {}
				else {
				pause=true;
				directionX=1;
				directionY=0;
				lastdirection=1;}
				break;
		}
		pausecopie=pause;
	}
	@Override
	public void keyReleased(KeyEvent event) {}
	@Override
	public void keyTyped(KeyEvent event) {}

}
