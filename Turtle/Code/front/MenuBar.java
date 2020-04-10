package front;

import design.AboutUs;
import design.DataFileFrame;
import design.FunctionIntro;
import file.Data;
import file.FileBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public enum MenuBar {

	file("File"),
	view("View"),
	properties("Properties"),
	help("Help");
	
	private JMenu bar;
	
	MenuBar(String text){
		this.setBar(this.setBarItem(new JMenu(text)));
	}

	private JMenu setBarItem(JMenu menu) {
		ArrayList<JMenuItem> sUb= createArrayListfile();
		for(int n =0; n<sUb.size();n++) {
			menu.add(sUb.get(n));
		}
		return menu;
	}

	private ArrayList<JMenuItem> createArrayListfile() {
		ArrayList<JMenuItem> file = new ArrayList<JMenuItem>();
		if(name().equals("file"))
			for(File sub : File.values()) {
				if(sub.isActive())
					file.add(sub.getId());
			}
		if(name().equals("view"))
			for(View sub : View.values()) {
				if(sub.isActive())
					file.add(sub.getId());
			}
		if(name().equals("help"))
			for(Help sub : Help.values()) {
				if(sub.isActive())
					file.add(sub.getId());
			}
		if(name().equals("properties"))
			for(Properties sub : Properties.values()) {
				if(sub.isActive())
					file.add(sub.getId());
			}
		return file;
	}

	public JMenu getBar() {
		return bar;
	}

	public void setBar(JMenu bar) {
		this.bar = bar;
	}

	public void notifyChange() {
		File.stop_saving.changeActive();
		File.save.changeActive();
		File.start_saving.setActive(File.save.isActive());
		Change();
	}
	
	public void Change() {
		JMenu temp=file.getBar();
		temp.removeAll();
		file.setBar(temp);
		file.setBar(setBarItem(file.getBar()));
	}

/*	public void noticeOpenLastFile() {
		File.closeFile.setActive(true);
		Change();
	}

	public void noticeCloseLastFile() {
		File.closeFile.setActive(false);
		Change();
	}*/

	
}

enum File implements ActionListener{
	
	fnew("New",true),
	open("Open",true),
	save("Save",getDataFaultSave()),
	stop_saving("Stop saving automatically", getDataFaultStop()),
	start_saving("Start saving automatically",getDataFaultStart()),
	save_as("Save as",true),
	//closeFile("Close File",checkFileOpen()),
	directory("Change reprisatory",true);
	
	private JMenuItem id;
	private boolean active;

	File(String text, boolean actif){
		this.setActive(actif);
		this.setId(new JMenuItem(text));
		this.getId().addActionListener(this);
	}

	private static boolean checkFileOpen() {
        return !home.special.getLastFile().equals("");
	}

	private static boolean getDataFaultStart() {
        return !home.special.getFirstSave().equals("0") && !home.special.getAutoSave().equals("YES");
	}
	
	private static boolean getDataFaultStop() {
        return !home.special.getFirstSave().equals("0") && !home.special.getAutoSave().equals("NO");
	}
	
	private static boolean getDataFaultSave() {
        return !home.special.getAutoSave().equals("YES");
	}
	
	public JMenuItem getId() {
		return id;
	}

	public void setId(JMenuItem id) {
		this.id = id;
	}

	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==fnew.getId()) FileBar.fnew();
		if(arg0.getSource()==open.getId()) FileBar.open();
		if(arg0.getSource()==save.getId()) FileBar.normal_save();
		if(arg0.getSource()==save_as.getId()) FileBar.save_as();
		if(arg0.getSource()==stop_saving.getId()) FileBar.stop_saving();
		if(arg0.getSource()==start_saving.getId()) FileBar.start_saving();
		if(arg0.getSource()==directory.getId()) FileBar.changeDirectory();
		//if(arg0.getSource()==closeFile.getId()) FileBar.closeFile();
		
	}

	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void changeActive() {
		this.active = !this.isActive();
	}
}

enum View implements ActionListener{

	turtle("Change Turtle shape",true);

	private JMenuItem id;
	private boolean active;

	View(String text, boolean actif){
		this.setActive(actif);
		this.setId(new JMenuItem(text));
		this.getId().addActionListener(this);
	}


	public JMenuItem getId() {
		return id;
	}

	public void setId(JMenuItem id) {
		this.id = id;
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==turtle.getId()) home.special.changeTurtle();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void changeActive() {
		this.active = !this.isActive();
	}
}

enum Help implements ActionListener{

	code("Code Instructions",true);

	private JMenuItem id;
	private boolean active;

	Help(String text, boolean actif){
		this.setActive(actif);
		this.setId(new JMenuItem(text));
		this.getId().addActionListener(this);
	}


	public JMenuItem getId() {
		return id;
	}

	public void setId(JMenuItem id) {
		this.id = id;
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==code.getId()) new FunctionIntro();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void changeActive() {
		this.active = !this.isActive();
	}
}

enum Properties implements ActionListener{

	datafile("Data in the app",true),
	aboutUs("About Us", true);

	private JMenuItem id;
	private boolean active;

	Properties(String text, boolean actif){
		this.setActive(actif);
		this.setId(new JMenuItem(text));
		this.getId().addActionListener(this);
	}


	public JMenuItem getId() {
		return id;
	}

	public void setId(JMenuItem id) {
		this.id = id;
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==datafile.getId()) new DataFileFrame();
		if(arg0.getSource()==aboutUs.getId()) new AboutUs();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void changeActive() {
		this.active = !this.isActive();
	}
}