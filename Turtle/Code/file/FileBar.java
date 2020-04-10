package file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import design.Frames;
import design.Panels;
import front.home;

public class FileBar {
	
	static File selectedFile ;
	static FileNameExtensionFilter filter = new FileNameExtensionFilter( "Turtle Special Type", "TST");
	static JFileChooser jfc ;
	
	public static void fnew() {
		home.special.setLoaded(false);
		((JLabel)Frames.info).setText("Choosing...");
		String name = design.Frames.askName();
		if(name!=null) {
			Panels.addWindow(TST.Files.createTSTfile(name));
		}
	}
	
	public static void open() {
		home.special.setLoaded(false);
		((JLabel)Frames.info).setText("Opening...");
		jfc = new JFileChooser();
		jfc.setFileFilter(filter);
		jfc.setCurrentDirectory(new File(front.home.special.getReprisatory()));
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			Panels.addWindow(selectedFile);
		}
		else ((JLabel)Frames.info).setText("Loading failed");
	}
	
	public static void normal_save() {
		if(front.home.special.getFirstSave().equals("0")) {
			String title="ask";
			String text="<html>do you want your file to start saving automatically?<br><h5>hint : this is last time this box will show you can change it next time in setting</h5></html>";
			if(design.Frames.askYesNo(title,text)) {
				front.home.special.setAutoSave("YES");
				front.home.special.setFirstSave("1");
				front.MenuBar.file.notifyChange();
			}
			else {
				front.home.special.setAutoSave("NO");
				front.home.special.setFirstSave("1");
			}
		}
		TST.Files.saveTSTfile();
	}
	
	public static void stop_saving() {
		front.home.special.setAutoSave("NO");
		front.MenuBar.file.notifyChange();
	}
	
	public static void save_as() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(filter);
		jfc.setCurrentDirectory(new File(front.home.special.getReprisatory()));
		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFolder = jfc.getSelectedFile();
			if(!selectedFolder.getName().contains(".tst"))
				selectedFolder = new File(selectedFolder.getAbsolutePath()+".tst");
			System.out.println(selectedFolder.getAbsolutePath());
			store.create_file(selectedFolder.getAbsolutePath());
			TST.Files.saveasTSTfile(selectedFolder.getAbsolutePath());
		}
	}

	public static void changeDirectory() {
		((JLabel)Frames.info).setText("Choosing ...");
		store.addReprisatory(front.home.special);
	}

	public static void start_saving() {
		front.home.special.setAutoSave("YES");
		front.MenuBar.file.notifyChange();
	}

	public static void closeFile() {

		home.special.setLoaded(false);
		//front.MenuBar.file.noticeCloseLastFile();
	}
	
}
