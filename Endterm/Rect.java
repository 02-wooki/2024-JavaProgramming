public class Rect {
	int width;
	int height;
	String color;
	
	public Rect(int width, int height, String color) {
		this.width = width; 
		this.height = height;
		this.color = color;
	}
	
	public String toString() {
		return "Rect("+Integer.toString(width)+","+Integer.toString(height)+",\""+color+"\")";
	}
}
