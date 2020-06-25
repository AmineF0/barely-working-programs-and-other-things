package GUI;

import DCS.Level;
import javax.swing.*;
import java.awt.*;

public class PFrame extends JFrame { //The core of GUI , Personalized JFrame that will contain all events

    private JLabel information; //JLabel that shows user data to the user
    private int score; //store the score for manipulation purpses
    String sentence; //a string to deal with information JLabel
    JPanel panel; //a contentPane

    public PFrame(){ //set personalised properties to PFrame and information
        setLocation(300,100);
        setSize(450,470);
        setResizable(false);
        setTitle("COUNTRY FLAG QUIZ GAME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new PPHome()); // starts with a PPHome added
        setVisible(true);

        //make up the score and level information bar
        //used to be in PPLevel but was moved for practical and access purposes
        information = new JLabel();
        information.setBackground(Color.DARK_GRAY);
        information.setVerticalTextPosition(JLabel.CENTER);
        information.setVerticalAlignment(JLabel.CENTER);
        information.setHorizontalAlignment(JLabel.CENTER);
        information.setPreferredSize(new Dimension(0,40));
        information.setOpaque(true);
        information.setForeground(Color.white);
        information.setFont(new Font("Arial", Font.BOLD, 20));
    }

    //generate a level and add in form of PPLevel and add it , if there is not add a PPGameOver
    public void generate(){
        Level lvl = new Level();//declare DCS.Level to get data of level
        if(lvl.getOptions()==null) // if DCS.Level return before finishing initialization which means no level left
            setContentPane(new PPGameOver()); // add PPgameover
        else {//level left
            panel = new JPanel(); // new contentPane
            score=lvl.getScore(); //get score
            sentence = "LEVEL  "+lvl.getLevel()+"   |   SCORE  "; //make the sentence of information
            information.setText(sentence+score); //set the text of it from the sentence
            panel.setLayout(new BorderLayout());
            panel.add(information , BorderLayout.NORTH);//add upBar north and PPLevel in the center
            panel.add(new PPLevel(lvl) , BorderLayout.CENTER); //generate a PPLevel using the data gotten from DCS
            setContentPane(panel);
        }
        revalidate();
        repaint();
    }

    public void changeScore(int n){
        score+=n; //update upBar when the score changes locally
        information.setText(sentence+score);
    }

}