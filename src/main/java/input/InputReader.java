package input;

import model.Account;
import model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader {

    private Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public User inputUser() {
        User user = null;
        try {
            System.out.print("Account: ");
            String username = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();
            System.out.print("Name: ");
            String name = scanner.next();
            System.out.print("Phone: ");
            String phone = scanner.next();
            System.out.print("Email: ");
            String email = scanner.next();
            System.out.print("Address: ");
            String address = scanner.next();
            System.out.print("DOB: ");
            String dateOfBirth = scanner.next();
            user = new User(username, password, name, phone, email, address, dateOfBirth);
        } catch (InputMismatchException e) {
            System.out.println("Error while entering information");
        }

        return user;
    }

    public Account inputAccount() {
        Account account = null;
        try {
            System.out.println("------------------- Login --------------------\n" +
                    "Press username and password !!!");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Account: ");
            String username = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();
            account = new Account(username, password);
        } catch (InputMismatchException e) {
            System.out.println("Error while entering information");
        }
        return account;
    }
}
