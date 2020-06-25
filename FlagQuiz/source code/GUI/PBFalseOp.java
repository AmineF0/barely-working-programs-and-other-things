package GUI;

import Core.Start;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class PBFalseOp extends PBOptions{

    public PBFalseOp(String text) {
        super(text);
    }

    @Override
    void act() { // the action on click , change user data (score)
        Start.dataStructure.changeScore(-3); //communicate to DCS to change score
        Start.frame.changeScore(-3); //communicate to the frame to visualize the change
        setBackground(Color.red); // a visual effect to show the user that the option is wrong by turning red for a minute
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                setBackground(Color.DARK_GRAY);
            }

        };
        timer.schedule(task , 1000);
    }

    @Override
    boolean eliminate() { //eliminating the button by :
        setBackground(Color.white); //visuale effect
        setForeground(Color.DARK_GRAY);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setEnabled(false); // disable action listener and other properties
        return true; //return true means it is able to be eliminated , ths boolean is passed to be dealt with by the caller
    }

}
