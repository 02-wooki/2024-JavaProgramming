import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HW1 {
    public static void main(String[] args) throws FileNotFoundException {

        File f = new File(args[0]);
        if (!f.exists()) {
            System.out.println(args[0] + " 파일을 찾을 수 없음");
            System.exit(1);
        }

        Scores sc = new Scores(args[0]);

        try {
            int id = Integer.parseInt(args[1]);
            String stInfo = sc.studentInfo(id);

            if (stInfo.equals("")) {
                System.out.printf("학번이 %d인 학생 없음\n", id);
            } else {
                System.out.printf("%-7s %-4s %s %s %s %-5s %s\n",
                        "학번", "이름", "국어", "영어", "수학", "평균", "평가");
                System.out.println(stInfo);
                System.out.printf("학생 전체 평균: %.3f\n", sc.avg);
            }
        } catch (NumberFormatException e) {
            String name = args[1];
            String[] stInfo = sc.studentInfo(name);

            if (stInfo.length == 0) {
                System.out.printf("이름이 %s인 학생 없음\n", name);
            } else {
                System.out.printf("%-7s %-4s %s %s %s %-5s %s\n",
                        "학번", "이름", "국어", "영어", "수학", "평균", "평가");
                for (String s : stInfo)
                    System.out.println(s);
                System.out.printf("학생 전체 평균: %.3f\n", sc.avg);
            }
        }
    }


}

class Scores {

    Scanner in;
    double avg;

    public Scores(String f) throws FileNotFoundException {
        this.in = new Scanner(new File(f));
        setAvg();
        this.in = new Scanner(new File(f));
    }

    private void setAvg() {
        double sum = 0;
        int num = 0;
        while (in.hasNextLine()) {
            num++;
            Scanner lin = new Scanner(in.nextLine());
            lin.nextInt();
            lin.next();
            sum += (double)(lin.nextInt() + lin.nextInt() + lin.nextInt()) / 3;
        }
        this.avg = sum / num;
    }

    String studentInfo(int id) {
        String stInfo = "";

        while (in.hasNextLine()) {
            Scanner lin = new Scanner(in.nextLine());
            int studentId = lin.nextInt();
            if (studentId == id) {
                String name = lin.next();
                int[] score = {lin.nextInt(), lin.nextInt(), lin.nextInt()};
                double avg = (double)(score[0] + score[1] + score[2]) / 3;

                stInfo = String.format("%-8d %s %3d %3d %3d %6.3f %s",
                        studentId, name, score[0], score[1], score[2], avg,
                        avg > this.avg ? "평균이상" : "평균이하");
                break;
            }
        }
        return stInfo;
    }

    String[] studentInfo(String name) {
        int size = 0;
        String[] stInfo = new String[size];

        while (in.hasNextLine()) {
            Scanner lin = new Scanner(in.nextLine());
            int id = lin.nextInt();
            String stName = lin.next();

            if (stName.equals(name)) {
                int[] score = {lin.nextInt(), lin.nextInt(), lin.nextInt()};
                double avg = (double)(score[0] + score[1] + score[2]) / 3;

                stInfo = Arrays.copyOf(stInfo, size + 1);
                stInfo[size++] = String.format("%-8d %s %3d %3d %3d %6.3f %s",
                        id, stName, score[0], score[1], score[2], avg,
                        avg > this.avg ? "평균이상" : "평균이하");
            }
        }
        return stInfo;
    }
}