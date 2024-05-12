import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) throws FileNotFoundException {

        exceptionHandler(args);

        // 고객 객체 배열, 계좌 객체 배열, 스캐너 생성
        Account[] accounts = new Account[0];
        Client[] clients = new Client[0];
        Scanner acIn = new Scanner(new File(args[0]));
        Scanner clIn = new Scanner(new File(args[1]));

        // 모든 계좌 정보 객체 생성
        while (acIn.hasNextLine()) {
            Scanner acline = new Scanner(acIn.nextLine());
            accounts = Arrays.copyOf(accounts, accounts.length + 1);
            accounts[accounts.length - 1] = new Account(acline.next(), acline.next(), acline.next(), acline.nextInt());
        }

        // 모든 고객 정보 객체 생성
        while (clIn.hasNextLine()) {
            Scanner clline = new Scanner(clIn.nextLine());
            clients = Arrays.copyOf(clients, clients.length + 1);
            clients[clients.length - 1] = new Client(clline.next(), clline.next(), clline.next(), clline.next(), clline.next());

            // 해당 고객이 소유한 계좌 정보를 객체에 입력
            while (clline.hasNext()) {
                clients[clients.length - 1].setAccount(clline.next(), accounts);
            }
        }

        // 계좌주 정보 설정
        for (Account a : accounts)
            a.setOwner(clients);

        // 전체 계좌 출력
        for (Account a : accounts)
            System.out.println(a);
        System.out.println();

        // 입금, 출금, 송금 각각 5회씩 임의 수행
        int depositCounter = 0, withdrawCounter = 0, transferCounter = 0;
        boolean causedWithdrawError = false, causedTransferErrer = false;
        while (depositCounter < 5 || withdrawCounter < 5 || transferCounter < 5) {
            switch (random(0, 2)) {
                case 0: // deposit
                     if (depositCounter++ < 5) {
                        Account d = randomAccount(accounts);
                        System.out.println(d.deposit(random(100, 100000, 100)));
                    }
                    break;
                case 1: // withdraw
                    if (withdrawCounter < 5) {
                        Account w = randomAccount(accounts);
                        int withdrawAmount;

                        // 잔액부족 1회 이상 만들기
                        if (!causedWithdrawError && withdrawCounter >= 4)
                            withdrawAmount = random(w.balance + 100, w.balance + 300000, 100);
                        else
                            withdrawAmount = random(100, 300000, 100);

                        // 잔액부족 검사
                        if (withdrawAmount > w.balance)
                            causedWithdrawError = true;

                        System.out.println(w.withdraw(withdrawAmount));
                        withdrawCounter++;
                    }
                    break;
                case 2: // transfer
                    if (transferCounter < 5) {
                        Account t = randomAccount(accounts);
                        Account to = randomAccount(accounts);
                        int transferAmount;

                        // 출금계좌와 입금계좌가 같은경우 입금계좌 다시 뽑기
                        while (t.id.equals(to.id))
                            to = randomAccount(accounts);

                        // 잔액부족 1회 이상 만들기
                        if (!causedTransferErrer && transferCounter >= 4)
                            transferAmount = random(t.balance + 100, t.balance + 300000, 100);
                        else
                            transferAmount = random(100, 300000, 100);

                        // 잔액부족 검사
                        if (transferAmount > t.balance)
                            causedTransferErrer = true;

                        System.out.println(t.transfer(transferAmount, to));
                        transferCounter++;
                    }
                }
            }

        // 전체 계좌 출력
        System.out.println();
        for (Account a : accounts)
            System.out.println(a);

    }

    static Account randomAccount(Account[] accounts) {
        return accounts[random(0, accounts.length - 1)];
    }

    static int random(int min, int max)  {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
    static int random(int min, int max, int unit) {
        int range = (max - min) + 1;
        int num = (int)(Math.random() * range) + min;
        return num - (num % unit);
    }

    static void exceptionHandler(String[] args) {
        if (args.length != 2) {
            System.out.println("프로그램 인자 개수 불일치");
            System.exit(1);
        }

        try {
            Scanner inA = new Scanner(new File(args[0]));
            Scanner inP = new Scanner(new File(args[1]));
        } catch (FileNotFoundException e) {
            System.out.println("파일이 존재하지 않음");
            System.exit(1);
        }
    }
}

