
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
        Oval[] ovals = new Oval[0];
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
                    ovals = Arrays.copyOf(ovals, ovals.length + 1);
                    ovals[ovals.length - 1] = new Oval(name, lin.nextInt(), lin.nextInt(), lin.nextInt(), lin.nextInt());
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
        if (args[1].equals("print")) {

            if (args.length < 3) {
                System.out.println("도형을 선택하세요");
                System.exit(1);
            }

            // 프로그램 인자로 제시된 도형이 모두 무엇인지 파악
            boolean circleExists = false, ovalExists = false, rectExists = false, triExists = false, trapExists = false;
            for (int i = 2; i < args.length; i++) {
                switch (args[i]) {
                    case "원":
                        if (circleExists)
                            args[i] = "";
                        circleExists = true;
                        break;
                    case "타원":
                        if (ovalExists)
                            args[i] = "";
                        ovalExists = true;
                        break;
                    case "삼각형":
                        if (triExists)
                            args[i] = "";
                        triExists = true;
                        break;
                    case "사각형":
                        if (rectExists)
                            args[i] = "";
                        rectExists = true;
                        break;
                    case "사다리꼴":
                        if (trapExists)
                            args[i] = "";
                        trapExists = true;
                        break;
                    default:
                        System.out.println("도형 종류 오류");
                        System.exit(1);
                }
            }

            // 없는 도형 제거
            if (!circleExists)
                circles = new Circle[0];
            if (!ovalExists)
                ovals = new Oval[0];
            if (!triExists)
                triangles = new Triangle[0];
            if (!rectExists)
                rects = new Rectangle[0];
            if (!trapExists)
                trapezoids = new Trapezoid[0];

            // 최소값, 최대값, 평균 산출
            setSmallLarge(circles, ovals, triangles, rects, trapezoids);
            avg = avgArea(circles, ovals, triangles, rects, trapezoids);

            // 출력
            for (int i = 2; i < args.length; i++) {
                switch (args[i]) {
                    case "원":
                        for (Circle c : circles)
                            System.out.println(c);
                        break;
                    case "타원":
                        for (Oval o : ovals)
                            System.out.println(o);
                        break;
                    case "사각형":
                        for (Rectangle r : rects)
                            System.out.println(r);
                        break;
                    case "삼각형":
                        for (Triangle t : triangles)
                            System.out.println(t);
                        break;
                    case "사다리꼴":
                        for (Trapezoid t : trapezoids)
                            System.out.println(t);
                        break;
                }
            }
            System.out.println();
            System.out.printf("평균 면적: %.2f\n", avg);
        } else if (args[1].equals("print_info")) {

            // 최소값, 최대값, 평균 산출
            setSmallLarge(circles, ovals, triangles, rects, trapezoids);
            avg = avgArea(circles, ovals, triangles, rects, trapezoids);

            // 파일에 있는 도형순서대로 출력
            in = new Scanner(new File(args[0]));
            int cIdx = 0, oIdx = 0, triIdx = 0, rIdx = 0, trapIdx = 0;
            while (in.hasNextLine()) {
                Scanner lin = new Scanner(in.nextLine());
                switch (lin.next()) {
                    case "원":
                        System.out.println(circles[cIdx++]);
                        break;
                    case "타원":
                        System.out.println(ovals[oIdx++]);
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
            System.out.println();
            System.out.printf("평균 면적: %.2f\n", avg);
        } else {
            System.out.println("없는 명령어");
            System.exit(1);
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

    static void setSmallLarge(Circle[] circles, Oval[] ovals, Triangle[] triangles, Rectangle[] rects, Trapezoid[] trapes) {

        // 최대, 최소값 구하기
        double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        for (Circle i : circles) {
            if (i.area > max)
                max = i.area;
            if (i.area < min)
                min = i.area;
        }
        for (Oval i : ovals) {
            if (i.area > max)
                max = i.area;
            if (i.area < min)
                min = i.area;
        }
        for (Triangle i : triangles) {
            if (i.area > max)
                max = i.area;
            if (i.area < min)
                min = i.area;
        }
        for (Rectangle i : rects) {
            if (i.area > max)
                max = i.area;
            if (i.area < min)
                min = i.area;
        }
        for (Trapezoid i : trapes) {
            if (i.area > max)
                max = i.area;
            if (i.area < min)
                min = i.area;
        }

        // 최대, 최소 크기를 가진 객체 세팅
        for (Circle i : circles) {
            if (i.area == max)
                i.isLargest = true;
            if (i.area == min)
                i.isSmallest = true;
        }
        for (Oval i : ovals) {
            if (i.area == max)
                i.isLargest = true;
            if (i.area == min)
                i.isSmallest = true;
        }
        for (Triangle i : triangles) {
            if (i.area == max)
                i.isLargest = true;
            if (i.area == min)
                i.isSmallest = true;
        }
        for (Rectangle i : rects) {
            if (i.area == max)
                i.isLargest = true;
            if (i.area == min)
                i.isSmallest = true;
        }
        for (Trapezoid i : trapes) {
            if (i.area == max)
                i.isLargest = true;
            if (i.area == min)
                i.isSmallest = true;
        }
    }

    static double avgArea(Circle[] c, Oval[] o, Triangle[] tri, Rectangle[] r, Trapezoid[] trap){
        double avgs = 0.0;

        for (Circle i : c)
            avgs += i.area;
        for (Oval i : o)
            avgs += i.area;
        for (Triangle i : tri)
            avgs += i.area;
        for (Rectangle i : r)
            avgs += i.area;
        for (Trapezoid i : trap)
            avgs += i.area;

        return avgs/(double)(c.length + o.length + tri.length + r.length + trap.length);
    }
}