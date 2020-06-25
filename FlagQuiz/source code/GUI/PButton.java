package GUI;

import javax.swing.*;
import java.awt.*;

public class PButton extends JButton{

    PButton(String text){
        super(text);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 21));
    }

}
