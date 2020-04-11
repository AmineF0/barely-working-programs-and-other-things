

public class Movements{

	final static int gravity=-1;
	private static boolean game=false;
	private static int mov=1,up=-5,count=0,down=2;
	
	public static void runt() 
	{
		while(true) {
			System.out.print("");
		while(game) {
			int x=Game_play.getPosX();
			if(mov==up ) {count++;}
			if(count==10) {count=0;mov=1;}
			if(x<10) {mov=1;}
			if(x>240) {mov=0;}
				try {
				Thread.sleep(7);
				x=Game_play.getPosX()+mov;
				Game_play.setPosX(x);
				Game_play.moveObs();
				} 
				catch (InterruptedException e) {e.printStackTrace();}
		}}
	}
	
	public static void setGame(boolean r) 
	{
		game=r;
	}
	public static boolean getGame() 
	{
		return game;
	}

	public static void setmovup() 
	{
		mov=up;
	} 
	public static void setmovN() 
	{
		mov=1;
	} 
	public static void setmovdown() 
	{
		mov=down;
	} 
}