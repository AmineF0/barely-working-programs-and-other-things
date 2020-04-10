package design;

import java.awt.*;
import javax.swing.*;

public class ShowPanel extends JPanel{
	
	JPanel showScene;
	JPanel funct;
	Show scene;
	boolean started= false;
	
	ShowPanel(){
		
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

		showScene = new JPanel();
		funct = new JPanel();

		JLabel n = new JLabel("<html>SHOW<br>PANEL</html>");
		n.setFont(new Font("Arial",Font.ITALIC,40));
		n.setForeground(Color.lightGray);

		showScene.setBackground(Color.darkGray);
		showScene.setBorder(BorderFactory.createEtchedBorder());
		showScene.setLayout(new BorderLayout());
		showScene.setOpaque(true);
		showScene.add(n,BorderLayout.CENTER);
		showScene.revalidate();
		add(Box.createRigidArea(new Dimension(0,20)));
		add(showScene);

		for(Btn btn : Btn.values()) {
			funct.add(btn.buton);
		}

		add(Box.createRigidArea(new Dimension(0,15)));

		funct.setOpaque(false);
		add(funct);
		
		setOpaque(false);
		revalidate();
	}

}