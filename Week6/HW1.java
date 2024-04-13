import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// 2021763040 성현욱
// 자바프로그래밍 6주차 과제
// 2024.4.13 (최종)

public class HW1 {
    public static void main(String[] args) throws FileNotFoundException {

        // 프로그램 인자 오류 예외처리
        if (args.length != 1) {
            System.out.println("프로그램 인자 오류");
            System.exit(0);
        }
        // 파일명 오류 예외처리
        if (!(new File(args[0]).exists())) {
            System.out.printf("[%s]를 찾을 수 없음", args[0]);
            System.exit(0);
        }
        
        Scanner in = new Scanner(new File(args[0]));

        int[][] arr = new int[0][];   // 행 크기가 지정되지 않았고, 열 크기가 1인 초기 배열

        while (in.hasNextLine()) {

            // 열 하나를 읽어오는 스캐너 객체 생성
            Scanner line = new Scanner(in.nextLine());
            int[] planeArr = new int[0];

            // 정수를 읽을 때마다 배열의 크기를 1씩 늘리면서 배열의 마지막 위치에 정수를 저장
            while (line.hasNextInt())
                planeArr = Arrays.copyOf(expandArr(planeArr, line.nextInt()), planeArr.length + 1);

            // 한 줄을 완료한 후 배열을 오름차순 정렬 후 2차원 배열의 새 열에 저장
            sort(planeArr);
            arr = Arrays.copyOf(expandArr(arr, planeArr), arr.length + 1);


        }

        // 각 열을 오름차순 정렬 후 출력
        sort(arr);
        printArr(arr);

    }


    // 배열의 i번과 j번 인덱스의 멤버를 교환하는 메소드
    public static void swap(int[] n, int i, int j) {
        int tmp = n[i];
        n[i] = n[j];
        n[j] = tmp;
    }
    // (overriding) 2차원 배열의 i열고 j열을 교환하는 메소드
    public static void swap(int[][] n, int i, int j) {
        int[] tmp = Arrays.copyOf(n[i], n[i].length);
        n[i] = Arrays.copyOf(n[j], n[j].length);
        n[j] = Arrays.copyOf(tmp, tmp.length);
    }


    // 1차원 배열의 크기를 1 늘리고 그 자리에 새 숫자를 채우는 메소드
    public static int[] expandArr(int[] origin, int newMem) {

        int[] newArr;
        newArr = Arrays.copyOf(origin, origin.length + 1);
        newArr[newArr.length - 1] = newMem;

        return newArr;
    }
    // 2차원 배열의 열을 1 늘리고 새 열에 새로운 배열 멤버를 채우는 메소드
    // (overriding method)
    public static int[][] expandArr(int[][] origin, int[] newMem) {

        int[][] newArr;
        newArr = Arrays.copyOf(origin, origin.length + 1);
        newArr[newArr.length - 1] = newMem;

        return newArr;
    }


    // 1차원 배열을 오름차순 버블 정렬하는 메소드
    public static void sort(int[] n) {

        int loopRange = n.length;
        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < loopRange - 1; j++) {
                if (n[j] > n[j+1])
                    swap(n, j, j+1);
            }
            loopRange--;
        }
    }
    // (override) 2차원 배열의 각 열을 각 열의 첫번째 요소 기준 오름차순 버블 정렬하는 메소드
    public static void sort(int[][] n) {

        int loopRange = n.length;
        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < loopRange - 1; j++) {
                if (n[j][0] > n[j + 1][0])
                    swap(n, j, j+1);
            }
            loopRange--;
        }

    }


    // 2차원 배열을 출력하는 메소드
    public static void printArr(int[][] n) {
        for (int i = 0; i < n.length; i++) {
            for (int ii : n[i])
                System.out.printf("%5d ", ii);
            System.out.println();
        }
    }
}
