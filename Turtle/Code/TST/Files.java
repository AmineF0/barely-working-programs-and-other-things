package TST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;

import design.Frames;
import design.Panels;
import front.home;

public class Files {

	public static boolean exist(String text) {
		File file = new File(front.home.special.getReprisatory()+File.separator+text+".tst");
        return file.exists();
	}

	public static File createTSTfile(String name) {
		((JLabel)Frames.info).setText("Loading...");
		return file.store.create_file(front.home.special.getReprisatory()+File.separator+name+".tst");
	}

	public static String openTSTfile(String string) {
		String text = "";
		front.home.special.setLastFile(string);
		//front.MenuBar.file.noticeOpenLastFile();
		try {
			((JLabel)Frames.info).setText("Loading...");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(string));
			String line = bufferedReader.readLine();
		    while(line != null) {
		    	text+=line+System.getProperty("line.separator");
		        line = bufferedReader.readLine();
		    }
		    bufferedReader.close();
		} catch (IOException e) {}
		home.special.setLastFile(string);
		home.special.setLoaded(true);
		((JLabel)Frames.info).setText(string);
		return text;
	}

	public static void saveTSTfile() {
		if(home.special.getLastFile().isEmpty()) {
			design.Frames.alert("error", "Open a file first");
		}
		else {
			try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(home.special.getLastFile()))) {
			    bufferedWriter.write(design.ScriptPanel.getField().getText());
			} catch (IOException e) {}
		}
	}

	public static void saveasTSTfile(String string) {
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(string))) {
		    bufferedWriter.write(design.ScriptPanel.getField().getText());
		} catch (IOException e) {}
		
	}

	
}
