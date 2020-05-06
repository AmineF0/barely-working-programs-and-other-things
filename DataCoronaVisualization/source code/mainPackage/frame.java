package mainPackage;

import countryCSV.Country;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class frame extends JFrame{
	
	/**
	 * 
	 */
	
	int highest=0 , streamPos = 1;
	//data of Coronavirus in a String[][] with important functions
	Country coronaData;
	ArrayList<country> countries;
	Date date ;
	
	frame(){
		
		//check data
		if(!new File( new File("").getAbsolutePath()+
				File.separatorChar+"data"+
				File.separatorChar+"coronavirus"+
				File.separatorChar+"sortedDataByDate.csv").exists()) {
			coronaData = new Country(new File("").getAbsolutePath()+
					File.separatorChar+"data"+
					File.separatorChar+"coronavirus"+
					File.separatorChar+"owid-covid-data.csv");
			coronaData.orderBy("date", coronaData.DATECLASS);
			coronaData.saveAsCSV(new File("").getAbsolutePath()+
					File.separatorChar+"data"+
					File.separatorChar+"coronavirus"+
					File.separatorChar+"sortedDataByDate.csv");
		}
		else
			coronaData = new Country(new File("").getAbsolutePath()+
					File.separatorChar+"data"+
					File.separatorChar+"coronavirus"+
					File.separatorChar+"sortedDataByDate.csv");
		
		System.out.println(coronaData.toString(coronaData.getData()));
		//initialize array containing blocks
		countries = new ArrayList<>();
		
		//set properties of the frame
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension dim = new Dimension(400,Toolkit.getDefaultToolkit().getScreenSize().height*8/10);
		this.setMinimumSize(dim);
		this.setTitle("CoronaVirus Total Cases By Country");
		//set date
		try {
			String str = coronaData.getInfo("date", 1);
			date=new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (ParseException e1) {
		}
		this.setVisible(true);
		pack();
		//data flow
		while(coronaData.getData().length-1>streamPos) {
			updateComponents();
			addGradient();
		}
		
	}
		
	//update content of components
	private  void updateComponents() {
		try {
			//a stream of data of the same date
			while(date.compareTo(new SimpleDateFormat("yyyy-MM-dd").parse((coronaData.getInfo("date", streamPos))))==0) {
				//check if it is new and valid
				if(mustAddToArray())
					countries.add(
						new country(
							Integer.parseInt(coronaData.getInfo("total_cases",streamPos)),
							highest,
							coronaData.getInfo("iso_code",streamPos),
							this.getWidth()));
				//next data
				streamPos++;
			}
			//update frame according to new data
			addComponents();
			date=new SimpleDateFormat("yyyy-MM-dd").parse((coronaData.getInfo("date", streamPos)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	//find components that has to be added and update highest value
	private boolean mustAddToArray() {
		//check if data is valid
		if(coronaData.getInfo("total_cases",streamPos)==null || coronaData.getInfo("total_cases",streamPos).isEmpty() ||
				coronaData.getInfo("iso_code",streamPos)==null || coronaData.getInfo("iso_code",streamPos).isEmpty())
			return false;
		//check if the block is already existing and update it if so
		for(country cn : countries) {
			if(cn.getNameCountry().equals(coronaData.getInfo("iso_code",streamPos))) {
				cn.lastValue=cn.value;
				cn.value=Integer.parseInt(coronaData.getInfo("total_cases",streamPos));
				if(cn.transitionValue>highest) {
					highest=cn.transitionValue;
				}
				return false;
			}
		}
		//not existing and valid to add block
		return true;
	}

	//add or update components
	private void addComponents() {
		getContentPane().removeAll();
		add(new JLabel("<html><h2>Total Cases Of Coronavirus By Country : "+new SimpleDateFormat("yyyy-MM-dd").format(date)+"</h2></html>"));
		for(country block : countries) {
			block.update(highest, this.getWidth());
			add(block);
		}
		revalidate();
		repaint();
		pack();
	}

	//sort the blocks in the array according the the transition value using bubble sort
	private void sortCountriesByType() {
		for(int n = 1 ; n < countries.size() ; n++) {
			for(int y = 0 ; y < countries.size()-1 ; y++) {
				if(countries.get(y).transitionValue<countries.get(y+1).transitionValue) {
					country temp = countries.get(y+1);
					countries.set(y+1, countries.get(y));
					countries.set(y, temp);
				}
			}
		}
	}
	
	//make the rectangle and number increase slowly making a transition phase
	private void addGradient() {
		int period = 40;//steps of the transition
		for(int n =0 ; n < period ; n++) {
			try {
				Thread.sleep(20);//time
				//refresh biggest value and highest
				countries.get(0).transitionValue=countries.get(0).lastValue+(int)((countries.get(0).value-countries.get(0).lastValue)*(n+1)/period);
				highest=countries.get(0).transitionValue;
				// refresh all blocks
				for(country cn : countries) {
					cn.transitionValue=cn.lastValue+(int)((cn.value-cn.lastValue)*(n+1)/period);
					cn.update(highest, this.getWidth());
				}
				//refresh
				revalidate();
				repaint();
				//sort
				sortCountriesByType();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
	}
}
