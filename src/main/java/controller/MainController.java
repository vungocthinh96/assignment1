package controller;

import dao.UserDAOImpl;
import model.Account;
import model.User;
import utils.Validator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainController {

    private UserDAOImpl userDAO;
    private Validator validator;

    public MainController(UserDAOImpl userDAO, Validator validator) {
        this.userDAO = userDAO;
        this.validator = validator;
    }

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void addAccount(User user) {
        System.out.println("--------------- Add User -----------------");
        if (user == null)
            return;
        User userCheck = userDAO.getUserByUsername(user.getUsername());
        if (userCheck == null) {
            /**
             * add user to database
             */
            String md5Password = validator.md5Encrypt(user.getPassword());
            user.setPassword(md5Password);
            userDAO.addAccountToDB(user);
            System.out.println("added account to database");
            System.out.println(
                    "Account: " + user.getUsername() + "\n" +
                            "Password: " + user.getPassword() + "\n" +
                            "Name: " + user.getName() + "\n" +
                            "Phone: " + user.getPhone() + "\n" +
                            "Email: " + user.getEmail() + "\n" +
                            "Address: " + user.getAddress() + "\n" +
                            "DOB: " + user.getDateOfBirth() + "\n"
            );
        } else {
            System.out.println("the username already exists in the database".toUpperCase());
        }

    }

    public void login(Account account) {
        if (account == null)
            return;

        if (account.getUsername() == null || account.getUsername().isEmpty() || account.getUsername().trim().isEmpty()) {
            System.out.println("username empty !!!");
            return;
        }
        if (!(validator.validatePassword(account.getPassword()))) {
            System.out.println("password invalid !!!");
            return;
        }
        try {
            User user = userDAO.getUserByUsername(account.getUsername());
            if (account.getUsername().equals(user.getUsername()) && validator.md5Encrypt(account.getPassword()).equals(user.getPassword())) {
                System.out.println("-------------------- Welcome ------------------\nHi " + account.getUsername());
                System.out.print("Do you want change password now? Y/N: ");
                Scanner scanner = new Scanner(System.in);
                String changePassword = scanner.nextLine();
                if (changePassword.equals("Y") || changePassword.equals("y")) {
                    int change = changePassword(account.getUsername(), account.getPassword());
                    if (change == 0) {
                        System.out.println("error while change password");
                    } else {
                        System.out.println("change password success !!!");
                    }
                }
            } else {
                System.out.println("invalid username or password");
            }
        } catch (NullPointerException e) {
            System.out.println("invalid username or password !!!");
        }
    }

    public int changePassword(String username, String password) {
        Scanner scanner = new Scanner(System.in);
        int change = 0;
        try {
            System.out.println("Old password: ");
            String oldPassword = scanner.nextLine();
            if (!(oldPassword.equals(password))) {
                System.out.println("old password invalid !!!");
                return 0;
            }
            System.out.println("new password: ");
            String newPassword = scanner.nextLine();
            System.out.println("new password: ");
            String reNewPassword = scanner.nextLine();
            if (newPassword == null || newPassword.isEmpty() || newPassword.trim().isEmpty() || !(newPassword.equals(reNewPassword))) {
                System.out.println("new password invalid !!!");
            } else {
                change  = userDAO.changePassword(username, validator.md5Encrypt(newPassword));

            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return change;
    }
}
