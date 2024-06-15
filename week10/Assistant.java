public class Assistant extends Staff {
    public String part;

    Assistant(String type, String number, String name, String birthday, String phone, String address) {
        super(type, number, name, birthday, phone, address);
        setPart();
    }

    private void setPart() {
        switch (Rand.r(0,2)) {
            case 0:
                part = "전임";
                break;
            case 1:
                part = "근로";
                break;
            case 2:
                part = "시간제";
                break;
        }
    }

    @Override
    public String toString() {
        return String.format("%s, 구분: %s", super.toString(), part);
    }
}
