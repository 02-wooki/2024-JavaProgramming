//2024-1 자바프로그래밍 2주차 과제
//2021763040 성현욱

import java.util.Scanner;

public class HW2 {

    static int a, b, c;
    static char abCompOP = '=', bcCompOP = '=';

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("세 정수 입력: ");

        while (sc.hasNextInt()) {


            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

            abcSwap();
            SetCompOp();

            System.out.println("결과: "+ a + abCompOP + b + bcCompOP + c);
            System.out.print("세 정수 입력: ");

        }
    }
    static void abcSwap() {
        if(a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if(a > c) {
            int temp = a;
            a = c;
            c  = temp;
        }
        if(b > c) {
            int temp = b;
            b = c;
            c = temp;
        }
    }
    static void SetCompOp() {
        abCompOP = a < b ? '<': '=';
        bcCompOP = b < c ? '<': '=';
    }
}
