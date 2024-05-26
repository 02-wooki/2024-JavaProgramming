public class Figure {
    String name;
    int x, y;
    double area;
    boolean isSmallest, isLargest;

    public Figure() {
        name = "undefined";
        x = y = 0;
        area = 0.0;
        isSmallest = isLargest = false;
    }

    public Figure(String name, int x, int y) {
        this();
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void setLargest(boolean largest) {
        isLargest = largest;
    }

    public void setSmallest(boolean smallest) {
        isSmallest = smallest;
    }

    public String toString() {
        return String.format("%s%s%s - [x:%d, y:%d]",
                isLargest ? "L " : "", isSmallest ? "S " : "", name, x, y);
    }
}

class Circle extends Figure {

    static final double PI = 3.1415;
    int radius;

    public Circle(String name, int x, int y, int radius) {
        super(name, x, y);
        this.radius = radius;
        setArea();
    }

    private void setArea() {
        area = radius * radius * PI;
    }

    public String toString() {
        return String.format("%s 반지름[%d] %s",
                super.toString(), radius, this instanceof Oval ? "" : String.format("면적[%.2f]", area));
    }
}

class Oval extends Circle {
    int smallRadius;

    public Oval(String name, int x, int y, int radius, int smallRadius) {
        super(name, x, y, radius);
        this.smallRadius = smallRadius;
        setArea();
    }

    private void setArea() {
        area = radius * smallRadius * PI;
    }

    public String toString() {
        return String.format("%s짧은반지름[%d] 면적[%.2f]",
                super.toString(), smallRadius, area);
    }
}

class Triangle extends Figure {
    int width, height;

    public Triangle(String name, int x, int y, int width, int height) {
        super(name, x, y);
        this.width = width;
        this.height = height;
        setArea();
    }

    private void setArea() {
        area = (double)width * height / 2;
    }

    public String toString() {
        return String.format("%s %s",
                super.toString(), this instanceof Rectangle ? "" : String.format("밑변[%d] 높이[%d] 면적[%.2f]", width, height, area));
    }
}

class Rectangle extends Triangle {
    public Rectangle(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
        setArea();
    }

    private void setArea() {
        area = width * height;
    }

    public String toString() {
        return String.format("%s가로[%d] %s",
                super.toString(), width, this instanceof Trapezoid ? "" : String.format("세로[%d] 면적[%.2f]", height, area));
    }
}

class Trapezoid extends Rectangle {

    int smallWidth;
    public Trapezoid(String name, int x, int y, int width, int smallWidth, int height) {
        super(name, x, y, width, height);
        this.smallWidth = smallWidth;
        setArea();
    }

    private void setArea() {
        area = (double)(smallWidth + width) * height / 2;
    }

    public String toString() {
        return String.format("%s짧은가로[%d] 높이[%d] 면적[%.2f]",
                super.toString(), smallWidth, height, area);
    }
}