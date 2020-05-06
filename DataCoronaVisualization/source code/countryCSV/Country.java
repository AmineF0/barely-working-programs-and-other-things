package countryCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Country {

	public final int NUMBER = 0 , CHAR = 1 , STRING = 2, DATECLASS = 3;//constant of type of sort
	protected String [][] data ; //structure
	protected String link; // link of data
	protected SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); // date format
	
	public Country(String link){
		//initialize
		this.link = link;
		getDim();
		fill();
		
	}
	
	//fill with data from csv
	private void fill() {
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader(link));
			String line;
			
			//get data
			for(int x = 0 ; x < data.length && (line=file.readLine()) != null ; x++) {
				data[x] = line.split(",");
			}
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}

	//get dimension of csv table
	private void getDim(){
		
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader(link));
			
			String line;
			int width = 0 , lenght = 0;
			
			width++;
			
			if((line=file.readLine()) != null) {
				lenght= line.split(",").length;
				width++;
				while((line=file.readLine()) != null) {
					width++;
				}
			}
			data = new String[width][lenght];
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//find the raw of the information in the table 
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

	//find data
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
	
	//make a string from the data structure
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
	
	//make string from a line in the data structure
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
			string = string.substring(0, string.length()-2);
			string += System.lineSeparator();
		}
		return string;
	}
	
	//save data as CSV
	public void saveAsCSV(String name) {
		try {
			PrintWriter writer = new PrintWriter(new File(name));
			writer.print(toCSVfile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//sort by bubble algorithm
	public String[][] orderBy(String type , int classOfType){
		String[][] orderedData = new String[data.length][data[0].length];
		
		int row = findRow(type);
		if(row==-1) {
			System.out.print("invalid type");
			return null;
		}
		
		for(int y = 1 ; y < data.length && data[y][row]!= null && data[y][row].length()!=0 ; y ++) {
			switch(classOfType) {
				case NUMBER : 
					if(!data[y][row].matches("^[+-]?(?:\\d+\\.?\\d*|\\d*\\.\\d+)$")) {
						System.out.println("invalid data");
						return null;
					}break;
				case CHAR : if(data[y][row].length()!=1){
						System.out.println("invalid data");
						return null;
					}break;
				case DATECLASS : try {
					if(date.parse(data[y][row]) == null){
						System.out.println("invalid data");
						return null;
					}
					} catch (ParseException e) {
						System.out.println(e+"invalid data");
						return null;
					}break;
			}
		}
		
		for(int y = 1 ; y < data.length && data[y][row]!= null && data[y][row].length()!=0 ; y ++) {
			switch(classOfType) {
				case NUMBER : return sortNumber(row);
				case CHAR : return sortString(row);
				case STRING : return sortString(row);
				case DATECLASS : return sortDate(row);
			}
		}
		
		return orderedData;
	}

	private String[][] sortString(int row) {
		String[][] orderedData = data;
		for(int y = 2 ; y < data.length ; y++) {
			for(int yy = 1 ; yy < data.length -1 ; yy++) {
				for(int n = 0 ; orderedData[yy][row]!=null && orderedData[yy+1][row]!=null && (n<orderedData[yy][row].length()) && (n<orderedData[yy+1][row].length()) ; n++) {
					orderedData=sortChar(row, n , y , yy);
				}
			}
		}
		return orderedData;
	}
	
	private String[][] sortDate(int row) {
		String[][] orderedData = data;
		String[] tempArr;
		for(int y = 2 ; y < data.length ; y++) {
			for(int yy = 1 ; yy < data.length -1 ; yy++) {
				if(orderedData[yy][row]!=null && orderedData[yy+1][row]!=null 
						&& !orderedData[yy][row].isEmpty() && !orderedData[yy+1][row].isEmpty()
						&&  dStr1BigdStr2(orderedData[yy][row],(orderedData[yy+1][row]))) {
					tempArr = orderedData[yy];
					orderedData[yy] = orderedData[yy+1];
					orderedData[yy+1] = tempArr;
				}
				else if(orderedData[yy][row]==null || orderedData[yy][row].isEmpty()) {
					tempArr = orderedData[yy];
					orderedData[yy] = orderedData[yy+1];
					orderedData[yy+1] = tempArr;
				}
			}
		}
		return orderedData;
	}
	
	//return true if date of str1 bigger than date of str2
	private boolean dStr1BigdStr2(String str1 , String str2) {
		try {
			return date.parse(str1).compareTo(date.parse(str2))>0;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	private String[][] sortChar(int row , int n , int y , int yy) {
		String[][] orderedData = data;
		String[] tempArr;
				if(orderedData[yy][row]!=null && orderedData[yy+1][row]!=null  
						&& (int)Character.toLowerCase(orderedData[yy][row].charAt(n))<(int)Character.toLowerCase(orderedData[yy+1][row].charAt(n))) {
					tempArr = orderedData[yy];
					orderedData[yy] = orderedData[yy+1];
					orderedData[yy+1] = tempArr;
				}
				else if(orderedData[yy][row]== null) {
					tempArr = orderedData[yy];
					orderedData[yy] = orderedData[yy+1];
					orderedData[yy+1] = tempArr;
				}
		return orderedData;
	}

	private String[][] sortNumber(int row) {
		String[][] orderedData = data;
		String[] tempArr;
		for(int y = 2 ; y < data.length ; y++) {
			for(int yy = 1 ; yy < data.length -1 ; yy++) {
				if(orderedData[yy][row]!=null && orderedData[yy+1][row]!=null 
						&& !orderedData[yy][row].isEmpty() && !orderedData[yy+1][row].isEmpty()
						&&  Double.parseDouble(orderedData[yy][row])<Double.parseDouble(orderedData[yy+1][row])) {
					tempArr = orderedData[yy];
					orderedData[yy] = orderedData[yy+1];
					orderedData[yy+1] = tempArr;
				}
				else if(orderedData[yy][row]==null || orderedData[yy][row].isEmpty()) {
					tempArr = orderedData[yy];
					orderedData[yy] = orderedData[yy+1];
					orderedData[yy+1] = tempArr;
				}
			}
		}
		return orderedData;
	}
	
	public void setData(String[][] newData) {
		this.data = newData;
	}
	
	public String[][] getData(){
		return this.data;
	}

}