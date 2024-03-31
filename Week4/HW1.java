// 2021763040 성현욱
// 자바프로그래밍 4주차 과제 2024.3.28.

public class HW1 {
    public static void main(String[] args) {

        // 오만원권 (신사임당), 만원권 (세종), 오천원권 (퇴계 이황), 천원권 (율곡 이이)
        // 오백원 (학), 백원 (이순신 장군), 오십원 (벼), 십원 (다보탑), 오원 (거북선), 일원 (무궁화)

        int jandon = Integer.parseInt(args[0]);

        System.out.print(jandon + "원은 ");

        while (jandon != 0) {
            jandon = MoneyCounter(jandon, 50000);
            jandon = MoneyCounter(jandon, 10000);
            jandon = MoneyCounter(jandon, 5000);
            jandon = MoneyCounter(jandon, 1000);
            jandon = MoneyCounter(jandon, 500);
            jandon = MoneyCounter(jandon, 100);
            jandon = MoneyCounter(jandon, 50);
            jandon = MoneyCounter(jandon, 10);
            jandon = MoneyCounter(jandon, 5);
            jandon = MoneyCounter(jandon, 1);
        }
    }

    // money 원이 n원권으로 몇 장인지를 구하고, 잔돈을 반환하는 메소드
    // 예를들어 moeny = 57400이고 n = 50000이라면 57400원은 오만원권 1장을 출력하고 잔돈 7400원을 반환
    public static int MoneyCounter(int money, final int n) {

        // 돈이 n원권보다 적을 때 예외처리
        if (money < n) {
            return money;
        }

        // 변수선언
        int jandon; String output = "";

        // money가 n원권 몇 장에 해당하는지와 n원권으로 딱 떨어지는지를 구하는 코드
        jandon = money % n;
        money = (money - money % n) / n;

        // n원권으로 딱 떨어진다면 "n원권 몇 장이다." 를 출력하고 그렇지 않다면 "n원권 몇 장, "을 출력하는 코드
        switch (n) {
            case 50000:
                output = "오만원";
                break;
            case 10000:
                output = "만원";
                break;
            case 5000:
                output = "오천원";
                break;
            case 1000:
                output = "천원";
                break;
            case 500:
                output = "오백원 동전";
                break;
            case 100:
                output = "백원 동전";
                break;
            case 50:
                output = "오십원 동전";
                break;
            case 10:
                output = "십원 동전";
                break;
            case 5:
                output = "오원 동전";
                break;
            case 1:
                output = "일원 동전";
                break;
            default:
                output = "뭔가 잘못됐는데..";
        }

        output += String.format(" %d", money);
        output += n >= 1000 ? "장" : "개";
        output += jandon == 0 ? "이다." : ", ";
        System.out.print(output);

        // 잔돈 반환
        return jandon;
    }
}
