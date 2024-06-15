public class PhD extends Graduate {
    public int annual;

    PhD(String type, String number, String name, String birthday, String phone, String address) {
        super(type, number, name, birthday, phone, address);
        setAnnual();
    }

    private void setAnnual() {
        annual = Rand.r(1, 6);
    }

    @Override
    public String toString() {
        return String.format("%s, 연차: %s", super.toString(), annual);
    }
}
