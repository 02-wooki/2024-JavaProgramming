import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HW1 {
    public static void main(String[] args) throws FileNotFoundException {

        // FileNotFoundException 처리
        if (!fileExitsts(args[0])) {
            System.out.println(args[0] + "는 없는 파일");
            System.exit(1);
        }

        // 빈 객체 배열 생성
        Circle[] circles = new Circle[0];
        Rectangle[] rects = new Rectangle[0];
        Ellipse[] ellipses = new Ellipse[0];
        Triangle[] triangles = new Triangle[0];
        Trapezoid[] trapezoids = new Trapezoid[0];

        Scanner in = new Scanner(new File(args[0]));
        while (in.hasNextLine()) {
            // 파일 한 줄씩 읽어서 해당 객체 생성
            Scanner lin = new Scanner(in.nextLine());
            String name = lin.next();
            switch (name) {
                case "원":
                    circles = Arrays.copyOf(circles, circles.length + 1);
                    circles[circles.length - 1] = new Circle(name, lin.nextInt(), lin.nextInt(), lin.nextInt());
                    break;
                case "타원":
                    ellipses = Arrays.copyOf(ellipses, ellipses.length + 1);
                    ellipses[ellipses.length - 1] = new Ellipse(name, lin.nextInt(), lin.nextInt(), lin.nextInt(), lin.nextInt());
                    break;
                case "삼각형":
                    triangles = Arrays.copyOf(triangles, triangles.length + 1);
                    triangles[triangles.length - 1] = new Triangle(name, lin.nextInt(), lin.nextInt(), lin.nextInt(), lin.nextInt());
                    break;
                case "사각형":
                    rects = Arrays.copyOf(rects, rects.length + 1);
                    rects[rects.length - 1] = new Rectangle(name, lin.nextInt(), lin.nextInt(), lin.nextInt(), lin.nextInt());
                    break;
                case "사다리꼴":
                    trapezoids = Arrays.copyOf(trapezoids, trapezoids.length + 1);
                    trapezoids[trapezoids.length - 1] = new Trapezoid(name, lin.nextInt(), lin.nextInt(), lin.nextInt(), lin.nextInt(), lin.nextInt());
                    break;
            }
        }

        // ArrayIndexOutOfBoundsException 처리
        if (args.length < 2) {
            System.out.println("명령어가 없음");
            System.exit(1);
        } else if (!args[1].equals("print_info") && !args[1].equals("print")) {
            System.out.println("없는 명령어");
            System.exit(1);
        }

        double avg;

        switch (args[1]) {
            case "print":
                // 각 도형이 명령어에 있는지 없는지 검사 + 중복 제거
                boolean circleExitsts = false, ellipseExists = false, triangleExists = false, rectExists = false, trapeExists = false;
                for (int i = 2; i < args.length; i++) {
                    switch (args[i]) {
                        case "원":
                            if (circleExitsts)
                                args[i] = "";
                            circleExitsts = true;
                            break;
                        case "타원":
                            if (ellipseExists)
                                args[i] = "";
                            ellipseExists = true;
                            break;
                        case "사각형":
                            if (rectExists)
                                args[i] = "";
                            rectExists = true;
                            break;
                        case "삼각형":
                            if (triangleExists)
                                args[i] = "";
                            triangleExists = true;
                            break;
                        case "사다리꼴":
                            if (trapeExists)
                                args[i] = "";
                            trapeExists = true;
                            break;
                        default:
                            System.out.println("도형 종류 오류");
                            System.exit(1);
                    }
                }

                // 없는 도형 제거
                if (!circleExitsts)
                    circles = new Circle[0];
                if (!ellipseExists)
                    ellipses = new Ellipse[0];
                if (!triangleExists)
                    triangles = new Triangle[0];
                if (!rectExists)
                    rects = new Rectangle[0];
                if (!trapeExists)
                    trapezoids = new Trapezoid[0];

                // 최대값, 최소값, 평균 설정
                setLargeSmall(circles, rects, ellipses, triangles, trapezoids);
                avg = averageArea(circles, rects, ellipses, triangles, trapezoids);
                // 프로그램 인자 입력 순서대로 출력
                for (int i = 2; i < args.length; i++) {
                    switch (args[i]) {
                        case "원":
                            prtObjects(circles);
                            break;
                        case "타원":
                            prtObjects(ellipses);
                            break;
                        case "사각형":
                            prtObjects(rects);
                            break;
                        case "삼각형":
                            prtObjects(triangles);
                            break;
                        case "사다리꼴":
                            prtObjects(trapezoids);
                            break;
                    }
                }
                System.out.printf("\n평균 면적: %.2f\n", avg);
                break;
            case "print_info":
                // 최대값, 최소값, 평균 설정
                setLargeSmall(circles, rects, ellipses, triangles, trapezoids);
                avg = averageArea(circles, rects, ellipses, triangles, trapezoids);
                // 파일에 기재된 순서대로 출력
                in = new Scanner(new File(args[0]));
                int cIdx = 0, rIdx = 0, eIdx = 0, triIdx = 0, trapIdx = 0;
                while (in.hasNextLine()) {
                    Scanner lin = new Scanner(in.nextLine());
                    switch (lin.next()) {
                        case "원":
                            System.out.println(circles[cIdx++]);
                            break;
                        case "타원":
                            System.out.println(ellipses[eIdx++]);
                            break;
                        case "삼각형":
                            System.out.println(triangles[triIdx++]);
                            break;
                        case "사각형":
                            System.out.println(rects[rIdx++]);
                            break;
                        case "사다리꼴":
                            System.out.println(trapezoids[trapIdx++]);
                            break;
                    }
                }
                System.out.printf("\n평균 면적: %.2f\n", avg);
                break;
            default:
                System.out.println("없는 명령어");
                System.exit(1);
                break;
        }
    }

    static boolean fileExitsts(String filename) {
        boolean b = true;
        try {
            Scanner in = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            b = false;
        }
        return b;
    }

    static void prtObjects(Circle[] circles) {
        for (Circle c : circles)
            System.out.println(c);
    }
    static void prtObjects(Ellipse[] ellipses) {
        for (Ellipse e : ellipses)
            System.out.println(e);
    }
    static void prtObjects(Rectangle[] rects) {
        for (Rectangle r : rects)
            System.out.println(r);
    }
    static void prtObjects(Triangle[] tris) {
        for (Triangle r : tris)
            System.out.println(r);
    }
    static void prtObjects(Trapezoid[] traps) {
        for (Trapezoid t : traps)
            System.out.println(t);
    }

    static void setLargeSmall(Circle[] circles, Rectangle[] rects, Ellipse[] ellipses, Triangle[] triangles, Trapezoid[] trapes) {
        double maxArea = -1, minArea = Double.MAX_VALUE;

        // 모든 도형에 대해 넓이 최대값과 최소값 검사
        for (Circle i : circles) {
            if (i.area > maxArea)
                maxArea = i.area;
            else if (i.area < minArea)
                minArea = i.area;
        }
        for (Ellipse i : ellipses) {
            if (i.area > maxArea)
                maxArea = i.area;
            else if (i.area < minArea)
                minArea = i.area;
        }
        for (Rectangle i : rects) {
            if (i.area > maxArea)
                maxArea = i.area;
            else if (i.area < minArea)
                minArea = i.area;
        }for (Triangle i : triangles) {
            if (i.area > maxArea)
                maxArea = i.area;
            else if (i.area < minArea)
                minArea = i.area;
        }for (Trapezoid i : trapes) {
            if (i.area > maxArea)
                maxArea = i.area;
            else if (i.area < minArea)
                minArea = i.area;
        }

        // 최대값 혹은 최소값을 가진 도형의 isLargest, isSmallest 멤버에 값 대입
        for (Circle i : circles) {
            if (i.area == maxArea)
                i.setLargest(true);
            else if (i.area == minArea)
                i.setSmallest(true);
        }
        for (Ellipse i : ellipses) {
            if (i.area == maxArea)
                i.setLargest(true);
            else if (i.area == minArea)
                i.setSmallest(true);
        }
        for (Rectangle i : rects) {
            if (i.area == maxArea)
                i.setLargest(true);
            else if (i.area == minArea)
                i.setSmallest(true);
        }
        for (Triangle i : triangles) {
            if (i.area == maxArea)
                i.setLargest(true);
            else if (i.area == minArea)
                i.setSmallest(true);
        }
        for (Trapezoid i : trapes) {
            if (i.area == maxArea)
                i.setLargest(true);
            else if (i.area == minArea)
                i.setSmallest(true);
        }
    }

    static double averageArea(Circle[] c, Rectangle[] r, Ellipse[] e, Triangle[] tri, Trapezoid[] trap) {
        double[] objAvgs = new double[5];

        // 원 평균
        double sum = 0.0;
        if (c.length == 0)
            objAvgs[0] = 0.0;
        else {
            for (Circle i : c)
                sum += i.area;
            objAvgs[0] = sum / c.length;
        }

        // 사각형 평균
        sum = 0.0;
        if (r.length == 0)
            objAvgs[1] = 0.0;
        else {
            for (Rectangle i : r)
                sum += i.area;
            objAvgs[1] = sum / r.length;
        }

        // 타원 평균
        sum = 0.0;
        if (e.length == 0)
            objAvgs[2] = 0.0;
        else {
            for (Ellipse i : e)
                sum += i.area;
            objAvgs[2] = sum / e.length;
        }

        // 삼각형 평균
        sum = 0.0;
        if (tri.length == 0)
            objAvgs[3] = 0.0;
        else {
            for (Triangle i : tri)
                sum += i.area;
            objAvgs[3] = sum / tri.length;
        }

        // 사다리꼴 평균
        sum = 0.0;
        if (trap.length == 0)
            objAvgs[4] = 0.0;
        else {
            for (Trapezoid i : trap)
                sum += i.area;
            objAvgs[4] = sum / trap.length;
        }

        // 전체 평균
        return (objAvgs[0] + objAvgs[1] + objAvgs[2] + objAvgs[3] + objAvgs[4]) / 5;
    }
}

