package task.week3;

import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) {

        // 한줄 전체를 입력받는 스캐너
        Scanner scLine = new Scanner(System.in);
        int currentLine = 1;
        int iMaxCount = -1, rMaxCount = -1, sMaxCount = -1;
        double iMaxAvg = -1.0, rMaxAvg = -1.0, sMaxLenAvg = -1.0;
        int iMaxLine = 0, rMaxLine = 0, sMaxLine = 0;

        while (scLine.hasNextLine()) {

            // 한줄째로 변수에 집어넣고 // 그 변수를 받는 스캐너 생성
            String thisLine = scLine.nextLine();
            Scanner sc = new Scanner(thisLine);

            // 변수선언 (갯수, 합, 평균)
            int iCount = 0, rCount = 0, sCount = 0, iSum = 0, sLenSum = 0;
            double rSum = 0.0, iAvg, rAvg, sLenAvg;
            String lineOutput = String.format("%d: ", currentLine);

            // 한줄 내의 정수, 실수, 문자열 각각의 갯수와 합 계산
            while (sc.hasNext()) {
                if (sc.hasNextInt()) {
                    int next = sc.nextInt();
                    iCount++;   iSum += next;
                } else if (sc.hasNextDouble()) {
                    double next = sc.nextDouble();
                    rCount++;   rSum += next;
                } else if (sc.hasNext()) {
                    String next = sc.next();
                    sCount++;   sLenSum += next.length();
                }
            }

            // 정수, 실수, 문자열 각각 평균값 산출
            // 동시에 현재 줄의 평균이 평균 최고치보다 크다면 최고치 교체
            if (iCount != 0) {
                iAvg = (double)iSum / iCount;
                lineOutput += String.format("정수(%d개) %.3f", iCount, iAvg);
                if (iMaxAvg < iAvg) {
                    iMaxAvg = iAvg;
                    iMaxCount = iCount;
                    iMaxLine = currentLine;
                }
            }

            // 정수와 실수 둘 다 존재할때만 ", "
            lineOutput += iCount * rCount != 0 ? ", " : "";

            if (rCount != 0) {
                rAvg = rSum / rCount;
                lineOutput += String.format("실수(%d개) %.3f", rCount, rAvg);
                if (rMaxAvg < rAvg) {
                    rMaxAvg = rAvg;
                    rMaxCount = rCount;
                    rMaxLine = currentLine;
                }
            }

            // (정수 존재 OR 실수 존재) AND 문자열 존재 -> ", "
            lineOutput += (iCount + rCount) * sCount != 0 ? ", " : "";

            if (sCount != 0) {
                sLenAvg = (double)sLenSum / sCount;
                lineOutput += String.format("문자열(%d개) %.3f", sCount, sLenAvg);
                if (sMaxLenAvg < sLenAvg) {
                    sMaxLenAvg = sLenAvg;
                    sMaxCount = sCount;
                    sMaxLine = currentLine;
                }
            }

            // 해당 라인의 계산된 값 출력
            System.out.println(lineOutput);

            currentLine++;

        }

        System.out.printf("\n정수 평균 최고: %d번줄 %.3f (%d개)\n", iMaxLine, iMaxAvg, iMaxCount);
        System.out.printf("실수 평균 최고: %d번줄 %.3f (%d개)\n", rMaxLine, rMaxAvg, rMaxCount);
        System.out.printf("문자열 길이 평균 최고: %d번줄 %.3f (%d개)", sMaxLine, sMaxLenAvg, sMaxCount);

    }

}
