public class H3 {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);

        boolean[] b = new boolean[y + 1];
        for (int i = 0; i < b.length; i++)
            b[i] = true;

        for (int i = 2; i < y / 2; i++) {
            if (b[i] == true) {
                for (int j = 2; j * i < b.length; j++)
                    b[j * i] = false;
            }
        }

        int sum = 0, num = 0;
        for (int i = x; i <= y; i++) {
            sum += b[i] ? i : 0;
            num += b[i] ? 1 : 0;
        }

        System.out.printf("합계: %d, 평균: %.3f", sum, (double)sum / num);
    }
}
