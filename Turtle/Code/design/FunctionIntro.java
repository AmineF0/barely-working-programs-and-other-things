package design;

import TST.Code;

import javax.swing.*;
import java.awt.*;

public class FunctionIntro extends JFrame {

    JLabel code = new label("CODE");
    JLabel type = new label("TYPE");
    JLabel description = new label("DESCRIPTION");

    public FunctionIntro(){
        setLayout(new GridLayout(Code.values().length+1, 3));

        add(code);
        add(type);
        add(description);

        for(Code code : Code.values()){
            this.add(new label(code.name()));
            this.add(new label(code.getType()));
            this.add(new label(("<html>"+code.getExplanation()+"<html>")));
        }
        Dimension dim = getToolkit().getScreenSize();
        setSize(new Dimension(3*350,Code.values().length*60));
        setLocation((dim.width-getWidth())/2,(dim.height-getHeight())/2);
        setVisible(true);
    }

}

class label extends JLabel{

    label(String text){
        super(text);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

}