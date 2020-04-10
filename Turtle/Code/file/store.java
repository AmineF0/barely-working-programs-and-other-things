package file;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import design.Frames;

public class store {
	
	public static File choose_directory() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setCurrentDirectory(new File("."));
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile();
		}
		else 
			return null;
	}
	
	public static File create_file(String path) {
		if(path==null) return null;
		File dataFile = new File(path);
		if(dataFile.exists()) {
			if (Frames.askYesNo("File exists", "Do you want to overwrite it?")) {
				dataFile.delete();
				try {
					dataFile.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		else {
			try {
				dataFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return dataFile;
	}
	
	public static String create_folder(File file) {
		if(file==null) {
			JOptionPane.showMessageDialog(null,
			    "Creation Directory Failed!",
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else {
			file.mkdir();
			return file.getAbsolutePath();
		}
	}

	public static void addReprisatory(Data special) {
			String text=
					 "Would you like to choose a special repisatory?\n"
					 + "if you press no an automatic one will be chosen" ;
			String title= "Make A Repisatory";
			 if(design.Frames.askYesNo(title , text)) {
				 String pth=create_folder(choose_directory());
				 if(pth!=null)
				 special.setReprisatory(pth);
				 }
			 else special.setReprisatory(create_folder(new File("Reprisatory")));
		
	}

	public static void checkReprisatory(Data special) {
		File test = new File(special.getReprisatory());
		if(!test.exists()) {
			JOptionPane.showMessageDialog(null,
				    "Directory Not Found!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			addReprisatory(special);
		}
	}

}
