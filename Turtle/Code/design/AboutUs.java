package design;
import java.awt.*;
import javax.swing.*;

public class AboutUs extends JFrame{

    public AboutUs(){
    	setLayout(new BorderLayout());
    	JLabel text = new JLabel("<html>This program was made by Amine Firdawsi<br>github : AmineF0</html>");
    	text.setHorizontalTextPosition(0);
    	add(text, BorderLayout.CENTER);
    	setSize(200,200);
    	setLocation(200,200);
    	setVisible(true);
    }
}
