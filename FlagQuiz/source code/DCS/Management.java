package DCS;

import Utilities.Functions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Core.Start.encryption;

public class Management {

    //connection to data user
    private CSVFile levels; //declare a CSVFile data structure to connect with temp.csv in data user
    private Data userData; //declare a Data data structure to connect with dataFile in data user

    public Management(){
        //create a hidden directory that if it does not exist in the home directory to use it for data user
        if(!new File(System.getProperty("user.home")+ Functions.fileLinkCP("/.QuizGameData", "unix")).exists())
            new File(System.getProperty("user.home")+Functions.fileLinkCP("/.QuizGameData", "unix")).mkdir();
        //assign values to the data structures with files path
        levels = new CSVFile(System.getProperty("user.home")+Functions.fileLinkCP("/.QuizGameData/temp.csv", "unix"));
        userData = new Data(System.getProperty("user.home")+Functions.fileLinkCP("/.QuizGameData/dataFile", "unix"));

    }

    public void changeScore(int n){
        userData.changeScore(n);//change user score (fileData)
    }

    //
    public void levelUp(){
        levels.markAnswered();//update level (temp.csv )data structure
        userData.levelUp();//update userData (fileData )data structure
    }

    public int getLevel(){
        return userData.getLevel();//get level from userData data structure
    }

    public int getScore(){
        return userData.getScore();//get score from userData data structure
    }

    //import flag from data/flag directory
    public BufferedImage getFlag(String name) throws IOException {
        return ImageIO.read(getClass().getResourceAsStream("/data/flags/"+name+".png"));
    }

    //get Options from levels data structure
    //opt[] has basically size 4 , for 4 countries
    // opt[0] is the correct answer, others are false
    //dataprocessed is opt but with deciphered values
    //dataprocessed[4] is the encryption of the correct answer to get the flag file name
    public String[] getOptions(){
        String[] opt = levels.getCountry(getLevel()); //get level's options
        if(opt == null) return null; //return null if it finds nothing
        String[] optionProcessed = new String[5]; //4 options + name
        optionProcessed[4] = opt[0]; //flag file name
        for (int n = 0 ; n < opt.length ; n++){
            optionProcessed[n] = encryption.decrypt(opt[n]);//decrypt the data
        }
        return optionProcessed;
    }

}
