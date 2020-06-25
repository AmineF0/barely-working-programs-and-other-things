package Utilities;
import java.util.ArrayList;

public class Encryption {

    private final String keyWord = "AMINEF0";
    private final char[] newAlphabet = createAlphabetArray();

    //create the encryption data by making an arrat , size 26 containing the latin letters in a customizable way
    private char[] createAlphabetArray() {

        char[] temporary = new char[26];
        ArrayList<Character> alphabet = new ArrayList<Character>();

        //make an array to store all the letter that are not in the key word to add them later
        for(int n = (int) 'A' ; n < (int) 'Z' ; n++){
            Character letter = (Character)(char)n;
            if(!keyWord.contains(letter.toString()))
                alphabet.add(letter);
        }

        //make the array that will store the new structure of old alphabet in reverse
        for(int n = 0 ; n < temporary.length ; n++){
            //add the letters in key word from last
            while(n < keyWord.length()) {
                temporary[temporary.length - n - 1] = keyWord.charAt(n);
                n++;
            }
            //add the left letter in alphabet and add them from the last empty place int the strutcture to the first
            for (Character letter : alphabet){
                temporary[temporary.length - n - 1] = letter;
                n++;
            }
        }

        return temporary;
    }

    //encrypt the input string
    public String encrypt(String input){
        String output = "";
        //walks through the input string character by character
        for(char letter : input.toCharArray())
            //encrypt only the alphabet in capital letters
            if(letter>='A'&&letter<='Z')
                output+=newAlphabet[letter-(int)'A'];
            else if(letter=='0')
                output+="Z";
            else
                output+=letter;
        return output;
    }

    //decrypt the input string
    public String decrypt(String input){
        String output = "";
        //walks through the input string character by character
        for(char letter : input.toCharArray())
            //encrypt only the alphabet in capital letters
            if(letter=='0' || (letter>='A'&&letter<='Y'))
                output+=(char)(getLocation(letter)+(int)'A');
            else if (letter=='Z')
                output+=0;
            else
                output+=letter;
        return output;
    }

    //find the location in the array that contains the letter
    private int getLocation(char character){
        for(int n=0 ; n < newAlphabet.length ; n++){
            if(newAlphabet[n]==character)
                return n;
        }
        return 0;
    }

}
