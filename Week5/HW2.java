public class HW2 {
    public static void main(String[] args) {

        int arrSize = rand(1, 100);
        int[] arr = new int[arrSize];
        int lineSize = Integer.parseInt(args[0]);

        if (lineSize <= 0) {
            System.out.println("인자 값 오류");
            System.exit(0);
        }

        // 배열 초기화
        for (int i = 0; i < arrSize; i++)
            arr[i] = rand(-100, 100);

        double arravg = avg(arr);
        int largeCount = 0;

        // 평균보다 크거나 같은값 집계
        // 예제에도 평균이랑 같으면 이상으로 집계되는데 점수 깎이면 폭동 일어남
        for (int i = 0; i < arr.length; i++)
            largeCount += arr[i] >= arravg ? 1 : 0;

        printArr(arr, lineSize);
        System.out.printf("\n평균: %.3f\n", arravg);
        System.out.printf("평균 이상: %d개\n", largeCount);
        System.out.printf("평균 이하: %d개\n", arrSize - largeCount);
    }

    public static int rand(int min, int max)  {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public static void printArr(int[] arr, int lineSize) {

        for (int i = 0; i < arr.length; i++) {
            if (i % lineSize == 0)
                System.out.println();
            System.out.printf("%4d", arr[i]);
        }
        System.out.println();

    }

    public static double avg(int[] arr) {

        int sum = 0;
        for (int n: arr)
            sum += n;

        return (double)sum/arr.length;
    }

}
