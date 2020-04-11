package environment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
ArrayList<pixel> shape = new ArrayList<pixel>();
addI image = new addI();
button start = new button("start");
button finish = new button("create");
JPanel intro =new JPanel(){
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g)
	{
        Font font = new Font("Arial",1,45);
        g.setColor(Color.CYAN);
        g.setFont(font);
        g.drawString("Welcome To", 70, 120);
        g.drawString("ANT WORLD", 60, 200);
        Font font1 = new Font("Arial",1,15);
        g.setFont(font1);
        g.drawString("press the button to start", 100, 300);
    }
};
JPanel create = new JPanel();
	public Fenetre()
	{
		this.setTitle("ANT GAME");
		this.setSize(400, 400);
		this.setLocation(50,50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("back.jpg"));
		add(background);
		background.setLayout(new BorderLayout());
		background.add(intro,BorderLayout.CENTER);
	    background.add(start,BorderLayout.SOUTH);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				background.removeAll();
				Janel(create);
				background.add(create,BorderLayout.CENTER);
				background.add(finish,BorderLayout.SOUTH);
				finish.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						traiter();
					}	
				});
				background.validate();
			}	
		});
		this.setVisible(true);
	}
	public void Janel(JPanel c) {
		c.setLayout(new GridLayout(15,15));
         for(int i=0;i<15*15;i++) {
        	 shape.add(new pixel(i));
        	 c.add(shape.get(i));
         }
	}
	public void traiter() {
		ArrayList<Integer> rps= new ArrayList<Integer>();
		for(int i=0;i<15*15;i++) {
			if(shape.get(i).getClas()) {rps.add(shape.get(i).getPos());}
		}
		Thread run = new Thread() {
			public void run() {
				@SuppressWarnings("unused")
				Process ps = new Process(rps);
			}
		};
		run.start();
	}
}
