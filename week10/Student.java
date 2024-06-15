import java.util.Calendar;

public class Student extends Person {
    public int admission;
    Dept department;

    Student(String type, String number, String name, String birthday, String phone, String address) {
        super(type, number, name, birthday, phone, address);
        setAdmission();
        setDepartment();
    }

    // 입학 연도는 20 ~ 25세 사이에 입학했다고 가정하고 랜덤하게 설정
    private void setAdmission() {
        Calendar calendar = Calendar.getInstance();

        admission = birthYear + 19;
        admission += Rand.r(0, 5);
        if (admission > calendar.get(Calendar.YEAR))
            admission = calendar.get(Calendar.YEAR);
    }

    // 소프트웨어학과, 컴퓨터공학과, 도시계획학과, 메카트로닉스공학과, 토목공학과 중 랜덤하게 설정
    private void setDepartment() {
        switch (Rand.r(0, 4)) {
            case 0:
                this.department = Dept.Software;
                break;
            case 1:
                this.department = Dept.Computer;
                break;
            case 2:
                this.department = Dept.City;
                break;
            case 3:
                this.department = Dept.Civil;
                break;
            case 4:
                this.department = Dept.Mechatronics;
        }
    }

    @Override
    public String toString() {
        return String.format("%s 입학년도: %s, 학과: %s", super.toString(), admission, department.getDept());
    }
}
