import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class H4 {
    public static void main(String[] args) throws FileNotFoundException {

        File f = new File(args[0]);
        if (!f.exists()) {
            System.out.println("파일이 없음");
            System.exit(0);
        }

        Scanner in = new Scanner(f);
        ArrayList<IntArrays> arr = new ArrayList<>();


        while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            IntArrays a = new IntArrays();
            while (line.hasNextInt()) {
                a.add(line.nextInt());
            }
            arr.add(a);
        }

        for (IntArrays i : arr) {
            i.sort();
        }

        Collections.sort(arr, new Sort().new numSize());

        for (IntArrays i : arr) {
            System.out.println(i);
        }

    }
}

class IntArrays implements Comparable<IntArrays> {

    int[] list;

    public IntArrays() {
        list = new int[0];
    }

    public void add(int x) {
        int[] t = Arrays.copyOf(list, list.length + 1);
        t[list.length] = x;
        list = Arrays.copyOf(t, t.length);
    }

    public void sort() {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public int compareTo(IntArrays o) {
        return list[0] > o.list[0] ? 1 : list[0] < o.list[0] ? -1 : 0;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i : list)
            s += String.format("%-3d", i);
        return s.length() == 0 ? "null" : s;
    }
}

class Sort {
    class numSize implements Comparator<IntArrays> {
        @Override
        public int compare(IntArrays a, IntArrays b) {
            return a.compareTo(b);
        }
    }
}