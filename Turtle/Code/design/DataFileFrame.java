package design;

import TST.Code;
import front.home;

import javax.swing.*;
import java.awt.*;

public class DataFileFrame extends JFrame{
    JLabel dataType = new label("Data Type");
    JLabel data = new label("Data Value");
    String[][] dataProcessed ;

    public DataFileFrame(){

        dataProcessed = home.special.getDataProcessed();

        setLayout(new GridLayout(dataProcessed[0].length+1, 3 ));
        add(dataType);
        add(data);

        for(int n = 0 ; n < dataProcessed[0].length ; n++){
            this.add(new label(dataProcessed[0][n]));
            this.add(new label(dataProcessed[1][n]));
        }

        Dimension dim = getToolkit().getScreenSize();
        setSize(new Dimension(3*350,dataProcessed[0].length*60));
        setLocation((dim.width-getWidth())/2,(dim.height-getHeight())/2);
        setVisible(true);
    }

}