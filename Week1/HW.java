// 2024-1 자바프로그래밍 1주차 과제
// 2021763040 성현욱

import java.util.Calendar;
import java.util.Scanner;

public class HW {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calendar cl = Calendar.getInstance();

        String name;
        int birthYear, birthMonth, birthDay;
        int yoonsAge;
        String addressSi, addressGu, addressDong;
        String univ, major;
        int admission, jjam;

        System.out.print("이름: ");
        name = sc.nextLine();
        System.out.print("생년월일: ");
        birthYear = sc.nextInt();
        birthMonth = sc.nextInt();
        birthDay = sc.nextInt();
        sc.nextLine(); // 버퍼에 남은 개행문자 버리기
        System.out.println("주소");
        System.out.print("- 시: ");
        addressSi = sc.nextLine();
        System.out.print("- 구: ");
        addressGu = sc.nextLine();
        System.out.print("- 동: ");
        addressDong = sc.nextLine();
        System.out.print("대학교: ");
        univ = sc.nextLine();
        System.out.print("학과: ");
        major = sc.nextLine();
        System.out.print("입학년도: ");
        admission = sc.nextInt();

        sc.close();

        jjam = cl.get(Calendar.YEAR) - admission + 1;
        yoonsAge = yoonsAgeCalc(birthYear, birthMonth, birthDay);

        System.out.printf("%s(만 %d세) 학생은 %s %s에 %d학년으로 재학 중이다.\n", name, yoonsAge, univ, major, jjam);
        System.out.printf("\"%s %s %s\"에 거주 중이다.\n", addressSi, addressGu, addressDong);

    }

    static int yoonsAgeCalc(int year, int month, int day) {

        Calendar cl = Calendar.getInstance();

        int manAge = cl.get(Calendar.YEAR) - year;

        if (cl.get(Calendar.MONTH) + 1 < month) {
            manAge -= 1;
        } else if (cl.get(Calendar.MONTH) + 1 == month && cl.get(Calendar.DATE) < day) {
            manAge -= 1;
        }

        return manAge;

    }
}
