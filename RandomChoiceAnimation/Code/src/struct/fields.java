package struct;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class fields {
	
	private int id ;
	private int state ;
	private Label label;
	private String name;
	
	public fields(int id,String name){
		setName(name);
		setLabel(new Label(name));
		this.setState(0);
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public void changeimg() {
		if(state==0) {changeIMG(images.btn.getImg());}
		if(state==1) {changeIMG(images.btn2.getImg());}
		if(state==2) {changeIMG(images.chosen.getImg());}
	}

	private void changeIMG(ImageIcon imageIcon) {
		label.setIcon(imageIcon);
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class Label extends JLabel {
	
	private static final long serialVersionUID = 4713185109732047253L;

	public Label(String name) {
		this.setLayout(new BorderLayout());
		JLabel text = new JLabel(name);
		this.add(text,BorderLayout.CENTER);
	}
	
}