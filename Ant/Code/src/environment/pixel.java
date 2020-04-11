package environment;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class pixel extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean clas=false;
	private int id;
	
	public pixel(int pos)
	{
		id=pos;
		this.setBackground(Color.lightGray);
		this.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				clas=!clas;
				if(clas) {setBackground(Color.ORANGE);}
				else {setBackground(Color.lightGray);}
			}
		});
	}
	public boolean getClas() {return clas;}
	public int getPos() {return id;}
}
