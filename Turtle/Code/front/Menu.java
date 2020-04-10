package front;

import javax.swing.JMenuBar;

public class Menu extends JMenuBar{

	private static final long serialVersionUID = 1L;

	public Menu() {
		for(MenuBar sub : MenuBar.values()) {
			this.add(sub.getBar());
		}
	}

}