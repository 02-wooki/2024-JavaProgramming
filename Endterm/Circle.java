public class Circle {
	int radius;
	String color;
	
	public Circle(int r, String color) {
		radius = r;
		this.color = color;
	}
	
	public String toString() {
		return "Circle("+radius+",\""+color+"\")";
	}
}
