public class Rand {
    public int r(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range + min);
    }
}