public class H2 {
    public static void main(String[] args) {

        int maxlen = Integer.MIN_VALUE;
        for (String s : args)
            if (maxlen < s.length())
                maxlen = s.length();


        for (int i = 0; i < maxlen; i++) {
            for (int j = 0; j < args.length; j++) {
                if (i < args[j].length())
                    System.out.print(args[j].charAt(i));
            }
        }
    }
}
