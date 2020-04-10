package design;

import front.home;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static java.awt.Color.*;

public class Panels {

    public static ShowPanel show;
    static JPanel script , files , panel;
    public static ScriptPanel sc;
    static JLabel n ;
    public static FilesOp fileOpen;

    public static JPanel setPanel() {

        panel = new JPanel();
        show = new ShowPanel();
        script = new JPanel();
        n = new JLabel("<html>SCRIPT<br>PANEL</html>");
        files = new JPanel();
        fileOpen = new FilesOp();
        addAllWindows();

        script.setBackground(lightGray);
        script.setLayout(new BorderLayout());
        n.setFont(new Font("Arial",Font.ITALIC,45));
        n.setForeground(darkGray);

        files.setLayout(new FlowLayout(FlowLayout.LEFT));

        script.add(files,BorderLayout.NORTH);
        script.add(n,BorderLayout.CENTER);

        panel.setLayout(new GridLayout(1,2,10,10));
        panel.add(script);
        panel.add(show);

        show.setOpaque(true);
        show.setBorder(BorderFactory.createEtchedBorder());
        show.setBackground(GRAY);

        script.setOpaque(true);
        script.setBorder(BorderFactory.createEtchedBorder());

        panel.revalidate();


        return panel;
    }

    public static void addWindow(File file){
        if(Panels.sc!=null){Panels.script.remove(Panels.sc);
            Panels.sc = null;}
        Panels.script.remove(Panels.n);
        Panels.script.revalidate();
        Panels.script.repaint();
        fileOpen.addItem(file.getAbsolutePath());
        addAllWindows();
    }

    public static void removeWindows(Window window) {
        files.removeAll();
        for (int n= 0 ; n< fileOpen.windows.size();n++)
            if(fileOpen.windows.get(n)==window)
                fileOpen.removeItem(n);
        addAllWindows();

        Panels.script.add(Panels.n,BorderLayout.CENTER);
        Panels.script.revalidate();
        Panels.script.repaint();

    }

    public static void addAllWindows(){
        files.removeAll();
        for(Window file : fileOpen.windows)
            files.add(file);
        files.revalidate();
        files.repaint();
    }

}

