import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// 2021763040 성현욱
// 자바프로그래밍 6주차 과제
// 2024.4.13 (최종)

public class HW2 {
    public static void main(String[] args) throws FileNotFoundException {

        // 인자 개수 오류 혹은 파일명 오류 예외처리
        if (args.length != 1) {
            System.out.println("프로그램 인자 오류");
            System.exit(0);
        }
        if (!new File(args[0]).exists()) {
            System.out.printf("[%s]을 찾을 수 없음\n", args[0]);
            System.exit(0);
        }

        // 데이터를 담을 배열 생성
        int dataLength = 0;                             // 학생 수
        int[] studentId = new int[dataLength];          // 학번
        String[] studentName = new String[dataLength];  // 이름
        int korSum = 0, engSum = 0, mathSum = 0;        // 모든 학생의 국, 영, 수 성적 합
        double[] studentAvgs = new double[dataLength];  // 각 학생의 평균점수

        double maxavg = 0.0, minavg = 100.0;            // 최고 평균과 최저 평균


        Scanner in = new Scanner(new File(args[0]));

        System.out.printf("%-8s %-4s %-5s %-5s %-5s %s\n", "학번", "이름", "국어", "영어", "수학", "평균");

        while (in.hasNextLine()) {
            Scanner ln = new Scanner(in.nextLine());
            int kor, eng, math;
            double avg;

            // 한 학생의 정보를 할당
            dataLength++;

            studentId = Arrays.copyOf(expandArr(studentId, ln.nextInt()), dataLength);
            studentName = Arrays.copyOf(expandArr(studentName, ln.next()), dataLength);

            kor = ln.nextInt();
            eng =ln.nextInt();
            math = ln.nextInt();

            // 학생 개인 평균과 모든 학생의 국어, 영어, 수학 각각의 합을 구함
            avg = (double)(kor+ eng + math) / 3;
            korSum += kor;
            engSum += eng;
            mathSum += math;

            // 학생 개인 평균을 배열의 새 열에 저장
            studentAvgs = Arrays.copyOf(expandArr(studentAvgs, avg), dataLength);

            // maxavg, minavg 구하기
            if (avg > maxavg)
                maxavg = avg;
            if (avg < minavg)
                minavg = avg;

            // 학생 개인의 정보를 출력
            System.out.printf("%-9d %-4s %-6d %-6d %-6d %.2f\n",
                    studentId[dataLength - 1], studentName[dataLength - 1], kor, eng, math, studentAvgs[dataLength - 1]);
        }

        // 파일에 값이 없을때 예외처리
        if (dataLength == 0) {
            System.out.println("오류: 빈 소스 파일");
            System.exit(0);
        }

        // 국,영,수 각각의 과목 평균 출력
        System.out.printf("%-13s %-6.2f %-6.2f %-6.2f\n",
                "과목 평균: ", (double)korSum/dataLength, (double)engSum/dataLength, (double)mathSum/dataLength);

        // 최고 평균점수를 가진 학생과 최저 평균점수를 가진 학생 출력
        int[] maxIdx = maxAvgIdx(studentAvgs, maxavg);
        int[] minIdx = minAvgIdx(studentAvgs, minavg);

        System.out.printf("최고 평균: ");
        for (int i : maxIdx) {
            if (i != maxIdx[0])
                System.out.printf(", ");
            System.out.printf("%d(%s)", studentId[i], studentName[i]);
        }
        System.out.printf("\n최저 평균: ");
        for (int i : minIdx) {
            if (i != minIdx[0])
                System.out.printf(", ");
            System.out.printf("%d(%s)", studentId[i], studentName[i]);
        }
    }

    // 배열 길이 늘리고 마지막에 새 값을 넣는 메소드
    public static int[] expandArr(int[] origin, int newMem) {

        int[] tmp;
        tmp = Arrays.copyOf(origin, origin.length + 1);

        tmp[tmp.length - 1] = newMem;
        return tmp;
    }
    public static double[] expandArr(double[] origin, double newMem) {
        
        double[] tmp;
        tmp = Arrays.copyOf(origin, origin.length + 1);
        tmp[tmp.length - 1] = newMem;

        return tmp;
    }
    public static String[] expandArr(String[] origin, String newMem) {
        String[] tmp;
        tmp = Arrays.copyOf(origin, origin.length + 1);

        tmp[tmp.length - 1] = newMem;
        return tmp;
    }

    public static int[] maxAvgIdx(double[] avgs, double max) {
        int[] idxArr = new int[0];

        for (int i = 0; i < avgs.length; i++) {
            if (avgs[i] == max)
                idxArr = expandArr(idxArr, i);
        }

        return idxArr;
    }
    public static int[] minAvgIdx(double[] avgs, double min) {
        int[] idxArr = new int[0];

        for (int i = 0; i < avgs.length; i++) {
            if (avgs[i] == min)
                idxArr = expandArr(idxArr, i);
        }

        return idxArr;
    }
}
