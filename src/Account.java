import java.io.Serializable;

public class Account implements Serializable {
    private String nationalCode;
    private String accountId;
    private double balance = 0;

    public Account(String nationalCode, String accountId, double balance) {
        this.nationalCode = nationalCode;
        this.accountId = accountId;
        this.balance = balance;
    }

    public void deposit(double balance) {
        this.balance += balance;
    }
    public void withdraw(double balance) {
        this.balance -= balance;
    }

    //get
    public String getNationalCode() {
        return nationalCode;
    }
    public String getAccountId() {
        return accountId;
    }
    public double getBalance() {
        return balance;
    }
    @Override
    public String toString() {
        return "\n id=" + accountId + ", balance=" + balance;
    }

}