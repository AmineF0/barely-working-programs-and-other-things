package GUI;

import Core.Start;
import DCS.Management;
import Utilities.Functions;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PPGameOver extends JPanel{

        private JLabel text;
        private PButton reStartButton;
        private PButton feedback ;

        //extends JPanel and stands for Personalised Panel Game Over , The panel shown when the game is over
        PPGameOver(){
            //text showing
            text = new JLabel("<html><center>You saw everything I have in my jar. Congrats, you defeated me!<br><br></html>");
            text.setFont(new Font("Arial", Font.BOLD, 25));
            text.setHorizontalAlignment(JLabel.CENTER);

            // button open the browser and directs to my link
            feedback = new PButton("Click Here to leave us a feedback !");
            feedback.addActionListener(actionEvent -> {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/AmineF0/barely-working-programs-and-other-things/issues"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            });

            //button that destroy user data and re assign a new value to Management and add PPHome to PFrame will restarts the game
            reStartButton = new PButton("START OVER");
            reStartButton.addActionListener(actionEvent -> {
                new File(System.getProperty("user.home")+ Functions.fileLinkCP("/.QuizGameData/temp.csv", "unix")).delete();
                new File(System.getProperty("user.home")+Functions.fileLinkCP("/.QuizGameData/dataFile", "unix")).delete();
                Start.dataStructure= new Management();
                Start.frame.setContentPane(new PPHome());
                Start.frame.revalidate();
                Start.frame.repaint();
            });

            setBackground(Color.WHITE);
            setLayout(new BorderLayout());
            add(feedback,BorderLayout.NORTH);
            add(text , BorderLayout.CENTER);
            add(reStartButton , BorderLayout.SOUTH);
        }

}