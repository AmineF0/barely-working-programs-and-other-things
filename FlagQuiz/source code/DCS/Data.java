package DCS;

import Core.Start;
import java.io.*;
import java.util.ArrayList;

public class Data {

	private String datafile ;
	private String[][] dataProcessed;

	//initialize data
	Data(String datafile) {
		this.datafile=datafile;
		init();
	}	

	//read file xor (create it if it does not exist , fill it if empty, then read)
	public void init () {
		ArrayList<String> data = new ArrayList<String>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(datafile)); //read fileo
		    String line = bufferedReader.readLine();
		    if(line==null) {
				Edit_DataFileStruct(createDemo()); //fill with data structure
				bufferedReader.close();
				bufferedReader = new BufferedReader(new FileReader(datafile)); //read fileo
				line = bufferedReader.readLine();
			}
		    while(line != null) {
		        data.add(Start.encryption.decrypt(line));
		        line = bufferedReader.readLine();
		    }
		    bufferedReader.close();
		} catch (FileNotFoundException e) {
			File g = new File(datafile); //create file
			try {
				g.createNewFile();
			} catch (IOException ex) { ex.printStackTrace(); }
			init(); //recall
			return;
		} catch (IOException e) { System.out.print(e); }
		Process_DataFile(data); //process
	}

	//create new game data structure
	private String createDemo() {
		String demo="";
		for(Function func : Function.values()) {
			if(func.toString().equals("LEVEL")) demo += func + "%" + "1" + "\r";
			else if(func.toString().equals("SCORE")) demo += func + "%" + "20" + "\r";
			else demo += func + "%" + "\r";
		}
		demo=demo.substring(0, demo.length() - 1);
		return demo;
	}

	//process data 	and store it in data structure
	private String[][] Process_DataFile(ArrayList<String> data) {	
		dataProcessed=new String[2][data.size()];
		for(int n=0;n<data.size();n++) {
			String line = data.get(n);
			String[] two  =splitLine(line);
			for(int x=0;x<2;x++) {
				dataProcessed[x][n]=two[x];
			}
		}
		return dataProcessed;
	}

	//split data from text to type and value and store it in String[2]
	private String[] splitLine(String line) {
		String[] two = {"",""};
		for(int n=0;n<line.length();n++) {
			if(line.charAt(n)=='%') {
				for(int y=0,x=0;y<2;y++) {
					for(;x<n;x++) {
						two[y]=two[y]+line.charAt(x);
					}
					x=n+1;
					n=line.length();
				}
			}
		}
		return two;
	}

	//get item from data structure
	public String getItem(String item) {
		return dataProcessed[1][functions(item)];
	}

	//set item to data structure
	private void setItem(String item,String itemPlace) {
		dataProcessed[1][functions(itemPlace)]=item;
		updateFile();
	}

	//find data type wanted to change location in data structure
	private int functions(String section) {
		for(int n=0;n<dataProcessed[0].length;n++) {
			if(dataProcessed[0][n].equals(section))
				return n;
		}
		return -1;
	}

	//update the file containing data structure
	private void updateFile() {
		String newVersion = "";
		for(int x = 0; x<dataProcessed[0].length; x++) {
			newVersion += dataProcessed[0][x] + "%" + dataProcessed[1][x] + "\r";
		}
		newVersion=newVersion.substring(0, newVersion.length() - 1);
		Edit_DataFileStruct(newVersion);
	}

	//write into file the updates
	private void Edit_DataFileStruct(String text) {
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(datafile))) {
			bufferedWriter.write(Start.encryption.encrypt(text));
		} catch (IOException e) {}
	}

	public void changeScore(int n){
		setItem(Integer.toString(Integer.parseInt(getItem("SCORE"))+n),"SCORE");
	}

	//increment the level which will cause the whole data array and data file to update
	public void levelUp(){
		setItem(Integer.toString(Integer.parseInt(getItem("LEVEL"))+1),"LEVEL");
	}

	public int getLevel(){
		return Integer.parseInt(getItem("LEVEL"));
	}

	public int getScore(){
		return Integer.parseInt(getItem("SCORE"));
	}

}

//types of data containing data structure
enum Function{
	SCORE,
	LEVEL
}