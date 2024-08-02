import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class H5 {
    public static void main(String[] args) {
        if (args[0].length() > 10) {
            String[] x = args[0].split("-");
            System.out.printf("%s %s", strDate(x[0]), strTime(x[1]));
        } else if (args[0].length() == 10) {
            if (args.length > 1)
                System.out.printf("%s %s", strDate(args[0]), strTime(args[1]));
            else
                System.out.println(strDate(args[0]));
        } else if (args[0].length() == 8) {
            if (args.length > 1)
                System.out.printf("%s %s", strDate(args[0]), strTime(args[1]));
            else {
                if (args[0].split(":")[0].equals(args[0]))
                    System.out.println(strDate(args[0]));
                else
                    System.out.println(strTime(args[0]));
            }
        } else if (args[0].length() == 6) {
            System.out.println(strTime(args[0]));
        }
    }

    public static String strTime(String s) {
        int hour, minute, second;

        if (s.length() == 8) {
            String[] x = s.split(":");
            hour = Integer.parseInt(x[0]);
            minute = Integer.parseInt(x[1]);
            second = Integer.parseInt(x[2]);
        } else {
            hour = Integer.parseInt(s.substring(0, 2));
            minute = Integer.parseInt(s.substring(2, 4));
            second = Integer.parseInt(s.substring(4, 6));
        }

        String t = hour < 12 ? "오전 " : "오후 ";
        t += (hour < 13 ? hour : hour - 12) + "시 ";
        t += minute + "분 ";
        t += second + "초";

        return t;
    }

    public static String strDate(String s) {
        Calendar c = Calendar.getInstance();
        int year, month, day;

        if (s.length() == 10) {
            String[] x = s.split("/");
            month = Integer.parseInt(x[0]);
            day = Integer.parseInt(x[1]);
            year = Integer.parseInt(x[2]);
        } else {
            year = Integer.parseInt(s.substring(0, 4));
            month = Integer.parseInt(s.substring(4, 6));
            day = Integer.parseInt(s.substring(6, 8));
        }

        c.set(year, month - 1, day);
        String d = year + "년 " + month + "월 " + day + "일 (";

        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                d += "일요일";
                break;
            case Calendar.MONDAY:
                d += "월요일";
                break;
            case Calendar.TUESDAY:
                d += "화요일";
                break;
            case Calendar.WEDNESDAY:
                d += "수요일";
                break;
            case Calendar.THURSDAY:
                d += "목요일";
                break;
            case Calendar.FRIDAY:
                d += "금요일";
                break;
            case Calendar.SATURDAY:
                d += "토요일";
        }

        return d + ")";
    }
}
