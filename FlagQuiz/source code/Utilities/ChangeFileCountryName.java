package Utilities;

import DCS.CSVFile;

import java.io.*;
import java.util.Scanner;

public class ChangeFileCountryName {

	//data/flags
	public static void main(String[] args) {

		Encryption session = new Encryption();
		String text ="C,D\n";
		for(File file : new File("src/data/flags").listFiles()) {
			text+=file.getName().substring(0,file.getName().length()-4)+",0\n";
		}
		text+=text.substring(0,text.length()-1);
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/data/h.csv"))) {
			bufferedWriter.write(text);
		} catch (IOException e) {}

		String link = (new Scanner(System.in)).nextLine();
		CSVFile country = new CSVFile("src/Utilities/CountriesInfo.csv");
		if(link.isEmpty() || !(new File(link).exists() && new File(link).isDirectory())) {
			System.out.println("invalid call of the function , you need to add a valid directory");
			return;
		}
		
		for(File file : new File(link).listFiles()) {
			String name = file.getAbsolutePath().split(File.separator)[file.getAbsolutePath().split(File.separator).length-1].split(".png")[0]; 
			String nextName = country.getInfo("alpha-3", "name", name.toUpperCase());
			System.out.println(file.toString());
			if(nextName != null) {
				file.renameTo(new File(file.getParentFile()+File.separator+session.encrypt(nextName.toUpperCase())+".png"));
			}
		}
	}

}


