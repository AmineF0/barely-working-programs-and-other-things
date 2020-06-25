package GUI;

import Core.Start;

public class PBTrueOp extends PBOptions{
    public PBTrueOp(String text) {
        super(text);
    }

    @Override
    void act() {
        if(!getText().equals("START")) { // if the the button is not named start which means not the button in PPHome
            Start.dataStructure.changeScore(+6);// connect to DCS and call it to change score
            Start.dataStructure.levelUp();// connect to DCS and tell it to validate the level as passed and increment level
            // no need for score and level visualization because everything will refresh
        }
        Start.frame.generate(); // call the generate function in frame to tell it to pass the next stage
    }

    @Override
    boolean eliminate() {
        return false; // return false that means nothing has changed and nothing is changeable ,
        // can not eliminate the true answer
    }
}
