public class HW2 {
    public static void main(String[] args) {

        int treeSize = args.length;
        int cycle = 0;
        // cycle % 4
        // 0이면 덧셈, 1이면 뺄셈, 2면 곱셈, 3이면 나눗셈

        double[] tree = new double[treeSize];

        // 최초 입력
        for (int i = 0; i < args.length; i++)
            tree[i] = Double.parseDouble(args[i]);

        while (treeSize > 1) {

            if (treeSize % 2 != 0) {
                tree[treeSize - 2] = calc(cycle, tree[treeSize - 2], tree[treeSize - 1]);
                treeSize--;
            }

            for (int i = 0; i < treeSize / 2; i++)
                tree[i] = calc(cycle, tree[i * 2], tree[(i * 2) + 1]);

            treeSize /= 2;
            cycle++;
        }

        System.out.printf("%.3f\n", tree[0]);

    }

    static double calc(int op, double x, double y) {
        double output = 0;
        switch (op % 4) {
            case 0:
                output = x + y;
                break;
            case 1:
                output = x - y;
                break;
            case 2:
                output = x * y;
                break;
            case 3:
                if (y == 0.0)
                    y = 1.0;
                output = x / y;
        }

        return output;
    }

}
