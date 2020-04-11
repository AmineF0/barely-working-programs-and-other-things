package environment;

public class Point {

	private int posx,posy;
	
	public Point(int x , int y) {
		setPosx(x);
		setPosy(y);
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
}
