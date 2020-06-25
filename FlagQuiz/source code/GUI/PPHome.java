package GUI;

import javax.swing.*;
import java.awt.*;

public class PPHome extends JPanel{

    JLabel text; //the shown text of introduction
    PBOptions startButton;

    //extends PPanel and stands for Personalised Panel Home , The home of home game
    PPHome(){
        text = new JLabel("<html><center>" +
                "<br>FLAG QUIZ GAME<br>" +
                "<h3>MADE BY : AmineF0</h3><br>" +
                "</center>" +
                "<h4>Score System: <br>" +
                "- right +6<br>" +
                "- wrong -3<br>"+
                "- eliminate -5<br>" +
                "- skip -10</h4>" +
                "</html>"); //html formatted text
        startButton = new PBTrueOp("START");// add a PBtrue

        text.setFont(new Font("Arial", Font.BOLD, 45));
        text.setHorizontalAlignment(JLabel.CENTER);

        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(text , BorderLayout.CENTER);
        add(startButton , BorderLayout.SOUTH);
    }

}