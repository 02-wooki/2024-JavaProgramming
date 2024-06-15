import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HW {
    public static void main(String[] args) throws FileNotFoundException {

        // 인자 개수 예외 처리
        if (args.length != 3) {
            System.out.println("프로그램 인자 개수 오류");
            System.exit(1);
        }

        // 파일 열고 스캐너 생성
        File f = new File(args[0]);
        if (!f.exists()) {
            System.out.println(args[0] + "파일이 존재하지 않음");
            System.exit(1);
        }

        Scanner in = new Scanner(f);

        // Person 객체 배열을 가지는 객체 생성
        Persons p = new Persons();

        // 파일 읽어서 배열에 데이터 채우기
        while (in.hasNextLine()) {
            Scanner lin = new Scanner(in.nextLine());
            lin.useDelimiter("\\|");
            String type = lin.next();

            switch (type) {
                case "교수":
                    p.add(new Professor(type, lin.next(), lin.next(), lin.next(), lin.next(), lin.next()));
                    break;
                case "조교":
                    p.add(new Assistant(type, lin.next(), lin.next(), lin.next(), lin.next(), lin.next()));
                    break;
                case "학부":
                    p.add(new Under(type, lin.next(), lin.next(), lin.next(), lin.next(), lin.next()));
                    break;
                case "박사":
                    p.add(new PhD(type, lin.next(), lin.next(), lin.next(), lin.next(), lin.next()));
                    break;
                case "석사":
                    p.add(new MS(type, lin.next(), lin.next(), lin.next(), lin.next(), lin.next()));
                    break;
            }
        }

        // print 명령어
        if (args[1].equals("print")) {
            switch (args[2]) {
                case "all":
                    System.out.println(p);
                    break;
                case "student":
                case "staff":
                case "professor":
                    System.out.println(p.matchCategory(args[2]));
                    break;
                default:
                    if (args[2].length() == 14)
                        System.out.println(p.matchID(args[2]));
                    else {
                        System.out.println("print 명령어 인자 오류.");
                        System.exit(1);
                    }
            }
        // sort 명령어
        } else if (args[1].equals("sort")) {
            switch (args[2]) {
                case "ssno":
                    p.sortId();
                    break;
                case "name":
                    p.sortName();
                    break;
                case "birthdate":
                    p.sortBirth();
                    break;
                case "address":
                    p.sortAddr();
                    break;
                default:
                    System.out.println("sort 종류 오류");
                    System.exit(1);
            }
            System.out.println(p);
        } else {
            System.out.println("명령어 오류");
            System.exit(1);
        }
    }
}

class Sorting {
    class Id implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.number.equalsIgnoreCase(o2.number) ?
                    o1.name.compareTo(o2.name) : o1.number.compareTo(o2.number);
        }
    }
    class Name implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.name.equalsIgnoreCase(o2.name) ?
                    o1.number.compareTo(o2.number) : o1.name.compareTo(o2.name);
        }
    }

    class Birth implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.strBirthday.equalsIgnoreCase(o2.strBirthday) ?
                    o1.number.compareTo(o2.number) : o1.strBirthday.compareTo(o2.strBirthday);
        }
    }

    class Addr implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.address.equalsIgnoreCase(o2.address) ?
                    o1.number.compareTo(o2.number) : o1.address.compareTo(o2.address);
        }
    }
}


class Persons {
    ArrayList<Person> list = new ArrayList<Person>();

    // 데이터 추가
    public void add(Person data) {
        list.add(data);
    }

    // 컬렉션 정렬 사용하여 정렬
    public void sortId() {
        Collections.sort(list, new Sorting().new Id());
    }
    public void sortName() {
        Collections.sort(list, new Sorting().new Name());
    }
    public void sortBirth() {
        Collections.sort(list, new Sorting().new Birth());
    }
    public void sortAddr() {
        Collections.sort(list, new Sorting().new Addr());
    }

    // 주민번호가 같은 객체 찾아서 출력
    public String matchID(String id) {
        String s = String.format("%-14s %-4s %-3s %-10s %-14s %-40s 기타정보\n",
                "주민번호", "이름", "구분", "생일", "연락처", "주소");
        boolean isfound = false;
        for (Person t: list) {
            if (id.equalsIgnoreCase(t.number)) {
                s += String.format("%s\n", t);
                isfound = true;
            }
        }

        return isfound ? s : "해당 주민번호를 가진 사람이 없음";
    }

    // student/staff/professor 카테고리 찾아서 출력
    public String matchCategory(String cat) {

        if (cat.charAt(0) >= 'a')
            cat = (char)(cat.charAt(0) - 'a' + 'A') + cat.substring(1);

        String s = String.format("%-14s %-4s %-3s %-10s %-14s %-40s 기타정보\n",
                "주민번호", "이름", "구분", "생일", "연락처", "주소");
        boolean isfound = false;

        for (Person t: list) {
            if (t instanceof Student && cat.equals("Student")) {
                s += String.format("%s\n", t);
                isfound = true;
            } else if (t instanceof Staff && cat.equals("Staff")) {
                s += String.format("%s\n", t);
                isfound = true;
            } else if (t instanceof Professor && cat.equals("Professor")) {
                s += String.format("%s\n", t);
                isfound = true;
            }
        }

        return isfound ? s : "해당하는 사람이 없음";
    }

    // 전체 출력
    public String toString() {
        String s = String.format("%-14s %-4s %-3s %-10s %-14s %-48s 기타정보\n",
                "주민번호", "이름", "구분", "생일", "연락처", "주소");

        for (Person t : list) {
            s += String.format("%s\n", t);
        }

        return s;
    }
}