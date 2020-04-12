package partone;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class panel extends JPanel{
	
int x=0,y=0,color=1;
private int sposx=snake.snake0;
private int sposy=snake.snake0;
private int posx=food.searchNumber();
private int posy=food.searchNumber();
private int score=0;
private boolean check=false;
private ArrayList snakex=new ArrayList();
private ArrayList snakey=new ArrayList();
private int snakelong=snake.getLonge();
private static boolean pause;
private int checkpause;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        this.setOpaque(true);
        this.setBackground(Color.black);
		Font font = new Font("Verdana", Font.BOLD, 40);
		g.setFont(font);
		do {
		do {
		color = traiterpos(x,y);
		g.setColor(Color.white);
		if(color==2) {	g.setColor(Color.GRAY);}
		if(color==3) {  g.setColor(Color.ORANGE);}
		if(color==4) {  g.setColor(Color.lightGray);}
		g.fillRect(5+15*x,5+ 15*y , home.longueur, home.largeur);
		x++;
		}while(x<=30);
		x=0;
		y++;
		}while(y<=30);
		y=0;
		g.drawString("SCORE      "+score, 6*home.longueur,36*home.largeur);
		if(check) {addSnake();}else {refreshSnake();}
		check=false;
		if(Fenetre.pausecopie==false){
			g.setColor(Color.darkGray);
			g.fillRect(5+15*6,5+ 15*6 , 6*home.longueur, 18*home.largeur);
			g.fillRect(5+15*18,5+ 15*6 , 6*home.longueur, 18*home.largeur);
		}
	}
	
	public int traiterpos(int x , int y) 
	{
		int color=1;
		
		if(sposx==posx && sposy==posy) 
		{
			posx=food.searchNumber();
			posy=food.searchNumber();
			score++;
			check=true;
		}
		
		if(x==posx && y==posy) {color=3;}
		if(traiterSnake(x,y)) {color=4;}
		if(x==sposx && y==sposy) {color=2;}
		return color;
	}
	
	public boolean traiterSnake(int x , int y ) 
	{
		boolean res=false;
		for(int i = 0; i < snakex.size(); i++)
		{
			int z;
			z=(int) snakex.get(i);
			if(z==x) 
			{
				z=(int) snakey.get(i);
				if(z==y) 
				{
					res=true;
				}
			}
		}
		
		return res;
	}
	public void addSnake() 
	{
		snakex.add(sposx);
		snakey.add(sposy);
	}
	public void refreshSnake()
	{
		snakex.add(sposx);
		snakey.add(sposy);
		snakex.remove(0);
		snakey.remove(0);
	}
	
	public void setsposx(int n) {sposx=n;}
	public void setsposy(int n) {sposy=n;}
	public int getsposy() {return sposy;}
	public int getsposx() {return sposx;}

	public static void getpause() {pause=false;}

}
