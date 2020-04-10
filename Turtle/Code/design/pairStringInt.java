package design;

public class pairStringInt {

    protected String s;
    protected int i = 0;


    pairStringInt(String s , int i ){
        this.s = s;
        this.i = i;
    }

    public void setS(String s){this.s = s;}
    public void setI(int i){this.i = i;}
    public String getS(){return this.s;}
    public int getI() {
        return i;
    }
}
