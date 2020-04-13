package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import struct.fields;

public class MyPanel extends JFrame{
	
	ImageIcon background = new ImageIcon("Background.jpg");
	public static fields[] field;
	JButton next = new JButton("NEXT");
	boolean process = true;
	boolean search = false;
	int counting = 1;
	Panel panel;
	String[] snt;
	String html="<html>"
			+ "<div color=white height=100px width=200px>"
			+ "<center>"
			+ "<h2>"
			+ "RESULTS:"
			+ "</h2>"
			+ "</center>";
	Labeln plae= new Labeln(html);
	JPanel pnl = new JPanel();
	Thread think = new Thread() {
		public void run() {
			while(process) {
				System.out.print("");
				if(search) {
					if(counting==field.length+1) { 
						Results.main(plae);
						return;
					}
					Random rand = new Random();
					boolean done;
					int place=0, tem=0;
					int count;
					String text = "";
					if(counting==field.length) {
						for(fields fld : field){
							if(fld.getState()==0) {
								fld.setState(1);
								fld.changeimg();
								text=fld.getName();
							}
							next.setText("show results");
						}
						snt[counting-1]=text;
					}
					else {
						for(int x=0; x<30 ;x++) {
							done=true;
							count = x*x;
							place = rand.nextInt(field.length)+1;
							while(tem==place) {place = rand.nextInt(field.length)+1;}
							while(done) {
								done=false;
								for(fields fld : field){
									if(fld.getState()==2 && fld.getId()==place) {
										place = rand.nextInt(field.length)+1;
										done=true;
									}
								}
							}
							try {
								Thread.sleep(count);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							for(fields fld : field){
								if(fld.getId()==place) {
									fld.setState(1);
									fld.changeimg();
								}
								else if(fld.getState()!=2) {
									fld.setState(0);
									fld.changeimg();
								}
							}
							tem=place;
						}
					}
					for(fields fld : field){
						if(fld.getState()==1) {
							fld.setState(2);
							fld.changeimg();
							text=fld.getName();
						}
					}
					search=false;
					snt[counting-1]=text;
					counting++;
					String html="<html>"
							+ "<div color=white height=100px width=200px>"
							+ "<center>"
							+ "<h2>"
							+ "RESULTS:"
							+ "</h2>"
							+ "</center>";
					for(int n =0; n < snt.length ; n++) {
						html+="<br>"+(n+1)+"-";
						if(!(snt[n]==null))html+=snt[n];
					}
					html+= "</div>"
					+ "</html>"; 
					plae.settText(html);
				}
			}
		}	
	};   
	
	MyPanel(String[] values){
		
		pnl.setLayout(new BorderLayout());
		
		snt = new String[values.length];
		setLayout(new BorderLayout());
		
		field = new fields[values.length];
		for(int n=0 ; n<field.length ;n++ ) {
			field[n] = new fields(n+1,values[n]);
			html+="<br>"+(n+1)+"-";
		}
		html+=( "</div>"
		+ "</html>"); 
		plae.settText(html);
		
		setB();
		panel = new Panel();
		
		pnl.add(panel, BorderLayout.WEST);
		pnl.add(next,BorderLayout.SOUTH);
		pnl.add(plae,BorderLayout.EAST);
		
		setContentPane(pnl);
		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dim.width-getSize().width)/2,(dim.height-getSize().height)/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);	
		setVisible(true);
		
		think.start();
	}
	
	private void setB() {
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search=true;
			}
		});	
	}
}

class Labeln extends JLabel {

	private static final long serialVersionUID = 1L;
	private JLabel text = new JLabel();
	
	public Labeln(String html) {
		this.setIcon(new ImageIcon("background.jpg"));
		this.setLayout(new BorderLayout());
		settText(html);
		this.add(text,BorderLayout.EAST);
		setMaximumSize(new Dimension(300, 300));
	}
	
	public JLabel gettText() {
		return text;
	}

	public void settText(String text) {
		this.text.setText(text);
	}
	
}