import dao.UserDAOImpl;
import input.InputReader;
import controller.UserController;
import model.Account;
import model.User;
import utils.Validator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Assignment1 {

    private UserController userController;
    private InputReader inputReader;

    public Assignment1(UserController userController, InputReader inputReader) {
        this.userController = userController;
        this.inputReader = inputReader;
    }

    public static void main(String[] args) {

        InputReader inputUser = new InputReader(new Scanner(System.in));
        UserController userController = new UserController(inputUser, new UserDAOImpl(), new Validator());
        Assignment1 assignment1 = new Assignment1(userController, inputUser);
        while (true) {
            assignment1.run();
        }
    }

    public void run() {
        System.out.print("===========Assignment 1===========\n" +
                "========== Login Program ==========\n" +
                "Press the number corresponding to the option !!!\n" +
                "Option 1 (press 1): Add Account \n" +
                "Option 2 (press 2): Login\n" +
                "Option 3 (press 3): Exit the program\n" +
                "You press the number: ");
        try {
            int option = inputReader.getScanner().nextInt();
            switch (option) {
                case 1:
                    User user = inputReader.inputUser();
                    userController.addAccount(user);
                    break;
                case 2:
                    Account account = inputReader.inputAccount();
                    userController.login(account);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("You do not press one of three numbers: 1, 2 or 3".toUpperCase());
            }
        } catch (InputMismatchException e) {
            System.out.println("you must press a number".toUpperCase() + "\t" + e.getMessage());
        }
    }
}