class Circle {

    final static double PI = 3.1415;
    String name;
    int x, y, radius;
    double area;
    boolean isLargest, isSmallest;

    public Circle() {
        name = "undefined";
        x = y = radius = 0;
        isLargest = isSmallest = false;
    }
    public Circle(String name, int x, int y, int radius) {
        this();
        this.name = name;
        this.x = x;
        this.y = y;
        this.radius = radius;
        setArea();
    }

    private void setArea() {
        this.area = PI * radius * radius;
    }

    public void setLargest(boolean b) {
        this.isLargest = b;
    }
    public void setSmallest(boolean b) {
        this.isSmallest = b;
    }

    public String toString() {
        return String.format("%s%s%s-[X:%d, Y:%d] 반지름[%d] 면적[%.2f]",
                isLargest ? "L " : "", isSmallest ? "S " : "", name, x, y, radius, area);
    }
}

class Ellipse {

    final static double PI = 3.1415;
    String name;
    int x, y, longRadius, shortRadius;
    double area;
    boolean isLargest, isSmallest;

    public Ellipse() {
        name = "undefined";
        x = y = longRadius = shortRadius;
        isLargest = isSmallest = false;
    }
    public Ellipse(String name, int x, int y, int longRadius, int shortRadius) {
        this();
        this.name = name;
        this.x = x;
        this.y = y;
        this.longRadius = longRadius;
        this.shortRadius = shortRadius;
        setArea();
    }

