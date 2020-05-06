package countryCSV;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CountryISOAlpha2to3 {

	///home/aminef0/Desktop/temporary/DA/bin/data/flags
	public static void main(String[] args) {
		String link = (new Scanner(System.in)).nextLine();
		Country country = new Country("/home/aminef0/Desktop/temporary/DA/src/data/coronavirus/CountriesInfo.csv"); 
		if(link.isEmpty() || !(new File(link).exists() && new File(link).isDirectory())) {
			System.out.println("invalid call of the function , you need to add a valid directory");
			return;
		}
		
		for(File file : new File(link).listFiles()) {
			String name = file.getAbsolutePath().split(File.separator)[file.getAbsolutePath().split(File.separator).length-1].split(".png")[0]; 
			String nextName = country.getInfo("alpha-2", "alpha-3", name.toUpperCase());
			System.out.println(file.toString());
			if(nextName != null) {
				file.renameTo(new File(file.getParentFile()+File.separator+nextName+".png"));
			}
		}
	}

}


