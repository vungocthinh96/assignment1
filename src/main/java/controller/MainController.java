package controller;

import constants.CommonConstants;
import dao.UserDAOImpl;
import datasource.DatasourceConfig;
import model.User;
import utils.CommonUtils;

import java.sql.SQLException;
import java.util.Scanner;

public class MainController {

    private UserDAOImpl userDAO;
    private InputReader inputUser;

    public MainController(UserDAOImpl userDAO, InputReader inputUser) {
        this.userDAO = userDAO;
        this.inputUser = inputUser;
    }

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public InputReader getInputUser() {
        return inputUser;
    }

    public void setInputUser(InputReader inputUser) {
        this.inputUser = inputUser;
    }

    public void addAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------- Add User -----------------");
        InputReader inputUser = new InputReader(scanner);
        User user = inputUser.inputReader();
        if (user == null)
            return;
        try {
            UserDAOImpl userDAO = new UserDAOImpl(DatasourceConfig.mysqlDataSource(
                    CommonConstants.MYSQL_DRIVER_CLASS,
                    CommonConstants.HOST,
                    CommonConstants.PORT,
                    CommonConstants.DATABASE,
                    CommonConstants.USERNAME,
                    CommonConstants.PASSWORD).getConnection());
            User userCheck = userDAO.getUserByUsername(user.getUsername());
            if (userCheck == null) {
                /**
                 * add user to database
                 */
                String md5Password = CommonUtils.md5Encrypt(user.getPassword());
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
                return;
            } else {
                System.out.println("the username already exists in the database".toUpperCase());
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void login() {
        System.out.println("------------------- Login --------------------\n" +
                "Press username and password !!!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Account: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (username == null || username.isEmpty() || username.trim().isEmpty()) {
            System.out.println("username empty !!!");
            return;
        }
        if (!(CommonUtils.validatePassword(password))) {
            System.out.println("password invalid !!!");
            return;
        }
        try {
            User user = userDAO.getUserByUsername(username);
            if (username.equals(user.getUsername()) && CommonUtils.md5Encrypt(password).equals(user.getPassword())) {
                System.out.println("-------------------- Welcome ------------------\nHi " + username);
                System.out.print("Do you want change password now? Y/N: ");
                String changePassword = scanner.nextLine();
                if (changePassword.equals("Y") || changePassword.equals("y")) {
                    int change = changePassword(username, password);
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
            return 0;
        } else {
            int changePassword = userDAO.changePassword(username, CommonUtils.md5Encrypt(newPassword));
            return changePassword;
        }
    }
}
