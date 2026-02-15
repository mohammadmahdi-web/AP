import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        A:while (true) {
            System.out.println(" 1-login Admin \n 2-login Cashier\n 3-login Customer \n 4-Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter username: ");
                    String username = scanner.next();
                    System.out.println("Enter password: ");
                    String password = scanner.next();
                    if (username.equals("Admin") && password.equals("Admin")) bank.menuAdmin(scanner);
                    else System.out.println("Invalid username or password");
                    break;
                case 2:
                    bank.menuCashier(scanner);
                    break;
                case 3:
                    bank.menuCustomer(scanner);
                    break;
                case 4:
                    break A;
            }
        }
    }
}