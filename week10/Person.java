import java.util.Scanner;

public abstract class Person implements Comparable<Person> {

    // 멤버 변수 정의
    Category type;
    public String number, name, strBirthday, phone, address;
    public int birthYear, birthMonth, birthday;

    // 생성자
    Person(String type, String number, String name, String birthday, String phone, String address) {
        this.number = number;
        this.name = name;
        this.strBirthday = birthday;
        this.phone = phone;
        this.address = address;
        setBirth();
        setType(type);
    }

    // 타입은 교수, 박사, 조교, 학부, 석사 중 랜덤하게 설정
    private void setType(String type) {
        switch (type) {
            case "교수":
                this.type = Category.Professor;
                break;
            case "박사":
                this.type = Category.PhD;
                break;
            case "조교":
                this.type = Category.Assistant;
                break;
            case "학부":
                this.type = Category.Under;
                break;
            case "석사":
                this.type = Category.MS;
                break;
            default:
                System.out.println("버?그");
                System.exit(1);
        }
    }

    // 계산 편의를 위해 정수형 생일 데이터도 생성
    private void setBirth() {
        Scanner b = new Scanner(strBirthday);
        b.useDelimiter("-");

        this.birthYear = Integer.parseInt(b.next());
        this.birthMonth = Integer.parseInt(b.next());
        this.birthday = Integer.parseInt(b.next());
    }

    @Override
    public int compareTo(Person o) {
        return this.name.equalsIgnoreCase(o.name) ?
                this.number.compareTo(o.number) : this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        String birth = String.format("%d.%d.%d", birthYear, birthMonth, birthday);
        return String.format("%-15s %-4s %-3s %-11s %-15s %-45s",
                number, name, type.getCategory(), birth, phone, address);
    }
}
