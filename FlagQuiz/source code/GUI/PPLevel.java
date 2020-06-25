package GUI;

import Core.Start;
import DCS.Level;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PPLevel extends JPanel{

    private JLabel flag;
    private PBOptions[] options;
    private PButton[] hints;
    private JPanel downBar , optionGroup , middleBar;

    PPLevel(Level level){
        flag = level.getFlag();
        this.options = level.getOptions();
        hints = new PButton[]{new PButton("Eliminate 1"), new PButton("SKIP")};

        //make up the middle bar which is the field of actions : flags + responses
        middleBar = new JPanel();
        middleBar.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.insets = new Insets(35,10,10,10);
        c.gridy = 0;
        middleBar.add(flag,c);
        c.gridy = 1;
        optionGroup = new JPanel();
        optionGroup.setLayout(new GridLayout(2,2));
        //add randomly the buttons
        //this works in with algorithm O(n)=4 because the random function does not add steps
        //because probability is always 1
        //this is possible because we don't use a random then check if it was already used
        //we use an array that contains from 0 to 3 , we take a random value in it use it then we remove it from the array
        //we repeat till the array becomes empty
        //it is certain that we will never get a value twice because in every time after choosing one it will not exist
        ArrayList<Integer> places = new ArrayList<Integer>(Arrays.asList(0 , 1 , 2 , 3 ));
        while (places.size()>0){
            int opt = (int) ((places.size()-1)*Math.random());
            optionGroup.add(options[places.get(opt)]);
            places.remove(opt);
        }
        middleBar.add(optionGroup,c);

        //make up an extra bar to help the user in case he is stack : hint bar
        addHintActionListener();
        downBar = new JPanel();
        downBar.setLayout(new GridLayout());
        downBar.add(hints[0]);
        downBar.add(hints[1]);

        //add all components
        setLayout(new BorderLayout());
        add(middleBar,BorderLayout.CENTER);
        add(downBar,BorderLayout.SOUTH);
    }

    // add action listener to hint buttons which will reduce the score in exchange for a potential help
    private void addHintActionListener(){
        hints[0].addActionListener(actionEvent -> {
            if(Start.dataStructure.getScore()>=4) //check if the score is enough
                for(int n=0 ; n < options.length ; n++) {
                    if (options[n].isEnabled() && options[n].eliminate()) {
                        //this loop is made for eliminating an option
                        //it works by surfing the array
                        //it checks if options is enabled because you can not disable what is already diabled
                        //then it checks isf the option is able to be eliminited
                        //example : only PBFalsep return true
                        //before eliminate return true it would have already done the job
                        //if eliminate retuen true of a button that was enabled it means that eliminate button has worked
                        //else it means there is nothing it can do
                        //in case it worked the score will be deduced.
                        Start.dataStructure.changeScore(-4); // change score
                        Start.frame.changeScore(-4);
                        break;
                    }
                }
            else
                scoreAlert();
        });
        hints[1].addActionListener(actionEvent -> {
            if(Start.dataStructure.getScore()>=10){ //check if the score is enough
                Start.dataStructure.changeScore(-10);
                Start.dataStructure.levelUp(); // go to next level
                Start.frame.generate();
            }
            else
                scoreAlert();
        });
    }
    // show a message that inform the user that he can not make the operation because his score is not enough
    private void scoreAlert(){
        JFrame alert = new JFrame();
        alert.setSize(260,90);
        alert.setLocation(400,300);
        alert.setTitle("ALERT");
        alert.add(new JLabel("Score insufficient to do the operation !"));
        alert.setVisible(true);
        alert.pack();
    }

}