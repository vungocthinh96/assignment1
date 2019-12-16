package controller;

import dao.UserDAO;
import dao.UserDAOImpl;
import input.InputReader;
import model.Account;
import model.User;
import utils.Validator;

import java.util.InputMismatchException;

public class UserController {
    private InputReader inputReader;
    private UserDAO userDAO;
    private Validator validator;

    public UserController(InputReader inputReader, UserDAOImpl userDAO, Validator validator) {
        this.inputReader = inputReader;
        this.userDAO = userDAO;
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
            String md5Password = validator.Encrypt(user.getPassword(), "MD5");
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
            if (account.getUsername().equals(user.getUsername()) && validator.Encrypt(account.getPassword(), "MD5").equals(user.getPassword())) {
                System.out.println("-------------------- Welcome ------------------\nHi " + account.getUsername());
                String changePassword = inputReader.getYesNo();
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
        int change = 0;
        try {
            String oldPassword = inputReader.readOldPassword();
            if (!(oldPassword.equals(password))) {
                System.out.println("old password invalid !!!");
                return 0;
            }
            System.out.println("new password: ");
            String newPassword = inputReader.readNewPassword();
            System.out.println("new password: ");
            String reNewPassword = inputReader.readNewPassword();
            if (newPassword == null || newPassword.isEmpty() || newPassword.trim().isEmpty() || !(newPassword.equals(reNewPassword))) {
                System.out.println("new password invalid !!!");
            } else {
                change  = userDAO.changePassword(username, validator.Encrypt(newPassword, "MD5"));

            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return change;
    }
}
