package design;
import design.Panels;
import design.Window;
import front.home;

import java.io.*;
import java.util.ArrayList;

public class FilesOp {

    ArrayList<Window> windows;

    public FilesOp(){
        windows = new ArrayList<>();
        getList();
    }

    private void getList() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(home.special.getFiles()));
            String line = bufferedReader.readLine();
            while(line != null) {
                windows.add(new Window(line));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {}
    }

    private void write(){
        String text = "";
        for (Window table : windows){
            text += table.link + System.lineSeparator();
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(home.special.getFiles()))) {
            bufferedWriter.write(text);
        } catch (IOException e) {}
    }

    public void addItem(String link){
        windows.add(new Window(link));
        windows.get(windows.size()-1).doClick();
        write();
    }

    public void removeItem(int n){
        windows.remove(n);
        write();
    }

    public void setAllOff(){
        for (Window table : windows){
            table.setClick();
        }
    }

}