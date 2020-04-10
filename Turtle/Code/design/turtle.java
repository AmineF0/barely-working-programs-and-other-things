package design;

import front.home;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class turtle{

    protected final int width=35, height=35;
    protected int x , y , direction = 0;
    protected BufferedImage image;
    protected String url=home.special.checkTurtle(home.special.getTurtle());
    protected boolean penIn = true;

    turtle(int x , int y){
        this.x=x-width/2;
        this.y=y-height/2;
        loadImage();
    }

    private void loadImage() {
        if(!(url ==""))
            try {
                image = ImageIO.read(new File(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        else {
            Btn.stop.buton.doClick();
            Frames.alert("Image loader","turtle image can not be loaded");
        }
    }

    public void walk(){
        Math.sin(direction);
        Math.cos(direction);
    }

    public void rotate(int degree){
        direction+=degree;
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
