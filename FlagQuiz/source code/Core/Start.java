package Core;

import DCS.Management;
import GUI.PFrame;
import Utilities.Encryption;

public class Start {

    //declare and initiate an encryption session
    public static Encryption encryption = new Encryption();
    //declare and initiate the DCS subsystem
    public static Management dataStructure = new Management();
    //declare and initiate the GUI subsystem
    public static PFrame frame;

    public static void main(String[] args){
        frame = new PFrame();
    }

}
