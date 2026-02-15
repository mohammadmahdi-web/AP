import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data{


    public void writer(Customer customer) {
        File file = new File("Customers.ser");
        if (file.length() == 0) {
            try {
                FileOutputStream w = new FileOutputStream(file,true);
                ObjectOutputStream r = new ObjectOutputStream(w);
                r.writeObject(customer);
                r.close();
                return;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream w = new FileOutputStream(file,true);
            TempObjectOutputStream r = new TempObjectOutputStream(w);
            r.writeObject(customer);
            r.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void writer(Account account) {
        File file = new File("Accounts.ser");
        if (file.length() == 0) {
            try {
                FileOutputStream w = new FileOutputStream(file,true);
                ObjectOutputStream r = new ObjectOutputStream(w);
                r.writeObject(account);
                r.close();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            FileOutputStream w = new FileOutputStream("Accounts.ser",true);
            TempObjectOutputStream r = new TempObjectOutputStream(w);
            r.writeObject(account);
            r.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void writer(Cashier cashier) {
        File file = new File("Cashiers.ser");
        if (file.length() == 0) {
            try {
                FileOutputStream w = new FileOutputStream(file,true);
                ObjectOutputStream r = new ObjectOutputStream(w);
                r.writeObject(cashier);
                r.close();
                return;
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        try {
            FileOutputStream w = new FileOutputStream("Cashiers.ser",true);
            TempObjectOutputStream r = new TempObjectOutputStream(w);
            r.writeObject(cashier);
            r.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void writer(Transaction transaction) {
        File file = new File("Transactions.ser");
        if (file.length() == 0) {
            try {
                FileOutputStream w = new FileOutputStream(file,true);
                ObjectOutputStream r = new ObjectOutputStream(w);
                r.writeObject(transaction);
                r.close();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            FileOutputStream w = new FileOutputStream("Transactions.ser",true);
            TempObjectOutputStream r = new TempObjectOutputStream(w);
            r.writeObject(transaction);
            r.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Customer getCustomer(String nationalcode,String username , String password) {
        File file = new File("Customers.ser");
        if (file.length() == 0) {
            return null;
        }
        Customer customer;
        FileInputStream w = null;
        try {
            w = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                customer = (Customer) r.readObject();
                if (customer.getNationalCode().equals(nationalcode)|| (customer.getUsername().equals(username) && customer.getPassword().equals(password))) {
                    return customer;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public List<Account> getAccount(String nationalCode,String Id) {
        List<Account> accounts = new ArrayList<>();
        File file = new File("Accounts.ser");
        if (file.length() == 0) {
            return null;
        }
        Account account;
        FileInputStream w = null;
        try {
            w = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                account = (Account) r.readObject();
                if (account.getAccountId().equals(Id) ||  account.getNationalCode().equals(nationalCode)) {
                    accounts.add(account);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return accounts;
    }
    public Cashier getCashier(String employeeId,String username,String password) {
        File file = new File("Cashiers.ser");
        if (file.length() == 0) {
            return null;
        }
        Cashier cashier;
        FileInputStream w = null;
        try {
            w = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                cashier = (Cashier) r.readObject();
                if (cashier.getEmployeeId().equals(employeeId) || ( cashier.getUsername().equals(username) && cashier.getPassword().equals(password) )) {
                    return cashier;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
    public Transaction getTransaction(String nationalCode,String Id) {
        File file = new File("Transactions.ser");
        if (file.length() == 0) {
            return null;
        }
        Transaction transaction;
        FileInputStream w = null;
        try {
            w = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                transaction = (Transaction) r.readObject();
                if ((transaction.getSenderCustomer().getNationalCode().equals(nationalCode) || transaction.getRecipientCustomer().getNationalCode().equals(nationalCode)) || transaction.getTransactionId().equals(Id)) {
                    return transaction;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public List<List<Transaction>> getTransactions() {
        List<List<Transaction>> transactions =  new ArrayList<>();
        transactions.add(new ArrayList<>());
        transactions.add(new ArrayList<>());
        transactions.add(new ArrayList<>());
        File file = new File("Transactions.ser");
        if (file.length() == 0) {
            return null;
        }
        Transaction transaction;
        FileInputStream w = null;
        try {
            w = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                transaction = (Transaction) r.readObject();
                if (transaction.getTransactionStatus() == null) {
                    transactions.get(0).add(transaction);
                } else if (!transaction.getTransactionStatus()) {
                    transactions.get(2).add(transaction);
                }
                else transactions.get(1).add(transaction);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return transactions;
    }
    public List<Cashier> getCashiers() {
        List<Cashier> cashiers =  new ArrayList<>();
        File file = new File("Cashiers.ser");
        if (file.length() == 0) {
            return null;
        }
        Cashier cashier;
        FileInputStream w = null;
        try {
            w = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                cashier = (Cashier) r.readObject();
                cashiers.add(cashier);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return cashiers;
    }
    public List<Customer> getCustomers() {
        List<Customer> customers =  new ArrayList<>();
        File file = new File("Customers.ser");
        if (file.length() == 0) {
            return null;
        }
        Customer customer;
        FileInputStream w = null;
        try {
            w = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                customer = (Customer) r.readObject();
                customers.add(customer);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }
    public List<Account> getAccounts() {
        List<Account> accounts =  new ArrayList<>();
        File file = new File("Accounts.ser");
        if (file.length() == 0) {
            return null;
        }
        Account account;
        FileInputStream w = null;
        try {
            w = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                account = (Account) r.readObject();
                accounts.add(account);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return accounts;
    }

    public boolean updateTransaction(Transaction transaction) {
        File file = new File("Transactions.ser");
        ObjectInputStream r =  null;
        ObjectOutputStream temp = null;
        try {
            FileInputStream w = new FileInputStream(file);
            r = new ObjectInputStream(w);
            FileOutputStream ws = new FileOutputStream("temp.ser");
            temp = new ObjectOutputStream(ws);
        }catch (IOException e) {
            e.printStackTrace();
        }

        Transaction tempTransaction;
        while (true) {
            try {
                tempTransaction = (Transaction) r.readObject();
                if (!tempTransaction.getTransactionId().equals(transaction.getTransactionId())) {
                    temp.writeObject(tempTransaction);
                }
                else {
                    temp.writeObject(transaction);
                }
            }catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }catch (EOFException e) {
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            r.close();
            temp.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        ObjectInputStream te =  null;
        ObjectOutputStream tw =  null;
        try {
            FileInputStream t = new FileInputStream("temp.ser");
            te = new ObjectInputStream(t);
            FileOutputStream wt = new FileOutputStream("Transactions.ser");
            tw = new ObjectOutputStream(wt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                tempTransaction = (Transaction) te.readObject();
                tw.writeObject(tempTransaction);
            }catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }catch (EOFException e) {
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            te.close();
            tw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
    public boolean updateAccount(Account account) {
        File file = new File("Accounts.ser");

        ObjectInputStream r =  null;
        ObjectOutputStream  temp = null;
        try {
            FileInputStream w = new FileInputStream(file);
            r = new ObjectInputStream(w);
            FileOutputStream ws = new FileOutputStream("temp.ser");
            temp = new ObjectOutputStream(ws);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Account tempAccount;
        while (true) {
            try {
                tempAccount = (Account) r.readObject();
                if (!tempAccount.getAccountId().equals(account.getAccountId())) {
                    temp.writeObject(tempAccount);
                }
                else {
                    temp.writeObject(account);
                }
            }catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }catch (EOFException e) {
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            r.close();
            temp.close();
        }catch (IOException e) {
            e.printStackTrace();
        }


        ObjectInputStream  te =  null;
        ObjectOutputStream tw =  null;
        try {
            FileInputStream t = new FileInputStream("temp.ser");
            te = new ObjectInputStream(t);
            FileOutputStream wt = new FileOutputStream("Accounts.ser");
            tw = new ObjectOutputStream(wt);
        }catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                tempAccount = (Account) te.readObject();
                tw.writeObject(tempAccount);
            }catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }catch (EOFException e) {
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            te.close();
            tw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    //id /Check/ code || >;
    public boolean checkUsernameOrNationalCode(String file, String x) {
        File f = new File(file);
        if (f.length() == 0) {
            return true;
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Person person;
        while (true) {
            try {
                person = (Person) r.readObject();
                if (person.getUsername().equals(x) || person.getNationalCode().equals(x)) {
                    return false;
                }
            }catch (EOFException e) {
                break;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            r.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean checkIdAccount(String id) {
        File file = new File("Accounts.ser");
        if (file.length() == 0) {
            return true;
        }
        Account account;
        FileInputStream w = null;
        try {
            w = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                account = (Account) r.readObject();
                if (account.getAccountId().equals(id)) {
                    return false;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
    public boolean checkIdTransaction(String id) {
        File file = new File("Transactions.ser");
        if (file.length() == 0) {
            return true;
        }
        Transaction transaction;
        FileInputStream w = null;
        try {
            w = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                transaction = (Transaction) r.readObject();
                if (transaction.getTransactionId().equals(id)) {
                    return false;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public boolean checkEmployeeId(String id) {
        File file = new File("Cashiers.ser");
        if (file.length() == 0) {
            return true;
        }
        ObjectInputStream r = null;
        try {
            r = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cashier cashier;
        while (true) {
            try {
                cashier = (Cashier) r.readObject();
                if (cashier.getEmployeeId().equals(id)) {
                    return false;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EOFException e) {
                break;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}