package controller;

import model.User;
import utils.CommonUtils;
import java.util.Scanner;

public class InputReader {

    private Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public User inputReader() {
        System.out.print("Account: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("DOB: ");
        String dateOfBirth = scanner.nextLine();
        String checkInput = CommonUtils.validateInput(username, password,
                phone, email, dateOfBirth);
        if(!checkInput.equals("valid")) {
            return null;
        }
        return new User(username, password, name, phone, email, address, dateOfBirth);
    }
}
