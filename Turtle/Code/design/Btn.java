package design;


import TST.Code;
import front.home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static design.Panels.show;

enum Btn{

    start,
    pause,
    stop,
    speed;

    JButton buton;

    Btn(){
        buton=new JButton(this.name());
        buton.addActionListener(this::actionPerformed);
    }

    public void start() {
        ArrayList<Pair> instruction = new ArrayList<>();
        if(show.started)
            if(!show.scene.pause) stop();
            else {show.scene.rerun(); show.scene.pause=false; start.buton.setText("restart");}

        if(Panels.sc == null )
            Frames.alert("Turtle","Open a file");
        else if(!show.started && Panels.sc.step.CheckArrayFunc()){
            start.buton.setText("restart");
            show.scene = new Show(createOrders(Panels.sc.step));
            show.showScene.add(show.scene,BorderLayout.CENTER);
            show.started=true;
            show.revalidate();
            show.scene.revalidate();
            show.scene.repaint();
            show.scene.setTurtle(show.showScene.getSize());
        }
    }

    private ArrayList<pairStringInt> createOrders(MyArrayList step) {
        //basic features
        ArrayList<pairStringInt> order = new ArrayList();
        for(int n=0; n<step.getArrayFunc().size();n++) {
            String containt = step.getArrayFunc().get(n);
            String s = "";
            String i = "";
            if(containt.contains("load")){
                step.getArrayFunc().remove(n);
                ArrayList<String> load = getLoaded(new File(home.special.getReprisatory()+File.separator+containt.substring("load".length()))).getArrayFunc();
                if(load.size()!=0)
                    step.getArrayFunc().addAll(n,load);
                else{
                    stop();
                    break;
                }
            }
            containt = step.getArrayFunc().get(n);
            for(int y=0; y<containt.length(); y++){
                if(Character.isLetter(containt.charAt(y))){
                    s += containt.charAt(y);
                }
                else if(!Character.isLetter(containt.charAt(y))){
                    i += containt.charAt(y);
                }
            }
            if(i.equals("")) i="0";
            order.add(new pairStringInt(s,Integer.parseInt(i.trim())));
        }
        int size = order.size();
        for (int n = size -1;n>=0;n--){
            if (order.get(n).s.equals("repeat")){
                int times = order.get(n).i;
                order.remove(n);
                order = getRepeated(order,n,times);
            }
        }
 //       for (int n=0; n<order.size();n++)
 //          System.out.println(n + "\t " + order.get(n).s + "\t " + order.get(n).i);
        return order;
    }

    private MyArrayList getLoaded(File file){
        MyArrayList load = new MyArrayList();
        if(file.exists() && file.toString().contains(".tst")){
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                String text = "" ;
                while(line != null) {
                    text += line+System.getProperty("line.separator");
                    line = bufferedReader.readLine();
                }
                load.AddChangeT(text);
                bufferedReader.close();
            } catch (IOException e) {}
            if(load.CheckArrayFunc()) return load;
            else return new MyArrayList();
        }
        else
            Frames.alert("compiler","file trying to load is either inexistant or incompatible");
        return load;
    }

    private ArrayList<pairStringInt> getRepeated(ArrayList<pairStringInt> order, int n ,int times) {
        ArrayList<pairStringInt> repeated = new ArrayList<>();
        while(n < order.size()){
            if(order.get(n).s.equals("endrepeat")) {
                order.remove(n);
                break;
            }
            repeated.add(order.get(n));
            n++;
        }
        for(int y = 0 ; y<times-1;y++){
            for (int x=0;x<repeated.size();x++){
                order.add(n,repeated.get(repeated.size()-1-x));
            }
        }
        return order;
    }

    public void pause() {
        if (show.started) {
            start.buton.setText("resume");
            show.scene.time.cancel();
            show.scene.pause=true;
        }
    }

    public void stop() {
        if(show.started) {
            start.buton.setText("start");
            show.scene.task.cancel();
            show.scene.time.purge();
            show.showScene.remove(show.scene);
            show.scene=null;
            show.revalidate();
            show.repaint();
            show.started=false;
        }
    }

    public void speed() {
        int speed = changeSpeed();
        if(show.started && speed !=0) {
            show.scene.setSpeed(speed);
        }else if(speed !=0){
            home.special.setSpeed(speed);
        }
    }

    private int changeSpeed() {
        String s = JOptionPane.showInputDialog(null,"Speed (ms) :");
        if(s==null) return 0;
        else if(!s.matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "Input is not integer", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        else if(Integer.parseInt(s.trim())<=0) {
            JOptionPane.showMessageDialog(null, "Input is not a valid number.\r hint : strictly positive number ", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        else return Integer.parseInt(s.trim());

    }

    private void actionPerformed(ActionEvent arg0) {
        switch (name()) {
            case "start":
                start();
                break;
            case "pause":
                pause();
                break;
            case "stop":
                stop();
                break;
            case "speed":
                speed();
                break;

        }
    }
}