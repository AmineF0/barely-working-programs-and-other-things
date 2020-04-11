import java.util.Random;

public class Obs {

	final int lenght=20,width=260;
	private int posY;
	private int part;
	Random rand = new Random();
	
	public Obs(int posy){
		this.setPosY(posy);
		setPart(rand.nextInt(200));
	}
	
	public int getPosY() {
		return posY;
	}	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public void moveObs() {
		if(this.getPosY()!=-20) {
			this.setPosY(this.getPosY()-1);}
		else {this.setPosY(450);setPart(rand.nextInt(200));}
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}
}
