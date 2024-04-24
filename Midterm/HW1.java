public class HW1 {
    public static void main(String[] args) {

        int oddSum = 0, evenSum = 0;
        double realSum = 0.0, totalSum = 0.0;

        int oddSize = 0, evenSize = 0, realSize = 0, totalSize = 0;

        double oddAvg, evenAvg, realAvg, totalAvg;

        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) >= '0' && args[i].charAt(0) <= '9') {
                double num = Double.parseDouble(args[i]);
                if ((num * 10) % 10 == 0) { //정수 판별 (0이면 정수)
                    if (num % 2 == 0) {
                        evenSum += num;
                        evenSize++;
                    } else {
                        oddSum += num;
                        oddSize++;
                    }
                } else {
                    realSum += num;
                    realSize++;
                }
            }
        }

        oddAvg = oddSize == 0 ? 0.0 : (double)oddSum / oddSize;
        evenAvg = evenSize == 0 ? 0.0 : (double)evenSum / evenSize;
        realAvg = realSize == 0 ? 0.0 : realSum / realSize;

        totalSize = oddSize + evenSize + realSize;
        totalSum = oddSum + evenSum + realSum;
        totalAvg = totalSum / totalSize;

        System.out.printf("홀수 합계: %d, 평균: %.2f\n", oddSum, oddAvg);
        System.out.printf("짝수 합계: %d, 평균: %.2f\n", evenSum, evenAvg);
        System.out.printf("실수 합계: %.3f, 평균: %.2f\n", realSum, realAvg);
        System.out.printf("총 합계: %.3f, 평균: %.2f\n", totalSum, totalAvg);

    }
}
