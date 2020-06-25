package DCS;

import java.io.*;

public class CSVFile {

	protected String [][] data ; //data array
	protected String link; // link of data
	protected InputStream original ; //original file hh that exists in system data
	private int place ; //the row of the correct answer in the array , it is a temporary information that changes with the level or reboot

	public CSVFile(String link){
		//initialize
		this.link = link;
		original = getClass().getResourceAsStream("/data/h") ;

		//check if temp.csv exists , if not it create it and copy the template of h
		if(!new File(link).exists())
			createTempFile(); //create temp.csv from template

		//prepare the array
		getDim();// calculates the array needed dimensions
		fill();// fill the array after assign a defined array to it
	}

	//get dimension of csv table by streaming the file content and analysing in respect of ',' and line return
	private void getDim(){
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader(link));
			String line;
			int width = 0 , lenght = 0;
			if((line=file.readLine()) != null) {
				lenght= line.split(",").length; //get lenght in respect of (the number of comma)+1
				width++;
				while((line=file.readLine()) != null)
					width++;//get width by incrementing for each new line on stream , stop when there is not
			}
			data = new String[width][lenght]; //assignig a determined exact size to the array
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}

	//fill with data from csv
	private void fill() {
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader(link));
			String line;
			//get data from stream and give it to array
			for(int x = 0 ; x < data.length && (line=file.readLine()) != null ; x++) {
				data[x] = line.split(",");
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}

	//search in the data array
	//find the row of the information in the table
	public String[] getInfo( int row ) {
		if( row < data.length ) {
			return data[row];
		}
		return null;
	}
	
	//find the cell in the table
	public String getInfo( String type , int row ) {
		int rowIn = findRow(type) ;
		if( row < data.length ) {
			return data[row][rowIn];
		}
		return null;
	}

	//find data by type of the value entered and wanted , in the table
	public String getInfo( String typeIn , String typeOut , String value ) {
		int rowIn = findRow(typeIn) , rowOut = findRow(typeOut);
		for(int y=1 ; y < data.length ; y++) {
			if(data[y][rowIn]!= null && data[y][rowIn].equals(value)) {
				return data[y][rowOut];
			}
		}
		return null;
	}
	
	private int findRow(String info) {
		for(int x = 0 ; x<data.length ; x++) {
			if(info.equals(data[0][x])) {
				return x;
			}
		}
		return -1;
	}

	//special features given to CSV data structures for this precise project

	//get 4 countries name , the first was never got before as an answer which will be
	public String[] getCountry(int level){
		String[] options = new String[4];
		if((data.length-level)==0)//check if there is still a level to play
			return  null;//null means there is not , it will be handled correctly as a return value by the caller
		place = (int) (Math.random()*(data.length-level-1))+1; // get a random possible level that was not made store it for later use if the user found the correct answer
		options[0] = data[place][0];
		for (int n = 1 ; n < options.length ; n++){
			String text = data[(int) (Math.random()*(data.length-2))+1][0];//fill the three left options with any country name
			for (int previous = 0 ; previous < n ; previous++){ //check there is no duplicates
				if (text.equals(options[previous])) {
					n--;//refind a new value
					break;
				}
				options[n]=text;
			}
		}
		return options;
	}

	//mark as answered
	//take the line and bring it next to the answered ones , making the csv in to distenguish parts
	//more explanation is given below on the reasons
	public void markAnswered(){
		data[place][1]="1";//mark data in place answered
		while(place+1<data.length && data[place+1][1].equals("0")){
			String temp = data[place][1];
			data[place][1] =  data[place+1][1];
			data[place+1][1] = temp;
			temp = data[place][0];
			data[place][0] =  data[place+1][0];
			data[place+1][0] = temp;
			place++;
		}//put it in the second partition of the file
		saveAsCSV(toCSVfile());
	}

	//creating a temp.csv by coping the values in h from system data
	private void createTempFile(){
		BufferedReader file = null;
		String text = "";
		try {
			System.out.println(original);
			file = new BufferedReader(new InputStreamReader(original));
			String line ;
			while ((line=file.readLine()) != null) {
				text += line + "\n";
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		saveAsCSV(text.substring(0,text.length()-1));
	}

	//extra to get information in a humanly understandable or store it

	//make a string from the data array
	public String toString(String[][] characters){
		String string = "";
		for(String[] row : characters){
			for(String character : row){
				string += character +"|\t";
			}
			string += System.lineSeparator();
		}
		return string;
	}

	//make string from a line in the data array
	public String toString(String[] characters){
		String string = "";
		for(String character : characters){
			string += character +"|\t";
		}
		string += System.lineSeparator();
		return string;
	}

	//make a csv file format string from data
	private String toCSVfile(){
		String string = "";
		for(String[] row : data){
			for(String character : row){
				string += character +",";
			}
			string = string.substring(0, string.length()-1);
			string += System.lineSeparator();
		}
		return string;
	}

	//save data as CSV
	private void saveAsCSV(String text) {
		try {
			PrintWriter writer = new PrintWriter(link, "UTF-8");
			writer.print(text);
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}

}