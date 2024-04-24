import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HW4 {
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 1) {
            System.out.println("프로그램 인자 개수 오류");
            System.exit(1);
        }

        String fileName = args[0];
        Scanner in = new Scanner(new File(fileName));
        int totalMax = 0, totalMin = 0, lineSize = 0;

        while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            int[] arr = new int[0];

            // 새 정수 받을때마다 planeArr 늘리기
            while (line.hasNextInt())
                arr = Arrays.copyOf(expandArr(arr, line.nextInt()), arr.length + 1);

            // 각 줄의 최대, 최솟값 찾고 배열에서 삭제
            int max = maxPop(arr);
            int min = minPop(arr);
            int minSize = 1;
            int maxSize = 1;
            lineSize++;

            for (int i: arr) {
                if (i == minSize) {
                    minSize++;
                    minPop(arr);
                } else if (i == maxSize) {
                    maxSize++;
                    maxPop(arr);
                }
            }

            totalMax += max;
            totalMin += min;

            // 한 줄 받은정수 전체 출력
            for (int i = 0; i < minSize - 1; i++)
                System.out.print(min + " ");

            for (int i : arr)
                System.out.printf("%d ", i);

            for (int i = 0; i < maxSize - 1; i++)
                System.out.print(max);
            System.out.println();
        }

        System.out.printf("최솟값 합계: %d, 평균: %.2f\n", totalMin, (double) totalMin / lineSize);
        System.out.printf("최댓값 합계: %d, 평균: %.2f\n", totalMax, (double) totalMax / lineSize);
    }

    static int maxPop(int[] source) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < source.length; i++) {
            if (max < source[i]) {
                max = source[i];
                maxIndex = i;
            }
        }

        // 꺼낸 자리 이후로 배열 땡겨주기
        for (int i = maxIndex; i < source.length - 1; i++)
            source[i] = source[i + 1];

        source = Arrays.copyOf(source, source.length - 1);

        return max;
    }

    static int minPop(int[] source) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < source.length; i++) {
            if (min > source[i]) {
                min = source[i];
                minIndex = i;
            }
        }

        for (int i = minIndex; i < source.length - 1; i++) {
            source[i] = source[i + 1];
        }

        source = Arrays.copyOf(source, source.length - 1);

        return min;
    }

    public static int[] expandArr(int[] origin, int newMem) {

        int[] newArr;
        newArr = Arrays.copyOf(origin, origin.length + 1);
        newArr[newArr.length - 1] = newMem;

        return newArr;
    }
}
