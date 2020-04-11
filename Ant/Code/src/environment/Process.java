package environment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Process extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Playground playground = new Playground();

	private static ArrayList<Point> store = new ArrayList<Point>();
	private static int decision=0;
	boolean box_in=false;
	ArrayList<Integer> target = new ArrayList<Integer>();
	boolean game=true;
	int speed=100;
	JLabel spd = new JLabel("Speed :");
	JButton fast= new JButton("Fast");
	JButton medium= new JButton("Medium");
	JButton slow= new JButton("Slow");
	

	public Process(ArrayList<Integer> rps) {
		super();
		trait(rps);
		this.setSize(607,410);
		this.setLocation(500,80);
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		this.add(playground, BorderLayout.CENTER);
		fast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				speed=50;
			}
		});
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				speed=100;
			}
		});
		slow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				speed=200;
			}
		});
		JPanel btn = new JPanel();
		btn.add(spd);
		btn.add(fast);
		btn.add(medium);
		btn.add(slow);
		this.add(btn,BorderLayout.SOUTH);
		this.setResizable(false);
		this.setVisible(true);
		decide();
	}
	
	private void decide() {
		road();
		do {
			try {
				playground.repaint();
				Thread.sleep(speed);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
			setDecision(choose(getDecision()));
			playground.repaint();
		}while(game);
		
	}
	
	private void road() {
		int posX=playground.posX,posY=playground.posY;
		int x=0,y=0;
		if(!box_in) {
			if(!(posX>=40 && posX<=80))  	x=posX-60;
			if(!(posY>=160 && posY<=200)) 	y=posY-180;
		}
		if(box_in) {
			x=posX-20*store.get(0).getPosy()-260;
			y=posY-20*store.get(0).getPosx()-40;
		}
		x=x/20;
		y=y/20;
		if(x>0) target.add(2);
		if(x<0) target.add(3);
		for(int i=0;i<java.lang.Math.abs(x);i++) target.add(0);
		if(y>0) target.add(1);
		if(y<0) target.add(4);
		for(int i=0;i<java.lang.Math.abs(y);i++) target.add(0);
	}

	private int choose(int decision2) {
		if(target.isEmpty() && !store.isEmpty()) {
			if(box_in) {playground.done.add(store.get(0));store.remove(0);}
			box_in=!box_in;road();}
		if(store.isEmpty()) {game=false;}
		decision2=target.get(0);
		target.remove(0);
		return decision2;
	}
	
	private void trait(ArrayList<Integer> rps) {
		for(int i=0;i<rps.size();i++) {
			getStore().add(new Point(rps.get(i)/15,rps.get(i)%15));
		}
	}
	
	public static int getDecision() {
		return decision;
	}

	public void setDecision(int decision) {
		Process.decision = decision;
	}

	public static ArrayList<Point> getStore() {
		return store;
	}

	public static void setStore(ArrayList<Point> store) {
		Process.store = store;
	}
}