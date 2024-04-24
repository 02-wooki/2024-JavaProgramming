public class HW6 {
    public static void main(String[] args) {

        //인자개수 예외처리
        if (args.length != 2) {
            System.out.println("프로그램 인자 개수 오류");
            System.exit(1);
        }

        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        // 예외처리
        if (m <= 0 || n <= 0) {
            System.out.println("매개변수의 범위가 잘못됨");
            System.exit(1);
        } else if (m % 2 == 0 || n % 2 == 0) {
            System.out.println("매개변수가 짝수임");
            System.exit(1);
        } else if (m < n) {
            System.out.println("두 번째 정수가 더 큼");
            System.exit(1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (((m - n) / 2 - 1 < i && i < m - ((m - n) / 2) ) && (j == i || j == m - 1 - i))
                    System.out.printf("x ");
                else
                    System.out.printf("O ");
            }
            System.out.println();
        }

    }
}
