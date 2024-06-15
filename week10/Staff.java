public class Staff extends Person {
    public Dept department;

    Staff(String type, String number, String name, String birthday, String phone, String address) {
        super(type, number, name, birthday, phone, address);
        setDepartment();
    }

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
        return String.format("%s 부서: %s", super.toString(), department.getDept());
    }
}
