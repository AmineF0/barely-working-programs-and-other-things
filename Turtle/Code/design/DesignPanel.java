package design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import TST.Code;

public class DesignPanel extends JPanel{

	JPanel stock;
	public static JPanel process;
	JScrollPane jspS,jspP;
	static ArrayList<ArrayList<added>> proc = new ArrayList<ArrayList<added>>();
	
	DesignPanel(){
		stock = new JPanel();
		process= new JPanel();
		process.setLayout(null);
		setLayout(null);
		stock.setLayout(new BoxLayout(stock,BoxLayout.PAGE_AXIS));
		setOpaque(false);
		
		for(TST.Code code : TST.Code.values()) {
			stock.add(Box.createRigidArea(new Dimension(0,10)));
			stock.add(new add(code));
		}
		
		jspS=scrollpane(new JScrollPane(stock));
		jspP=scrollpane(new JScrollPane(process));
		jspS.setBounds(2,9,135,380);
		jspP.setBounds(140,9,220,380);
		add(jspS);
		add(jspP);


		JFrame fr = new JFrame();
		fr.setMinimumSize(new Dimension(400,400));
		fr.add(this);
		fr.setVisible(true);
		fr.pack();
	}



    public JScrollPane scrollpane(JScrollPane jsp) {
	    jsp.getBackground();
		jsp.setOpaque(false);
		jsp.setBorder(null);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//jsp.getViewport().setOpaque(false);
		return jsp;
	}
	
	public static void adds(added e) {
		proc.add(new ArrayList<added>());
		proc.get(proc.size()-1).add(e);
		process.add(e.container);
		e.container.revalidate();
		e.revalidate();
		process.revalidate();
		process.repaint();
	}

}

class Obj extends JPanel{
	
	JTextField tfi;
	Code code;
	
	Obj(TST.Code code){
		this.code=code;
		JLabel name = new JLabel(code.name());
		add(name);
		if(code.getType().equals("int")) {
			tfi=new JTextField(1);
			tfi.setBorder(BorderFactory.createLoweredBevelBorder());
			tfi.setOpaque(false);
			tfi.setFocusable(false);
			add(tfi);
		}
		setOpaque(false);
		setBackground(Color.LIGHT_GRAY);
		setSize(new Dimension(getPreferredSize().getSize().width+15,getPreferredSize().getSize().height+5));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		setMaximumSize(new Dimension(getPreferredSize().getSize().width+15,getPreferredSize().getSize().height));
	}

	
}

class add extends Obj implements MouseListener{
	

	add(Code code) {
		super(code);
		if(code.getType().equals("int"))
			this.tfi.setEditable(false);
		this.addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent arg0) {
		DesignPanel.adds(new added(this.code));
		DesignPanel.process.revalidate();
		DesignPanel.process.repaint();
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
}

class added extends Obj implements MouseMotionListener{
	
	 JPanel container=new JPanel();

	added(Code code) {
		super(code);
		container.setOpaque(false);
		container.setBackground(Color.blue);
		container.addMouseMotionListener(this);
		if(code.getType().equals("int")) {
		this.tfi.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
				if(Character.isDigit(arg0.getKeyChar()) 
						|| arg0.getKeyCode()==KeyEvent.VK_BACK_SPACE
						|| arg0.getKeyCode()==KeyEvent.VK_LEFT
						|| arg0.getKeyCode()==KeyEvent.VK_RIGHT) 
					tfi.setEditable(true);
				else
					tfi.setEditable(false);
			}

			public void keyReleased(KeyEvent arg0) {}

			public void keyTyped(KeyEvent arg0) {}
			
		});}
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		container.add(this);
		container.setSize(container.getPreferredSize());
		container.revalidate();
		container.repaint();
	}

	public void mouseDragged(MouseEvent arg0) {
		container.setLocation(container.getLocation().x+arg0.getX(),container.getLocation().y+arg0.getY());
		((JPanel)DesignPanel.process.getComponentAt(container.getLocation().x-1,container.getLocation().y-1)).add(container);
		getLocationArray();
		/**for(int n=0;n<DesignPanel.proc.size()-1;n++) {
			for(int y=0;y<DesignPanel.proc.get(n).size()-1;y++) {
				if(DesignPanel.proc.get(n).get(y) instanceof added) {
					DesignPanel.proc.get(n).get(y).container.setSize(DesignPanel.proc.get(n).get(y).container.getMaximumSize());
					DesignPanel.proc.get(n).get(y).container.revalidate();}
			}
		}*/
		DesignPanel.process.revalidate();

	}

	public static void getLocationArray() {
		DesignPanel.proc.removeAll(DesignPanel.proc);
		for(Component compo : DesignPanel.process.getComponents()) {
			DesignPanel.proc.add(new ArrayList<added>());
			if(compo instanceof JPanel) {
					addAr(compo);
			}
		}
	}

	private static void addAr(Component compo) {
		for(Component compo1 : ((JPanel)compo).getComponents()) {
		if(compo1 instanceof added) {
			DesignPanel.proc.get(DesignPanel.proc.size()-1).add((added) compo1);
		}
		else if (compo1 instanceof JPanel){
			addAr(compo1);
			}
		else return;
		}
		
	}

	public void mouseMoved(MouseEvent arg0) {}
	
}

class Decision {

	ArrayList<String[]> action;

	Decision(){
		action = new ArrayList<>();



	}

}