import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class frame extends JFrame{
	
	int highest=100;
	
	frame(){
		
		//set properties of the frame
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		//this.setSize(500, 700);
		
		
		this.add(new country(new ImageIcon("IMG_20200330_142713.jpg")));
		this.add(new country(new ImageIcon("IMG_20200330_142713.jpg")));
		
		this.setVisible(true);
		pack();
	}

}