    private void setArea() {
        area = (double) shortRadius * longRadius * PI;
    }

    public void setSmallest(boolean b) {
        this.isSmallest = b;
    }

    public void setLargest(boolean b) {
        this.isLargest = b;
    }

    public String toString() {
        return String.format("%s%s%s-[X:%d, Y:%d] 반지름[%d] 짧은반지름[%d] 면적[%.2f]",
                isLargest ? "L " : "", isSmallest ? "S " : "", name, x, y, longRadius, shortRadius, area);
    }
}

class Rectangle {

    String name;
    int x, y, height, bottom;
    double area;
    boolean isLargest, isSmallest;

    public Rectangle() {
        name = "undefined";
        x = y = height = bottom = 0;
        isLargest = isSmallest = false;
    }
    public Rectangle(String name, int x, int y, int bottom, int height) {
        this();
        this.name = name;
        this.x = x;
        this.y = y;
        this.bottom = bottom;
        this.height = height;
        setArea();
    }

    private void setArea() {
        area = (double) height * bottom;
    }

    public void setSmallest(boolean b) {
        this.isSmallest = b;
    }
    public void setLargest(boolean b) {
        this.isLargest = b;
    }

    public String toString() {
        return String.format("%s%s%s-[X:%d, Y:%d] 가로[%d] 세로[%d] 면적[%.2f]",
                isLargest ? "L " : "", isSmallest ? "S " : "", name, x, y, bottom, height, area);
    }
}

class Triangle {

    String name;
    int x, y, bottom, height;
    double area;
    boolean isSmallest, isLargest;

    public Triangle() {
        name = "undefined";
        x = y = bottom = height = 0;
        isSmallest = isLargest = false;
    }
    public Triangle(String name, int x, int y, int bottom, int height) {
        this();
        this.name = name;
        this.x = x;
        this.y = y;
        this.bottom = bottom;
        this.height = height;
        setArea();
    }

    private void setArea() {
        area = (double) bottom * height / 2;
    }

    public void setLargest(boolean b) {
        isLargest = b;
    }
    public void setSmallest(boolean b) {
        isSmallest = b;
    }

    public String toString() {
        return String.format("%s%s%s-[X:%d, Y:%d] 밑변[%d] 높이[%d] 면적[%.2f]",
                isLargest ? "L " : "", isSmallest ? "S " : "", name, x, y, bottom, height, area);
    }
}

class Trapezoid {

    String name;
    int x, y, longWidth, shortWidth, height;
    double area;
    boolean isLargest, isSmallest;

    public Trapezoid() {
        name = "undefined";
        x = y = longWidth = shortWidth = height = 0;
        isLargest = isSmallest = false;
    }
    public Trapezoid(String name, int x, int y, int longWidth, int shortWidth, int height) {
        this();
        this.name = name;
        this.x = x;
        this.y = y;
        this.longWidth = longWidth;
        this.shortWidth = shortWidth;
        this.height = height;
        setArea();
    }

    private void setArea() {
        area = (double)(longWidth + shortWidth) * height / 2;
    }

    public void setLargest(boolean b) {
        isLargest = b;
    }
    public void setSmallest(boolean b) {
        isSmallest = b;
    }

    public String toString() {
        return String.format("%s%s%s-[X:%d, Y:%d] 가로[%d] 짧은가로[%d] 높이[%d] 면적[%.2f]",
                isLargest ? "L " : "", isSmallest ? "S " : "", name, x, y, longWidth, shortWidth, height, area);
    }
}