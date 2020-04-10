package design;

import java.util.ArrayList;

public class MyArrayList{

	private ArrayList<ArrayList<Pair>> arrayArray;
	private ArrayList<Character> arrayChar ;
	private ArrayList<String> arrayFunc ;


	MyArrayList(){
		setArrayChar(new ArrayList<Character>());
		setArrayFunc(new ArrayList<String>());
		setArrayArray(new ArrayList<ArrayList<Pair>>());
	}

	public ArrayList<Character> getArrayChar() {
		return arrayChar;
	}

	public void setArrayChar(ArrayList<Character> arrayChar) {
		this.arrayChar = arrayChar;
	}

	public ArrayList<String> getArrayFunc() {
		return arrayFunc;
	}

	public void setArrayFunc(ArrayList<String> arrayFunc) {
		this.arrayFunc = arrayFunc;
	}

	public void AddChangeT(String text) {
		getArrayChar().removeAll(arrayChar);
		getArrayArray().removeAll(arrayArray);
		for(int n=0 ; n<text.length();n++) {
			getArrayChar().add(n, text.charAt(n));
		}
		getInterval();
	}

	private void getInterval() {

		arrayArray.add(0,new ArrayList<Pair>());
		for(int n=arrayChar.size()-1;n>=0;n--) {
			if(arrayChar.get(n)=='\n') arrayArray.add(0,new ArrayList<Pair>());
			else if(arrayChar.get(n)=='\r');
			else if(arrayChar.get(n)==' ');
			else arrayArray.get(0).add(0,new Pair(n,Character.toLowerCase(arrayChar.get(n))));
		}
		setFunc();
	}

	private void setFunc() {
		arrayFunc.removeAll(arrayFunc);
		boolean valid=true;
		for(int n=0;n<arrayArray.size();n++) {
			arrayFunc.add("");
			for(TST.Code code : TST.Code.values()) {
				int y ;	String function = "" ;
				valid= arrayArray.get(n).size() >= code.name().length();
				for(y=0;valid&&y<arrayArray.get(n).size()&&y<code.name().length();y++) {
					if(code.name().charAt(y)!=arrayArray.get(n).get(y).getCharact()) valid=false;
				}
				if(valid)function=code.name();
				for(;valid&&y<arrayArray.get(n).size()&&!code.getType().equals("");y++) {
					if((code.getType().equals("String"))
							|| (code.getType().equals("int") && Character.isDigit(arrayArray.get(n).get(y).getCharact()))){
						function += arrayArray.get(n).get(y).getCharact();
					}
					else valid = false ;

				}
				if(arrayArray.get(n).size()==0 && n!=0) continue;
				if(valid) arrayFunc.set(n, function);
				else if(arrayFunc.get(n).equals("")){arrayFunc.set(n, "scratch");}
			}
		}
	}

	public ArrayList<ArrayList<Pair>> getArrayArray() {
		return arrayArray;
	}

	public void setArrayArray(ArrayList<ArrayList<Pair>> arrayArray) {
		this.arrayArray = arrayArray;
	}

	public boolean CheckArrayFunc() {
		if(arrayFunc.size()==0) {
			Frames.alert("compiler","code is empty");
			return false;
		}
		for (String s : arrayFunc)
			if(s.equals("scratch")) {
				Frames.alert("compiler", "code unable to be interpreted");
				return false;
			}
		return true;
	}
}
