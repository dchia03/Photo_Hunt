public class Pixel {
	
	public int xPos;
	public int yPos;
	public int value;
		
	public Pixel(int yPos, int xPos, int value) {
		setXpos(xPos);
		setYpos(yPos);
		setValue(value);
	}
	
	public void setXpos(int xPos) {
		this.xPos = xPos;
	}
	public void setYpos(int yPos) {
		this.yPos = yPos;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getXpos() {
		return xPos;
	}
	public int getYpos() {
		return yPos;
	}
	public int getValue() {
		return value;
	}

	public String toString() {
		return "(" + yPos + "," + xPos + "," + value + ")";
	}
}
