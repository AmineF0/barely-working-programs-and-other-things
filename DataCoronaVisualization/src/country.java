import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class country extends JPanel{
	
	protected JLabel number ;
	protected JLabel flag;
	protected JLabel amount;
	
	//a block that contains a country
	country(ImageIcon image, int firstAmount , int highest){
		//initialize
		this.flag = new JLabel(image);
		this.number = new JLabel(Integer.toString(firstAmount));
		this.amount = new JLabel();
		
		//size of components
		flag.setPreferredSize(new Dimension(100,30));
		amount.setPreferredSize(new Dimension(getRelativeSize(firstAmount , highest),30));
		
		amount.setBackground(getRandomColor());
		amount.setOpaque(true);
		
		number.setAlignmentY(CENTER_ALIGNMENT);
		number.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(amount);
		this.add(number);
		this.add(flag);
		
	}
	
	//get A random color that the rectangle will use
	private Color getRandomColor() {
		Color[] color = {Color.cyan ,Color.green , Color.blue , Color.LIGHT_GRAY , Color.yellow , Color.magenta , Color.ORANGE , Color.RED , Color.pink};
		return color[(int)Math.random()*color.length];
	}

	//get the relative size that will show
	private int getRelativeSize(int Amount , int highest ) {
		return Amount*Amount*75/(highest*100);
	}
	

}
