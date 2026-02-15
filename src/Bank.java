import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Bank {
    public void menuAdmin(Scanner scanner) {
        System.out.println("Welcome Admin");
        A:while (true){
            System.out.printf("1-Add Cashier\t2-Add Customer\t3-Get all cashier\t4-Get cashier by employee code\n5-Get all customer\t6-Get customer by national code\t7-Customer sort by money\t8-Get total money bank\n9-Exit%n");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    addCashier();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    getCashiers();
                    break;
                case 4:
                    getCashier();
                    break;
                case 5:
                    getCustomers();
                    break;
                case 6:
                    getCustomer();
                    break;
                case 7:
                    sortCustomersByMoney();
                    break;
                case 8:
                    getTotalMoney();
                    break;
                case 9:
                    break A;
            }
        }

    }

    //method
    private String date(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime;
        formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }
    private String inputNationalCode(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter National Code:");
            String nationalCode = scanner.nextLine();
            if (nationalCode.matches("\\d{10}")){
                return nationalCode;
            }
            System.out.println("Invalid National Code it must exactly 10 digits");
            System.out.println("Try again !!!");
        }
    }
    private void addCashier() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Surname:");
        String surname = scanner.nextLine();
        String nationalCode = inputNationalCode();

        Data q = new Data();

        if (!q.checkUsernameOrNationalCode("Cashiers.ser",nationalCode)){
            System.out.println("An Cashier with this nationalCode already exists!");
            //goto?---------
            return;
        }
        System.out.println("Enter Birth Date:");
        String birthDate = scanner.nextLine();
        B:System.out.println("Enter Username:");
        String username = scanner.nextLine();
        if (!q.checkUsernameOrNationalCode("Cashiers.ser",username)){
            System.out.println("An Cashier with this username already exists!");
            //gotoB?---------
            return;
        }
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        String enrolmentDate = date();
        Random random = new Random();
        String employeeId;
        A:while (true){
            int employee = 10000000 +  random.nextInt(9000000);
            employeeId = String.valueOf(employee);
            if (q.checkEmployeeId(employeeId)){
                break A;
            }
        }

        Cashier cashier = new Cashier(employeeId,name,surname,nationalCode,birthDate,username,password,enrolmentDate);
        q.writer(cashier);
        System.out.println("Successfully added cashier");
    }
    private void addCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Surname:");
        String surname = scanner.nextLine();
        String nationalCode = inputNationalCode();

        Data q = new Data();

        if (!q.checkUsernameOrNationalCode("Customers.ser",nationalCode)){
            System.out.println("An customer with this nationalCode already exists!");
            //goto?---------
            return;
        }
        System.out.println("Enter Birth Date:");
        String birthDate = scanner.nextLine();
        B:System.out.println("Enter Username:");
        String username = scanner.nextLine();
        if (!q.checkUsernameOrNationalCode("Customers.ser",username)){
            System.out.println("An customer with this username already exists!");
            //gotoB?---------
            return;
        }
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        String enrolmentDate = date();

        Customer customer = new Customer(name,surname,nationalCode,birthDate,username,password,enrolmentDate);
        q.writer(customer);
        System.out.println("Successfully added customer");
    }
    private void getCustomers() {
        Data q = new Data();
        List<Customer> customers = q.getCustomers();
        for (int i = 0;i<customers.size();i++){
            System.out.println("--Customer "+(i+1)+"--" + customers.get(i).toString());
        }
    }
    private void getCashiers() {
        Data q = new Data();
        List<Cashier> cashiers = q.getCashiers();
        for (int i = 0;i<cashiers.size();i++){
            System.out.println("--Cashier "+(i+1)+"--" + cashiers.get(i).toString());
        }
    }
    private void getCashier() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee ID:");
        String employeeId = scanner.nextLine();
        Data r = new Data();
        Cashier cashier = r.getCashier(employeeId,"-","-");
        System.out.println(cashier.toString());
    }
    private void getCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter NationalCode: ");
        String nationalCode = scanner.nextLine();
        Data r = new Data();
        Customer customer = r.getCustomer(nationalCode,"-", "-");
        System.out.println(customer.toString());
    }
    private void getTotalMoney() {
        Data q = new Data();
        List<Account> accounts =  q.getAccounts();
        Double money = 0D;
        if (accounts != null){
            for (Account account : accounts){
                money += account.getBalance();
            }
        }
        System.out.println("Total money: " + money+"\n");
    }
    private ArrayList<ArrayList<String>> BubbleSort(ArrayList<ArrayList<String>> list){
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                double t1 = Double.parseDouble(list.get(j).get(0));
                double t2 = Double.parseDouble(list.get(j+1).get(0));
                if (t1<t2){
                    ArrayList<String> temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
}
        }
        return list;
    }
    private void sortCustomersByMoney() {
        Data q = new Data();
        List<Customer> customers = q.getCustomers();
        if (customers == null){
            System.out.println("Customer Bank is empty");
            return;
        }
        List<Account> accounts = q.getAccounts();
        if (accounts == null ) {
            for(int i = 0;i<customers.size();i++){
                System.out.println("--Customer : "+i+1+"--\tCustomer Information:\n" +
                        "Name: " + customers.get(i).getName() + "\n" +
                        "Surname: " + customers.get(i).getSurname() + "\n" +
                        "National Code: " + customers.get(i).getNationalCode() + "\n" +
                        "Birth Date: " + customers.get(i).getBirthDate() + "\n" +
                        "Username: " + customers.get(i).getUsername() + "\n" +
                        "Enrolment Date: " + customers.get(i).getEnrolmentDate()+"\n"+
                        "By Money: 0 "+"\n");
            }
            return;
        };
        ArrayList<ArrayList<String>> A = new ArrayList<>();
        A.add(new ArrayList<>());
        for (int i = 0; i<customers.size();i++){
            double money = 0;
            for (Account account : accounts){
                if (account.getNationalCode().equals(customers.get(i).getNationalCode())){
                    money += account.getBalance();
                }
            }
            A.get(i).add(String.valueOf(money));
            A.get(i).add(customers.get(i).getNationalCode());
        }
        A = BubbleSort(A);
        for (int i = 0; i<A.size();i++){
            for (int j = i+1;j<customers.size();j++){
                if (A.get(i).get(1).equals(customers.get(j).getNationalCode())){
                    System.out.println("--Customer : "+i+1+"--\tCustomer Information:\n" +
                            "Name: " + customers.get(j).getName() + "\n" +
                            "Surname: " + customers.get(j).getSurname() + "\n" +
                            "National Code: " + customers.get(j).getNationalCode() + "\n" +
                            "Birth Date: " + customers.get(j).getBirthDate() + "\n" +
                            "Username: " + customers.get(j).getUsername() + "\n" +
                            "Enrolment Date: " + customers.get(j).getEnrolmentDate()+"\n"+
                            "By Money: "+A.get(i).get(0)+"\n");
                }
            }
        }
    }
    private void reviewTransaction(){
        System.out.println("Welcome to the transaction bank");
        Data w = new Data();
        List<List<Transaction>>  transactions = w.getTransactions();
        int i = 0;
        int j = transactions.get(0).size();
        if (j == 0){
            System.out.println("Bank is no transaction for review.");
            return;
        }
        Scanner input = new Scanner(System.in);
        A:while (true){
            if (i == j){
                System.out.println("Transaction end for review.");
                break A;
            }
            Transaction transaction = transactions.get(0).get(i);
            System.out.println(transaction.toString());
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Confirm this transaction");
            System.out.println("2. Reject this transaction");
            System.out.println("3. Skip to next transaction");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    if (transaction.getSenderAccount().getBalance() <  transaction.getAmount()){
                        System.out.println("Sender have not enough balance");
                        transaction.setTransactionStatus(false);
                        w.updateTransaction(transaction);
                        return;
                    }
                    transaction.setTransactionStatus(true);
                    w.updateTransaction(transaction);
                    Account account = transaction.getSenderAccount();
                    account.withdraw(transaction.getAmount());
                    w.updateAccount(account);
                    account = transaction.getRecipientAccount();
                    account.deposit(transaction.getAmount());
                    w.updateAccount(account);
                    System.out.println("Transaction Confirmed!");
                    break;
                case 2:
                    transaction.setTransactionStatus(false);
                    w.updateTransaction(transaction);
                    System.out.println("Transaction Rejected!");
                    break;
                case 3:
                    break;
            }
            i++;
        }
    }



    public void menuCashier(Scanner scanner){
        Data q = new Data();
        System.out.println("Enter employeeId: ");
        String empire = scanner.next();
        Cashier cashier = q.getCashier(empire,"-","-");
        if (cashier == null){
            System.out.println("Cashier not found");
            return;
        }
        System.out.println("Enter Username: ");
        String username = scanner.next();
        System.out.println("Enter Password: ");
        String password = scanner.next();
        if (!cashier.login(username,password)){
            System.out.println("Username or password is incorrect");
            return;
        }
        A:while (true){
            System.out.println("1-Add Customer\t2-Get customer by national code\t3-Get Account by id\t4-Review transaction\n5-Exit");
            System.out.println("Enter Choice: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    addCustomer();
                    break ;
                case 2:
                    getCustomer();
                    break ;
                case 3:
                    System.out.println("Enter Account ID: ");
                    String accountID = scanner.nextLine();
                    getAccount(accountID);
                    break ;
                case 4:
                    reviewTransaction();
                    break ;
                case 5:
                    break A;
            }
        }
    }

    public void menuCustomer(Scanner scanner){
        Data r = new Data();
        System.out.println("Enter NationalCode: ");
        String nationalCode = scanner.next();
        Customer customer = r.getCustomer(nationalCode,"-","-");
        if (customer == null){
            System.out.println("Customer Not Found");
            return;
        }
        System.out.println("Enter Username: ");
        String username = scanner.next();
        System.out.println("Enter Password: ");
        String password = scanner.next();
        if (!customer.login(username, password)){
            System.out.println("Login Failed");
            return;
        }
        A:while (true){
            System.out.println("---Menu Customer---");
            System.out.println("1-Add Account");
            System.out.println("2-Transfer Money");
            System.out.println("3-Get All Account");
            System.out.println("4-Get Account By ID");
            System.out.println("5-Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    addAccount(customer);
                    break;
                case 2:
                    transferMoney(customer);
                    break;
                case 3:
                    getAccounts(customer);
                    break;
                case 4:
                    System.out.println("Enter Account ID: ");
                    String id = scanner.next();
                    getAccountPrint(id);
                    break;
                case 5:
                    break A;
            }
        }
    }

    private void getAccountPrint(String id){
        Account account = getAccount(id);
        if (account == null){
            System.out.println("Account Not Found");
            return;
        }
        System.out.println(account);
    }
    private void getAccounts(Customer customer){
        Data q = new Data();
        for (Account a : q.getAccount(customer.getNationalCode(),"-")){
            System.out.println(a);
        }
    }
    public void addAccount(Customer customer){
        Random random = new Random();
        Data r = new Data();
        String id;
        A:while (true){
            int Id = 1000000000 +  random.nextInt(900000000);
            id = String.valueOf(Id);
            if (r.checkIdAccount(id)) break A;
        }
        Account account = new Account(customer.getNationalCode(),id,500);
        r.writer(account);
        System.out.println("Successfully added account ,whit Account id : "+id);
    }
    private Account getAccount(String id){
        Data r = new Data();
        List<Account> accounts = r.getAccount("-",id);
        if (accounts.size() == 0){
            return null;
        }
        Account account = accounts.getFirst();
        return account;
    }
    private Customer SearchCustomer_byIdAccount(String id){
        Data r = new Data();
        Account account = getAccount(id);
        Customer customer = r.getCustomer(account.getNationalCode(),"-","-");
        return customer;
    }
    private void transferMoney(Customer customer){
        Data w =  new Data();
        Scanner input = new Scanner(System.in);
        List<Account> accounts = w.getAccount(customer.getNationalCode(),"-");
        for(int i = 0 ; i<accounts.size(); i++){
            System.out.println("Account number "+ (i+1) +" : "+accounts.get(i).toString());
        }
        System.out.println("Select your Account: ");
        int selected = input.nextInt();
        Account account = accounts.get(selected-1);
        System.out.println("Enter Amount to Transfer: ");
        double amount = input.nextDouble();
        if (account.getBalance()-amount<0){
            System.out.println("Insufficient Balance");
            return;
        }
        System.out.println("Enter the recipient account Id: ");
        String idAccount_recipient = input.next();
        Customer customerRecipient = SearchCustomer_byIdAccount(idAccount_recipient);
        if (customerRecipient == null){
            System.out.println("Account recipient not found");
            return;
        }
        Account accountRecipient = getAccount(idAccount_recipient);
        Transaction transaction = TransactionOriginator(customer,account,customerRecipient,accountRecipient,amount);
        if (transaction == null){
            return;
        }
        if (customer.getNationalCode().equals(customerRecipient.getNationalCode()) ){
            transaction.setTransactionStatus(true);
            w.writer(transaction);
            account.withdraw(amount);
            accountRecipient.deposit(amount);
            w.updateAccount(account);
            w.updateAccount(accountRecipient);
            System.out.println("Transfer Successfully");
            return;
        }
        w.writer(transaction);
        System.out.println("Transaction confirmed. Processing...");
        System.out.println("\n\n\n please wait while the cashier reviews your request.");
    }

    private Transaction TransactionOriginator(Customer customer, Account account, Customer customerRecipient, Account accountRecipient, double amount){
        String time = date();
        Data c = new Data();
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        String id;
        A:while (true){
            int Id = 10000000 +  random.nextInt(9000000);
            id = String.valueOf(Id);
            if (c.checkIdTransaction(id)) break A;
        }
        Transaction transaction = new Transaction(customer,account,customerRecipient,accountRecipient,amount,time,id);
        System.out.println(transaction);
        System.out.println("\nDo you want to confirm this transaction? (yes/no)");
        String confirm = input.next().toLowerCase();
        if (confirm.equals("no")){
            System.out.println("Transaction canceled.");
            return null;
        }
        return transaction;
    }
}