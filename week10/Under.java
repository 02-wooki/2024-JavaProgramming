import java.util.Calendar;

public class Under extends Student{
    public int grade;
    public String club;

    Under(String type, String number, String name, String birthday, String phone, String address) {
        super(type, number, name, birthday, phone, address);
        setGrade();
        setClub();
    }

    // 학년은 입학 후 흐른시간만큼으로 하되 최대 4학년
    private void setGrade() {
        Calendar calendar = Calendar.getInstance();
        this.grade = calendar.get(Calendar.YEAR) - admission + 1;
        if (this.grade > 4)
            this.grade = 4;
    }

    // 동아리 튜닝, 데굴데굴, 식도락, Prod.By 중 하나 혹은 없음으로 설정
    private void setClub() {
        switch (Rand.r(0, 5)) {
            case 0:
                this.club = "튜닝";
                break;
            case 1:
                this.club = "데굴데굴";
                break;
            case 2:
                this.club = "식도락";
                break;
            case 3:
                this.club = "Prod.By";
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return String.format("%s, 학년: %d%s", super.toString(), grade, club == null ? "" : String.format(", 동아리: %s", club));
    }
}
