public class H1 {
    public static void main(String[] args) {
        if (args[0].length() % 2 == 1) {
            long x = Integer.parseInt(args[0].substring(0, (int)args[0].length() / 2));
            long y = Integer.parseInt(args[0].substring((int)args[0].length() / 2, (int)args[0].length() / 2 + 1));
            String zRev = args[0].substring((int) args[0].length() / 2 + 1);
            long z = Integer.parseInt(reverse(zRev));

            System.out.println(x + y + z);
        } else {
            String xstr = args[0].substring(0, args[0].length() / 2);
            String yrev = args[0].substring(args[0].length() / 2);
            String x2str = xstr.substring(0, xstr.length() /2);
            String x3rev = xstr.substring(xstr.length() / 2);

            long x = Integer.parseInt(x2str + reverse(x3rev));
            long z = Integer.parseInt(reverse(yrev));

            System.out.println(x + z);
        }
    }

    public static String reverse(String s) {
        String x = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            x += s.charAt(i);
        }

        return x;
    }
}
