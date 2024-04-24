public class HW3 {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("프로그램 인자 개수 오류");
            System.exit(1);
        }

        int startTimeHour = Integer.parseInt(args[0]) / 100;
        int startTimeMinute = Integer.parseInt(args[0]) % 100;
        int endTimeHour = Integer.parseInt(args[1]) / 100;
        int endTimeMinute = Integer.parseInt(args[1]) % 100;

        String startTimeStr = String.format("%s %d시", startTimeHour < 12 ? "오전" : "오후", startTimeHour < 13 ? startTimeHour : startTimeHour - 12);
        startTimeStr += startTimeMinute == 0 ? "" : String.format("%d분", startTimeMinute);
        String endTimeStr = String.format("%s %d시", endTimeHour < 12 ? "오전" : "오후", endTimeHour < 13 ? endTimeHour : endTimeHour - 12);
        endTimeStr += endTimeMinute == 0 ? "" : String.format("%d분", endTimeMinute);

        System.out.printf("%s ~ %s : ", startTimeStr, endTimeStr);

        int minute = endTimeMinute - startTimeMinute;
        int hour = endTimeHour - startTimeHour;
        if (minute < 0) {
            minute += 60;
            hour--;
        }
        if (hour < 0 || (hour == 0 && minute == 0)) {
            hour += 24;
        }

        String timeStr = String.format("%d시간 ", hour);
        timeStr += minute == 0 ? "경과" : String.format("%d분 경과", minute);
        System.out.printf("%s\n", timeStr);
    }
}
