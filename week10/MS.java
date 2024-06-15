public class MS extends Graduate {
    public int semester;

    MS(String type, String number, String name, String birthday, String phone, String address) {
        super(type, number, name, birthday, phone, address);
        setSemester();
    }

    private void setSemester() {
        this.semester = Rand.r(1, 4);
    }

    @Override
    public String toString() {
        return String.format("%s, 학기: %s", super.toString(), semester);
    }
}
