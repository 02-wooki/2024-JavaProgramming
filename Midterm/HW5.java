import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HW5 {
    public static void main(String[] args) throws FileNotFoundException {

        File f = new File(args[0]);

        if (!f.exists()) {
            System.out.println("파일이 없어용");
            System.exit(1);
        }

        Scanner in = new Scanner(f);
        String command = args[1];

        switch (command) {
            case "find":

                if (args.length != 3) {
                    System.out.println("명령어 매개변수 오류");
                    System.exit(1);
                }

                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    System.out.println(line);
                    boolean[] xLocated = findString(line, args[2]);
                    boolean found = false;
                    String xlocStr = "";
                    for (boolean b : xLocated) {
                        xlocStr += b ? "^" : " ";
                        found = b ? true : found;
                    }
                    System.out.printf("%s", found ? xlocStr + "\n" : "");
                }
                break;
            case "replace":

                if (args.length != 4) {
                    System.out.println("명령어 매개변수 오류");
                    System.exit(1);
                }

                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    line = replace(line, args[2], args[3]);
                    System.out.println(line);
                }

                break;
            case "remove":

                if (args.length != 3) {
                    System.out.println("명령어 매개변수 오류");
                     System.exit(1);
                }

                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    line = remove(line, args[2]);
                    System.out.println(line);
                }

                break;
            case "delete_line":

                if (args.length != 3) {
                    System.out.println("명령어 매개변수 오류");
                    System.exit(1);
                }

                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    System.out.print(deleteLine(line, args[2]));
                }

                break;
            default:
                System.out.println("명령어 오류");
                System.exit(1);
        }
    }

    static boolean[] findString(String source, String x) {

        boolean[] isMatch = new boolean[source.length()];
        for (int i = 0; i < isMatch.length; i++)
            isMatch[i] = false;

        int i = 0;
        while (i != -1 && i < source.length()) {
            if (source.indexOf(x, i) == -1) {
                i = -1;
            } else {
                i = source.indexOf(x, i);
                for (int j = i; j < i + x.length(); j++)
                    isMatch[j] = true;
                i++;
            }
        }

        return isMatch;
    }

    static String replace(String source, String x, String newx) {
        char[] cSource = source.toCharArray();
        char[] cX = newx.toCharArray();
        boolean[] xLocated = findString(source, x);

        for (int i = 0; i < cSource.length; i++) {
            if (xLocated[i]) {
                for (int j = i; j < i + x.length(); j++)
                    cSource[j] = cX[j - i];
                i += x.length() - 1;
            }
        }
        String newString = "";
        for (char c : cSource)
            newString += c;

        return newString;
    }

    static String remove(String source, String x) {

        char[] cSource = source.toCharArray();
        boolean[] xLocated = findString(source, x);

        String newString = "";
        for (int i = 0; i < cSource.length; i++) {
            if (!xLocated[i])
                newString += cSource[i];
        }

        return newString;
    }

    static String deleteLine(String source, String x) {

        boolean[] xLocated = findString(source, x);
        boolean found = false;

        for (boolean b : xLocated)
            found = b ? true : found;

        return found ? "" : source + "\n";
    }
}
