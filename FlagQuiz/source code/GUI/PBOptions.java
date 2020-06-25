package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PBOptions extends PButton implements ActionListener {

    public PBOptions(String text){
        super(text);//visual properties and relative font to the size fo the text
        if(text.length()>=10)
            setFont(new Font("Arial", Font.BOLD, 16));
        if(text.length()>=15)
            setFont(new Font("Arial", Font.BOLD, 13));
        if(text.length()>=20)
            setFont(new Font("Arial", Font.BOLD, 10));
        if(text.length()>=25)
            setFont(new Font("Arial", Font.BOLD, 9));
        setMinimumSize(new Dimension(200,50));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        act();
    }

    abstract void act(); //abstract function that represents the action done when the button is clicked

    abstract boolean eliminate();// return a boolean that shows in the PBOption is able to be eliminated

}
