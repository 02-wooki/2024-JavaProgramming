//2024-1 자바프로그래밍 2주차 과제
//2021763040 성현욱

import java.util.Scanner;

public class HW1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int i = 0, sum = 0, max = 0, min = 0;

        while (sc.hasNextInt()) {
            int num = sc.nextInt();

            if(i == 0) {
                max = num;
                min = num;
            } else {
                if (max < num) {
                    max = num;
                }
                if (min > num) {
                    min = num;
                }
            }

            i++;
            sum += num;

        }

        if (i > 0) {
            System.out.println("총 입력 정수: " + i);
            System.out.print("총합: " + sum);
            System.out.printf(", 평균: %.3f", (double)sum/i);
            System.out.println("\n가장 큰 수: " + max);
            System.out.println("가장 작은 수: " + min);
        }
    }
}
