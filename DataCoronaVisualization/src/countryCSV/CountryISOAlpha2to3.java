
import java.io.File;

public class CountryISOAlpha2to3 {

	public static void main(String[] args) {
		//check for inherited data 
		if(args.length==0 || !(new File(args[0]).exists() && new File(args[0]).isDirectory())) {
			System.out.println("invalid call of the function , you need to add a valid directory");
			return;
		}
		
		for(File file : new File(args[0]).listFiles()) {
			String name = file.getAbsolutePath().split(File.separator)[args[0].split(File.separator).length-1].split(".")[0]; 
			System.out.print(name);
		}
	}

}


