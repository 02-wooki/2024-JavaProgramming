// 2021763040 성현욱
// 자바프로그래밍 4주차 과제 2024.3.31.

public class HW3 {
    public static void main(String[] args) {

        // 오류 검사 (명령어, 피연산자 개수)
        int operationCode = isErrorExists(args[0]);
        if (operationCode == 0) {
            System.out.println("명령어 오류 !!!");
            return;
        } else if (operationCode != args.length) {
            System.out.println("피연산자 개수 불일치 !!!");
            return;
        }

        // 변수 선언 및 프로그램 인자 대입
        String operator = args[0];
        int operand = Integer.parseInt(args[1]);
        int opSize = configSize(Integer.parseInt(args[1]));

        // 오류 검사 (피연산자 범위)
        if (opSize == 0) {
            System.out.println("피연산자 범위 오류 !!!");
            return;
        }

        // 시프트 연산일 때
        if (operationCode == 3) {
            int shiftRange = Integer.parseInt(args[2]);
            String output = toBinary(operand, opSize);

            // 오류 검사 (시프트 연산 범위)
            if (shiftRange >= opSize) {
                System.out.println("시프트 횟수 오류 !!!");
                return;
            }

            switch (operator) {
                // 왼쪽 시프트는 자료 크기에 상관없이 연산 가능
                case "l_shift":
                    output += String.format(" << %d = ", shiftRange);
                    output += toBinary(operand << shiftRange, opSize);
                    break;

                // 우측 논리 시프트는 부호 상관없이 빈자리를 0으로 채워야 하기 때문에
                // int보다 작은 자료형이 형변환되며 1로 채워지는것을 방지하기 위해 적절 자리수만큼만 1로 마스킹
                case "r_shift":
                    output += String.format(" >> %d = ", shiftRange);
                    if (opSize == Byte.SIZE)
                        output += toBinary((operand & 0xff) >>> shiftRange, opSize);
                    else if (opSize == Short.SIZE)
                        output += toBinary((operand & 0xffff) >>> shiftRange, opSize);
                    else
                        output += toBinary((operand & 0xffffffff) >>> shiftRange, opSize);
                    break;

                // 우측 산술 시프트는 부호대로 채우면 되기 때문에 자료형 상관없이 연산 가능
                case "r_ashift":
                    output += String.format(" >> %d = ", shiftRange);
                    output += toBinary(operand >> shiftRange, opSize);
                    break;
            }

            // 출력
            System.out.println(output);

        // 범위 연산 (추출, 0, 1, 반전)
        } else if (operationCode == 4) {

            int startBit = Integer.parseInt(args[2]);
            int size = Integer.parseInt(args[3]);
            String output = toBinary(operand, opSize) + " => ";

            // 오류 검사 (연산 범위 초과)
            if (startBit + size > opSize + 1) {
                System.out.println("피연산자 범위 오류 !!!");
                return;
            }

            switch (operator) {
                case "extract":
                    output += toBinary(operand >> (startBit -1), size);
                    break;
                case "clear":
                    output += toBinary((operand & ~makeMask(startBit, size)), opSize);
                    break;
                case "set":
                    output += toBinary((operand | makeMask(startBit, size)), opSize);
                    break;
                case "complement":
                    output += toBinary((operand ^ makeMask(startBit, size)), opSize);
                    break;
            }

            System.out.println(output);
        }
    }

    // op의 2진수 구하는 프로그램
    public static String toBinary(int op, int size) {

        String bin = "";

        // 오른쪽으로 한 비트씩 밀면서 &1 로 마스킹
        for (int i = 0; i < size; i++) {
            bin = (((op >> i) & 1) == 1 ? "1" : "0") + bin;
            bin = (i % 8 == 7 && i != size-1 ? "_" : "") + bin;
        }
        return bin;
    }

    public static int makeMask(int start, int size) {

        int mask = 0;

        for (int i = start; i < start + size; i++) {
            int weight = 1;
            for (int j = 0; j < i -1; j++)  weight *= 2;
            mask += weight;
        }

        return mask;
    }

    public static int isErrorExists(String str) {
        int errorCode = 0;
        switch (str) {
            case "l_shift":
            case "r_shift":
            case "r_ashift":
                errorCode = 3;
                break;
            case "extract":
            case "clear":
            case "set":
            case "complement":
                errorCode = 4;
                break;
        }
        return errorCode;
    }

    public static int configSize(int num) {
        int size = 0;
        if (num <= Byte.MAX_VALUE && num >= Byte.MIN_VALUE)
            size = Byte.SIZE;
        else if (num <= Short.MAX_VALUE && num >= Short.MIN_VALUE)
            size = Short.SIZE;
        else
            size = Integer.SIZE;

        return size;
    }
}
