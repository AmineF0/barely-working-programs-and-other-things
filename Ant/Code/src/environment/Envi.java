package environment;
import javax.swing.JFrame;

public class Envi extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	button start = new button("start");
	
	public Envi(){
		this.setSize(800, 400);
		this.setLocation(200, 100);
		this.setTitle("ANT");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(start);
		this.setVisible(true);
		pack();
	}
}
