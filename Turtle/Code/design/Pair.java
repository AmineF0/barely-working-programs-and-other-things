package design;

public class Pair {

	private Integer num;
	private Character charact;

	Pair(int num,char charact){
		this.num=num;
		this.charact=charact;
	}

	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Character getCharact() {
		return charact;
	}
	public void setCharact(Character charact) {
		this.charact = charact;
	}
}
