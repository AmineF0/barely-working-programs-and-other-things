package mainPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class country extends JPanel{
	
	protected int value;
	protected String name;//name of the country
	protected JLabel number;// number to show
	protected JLabel flag;// the flag
	protected JLabel amount;// the rectangle showing data
	protected ImageIcon image;// image of the flag
	protected int lastValue , transitionValue;// needed data while processing
	
	//a block that contains a country
	country(int firstAmount , int highest , String name , int dim){
		
		//initialize
		this.name=name;
		this.value=firstAmount;
		this.flag = new JLabel(name);
		this.number = new JLabel(Integer.toString(firstAmount));
		this.amount = new JLabel("");
		
		//set panel properties
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//set flag properties
		flag.setPreferredSize(new Dimension(55,35));
		importFlag();
		
		//set rectangle properties
		amount.setPreferredSize(new Dimension(getRelativeSize(firstAmount , highest , dim),35));
		amount.setBackground(getRandomColor());
		amount.setOpaque(true);
		
		//add components
		this.add(amount);
		this.add(number);
		this.add(flag);
		//refresh
		revalidate();
		repaint();
		//setDimensions
		setPreferredSize(new Dimension(dim,45));
		setMaximumSize(new Dimension(dim,45));
	}
	
	//get A random color that the rectangle will use
	private Color getRandomColor() {
		Color[] color = {Color.cyan ,Color.green , Color.blue , Color.LIGHT_GRAY , Color.yellow , Color.magenta , Color.ORANGE , Color.RED , Color.pink};
		return color[(int)(Math.random()*color.length)];
	}

	//get the relative size that will show
	private int getRelativeSize(int Amount , int highest , int dim) {
		//refresh dimensions
		setMaximumSize(new Dimension(dim,45));
		setPreferredSize(new Dimension(dim,45));
		//at first to eliminate logic error while highest = 0
		if(highest==0)
			return 1;
		//find the average value
		return (int)((long)dim*Amount*72/(highest*100)+1);
	}
	
	//import flag from the file
	private void importFlag() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(
					//make the import cross platform
					new File(new File("").getAbsolutePath()+
							File.separatorChar+"data"+
							File.separatorChar+"flags"+
							File.separatorChar+name+".png"));
			Image rimage  = img.getScaledInstance( flag.getPreferredSize().width, flag.getPreferredSize().height, Image.SCALE_SMOOTH);
			flag.setIcon(new ImageIcon(rimage));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//update the panel and refresh it
	public void update(int highest,int dim) {
		//change the number
		number.setText(Integer.toString(transitionValue));
		//change dimension of the rectangle
		amount.setPreferredSize(new Dimension(getRelativeSize(transitionValue , highest , dim),35));
		amount.setMinimumSize(new Dimension(getRelativeSize(transitionValue , highest , dim),35));
		amount.setMaximumSize(new Dimension(getRelativeSize(transitionValue , highest , dim),35));
		//refresh everything
		revalidate();
		repaint();
	}
	
	//get the name of the country inthis block
	public String getNameCountry() {
		return this.name;
	}
}
