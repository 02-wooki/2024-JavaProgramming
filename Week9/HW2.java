import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) throws IOException {

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
        } else if (!args[1].equals("print_info") && !args[1].equals("print") && !args[1].equals("sort_area") && !args[1].equals("sort_coord")) {
            System.out.println("없는 명령어");
            System.exit(1);
        }

        double avg;
        switch (args[1]){
            case "print":

                if (args.length > 5) {
                    System.out.println("도형 종류는 3개까지만 가능");
                    System.exit(1);
                } else if (args.length < 3) {
                    System.out.println("도형 종류를 1개이상 선택하세요");
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

                break;
            case "print_info":

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

                break;
            case "sort_area":

                // 면적 최소값, 최대값, 평균 산출
                setSmallLarge(circles, ovals, triangles, rects, trapezoids);
                avg = avgArea(circles, ovals, triangles, rects, trapezoids);

                // 면적기준 정렬된 객체 생성하고 출력
                SortObj areaSorted = new SortObj(args[1], circles, ovals, triangles, rects, trapezoids);
                System.out.println(areaSorted);
                System.out.printf("평균 면적: %.2f\n", avg);

                break;
            case "sort_coord":

                // 면적 최소값, 최대값, 평균 산출
                setSmallLarge(circles, ovals, triangles, rects, trapezoids);
                avg = avgArea(circles, ovals, triangles, rects, trapezoids);

                // 좌표기준 정렬된 객체 생성하고 출력
                SortObj coordSorted = new SortObj(args[1], circles, ovals, triangles, rects, trapezoids);
                System.out.println(coordSorted);
                System.out.printf("평균 면적: %.2f\n", avg);

                break;
            default:
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

class SortObj {

    Circle[] circles;
    Oval[] ovals;
    Triangle[] triangles;
    Rectangle[] rectangles;
    Trapezoid[] trapezoids;
    int[] sortedArgs;   // 정렬된 순서 (0: 원, 1: 타원, 2: 삼각형, 3: 사각형, 4: 사다리꼴)

    public SortObj(Circle[] circles, Oval[] ovals, Triangle[] triangles, Rectangle[] rectangles, Trapezoid[] trapezoids) {
        this.circles = Arrays.copyOf(circles, circles.length);
        this.ovals = Arrays.copyOf(ovals, ovals.length);
        this.triangles = Arrays.copyOf(triangles, triangles.length);
        this.rectangles = Arrays.copyOf(rectangles, rectangles.length);
        this.trapezoids = Arrays.copyOf(trapezoids, trapezoids.length);
        sortedArgs = new int[circles.length + triangles.length + rectangles.length + trapezoids.length + ovals.length];
    }

    public SortObj(String mode, Circle[] circles, Oval[] ovals, Triangle[] triangles, Rectangle[] rectangles, Trapezoid[] trapezoids) throws IOException {
        this(circles, ovals, triangles, rectangles, trapezoids);
        if (mode.equals("sort_area")) {
            areaSort();
        } else if (mode.equals("sort_coord")) {
            coordSort();
        }
    }

    // 면적 기준으로 도형마다 각각 정렬한 후
    // sortedArgs에 최종적으로 정렬된 도형 번호를 나열함으로써 오름차순 정렬 완성
    private void areaSort() {
        // 원 내부 정렬
        for (int i = 0; i < circles.length; i++) {
            for (int j = 0; j < circles.length - i - 1; j++) {
                if (circles[j].area > circles[j + 1].area)
                    swap("원", j, j + 1);
            }
        }
        // 타원 내부 정렬
        for (int i = 0; i < ovals.length; i++) {
            for (int j = 0; j < ovals.length - i - 1; j++) {
                if (ovals[j].area > ovals[j + 1].area)
                    swap("타원", j, j + 1);
            }
        }
        // 삼각형 내부 정렬
        for (int i = 0; i < triangles.length; i++) {
            for (int j = 0; j < triangles.length - i - 1; j++) {
                if (triangles[j].area > triangles[j + 1].area)
                    swap("삼각형", j, j + 1);
            }
        }
        // 사각형 내부 정렬
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles.length - i - 1; j++) {
                if (rectangles[j].area > rectangles[j + 1].area)
                    swap("사각형", j, j + 1);
            }
        }
        // 사다리꼴 내부 정렬
        for (int i = 0; i < trapezoids.length; i++) {
            for (int j = 0; j < trapezoids.length - i - 1; j++) {
                if (trapezoids[j].area > trapezoids[j + 1].area)
                    swap("사다리꼴", j, j + 1);
            }
        }

        // 다섯 종류 도형을 모두 비교해 전체 정렬된 순서를 설정
        int srtArgsIdx = 0, cirIdx = 0, ovaIdx = 0, triIdx = 0, rctIdx = 0, traIdx = 0;
        double areaNow = 0.0;

        while (srtArgsIdx < circles.length + ovals.length + triangles.length + rectangles.length + trapezoids.length) {

            // 원
            if (cirIdx < circles.length) {
                // 원이 현재 비교 대상 중 최소 넓이를 가졌는지 검사
                boolean isThisMinValue = true;
                if (ovaIdx < ovals.length && circles[cirIdx].area > ovals[ovaIdx].area)
                    isThisMinValue = false;
                else if (triIdx < triangles.length && circles[cirIdx].area > triangles[triIdx].area)
                    isThisMinValue = false;
                else if (rctIdx < rectangles.length && circles[cirIdx].area > rectangles[rctIdx].area)
                    isThisMinValue = false;
                else if (traIdx < trapezoids.length && circles[cirIdx].area > trapezoids[traIdx].area)
                    isThisMinValue = false;
                // 이 원의 크기가 최소이면 같은 크기의 원을 모두 입력
                if (isThisMinValue) {
                    areaNow = circles[cirIdx].area;
                    while (cirIdx < circles.length && areaNow == circles[cirIdx].area) {
                        System.out.println(circles[cirIdx]);
                        sortedArgs[srtArgsIdx++] = 0;
                        cirIdx++;
                    }
                }
            }
            // 타원
            if (ovaIdx < ovals.length) {
                boolean isThisMinValue = true;
                if (cirIdx < circles.length && ovals[ovaIdx].area > circles[cirIdx].area)
                    isThisMinValue = false;
                else if (triIdx < triangles.length && ovals[ovaIdx].area > triangles[triIdx].area)
                    isThisMinValue = false;
                else if (rctIdx < rectangles.length && ovals[ovaIdx].area > rectangles[rctIdx].area)
                    isThisMinValue = false;
                else if (traIdx < trapezoids.length && ovals[ovaIdx].area > trapezoids[traIdx].area)
                    isThisMinValue = false;

                if (isThisMinValue) {
                    areaNow = ovals[ovaIdx].area;
                    while (ovaIdx < ovals.length && areaNow == ovals[ovaIdx].area) {
                        System.out.println(ovals[ovaIdx]);
                        sortedArgs[srtArgsIdx++] = 1;
                        ovaIdx++;
                    }
                }
            }
            // 삼각형
            if (triIdx < triangles.length) {
                boolean isThisMinValue = true;
                if (cirIdx < circles.length && triangles[triIdx].area > circles[cirIdx].area)
                    isThisMinValue = false;
                else if (ovaIdx < ovals.length && triangles[triIdx].area > ovals[ovaIdx].area)
                    isThisMinValue = false;
                else if (rctIdx < rectangles.length && triangles[triIdx].area > rectangles[rctIdx].area)
                    isThisMinValue = false;
                else if (traIdx < trapezoids.length && triangles[triIdx].area > trapezoids[traIdx].area)
                    isThisMinValue = false;

                if (isThisMinValue) {
                    areaNow = triangles[triIdx].area;
                    while (triIdx < triangles.length && areaNow == triangles[triIdx].area) {
                        System.out.println(triangles[triIdx]);
                        sortedArgs[srtArgsIdx++] = 2;
                        triIdx++;
                    }
                }
            }
            // 사각형
            if (rctIdx < rectangles.length) {
                boolean isThisMinValue = true;
                if (cirIdx < circles.length && rectangles[rctIdx].area > circles[cirIdx].area)
                    isThisMinValue = false;
                else if (ovaIdx < ovals.length && rectangles[rctIdx].area > ovals[ovaIdx].area)
                    isThisMinValue = false;
                else if (triIdx < triangles.length && rectangles[rctIdx].area > triangles[triIdx].area)
                    isThisMinValue = false;
                else if (traIdx < trapezoids.length && rectangles[rctIdx].area > trapezoids[traIdx].area)
                    isThisMinValue = false;

                if (isThisMinValue) {
                    areaNow = rectangles[rctIdx].area;
                    while (rctIdx < rectangles.length && areaNow == rectangles[rctIdx].area) {
                        System.out.println(rectangles[rctIdx]);
                        sortedArgs[srtArgsIdx++] = 3;
                        rctIdx++;
                    }
                }
            }
            // 사다리꼴
            if (traIdx < trapezoids.length) {
                boolean isThisMinValue = true;
                if (cirIdx < circles.length && trapezoids[traIdx].area > circles[cirIdx].area)
                    isThisMinValue = false;
                else if (ovaIdx < ovals.length && trapezoids[traIdx].area > ovals[ovaIdx].area)
                    isThisMinValue = false;
                else if (triIdx < triangles.length && trapezoids[traIdx].area > triangles[triIdx].area)
                    isThisMinValue = false;
                else if (rctIdx < rectangles.length && trapezoids[traIdx].area > rectangles[rctIdx].area)
                    isThisMinValue = false;

                if (isThisMinValue) {
                    areaNow = trapezoids[traIdx].area;
                    while (traIdx < trapezoids.length && areaNow == trapezoids[traIdx].area) {
                        System.out.println(trapezoids[traIdx]);
                        sortedArgs[srtArgsIdx++] = 4;
                        traIdx++;
                    }
                }
            }
        }
    }

    private void coordSort() {
        // 원 내부 정렬
        for (int i = 0; i < circles.length; i++) {
            for (int j = 0; j < circles.length - i - 1; j++) {
                if (circles[j].x > circles[j + 1].x)
                    swap("원", j, j + 1);
                else if (circles[j].x == circles[j + 1].x)
                    if (circles[j].y > circles[j + 1].y)
                        swap("원", j, j + 1);
            }
        }
        // 타원 내부 정렬
        for (int i = 0; i < ovals.length; i++) {
            for (int j = 0; j < ovals.length - i - 1; j++) {
                if (ovals[j].x > ovals[j + 1].x)
                    swap("타원", j, j + 1);
                else if (ovals[j].x == ovals[j + 1].x)
                    if (ovals[j].y > ovals[j + 1].y)
                        swap("타원", j, j + 1);
            }
        }
        // 삼각형 내부 정렬
        for (int i = 0; i < triangles.length; i++) {
            for (int j = 0; j < triangles.length - i - 1; j++) {
                if (triangles[j].x > triangles[j + 1].x)
                    swap("삼각형", j, j + 1);
                else if (triangles[j].x == triangles[j + 1].x)
                    if (triangles[j].y > triangles[j + 1].y)
                        swap("삼각형", j, j + 1);
            }
        }
        // 사각형 내부 정렬
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles.length - i - 1; j++) {
                if (rectangles[j].x > rectangles[j + 1].x)
                    swap("사각형", j, j + 1);
                else if (rectangles[j].x == rectangles[j + 1].x)
                    if (rectangles[j].y > rectangles[j + 1].y)
                        swap("사각형", j, j + 1);
            }
        }
        // 사다리꼴 내부 정렬
        for (int i = 0; i < trapezoids.length; i++) {
            for (int j = 0; j < trapezoids.length - i - 1; j++) {
                if (trapezoids[j].x > trapezoids[j + 1].x)
                    swap("사다리꼴", j, j + 1);
                else if (trapezoids[j].x == trapezoids[j + 1].x)
                    if (trapezoids[j].y > trapezoids[j + 1].y)
                        swap("사다리꼴", j, j + 1);
            }
        }

        // 모든 도형을 비교해 최종 순서를 결정
        int srtArgsIdx = 0, cIdx = 0, oIdx = 0, tIdx = 0, rIdx = 0, zIdx = 0;
        int nowx, nowy;

        while (srtArgsIdx < circles.length + ovals.length + triangles.length + rectangles.length + trapezoids.length) {
            // 원
            if (cIdx < circles.length) {
                boolean isThisMinValue = true;
                if (oIdx < ovals.length &&
                        (circles[cIdx].x > ovals[oIdx].x || (circles[cIdx].x == ovals[oIdx].x && circles[cIdx].y > ovals[oIdx].y)))
                    isThisMinValue = false;
                else if (tIdx < triangles.length &&
                        (circles[cIdx].x > triangles[tIdx].x || (circles[cIdx].x == ovals[oIdx].x && circles[cIdx].y > ovals[oIdx].y)))
                    isThisMinValue = false;
                else if (rIdx < rectangles.length &&
                        (circles[cIdx].x > rectangles[rIdx].x || (circles[cIdx].x == rectangles[rIdx].x && circles[cIdx].y > rectangles[rIdx].y)))
                    isThisMinValue = false;
                else if (zIdx < trapezoids.length &&
                        (circles[cIdx].x > trapezoids[zIdx].x || (circles[cIdx].x == trapezoids[zIdx].x && circles[cIdx].y > trapezoids[zIdx].y)))
                    isThisMinValue = false;

                if (isThisMinValue) {
                    nowx = circles[cIdx].x;
                    nowy = circles[cIdx].y;
                    while (cIdx < circles.length && nowx == circles[cIdx].x && nowy == circles[cIdx].y) {
                        sortedArgs[srtArgsIdx++] = 0;
                        cIdx++;
                    }
                }
            }
            // 타원
            if (oIdx < ovals.length) {
                boolean isThisMinValue = true;
                if (cIdx < circles.length &&
                        (ovals[oIdx].x > circles[cIdx].x || (ovals[oIdx].x == circles[cIdx].x && ovals[oIdx].y > circles[cIdx].y)))
                    isThisMinValue = false;
                else if (tIdx < triangles.length &&
                        (ovals[oIdx].x > triangles[tIdx].x || (ovals[oIdx].x == triangles[tIdx].x && ovals[oIdx].y > triangles[tIdx].y)))
                    isThisMinValue = false;
                else if (rIdx < rectangles.length &&
                        (ovals[oIdx].x > rectangles[rIdx].x || (ovals[oIdx].x == rectangles[rIdx].x && ovals[oIdx].y > rectangles[rIdx].y)))
                    isThisMinValue = false;
                else if (zIdx < trapezoids.length &&
                        (ovals[oIdx].x > trapezoids[zIdx].x || (ovals[oIdx].x == trapezoids[zIdx].x && ovals[oIdx].y > trapezoids[zIdx].y)))
                    isThisMinValue = false;

                if (isThisMinValue) {
                    nowx = ovals[oIdx].x;
                    nowy = ovals[oIdx].y;
                    while (oIdx < ovals.length && nowx == ovals[oIdx].x && nowy == ovals[oIdx].y) {
                        sortedArgs[srtArgsIdx++] = 1;
                        oIdx++;
                    }
                }
            }
            // 삼각형
            if (tIdx < triangles.length) {
                boolean isThisMinValue = true;
                if (cIdx < circles.length &&
                        (triangles[tIdx].x > circles[cIdx].x || (triangles[tIdx].x == circles[cIdx].x && triangles[tIdx].y > circles[cIdx].y)))
                    isThisMinValue = false;
                else if (oIdx < ovals.length &&
                        (triangles[tIdx].x > ovals[oIdx].x || (triangles[tIdx].x == ovals[oIdx].x && triangles[tIdx].y > ovals[oIdx].y)))
                    isThisMinValue = false;
                else if (rIdx < rectangles.length &&
                        (triangles[tIdx].x > rectangles[rIdx].x || (triangles[tIdx].x == rectangles[rIdx].x && triangles[tIdx].y > rectangles[rIdx].y)))
                    isThisMinValue = false;
                else if (zIdx < trapezoids.length &&
                        (triangles[tIdx].x > trapezoids[zIdx].x || (triangles[tIdx].x == trapezoids[zIdx].x && triangles[tIdx].y > trapezoids[zIdx].y)))
                    isThisMinValue = false;

                if (isThisMinValue) {
                    nowx = triangles[tIdx].x;
                    nowy = triangles[tIdx].y;
                    while (tIdx < triangles.length && nowx == triangles[tIdx].x && nowy == triangles[tIdx].y) {
                        sortedArgs[srtArgsIdx++] = 2;
                        tIdx++;
                    }
                }
            }
            // 사각형
            if (rIdx < rectangles.length) {
                boolean isThisMinValue = true;
                if (cIdx < circles.length &&
                        (rectangles[rIdx].x > circles[cIdx].x || (rectangles[rIdx].x == circles[cIdx].x && rectangles[rIdx].y > circles[cIdx].y)))
                    isThisMinValue = false;
                else if (oIdx < ovals.length &&
                        (rectangles[rIdx].x > ovals[oIdx].x || (rectangles[rIdx].x == ovals[oIdx].x && rectangles[rIdx].y > ovals[oIdx].y)))
                    isThisMinValue = false;
                else if (tIdx < triangles.length &&
                        (rectangles[rIdx].x > triangles[tIdx].x || (rectangles[rIdx].x == triangles[tIdx].x && rectangles[rIdx].y > triangles[tIdx].y)))
                    isThisMinValue = false;
                else if (zIdx < trapezoids.length &&
                        (rectangles[rIdx].x > trapezoids[zIdx].x || (rectangles[rIdx].x == trapezoids[zIdx].x && rectangles[rIdx].y > trapezoids[zIdx].y)))
                    isThisMinValue = false;

                if (isThisMinValue) {
                    nowx = rectangles[rIdx].x;
                    nowy = rectangles[rIdx].y;
                    while (rIdx < rectangles.length && nowx == rectangles[rIdx].x && nowy == rectangles[rIdx].y) {
                        sortedArgs[srtArgsIdx++] = 3;
                        rIdx++;
                    }
                }
            }
            // 사다리꼴
            if (zIdx < trapezoids.length) {
                boolean isThisMinValue = true;
                if (cIdx < circles.length &&
                        (trapezoids[zIdx].x > circles[cIdx].x || (trapezoids[zIdx].x == circles[cIdx].x && trapezoids[zIdx].y > circles[cIdx].y)))
                    isThisMinValue = false;
                else if (oIdx < ovals.length &&
                        (trapezoids[zIdx].x > ovals[oIdx].x || (trapezoids[zIdx].x == ovals[oIdx].x && trapezoids[zIdx].y > ovals[oIdx].y)))
                    isThisMinValue = false;
                else if (tIdx < triangles.length &&
                        (trapezoids[zIdx].x > triangles[tIdx].x || (trapezoids[zIdx].x == triangles[tIdx].x && trapezoids[zIdx].y > triangles[tIdx].y)))
                    isThisMinValue = false;
                else if (rIdx < rectangles.length &&
                        (trapezoids[zIdx].x > rectangles[rIdx].x || (trapezoids[zIdx].x == rectangles[rIdx].x && trapezoids[zIdx].y > rectangles[rIdx].y)))
                    isThisMinValue = false;

                if (isThisMinValue) {
                    nowx = trapezoids[zIdx].x;
                    nowy = trapezoids[zIdx].y;
                    while (zIdx < trapezoids.length && nowx == trapezoids[zIdx].x && nowy == trapezoids[zIdx].y) {
                        sortedArgs[srtArgsIdx++] = 4;
                        zIdx++;
                    }
                }
            }
        }
    }

    private void swap(String name, int i, int j) {
        switch (name) {
            case "원":
                Circle c = circles[i];
                circles[i] = circles[j];
                circles[j] = c;
                break;
            case "타원":
                Oval o = ovals[i];
                ovals[i] = ovals[j];
                ovals[j] = o;
                break;
            case "삼각형":
                Triangle t = triangles[i];
                triangles[i] = triangles[j];
                triangles[j] = t;
                break;
            case "사각형":
                Rectangle r = rectangles[i];
                rectangles[i] = rectangles[j];
                rectangles[j] = r;
                break;
            case "사다리꼴":
                Trapezoid trap = trapezoids[i];
                trapezoids[i] = trapezoids[j];
                trapezoids[j] = trap;
                break;
        }
    }

    public String toString() {
        String output = "";
        int cirIdx = 0, ovalIdx = 0, triIdx = 0, rctIdx = 0, traIdx = 0;
        for (int i : sortedArgs) {
            switch (i) {
                case 0: // 원
                    output += String.format("%s\n", circles[cirIdx++]);
                    break;
                case 1: // 타원
                    output += String.format("%s\n", ovals[ovalIdx++]);
                    break;
                case 2: // 삼각형
                    output += String.format("%s\n", triangles[triIdx++]);
                    break;
                case 3: // 사각형
                    output += String.format("%s\n", rectangles[rctIdx++]);
                    break;
                case 4: // 사다리꼴
                    output += String.format("%s\n", trapezoids[traIdx++]);
            }
        }
        return output;
    }
}