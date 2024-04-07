// 2021763040 성현욱
// 자바프로그래밍 5주차 과제

public class HW1 {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        // 문제에서 주어진 값 이외의 값들에 대해 예외처리
        if (n <= 0 || n > 50) {
            System.out.println("크기 오류");
        } else {
            printOX(n);
        }
    }

    public static void printOX(int n) {
        // n이 2일때 출력은 규칙과 달라 예외처리
        if (n == 2) {
            System.out.println("o-");
            System.out.println("-o");
        } else {

            // 절반까지 출력
            for (int i = 0; i < n / 2; i++) {
                String ln = "";

                // 앞에서 i번째와 뒤에서 i번째 자리에만 o 출력
                for (int j = 0; j < n; j++) {
                    if (j == i || j == n - 1 - i)
                        ln += "o";
                    else
                        ln += "-";
                }
                System.out.println(ln);
            }

            // 절반부터 출력
            for (int i = n / 2; i < n; i++) {
                String ln = "";
                for (int j = 0; j < n; j++) {
                    if (j == i || j == n - 1 - i)
                        ln += "o";
                    else
                        ln += "-";
                }
                System.out.println(ln);
            }
        }
    }
}
