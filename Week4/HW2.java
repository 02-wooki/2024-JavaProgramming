//2021763040 성현욱
//자바프로그래밍 4주차 과제 2024.3.30.

import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) {

        // 변수 생성 (달러 환율, 유로 환율, 엔 환율, 환전 단위) 및 프로그램 인자 이용해 초기화
        double sum = 0.0;
        int i = 0;

        double dollar, euro, yen;
        String exchangeUnit = args[3];

        dollar = Double.parseDouble(args[0]);
        euro = Double.parseDouble(args[1]);
        yen = Double.parseDouble(args[2]) / 100;

        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {

            double won;
            double result = 0;

            // 입력 스트림에서 값 추출
            int money = in.nextInt();
            String unit = in.next();

            // 화폐 단위가 같으면 환전할 필요 없음
            if (unit.equals(exchangeUnit)) {
                result = money;
            } /* else { // 화폐 단위가 다르면 환전
                // 입력받은 돈을 원화로 환전
                switch (unit) {
                    case "dollar":
                        won = money * dollar;
                        break;
                    case "euro":
                        won = money * euro;
                        break;
                    case "yen":
                        won = money * yen;
                        break;
                    case "won":
                        won = money;
                        break;
                    default:
                        won = 0;
                        System.out.println("입력 통화 오류");
                        continue;
                }

                // 원화를 프로그램 인자로 입력받은 화폐로 환전
                switch (exchangeUnit) {
                    case "dollar":
                        result = won / dollar;
                        break;
                    case "euro":
                        result = won / euro;
                        break;
                    case "yen":
                        result = won / yen;
                        break;
                    case "won":
                        result = won;
                        break;
                    default:
                        result = 0;
                        System.out.println("프로그램 인자 통화 오류");
                        return;
                }*/

                // 원화로 바꾼 후 환전하는 것은 수가 커지면 부동소수점 수 오차가 커지기 때문에
                // 교수님 테스트케이스로 테스트 시 오차 발생하여 감점
                // 바꾸고자 하는 화폐로 바로 환전해야 한다
                else {
                    String condition = unit + exchangeUnit;
                    result = switch (condition) {
                        case "dollareuro" -> money * dollar / euro;
                        case "dollaryen" -> money * dollar / yen;
                        case "dollarwon" -> money * dollar;
                        case "eurodollar" -> money * euro / dollar;
                        case "euroyen" -> money * euro / yen;
                        case "eurowon" -> money * euro;
                        case "yendollar" -> money * yen / dollar;
                        case "yeneuro" -> money * yen / euro;
                        case "yenwon" -> money * yen;
                        case "wondollar" -> money / dollar;
                        case "woneuro" -> money / euro;
                        case "wonyen" -> money / yen;
                        default -> throw new IllegalStateException("Unexpected value: " + condition);
                    };
                }

            // 환전 결과 출력
            System.out.printf("%d %s -> %.3f %s\n", money, unit, result, exchangeUnit);

            // 평균 계산을 위해 합계와 갯수 저장
            sum += result;
            i++;
        }

        System.out.printf("\n합계 : %.3f %s\n", (float)sum, exchangeUnit);
        System.out.printf("평균 : %.3f %s\n", (float)sum / i, exchangeUnit);

    }
}
