import java.io.Serializable;

public class Transaction implements Serializable {
    private String transactionId;
    private Customer senderCustomer;
    private Account senderAccount;
    private Customer recipientCustomer;
    private Account recipientAccount;
    private double amount;
    private String transactionDate;
    private Boolean transactionStatus = null;

    public Transaction(Customer senderCustomer, Account senderAccount, Customer recipientCustomer, Account recipientAccount, double amount, String transactionDate,String transactionId) {
        this.senderCustomer = senderCustomer;
        this.senderAccount = senderAccount;
        this.recipientCustomer = recipientCustomer;
        this.recipientAccount = recipientAccount;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionId =  transactionId;
    }


    //get

    public String getTransactionId() {
        return transactionId;
    }

    public Customer getSenderCustomer() {
        return senderCustomer;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public Customer getRecipientCustomer() {
        return recipientCustomer;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public Boolean getTransactionStatus() {
        return transactionStatus;
    }

    //set
    public void setTransactionStatus(Boolean transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public String toString() {
        return "Transaction Details:\n" +
                "Sender Account ID: " + senderAccount.getAccountId() + " (Name: " + senderCustomer.getName() + ")\n" +
                "Recipient Account ID: " + recipientAccount.getAccountId() + " (Name: " + recipientCustomer.getName() + ")\n" +
                "Amount: $" + amount + "\n" +
                "Transaction Date: " + transactionDate + "\n" +
                "Transaction ID: " + transactionId + "\n" +
                "Status: " + transactionStatus;
    }
}
