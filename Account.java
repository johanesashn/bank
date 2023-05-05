public class Account {
    String name, registerDate, citizen;
    int accountNumber;
    long balance;

    public Account(String name, String citizen, int accountNumber, String registerDate, long balance){
        this.name = name;
        this.citizen = citizen;
        this.accountNumber = accountNumber;
        this.registerDate = registerDate;
        this.balance = balance;
    }

    public void deposite(long money){
        this.balance += money;
    }

    public boolean transfer(Account account, long money){
        if (this.balance - money > 100000){
            this.balance -= money;
            account.balance += money;

            return true;
        } else {
            return false;
        }

    }
}