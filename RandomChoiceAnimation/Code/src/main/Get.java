package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Get extends JFrame{
	
	JLabel getNum ;
	JLabel num ;
	JTextField b ;
	JButton btn  , valid;
	String[] values;
	int number;
	JPanel cop;
	
	
	Get(){
		setLayout(new FlowLayout());
		getNum = new JLabel();
		num = new JLabel("Enter a number");
		b = new JTextField(3);
		btn = new JButton("Choose");
		valid = new JButton("Finish");
		cop = new JPanel();
		cop.setLayout(new GridLayout(0,1));
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!b.getText().equals("") && b.getText().matches("[0-9]*")) {
					cop.removeAll();
					addComponent(Integer.parseInt(b.getText()));
					revalidate();
					repaint();
					pack();
				}
			}
		});

		valid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				values = new String[number];
				int n = 0;
				for(Component compo : cop.getComponents()) {
					if(compo instanceof Block) {
						values[n]=((Block) compo).getValuen();
						n++;
					}
				}
				
				new MyPanel(values);
				removeAll();
				pack();
			}
		});
		
		cop.add(num);
		cop.add(b);
		cop.add(btn);
		
		add(cop);
		
		pack();
		setVisible(true);
	}
	
	private void addComponent(int parseInt) {
		number = parseInt;
		getNum.setText("number "+parseInt+" , Choose names :");
		cop.removeAll();
		cop.add(getNum);
		for(int n = 0 ; n<parseInt ; n++) {
			cop.add(new Block(n));
		}
		cop.add(valid);
	}
	
}

class Block extends JPanel{
	
	JLabel text ;
	JTextField b ;
	
	Block(int n){
		setLayout(new BorderLayout());
		text= new JLabel(" object "+(n+1)+" :");
		b = new JTextField(6);
		add(text,BorderLayout.WEST);
		add(b,BorderLayout.EAST);
		setSize(this.getPreferredSize());
		revalidate();
		repaint();
	}
	
	public String getValuen() {
		return b.getText();
	}
	
}
