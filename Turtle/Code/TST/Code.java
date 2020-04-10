package TST;

public enum Code {
	walk("int","move turtle with an n step"),
	rotate("int","rotate turtle to change direction"),
	penin("","activate drawing turtle path"),
	penoff("","deactivate drawing turtle path"),
	begin("",""),
	end("",""),
	repeat("int","make a loop that repeats n times"),
	endrepeat("","mark the end of a loop , if it's not written the end of the file will be considered as endrepeat"),
	load("String","load a file , it must be existing in repisatory"),
	setspeed( "int","set the speed of the turtle , it is in step/milliseconds"),
	setzoom( "int","set the zoom percentage")

			;

	private String explanation;
	private String type;

	Code(String type , String explain)
	{
		this.explanation=explain;
		this.type=type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExplanation() {
		return explanation;
	}
}
