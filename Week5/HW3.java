import java.util.Scanner;

public class HW3 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int[] alphaCounter = new int[52];   // 알파벳 수 카운트하는 배열
                                            // 0 ~ 25 (26개): 'a' ~ 'z', 26 ~ 51 (26개): 'A' ~ 'Z'
        int[] braketsCounter = new int[7];  // 괄호, 스페이스 수 카운트하는 배열
        char[] brakets = {'(', ')', '{', '}', '[', ']', ' '};
                                            // braketsCounter 배열의 각 인덱스가 무슨 글자를 의미하는지
        int linefeedCounter = 0;            // 줄 바꿈 카운터

        // 카운터 배열 초기화
        for (int i = 0; i < braketsCounter.length; i++)
            braketsCounter[i] = 0;

        for (int i = 0; i < alphaCounter.length; i++)
            alphaCounter[i] = 0;

        while (in.hasNextLine()) {

            char[] s = in.nextLine().toCharArray();

            // 한 글짜씩
            for (char c : s) {

                // 소문자 카운팅
                if (c >= 'a' && c <= 'z')
                    alphaCounter[c - 'a']++;
                // 대문자 카운팅
                else if (c >= 'A' && c <= 'Z')
                    alphaCounter[c - 'A' + 26]++;
                // 브라켓, 공백 카운팅
                else {
                    for (int i = 0; i < braketsCounter.length; i++) {
                        if (c == brakets[i])
                            braketsCounter[i]++;
                    }
                }
            }

            // 한 줄이 끝나면 줄 바꿈 카운팅
            linefeedCounter++;

        }

        // 알파벳 갯수 출력
        int printCounter = 0;
        for (int i = 0; i < alphaCounter.length; i++) {
            if (alphaCounter[i] != 0) {
                if (printCounter++ % 5 == 0 && printCounter != 0)
                    System.out.println();

                char c = (char) ((i < 26) ? (i + 'a') : (i - 26 + 'A'));
                System.out.printf("%c:%d ", c, alphaCounter[i]);

            }
        }

        // 괄호 수 출력
        for (int i = 0; i < braketsCounter.length; i++) {
            if (braketsCounter[i] != 0) {
                if (printCounter++ % 5 == 0 && printCounter != 0)
                    System.out.println();

                System.out.printf("%s:%d ", i == 6 ? "공백" : brakets[i], braketsCounter[i]);

            }
        }

        // 줄바꿈 횟수 출력
        if (linefeedCounter != 0) {
            if (printCounter % 5 == 0 && printCounter == 0)
                System.out.println();

            System.out.printf("줄바꿈:%d", linefeedCounter);

        }

    }
}
