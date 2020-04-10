package design;

import front.home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Show extends JPanel {

    protected boolean pause;
    protected ArrayList<pairStringInt> step;
    protected ArrayList<pairStringInt> temp = new ArrayList<>();
    java.util.Timer time = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
             process();
        }
    };
    protected int speed;
    turtle turt;
    Zoom zoom=new Zoom();
    String file;

    Show(ArrayList<pairStringInt> step){
        super();
        setLayout(null);
        zoom.setLocation(new Point(10,10)) ;
        add(zoom);
        this.step=step;
        pause = false;
        speed = home.special.getSpeed();
        file=home.special.getLastFile();
        run();
        revalidate();
        repaint();
    }

    public void setTurtle(Dimension dim){
        turt=new turtle(dim.width/2,dim.height/2);
    }

    private void run() {
        time.schedule(task, speed, speed);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setOpaque(true);
        int x = turt.x,y=turt.y;
        int direction= turt.direction;
        boolean penIn = turt.penIn;
        BufferedImage image = turt.image;
        for(int n = 0;n<temp.size();n++){
            pairStringInt pair = temp.get(n);
            if(pair.s.equals("walk")) {
                if(penIn)
                    g.drawLine(x, y,
                            x += (int) (pair.i * Math.cos(Math.toRadians(direction))*home.special.getZoom()/100),
                            y += (int) (pair.i * Math.sin(Math.toRadians(direction))*home.special.getZoom()/100));
                else {
                    x += (int) (pair.i * Math.cos(Math.toRadians(direction))*home.special.getZoom()/100) ;
                    y += (int) (pair.i * Math.sin(Math.toRadians(direction))*home.special.getZoom()/100);
                }
            }
            if(pair.s.equals("rotate")){
                direction+=pair.i;
                image=
                new AffineTransformOp(AffineTransform.getRotateInstance(Math.toRadians(direction),turt.image.getWidth()/2,turt.image.getHeight()/2), AffineTransformOp.TYPE_BILINEAR)
                        .filter(turt.image, null);
            }
            if(pair.s.equals("penoff"))
                penIn=false;
            if(pair.s.equals("penin"))
                penIn=true;
        }
        g.drawImage(image,x-turt.width/2,y-turt.height/2,turt.width,turt.height,null);
        revalidate();
        repaint();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed){
        home.special.setSpeed(speed);
        time.cancel();
        this.speed=speed;
        rerun();
    }

    private void process() {
        if ((temp.size() == 0 || step.get(temp.size() - 1).i == temp.get(temp.size() - 1).i) && step.size() != temp.size()){
            if (step.get(temp.size()).s.equals("walk") || step.get(temp.size()).s.equals("rotate"))
                temp.add(new pairStringInt(step.get(temp.size()).s, 0));
            else {
                if (step.get(temp.size()).s.equals("setspeed"))
                    setSpeed(step.get(temp.size()).i);
                else if (step.get(temp.size()).s.equals("setzoom"))
                    home.special.setZoom(step.get(temp.size()).i);
                temp.add(new pairStringInt(step.get(temp.size()).s, step.get(temp.size()).i));
            }
            Frames.info.setText(temp.get(temp.size() - 1).s);
        }
        else if(temp.get(temp.size()-1).i<step.get(temp.size()-1).i){
            temp.get(temp.size()-1).i++;
            Frames.info.setText(file+" : "+step.get(temp.size()-1).s+" : "+temp.get(temp.size()-1).i+"/"+step.get(temp.size()-1).i);
        }
        else{
            Frames.info.setText("Finished : "+ file);
            Btn.start.buton.setText("start");
            task.cancel();
            time.cancel();
        }
        revalidate();
        repaint();
    }

    public void rerun(){
        time= new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                process();
            }
        };
        time.schedule(task, speed, speed);
    }

}

class Zoom extends JPanel {

    JButton add ;
    JButton reduce;
    JButton add10 ;
    JButton reduce10;
    JPanel panel;

    JLabel value;
    int zoom;

    Zoom(){
        setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add = setButton(20,"+",1);
        reduce = setButton(20,"-",-1);
        add10 = setButton(25,"+10",10);
        reduce10 = setButton(25,"-10",-10);
        zoom=home.special.getZoom();
        value = new JLabel(Integer.toString(zoom));
        value.setHorizontalAlignment(SwingConstants.CENTER);

        add(add10 , BorderLayout.WEST);
        add(reduce10 , BorderLayout.EAST);
        add(panel , BorderLayout.CENTER);

        panel.add(add , BorderLayout.WEST);
        panel.add(reduce , BorderLayout.EAST);
        panel.add(value , BorderLayout.CENTER);


        setBackground(Color.white);
        setSize(new Dimension(120,20));
        setOpaque(true);
    }

    private JButton setButton(int x, String str , int ajout){
        JButton btn = new JButton(str);
        btn.setPreferredSize(new Dimension(x,20));
        btn.setMargin(new Insets(0,0,0,0));
        btn.setFont(new Font("Arial",Font.BOLD,9));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setValue(zoom += ajout);
            }
        });
        return btn;
    }

    public void setValue(int zoom){
        this.zoom=zoom;
        home.special.setZoom(zoom);
        value.setText(Integer.toString(home.special.getZoom()));
    }
}