class Client {

    String id, name, sex, moblie;
    Address address;
    Account[] accounts;

    public Client() {
        accounts = new Account[0];
    }
    public Client(String id, String name, String sex, String mobile, String address) {
        this();
        this.id = id;
        this.name = name;
        this.sex = sex.equals("f") ? "여성" : "남성";
        this.moblie = mobile;
        this.address = new Address(address);
    }

    public String setAccount(String accId, Account[] accounts) {
        boolean isFound = false;
        for (Account a : accounts) {
            if (a.id.equals(accId)) {
                this.accounts = Arrays.copyOf(this.accounts, this.accounts.length + 1);
                this.accounts[this.accounts.length - 1] = a;
                isFound = true;
            }
        }
        return isFound ? accounts[accounts.length - 1].id : "Account not Found";
    }

    public String toString() {
        return String.format("%s(%s/%s), 연락처: %s, 거주지: %s",
                name, id, sex, moblie, address);
    }
}

class Account {

    String category, id;
    int balance;
    Date openDate;
    Client owner;

    public Account(String category, String id, String openDate, int balance) {
        this.category = category;
        this.id = id;
        this.balance = balance;
        this.openDate = new Date(openDate);
    }

    public void setOwner(Client[] clients) {
        for (Client c : clients)
            for (Account a : c.accounts)
                if (a.id == this.id) {
                    this.owner = c;
                    return;
                }
    }

    public static Account findAccount(String acc, Account[] accounts) {
        for (Account a : accounts)
            if (a.id.equals(acc))
                return a;
        return null;
    }

    public String deposit(int amount) {
        this.balance += amount;
        return String.format("<입금 %d원>[%s-%s:잔액(%d원)]", amount, id, category, balance);
    }

    private boolean isWithdrawAble(int amount) {
        return balance >= amount ? true : false;
    }

    public String withdraw(int amount) {
        // 잔액이 출금액보다 많거나 같으면 출금
        // 잔액이 출금액보다 적으면 "잔액 부족" 출력
        String result = String.format("<출금 %d원>[%s-%s:", amount, id, category);
        if (isWithdrawAble(amount)) {
            balance -= amount;
            result += String.format("잔액(%d원)]", balance);
        } else {
            result += String.format("잔액(%d원)]: 잔액 부족", balance);
        }
        return result;
    }

    public String transfer(int amount, Account to) {
        // 출금 계좌의 잔액이 출금액보다 많거나 같으면 송금
        // 잔액이 출금액보다 적으면 "잔액 부족" 출력
        String result = String.format("<이체 %d원>[%s-%s:", amount, id, category);
        if (isWithdrawAble(amount)) {
            this.balance -= amount;
            to.balance += amount;
            result += String.format("잔액(%d원)] -> [%s-%s:잔액(%d원)]",
                    this.balance, to.id, to.category, to.balance);
        } else {
            result += String.format("잔액(%d원)] -> [%s-%s:잔액(%d원)]: 잔액 부족",
                    this.balance, to.id, to.category, to.balance);
        }
        return result;
    }

    public String toString() {
        return String.format("* %s[%s] 잔액 %s (개설일 %s)\n [계좌주: %s]",
                category, id, balance, openDate, owner);
    }
}

class Date {

    int year, month, day;

    public Date(String strDate) {
        String[] tmp = strDate.split("-");
        year = Integer.parseInt(tmp[0]);
        month = Integer.parseInt(tmp[1]);
        day = Integer.parseInt(tmp[2]);
    }

    public String toString() {
        return String.format("%d.%d.%d", year, month, day);
    }

}

class Address {
    String si, gu, dong;

    public Address(String strAddress) {
        String[] tmp = strAddress.split("_");
        switch (tmp[0]) {
            case "서울":
                si = tmp[0] + "특별시";
                break;
            case "부산":
            case "울산":
            case "인천":
            case "대구":
            case "대전":
            case "광주":
                si = tmp[0] + "광역시";
                break;
            default:
                si = tmp[0];
        }
        gu = tmp[1];
        dong = tmp[2];
    }

    public String toString() {
        return String.format("%s %s %s", si, gu, dong);
    }
}