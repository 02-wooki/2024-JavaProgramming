import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 2) {
            System.out.println("프로그램 인자 개수 오류");
            System.exit(1);
        }
        if (!new File(args[1]).exists()) {
            System.out.println("파일을 찾을 수 없음");
            System.exit(1);
        }

        Students st = new Students(args[1], args[0]);
        st.sort();
        st.printStudents();
    }
}

class Students {
    String sortBy;
    String[] name;
    int[][] idScore; // {학번, 국, 영, 수, 등수}
    double[] avg;

    double[] subjectAvg; // 국어평균, 영어평균, 수학평균

    public Students(String fileName, String sortBy) throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));
        this.sortBy = sortBy;

        // 배열로 파일 내용 복사
        this.name = new String[0];
        this.idScore = new int[0][5];
        this.avg = new double[0];
        while (in.hasNextLine()) {
            Scanner lin = new Scanner(in.nextLine());
            expandArr(lin.nextInt(), lin.next(), lin.nextInt(), lin.nextInt(), lin.nextInt());
            lin.close();
        }

        // 빈 데이터 파일인 경우 예외처리
        if (name.length == 0) {
            System.out.printf("%s가 비어있는 파일임\n", fileName);
            System.exit(1);
        }

        // 과목별 평균 산출
        this.subjectAvg = new double[3];
        int korsum = 0, engsum = 0, mathsum = 0;
        for (int[] i : this.idScore) {
            korsum += i[1];
            engsum += i[2];
            mathsum += i[3];
        }
        this.subjectAvg[0] = (double)korsum / this.name.length;
        this.subjectAvg[1] = (double)engsum / this.name.length;
        this.subjectAvg[2] = (double)mathsum / this.name.length;

        // 등수 산출
        setRank();

        in.close();
    }

    private void expandArr(int id, String name, int kor, int eng, int math) {

        this.name = Arrays.copyOf(this.name, this.name.length + 1);
        this.name[this.name.length - 1] = name;

        int[] idScore = {id, kor, eng, math, 0};
        this.idScore = Arrays.copyOf(this.idScore, this.idScore.length + 1);
        this.idScore[this.idScore.length - 1] = idScore;

        this.avg = Arrays.copyOf(this.avg, this.avg.length + 1);
        this.avg[this.avg.length - 1] = (double)(kor + eng + math) / 3;
    }

    private void setRank() {
        // 최대값 세팅
        for (int i = 0; i < avg.length; i++) {
            idScore[i][4] = 1;
            for (int j = 0; j < avg.length; j++) {
                if (avg[i] < avg[j])
                    idScore[i][4]++;
            }
        }
    }

    public void sort() {
        switch (sortBy) {
            case "number":
                for (int i = 0; i < idScore.length - 1; i++)
                    for (int j = 0; j < idScore.length - i - 1; j++)
                        if (idScore[j][0] > idScore[j + 1][0])
                            swap(j, j + 1);
                break;
            case "ranking":
                for (int i = 0; i < idScore.length - 1; i++)
                    for (int j = 0; j < idScore.length - i - 1; j++)
                        if (idScore[j][4] > idScore[j + 1][4])
                            swap(j, j+ 1);
                break;
            case "name":
                for (int i = 0; i < name.length - 1; i++)
                    for (int j = 0; j < name.length - i - 1; j++)
                        if (name[j].compareTo(name[j + 1]) > 0)
                            swap(j, j + 1);
                break;
            default:
                System.out.println("정렬 방법이 잘못됨");
                System.exit(1);
        }
    }

    private void swap(int i, int j) {
        int[] idscTmp = Arrays.copyOf(idScore[i], idScore[i].length);
        String nameTmp = name[i];
        double avgTmp = avg[i];

        idScore[i] = Arrays.copyOf(idScore[j], idScore[j].length);
        name[i] = name[j];
        avg[i] = avg[j];

        idScore[j] = Arrays.copyOf(idscTmp, idscTmp.length);
        name[j] = nameTmp;
        avg[j] = avgTmp;
    }

    public void printStudents() {
        System.out.printf("%-7s %-4s %-4s %-4s %-5s %-4s %s\n",
                "학번", "이름", "국어", "영어", "수학", "평균", "등수");
        for (int i = 0; i < name.length; i++) {
            System.out.printf("%-8d %-4s %-5d %-5d %-5d %-5.3f %d\n",
                    idScore[i][0], name[i], idScore[i][1], idScore[i][2], idScore[i][3],
                    avg[i], idScore[i][4]);
        }
        System.out.printf("과목 평균 %12.2f %5.2f %5.2f %7.3f\n",
                subjectAvg[0], subjectAvg[1], subjectAvg[2],
                (double)(subjectAvg[0] + subjectAvg[1] + subjectAvg[2]) / 3);
    }
}