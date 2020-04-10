package front;

import design.DesignPanel;
import file.Data;
import file.store;

public class home {
	
	public static Data special;

	public static void main(String[] args) {

		Data.checkDataFile();
		
		special = new Data();
		if(special.getReprisatory().isEmpty())
			store.addReprisatory(special);
		else
			store.checkReprisatory(special);
		
		design.Frames.setHome();
	}

}
