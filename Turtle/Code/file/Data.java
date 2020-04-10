package file;

import design.Frames;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
	
	String datafile = "data"+File.separator+"dataFile.data";
	private String[][] dataProcessed;
	String[][] dataProcessedtemp;
	private boolean loaded=false;

	public Data() {
		this.setDataProcessed(main());
	}	
	
	public String[][] main () {
		ArrayList<String> data = new ArrayList<String>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(datafile));
		    String line = bufferedReader.readLine();
		    if(line==null) 
		    	{
		    	Edit_DataFileStruct(createDemo());
		    	bufferedReader = new BufferedReader(new FileReader(datafile));
		    	line = bufferedReader.readLine();
		    	}
		    while(line != null) {
		        data.add(line);
		        line = bufferedReader.readLine();
		    }
		    bufferedReader.close();
		} catch (FileNotFoundException e) {
			File g = new File(datafile);
			try {
				g.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return main();
		} catch (IOException e) {
			System.out.print(e);
		}
		return Process_DataFile(data);
		
	}

	private String createDemo() {
		String demo="";
		for(Function func : Function.values()) {
			if(func.toString().equals("automaticSave")) demo += func + ">" + "NO" + "\r";
			else if(func.toString().equals("firstSave")) demo += func + ">" + "0" + "\r";
			else if(func.toString().equals("speed")) demo += func + ">" + "100" + "\r";
			else if(func.toString().equals("zoom")) demo += func + ">" + "1" + "\r";
			else demo += func + ">" + "\r";
		}
		demo=demo.substring(0, demo.length() - 1);
		return demo;
	}

	private String[][] Process_DataFile(ArrayList<String> data) {	
		dataProcessedtemp=new String[2][data.size()];
		for(int n=0;n<data.size();n++) {
			String line = data.get(n);
			String[] two = new String[2];
			two=splitLine(line);
			for(int x=0;x<2;x++) {
				dataProcessedtemp[x][n]=two[x];
			}
		}
		return dataProcessedtemp;	
	}

	private String[] splitLine(String line) {
		String[] two = {"",""};
		for(int n=0;n<line.length();n++) {
			if(line.charAt(n)=='>') {
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

	private void Edit_DataFileStruct(String text) {
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(datafile))) {
		    bufferedWriter.write(text);
		} catch (IOException e) {}
	}

	private void updateFile() {
		String newVersion = "";
		dataProcessedtemp=getDataProcessed();
		for(int x=0;x<dataProcessedtemp[0].length;x++) {
			newVersion= newVersion + dataProcessedtemp[0][x] + ">" + dataProcessedtemp[1][x] + "\r";
		}
		newVersion=newVersion.substring(0, newVersion.length() - 1);
		Edit_DataFileStruct(newVersion);
	}
	
 	public String[][] getDataProcessed() {
		return dataProcessed;
	}

	public void setDataProcessed(String[][] dataProcessed) {
		this.dataProcessed = dataProcessed;
	}
	
	public String getItem(String item) {
		return getDataProcessed()[1][functions(item)];
	}
	
	public void setItem(String item,String itemPlace) {
		dataProcessedtemp=getDataProcessed();
		dataProcessedtemp[1][functions(itemPlace)]=item;
		setDataProcessed(dataProcessedtemp);
		updateFile();
	}
	
	public String getReprisatory() {
		return getItem("directory");
	}
	
	public void setReprisatory(String repisatory) {
		setItem(repisatory,"directory");
	}

	public String getLastFile() {
		return getItem("lastfile");

	}

	public void setLastFile(String repisatory) {
		setItem(repisatory,"lastfile");
	}

	public String getAutoSave() {
		return getItem("automaticSave");
	}
	
	public void setAutoSave(String repisatory) {
		setItem(repisatory,"automaticSave");
	}
	
	public String getFirstSave() {
		return getItem("firstSave");
	}
	
	public void setLoaded(boolean loaded) {
		this.loaded=loaded;
	}
	
	public boolean getLoaded() {
		return loaded;
	}
	
	public void setFirstSave(String repisatory) {
		setItem(repisatory,"firstSave");
	}
	
	public int functions(String section) {
		for(int n=0;n<getDataProcessed()[0].length;n++) {
			if(getDataProcessed()[0][n].equals(section))
			return n;
		}
		return -1;
	}

	public static void checkDataFile() {
		File check = new File("data"+File.separator+"dataFile.data");
		if(!check.exists()) {
			File data = new File("data");
			data.mkdir();
			store.create_file("data"+File.separator+"dataFile.data");
		}
	}

	public int getSpeed() {
		return Integer.parseInt(getItem("speed").trim());
	}

	public void setSpeed(int repisatory) {
		setItem(Integer.toString(repisatory),"speed");
	}

	public String getFiles() {
		if (getItem("openedfile").equals("") || !new File(getItem("openedfile")).exists()){
			File file = new File("data"+File.separator+"data.files");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			setFiles(file.getAbsolutePath());
		}
		return getItem("openedfile");
	}

	public void setFiles(String repisatory) {
		setItem(repisatory,"openedfile");
	}

	public int getZoom() {
		return Integer.parseInt(getItem("zoom").trim());
	}

	public void setZoom(int repisatory) {
		setItem(Integer.toString(repisatory),"zoom");
	}

	public String getTurtle() {
		return getItem("turtle");
	}

	public void setTurtle(String repisatory) {
		setItem(repisatory,"turtle");
	}

	public String checkTurtle(String turtle) {
		if(turtle=="" || !new File(turtle).exists()){
			Frames.alert("Image loader","Choose an image");
			changeTurtle();
			return getTurtle();
		}
		return turtle;
	}

	public void changeTurtle(){
		String temp=addTurtle();
		if(!(temp ==null))
			setTurtle(temp);
	}

	private String addTurtle(){
		FileNameExtensionFilter filter = new FileNameExtensionFilter( "Images", "jpg","png","gif","bmp");
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setFileFilter(filter);
		jfc.setCurrentDirectory(new File("."));
		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFolder = jfc.getSelectedFile();
			return selectedFolder.getAbsolutePath();
		}
		return null;
	}
}

enum Function{
	
	directory,
	lastfile,
	automaticSave,
	firstSave,
	turtle,
	speed,
	zoom,
	openedfile

}