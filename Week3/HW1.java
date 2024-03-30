// 자바프로그래밍 3주차 과제

public class HW1 {
    public static void main(String[] args) {

        //연산자 존재 여부 확인
        int operatorNum = 0;
        int operatorIndex = -1;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "+":
                case "-":
                case "x":
                case "X":
                case "/":
                case "%":
                    operatorNum += 1;
                    operatorIndex = i;
                    break;
            }
        }

        //인자 개수에 따른 예외처리
        if (args.length == 0) {         // 인자가 없을 때
            System.out.printf("수식 오류");
        } else if (args.length == 1) {  // 인자가 한 개일 때
            System.out.printf("%s = 수식 오류", args[0]);
        } else if (args.length == 2) {  // 인자가 두 개일 때
            if (operatorNum == 0)         // 연산자가 없을 때
                System.out.printf("%s %s = 연산자 없음", args[0], args[1]);
            else                          // 연산자가 있을 때 (1개 혹은 2개)
                System.out.printf("%s%s = 피연산자 부족", args[0], args[1]);
        } else if (args.length == 3) {  // 인자가 세 개일 때
            System.out.printf("%s%s%s = ", args[0], args[1], args[2]);
            if (operatorNum == 0) {         // 연산자 없을 때
                System.out.printf("연산자 오류");
            } else if (operatorNum == 1) {  // 연산자 한 개일 때
                if (operatorIndex == 1)       // (정수 연산자 정수) = 정상
                    System.out.println(Calculator(Integer.parseInt(args[0]), Integer.parseInt(args[2]), args[1]));
                else                          // (연산자 정수 정수) 혹은 (정수 정수 연산자)
                    System.out.printf("수식 오류");
            } else {                        // 연산자가 두 개 이상일 때
                System.out.printf("수식 오류");
            }
        } else {                        // 인자가 네 개 이상일 때
            for (int i = 0; i < args.length; i++)
                System.out.printf("%s", args[i]);
            System.out.printf(" = 수식 오류");
        }
    }

    public static String Calculator(int arg1, int arg2, String operator) {

        String result = "";

        switch (operator) {
            case "+":
                    result = String.format("%d", arg1 + arg2);
                break;
            case "-":
                    result = String.format("%d", arg1 - arg2);
                break;
            case "x":
            case "X":
                    result = String.format("%d", arg1 * arg2);
                break;
            // 0으로 나누는 경우 예외처리
            case "/":
                    result = arg2 == 0? "분모가 0인 오류" : String.format("%.3f", (double)arg1 / arg2);
                break;
            case "%":
                    result = arg2 == 0? "분모가 0인 오류" : String.format("%d", arg1 % arg2);
                break;
        }

        return result;

    }
}
