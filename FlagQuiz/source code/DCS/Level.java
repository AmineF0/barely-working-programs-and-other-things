package DCS;

import Core.Start;
import GUI.PBFalseOp;
import GUI.PBOptions;
import GUI.PBTrueOp;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level {

    private final int flagWidth = 230, flagHeight = 145; //flag dimensions
    private PBOptions[] options;
    private JLabel flag;
    private int score = Start.dataStructure.getScore() , level = Start.dataStructure.getLevel();

    public Level(){
        String[] opt = Start.dataStructure.getOptions();
        if(opt == null) return; //in case opt is null , the initialization stops and return to JFrame to proceed it
        options = new PBOptions[]{
                new PBTrueOp(opt[0]),
                new PBFalseOp(opt[1]),
                new PBFalseOp(opt[2]),
                new PBFalseOp(opt[3])
        };
        flag = importFlag(opt[4]);
    }

    //import flag from location
    private JLabel importFlag(String name) {
        JLabel label = new JLabel();
        try {
            BufferedImage img = Start.dataStructure.getFlag(name);
            label.setIcon(new ImageIcon(img.getScaledInstance( flagWidth, flagHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return label;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public PBOptions[] getOptions() {
        return options;
    }

    public JLabel getFlag() {
        return flag;
    }
}
