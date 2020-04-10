package design;

import TST.Files;
import front.home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Window extends JButton{

    JButton exit;
    String link ;
    ScriptPanel mine ;

    Window(String text){
        super(getNameOf(text));
        link = text;
        mine = new ScriptPanel(link);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                home.special.setLastFile(link);
                if(Panels.sc!= null){Panels.script.remove(Panels.sc);
                Panels.sc=null;}
                Panels.sc=new ScriptPanel(link);
                Panels.script.add(Panels.sc , BorderLayout.CENTER);
                Panels.fileOpen.setAllOff();
                setBorder(BorderFactory.createLoweredBevelBorder());
            }
        });
        setLayout(new BorderLayout());
        setMargin(new Insets(0,0,0,0));
        setPreferredSize(getPreferredSize());
        setFont(new Font("Arial",Font.PLAIN,11));
        setBorder(BorderFactory.createEtchedBorder());
        setHorizontalAlignment(SwingConstants.LEFT);
        revalidate();

        exit = new JButton("x");
        exit.setBorder(BorderFactory.createRaisedBevelBorder());
        exit.setPreferredSize(new Dimension(20,20));
        exit.setMargin(new Insets(0,0,0,0));
        exit.setFont(new Font("Arial",Font.PLAIN,11));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeThis();
            }
        });

        add(exit, BorderLayout.EAST);
        setPreferredSize(new Dimension(getPreferredSize().width+12,getPreferredSize().height));

        //doClick();
    }

    private static String getNameOf(String text) {
        int index=0;
        for(int n = 0; n < text.length() ; n++)
            if(text.charAt(n) == File.separatorChar) index=n;
        if(index!=0) return text.substring(index+1);
        else return text;
    }

    void removeThis(){
        if(Panels.sc!=null && home.special.getLastFile().equals(link)){
            Panels.script.remove(Panels.sc);
            Panels.sc = null;
            home.special.setLastFile("");
        }
        Panels.removeWindows(this);
        Panels.script.revalidate();
        Panels.script.repaint();
    }

    public void setClick(){
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }

}
